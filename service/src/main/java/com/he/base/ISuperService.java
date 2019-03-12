package com.he.base;


import com.baomidou.mybatisplus.extension.service.IService;
import com.he.entity.BaseEntity;

public interface ISuperService<T extends BaseEntity> extends IService<T> {
    boolean deleteAll();
}
