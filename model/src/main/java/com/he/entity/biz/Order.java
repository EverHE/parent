package com.he.entity.biz;

import com.he.entity.BaseEntity;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class Order extends BaseEntity<Long> {

    private Integer userId;

    private String number;

    private String note;


}