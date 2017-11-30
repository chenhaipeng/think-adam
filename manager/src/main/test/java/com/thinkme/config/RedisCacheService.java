package com.thinkme.config;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.CachePut;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Component;

@Component
public class RedisCacheService {

    @Autowired
	BeautifulPicturesService beautifulPicturesService;

    //查询
    @Cacheable(value = "beautifulPictures",keyGenerator ="keyGenerator")
    public BeautifulPictures getBeautifulPicturesList(String id) {
        System.out.println("getBeautifulPicturesList---不命中缓存");
        return beautifulPicturesService.selectById(id);
    }

    //修改
    @CachePut(value = "beautifulPictures",keyGenerator ="keyGenerator")
    public BeautifulPictures updateBeautifulPicture(String id) {
        BeautifulPictures beautifulPictures = new BeautifulPictures();
        beautifulPictures.setTitle("Title");
        beautifulPictures.setId(id);
        return beautifulPicturesService.updateById(beautifulPictures);
    }
}
