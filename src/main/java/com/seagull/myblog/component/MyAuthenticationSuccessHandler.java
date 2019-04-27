package com.seagull.myblog.component;

import com.seagull.myblog.mapper.UserMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.web.DefaultRedirectStrategy;
import org.springframework.security.web.authentication.SavedRequestAwareAuthenticationSuccessHandler;
import org.springframework.stereotype.Component;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.Date;

/**
 * @author Seagull_gby
 * @date 2019/3/23 13:44
 * Description: 登录成功后自定义跳转路径（原始路径）
 */

@Component("myAuthenticationSuccessHandler")
public class MyAuthenticationSuccessHandler extends SavedRequestAwareAuthenticationSuccessHandler {

    @Autowired
    private UserMapper userMapper;

    @Override
    public void onAuthenticationSuccess(HttpServletRequest request, HttpServletResponse response, Authentication authentication) throws ServletException, IOException {

        /* 登录成功更新登录时间 */
        userMapper.updateRecentLoginDate(authentication.getName(), new Date());

        if(request.getSession().getAttribute("url")!=null){
            //如果是要跳转到某个页面的
            new DefaultRedirectStrategy().sendRedirect(request, response,(String)request.getSession().getAttribute("url"));
            request.getSession().removeAttribute("url");
        } else {
            super.onAuthenticationSuccess(request, response, authentication);
        }

    }
}