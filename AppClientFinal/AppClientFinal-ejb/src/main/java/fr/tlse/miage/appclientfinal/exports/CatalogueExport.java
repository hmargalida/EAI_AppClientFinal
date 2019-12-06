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
public class CatalogueExport implements Serializable {

    /**
     * Classe permettant de recevoir la list des formations d'une autre
     * application
     */

    private ArrayList<FormationExport> catalogue;   //liste des formations parmi lesquelles les clients peuvent choisir

    public CatalogueExport() {
        this.catalogue = new ArrayList<FormationExport>();
    }

    /**
     * Récupération du catalogue
     *
     * @return - liste des formations disponibles
     */
    public ArrayList<FormationExport> getCatalogue() {
        return catalogue;
    }

}
