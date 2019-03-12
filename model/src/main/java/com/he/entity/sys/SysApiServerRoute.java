package com.he.entity.sys;

import com.he.entity.BaseEntity;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class SysApiServerRoute extends BaseEntity<Long> {
    /**
     * 模块id
     */
    private String moduleId;

    /**
     * apicode
     */
    private String funcId;

    /**
     * api名称
     */
    private String funcName;

    /**
     * 是否需要验证token。0-不验证，1-验证
     */
    private String funcType;

    /**
     * 接口字段，|分割
     * 例：funcno|username|password
     */
    private String dealFile;

    /**
     * 接口服务class名
     */
    private String dealClass;

    /**
     * 接口服务func名
     */
    private String dealFunc;

    /**
     * 接口描述
     */
    private String funcDesc;
}