import lombok.extern.slf4j.Slf4j;
import net.sf.jsqlparser.JSQLParserException;
import net.sf.jsqlparser.expression.Alias;
import net.sf.jsqlparser.expression.Expression;

import net.sf.jsqlparser.expression.operators.relational.LikeExpression;
import net.sf.jsqlparser.parser.CCJSqlParserUtil;
import net.sf.jsqlparser.schema.Column;
import net.sf.jsqlparser.schema.Table;
import net.sf.jsqlparser.statement.Statement;
import net.sf.jsqlparser.statement.select.FromItem;
import net.sf.jsqlparser.statement.select.Join;
import net.sf.jsqlparser.statement.select.PlainSelect;
import net.sf.jsqlparser.statement.select.Select;

import net.sf.jsqlparser.util.TablesNamesFinder;
import org.apache.commons.lang3.ObjectUtils;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

@Slf4j
public class JsqlparserTest {
    String selectSql = "select\n" +
                               "      ccbi.name                                          单位名称\n" +
                               "     ,DATE_FORMAT(a.receive_time, '%Y-%m-%d' )                  报警时间\n" +
                               "     ,blade_dict_biz.dict_value as '报警类型'\n" +
                               "     , count(distinct  a.id) 报警数量\n" +
                               "     ,(select blade_dept.full_name from blade_dept where id= ct_equipment_info.social_center_id) 值守机构\n" +
                               "     ,(select blade_dept_operate.leader from blade_dept_operate where dept_id= ct_equipment_info.social_center_id) 值守负责人\n" +
                               "     ,(select blade_dept_operate.phone from blade_dept_operate where dept_id= ct_equipment_info.social_center_id) 值守负责人电话\n" +
                               "     ,a.monitor_name 设备名称\n" +
                               "        ,(select event_cause from ct_event where id =a.event_id )  起因\n" +
                               "     , a.alarm_summary                                    as 警情描述\n" +
                               "     , a.alarm_sketch                                    as 警情简述\n" +
                               "     , region.city_name                                  as 地市\n" +
                               "     , region.district_name                                  as 区县\n" +
                               "from ct_firealarm\n" +
                               "    left join (select * from ct_equipment_info) ct_equipment_info on ct_equipment_info.equipment_code=a.monitor_code\n" +
                               "         left join ct_company_base_info ccbi on ct_equipment_info.company_id = ccbi.id\n" +
                               "         LEFT JOIN blade_region region ON ccbi.area_code = region.code\n" +
                               "left join (select * from blade_dict_biz where code='alarm_type') blade_dict_biz on blade_dict_biz.dict_key=a.alarm_type\n" +
                               "\n" +
                               "where a.is_deleted = 0\n" +
                               "  and a.alarm_code != '2-0-0'\n" +
                               "and a.receive_time >= DATE_FORMAT(date_sub(current_date(), interval 1 day), '%Y-%m-%d 00:00:00' )\n" +
                               "and a.receive_time <= DATE_FORMAT(current_date(), '%Y-%m-%d 00:00:00' )\n" +
                               "group by ccbi.name,ccbi.social_center_id\n" +
                               "order by 报警数量 desc , ccbi.name,ccbi.social_center_id";
    
