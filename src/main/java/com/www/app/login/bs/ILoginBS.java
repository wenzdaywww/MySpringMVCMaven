package com.www.app.login.bs;

import java.util.Map;

import org.springframework.web.servlet.ModelAndView;

import com.www.app.login.dto.LoginInDTO;
import com.www.app.login.dto.LoginOutDTO;
import com.www.app.login.dto.RegistInDTO;
import com.www.app.login.dto.RegistOutDTO;

/**
 * 用户登入
  * @author www
 */
public interface ILoginBS {
	/**
	 * 登入
	 * @param dto
	 * @return
	 */
	public abstract ModelAndView login(LoginInDTO dto,LoginOutDTO login);
	/**
	 * 注册
	 * @param dto
	 * @return
	 */
	public abstract ModelAndView registTA(RegistInDTO dto,RegistOutDTO regist);
	/**
	 * 用户ID校验
	 * @param userId
	 * @return
	 */
	public abstract Map<String,Object> validateUserId(String userId);
}
