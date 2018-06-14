package com.sks.study.cmmn.security;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.security.core.AuthenticationException;
import org.springframework.security.web.authentication.AuthenticationFailureHandler;

public class CommonAuthenticationFailureHandler implements AuthenticationFailureHandler {
	private String loginIdentity;
    private String loginPassword;
    private String failureUrl;
    
    public CommonAuthenticationFailureHandler()
    {
        this.loginIdentity = "loginIdentity";
        this.loginPassword = "loginPassword";
        this.failureUrl = "/login.do?fail=true";
    }

    @Override
    public void onAuthenticationFailure(HttpServletRequest request, HttpServletResponse response, AuthenticationException authenticationException) throws IOException, ServletException
    {
        request.setAttribute(loginIdentity, request.getParameter(loginIdentity));
        request.setAttribute(loginPassword, request.getParameter(loginPassword));
        request.setAttribute("message", authenticationException.getMessage());

        request.getRequestDispatcher(failureUrl).forward(request, response);
    }

	public String getLoginIdentity() {
		return loginIdentity;
	}

	public void setLoginIdentity(String loginIdentity) {
		this.loginIdentity = loginIdentity;
	}

	public String getLoginPassword() {
		return loginPassword;
	}

	public void setLoginPassword(String loginPassword) {
		this.loginPassword = loginPassword;
	}

	public String getFailureUrl() {
		return failureUrl;
	}

	public void setFailureUrl(String failureUrl) {
		this.failureUrl = failureUrl;
	}

}
