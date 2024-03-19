package mrwho.mybatisjava;

import com.alibaba.druid.support.json.JSONUtils;
import com.baomidou.mybatisplus.core.toolkit.StringUtils;
import jakarta.annotation.Resource;
import lombok.SneakyThrows;
import lombok.extern.slf4j.Slf4j;
import mrwho.mybatisjava.entity.Region;
import mrwho.mybatisjava.mapper.RegionMapper;
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
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.springframework.beans.BeanUtils;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.scheduling.concurrent.ThreadPoolTaskExecutor;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.CompletableFuture;
import java.util.concurrent.CopyOnWriteArrayList;
import java.util.concurrent.ExecutionException;
import java.util.stream.Stream;


@Slf4j
@SpringBootTest(classes = MybatisplusApplication.class)
public class GetAreaFromStats {
    static final String preUrl = "https://www.stats.gov.cn/sj/tjbz/tjyqhdmhcxhfdm/2023/";
    static final String startUrl = preUrl + "index.html";
    
    static final String URL_XPATH = "/html/body/table[2]/tbody/tr[1]/td/table/tbody/tr[2]/td/table/tbody/tr/td/table/tbody/tr/td[1]/a";
    static final String TOWN_CODE_XPATH = "/html/body/table[2]/tbody/tr[1]/td/table/tbody/tr[2]/td/table/tbody/tr/td/table/tbody/tr/td[1]";
    static final String NAME_XPATH = "/html/body/table[2]/tbody/tr[1]/td/table/tbody/tr[2]/td/table/tbody/tr/td/table/tbody/tr/td[2]/a";
    static final String TOWN_NAME_XPATH = "/html/body/table[2]/tbody/tr[1]/td/table/tbody/tr[2]/td/table/tbody/tr/td/table/tbody/tr/td[3]";
    List<Region> list = new CopyOnWriteArrayList<>();
    static final WebDriverFactory webDriverFactory = new WebDriverFactory();
    @Resource
    private RegionService regionService;
    @Resource
    private RegionMapper regionMapper;
    @Resource
    private ThreadPoolTaskExecutor threadPoolTaskExecutor;
    
    @Test
    @SneakyThrows
    public void ASdawsd() {
        List<Region> regions = regionService.getBaseMapper().selectList(null);
        WebDriver driver = webDriverFactory.getInstance();
        driver.get(startUrl);
        List<WebElement> elements = driver.findElements(new By.ByXPath("/html/body/table[2]/tbody/tr[1]/td/table/tbody/tr[2]/td/table/tbody/tr/td/table/tbody/tr/td/a"));
        String ancestors = "0";
        String treeNames = "";
        List<Region> regionList = elements.stream().map(k -> {
            String url = k.getAttribute("href");
            String code = k.getAttribute("href").substring(preUrl.length(), preUrl.length() + 2) + "0000";
            String name = k.getText();
            Region region = new Region();
            region.setProvinceName(name);
            region.setProvinceCode(code);
            region.setCode(code);
            region.setAncestors("0");
            region.setTreeNames(name);
            region.setName(name);
            region.setUrl(url);
            return region;
        }).toList();
        Stream<Region> concat = regionList.stream().skip(11).limit(2);
        
        List<CompletableFuture<Void>> list1 = concat.map(k -> CompletableFuture.runAsync(() -> {
            Region region = k;
            String name = region.getName();
            String code = region.getCode();
            String url = region.getUrl();
            this.getCity(url, code, region);
        }, threadPoolTaskExecutor)).toList();
        
        list1.forEach(k -> {
            try {
                k.get();
            } catch (InterruptedException | ExecutionException e) {
                throw new RuntimeException(e);
            }
            
        });
        

        List<List<Region>> partition = ListUtils.partition(list, 1000);
        partition.parallelStream().forEach(k->{
            boolean b = regionService.saveBatch(k);
            System.out.println(b);
        });

        System.out.println(list.size());
    }
    
//    @Test
//    @SneakyThrows
//    public void ASdawsd1() {
//
//        WebDriver driver = webDriverFactory.getInstance();
//        driver.get(startUrl);
//
//        List<WebElement> elements = driver.findElements(new By.ByXPath("/html/body/table[2]/tbody/tr[1]/td/table/tbody/tr[2]/td/table/tbody/tr/td/table/tbody/tr/td/a"));
//        String ancestors = "0";
//        String treeNames = "";
//        log.info("省份数量{}", elements.size());
//        elements.forEach(k -> {
//            {
//                String url = k.getAttribute("href");
//                String code = k.getAttribute("href").substring(preUrl.length(), preUrl.length() + 2) + "0000";
//                String name = k.getText();
//                Region region = new Region();
//                region.setProvinceName(name);
//                region.setProvinceCode(code);
//                this.getCity(url, code, ancestors, name, region);
//            }
//        });
//        Runtime.getRuntime().exec("taskkill -im chromedriver.exe -f");
//        Runtime.getRuntime().exec("taskkill -im  chrome.exe -f");
//        boolean b = regionService.saveBatch(list, 5000);
//
//        System.out.println(b);
//        System.out.println(list.size());
//
//
//    }
    