    String selectSql2 = "select\n" +
                                "    a.类型,\n" +
                                "    sum(a.全部接入单位) 已接入单位,\n" +
                                "    sum(b.全部接入单位) 目标接入数,\n" +
                                "    sum(a.全部接入单位)*100/sum(b.全部接入单位) 接入率,\n" +
                                "    sum(全部智慧烟感) + sum(全部智慧用电) + sum(全部其他消防设备) + sum(全部视频) + sum(全部音柱)  全部设备,\n" +
                                "    sum(全部智慧烟感) 全部智慧烟感,\n" +
                                "    sum(全部智慧用电) 全部智慧用电,\n" +
                                "    sum(全部其他消防设备) 全部其他消防设备,\n" +
                                "    sum(全部视频) 全部视频,\n" +
                                "    sum(全部音柱) 全部音柱,\n" +
                                "    sum(电信值守单位) 电信值守单位,\n" +
                                "    sum(a.全部接入单位) - sum(电信值守单位) - sum(自主值守单位) - sum(无值守单位机构)   第三方值守单位,\n" +
                                "    sum(自主值守单位) 自主值守单位,\n" +
                                "    sum(无值守单位机构) 无值守机构单位\n" +
                                "from (select\n" +
                                "        case\n" +
                                "            when blade_dict_biz.dict_key ='8' then '校外培训机构'\n" +
                                "            when blade_dict_biz.dict_key in('17','1') then '幼儿园'\n" +
                                "            when blade_dict_biz.dict_key in('14','3','2') then '中小学'\n" +
                                "            when blade_dict_biz.dict_key in('18','19') then '中职'\n" +
                                "            when blade_dict_biz.dict_key in('15','6','16','4') then '高校'\n" +
                                "            else\n" +
                                "                blade_dict_biz.dict_value\n" +
                                "            end 类型\n" +
                                "          ,\n" +
                                "        count(distinct  ct_company_base_info.id ) 全部接入单位,\n" +
                                "        count(distinct if(ct_equipment_info.equipment_type_code in('2','48') ,ct_equipment_info.id,null)) 全部智慧烟感,\n" +
                                "        count(distinct if(ct_equipment_info.equipment_type_code in('4','31','32','33','39','40','41','42','43','49','50','51')  ,ct_equipment_info.id,null)) 全部智慧用电,\n" +
                                "        count(distinct if((ct_equipment_info.equipment_type_code not in('2','48','4','31','32','33','39','40','41','42','43','49','50','51') or ct_equipment_info.equipment_type_code is null)  ,ct_equipment_info.id,null)) 全部其他消防设备,\n" +
                                "        count(distinct ct_data_er_camera.id) 全部视频,\n" +
                                "        count(distinct if(ct_data_er_camera.sound_id!='',ct_data_er_camera.id,null)) 全部音柱,\n" +
                                "        count(distinct if(ct_company_base_info.social_center_id='JXDX0000001' , ct_company_base_info.id,null )) 电信值守单位,\n" +
                                "        count(distinct if(blade_dept.dept_name = '自主值守'  , ct_company_base_info.id,null )) 自主值守单位,\n" +
                                "        count(distinct if((blade_dept.dept_name ='' or blade_dept.dept_name is null  ), ct_company_base_info.id,null ) ) 无值守单位机构\n" +
                                "from (select * from ct_company_base_info where   ct_company_base_info.is_deleted=0 and (has_smoke_device=1 or has_camera_device = 1))  ct_company_base_info\n" +
                                "\n" +
                                "left join (select * from blade_dict_biz where code = 'edu_school_type' and is_deleted=0) blade_dict_biz on blade_dict_biz.dict_key=school_type\n" +
                                "left join (select * from ct_equipment_info where is_deleted=0) ct_equipment_info on   ct_equipment_info.company_id=    ct_company_base_info.id\n" +
                                "left join (select * from ct_data_er_camera where is_deleted = 0) ct_data_er_camera on ct_data_er_camera.company_code =ct_company_base_info.id\n" +
                                "left join (select * from blade_dept where is_deleted=0) blade_dept on blade_dept.id=ct_company_base_info.social_center_id\n" +
                                "                                             group by 类型\n" +
                                "                                             ) a\n" +
                                "left join (select\n" +
                                "    b.类型,\n" +
                                "    if(b.类型='高校',109,全部接入单位)\n" +
                                "        全部接入单位\n" +
                                "from (select\n" +
                                "        case\n" +
                                "            when blade_dict_biz.dict_key ='8' then '校外培训机构'\n" +
                                "            when blade_dict_biz.dict_key in('17','1') then '幼儿园'\n" +
                                "            when blade_dict_biz.dict_key in('14','3','2') then '中小学'\n" +
                                "            when blade_dict_biz.dict_key in('18','19') then '中职'\n" +
                                "            when blade_dict_biz.dict_key in('15','6','16','4') then '高校'\n" +
                                "            else\n" +
                                "                blade_dict_biz.dict_value\n" +
                                "            end 类型,\n" +
                                "        count(1) 全部接入单位\n" +
                                "      from (select ct_company_base_info.*,if(ct_company_base_info.school_type='' or ct_company_base_info.school_type is null,'7',ct_company_base_info.school_type) school_type1 from ct_company_base_info where   ct_company_base_info.is_deleted=0 )  ct_company_base_info\n" +
                                "left join (select * from blade_dict_biz where code = 'edu_school_type' and is_deleted=0 ) blade_dict_biz on blade_dict_biz.dict_key=school_type1\n" +
                                "                                             group by 类型\n" +
                                "                                             ) b ) b\n" +
                                "on b.类型=a.类型\n" +
                                "group by 类型  ";
    String sql3="SELECT\n" +
                        "            t1.dateStr AS dateStr,\n" +
                        "            IFNULL(t2.faultCount,0) AS faultCount,\n" +
                        "            IFNULL(t2.residentFaultCount,0) AS residentFaultCount,\n" +
                        "            IFNULL(t2.companyFaultCount,0) AS companyFaultCount\n" +
                        "        FROM\n" +
                        "            (SELECT DATE_FORMAT(DATE_SUB(NOW(), INTERVAL( CAST(help_topic_id AS signed) INTEGER) HOUR), '%Y-%m-%d %H') AS dateStr\n" +
                        "             FROM mysql.help_topic\n" +
                        "             WHERE help_topic_id   <   24\n" +
                        "             ORDER BY dateStr) t1\n" +
                        "                LEFT JOIN\n" +
                        "            (SELECT\n" +
                        "                DATE_FORMAT(a.receive_time, '%Y-%m-%d %H') AS dateStr,\n" +
                        "                COUNT( 1 ) AS faultCount,\n" +
                        "                COUNT( CASE WHEN c.monitor_type = 6 THEN 1 END ) AS residentFaultCount,\n" +
                        "                COUNT( CASE WHEN c.monitor_type != 6 THEN 1 END ) AS companyFaultCount\n" +
                        "            from ct_fault a\n" +
                        "            left join ct_company_base_info c on a.company_code = c.id\n" +
                        "            where a.is_deleted = 0 and a.system = 3\n" +
                        "            and a.receive_time   >   DATE_FORMAT(DATE_SUB(NOW(), INTERVAL 1 DAY), '%Y-%m-%d %H:00:00')\n" +
                        "\n" +
                        "            GROUP BY dateStr ORDER BY dateStr) t2 ON t1.dateStr=t2.dateStr ORDER BY dateStr;";
    
