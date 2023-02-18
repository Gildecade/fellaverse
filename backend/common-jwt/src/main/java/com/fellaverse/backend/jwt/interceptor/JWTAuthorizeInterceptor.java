package com.fellaverse.backend.jwt.interceptor;

import com.fellaverse.backend.jwt.annotation.JWTCheckToken;
import com.fellaverse.backend.jwt.code.JWTResponseCode;
import com.fellaverse.backend.jwt.service.JWTTokenService;
import com.fellaverse.backend.jwt.util.JWTMemberDataService;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.util.StringUtils;
import org.springframework.web.method.HandlerMethod;
import org.springframework.web.servlet.HandlerInterceptor;

import java.lang.reflect.Method;

public class JWTAuthorizeInterceptor implements HandlerInterceptor {
    // a name for receiving token, token passed by rewriting address or header
    private static final String TOKEN_NAME = "fellaverse-token";
    @Autowired  // get roles and functions
    private JWTMemberDataService memberDataService;
    @Autowired  // check JWT validity
    private JWTTokenService tokenService;

    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {
        boolean allowed = true;  // whether being allowed
        // class mismatch
        if (!(handler instanceof HandlerMethod)) {
            return allowed;
        }
        // parse functions from handler
        HandlerMethod handlerMethod = (HandlerMethod) handler;
        // get called method
        Method method = handlerMethod.getMethod();
        response.setContentType("application/json");
        // if current class has specific annotation
        if (method.isAnnotationPresent(JWTCheckToken.class)) {
            JWTCheckToken checkToken = method.getAnnotation(JWTCheckToken.class);
            // if annotation enabled
            if (checkToken.required()) {
                // get token from para or header
                String token = this.memberDataService.getToken(request, TOKEN_NAME);
                // if no token
                if (!StringUtils.hasLength(token)) {
                    allowed = false;
                    // return error code in response
                    response.setStatus(JWTResponseCode.TOKEN_UNAUTHORIZED.getCode());
                    response.getWriter().print(JWTResponseCode.TOKEN_UNAUTHORIZED.getMessage());
                } else {  // token exists
                    // token verification fail
                    if (!this.tokenService.verifyToken(token)) {
                        allowed = false;
                        // return error code in response
                        response.setStatus(JWTResponseCode.TOKEN_EXPIRED.getCode());
                        response.getWriter().print(JWTResponseCode.TOKEN_EXPIRED.getMessage());
                    } else {
                        // role verification needed
                        if (!(checkToken.role() == null || "".equals(checkToken.role()))) {
                            // parse all roles from token, then check roles meet annotation requirement
                            if (this.memberDataService.roles(token).contains(checkToken.role())) {
                                allowed = true;
                            } else {
                                // role verification fail
                                allowed = false;
                                response.setStatus(JWTResponseCode.TOKEN_UNAUTHORIZED.getCode());
                                response.getWriter().print(JWTResponseCode.TOKEN_UNAUTHORIZED);
                            }
                        } else if (!(checkToken.function() == null || "".equals(checkToken.function()))) {
                            // parse all roles from token, then check roles meet annotation requirement
                            if (this.memberDataService.functions(token).contains(checkToken.function())) {
                                allowed = true;
                            } else {
                                // role verification fail
                                allowed = false;
                                response.setStatus(JWTResponseCode.TOKEN_UNAUTHORIZED.getCode());
                                response.getWriter().print(JWTResponseCode.TOKEN_UNAUTHORIZED);
                            }
                        } else {
                            // enabled annotation but no role or function required
                            allowed = true;
                        }
                    }
                }
            }
        }
        return allowed;
    }
}

