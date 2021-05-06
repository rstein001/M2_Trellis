/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package fr.insa.stein.cours_s2.trellis.matrice;

/**
 *
 * @author renaud
 */
public class ResGauss {
    
    public int rang;
    public int signature;

    public ResGauss(int rang, int signature) {
        this.rang = rang;
        this.signature = signature;
    }
    
    public ResGauss(int rang) {
        this(rang,1);
    }
    public String toString() {
        return "{ResGauss : rang = " + rang + " ; sigPerm = " + signature + '}';
    }
    
    
}
