package com.example.test.executors;

import java.util.concurrent.Executor;

public class NagaExecutors {

    private static final JobExecutor ioExecutor = new JobExecutor("naga-ads-io");

    public static Executor io() {
        return ioExecutor;
    }

}
