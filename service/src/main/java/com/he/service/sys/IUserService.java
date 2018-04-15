package com.he.service.sys;

import com.he.model.entity.sys.User;
import com.he.service.ISuperService;

public interface IUserService extends ISuperService<User> {
    int addUser(User user);
}
