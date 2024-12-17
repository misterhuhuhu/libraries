import com.baomidou.mybatisplus.core.MybatisConfiguration;
import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.core.override.MybatisMapperProxy;
import jakarta.annotation.Resource;

import lombok.extern.slf4j.Slf4j;
import mrwho.mybatisplus.MybatisplusApplication;
import mrwho.mybatisplus.entity.BladeParam;
import mrwho.mybatisplus.mapper.BladeParamMapper;
import mrwho.mybatisplus.service.BladeParamService;
import org.apache.ibatis.builder.xml.XMLStatementBuilder;
import org.apache.ibatis.mapping.MappedStatement;
import org.apache.ibatis.parsing.XNode;
import org.apache.ibatis.parsing.XPathParser;
import org.apache.ibatis.session.Configuration;
import org.apache.ibatis.session.SqlSession;
import org.junit.jupiter.api.Test;
import org.mybatis.spring.SqlSessionTemplate;
import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.test.context.SpringBootTest;

import java.lang.reflect.Field;
import java.lang.reflect.InvocationHandler;
import java.lang.reflect.Proxy;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;
import java.util.stream.Collectors;


@SpringBootTest(classes = {MybatisplusApplication.class})
@Slf4j
@MapperScan({"mrwho.mybatisplus.mapper**"})
public class MybatisPlusTest {
    
    @Resource
    private BladeParamMapper bladeParamMapper;
    @Resource
    private BladeParamService bladeParamService;

    @Resource
    private SqlSession sqlSession;
    
    @Test
    void testMapper() throws NoSuchFieldException, IllegalAccessException {
        MybatisMapperProxy invocationHandler = (MybatisMapperProxy)Proxy.getInvocationHandler(bladeParamMapper);
        
        List<BladeParam> bladeParams = bladeParamMapper.selectList(new LambdaQueryWrapper<BladeParam>().eq(BladeParam::getParamKey,"1"));
        MybatisConfiguration configuration = (MybatisConfiguration)sqlSession.getConfiguration();
        ArrayList<XNode> xNodes = new ArrayList<>(configuration.getSqlFragments().values());
//        xNodes.forEach(System.out::println);
        XPathParser xPathParser = new XPathParser(" <sql id=\"Base_Column_List\">\n" +
                                                          "        <if test=\"_databaseId\">\n" +
                                                          "            and databaseId='aseda'\n" +
                                                          "        </if>\n" +
                                                          "        and 2=2\n" +
                                                          "    </sql>");
        XNode xNode = xPathParser.evalNode("sql");
        configuration.getMappedStatements()
//        System.out.println(xNode);
        
//        List<BladeParam> list = bladeParamService.list();
//        log.info(bladeParams.toString());
//        log.info(list.toString());
        ConcurrentHashMap<String, XNode> abcd = configuration.getSqlFragments().entrySet().stream().filter(k -> !k.getKey().endsWith("Base_Column_List")).collect(Collectors.toMap(Map.Entry::getKey, Map.Entry::getValue, (a, b) -> a, ConcurrentHashMap::new));
        abcd.put("Base_Column_List",xNode);
        
        Field sqlFragments = MybatisConfiguration.class.getDeclaredField("sqlFragments");
        sqlFragments.setAccessible(true);
        sqlFragments.set(configuration,abcd);
        System.out.println();
//        System.out.println(incompleteStatements);
//        List<BladeParam> bladeParams1 = bladeParamMapper.selectByAll();
//        mappedStatements.forEach(k-> System.out.println(k.getClass()));
    }
    

}
