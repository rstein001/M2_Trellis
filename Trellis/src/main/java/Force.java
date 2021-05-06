/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

/**
 *
 * @author renaud
 */
public class Force {
    
    private double Fx;
    private double Fy;

    public Force(double Fx, double Fy) {
        this.Fx = Fx;
        this.Fy = Fy;
    }

    public double getFx() {
        return Fx;
    }

    public void setFx(double Fx) {
        this.Fx = Fx;
    }

    public double getFy() {
        return Fy;
    }

    public void setFy(double Fy) {
        this.Fy = Fy;
    }
    
    
}
