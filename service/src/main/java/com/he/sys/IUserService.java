package com.he.sys;

import com.he.base.ISuperService;
import com.he.entity.sys.SysUser;

public interface IUserService extends ISuperService<SysUser> {
    int addUser(SysUser user);
    int test ();
}
