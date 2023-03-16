package com.boot.exam.mapper;

import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.boot.exam.bean.UserBean;

public interface UserMapper extends BaseMapper<UserBean> {
	@Select("select * from tbl_user "
			+"where username=#{username} "
			+"and password=#{password} "
			+"and status=#{status}")
	UserBean selectUser(UserBean bean);
	
	//学生查找账号
	@Select("select * from tbl_user where username=#{username} and status='学生'")
	UserBean selectUserByUsername(@Param("username")String username);
	//这个方法没有方法体，就叫抽象方法
	//interface class
	//1.抽象方法，只能存在于抽象类和接口中，接口里面的方法必须是抽象的
	//class 不能写抽象方法
	@Select("select id from tbl_user where username=#{username}")
	int selectUid(@Param("username")String username);

}
