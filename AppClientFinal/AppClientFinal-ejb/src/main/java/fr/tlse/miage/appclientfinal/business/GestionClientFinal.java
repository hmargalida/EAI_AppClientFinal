/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package fr.tlse.miage.appclientfinal.business;

import fr.tlse.miage.appclientfinal.enumerations.StatutDemande;
import fr.tlse.miage.appclientfinal.exports.DemandeExport;
import fr.tlse.miage.appclientfinal.jms.SendDemandeAValiderLocal;
import javax.ejb.EJB;
import javax.ejb.Stateless;

/**
 *
 * @author SALLABERRYMarion
 */
@Stateless
public class GestionClientFinal implements GestionClientFinalLocal {

    @EJB
    private SendDemandeAValiderLocal sendDemandeAValider;

    @Override
    public String demanderFormation(long idClient, String nomClient, long idFormation, String intitule, int nbParticipants) {
        DemandeExport demande = new DemandeExport(StatutDemande.En_attente, idFormation, nbParticipants, idClient);
        this.sendDemandeAValider.sendDemande(demande);
        return "La demande a été envoyée !";
    }
  
    //Récupérer compte-rendu ?   
}
