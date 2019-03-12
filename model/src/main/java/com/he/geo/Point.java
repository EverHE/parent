package com.he.geo;

/**
 * 坐标点位
 *
 */
public class Point {
    
    /**
     * 经度 longitude
     */
    private double x;
    /**
     * 纬度 latitude
     */
    private double y;

    public Point() {
        super();
        // TODO Auto-generated constructor stub
    }
    
    public Point(double longitude, double latitude) {
        super();
        this.x = longitude;
        this.y = latitude;
    }

    public double getX() {
        return x;
    }

    public void setX(double x) {
        this.x = x;
    }

    public double getY() {
        return y;
    }

    public void setY(double y) {
        this.y = y;
    }

    
}
