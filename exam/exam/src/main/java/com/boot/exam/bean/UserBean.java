package com.boot.exam.bean;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import com.boot.exam.util.NotNull;

//eclipse自动提示

@TableName("tbl_user")//数据表表名
public class UserBean {
	//表里面有什么，Bean就有什么
	@TableId(type=IdType.AUTO)
	public Integer id;//id是自动增长
	@NotNull
	public String username;
	@NotNull
	public String password;
	public String name;
	public String status;

	public Integer getId() {
		return id;
	}
	public void setId(Integer id) {
		this.id = id;
	}
	public String getUsername() {
		return username;
	}
	public void setUsername(String username) {
		this.username = username;
	}
	public String getPassword() {
		return password;
	}
	public void setPassword(String password) {
		this.password = password;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getStatus() {
		return status;
	}
	public void setStatus(String status) {
		this.status = status;
	}
	
}
