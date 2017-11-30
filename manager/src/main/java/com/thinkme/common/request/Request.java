package com.thinkme.common.request;

import lombok.Data;

import java.io.Serializable;

@Data
public class Request implements Serializable {
	/**
	 * 系统版本IOS9/Android1.6/web/wechat
	 */
	private String osType;
	/**
	 * 客户端版本
	 */
	private String version;

	/**
	 * 授权令牌,可选,当用户登录后存在
	 */
	private String accessToken;
	/**
	 * 用户id,可选,当用户登录后存在
	 */
	private String userId;



}
