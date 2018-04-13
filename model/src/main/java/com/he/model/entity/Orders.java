package com.he.model.entity;

import lombok.Data;

import java.io.Serializable;
import java.util.Date;

@Data
public class Orders  implements Serializable{
	private static final long serialVersionUID = 1L;

	private Integer id;

    private Integer userId;

    private String number;

    private Date createtime;

    private String note;
}