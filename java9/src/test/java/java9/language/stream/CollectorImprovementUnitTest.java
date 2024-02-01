package java9.language.stream;


import org.junit.jupiter.api.Test;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.List;
import java.util.Map;
import java.util.function.Function;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

import static org.junit.jupiter.api.Assertions.assertEquals;


public class CollectorImprovementUnitTest {
    @Test
    public void givenList_whenSatifyPredicate_thenMapValueWithOccurences() {
        List<Integer> numbers = List.of(1, 2, 3, 5, 5);

        Map<Integer, Long> result = numbers.stream().filter(val -> val > 3)//找出大于3的
                                            .collect(Collectors.groupingBy(Function.identity(), Collectors.counting()));//用 Function.identity() :元素本身,Collectors.counting()出现次数   统计
        
        System.out.println(result);;
        
        /**
         * Collectors.filtering(条件,返回数据) 统计是否满足[条件],返回所有数据(不满足的没有[返回数据])
         */
        result = numbers.stream().collect(Collectors.groupingBy(Function.identity(), Collectors.filtering(val -> val > 3, Collectors.counting())));
        
        System.out.println(result);;
    }

    @Test
    public void givenListOfBlogs_whenAuthorName_thenMapAuthorWithComments() {
        Blog blog1 = new Blog("1", "Nice", "Very Nice");
        Blog blog2 = new Blog("2", "Disappointing", "Ok", "Could be better");
        List<Blog> blogs = List.of(blog1, blog2);

        Map<String, List<List<String>>> authorComments1 = blogs.stream().collect(Collectors.groupingBy(Blog::getAuthorName, Collectors.mapping(Blog::getComments, Collectors.toList())));

        assertEquals(2, authorComments1.size());
        assertEquals(2, authorComments1.get("1").get(0).size());
        assertEquals(3, authorComments1.get("2").get(0).size());

        Map<String, List<String>> authorComments2 = blogs.stream().collect(Collectors.groupingBy(Blog::getAuthorName, Collectors.flatMapping(blog -> blog.getComments().stream(), Collectors.toList())));

        assertEquals(2, authorComments2.size());
        assertEquals(2, authorComments2.get("1").size());
        assertEquals(3, authorComments2.get("2").size());
    }
    @Test
    
    public void dummy() {
        IntStream.range(0,7).forEach(System.out::println);
        List<String> collect = IntStream.range(0, 7).mapToObj(index -> LocalDate.now().minusDays(index).format(DateTimeFormatter.ofPattern("yyyy-MM-dd"))).collect(Collectors.toList());
        System.out.println(collect);
    }
}

class Blog {
    private String authorName;
    private List<String> comments;

    public Blog(String authorName, String... comments) {
        this.authorName = authorName;
        this.comments = List.of(comments);
    }

    public String getAuthorName() {
        return this.authorName;
    }

    public List<String> getComments() {
        return this.comments;
    }
    
}
