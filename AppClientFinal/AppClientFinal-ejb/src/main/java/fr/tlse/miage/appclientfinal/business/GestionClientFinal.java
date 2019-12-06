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
    /**
     * Provider JMS servant à envoyer la demande à l'AppCommerciale
     */
    @EJB
    private SendDemandeAValiderLocal sendDemandeAValider;

    /**
     * Envoi une demande de formation effectuée par une entreprise (client)
     * 
     * @param idClient - identifiant du client
     * @param nomClient - nom du client
     * @param idFormation - identifiant de la formation
     * @param intitule - intitule de la formation
     * @param nbParticipants - nombre de personnes de l'entreprise participant à la formation
     * @return - message confirmant le bon envoi de la demande
     */
    @Override
    public String demanderFormation(long idClient, String nomClient, long idFormation, String intitule, int nbParticipants) {
        DemandeExport demande = new DemandeExport(StatutDemande.En_attente, idFormation, nbParticipants, idClient);
        //Envoi de la demande à l'AppCommerciale
        this.sendDemandeAValider.sendDemande(demande);
        return "La demande a été envoyée !";
    }
  
    //Récupérer compte-rendu ?   
}
