package com.thinkme.config;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;

/**
 * @author chenhaipeng
 * @version 1.0
 * @mail donotcoffee@gmail.com
 * @date 2017/11/28 下午3:32
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
public class BeautifulPictures implements Serializable{
	private String title;
	private String id;

}
