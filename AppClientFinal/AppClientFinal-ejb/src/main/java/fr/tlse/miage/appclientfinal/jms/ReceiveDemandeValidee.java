/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package fr.tlse.miage.appclientfinal.jms;

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
    @ActivationConfigProperty(propertyName = "destinationLookup", propertyValue = "DemandeValidee")
    ,
        @ActivationConfigProperty(propertyName = "destinationType", propertyValue = "javax.jms.Topic")
})
public class ReceiveDemandeValidee implements MessageListener {

    @EJB
    private SendDemandeValideeLocal sendDemandeValidee;

    public ReceiveDemandeValidee() {
        //this.gson = new Gson();
    }

    // Add business logic below. (Right-click in editor and choose
    // "Insert Code > Add Business Method")
    @Override
    public void onMessage(Message message) {
        if (message instanceof TextMessage) {
            try {
                String json = ((TextMessage) message).getText();
                String msg = json;
                
                if (msg.contains("REFUSEE")) {
                    System.out.println("Received: " + json);
                } else {
                    String niveau = "";
                    this.sendDemandeValidee.sendDemande(msg, niveau);
                }

            } catch (JMSException ex) {
                Logger.getLogger(ReceiveDemandeValidee.class.getName()).log(Level.SEVERE, null, ex);
            }
        } else if (message != null) {
            System.out.println("Echec de réception du message");
        }
    }

}
