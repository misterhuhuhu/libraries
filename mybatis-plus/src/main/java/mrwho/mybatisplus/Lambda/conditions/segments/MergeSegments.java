package mrwho.mybatisplus.Lambda.conditions.segments;

import com.baomidou.mybatisplus.core.conditions.ISqlSegment;
import com.baomidou.mybatisplus.core.conditions.segments.GroupBySegmentList;
import com.baomidou.mybatisplus.core.conditions.segments.HavingSegmentList;
import com.baomidou.mybatisplus.core.conditions.segments.MatchSegment;
import com.baomidou.mybatisplus.core.conditions.segments.OrderBySegmentList;

import java.util.Arrays;
import java.util.List;

public class MergeSegments implements ISqlSegment {
    @Override
    public String getSqlSegment() {
        return null;
    }
    private final NormalSegmentList normal = new NormalSegmentList();
    private final GroupBySegmentList groupBy = new GroupBySegmentList();
    private final HavingSegmentList having = new HavingSegmentList();
    private final OrderBySegmentList orderBy = new OrderBySegmentList();
    private boolean cacheSqlSegment = true;
    public void add(ISqlSegment... iSqlSegments) {
        List<ISqlSegment> list = Arrays.asList(iSqlSegments);
        ISqlSegment firstSqlSegment = list.get(0);
        if (MatchSegment.ORDER_BY.match(firstSqlSegment)) {
            orderBy.addAll(list);
        } else if (MatchSegment.GROUP_BY.match(firstSqlSegment)) {
            groupBy.addAll(list);
        } else if (MatchSegment.HAVING.match(firstSqlSegment)) {
            having.addAll(list);
        } else {
            normal.addAll(list);
        }
        cacheSqlSegment = false;
    }
}
