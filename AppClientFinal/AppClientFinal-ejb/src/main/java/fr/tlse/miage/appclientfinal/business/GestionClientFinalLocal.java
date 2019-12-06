/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package fr.tlse.miage.appclientfinal.business;

import javax.ejb.Local;

/**
 *
 * @author SALLABERRYMarion
 */
@Local
public interface GestionClientFinalLocal {

    /**
     * Envoi une demande de formation effectuée par une entreprise (client)
     *
     * @param idClient - identifiant du client
     * @param nomClient - nom du client
     * @param idFormation - identifiant de la formation
     * @param intitule - intitule de la formation
     * @param nbParticipants - nombre de personnes de l'entreprise participant à
     * la formation
     * @return - message confirmant le bon envoi de la demande
     */
    String demanderFormation(long idClient, String nomClient, long idFormation, String intitule, int nbParticipants);

}
