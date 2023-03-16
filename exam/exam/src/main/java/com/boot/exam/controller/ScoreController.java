package com.boot.exam.controller;

import java.util.ArrayList;
import java.util.List;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import com.boot.exam.mapper.ExamMapper;
import com.boot.exam.mapper.UserMapper;
import com.google.gson.Gson;

@Controller
@RequestMapping("/score")
public class ScoreController {
	@Autowired
	ExamMapper examMapper;
	@Autowired
	UserMapper userMapper;
	
	@RequestMapping("/echarts")
	public String echarts(String exam,HttpServletRequest req){
		List<Integer> uidList = examMapper.selectUid(exam);
		req.setAttribute("exam", exam);
		List<Integer> scoreList = new ArrayList<>();
		List<String> nameList = new ArrayList<>();
		for(int uid:uidList){
			int count = examMapper.selectCount(uid, exam);
			int yes = examMapper.selectYesCount(uid, exam);
			int score = yes*100/count;
			scoreList.add(score);
			
			nameList.add(userMapper.selectById(uid).name);
		}
		Gson gson = new Gson();
	    req.setAttribute("scoreList",gson.toJson(scoreList));
	    req.setAttribute("nameList", gson.toJson(nameList));
		return "/score/echarts";
		
	}

}
