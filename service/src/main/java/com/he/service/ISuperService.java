package com.he.service;

import com.baomidou.mybatisplus.service.IService;
import com.he.model.entity.SuperEntity;

public interface ISuperService<T extends SuperEntity> extends IService<T> {
    /**
     * 根据Id物理删除数据
     * @param id
     * @return
     */
    int relDelById(Long id);

    /**
     * 无聊删除所有数据
     * @return
     */
    int relDelAll();
}
