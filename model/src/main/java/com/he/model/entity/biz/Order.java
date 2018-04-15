package com.he.model.entity.biz;

import com.he.model.entity.SuperEntity;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class Order extends SuperEntity<Long> {

    private Integer userId;

    private String number;

    private String note;
}