import lombok.extern.slf4j.Slf4j;
import net.sf.jsqlparser.JSQLParserException;
import net.sf.jsqlparser.parser.CCJSqlParser;
import net.sf.jsqlparser.parser.CCJSqlParserUtil;
import net.sf.jsqlparser.schema.Table;
import net.sf.jsqlparser.statement.Statement;
import net.sf.jsqlparser.statement.delete.Delete;
import net.sf.jsqlparser.statement.insert.Insert;
import net.sf.jsqlparser.statement.select.FromItem;
import net.sf.jsqlparser.statement.select.PlainSelect;
import net.sf.jsqlparser.statement.select.Select;
import net.sf.jsqlparser.statement.update.Update;
import org.junit.jupiter.api.Test;

@Slf4j
public class JsqlparserTest {
    String selectSql="select\n" +
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
                             "from ct_firealarm a\n" +
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
    @Test
    public void testCCJSqlParserUtil() throws JSQLParserException {
        Statement statement = CCJSqlParserUtil.parse(selectSql);
        PlainSelect statement1 = (PlainSelect) statement;
        System.out.println(statement1.getWhere());// a.is_deleted = 0 AND a.alarm_code != '2-0-0' AND a.receive_time >= DATE_FORMAT(date_sub(current_date(), INTERVAL 1 day), '%Y-%m-%d 00:00:00') AND a.receive_time <= DATE_FORMAT(current_date(), '%Y-%m-%d 00:00:00')
        System.out.println(statement1.getJoins());//[LEFT JOIN (SELECT * FROM ct_equipment_info) ct_equipment_info ON ct_equipment_info.equipment_code = a.monitor_code, LEFT JOIN ct_company_base_info ccbi ON ct_equipment_info.company_id = ccbi.id, LEFT JOIN blade_region region ON ccbi.area_code = region.code, LEFT JOIN (SELECT * FROM blade_dict_biz WHERE code = 'alarm_type') blade_dict_biz ON blade_dict_biz.dict_key = a.alarm_type]
        System.out.println(statement1.getSelectItems());//[ccbi.name 单位名称, DATE_FORMAT(a.receive_time, '%Y-%m-%d') 报警时间, blade_dict_biz.dict_value AS '报警类型', count(DISTINCT a.id) 报警数量, (SELECT blade_dept.full_name FROM blade_dept WHERE id = ct_equipment_info.social_center_id) 值守机构, (SELECT blade_dept_operate.leader FROM blade_dept_operate WHERE dept_id = ct_equipment_info.social_center_id) 值守负责人, (SELECT blade_dept_operate.phone FROM blade_dept_operate WHERE dept_id = ct_equipment_info.social_center_id) 值守负责人电话, a.monitor_name 设备名称, (SELECT event_cause FROM ct_event WHERE id = a.event_id) 起因, a.alarm_summary AS 警情描述, a.alarm_sketch AS 警情简述, region.city_name AS 地市, region.district_name AS 区县]
        statement1.getSelectItems().forEach(k->log.info("{}=>{}",k.toString(),k.getExpression().getClass()));
        FromItem fromItem = statement1.getFromItem();
        System.out.println(fromItem.getAlias());//a
        Table fromItem1 = (Table) statement1.getFromItem();
        System.out.println(fromItem1.getName());//ct_firealarm
        System.out.println(statement1.getFromItem());//ct_firealarm a
        
//        System.out.println(statement1);
    }
}
