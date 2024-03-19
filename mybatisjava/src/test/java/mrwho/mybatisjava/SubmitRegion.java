package mrwho.mybatisjava;

import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import jakarta.annotation.Resource;
import lombok.extern.slf4j.Slf4j;
import mrwho.mybatisjava.entity.Region;
import mrwho.mybatisjava.plugins.OkHttpClientFactory;
import mrwho.mybatisjava.service.RegionService;
import okhttp3.MediaType;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.RequestBody;
import okhttp3.Response;
import okio.BufferedSource;
import org.apache.commons.collections4.ListUtils;
import org.apache.commons.lang3.ObjectUtils;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.scheduling.concurrent.ThreadPoolTaskExecutor;

import java.io.IOException;
import java.util.List;
import java.util.Map;
import java.util.concurrent.CompletableFuture;
import java.util.concurrent.CopyOnWriteArrayList;
import java.util.concurrent.ExecutionException;

@Slf4j
@SpringBootTest(classes = MybatisplusApplication.class)
public class SubmitRegion {
    static String BladeAuth = "bearer eyJhbGciOiJIUzI1NiIsInR5cCI6IkpXVCJ9.eyJ0ZW5hbnRfaWQiOiIwMDAwMDAiLCJsb2NhbF9hcmVhX2NvZGUiOiIzNjAxMDAiLCJkZXBhcnRtZW50X2lkIjoiIiwidXNlcl9uYW1lIjoiYWRtaW4iLCJhcmVhX2NvZGUiOiIwIiwiZGVwdF9uYW1lIjoi57O757uf566h55CG5ZGYIiwicmVhbF9uYW1lIjoiYWRtaW4iLCJhdXRob3JpdGllcyI6WyJiYXNpY0xldmVsIiwiYWRtaW5pc3RyYXRvciIsImVtZXJnZW5jeV9hZG1pbiIsImNvcnBzIiwic2VydmljZSJdLCJjbGllbnRfaWQiOiJzYWJlciIsInJvbGVfbmFtZSI6ImFkbWluaXN0cmF0b3Isc2VydmljZSxiYXNpY0xldmVsLGVtZXJnZW5jeV9hZG1pbixjb3JwcyIsImxpY2Vuc2UiOiJwb3dlcmVkIGJ5IGJsYWRleCIsInBvc3RfaWQiOiIxNTI1NDUyMTgzMjQ1OTQyNzg2LDE2MzAzODQyMDk4NDc2OTc0MDksMTEyMzU5ODgxNzczODY3NTIwOCIsInVzZXJfaWQiOiIxMTIzNTk4ODIxNzM4Njc1MjAxIiwicm9sZV9pZCI6IjExMjM1OTg4MTY3Mzg2NzUyMDEsMTU2MjM1MzA1MzMyNjc1Nzg4OSwxNTk1NjExNjU1ODM2ODQ0MDM0LDE2NTc2MzMyNDM4NTkwNTg2ODksMTYyMzYzMDczMzI4MzgwNzIzNCIsInNjb3BlIjpbImFsbCJdLCJuaWNrX25hbWUiOiJhZG1pbiIsInBvc3RfY29kZSI6ImZpcmVfMSx1bml0X2R1dHkseWpnbHkwMSIsIm9hdXRoX2lkIjoiIiwiZGV0YWlsIjp7InR5cGUiOiJ3ZWIiLCJleHQiOiIxMTExIiwiY29kZSI6ImNvZGUifSwiZXhwIjoxNzEwNTc4MTQ3LCJkZXB0X2lkIjoiamt6eDAwMDAxMSIsImp0aSI6IjZjMTIxMjVhLWEyYTItNGVjZi04MGQ2LWNiMWU0MmE3ZWJiMSIsImFjY291bnQiOiJhZG1pbiJ9.Lyvy1t3Rx-Bk-dmSqh_ftWrzMTbcZ0WrJCgNb_ukjqg" ;
    @Resource
    private RegionService regionService;
    private static final ObjectMapper objectMapper = new ObjectMapper();
    private static final List<Region> regionList = new CopyOnWriteArrayList<>();
    private static final OkHttpClientFactory factory = new OkHttpClientFactory();
    @Resource
    private ThreadPoolTaskExecutor threadPoolTaskExecutor;
    
    
    @Test
    void submit() {
        List<Region> regions = regionService.getBaseMapper().selectList(null);
        
        List<List<Region>> partition = ListUtils.partition(regions, 6);
        List<CompletableFuture<Void>> collect = partition.stream().map(k -> CompletableFuture.runAsync(() -> k.forEach(t -> {
            try {
                submit(t);
            } catch (IOException e) {
                throw new RuntimeException(e);
            }
        }), threadPoolTaskExecutor)).toList();
        collect.parallelStream().forEach(k -> {
            try {
                Void unused = k.get();
            } catch (InterruptedException | ExecutionException e) {
                throw new RuntimeException(e);
            }
        });
        regionList.forEach(k -> {
            try {
                submit(k);
            } catch (IOException e) {
                log.info("error : ", e);
                throw new RuntimeException(e);
            }
        });
        threadPoolTaskExecutor.shutdown();
    }
    
    private static void submit(Region region) throws IOException {
        
        long threadId = Thread.currentThread().getId();
        OkHttpClient client = factory.getInstance(threadId);
        
        MediaType mediaType = MediaType.parse("application/json;charset=UTF-8");
        RequestBody body = RequestBody.create(mediaType, objectMapper.writeValueAsString(region));
        Request request = new Request.Builder()
                                  .url("http://ceshi.jxgh.vip:8843/api/blade-system/region/submit")
                                  .method("POST", body)
                                  .addHeader("Accept", "application/json, text/plain, */*")
                                  .addHeader("Accept-Language", "zh-CN,zh;q=0.9")
                                  .addHeader("Authorization", "Basic c2FiZXI6c2FiZXJfc2VjcmV0")
                                  .addHeader("Blade-Auth", BladeAuth)
                                  .addHeader("Connection", "keep-alive")
                                  .addHeader("Content-Type", "application/json;charset=UTF-8")
                                  .addHeader("Origin", "http://ceshi.jxgh.vip:8843")
                                  .addHeader("Referer", "http://ceshi.jxgh.vip:8843/user")
                                  .addHeader("User-Agent", "Mozilla/5.0 (Windows NT 10.0; Win64; x64) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/122.0.0.0 Safari/537.36")
                                  .addHeader("cSessionId", "cf003fe2-be30-41cf-8e12-1e1f2f209063")
                                  .addHeader("platCode", "default")
                                  .addHeader("roleId", "1123598816738675201")
                                  .build();
        
        try (Response response = client.newCall(request).execute()) {
            
            assert response.body() != null;
            BufferedSource source = response.body().source();
            if (ObjectUtils.isNotEmpty(source)) {
                String s1 = source.readUtf8Line();
                Map<String, Object> map
                        = objectMapper.readValue(s1, new TypeReference<>() {
                });
                Boolean o = (Boolean) map.get("success");
                if (!o) {
                    regionList.add(region);
                }
                log.info(s1);
                //            return s1;
            } else {
                String message = response.message();
                regionList.add(region);
                log.info(message);
                //            return message;
            }
        }
    }
}
