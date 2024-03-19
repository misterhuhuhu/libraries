package mrwho.mybatisjava;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;

import java.util.List;

import java.util.Queue;
import java.util.concurrent.ConcurrentLinkedQueue;

public class WebDriverQueue {
    
    private static final Queue<WebDriver> webdriverstack = new ConcurrentLinkedQueue<>();
    private static final ChromeOptions options = new ChromeOptions() {{
        addArguments("--headless");
    }};
    
    static {
        for (int i = 0; i < 10; i++) {
            webdriverstack.add(new ChromeDriver(options));
        }
    }
    
    public List<WebElement> getbElementBy(String url, By by) {
        WebDriver poll = null;
        while ((poll = webdriverstack.poll()) != null) {
            poll.get(url);
            List<WebElement> elements = poll.findElements(by);
            webdriverstack.offer(poll);
            return elements;
        }
        throw new RuntimeException("webdriver被占用");
    }
}
