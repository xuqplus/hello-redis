package com.github.xuqplus2.hello.redis;

import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.data.redis.core.RedisTemplate;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.security.SecureRandom;

@SpringBootTest
@Slf4j
class DemoApplicationTests {

	@Autowired
	RedisTemplate redisTemplate;

	@Test
	void contextLoads() throws IOException {
		long millis = System.currentTimeMillis();
		SecureRandom secureRandom = new SecureRandom();

		for (int i = 0; i < 100; i++) {
			String key = "key-" + i;
			redisTemplate.boundValueOps(key).set("value" + millis);
			Object result = redisTemplate.boundValueOps(key).get();
			log.info("i=>{}, key=>{}, result=>{}, ", i, key, result);
//			redisTemplate.delete("key");
		}
	}

	private void f() throws IOException {
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
	}

}
