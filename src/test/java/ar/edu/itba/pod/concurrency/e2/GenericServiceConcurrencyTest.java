package ar.edu.itba.pod.concurrency.e2;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

import java.util.Collection;
import java.util.List;
import java.util.concurrent.*;

import org.junit.Before;
import org.junit.Test;

import ar.edu.itba.pod.concurrency.e1.GenericService;
import ar.edu.itba.pod.concurrency.e1.GenericServiceImpl;

/**
 * Unit test for {@link GenericService} using {@link Thread}s
 */
public class GenericServiceConcurrencyTest {
    private static final int EXPECTED_VISITS = 10000;

    private GenericService service;

    @Before
    public final void before() {
        service = new GenericServiceImpl();
    }

    /** Realiza 100 visitas al servicio */
    private final Runnable visitor = () -> {
            service.addVisit();
    };

    // instanciar el pool.
    private final ExecutorService pool = Executors.newFixedThreadPool(1000);

    /**
     * generates 5 threads with {@link #visitor} and runs them.
     */
    @Test
    public final void visit_count_with_thread_start() throws InterruptedException {
        for (int i=0; i< 10000; i++)
            pool.execute(visitor);
        pool.shutdown();
        pool.awaitTermination(10000, TimeUnit.MILLISECONDS);

        assertEquals(EXPECTED_VISITS, service.getVisitCount());
    }

    /**
     * generates 5 threads with {@link #visitor} and runs them submiting it via
     * the {@link ExecutorService}
     */
    @Test
    public final void visit_count_with_executor_submit() throws InterruptedException {
        assertEquals(EXPECTED_VISITS, service.getVisitCount());
    }

    /**
     * generates 5 threads with {@link #visitor} and runs with
     * {@link ExecutorService#invokeAll(Collection)}
     */
    @Test
    public final void visit_count_with_executor_invoke() throws InterruptedException {
        assertEquals(EXPECTED_VISITS, service.getVisitCount());
    }

    /**
     * generates 5 Threads of a Runnable that sleesps for 10 seconds an inserts
     * 10 elements into the service queue. Once all threads are done, the test
     * should check to see of the queue is empty
     */
    @Test
    public final void concurrent_queue() {
        assertTrue(service.isServiceQueueEmpty());
    }
}
