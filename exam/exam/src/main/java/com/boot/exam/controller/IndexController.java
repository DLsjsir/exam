package com.boot.exam.controller;

import java.io.File;
import java.io.IOException;

import javax.imageio.spi.RegisterableService;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.catalina.User;
import org.apache.commons.fileupload.UploadContext;
import org.apache.commons.lang3.StringUtils;
import org.apache.xmlbeans.impl.xb.xsdschema.Public;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import com.alibaba.druid.sql.dialect.oracle.ast.clause.ModelClause.MainModelClause;
import com.boot.exam.bean.UserBean;
import com.boot.exam.mapper.UserMapper;
import com.boot.exam.util.FileUtil;

@Controller
public class IndexController extends BaseController{
	@Autowired
	UserMapper userMapper;
	@RequestMapping("/upload")
	public void upload(MultipartFile file,HttpServletResponse resp){
		String fileName = file.getOriginalFilename();//获取文件原始文件名
		print(fileName);
		//文件操作工具
		FileUtil.createFile("D:/create/exam/logo");//创建文件夹
		try {
			file.transferTo(new File("D:/create/exam/logo/"+fileName));
		}catch (Exception e) {
			// TODO Auto-generated catch block
			print("请检查磁盘路径是否正确");
		}//保存成功，将图片路径返回给网页js中
		//outRespJson 1.直接返回给网页一个字符串 2.给网页返回json字符串
		//浏览器不能到盘符下找文件
		outRespJson("/exam/logo/"+fileName, resp);//resp 响应对象
		
	}
	
	
	
	
	
	@RequestMapping("/register")
	public String register(UserBean bean){
		if(StringUtils.isBlank(bean.username)){
			return "redirect:/index.html?msg=" + getUTF8Param(bean.username+"用户名不能为空");
		}
		try{
			bean.status = "老师";
			bean.name = bean.username + "老师";
			userMapper.insert(bean);//注册成功
			return "redirect:/index.html?msg=" + getUTF8Param(bean.username+"注册成功请登录");
		}catch(Exception e){
			return "redirect:/index.html?msg=" + getUTF8Param(bean.username+"已经注册过了"); 
		}
	}
	
	@RequestMapping("/login")
	public String login(UserBean bean){
		bean.status = "老师";
		UserBean user = userMapper.selectUser(bean);
		if (user == null) {
			String msg = getUTF8Param("用户名或密码错误");
			return "redirect:/index.html?msg=" + msg;
		}else {
			return "redirect:/main?uid=" + user.id;
		}
	}
	@RequestMapping("/main")
	public String main(int uid, HttpServletRequest req){
		UserBean user = userMapper.selectById(uid);
		req.setAttribute("user",user);
		req.setAttribute("uid",uid);
		return  "/main";
	}
		
	
	// 重定向
	// 语法:“redirect:/地址"
	// 可以打开static里面的文件
	// 不可以打开templates里面的文件
	// 打开网页时带参数到网页中   redirect:/xxx/xxx/xxx?参数名=参数值
	// 重定向是浏览器的行为，在浏览器地址栏中可以看到地址
	// 可以定向到controller中的地址
	
	// 转发
	// 语法:直接写地址“地址”此时不能加后缀
	// 只能打开templates里面的文件
	// 转发不能打开static里面的文件
	// 转发是java的行为，浏览器地址栏不会看到转发的新地址
	//通过req.setAttribute（“变量名，值）；带参到网页中
}
