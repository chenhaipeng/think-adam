package com.thinkme.config;

import org.springframework.stereotype.Component;

/**
 * @author chenhaipeng
 * @version 1.0
 * @mail donotcoffee@gmail.com
 * @date 2017/11/28 下午3:33
 */
@Component
public class BeautifulPicturesService {

	public BeautifulPictures updateById(BeautifulPictures beautifulPictures) {
		System.out.println("update "+beautifulPictures);

		return beautifulPictures;

	}

	public BeautifulPictures selectById(String id) {
		return new BeautifulPictures(id,"xxx");
	}
}
