/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

/**
 *
 * @author renaud
 */
public class ZoneConstructible {
    
    private double Xmax;
    private double Xmin;
    private double Ymax;
    private double Ymin;

    public ZoneConstructible(double Xmax, double Xmin, double Ymax, double Ymin) {
        this.Xmax = Xmax;
        this.Xmin = Xmin;
        this.Ymax = Ymax;
        this.Ymin = Ymin;
    }

    public double getXmax() {
        return Xmax;
    }

    public void setXmax(double Xmax) {
        this.Xmax = Xmax;
    }

    public double getXmin() {
        return Xmin;
    }

    public void setXmin(double Xmin) {
        this.Xmin = Xmin;
    }

    public double getYmax() {
        return Ymax;
    }

    public void setYmax(double Ymax) {
        this.Ymax = Ymax;
    }

    public double getYmin() {
        return Ymin;
    }

    public void setYmin(double Ymin) {
        this.Ymin = Ymin;
    }
    
}
