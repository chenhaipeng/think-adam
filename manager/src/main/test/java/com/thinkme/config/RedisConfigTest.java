package com.thinkme.config;

import com.thinkme.Application;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

/**
 * @author chenhaipeng
 * @version 1.0
 * @mail donotcoffee@gmail.com
 * @date 2017/11/28 下午3:26
 */
@RunWith(SpringJUnit4ClassRunner.class)
@SpringBootTest(classes = Application.class)
public class RedisConfigTest {
	@Autowired
	BeautifulPicturesService beautifulPicturesService;

	@Autowired
	StringRedisTemplate stringRedisTemplate;

	@Autowired
	RedisCacheService redisCache;

	@Test
	public void redisTest() throws Exception {

		//保存字符串
		stringRedisTemplate.opsForValue().set("aaa", "111");
		//读取字符串
		String aaa = stringRedisTemplate.opsForValue().get("aaa");
		System.out.println(aaa);
	}

	@Test
	public void CacheTest() {
		String id = "1";
		redisCache.updateBeautifulPicture(id);
		System.out.println("第一次查询结果：");
//		System.out.println(beautifulPicture);

		BeautifulPictures beautifulPicture1 = redisCache.getBeautifulPicturesList(id);
		System.out.println("第二次查询结果："+beautifulPicture1);

//		redisCache.updateBeautifulPicture(id);

		BeautifulPictures beautifulPicture2 = redisCache.getBeautifulPicturesList(id);
		System.out.println("第三次查询结果："+beautifulPicture2);
	}


}