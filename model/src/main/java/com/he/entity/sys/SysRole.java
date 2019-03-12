package com.he.entity.sys;

import com.he.entity.BaseEntity;
import com.he.enums.SysRoleEnum;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class SysRole extends BaseEntity<Long> {
    /**
     * 角色名称
     */
    private String name;
    /**
     * 角色编码
     */
    private String code;
    /**
     * 是否系统角色
     */
    private SysRoleEnum sys;
    /**
     * 备注
     */
    private String remark;
}