    public void getCity(String cityUrl, String parentId, Region region) {
        WebDriver driver = webDriverFactory.getInstance();
        driver.get(cityUrl);
        List<WebElement> urlXpath = driver.findElements(new By.ByXPath(URL_XPATH));
        List<WebElement> nameXpath = driver.findElements(new By.ByXPath(NAME_XPATH));
        List<Region> regionList = new ArrayList<>(urlXpath.size());
        for (int i = 0; i < urlXpath.size(); i++) {
            Region copy = new Region();
            BeanUtils.copyProperties(region, copy);
            WebElement urlElement = urlXpath.get(i);
            WebElement nameElement = nameXpath.get(i);
            String name = nameElement.getText();
            String url = urlElement.getAttribute("href");
            String code = urlElement.getText().substring(0, 6);
            copy.setCityName(name);
            copy.setCityCode(code);
            copy.setTreeNames(copy.getTreeNames().concat("/"+name));
            copy.setAncestors(copy.getAncestors().concat(","+code));
            copy.setUrl(url);
            regionList.add(copy);
        }
        regionList.forEach(k -> {
            String url = k.getUrl();
            String name = k.getName();
            String code = k.getCode();
            if (StringUtils.isEmpty(url)) {
                return;
            }
            this.getDistrict(url, code, k);
        });
        
    }
    
    public void getDistrict(String cityUrl, String parentId, Region region) {
        WebDriver driver = webDriverFactory.getInstance();
        driver.get(cityUrl);
        List<WebElement> urlXpath = driver.findElements(new By.ByXPath(URL_XPATH));
        List<WebElement> nameXpath = driver.findElements(new By.ByXPath(NAME_XPATH));
        List<Region> regionList = new ArrayList<>(urlXpath.size());
        for (int i = 0; i < urlXpath.size(); i++) {
            WebElement urlElement = urlXpath.get(i);
            WebElement nameElement = nameXpath.get(i);
            String url = urlElement.getAttribute("href");
            String code = urlElement.getText().substring(0, 6);
            String name = nameElement.getText();
           
            Region disReign = Region.getDisReign(parentId, region);
            disReign.setDistrictName(name);
            disReign.setDistrictCode(code);
            disReign.setAncestors(disReign.getAncestors().concat(","+code));
            disReign.setTreeNames(disReign.getTreeNames().concat("/"+name));
            disReign.setCode(code);
            disReign.setName(name);
            disReign.setSort(i);
            disReign.setUrl(url);
            regionList.add(disReign);
        }
        list.addAll(regionList);
        regionList.forEach(k->{
            String url = k.getUrl();
            String code = k.getCode();
            if (StringUtils.isEmpty(url)) {
                return;
            }
            this.getTown(url, code, k);
        });
        
    }
    
    public void getTown(String cityUrl, String parentId, Region region) {
        WebDriver driver = webDriverFactory.getInstance();
        driver.get(cityUrl);
        List<WebElement> urlXpath = driver.findElements(new By.ByXPath(URL_XPATH));
        List<WebElement> nameXpath = driver.findElements(new By.ByXPath(NAME_XPATH));
        List<Region> regionList = new ArrayList<>(urlXpath.size());
        for (int i = 0; i < urlXpath.size(); i++) {
            WebElement urlElement = urlXpath.get(i);
            WebElement nameElement = nameXpath.get(i);
            String url = urlElement.getAttribute("href");
            String code = urlElement.getText().substring(0, 9);
            String name = nameElement.getText();
            Region disReign = Region.getTownReign(parentId, region);
            disReign.setTownCode(code);
            disReign.setTownName(name);
            disReign.setUrl(url);
            disReign.setAncestors(disReign.getAncestors().concat(","+code));
            disReign.setTreeNames(disReign.getTreeNames().concat("/"+name));
            disReign.setCode(code);
            disReign.setName(name);
            disReign.setSort(i);
            regionList.add(disReign);
           
        }
        list.addAll(regionList);
        regionList.forEach(k->{
            String url = k.getUrl();
            String code = k.getCode();
            if (StringUtils.isEmpty(url)) {
                return;
            }
            this.getVillage(url, code, k);
        });
    }
    
