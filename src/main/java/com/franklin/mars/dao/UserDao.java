package com.franklin.mars.dao;

import com.franklin.mars.domain.SysUser;
import org.apache.ibatis.annotations.Mapper;

@Mapper
public interface UserDao {


    public SysUser findByUserName(String username);
}
