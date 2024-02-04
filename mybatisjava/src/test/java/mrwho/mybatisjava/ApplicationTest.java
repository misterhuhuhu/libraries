package mrwho.mybatisjava;

import jakarta.annotation.Resource;
import lombok.extern.slf4j.Slf4j;
import mrwho.mybatisjava.entity.CtCompanyBaseInfoJiaoyu;
import mrwho.mybatisjava.entity.CtCompanyInfoJiaoyu;
import mrwho.mybatisjava.entity.SupMerchantNodeTblWithRegion;
import mrwho.mybatisjava.mapper.CtCompanyBaseInfoJiaoyuMapper;
import mrwho.mybatisjava.mapper.CtCompanyInfoJiaoyuMapper;
import mrwho.mybatisjava.mapper.SupMerchantNodeTblMapper;
import mrwho.mybatisjava.service.CtCompanyBaseInfoJiaoyuService;
import mrwho.mybatisjava.service.CtCompanyInfoJiaoyuService;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.transaction.annotation.Transactional;

import java.math.BigDecimal;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

@SpringBootTest(classes = MybatisplusApplication.class)
@Slf4j
public class ApplicationTest {
    
    @Resource
    CtCompanyBaseInfoJiaoyuMapper ctCompanyBaseInfoJiaoyuMapper;
    @Resource
    SupMerchantNodeTblMapper supMerchantNodeTblMapper;
    @Resource
    CtCompanyInfoJiaoyuMapper ctCompanyInfoJiaoyuMapper;
    @Resource
    CtCompanyBaseInfoJiaoyuService ctCompanyBaseInfoJiaoyuService;
    @Resource
    CtCompanyInfoJiaoyuService ctCompanyInfoJiaoyuService;

    @Test
    public void test1() {
//        List<CtCompanyBaseInfoJiaoyu> ctCompanyBaseInfoJiaoyus = ctCompanyBaseInfoJiaoyuMapper.selectList(new LambdaQueryWrapper<>());
        List<CtCompanyBaseInfoJiaoyu> ctCompanyBaseInfoJiaoyus1 = ctCompanyBaseInfoJiaoyuMapper.selectCT_COMPANY_BASE_INFO_JIAOYU();
        System.out.println();
    }
    @Test
    @Transactional(rollbackFor = Exception.class)
    public void insert(){
        List<SupMerchantNodeTblWithRegion> supMerchantNodeTblWithRegions = supMerchantNodeTblMapper.selectSupMerchantNodeTblWithRegionByLimit(0, 16000);
        log.info("supMerchantNodeTblWithRegions.size()");
//        List<SupMerchantNodeTblWithRegion> supMerchantNodeTblWithRegions = supMerchantNodeTblMapper.selectSupMerchantNodeTblWithRegion();
//        Long l = supMerchantNodeTblMapper.selectSupMerchantNodeTblCount();
//        IntStream.range(0,9).parallel().forEach(index->{
//            List<SupMerchantNodeTblWithRegion> supMerchantNodeTblWithRegions = supMerchantNodeTblMapper.selectSupMerchantNodeTblWithRegionByLimit(index * 2100, 2100);
//            List<CtCompanyBaseInfoJiaoyu> collect = supMerchantNodeTblWithRegions.stream().map(item -> {
//                CtCompanyBaseInfoJiaoyu ctCompanyBaseInfoJiaoyu = new CtCompanyBaseInfoJiaoyu();
//                ctCompanyBaseInfoJiaoyu.setAddress(item.getDetailAdress());
//                ctCompanyBaseInfoJiaoyu.setName(item.getMerchantNodeName());
//                ctCompanyBaseInfoJiaoyu.setRegionName(item.getNamecn());
//                ctCompanyBaseInfoJiaoyu.setLongitude(BigDecimal.valueOf(Double.parseDouble(Optional.ofNullable(item.getLongitude()).orElse("0"))));
//                ctCompanyBaseInfoJiaoyu.setLatitude(BigDecimal.valueOf(Double.parseDouble( Optional.ofNullable(item.getLatitude()).orElse("0"))));
//                ctCompanyBaseInfoJiaoyu.setFireSafePerson(item.getContacts());
//                ctCompanyBaseInfoJiaoyu.setFireManageTel(item.getPhone());
//                ctCompanyBaseInfoJiaoyu.setThirdDataId(item.getMerchantNodeCode());
//                ctCompanyBaseInfoJiaoyu.setSchoolType(Integer.parseInt(item.getMerchantType()));
//                return ctCompanyBaseInfoJiaoyu;
//            }).toList();
//
//            List<CtCompanyInfoJiaoyu> list = supMerchantNodeTblWithRegions.stream().map(item -> {
//                CtCompanyInfoJiaoyu ctCompanyInfoJiaoyu = new CtCompanyInfoJiaoyu();
//                ctCompanyInfoJiaoyu.setCompanyName(item.getMerchantNodeName());
//                ctCompanyInfoJiaoyu.setRegionName(item.getNamecn());
//                ctCompanyInfoJiaoyu.setAddress(item.getDetailAdress());
//                ctCompanyInfoJiaoyu.setLatitude(BigDecimal.valueOf(Double.parseDouble(Optional.ofNullable(item.getLatitude()).orElse("0"))));
//                ctCompanyInfoJiaoyu.setLongitude(BigDecimal.valueOf(Double.parseDouble(Optional.ofNullable(item.getLongitude()).orElse("0"))));
//                ctCompanyInfoJiaoyu.setFireSafeManager(item.getContacts());
//                ctCompanyInfoJiaoyu.setFireSafeManagerTel(item.getPhone());
//                ctCompanyInfoJiaoyu.setThirdDataId(item.getMerchantNodeCode());
//                ctCompanyInfoJiaoyu.setSchoolType(Integer.parseInt(item.getMerchantType()));
//                return ctCompanyInfoJiaoyu;
//            }).toList();
//            log.info("第{}个插入",index);
//            boolean b = ctCompanyBaseInfoJiaoyuService.saveBatch(collect);
//            boolean b1 = ctCompanyInfoJiaoyuService.saveBatch(list);
//            log.info("第{}个插入完成",index);
//        });
        
    }
}
