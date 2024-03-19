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
//import lombok.extern.slf4j.Slf4j;
//import mrwho.mybatisjava.entity.BladeUser;
//import mrwho.mybatisjava.entity.JpjgInfoGen;
//import mrwho.mybatisjava.mapper.JpjgInfo2024Mapper;
//import mrwho.mybatisjava.mapper.JpjgInfoGenMapper;
//import mrwho.mybatisjava.service.BladeUserService;
//import mrwho.mybatisjava.service.JpjgInfo2024Service;
//import mrwho.mybatisjava.service.JpjgInfoGenService;
//import okhttp3.MediaType;
//import okhttp3.OkHttpClient;
//import okhttp3.Request;
//import okhttp3.RequestBody;
//import okhttp3.Response;
//import okhttp3.ResponseBody;
//import okio.BufferedSource;
//import org.apache.commons.lang3.ObjectUtils;
//import org.jsoup.internal.StringUtil;
//import org.junit.jupiter.api.Test;
//
//import org.springframework.boot.test.context.SpringBootTest;
//
//import java.io.IOException;
//import java.util.ArrayList;
//import java.util.HashMap;
//import java.util.List;
//import java.util.Map;
//import java.util.concurrent.ConcurrentHashMap;
//
//@SpringBootTest(classes = MybatisplusApplication.class)
//@Slf4j
//public class AddNewJXYunWei {
//    @Resource
//    JpjgInfo2024Mapper jpjgInfo2024Mapper;
//    @Resource
//    JpjgInfoGenService jpjgInfoGenService;
//    @Resource
//    JpjgInfoGenMapper jpjgInfoGenMapper;
//    @Resource
//    BladeUserService bladeUserService;
//    private final static String BLADEAUTH = "bearer eyJhbGciOiJIUzI1NiIsInR5cCI6IkpXVCJ9.eyJ0ZW5hbnRfaWQiOiIwMDAwMDAiLCJsb2NhbF9hcmVhX2NvZGUiOiIiLCJkZXBhcnRtZW50X2lkIjoiIiwidXNlcl9uYW1lIjoiYWRtaW4iLCJhcmVhX2NvZGUiOiIwIiwiZGVwdF9uYW1lIjoi5rGf6KW_55yB5pWZ6IKy5Y6FIiwicmVhbF9uYW1lIjoi566h55CG5ZGYIiwiYXV0aG9yaXRpZXMiOlsiYWRtaW5pc3RyYXRvciJdLCJjbGllbnRfaWQiOiJzYWJlciIsInJvbGVfbmFtZSI6ImFkbWluaXN0cmF0b3IiLCJsaWNlbnNlIjoicG93ZXJlZCBieSBibGFkZXgiLCJwb3N0X2lkIjoiIiwidXNlcl9pZCI6IjExMjM1OTg4MjE3Mzg2NzUyMDEiLCJyb2xlX2lkIjoiMTEyMzU5ODgxNjczODY3NTIwMSIsInNjb3BlIjpbImFsbCJdLCJuaWNrX25hbWUiOiLnrqHnkIblkZgiLCJwb3N0X2NvZGUiOiIiLCJvYXV0aF9pZCI6IiIsImRldGFpbCI6eyJ0eXBlIjoid2ViIiwiZXh0IjoiMTExMSIsImNvZGUiOiJjb2RlIn0sImV4cCI6MTcwODk0MjE4NiwiZGVwdF9pZCI6IjEyNzgxNTg0MDcxODk2ODAxMjgiLCJqdGkiOiJkYjE0MDYwZi1iZTE4LTQxZWItYWQwZi05MTk0NDdiMjVhMDYiLCJhY2NvdW50IjoiYWRtaW4ifQ.uM3oEJNBS2eHZ6nGBwiEOH6t0Te-PnTuZ6THPkYoqmY";
//    private static final ConcurrentHashMap<String, Map> 政务中心缓存 = new ConcurrentHashMap<>();
//    private static final ConcurrentHashMap<String, DemoData> 已新增用户缓存 = new ConcurrentHashMap<>();
//    @Test
//    public void insertjpjgInfoGenService(){
//        JpjgInfoGen jpjgInfoGen = new JpjgInfoGen();
//        jpjgInfoGen.setAddress("123123");
//        jpjgInfoGen.setCompanyName("123123");
//        int insert = jpjgInfoGenMapper.insert(jpjgInfoGen);
//
//        System.out.println(insert);
//    }
//    @Test
//    public void AddNewJXYunWei1() {
//        /**
//         *{
//         *   "id": "1658395012299829249",
//         *   "parentId": "1474209088492560386",
//         *   "hasChildren": false,
//         *   "title": "装维人员",
//         *   "key": "1658395012299829249",
//         *   "value": "1658395012299829249",
//         *   "type": ""
//         * }
//         */
//        List<DemoData> demoData = 读取Xlsx();
//        List<String> collect = demoData.stream().map(DemoData::get账号).toList();
//        BladeUser one = bladeUserService
//                                .getOne(new LambdaQueryWrapper<BladeUser>().in(BladeUser::getAccount, collect));
//        demoData.parallelStream().forEach(value -> {
//            try {
//                DemoData demoData1 = 已新增用户缓存.get(value.get账号());
//                if (ObjectUtil.isNotEmpty(demoData1)) {
//                    return;
//                }
//                已新增用户缓存.put(value.账号, value);
//                Map 查询政务运营中心 = 查询政务运营中心(value);
//
//                String deptName = (String) 查询政务运营中心.get("deptName");
//                String areaCode = (String) 查询政务运营中心.get("areaCode");
//                String deptId = (String) 查询政务运营中心.get("id");
//                String realName = value.get姓名();
//                String account = value.get账号();
//                String roleId = "1658395012299829249";
//                String postId = "1422479628868071435";
//
//                String 发送新增运维人员请求 = 发送新增运维人员请求(account, realName, deptName, areaCode, 加密密码("Zg119#2024"), deptId, roleId, postId);
//                if (发送新增运维人员请求.contains("false")) {
//                    log.info(发送新增运维人员请求.concat("/" + account));
//                }
//
//            } catch (Exception e) {
//                throw new RuntimeException(e);
//            }
//        });
//        System.out.println(已新增用户缓存);
//    }
//
//    /**
//     * {id=1270565047885324288, createUser=, createDept=, createTime=, updateUser=, updateTime=,
//     * status=0, isDeleted=-1, viewCode=jkzx000004, areaCode=360981, corpCode=,
//     * corpName=, industryType=16, isControlled=, companyType=control_center,
//     * localAreaCode=360981, lng=115.777473, lat=28.166615, thirdId=, dataId=, industryDept=0,
//     * monitorType=, provId=, cityId=, countyId=, townId=, villageId=, gridId=, tenantId=,
//     * fullName=丰城市教育局, sort=null, remark=, parentId=1267387064992354304, ancestors=0,,12781584071896801281267387064992354304,,
//     * deptName=丰城市教育局, deptType=, deptLevel=, halfCheckedKeys=, mergeArea=, children=[],
//     * hasChildren=false, deptFire={}, deptIndustry={}, deptInsurance={}, deptMaintenance={}, deptOperate={},
//     * parentName=, companyTypeName=,
//     * areaName=江西省/宜春市/丰城市, localAreaName=, policeFlag=false, areaCodePrefix=, deptNames=[],
//     * currentAreaCode=0, areaCodeOrigin=, platCode=, companyTypeList=[], roleControll=false}
//     *
//     * @param data
//     * @return
//     * @throws Exception
//     */
//    public Map 查询政务运营中心(DemoData data) throws Exception {
//
//        final ObjectMapper objectMapper = new ObjectMapper();
//
//        Map o1 = 政务中心缓存.get(data.get区号() + data.get区县());
//        if (ObjectUtil.isNotEmpty(o1)) {
//            return o1;
//        }
//        ResponseBody 查询结果;
//        if (StringUtil.isBlank(data.get区县())) {
//            查询结果 = 发送查询政务运营中心请求(data.get区号());
//        } else {
//            查询结果 = 发送查询政务运营中心请求(data.get区县());
//            String s = 查询结果.source().readUtf8Line();
//            Map tmpMap = objectMapper.readValue(s, Map.class);
//            List list = (List) ((Map) tmpMap.get("data")).get("records");
//            if (list.size() == 0) {
//                //若根据区县找不到,先尝试去掉 区县最后的
//                if (data.get区县().endsWith("市") || data.get区县().endsWith("县")) {
//                    String substring = data.get区县().substring(0, data.get区县().length() - 1);
//                    查询结果 = 发送查询政务运营中心请求(substring);
//                } else {
//                    // 若根据区县找不到,且 不以 市/县 结尾,则用 区号
//                    查询结果 = 发送查询政务运营中心请求(data.get区号());
//                }
//            } else {
//                政务中心缓存.put(data.get区号() + data.get区县(), (Map) list.get(0));
//                return (Map) list.get(0);
//            }
//        }
//
//        String s = 查询结果.source().readUtf8Line();
//        Map tmpMap = objectMapper.readValue(s, Map.class);
//        Map o = (Map) ((List) ((Map) tmpMap.get("data")).get("records")).get(0);
//        政务中心缓存.put(data.get区号() + data.get区县(), o);
//        return o;
//    }
//
//    private ResponseBody 发送查询政务运营中心请求(String deptName) throws IOException {
//        OkHttpClient client = new OkHttpClient().newBuilder()
//                                      .build();
//        Request request = new Request.Builder()
//                                  .url("http://219.145.215.184:8843/api/blade-system/dept/page?deptName=" + deptName + "&companyType=control_center&current=1&size=10")
//                                  .method("GET", null)
//                                  .addHeader("Accept", "application/json, text/plain, */*")
//                                  .addHeader("Accept-Language", "zh-CN,zh;q=0.9")
//                                  .addHeader("Authorization", "Basic c2FiZXI6c2FiZXJfc2VjcmV0")
//                                  .addHeader("Blade-Auth", BLADEAUTH)
//                                  .addHeader("Connection", "keep-alive")
//                                  .addHeader("Cookie", "SESSION=YjBmZTEwYmItNjU5MC00MjZkLWI1ZGEtZWZiZTg1MjMwZWU0; saber-access-token=eyJhbGciOiJIUzI1NiIsInR5cCI6IkpXVCJ9.eyJ0ZW5hbnRfaWQiOiIwMDAwMDAiLCJsb2NhbF9hcmVhX2NvZGUiOiIiLCJkZXBhcnRtZW50X2lkIjoiIiwidXNlcl9uYW1lIjoiYWRtaW4iLCJhcmVhX2NvZGUiOiIwIiwiZGVwdF9uYW1lIjoi5rGf6KW_55yB5pWZ6IKy5Y6FIiwicmVhbF9uYW1lIjoi566h55CG5ZGYIiwiYXV0aG9yaXRpZXMiOlsiYWRtaW5pc3RyYXRvciJdLCJjbGllbnRfaWQiOiJzYWJlciIsInJvbGVfbmFtZSI6ImFkbWluaXN0cmF0b3IiLCJsaWNlbnNlIjoicG93ZXJlZCBieSBibGFkZXgiLCJwb3N0X2lkIjoiIiwidXNlcl9pZCI6IjExMjM1OTg4MjE3Mzg2NzUyMDEiLCJyb2xlX2lkIjoiMTEyMzU5ODgxNjczODY3NTIwMSIsInNjb3BlIjpbImFsbCJdLCJuaWNrX25hbWUiOiLnrqHnkIblkZgiLCJwb3N0X2NvZGUiOiIiLCJvYXV0aF9pZCI6IiIsImRldGFpbCI6eyJ0eXBlIjoid2ViIiwiZXh0IjoiMTExMSIsImNvZGUiOiJjb2RlIn0sImV4cCI6MTcwODQxMTcwMCwiZGVwdF9pZCI6IjEyNzgxNTg0MDcxODk2ODAxMjgiLCJqdGkiOiJmZGMyOTU2ZS05ZTM1LTRjMDItOTBiNi1iOGQ3Nzk5NzkxNjMiLCJhY2NvdW50IjoiYWRtaW4ifQ.ijbQzzjlioucSP0nSTlOCg5IuJ2ht9axxKDs4bjeNJU; saber-refresh-token=eyJhbGciOiJIUzI1NiIsInR5cCI6IkpXVCJ9.eyJ0ZW5hbnRfaWQiOiIwMDAwMDAiLCJsb2NhbF9hcmVhX2NvZGUiOiIiLCJkZXBhcnRtZW50X2lkIjoiIiwidXNlcl9uYW1lIjoiYWRtaW4iLCJhcmVhX2NvZGUiOiIwIiwiZGVwdF9uYW1lIjoi5rGf6KW_55yB5pWZ6IKy5Y6FIiwicmVhbF9uYW1lIjoi566h55CG5ZGYIiwiYXV0aG9yaXRpZXMiOlsiYWRtaW5pc3RyYXRvciJdLCJjbGllbnRfaWQiOiJzYWJlciIsInJvbGVfbmFtZSI6ImFkbWluaXN0cmF0b3IiLCJsaWNlbnNlIjoicG93ZXJlZCBieSBibGFkZXgiLCJwb3N0X2lkIjoiIiwidXNlcl9pZCI6IjExMjM1OTg4MjE3Mzg2NzUyMDEiLCJyb2xlX2lkIjoiMTEyMzU5ODgxNjczODY3NTIwMSIsInNjb3BlIjpbImFsbCJdLCJuaWNrX25hbWUiOiLnrqHnkIblkZgiLCJwb3N0X2NvZGUiOiIiLCJhdGkiOiJmZGMyOTU2ZS05ZTM1LTRjMDItOTBiNi1iOGQ3Nzk5NzkxNjMiLCJvYXV0aF9pZCI6IiIsImRldGFpbCI6eyJ0eXBlIjoid2ViIiwiZXh0IjoiMTExMSIsImNvZGUiOiJjb2RlIn0sImV4cCI6MTcwOTAwMzc5NCwiZGVwdF9pZCI6IjEyNzgxNTg0MDcxODk2ODAxMjgiLCJqdGkiOiIzOWI5ZjRiOC1iMzdlLTRhMmUtYWRiOC0wMGMxYjlmNzU0MWUiLCJhY2NvdW50IjoiYWRtaW4ifQ.s6cLqPrtP5XQMM9hpA-hdfoAkLfC_5UvbIKhi8LUCKM")
//                                  .addHeader("Referer", "http://219.145.215.184:8843/user/")
//                                  .addHeader("User-Agent", "Mozilla/5.0 (Windows NT 10.0; Win64; x64) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/121.0.0.0 Safari/537.36")
//                                  .addHeader("cSessionId", "0fb35f54-0301-4fbd-813f-43e814cfae07")
//                                  .addHeader("platCode", "default")
//                                  .addHeader("roleId", "1123598816738675201")
//                                  .build();
//        Response response = client.newCall(request).execute();
//        ResponseBody responseBody = response.body();
//        return responseBody;
//    }
//
//    public String 加密密码(String data) {
//        return DesUtil.encryptToBase64(data, "Zg119@secret2021");
//    }
//
//    public String 发送新增运维人员请求(String account, String realName, String deptName, String areaCode, String password, String deptId, String roleId, String postId) throws IOException {
//        OkHttpClient client = new OkHttpClient().newBuilder().retryOnConnectionFailure(true)
//                                      .build();
//        Map<String, String> mapString = new HashMap<String, String>() {{
//            put("account", account);
//            put("realName", realName);
//            put("deptName", deptName);
//            put("areaCode", areaCode);
//            put("password", password);
//            put("password2", password);
//            put("deptId", deptId);
//            put("roleId", roleId);
//            put("postId", postId);
//            put("name", realName);
//            put("receivePushMessage", "0");
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
//    }
//
//    public static List<DemoData> 读取Xlsx() {
//
//        List<DemoData> objects = new ArrayList<>();
//        String fileName = "C:\\Users\\mr.who\\Desktop\\E+计划装维工号（赣州）(1) 的副本.xlsx";
//        // 这里 需要指定读用哪个class去读，然后读取第一个sheet 文件流会自动关闭
//        EasyExcel.read(fileName, DemoData.class, new AnalysisEventListener<DemoData>(
//        ) {
//            @Override
//            public void invoke(DemoData data, AnalysisContext context) {
//                objects.add(data);
//            }
//
//            @Override
//            public void doAfterAllAnalysed(AnalysisContext context) {
//
//            }
//        }).sheet().doRead();
//        return objects;
//    }
//
//    @Data
//    @EqualsAndHashCode
//    public static class DemoData {
//        private String 区号 = "";
//        private String 区县 = "";
//        private String 账号 = "";
//        private String 姓名 = "";
//
//    }
//}
