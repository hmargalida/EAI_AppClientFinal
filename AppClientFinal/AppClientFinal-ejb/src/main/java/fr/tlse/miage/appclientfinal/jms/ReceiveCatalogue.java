/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package fr.tlse.miage.appclientfinal.jms;

import com.google.gson.Gson;
import fr.tlse.miage.appclientfinal.enumerations.StatutDemande;
import fr.tlse.miage.appclientfinal.exports.CatalogueExport;
import fr.tlse.miage.appclientfinal.exports.DemandeExport;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.ejb.ActivationConfigProperty;
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
    @ActivationConfigProperty(propertyName = "destinationLookup", propertyValue = "Catalogue")
    ,
        @ActivationConfigProperty(propertyName = "destinationType", propertyValue = "javax.jms.Topic")
})
public class ReceiveCatalogue implements MessageListener {
    private Gson gson;
    
    public ReceiveCatalogue(){
        this.gson = new Gson();
    }

    @Override
    public void onMessage(Message message) {
        if (message instanceof TextMessage) {
            try {
                String json = ((TextMessage) message).getText();
                CatalogueExport catalogue = this.gson.fromJson(json, CatalogueExport.class);
                System.out.println("Received: " + catalogue);
            } catch (JMSException ex) {
                Logger.getLogger(ReceiveDemandeValidee.class.getName()).log(Level.SEVERE, null, ex);
            }
        } else if (message != null) {
            System.out.println("Echec de réception du message");
        }
    }

    // Add business logic below. (Right-click in editor and choose
    // "Insert Code > Add Business Method")
}
