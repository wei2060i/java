package com.crud.bean;

import java.util.HashMap;
import java.util.Map;

public class Msg {
	//状态码 100成功  200失败
	private int code;
	//提示信息
	private String msg;
	//用户返回给浏览器的数据
	private Map<String,Object> extend=new HashMap<>();
	public Msg add(String key,Object value) {
		this.getExtend().put(key, value);
		return this;
	}
	
	public static Msg success() {
		Msg result=new Msg();
		result.setCode(100);
		result.setMsg("处理成功");
		return result;
	}
	public static Msg fail() {
		Msg result=new Msg();
		result.setCode(200);
		result.setMsg("处理失败");
		return result;
	}
	public int getCode() {
		return code;
	}
	/**
	 * @param code the code to set
	 */
	public void setCode(int code) {
		this.code = code;
	}
	/**
	 * @return the msg
	 */
	public String getMsg() {
		return msg;
	}
	/**
	 * @param msg the msg to set
	 */
	public void setMsg(String msg) {
		this.msg = msg;
	}
	/**
	 * @return the extend
	 */
	public Map<String, Object> getExtend() {
		return extend;
	}
	/**
	 * @param extend the extend to set
	 */
	public void setExtend(Map<String, Object> extend) {
		this.extend = extend;
	}
	
	
}
