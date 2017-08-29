package com.example.shariqkhan.gawadar247.DaniyalClasses;

/**
 * Created by zameer on 05/08/2017.
 */
public class Data {
    String area, price, type, descr, plot;

    public Data(String area, String price, String type, String descr, String plot) {
        this.area = area;
        this.price = price;
        this.type = type;
        this.descr = descr;
        this.plot = plot;
    }

    public String getArea() {
        return area;
    }

    public void setArea(String area) {
        this.area = area;
    }

    public String getPrice() {
        return price;
    }

    public void setPrice(String price) {
        this.price = price;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public String getDescr() {
        return descr;
    }

    public void setDescr(String descr) {
        this.descr = descr;
    }

    public String getPlot() {
        return plot;
    }

    public void setPlot(String plot) {
        this.plot = plot;
    }
}
