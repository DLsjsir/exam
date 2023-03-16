package com.boot.exam.controller;

import java.util.Date;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import com.boot.exam.bean.QuestionBean;
import com.boot.exam.mapper.QuestionMapper;
import com.boot.exam.mapper.UserMapper;
import com.boot.exam.util.NotNullUtil;

@Controller//在类中配置地址必须加，使他成为控制器
@RequestMapping("/question")
public class QuestionController extends BaseController {
	@Autowired//相当于new
	QuestionMapper questionMapper;
	@Autowired
	UserMapper userMapper;
	//404地址写错 或者超链接写错
	//500 Java写错
	//get请求<a href=""> 重定向 浏览器地址发生改变
	//post请求<form action="" method = "post"
	//get请求地址地址栏可以看到 地址长度有限
	
	@GetMapping("/add")
	public String add(Integer id,HttpServletRequest req){
		req.setAttribute("bean", id == null?null:questionMapper.selectById(id));
		return "/question/add";//转发到templates文件下的/question/add.html
		
	}
	
	@PostMapping("/add")
	public String add(QuestionBean bean,HttpServletResponse resp){
		if(NotNullUtil.isBlank(bean)){
			return jsAlert("请完善信息","/question/add?"+(bean.id==null?"":"id="+bean.id),resp);
		}	
		bean.uid = userMapper.selectUid(bean.username);
		if(bean.id ==null){
			bean.ctime = new Date();
			questionMapper.insert(bean);
		}else{
			questionMapper.updateById(bean);
		}
		return "redirect:/question/list?exam="+bean.username;//重新打开试题列表页面
		
	}
	
	@RequestMapping("/list")
	public String list(String exam,HttpServletRequest req){ //转发参数传到网页中
		exam = exam ==null?"":exam;
		req.setAttribute("exam", exam);
		req.setAttribute("examList", questionMapper.selectExam());
		List<QuestionBean> retList = questionMapper.selectList(exam,null);
		req.setAttribute("retList", retList);
		return "/question/list";//跳到templates文件下的/question/list.html
	}
	
	@RequestMapping("/del")
	public String del(int id,String exam){
		questionMapper.deleteById(id);//mybatisplus已经写好SQL语句
		return "redirect:/question/list?exam="+exam;
	}
	@RequestMapping("/quit")
	public String quit(int id,String exam){
		QuestionBean bean =	questionMapper.selectById(id);
		bean.quit = bean.quit.equals("正常")?"已弃用":"正常";
		questionMapper.updateById(bean);
		return "redirect:/question/list?exam="+exam;
	
		
	}


}
