package com.he.base.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.baomidou.mybatisplus.extension.toolkit.SqlHelper;
import com.he.base.ISuperService;
import com.he.mapper.SuperMapper;
import com.he.entity.BaseEntity;

public class SuperServiceImpl<M extends SuperMapper<T>,T extends BaseEntity> extends ServiceImpl<M,T> implements ISuperService<T> {

    @Override
    public boolean deleteAll() {
        return SqlHelper.delBool(this.baseMapper.deleteAll());
    }
}
