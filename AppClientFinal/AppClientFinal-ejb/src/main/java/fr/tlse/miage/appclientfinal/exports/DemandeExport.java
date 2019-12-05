/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package fr.tlse.miage.appclientfinal.exports;

import fr.tlse.miage.appclientfinal.enumerations.StatutDemande;
import java.io.Serializable;

/**
 *
 * @author SALLABERRYMarion
 */
public class DemandeExport implements Serializable{

    private long idDemande;
    private StatutDemande statut;
    private long idFormation;
    private int nbParticipants;
    private long idClient;

    public DemandeExport(StatutDemande statut, long idFormation, int nbParticipants, long idClient) {
        this.statut = statut;
        this.idFormation = idFormation;
        this.nbParticipants = nbParticipants;
        this.idClient = idClient;
    }

    public DemandeExport(long idDemande, StatutDemande statut, long idFormation, int nbParticipants, long idClient) {
        this.idDemande = idDemande;
        this.statut = statut;
        this.idFormation = idFormation;
        this.nbParticipants = nbParticipants;
        this.idClient = idClient;
    }

    public long getIdDemande() {
        return idDemande;
    }

    public void setIdDemande(long idDemande) {
        this.idDemande = idDemande;
    }

    public StatutDemande getStatut() {
        return statut;
    }

    public void setStatut(StatutDemande statut) {
        this.statut = statut;
    }

    public long getIdFormation() {
        return idFormation;
    }

    public void setIdFormation(long idFormation) {
        this.idFormation = idFormation;
    }

    public int getNbParticipants() {
        return nbParticipants;
    }

    public void setNbParticipants(int nbParticipants) {
        this.nbParticipants = nbParticipants;
    }

    public long getIdClient() {
        return idClient;
    }

    public void setIdClient(long idClient) {
        this.idClient = idClient;
    }
}
