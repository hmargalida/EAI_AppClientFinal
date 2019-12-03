/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package fr.tlse.miage.appclientfinal.jms;

import fr.tlse.miage.appclientfinal.exports.DemandeExport;
import javax.ejb.Local;

/**
 *
 * @author SALLABERRYMarion
 */
@Local
public interface SendDemandeAValiderLocal {

    void sendDemande(DemandeExport demande);
    
}
