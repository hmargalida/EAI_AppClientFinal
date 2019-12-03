/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package fr.tlse.miage.appclientfinal.exports;

import java.io.Serializable;
import java.util.ArrayList;

/**
 *
 * @author SALLABERRYMarion
 */
public class CatalogueExport implements Serializable{
    private ArrayList<FormationExport> catalogue;
    
    public CatalogueExport(){
        this.catalogue = new ArrayList<FormationExport>();
    }

    public ArrayList<FormationExport> getCatalogue() {
        return catalogue;
    }
    
}
