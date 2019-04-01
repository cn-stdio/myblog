package com.seagull.myblog.service.security;

import com.seagull.myblog.mapper.UserMapper;
import com.seagull.myblog.model.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

/**
 * @author Seagull_gby
 * @date 2019/3/23 12:51
 * Description: 权限认证将用户名与权限关系储存
 */
@Service
public class CustomUserService implements UserDetailsService {

    @Autowired
    private UserMapper userMapper;

    @Override
    public UserDetails loadUserByUsername(String username) {

        User user = userMapper.queryUserByPhone(username);

        if(user == null){
            throw new UsernameNotFoundException("用户名不存在");
        }
        List<SimpleGrantedAuthority> authorities = new ArrayList<>();

        authorities.add(new SimpleGrantedAuthority(user.getRole().getName()));

        return new org.springframework.security.core.userdetails.User(user.getId(),
                user.getPassword(), authorities);
    }
}
