package com.geekbrains.geekmarketwinter.config;

import com.geekbrains.geekmarketwinter.entites.User;
import com.geekbrains.geekmarketwinter.services.UserService;
import com.geekbrains.geekmarketwinter.utils.RabbitProviderAuthErr;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.web.authentication.AuthenticationSuccessHandler;
import org.springframework.stereotype.Component;
import org.springframework.web.bind.annotation.GetMapping;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;

@Component
public class CustomAuthenticationSuccessHandler implements AuthenticationSuccessHandler {
	private RabbitProviderAuthErr rabbitProviderAuthErr;
    private UserService userService;

    @Autowired
	public void setUserService(UserService userService) {
		this.userService = userService;
	}

	@Autowired
	public void setRabbitProviderAuthErr(RabbitProviderAuthErr rabbitProviderAuthErr) {
		this.rabbitProviderAuthErr = rabbitProviderAuthErr;
	}

	@Override
	public void onAuthenticationSuccess(HttpServletRequest request, HttpServletResponse response, Authentication authentication)
			throws IOException, ServletException {
		String userName = authentication.getName();
		User theUser = userService.findByUserName(userName);
		HttpSession session = request.getSession();
		session.setAttribute("user", theUser);
		if(!request.getHeader("referer").contains("login")) {
			response.sendRedirect(request.getHeader("referer"));
			rabbitProviderAuthErr.openConnect();
			rabbitProviderAuthErr.sendMsg("Неудачная авторизация. Логин " + userName);
		} else {
			response.sendRedirect(request.getContextPath() + "/shop");
		}
	}
}
