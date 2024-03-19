package mrwho.mybatisjava;

import lombok.extern.slf4j.Slf4j;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;

import java.util.concurrent.ConcurrentHashMap;

@Slf4j
public class WebDriverFactory {
    //    private static final ThreadLocal<WebDriver> driverMapThreadLocal = new ThreadLocal<>();
    private static final ThreadLocal<ConcurrentHashMap<Long, WebDriver>> driverMapThreadLocal = new ThreadLocal<>();
    
    static final ChromeOptions options = new ChromeOptions() {{
        addArguments("--headless");
    }};
    
    public static void main(String[] args) {
        WebDriverFactory webDriverFactory = new WebDriverFactory();
        webDriverFactory.getInstance();
    }
    public WebDriver getInstance() {
        long threadId = Thread.currentThread().getId();
        ConcurrentHashMap<Long, WebDriver> threadMap = driverMapThreadLocal.get();
        if (threadMap == null) {
            synchronized (WebDriverFactory.class) {
                if (threadMap == null) {
                    threadMap = new ConcurrentHashMap<>();
                    driverMapThreadLocal.set(threadMap);
                }
            }
            
        }
        WebDriver value = threadMap.computeIfAbsent(threadId, k -> new ChromeDriver(options));
//        if (threadMap.size() == 1) {
//            // 如果这是唯一剩下的（threadId -> value），则删除 ThreadLocal
//            driverMapThreadLocal.remove();
//        }
//        log.info("thead name : {} 拿到 driver {}", Thread.currentThread().getName(), value.hashCode());
        return value;
    }
}
