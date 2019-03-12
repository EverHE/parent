package com.he.entity.sys;

import com.he.entity.BaseEntity;
import com.he.enums.ResourceTypeEnum;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class SysResource extends BaseEntity<Long> {
    /**
     * 资源名
     */
    private String name;
    /**
     * 资源编码
     */
    private String code;
    /**
     * 父ID
     */
    private Long parentId;
    /**
     * 图标
     */
    private String ico;
    /**
     * url链接
     */
    private String url;
    /**
     * 类型
     */
    private ResourceTypeEnum type;
    /**
     * 备注
     */
    private String remark;
}
