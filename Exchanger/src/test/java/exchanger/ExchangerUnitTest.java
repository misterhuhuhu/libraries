package exchanger;

import org.junit.Test;

import java.util.concurrent.CompletableFuture;
import java.util.concurrent.Exchanger;
import java.util.concurrent.ExecutionException;

import static java.util.concurrent.CompletableFuture.runAsync;
import static org.junit.Assert.assertEquals;

public class ExchangerUnitTest {
    
    
    @Test
    public void givenThreads_whenMessageExchanged_thenCorrect() {
        Exchanger<String> exchanger = new Exchanger<>();
        
        /**
         * 调用时，exchange 会等待该对中的另一个线程也调用它。此时，第二个线程发现第一个线程正在等待其对象。线程交换它们所持有的对象并发出交换信号，现在它们可以返回。
         */
        Runnable taskA = () -> {
            try {
                String message = exchanger.exchange("from A");
                assertEquals("from B", message);
            } catch (InterruptedException e) {
                Thread.currentThread().interrupt();
                throw new RuntimeException(e);
            }
        };

        Runnable taskB = () -> {
            try {
                String message = exchanger.exchange("from B");
                assertEquals("from A", message);
            } catch (InterruptedException e) {
                Thread.currentThread().interrupt();
                throw new RuntimeException(e);
            }
        };

        CompletableFuture.allOf(runAsync(taskA), runAsync(taskB)).join();
    }

    @Test
    public void givenThread_WhenExchangedMessage_thenCorrect() throws InterruptedException, ExecutionException {
        Exchanger<String> exchanger = new Exchanger<>();
        
        /**
         * 将主线程中的对象与新线程交换：
         */
        Runnable runner = () -> {
            try {
                String message = exchanger.exchange("from runner");
                assertEquals("to runner", message);
            } catch (InterruptedException e) {
                Thread.currentThread().interrupt();
                throw new RuntimeException(e);
            }
        };

        CompletableFuture<Void> result = CompletableFuture.runAsync(runner);
        String msg = exchanger.exchange("to runner");
        assertEquals("from runner", msg);
        result.join();
    }

}
