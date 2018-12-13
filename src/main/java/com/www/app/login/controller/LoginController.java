package com.www.app.login.controller;

import java.util.Map;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import com.www.app.login.bs.ILoginBS;
import com.www.app.login.dto.LoginInDTO;
import com.www.app.login.dto.LoginOutDTO;
import com.www.app.login.dto.RegistInDTO;
import com.www.app.login.dto.RegistOutDTO;
import com.www.utils.BeanUtils;
import com.www.utils.CodeUtils;
/**
 * 登入类控制层
 * @author www
 *
 */
@Controller
@RequestMapping("/login")
public class LoginController {
	@Resource
	private ILoginBS bs;
	/**
	 * 进入首页
	 * @param html
	 * @param request
	 * @return
	 */
	@RequestMapping("/login.do")
	public ModelAndView getLoginView(String html,HttpServletRequest request){
		return new ModelAndView("/login/login");
	}
	/**
	 * 用户登入
	 * @param dto
	 * @param request
	 * @return
	 */
	@RequestMapping("/userLogin.do")
	public ModelAndView login(LoginInDTO dto,HttpServletRequest request){
		LoginOutDTO login=BeanUtils.copyWithObject(dto, LoginOutDTO.class);
		if (dto.getPasswd()!=null&&!"".equals(dto.getPasswd())) {
			dto.setPasswd(CodeUtils.produce32MD5(dto.getPasswd()));
		}else {
			login.setInfo("密码不能为空！");
			return new ModelAndView("/login/login", "login", login);
		}
		return bs.login(dto,login);
	}
	/**
	 * 注册
	 * @param dto
	 * @param request
	 * @return
	 */
	@RequestMapping("/regist.do")
	public ModelAndView registUser(RegistInDTO dto,HttpServletRequest request){
		RegistOutDTO regist=BeanUtils.copyWithObject(dto, RegistOutDTO.class);
		if (dto.getPasswd()!=null&&!"".equals(dto.getPasswd())) {
			dto.setPasswd(CodeUtils.produce32MD5(dto.getPasswd()));
		}else {
			regist.setInfo("×密码不能为空！");
			return new ModelAndView("/login/login", "regist", regist);
		}
		return bs.registTA(dto,regist);
	}
	/**
	 * 验证用户ID
	 * @param userId
	 * @return
	 */
	@RequestMapping("/validateId.do")
	public @ResponseBody Map<String,Object> validateUserId(String userId){
		return bs.validateUserId(userId);
	}
}
