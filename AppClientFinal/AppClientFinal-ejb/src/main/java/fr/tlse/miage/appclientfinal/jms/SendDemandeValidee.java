/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package fr.tlse.miage.appclientfinal.jms;

import com.google.gson.Gson;
import fr.tlse.miage.appclientfinal.exports.DemandeExport;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.annotation.Resource;
import javax.ejb.Singleton;
import javax.inject.Inject;
import javax.jms.JMSConnectionFactory;
import javax.jms.JMSContext;
import javax.jms.JMSException;
import javax.jms.JMSProducer;
import javax.jms.TextMessage;
import javax.jms.Topic;

/**
 *
 * @author SALLABERRYMarion
 */
@Singleton
public class SendDemandeValidee implements SendDemandeValideeLocal {

    /**
     * Nom du Topic
     */
    @Resource(mappedName = "DemandeValidee")
    private Topic DemandeValidee;
    /**
     * contexte JMS
     */
    @Inject
    @JMSConnectionFactory("ConnectionFactory")
    private JMSContext context;

    private Gson gson;      //Objet permettant d'effectuer des conversions depuis/vers du json

    public SendDemandeValidee() {
        this.gson = new Gson();
    }

    /**
     * Envoi de messages dans la queue DemandesAValider
     *
     * @param demande - demande à envoyer
     */
    @Override
    public void sendDemande(DemandeExport demande) {
        try {
            JMSProducer producer = context.createProducer();
            TextMessage mess = context.createTextMessage();
            //Conversion de l'objet demande en json
            mess.setText(this.gson.toJson(demande));
            mess.setJMSType("DemandeExport");
            //Envoi du message
            context.createProducer().send(DemandeValidee, mess);
            System.out.println(demande + " envoyée.");
        } catch (JMSException ex) {
            Logger.getLogger(SendDemandeValidee.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

}
