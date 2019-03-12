package com.he.geo;

/**
 * 网格属性
 *
 */
public class Property {
    private String id;
    
    private String name;
    
    private String parentName;

    public Property(String id, String name, String parentName) {
        super();
        this.id = id;
        this.name = name;
        this.parentName = parentName;
    }

    public Property() {
        super();
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getParentName() {
        return parentName;
    }

    public void setParentName(String parentName) {
        this.parentName = parentName;
    }
    
    
}