    String c="SELECT\n" +
                     "            t1.dateStr AS dateStr,\n" +
                     "            IFNULL(t2.faultCount,0) AS faultCount,\n" +
                     "            IFNULL(t2.residentFaultCount,0) AS residentFaultCount,\n" +
                     "            IFNULL(t2.companyFaultCount,0) AS companyFaultCount\n" +
                     "        FROM\n" +
                     "            (SELECT DATE_FORMAT(DATE_SUB(NOW(), INTERVAL( CAST(help_topic_id AS signed INTEGER)) HOUR), '%Y-%m-%d %H') AS dateStr\n" +
                     "             FROM mysql.help_topic\n" +
                     "             WHERE help_topic_id   <   24\n" +
                     "             ORDER BY dateStr) t1\n" +
                     "                LEFT JOIN\n" +
                     "            (SELECT\n" +
                     "                DATE_FORMAT(a.receive_time, '%Y-%m-%d %H') AS dateStr,\n" +
                     "                COUNT( 1 ) AS faultCount,\n" +
                     "                COUNT( CASE WHEN c.monitor_type = 6 THEN 1 END ) AS residentFaultCount,\n" +
                     "                COUNT( CASE WHEN c.monitor_type != 6 THEN 1 END ) AS companyFaultCount\n" +
                     "            from ct_fault a\n" +
                     "            left join ct_company_base_info c on a.company_code = c.id\n" +
                     "            where a.is_deleted = 0 and a.system = 3\n" +
                     "            and a.receive_time   >   DATE_FORMAT(DATE_SUB(NOW(), INTERVAL 1 DAY), '%Y-%m-%d %H:00:00')\n" +
                     "\n" +
                     "            GROUP BY dateStr ORDER BY dateStr) t2 ON t1.dateStr=t2.dateStr ORDER BY dateStr";
    @Test
    public void testCCJSqlParserUtil() throws JSQLParserException {
        Select statement = (Select)CCJSqlParserUtil.parse(c);
//        TablesNamesFinder tablesNamesFinder = new TablesNamesFinder();
//        List<String> tableList = tablesNamesFinder.getTableList((Statement) statement);
//        PlainSelect statement1 = (PlainSelect) statement.getSelectBody();
//        FromItem fromItem2 = statement1.getFromItem();
//        if (fromItem2 instanceof Table) {
//            Table fromItem21 = (Table) fromItem2;
//            String name = fromItem21.getName();
//            System.out.println();
//        }
//        Expression where = statement1.getWhere();
//        System.out.println(where);// a.is_deleted = 0 AND a.alarm_code != '2-0-0' AND a.receive_time >= DATE_FORMAT(date_sub(current_date(), INTERVAL 1 day), '%Y-%m-%d 00:00:00') AND a.receive_time <= DATE_FORMAT(current_date(), '%Y-%m-%d 00:00:00')
//        System.out.println(statement1.getJoins());//[LEFT JOIN (SELECT * FROM ct_equipment_info) ct_equipment_info ON ct_equipment_info.equipment_code = a.monitor_code, LEFT JOIN ct_company_base_info ccbi ON ct_equipment_info.company_id = ccbi.id, LEFT JOIN blade_region region ON ccbi.area_code = region.code, LEFT JOIN (SELECT * FROM blade_dict_biz WHERE code = 'alarm_type') blade_dict_biz ON blade_dict_biz.dict_key = a.alarm_type]
//        System.out.println(statement1.getSelectItems());//[ccbi.name 单位名称, DATE_FORMAT(a.receive_time, '%Y-%m-%d') 报警时间, blade_dict_biz.dict_value AS '报警类型', count(DISTINCT a.id) 报警数量, (SELECT blade_dept.full_name FROM blade_dept WHERE id = ct_equipment_info.social_center_id) 值守机构, (SELECT blade_dept_operate.leader FROM blade_dept_operate WHERE dept_id = ct_equipment_info.social_center_id) 值守负责人, (SELECT blade_dept_operate.phone FROM blade_dept_operate WHERE dept_id = ct_equipment_info.social_center_id) 值守负责人电话, a.monitor_name 设备名称, (SELECT event_cause FROM ct_event WHERE id = a.event_id) 起因, a.alarm_summary AS 警情描述, a.alarm_sketch AS 警情简述, region.city_name AS 地市, region.district_name AS 区县]
//        FromItem fromItem = fromItem2;
//        System.out.println(fromItem.getAlias());//a
//        Table fromItem1 = (Table) fromItem2;
//        System.out.println(fromItem1.getName());//ct_firealarm
//        System.out.println(fromItem2);//ct_firealarm a

//        System.out.println(statement1);
    }
    
