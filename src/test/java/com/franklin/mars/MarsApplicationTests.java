package com.franklin.mars;

import com.franklin.mars.domain.AsyncDemo;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.concurrent.ExecutionException;
import java.util.concurrent.Future;

@RunWith(SpringRunner.class)
@SpringBootTest(classes = MarsApplication.class)
public class MarsApplicationTests {

    @Autowired
    private AsyncDemo asyncDemo;

    @Test
    public void contextLoads() throws ExecutionException, InterruptedException {

        asyncDemo.asyncInvokeSimplest();
        asyncDemo.asyncInvokeWithParam("test");
        Future<String> future = asyncDemo.asyncInvokeReturnFuture(100);
        System.out.println(future.get());
    }

}
