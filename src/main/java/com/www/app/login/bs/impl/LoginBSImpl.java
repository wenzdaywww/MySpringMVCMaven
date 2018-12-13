/**
 * 
 */
package com.www.app.login.bs.impl;

import java.util.HashMap;
import java.util.Map;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;
import org.springframework.web.servlet.ModelAndView;

import com.www.app.entity.Sysuser;
import com.www.app.login.bs.ILoginBS;
import com.www.app.login.dto.LoginInDTO;
import com.www.app.login.dto.LoginOutDTO;
import com.www.app.login.dto.RegistInDTO;
import com.www.app.login.dto.RegistOutDTO;
import com.www.utils.BeanUtils;
import com.www.utils.orm.IHibernateDB;

/**
 * 用户登入
 * @author www
 */
@Service
public class LoginBSImpl implements ILoginBS {
	@Resource
	private IHibernateDB op;
	/**
	 * 登入
	 * @param dto
	 * @return
	 */
	public ModelAndView login(LoginInDTO dto,LoginOutDTO login) {
		if (BeanUtils.isEmpty(dto)) {
			login.setInfo("用户或密码不能为空！");
			return new ModelAndView("/login/login", "login", login);
		}
		Sysuser user=op.queryUniqueBySqlClassWithObject("select * from sysuser where userid=:userid and passwd=:passwd", dto, Sysuser.class);
		if (user==null) {
			login.setInfo("用户或密码错误！");
			return new ModelAndView("/login/login", "login", login);
		}else {
			return new ModelAndView("/main/show", "user", user);
		}
	}
	/**
	 * 注册
	 * @param dto
	 * @return
	 */
	public ModelAndView registTA(RegistInDTO dto,RegistOutDTO regist) {
		if (BeanUtils.isEmpty(dto)) {
			regist.setInfo("×注册信息不能为空！");
			return new ModelAndView("/login/login", "regist", regist);
		}
		Sysuser user=BeanUtils.copyWithObject(dto, Sysuser.class);
		int count=op.getCountBySqlWithObject("select count(*) from sysuser where userid=:userId", user);
		if (count>0) {
			regist.setInfo("×此用户ID已被使用！");
			return new ModelAndView("/login/login", "regist", regist);
		}else {
			user.setReDate(op.getDBTimestamp());
			op.save(user);
			return new ModelAndView("/main/show", "user", user);
		}
	}
	/**
	 * 用户ID校验
	 * @param userId
	 * @return
	 */
	public Map<String,Object> validateUserId(String userId) {
		Map<String,Object> map = new HashMap<String,Object>(); 
		int count=op.getCountBySqlWithString("select count(*) from sysuser where userid='"+userId+"'");
		if (count>0) {
			map.put("infos", "fail");
		}else {
			map.put("infos", "success");
		}
		return map;
	}


}
