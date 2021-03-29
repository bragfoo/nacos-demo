package com.liuhx.nacos.user;

import com.liuhx.nacos.common.entity.po.User;
import com.liuhx.nacos.user.service.UserBloomFilterService;
import com.liuhx.nacos.user.service.UserService;
import lombok.extern.slf4j.Slf4j;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import javax.annotation.Resource;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.ThreadPoolExecutor;
import java.util.concurrent.locks.ReentrantLock;


@RunWith(SpringJUnit4ClassRunner.class)
@SpringBootTest
@Slf4j
public class SpringCloudDemoApplicationTests {
    @Resource
    UserService userService;
    @Resource
    UserBloomFilterService userBloomFilterService;

   public void contextLoads() {
       ExecutorService executors = Executors.newCachedThreadPool();

    }

    public void insertUserBloom() {
        int i = 0;
        while (i < 10000) {
            i++;
            User user = User.builder().auth(1).phone("18234581" + i).name("用户" + i).build();
            userService.addUser(user);
        }
    }
    @Test
    public void checkUser(){
        log.info("检验结果是：{}",userBloomFilterService.checkUser("60598c377b876407da4311d5"));
    }

}
