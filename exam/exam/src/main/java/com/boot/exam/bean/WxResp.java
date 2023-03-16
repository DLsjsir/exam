package com.boot.exam.bean;

import java.util.List;

public class WxResp {
	public int code = 1;
	public String msg = "";
	public int uid = 0;
	public String name="";
	public List<String> exam;
	
	public void fail(String msg){
		this.code = 0;
		this.msg = msg;
	}

}
