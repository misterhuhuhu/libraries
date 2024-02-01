package java9.reactive;


import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import org.springframework.util.StopWatch;
import reactive.BaeldungBatchSubscriberImpl;

import java.util.concurrent.ForkJoinPool;
import java.util.concurrent.SubmissionPublisher;
import java.util.concurrent.TimeUnit;
import java.util.stream.IntStream;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;


public class BaeldungBatchSubscriberImplIntegrationTest {

    private static final int ITEM_SIZE = 10;
    private SubmissionPublisher<String> publisher;
    private BaeldungBatchSubscriberImpl<String> subscriber;

    private final StopWatch stopWatch=new StopWatch();
    @BeforeAll
    public void initialize() {
        this.publisher = new SubmissionPublisher<String>(ForkJoinPool.commonPool(), 6);
        this.subscriber = new BaeldungBatchSubscriberImpl<String>();
        publisher.subscribe(subscriber);
    }



    @Test
    public void testReactiveStreamCount() {
        
        IntStream.range(0, ITEM_SIZE)
            .forEach(item -> publisher.submit(item + ""));
        publisher.close();

        do {
            // wait for subscribers to complete all processing.
            try {
                Thread.sleep(100);
            } catch (InterruptedException e) {
                // TODO Auto-generated catch block
                e.printStackTrace();
            }
        } while (!subscriber.isCompleted());

        int count = subscriber.getCounter();

        assertEquals(ITEM_SIZE, count);
    }

    @Test
    public void testReactiveStreamTime() {
        IntStream.range(0, ITEM_SIZE)
            .forEach(item -> publisher.submit(item + ""));
        publisher.close();

        do {
            // wait for subscribers to complete all processing.
            try {
                Thread.sleep(100);
            } catch (InterruptedException e) {
                // TODO Auto-generated catch block
                e.printStackTrace();
            }
        } while (!subscriber.isCompleted());

        // The runtime in seconds should be equal to the number of items in each batch.
        assertTrue(stopWatch.getTotalTime(TimeUnit.SECONDS) >= (ITEM_SIZE / subscriber.BUFFER_SIZE));
    }

}
