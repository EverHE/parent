package com.he.syslog;

import org.springframework.beans.BeanUtils;

import java.io.Serializable;

public abstract class Vo implements Serializable {
    private static final long serialVersionUID = 5793835222248175331L;

    // protected Log log = Logs.get();

    public Vo() {
        super();
    }

    public Vo(Object o) {
        if (o != null) {
            try {
                BeanUtils.copyProperties(o, this);
            } catch (Throwable e) {
                // log.warn("Fail to copy properties!", e);
            }
        }
    }

}
