package com.boot.exam.mapper;

import java.util.List;

import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.boot.exam.bean.QuestionBean;

public interface QuestionMapper extends BaseMapper<QuestionBean> {
	//视图只能查询数据，不能添加修改视图。
	//sql脚本,可以写判断分支结构
	@Select("<script>"
			+ "select * from v_question "
			+ "<where> "
				+"<if test='exam!=null and exam!=\"\"'>"//exam不等于null或空字符串
					+ "username=#{exam}"
				+"</if>"
					+"<if test='quit!=null and quit!=\"\"'>"
				+"and quit=#{quit}"
				+"</if>"
			+ "</where>"
		+ "</script>")
	List<QuestionBean> selectList(@Param("exam")String exam,@Param("quit")String quit);
	//["java",...]
	@Select("select username from v_question group by username")
	List<String>selectExam();
	
	@Select("select username from v_question where quit='正常' group by username")
	List<String>selectRunningExam();
}
