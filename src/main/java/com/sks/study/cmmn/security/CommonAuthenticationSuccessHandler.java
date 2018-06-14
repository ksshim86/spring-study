package com.sks.study.cmmn.security;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.log4j.Logger;
import org.springframework.security.core.Authentication;
import org.springframework.security.web.authentication.AuthenticationSuccessHandler;

public class CommonAuthenticationSuccessHandler implements AuthenticationSuccessHandler {
	private static final Logger logger = Logger.getLogger(CommonAuthenticationSuccessHandler.class);
	
//	private RequestCache requestCache = new HttpSessionRequestCache();
//	private String targetUrlParameter;
//	private String defaultUrl;
//	private boolean useReferer;
	
	@Override
    public void onAuthenticationSuccess(HttpServletRequest request, HttpServletResponse response, Authentication authentication) throws IOException, ServletException
    {
        /* 클라이언트 접속 아이피 가져오기 */
        String clientIP = request.getHeader("X_FORWARDED_FOR");
        logger.debug( "clientIP : " + clientIP );
        if (clientIP == null || clientIP.length() == 0 || clientIP.toLowerCase().equals("unknown"))
        {
            clientIP = request.getHeader("Proxy-Client-IP");
        }
        if (clientIP == null || clientIP.length() == 0 || clientIP.toLowerCase().equals("unknown"))
        {
            clientIP = request.getRemoteAddr();
        }
        
        
//        /* 사용자 아이디/역할 가져오기 */
//        String userIdentity = "";
//        List<String> roleList = new ArrayList<String>();
//        Collection<GrantedAuthority> authorityList;
//        Object principal = authentication.getPrincipal();
//        if (principal != null && principal instanceof UserInfoVO)
//        {
//            userIdentity = ((UserInfoVO) principal).getUsername();
//            authorityList = (Collection<GrantedAuthority>) ((UserInfoVO) principal).getAuthorities();
//
//            for (GrantedAuthority authority : authorityList)
//            {
//                roleList.add(authority.getAuthority());
//            }
//        }
//        
//        /* 사용자 정보 세션 저장 */
//        HttpSession session = request.getSession(false);
//        session.setAttribute("userId", "guest");
//        session.setAttribute("userName", "게스트");
//        session.setAttribute("userType", "일반");
//
//        super.onAuthenticationSuccess(request, response, authentication);
    }
}
