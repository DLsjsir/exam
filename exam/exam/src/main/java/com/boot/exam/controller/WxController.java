package com.boot.exam.controller;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Random;
import java.util.Set;

import javax.servlet.http.HttpServletResponse;

import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.alibaba.druid.sql.ast.statement.SQLSubmitJobStatement;
import com.boot.exam.bean.ExamBean;
import com.boot.exam.bean.QuestionBean;
import com.boot.exam.bean.UserBean;
import com.boot.exam.bean.WxResp;
import com.boot.exam.mapper.ExamMapper;
import com.boot.exam.mapper.QuestionMapper;
import com.boot.exam.mapper.UserMapper;
import com.boot.exam.util.NotNullUtil;
import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;

@Controller
@RequestMapping("/wx")//支持GET POST
public class WxController extends BaseController {
	@Autowired
	UserMapper userMapper;
	@Autowired
	QuestionMapper questionMapper;
	@Autowired
	ExamMapper examMapper;
	
	
	@RequestMapping("/check")
	public void check(int uid,String exam, HttpServletResponse resp){
		WxResp wx = new WxResp();
		int count = examMapper.selectCount(uid, exam);
		if(count!=0){
			int yes = examMapper.selectYesCount(uid, exam);
			int score = yes*100/count;
			wx.fail("你考完该科目了,得了"+score+" 分");
		}
		outRespJson(wx, resp);
		
	}
	
	//小程序没有办法将数组传给Java，只能传字符串
	@RequestMapping("/submit")
	public void submit(String json,HttpServletResponse resp){
		System.out.println(json);
		Gson gson = new Gson();
		List<ExamBean> objList = gson.fromJson(json, new TypeToken<List<ExamBean>>(){}.getType());
		for(ExamBean bean : objList){
			examMapper.insert(bean);
		}
		outRespJson(new WxResp(), resp);
	}
	
	
	
	
	
	
	
	
	
	@ResponseBody
	@RequestMapping("/random")
	public List<QuestionBean> random(String exam){
		List<QuestionBean> questionList = null;
		List<QuestionBean> allQuestion = questionMapper.selectList(exam, "正常");
		if(allQuestion.size()<=5){
			questionList = allQuestion;
		}else {
			Random random = new Random();
			Set<Integer> set = new HashSet<>();//列表，里面元素不会重复
			while (true) {
				int r = random.nextInt(allQuestion.size());
				set.add(r);
				if(set.size()==5){
					break;
				}
			}
			questionList = new ArrayList<>();
			for(int r:set){
				questionList.add(allQuestion.get(r));
				}
		}
		return questionList;
		
	}
	
	
	
	
	
	
	
	@GetMapping("/exam")
	public void exam(HttpServletResponse resp){
		WxResp wx = new WxResp();
		wx.exam = questionMapper.selectRunningExam();
		outRespJson(wx, resp);
	}
	
	
	//@ResponseBody
	@RequestMapping("/login")
	public void login(UserBean bean,HttpServletResponse resp){
		WxResp wx = new WxResp();
		bean.status = "学生";
		if(NotNullUtil.isBlank(bean)){
			wx.fail("请将信息填写完整");
			outRespJson(wx, resp);
			return;
		}
		
		
		try{
		if(StringUtils.isNotBlank(bean.name)){
			userMapper.insert(bean);
			wx.uid = bean.id;
			wx.name = bean.name;
			outRespJson(wx, resp);
			return;
		}}catch (Exception e) {
			// TODO: handle exception
		}
		
		UserBean student = userMapper.selectUserByUsername(bean.username);
		if(student == null){
			wx.fail("请注册该学号");
			outRespJson(wx, resp);
			return;
		}
		UserBean user = userMapper.selectUser(bean);
		if(user == null){
			wx.fail("用户名或密码错误");
			outRespJson(wx, resp);
			return;
		}
		wx.uid = user.id;
		wx.name = user.name;
		//JSON字符串{}代表对象，[]代表数组
		outRespJson(wx, resp);
		
	}

}
