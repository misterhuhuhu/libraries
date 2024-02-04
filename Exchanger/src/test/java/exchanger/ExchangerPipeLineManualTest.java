package exchanger;

import org.junit.Test;

import java.util.Queue;
import java.util.UUID;
import java.util.concurrent.CompletableFuture;
import java.util.concurrent.ConcurrentLinkedQueue;
import java.util.concurrent.Exchanger;
import java.util.concurrent.ExecutionException;

import static java.util.concurrent.CompletableFuture.runAsync;



public class ExchangerPipeLineManualTest {

    private static final int BUFFER_SIZE = 100;

    @Test
    public void givenData_whenPassedThrough_thenCorrect() throws InterruptedException, ExecutionException {

        Exchanger<Queue<String>> readerExchanger = new Exchanger<>();
        Exchanger<Queue<String>> writerExchanger = new Exchanger<>();
        
        /**
         * Exchanger 可用于创建管道类型的模式，将数据从一个线程传递到另一个线程
         *
         * 创建一个简单的线程堆栈，这些线程作为管道在彼此之间持续传递数据。
         *
         * readerExchanger 在读取器和处理器线程之间共享，而 writerExchanger 在处理器和编写器线程之间共享
         */
        // 读取器
        Runnable reader = () -> {
            Queue<String> readerBuffer = new ConcurrentLinkedQueue<>();
            while (true) {
                readerBuffer.add(UUID.randomUUID().toString());
                if (readerBuffer.size() >= BUFFER_SIZE) {
                    try {
                        readerBuffer = readerExchanger.exchange(readerBuffer);
                    } catch (InterruptedException e) {
                        Thread.currentThread().interrupt();
                        throw new RuntimeException(e);
                    }
                }
            }
        };
        
        // 处理器
        Runnable processor = () -> {
            Queue<String> processorBuffer = new ConcurrentLinkedQueue<>();
            Queue<String> writerBuffer = new ConcurrentLinkedQueue<>();
            try {
                processorBuffer = readerExchanger.exchange(processorBuffer);
                while (true) {
                    writerBuffer.add(processorBuffer.poll());
                    if (processorBuffer.isEmpty()) {
                        try {
                            processorBuffer = readerExchanger.exchange(processorBuffer);
                            writerBuffer = writerExchanger.exchange(writerBuffer);
                        } catch (InterruptedException e) {
                            Thread.currentThread().interrupt();
                            throw new RuntimeException(e);
                        }
                    }
                }
            } catch (InterruptedException e) {
                Thread.currentThread().interrupt();
                throw new RuntimeException(e);
            }
        };
        
        // 写入器
        Runnable writer = () -> {
            Queue<String> writerBuffer = new ConcurrentLinkedQueue<>();
            try {
                writerBuffer = writerExchanger.exchange(writerBuffer);
                while (true) {
                    System.out.println(writerBuffer.poll());
                    if (writerBuffer.isEmpty()) {
                        writerBuffer = writerExchanger.exchange(writerBuffer);
                    }
                }
            } catch (InterruptedException e) {
                Thread.currentThread().interrupt();
                throw new RuntimeException(e);
            }
        };
        
        CompletableFuture.allOf(runAsync(reader), runAsync(processor), runAsync(writer)).get();
    }

}
