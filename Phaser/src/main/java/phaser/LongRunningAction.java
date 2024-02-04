package phaser;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.concurrent.Phaser;

class LongRunningAction implements Runnable {

    private static final Logger log = LoggerFactory.getLogger(LongRunningAction.class);
    private final String threadName;
    private final Phaser ph;

    LongRunningAction(String threadName, Phaser ph) {
        this.threadName = threadName;
        this.ph = ph;

        this.randomWait();

        ph.register();//注册线程
        log.info("Thread {} registered during phase {}", threadName, ph.getPhase());
    }

    @Override
    public void run() {
        log.info("Thread {} BEFORE long running action in phase {}", threadName, ph.getPhase());
        ph.arriveAndAwaitAdvance();

        randomWait();

        log.info("Thread {} AFTER long running action in phase {}", threadName, ph.getPhase());
        ph.arriveAndDeregister();
    }

    // Simulating real work
    private void randomWait() {
        try {
            Thread.sleep((long) (Math.random() * 100));
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }
}