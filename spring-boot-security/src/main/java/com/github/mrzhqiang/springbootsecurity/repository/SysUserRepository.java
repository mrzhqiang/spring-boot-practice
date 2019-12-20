package com.github.mrzhqiang.springbootsecurity.repository;

import com.github.mrzhqiang.springbootsecurity.model.SysUser;
import org.springframework.data.jpa.repository.JpaRepository;

public interface SysUserRepository extends JpaRepository<SysUser, Long> {
  SysUser findByUsername(String username);
}
