package com.franklin.mars.domain;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.scheduling.annotation.Async;
import org.springframework.scheduling.annotation.AsyncResult;
import org.springframework.stereotype.Component;

import java.util.concurrent.Future;

@Component
public class AsyncDemo {


    private static final Logger logger = LoggerFactory.getLogger(AsyncDemo.class);

    @Async
    public void asyncInvokeSimplest() {
        logger.info("asyncInvokeSimplest");
    }

    @Async
    public void asyncInvokeWithParam(String s) {
        logger.info("asyncInvokeWithParam, param={}", s);
    }

    @Async
    public Future<String> asyncInvokeReturnFuture(int i) {
        logger.info("asyncInvokeReturnFuture,param={}", i);
        Future<String> future;
        try {

            Thread.sleep(1000L);
            future = new AsyncResult<>("success" + i);
        } catch (InterruptedException e) {
            future = new AsyncResult<>("error");
        }
        return future;
    }
}
