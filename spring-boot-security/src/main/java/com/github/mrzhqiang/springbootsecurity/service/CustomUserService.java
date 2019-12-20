package com.github.mrzhqiang.springbootsecurity.service;

import com.github.mrzhqiang.springbootsecurity.model.SysUser;
import com.github.mrzhqiang.springbootsecurity.repository.SysUserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;

public class CustomUserService implements UserDetailsService {
  @Autowired
  SysUserRepository sysUserRepository;

  @Override public UserDetails loadUserByUsername(String username)
      throws UsernameNotFoundException {
    SysUser sysUser = sysUserRepository.findByUsername(username);
    if (sysUser == null) {
      throw new UsernameNotFoundException("用户名不存在");
    }
    return sysUser;
  }
}
