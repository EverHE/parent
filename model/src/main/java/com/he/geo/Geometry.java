package com.he.geo;

import java.util.List;

/**
 * 地理，多边形区域
 *
 */
public class Geometry {
    
    private String type;
    
    private List<Coordinate> coordinates;

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public List<Coordinate> getCoordinates() {
        return coordinates;
    }

    public void setCoordinates(List<Coordinate> coordinates) {
        this.coordinates = coordinates;
    }
    
    
}
