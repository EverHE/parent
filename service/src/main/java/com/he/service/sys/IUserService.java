package com.he.service.sys;

import com.he.model.entity.sys.SysUser;
import com.he.service.ISuperService;

public interface IUserService extends ISuperService<SysUser> {
    int addUser(SysUser user);
}