    @Test
    public void testCCJSqlParserUtil2() throws JSQLParserException {
        Statement statement = CCJSqlParserUtil.parse(selectSql2);
        Select select = (Select) statement;
        PlainSelect statement1 = (PlainSelect)select.getSelectBody();
//        getExpressionWithAuth(statement1);
//        System.out.println(statement1);
    }
    
//    public static void getExpressionWithAuth(SelectBody selectExpression) {
//        if (ObjectUtils.isEmpty(selectExpression)) {
//            return  ;
//        }
//
//        if (selectExpression instanceof PlainSelect) {
//
//            Expression where = ((PlainSelect) selectExpression).getWhere();//null equalTo AndExpression
//            FromItem fromItem = ((PlainSelect) selectExpression).getFromItem();//Table SubSelect
//            if(fromItem instanceof SubSelect ){
//                Alias alias = fromItem.getAlias();
//                if(ObjectUtils.isNotEmpty(alias)){
//                    setAccurateWhere(selectExpression,alias);
//                }
//                getExpressionWithAuth(selectExpression);
//            }
//            if(fromItem instanceof Table){
//                setDefaltWhere(selectExpression);
//            }
//            List<Join> joins = ((PlainSelect) selectExpression).getJoins();
//        }
//        if (selectExpression instanceof SubSelect) {
//            SelectBody selectBody = ((SubSelect) selectExpression).getSelectBody();
//            if(selectBody instanceof PlainSelect||selectBody instanceof Select|| selectBody instanceof SubSelect){
//                getExpressionWithAuth(selectBody);
//            }
//        }
//        if(selectExpression instanceof Select){
//            getExpressionWithAuth(((Select) selectExpression).getSelectBody());
//        }
//    }
    
//    private static void setDefaltWhere(SelectBody selectExpression) {
//    }
//
//    private static void setAccurateWhere(SelectBody selectExpression, Alias alias) {
//    }
    
    @Test
    public void testCCJSqlParserUtil3() throws JSQLParserException {
        String s = new String("2");
        ArrayList<String> objects = new ArrayList<>() {{
            add(s);
            add(s);
            add(s);
        }};
        List<Expression> collect = objects.stream().map(Column::new).collect(Collectors.toList());
        System.out.println(collect);
        Statement statement = CCJSqlParserUtil.parse("(select * from abc) ");
        Select statement1 = (Select) statement;
        PlainSelect plainSelect = (PlainSelect) statement1.getSelectBody();
        LikeExpression ab = new LikeExpression() {{
            setRightExpression(new Column("ab%"));
            setLeftExpression(new Column("123"));
        }};
        System.out.println(ab);
        System.out.println(statement1.toString());
//        System.out.println(statement1);
    }
}
