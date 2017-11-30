package com.thinkme.common.constants.code;

public enum RespCode implements CodeInterface {
	OK("0000", "成功"),
	BAD_REQUEST("0001", "请求参数错误"),
	FORBIDDEN("0002", "没有权限"),
	UNAUTHORIZED("0003", "没有授权或登录"),
	NOT_FOUND("0004", "请求针对的是不存在的记录"),
	INTERNAL_SERVER_ERROR("0005", "服务器开小差");

	public String code;
	public String msg;

	RespCode(String code, String msg) {
		this.code = code;
		this.msg = msg;
	}

	@Override
	public String getCode() {
		return null;
	}

	@Override
	public String getMsg() {
		return null;
	}

	public void setCode(String code) {
		this.code = code;
	}

	public void setMsg(String msg) {
		this.msg = msg;
	}
}