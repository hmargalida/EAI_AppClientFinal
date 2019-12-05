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
     * Nom du Topic recherché.
     */
    @Resource(mappedName = "DemandeValidee")
    private Topic DemandeValidee;
    /**
     * contexte JMS. Injection auto par serveur d'appli.
     */
    @Inject
    @JMSConnectionFactory("ConnectionFactory")
    private JMSContext context;

    private Gson gson;
    
    public SendDemandeValidee() {
        this.gson = new Gson();
    }

    @Override
    public void sendDemande(DemandeExport demande) {
        try {
            JMSProducer producer = context.createProducer();
            TextMessage mess = context.createTextMessage();
            mess.setText(this.gson.toJson(demande));
            mess.setJMSType("DemandeExport");
            context.createProducer().send(DemandeValidee, mess);
            System.out.println(demande + " envoyée.");
        } catch (JMSException ex) {
            Logger.getLogger(SendDemandeAValider.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

}
