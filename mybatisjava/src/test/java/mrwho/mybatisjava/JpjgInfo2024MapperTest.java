//package mrwho.mybatisjava;
//
//import com.alibaba.druid.support.json.JSONUtils;
//import com.alibaba.excel.EasyExcel;
//import com.alibaba.excel.context.AnalysisContext;
//import com.alibaba.excel.event.AnalysisEventListener;
//import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
//import com.fasterxml.jackson.databind.ObjectMapper;
//import jakarta.annotation.Resource;
//import lombok.Data;
//import lombok.EqualsAndHashCode;
//import lombok.SneakyThrows;
//import lombok.ToString;
//import lombok.extern.slf4j.Slf4j;
//import mrwho.mybatisjava.entity.BladeUser;
//import mrwho.mybatisjava.entity.JpjgInfo2024;
//import mrwho.mybatisjava.mapper.JpjgInfo2024Mapper;
//import mrwho.mybatisjava.service.BladeUserService;
//import mrwho.mybatisjava.service.JpjgInfoGenService;
//import okhttp3.MediaType;
//import okhttp3.OkHttpClient;
//import okhttp3.Request;
//import okhttp3.RequestBody;
//import okhttp3.Response;
//import okhttp3.ResponseBody;
//import okio.BufferedSource;
//import org.apache.commons.lang3.ObjectUtils;
//import org.junit.jupiter.api.Test;
//import org.springblade.core.tool.utils.DesUtil;
//import org.springblade.core.tool.utils.ObjectUtil;
//import org.springblade.core.tool.utils.StringUtil;
//import org.springframework.boot.test.context.SpringBootTest;
//
//import java.io.IOException;
//import java.time.Duration;
//import java.util.ArrayList;
//import java.util.HashMap;
//import java.util.List;
//import java.util.Map;
//import java.util.concurrent.ConcurrentHashMap;
//
//@SpringBootTest(classes = MybatisplusApplication.class)
//@Slf4j
//
//public class JpjgInfo2024MapperTest {
//    @Resource
//    JpjgInfo2024Mapper jpjgInfo2024Mapper;
//    @Resource
//    JpjgInfoGenService jpjgInfoGenService;
//    @Resource
//    BladeUserService bladeUserService;
//
//    private final static String BLADEAUTH = "bearer eyJhbGciOiJIUzI1NiIsInR5cCI6IkpXVCJ9.eyJ0ZW5hbnRfaWQiOiIwMDAwMDAiLCJsb2NhbF9hcmVhX2NvZGUiOiIiLCJkZXBhcnRtZW50X2lkIjoiIiwidXNlcl9uYW1lIjoiYWRtaW4iLCJhcmVhX2NvZGUiOiIwIiwiZGVwdF9uYW1lIjoi5rGf6KW_55yB5pWZ6IKy5Y6FIiwicmVhbF9uYW1lIjoi566h55CG5ZGYIiwiYXV0aG9yaXRpZXMiOlsiYWRtaW5pc3RyYXRvciJdLCJjbGllbnRfaWQiOiJzYWJlciIsInJvbGVfbmFtZSI6ImFkbWluaXN0cmF0b3IiLCJsaWNlbnNlIjoicG93ZXJlZCBieSBibGFkZXgiLCJwb3N0X2lkIjoiIiwidXNlcl9pZCI6IjExMjM1OTg4MjE3Mzg2NzUyMDEiLCJyb2xlX2lkIjoiMTEyMzU5ODgxNjczODY3NTIwMSIsInNjb3BlIjpbImFsbCJdLCJuaWNrX25hbWUiOiLnrqHnkIblkZgiLCJwb3N0X2NvZGUiOiIiLCJvYXV0aF9pZCI6IiIsImRldGFpbCI6eyJ0eXBlIjoid2ViIiwiZXh0IjoiMTExMSIsImNvZGUiOiJjb2RlIn0sImV4cCI6MTcwODkzOTEyNiwiZGVwdF9pZCI6IjEyNzgxNTg0MDcxODk2ODAxMjgiLCJqdGkiOiI3NjFhMWVjZC03YWM0LTQwZGMtYWUzZC02MTcyOGMzYzIyNjYiLCJhY2NvdW50IjoiYWRtaW4ifQ.ISIRdjtyLWIyEtlAhtxdpq1B5AzAHYm1kTQ68TTkQZ8";
//
//    private static final List<String> 错误列表 = new ArrayList<>();
//    @Test
//    public void SelectAndInsert() {
//
//        final String phoneStr = "18079900345,18907996015,13426690527,13979859795,15879979557,13979975055,18079921557,13979916943,17707997757,13979933820,18979922115,15079991425,15870098652";
//        final String roleId = "1650663897047339009";//社会单位
//        final String postId = "1525452183245942786";
//        final String postName = "联网单位值班员";
//        List<JpjgInfo2024> jpjgInfo2024s = jpjgInfo2024Mapper.select20240226();
//        jpjgInfo2024s.forEach(value -> {
//
//            String account = value.getPhone();
//            String realName = value.getPeople();
//            String deptName = value.getCompanyName();
//            String areaCode = value.getAreaCode();
//            String deptId = value.getId();
//
//            try {
//                if(realName.length()>=20){
//                    realName = realName.substring(0,19);
//                }
//                String 发送新增社会单位人员 = 发送新增社会单位人员请求(account, realName, deptName, areaCode, 加密密码("Zg119#2024"), deptId, roleId,postId);
//                log.info(发送新增社会单位人员);
//                错误列表.add(发送新增社会单位人员);
//            } catch (IOException e) {
//                throw new RuntimeException(e);
//            }
//        });
////        System.out.println(错误列表);
//    }
//
//
//
//    public static String 加密密码(String data) {
//        return DesUtil.encryptToBase64(data, "Zg119@secret2021");
//    }
//
//    public static String 发送新增社会单位人员请求(String account, String realName, String deptName, String areaCode, String password, String deptId, String roleId, String postId) throws IOException {
//        OkHttpClient client = new OkHttpClient().newBuilder().retryOnConnectionFailure(true)
//                                      .build();
//        Map<String,String> mapString = new HashMap<>(){{
//            put("account",account);
//            put("realName",realName);
//            put("deptName",deptName);
//            put("areaCode",areaCode);
//            put("password",password);
//            put("password2",password);
//            put("deptId",deptId);
//            put("roleId",roleId);
//            put("postId",postId);
//            put("name",realName);
//            put("receivePushMessage","1");
//            put("messagePushType","phone,message");
//        }};
//        MediaType mediaType = MediaType.parse("application/json;charset=UTF-8");
//
//        String jsonString = JSONUtils.toJSONString(mapString);
//
//        RequestBody body = RequestBody.create(mediaType, jsonString);
//
//        Request request = new Request.Builder()
//                                  .url("http://219.145.215.184:8843/api/blade-user/submit")
//                                  .method("POST", body)
//                                  .addHeader("Accept", "application/json, text/plain, */*")
//                                  .addHeader("Accept-Language", "zh-CN,zh;q=0.9")
//                                  .addHeader("Authorization", "Basic c2FiZXI6c2FiZXJfc2VjcmV0")
//                                  .addHeader("Blade-Auth", BLADEAUTH)
//                                  .addHeader("Connection", "keep-alive")
//                                  .addHeader("User-Agent", "Mozilla/5.0 (Windows NT 10.0; Win64; x64) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/121.0.0.0 Safari/537.36")
//                                  .addHeader("cSessionId", "0fb35f54-0301-4fbd-813f-43e814cfae07")
//                                  .addHeader("platCode", "default")
//                                  .addHeader("roleId", "1123598816738675201")
//                                  .build();
//
//        Response response = client.newCall(request).execute();
//        BufferedSource source = response.body().source();
//        if (ObjectUtils.isNotEmpty(source)) {
//            String s1 = source.readUtf8Line();
////            log.info(s1);
//            return s1;
//        } else {
//            String message = response.message();
////            log.info(message);
//            return message;
//        }
//
//
//    }
//    @Test
//    @SneakyThrows
//    public void sendRequest(){
//        OkHttpClient client = new OkHttpClient().newBuilder()
//                                      .build();
//        MediaType mediaType = MediaType.parse("text/plain");
//        RequestBody body = RequestBody.create(mediaType, "");
//        Request request = new Request.Builder()
//                                  .url("http://219.145.215.184:8843/api/blade-user/page?account=13507921592&current=1&size=10")
//                                  .method("GET", null)
//                                  .addHeader("Accept", "application/json, text/plain, */*")
//                                  .addHeader("Accept-Language", "zh-CN,zh;q=0.9")
//                                  .addHeader("Authorization", "Basic c2FiZXI6c2FiZXJfc2VjcmV0")
//                                  .addHeader("Blade-Auth", "bearer eyJhbGciOiJIUzI1NiIsInR5cCI6IkpXVCJ9.eyJ0ZW5hbnRfaWQiOiIwMDAwMDAiLCJsb2NhbF9hcmVhX2NvZGUiOiIiLCJkZXBhcnRtZW50X2lkIjoiIiwidXNlcl9uYW1lIjoiYWRtaW4iLCJhcmVhX2NvZGUiOiIwIiwiZGVwdF9uYW1lIjoi5rGf6KW_55yB5pWZ6IKy5Y6FIiwicmVhbF9uYW1lIjoi566h55CG5ZGYIiwiYXV0aG9yaXRpZXMiOlsiYWRtaW5pc3RyYXRvciJdLCJjbGllbnRfaWQiOiJzYWJlciIsInJvbGVfbmFtZSI6ImFkbWluaXN0cmF0b3IiLCJsaWNlbnNlIjoicG93ZXJlZCBieSBibGFkZXgiLCJwb3N0X2lkIjoiIiwidXNlcl9pZCI6IjExMjM1OTg4MjE3Mzg2NzUyMDEiLCJyb2xlX2lkIjoiMTEyMzU5ODgxNjczODY3NTIwMSIsInNjb3BlIjpbImFsbCJdLCJuaWNrX25hbWUiOiLnrqHnkIblkZgiLCJwb3N0X2NvZGUiOiIiLCJvYXV0aF9pZCI6IiIsImRldGFpbCI6eyJ0eXBlIjoid2ViIiwiZXh0IjoiMTExMSIsImNvZGUiOiJjb2RlIn0sImV4cCI6MTcwODY4MjAzNSwiZGVwdF9pZCI6IjEyNzgxNTg0MDcxODk2ODAxMjgiLCJqdGkiOiJmOGFhN2I5ZC04ODE3LTRhMDAtOWE4OC1lOGZmM2ViNzE3OTIiLCJhY2NvdW50IjoiYWRtaW4ifQ.JlqV-w9XmcQWMaVwT_JZgosO-PcvCxIja4JGT4XNhgU")
//                                  .addHeader("Connection", "keep-alive")
//                                  .addHeader("Cookie", "SESSION=MDQ5YjE4MTctOWE5ZS00MmRlLWE0NjUtYmI0YWZkNWM2Yjll; saber-access-token=eyJhbGciOiJIUzI1NiIsInR5cCI6IkpXVCJ9.eyJ0ZW5hbnRfaWQiOiIwMDAwMDAiLCJsb2NhbF9hcmVhX2NvZGUiOiIiLCJkZXBhcnRtZW50X2lkIjoiIiwidXNlcl9uYW1lIjoiYWRtaW4iLCJhcmVhX2NvZGUiOiIwIiwiZGVwdF9uYW1lIjoi5rGf6KW_55yB5pWZ6IKy5Y6FIiwicmVhbF9uYW1lIjoi566h55CG5ZGYIiwiYXV0aG9yaXRpZXMiOlsiYWRtaW5pc3RyYXRvciJdLCJjbGllbnRfaWQiOiJzYWJlciIsInJvbGVfbmFtZSI6ImFkbWluaXN0cmF0b3IiLCJsaWNlbnNlIjoicG93ZXJlZCBieSBibGFkZXgiLCJwb3N0X2lkIjoiIiwidXNlcl9pZCI6IjExMjM1OTg4MjE3Mzg2NzUyMDEiLCJyb2xlX2lkIjoiMTEyMzU5ODgxNjczODY3NTIwMSIsInNjb3BlIjpbImFsbCJdLCJuaWNrX25hbWUiOiLnrqHnkIblkZgiLCJwb3N0X2NvZGUiOiIiLCJvYXV0aF9pZCI6IiIsImRldGFpbCI6eyJ0eXBlIjoid2ViIiwiZXh0IjoiMTExMSIsImNvZGUiOiJjb2RlIn0sImV4cCI6MTcwODY4MjAzNSwiZGVwdF9pZCI6IjEyNzgxNTg0MDcxODk2ODAxMjgiLCJqdGkiOiJmOGFhN2I5ZC04ODE3LTRhMDAtOWE4OC1lOGZmM2ViNzE3OTIiLCJhY2NvdW50IjoiYWRtaW4ifQ.JlqV-w9XmcQWMaVwT_JZgosO-PcvCxIja4JGT4XNhgU; saber-refresh-token=eyJhbGciOiJIUzI1NiIsInR5cCI6IkpXVCJ9.eyJ0ZW5hbnRfaWQiOiIwMDAwMDAiLCJsb2NhbF9hcmVhX2NvZGUiOiIiLCJkZXBhcnRtZW50X2lkIjoiIiwidXNlcl9uYW1lIjoiYWRtaW4iLCJhcmVhX2NvZGUiOiIwIiwiZGVwdF9uYW1lIjoi5rGf6KW_55yB5pWZ6IKy5Y6FIiwicmVhbF9uYW1lIjoi566h55CG5ZGYIiwiYXV0aG9yaXRpZXMiOlsiYWRtaW5pc3RyYXRvciJdLCJjbGllbnRfaWQiOiJzYWJlciIsInJvbGVfbmFtZSI6ImFkbWluaXN0cmF0b3IiLCJsaWNlbnNlIjoicG93ZXJlZCBieSBibGFkZXgiLCJwb3N0X2lkIjoiIiwidXNlcl9pZCI6IjExMjM1OTg4MjE3Mzg2NzUyMDEiLCJyb2xlX2lkIjoiMTEyMzU5ODgxNjczODY3NTIwMSIsInNjb3BlIjpbImFsbCJdLCJuaWNrX25hbWUiOiLnrqHnkIblkZgiLCJwb3N0X2NvZGUiOiIiLCJhdGkiOiJmOGFhN2I5ZC04ODE3LTRhMDAtOWE4OC1lOGZmM2ViNzE3OTIiLCJvYXV0aF9pZCI6IiIsImRldGFpbCI6eyJ0eXBlIjoid2ViIiwiZXh0IjoiMTExMSIsImNvZGUiOiJjb2RlIn0sImV4cCI6MTcwOTI4MDIzMCwiZGVwdF9pZCI6IjEyNzgxNTg0MDcxODk2ODAxMjgiLCJqdGkiOiIyMjhmYTJiNi1lNjc3LTQ0Y2ItODRlYS1mYWY4OWRhM2FkMDkiLCJhY2NvdW50IjoiYWRtaW4ifQ.yFgU7oOU6jWvl0qv-O3acSqz9wH0UlcOVY8e_ILL758")
//                                  .addHeader("Referer", "http://219.145.215.184:8843/user/")
//                                  .addHeader("User-Agent", "Mozilla/5.0 (Windows NT 10.0; Win64; x64) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/121.0.0.0 Safari/537.36")
//                                  .addHeader("cSessionId", "cf003fe2-be30-41cf-8e12-1e1f2f209063")
//                                  .addHeader("platCode", "default")
//                                  .addHeader("roleId", "1123598816738675201")
//                                  .build();
//        Response response = client.newCall(request).execute();
//        BufferedSource source = response.body().source();
//        if (ObjectUtils.isNotEmpty(source)) {
//            System.out.println((source.readUtf8Line()));
//        } else {
//
//            System.out.println(response.message());
//        }
//    }
//}
