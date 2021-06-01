/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package fr.insa.stein.cours_s2.trellis.gui;

import fr.insa.stein.cours_s2.trellis.model.Barre;
import fr.insa.stein.cours_s2.trellis.model.Noeud;
import fr.insa.stein.cours_s2.trellis.model.Treillis;
import fr.insa.stein.cours_s2.trellis.model.TriangleTerrain;
import javafx.scene.canvas.Canvas;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.layout.Pane;


public class DessinCanvas extends Pane {
    
    private MainPane main;

    private Canvas realCanvas;

    public DessinCanvas(MainPane main) {
        this.main = main;
        this.realCanvas = new Canvas(this.getWidth(), this.getHeight());
        this.getChildren().add(this.realCanvas);
        this.realCanvas.heightProperty().bind(this.heightProperty());
        this.realCanvas.heightProperty().addListener((o) -> {
            this.redrawAll();
        });
        this.realCanvas.widthProperty().bind(this.widthProperty());
        this.realCanvas.widthProperty().addListener((o) -> {
            this.redrawAll();
        });
        this.redrawAll();
    }

    public void redrawAll() {
        GraphicsContext context = this.realCanvas.getGraphicsContext2D();
        Treillis model = this.main.getTreillis();
        
       
        
            for (TriangleTerrain TT: model.getTT()){
                
                TT.dessine(context);
            }
            
            for (Noeud N: model.getNoeuds()){
                
                N.dessine(context);
            }
            for ( Barre Barre: model.getBarres()){
                
                Barre.dessine(context, main.getTreillis());
            }
            
           
        
    }


}
