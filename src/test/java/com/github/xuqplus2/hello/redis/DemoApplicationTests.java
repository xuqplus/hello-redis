package com.github.xuqplus2.hello.redis;

import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.data.redis.core.RedisTemplate;

import java.io.*;

@SpringBootTest
@Slf4j
class DemoApplicationTests {

    @Autowired
    RedisTemplate redisTemplate;

    @Test
    void contextLoads() throws IOException {
        System.getProperties().put("socksProxySet", "true");
        System.getProperties().put("socksProxyHost", "localhost");
        System.getProperties().put("socksProxyPort", "9999");
        Process exec = Runtime.getRuntime().exec("ping 10.244.1.12");
        OutputStream os = exec.getOutputStream();
        InputStream is = exec.getInputStream();
        InputStream es = exec.getErrorStream();
        try (BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(es, "gbk"))) {
            String line = null;
            while (null != (line = bufferedReader.readLine())) {
                log.info(line);
            }
        }
        try (BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(is, "gbk"))) {
            String line = null;
            while (null != (line = bufferedReader.readLine())) {
                log.info(line);
            }
        }

        long millis = System.currentTimeMillis();
        redisTemplate.boundValueOps("key" + millis).set("value" + millis);
    }

}