    public void getVillage(String cityUrl, String parentId, Region region) {
        WebDriver driver = webDriverFactory.getInstance();
        driver.get(cityUrl);
        List<WebElement> urlXpath = driver.findElements(new By.ByXPath(TOWN_CODE_XPATH));
        List<WebElement> nameXpath = driver.findElements(new By.ByXPath(TOWN_NAME_XPATH));
        for (int i = 1; i < urlXpath.size(); i++) {
            WebElement urlElement = urlXpath.get(i);
            WebElement nameElement = nameXpath.get(i);
            String url = urlElement.getAttribute("href");
            String code = urlElement.getText().substring(0, 12);
            String name = nameElement.getText();
            if (StringUtils.endsWith(name, "村村委会")) {
                name = name.substring(0, name.length() - 3);
            }
            if (StringUtils.endsWith(name, "村委会")) {
                name = name.substring(0, name.length() - 2);
            }
            if (StringUtils.endsWith(name, "居委会")) {
                name = name.substring(0, name.length() - 3);
            }
            if (StringUtils.endsWith(name, "村村民委员会")) {
                name = name.substring(0, name.length() - 5);
            }
            if (StringUtils.endsWith(name, "村民委员会")) {
                name = name.substring(0, name.length() - 4);
            }
            if (StringUtils.endsWith(name, "居民委员会")) {
                name = name.substring(0, name.length() - 5);
            }
            if (StringUtils.endsWith(name, "委员会")) {
                name = name.substring(0, name.length() - 3);
            }
            
            Region disReign = Region.getVillageReign(parentId, region);
            disReign.setVillageCode(code);
            disReign.setVillageName(name);
            disReign.setAncestors(disReign.getAncestors().concat(","+code));
            disReign.setTreeNames(disReign.getTreeNames().concat("/"+name));
            disReign.setCode(code);//河北省/沧州市/东光县/于桥镇/李习庄村村民委员会
            disReign.setName(name);
            disReign.setSort(i);
            list.add(disReign);
        }
    }
    
    static String BladeAuth = "bearer eyJhbGciOiJIUzI1NiIsInR5cCI6IkpXVCJ9.eyJ0ZW5hbnRfaWQiOiIwMDAwMDAiLCJsb2NhbF9hcmVhX2NvZGUiOiIzNjAxMDAiLCJkZXBhcnRtZW50X2lkIjoiIiwidXNlcl9uYW1lIjoiYWRtaW4iLCJhcmVhX2NvZGUiOiIwIiwiZGVwdF9uYW1lIjoi57O757uf566h55CG5ZGYIiwicmVhbF9uYW1lIjoiYWRtaW4iLCJhdXRob3JpdGllcyI6WyJiYXNpY0xldmVsIiwiYWRtaW5pc3RyYXRvciIsImVtZXJnZW5jeV9hZG1pbiIsImNvcnBzIiwic2VydmljZSJdLCJjbGllbnRfaWQiOiJzYWJlciIsInJvbGVfbmFtZSI6ImFkbWluaXN0cmF0b3Isc2VydmljZSxiYXNpY0xldmVsLGVtZXJnZW5jeV9hZG1pbixjb3JwcyIsInB3ZFNhbWUiOm51bGwsImxpY2Vuc2UiOiJwb3dlcmVkIGJ5IGJsYWRleCIsInBvc3RfaWQiOiIxNTI1NDUyMTgzMjQ1OTQyNzg2LDE2MzAzODQyMDk4NDc2OTc0MDksMTEyMzU5ODgxNzczODY3NTIwOCIsInVzZXJfaWQiOiIxMTIzNTk4ODIxNzM4Njc1MjAxIiwicm9sZV9pZCI6IjExMjM1OTg4MTY3Mzg2NzUyMDEsMTU2MjM1MzA1MzMyNjc1Nzg4OSwxNTk1NjExNjU1ODM2ODQ0MDM0LDE2NTc2MzMyNDM4NTkwNTg2ODksMTYyMzYzMDczMzI4MzgwNzIzNCIsInNjb3BlIjpbImFsbCJdLCJuaWNrX25hbWUiOiJhZG1pbiIsInBvc3RfY29kZSI6ImZpcmVfMSx1bml0X2R1dHkseWpnbHkwMSIsIm9hdXRoX2lkIjoiIiwiZGV0YWlsIjp7InR5cGUiOiJ3ZWIiLCJleHQiOiIxMTExIiwiY29kZSI6ImNvZGUifSwiZXhwIjoxNzEwNTY2MDUxLCJkZXB0X2lkIjoiamt6eDAwMDAxMSIsImp0aSI6ImZkZTZmZjcxLWRhNzAtNGE1NC1hZWI2LWY4NjkyZTZiODRkMiIsImFjY291bnQiOiJhZG1pbiJ9.T8872wjRzQ1R0tFstZWm38-tC9p68Te6BwzWGYq-_Dw";
    
    private static void submit(Region region) throws IOException {
        OkHttpClient client = new OkHttpClient().newBuilder()
                                      .build();
        MediaType mediaType = MediaType.parse("application/json;charset=UTF-8");
        RequestBody body = RequestBody.create(mediaType, JSONUtils.toJSONString(region));
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
        Response response = client.newCall(request).execute();
        BufferedSource source = response.body().source();
        if (ObjectUtils.isNotEmpty(source)) {
            String s1 = source.readUtf8Line();
            log.info(s1);
//            return s1;
        } else {
            String message = response.message();
            log.info(message);
//            return message;
        }
    }
}
