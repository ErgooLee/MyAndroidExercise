package com.example.test.executors;

import java.util.concurrent.Executor;
import java.util.concurrent.LinkedBlockingQueue;
import java.util.concurrent.ThreadFactory;
import java.util.concurrent.ThreadPoolExecutor;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.atomic.AtomicInteger;

/**
 * Decorated {@link ThreadPoolExecutor}
 */
public class JobExecutor implements Executor {
    private static final int CPU_COUNT = Runtime.getRuntime().availableProcessors();
    // We want at least 2 threads and at most 4 threads in the core pool,
    // preferring to have 1 less than the CPU count to avoid saturating
    // the CPU with background work
    private static final int CORE_POOL_SIZE = Math.max(2, Math.min(CPU_COUNT - 1, 4));
    private static final int MAXIMUM_POOL_SIZE = CPU_COUNT * 2 + 1;
    private static final int KEEP_ALIVE_SECONDS = 30;

    private final Executor executor;

    public JobExecutor(String tag) {
        ThreadPoolExecutor threadPoolExecutor = new ThreadPoolExecutor(
                CORE_POOL_SIZE, MAXIMUM_POOL_SIZE, KEEP_ALIVE_SECONDS, TimeUnit.SECONDS,
                new LinkedBlockingQueue<Runnable>(), new JobThreadFactory(tag));
        threadPoolExecutor.allowCoreThreadTimeOut(true);
        this.executor = threadPoolExecutor;
    }

    @Override
    public void execute(Runnable command) {
        this.executor.execute(command);
    }

    private static class JobThreadFactory implements ThreadFactory {
        private final String tag;

        private final AtomicInteger counter = new AtomicInteger(1);

        JobThreadFactory(String tag) {
            this.tag = tag;
        }

        @Override
        public Thread newThread(Runnable r) {
            return new Thread(r, tag + "#" + counter.getAndIncrement());
        }
    }

}
