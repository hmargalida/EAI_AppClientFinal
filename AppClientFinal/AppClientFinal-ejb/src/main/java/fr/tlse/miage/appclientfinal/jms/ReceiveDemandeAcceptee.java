/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package fr.tlse.miage.appclientfinal.jms;

import com.google.gson.Gson;
import fr.tlse.miage.appclientfinal.enumerations.StatutDemande;
import fr.tlse.miage.appclientfinal.exports.DemandeExport;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.ejb.ActivationConfigProperty;
import javax.ejb.EJB;
import javax.ejb.MessageDriven;
import javax.jms.JMSException;
import javax.jms.Message;
import javax.jms.MessageListener;
import javax.jms.TextMessage;

/**
 *
 * @author SALLABERRYMarion
 */
@MessageDriven(activationConfig = {
    @ActivationConfigProperty(propertyName = "destinationLookup", propertyValue = "DemandeAcceptee")
    ,
        @ActivationConfigProperty(propertyName = "destinationType", propertyValue = "javax.jms.Topic")
})
public class ReceiveDemandeAcceptee implements MessageListener {

    @EJB
    private SendDemandeValideeLocal sendDemandeValidee;
    
    private Gson gson;      //Objet permettant d'effectuer des conversions depuis/vers du json

    public ReceiveDemandeAcceptee() {
        this.gson = new Gson();
    }

    /**
     * Réception des messages contenus dans le topic DemandeAcceptee
     * @param message - message reçu
     */
    @Override
    public void onMessage(Message message) {
        if (message instanceof TextMessage) {
            try {
                //Récupération du contenu du message
                String json = ((TextMessage) message).getText();
                //Conversion du message en objet DemandeExport
                DemandeExport demande = this.gson.fromJson(json, DemandeExport.class);
                //Vérification du statut de la demande
                if (demande.getStatut() == StatutDemande.Refusee) {
                    System.out.println("Received: " + demande + "- demande refusée");
                } else {
                    System.out.println("Received: " + demande + "- demande acceptée");
                    //Renvoi de la demande à l'AppCommerciale
                    this.sendDemandeValidee.sendDemande(demande);
                }
            } catch (JMSException ex) {
                Logger.getLogger(ReceiveDemandeAcceptee.class.getName()).log(Level.SEVERE, null, ex);
            }
        } else if (message != null) {
            System.out.println("Echec de réception du message");
        }
    }

}
