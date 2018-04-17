package com.he.model.entity.sys;

import com.he.model.entity.SuperEntity;
import com.he.model.enums.SysRoleEnum;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class SysRole extends SuperEntity<Long> {
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
