/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package fr.tlse.miage.appclientfinal.exports;

import java.io.Serializable;

/**
 *
 * @author SALLABERRYMarion
 */
public class FormationExport implements Serializable {

    /**
     * Classe permettant de recuperer les informations d'une formation d'une autre
     * application
     */
    
    private Long idFormation;       //identifiant de la formation
    private String intitule;        //intitule de la formation
    private String description;     //description de la formation

    public FormationExport(Long idFormation, String intitule, String description) {
        this.idFormation = idFormation;
        this.intitule = intitule;
        this.description = description;
    }

    public Long getIdFormation() {
        return idFormation;
    }

    public void setIdFormation(Long idFormation) {
        this.idFormation = idFormation;
    }

    public String getIntitule() {
        return intitule;
    }

    public void setIntitule(String intitule) {
        this.intitule = intitule;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

}
