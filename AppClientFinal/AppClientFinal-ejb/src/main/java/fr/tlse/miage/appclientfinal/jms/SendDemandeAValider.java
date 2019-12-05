/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package fr.tlse.miage.appclientfinal.jms;

import com.google.gson.Gson;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.annotation.Resource;
import javax.ejb.Singleton;
import javax.inject.Inject;
import javax.jms.JMSConnectionFactory;
import javax.jms.JMSContext;
import javax.jms.JMSException;
import javax.jms.JMSProducer;
import javax.jms.ObjectMessage;
import javax.jms.Queue;
import fr.tlse.miage.appclientfinal.exports.DemandeExport;
import java.io.Serializable;
import javax.jms.TextMessage;

/**
 *
 * @author SALLABERRYMarion
 */
@Singleton
public class SendDemandeAValider implements SendDemandeAValiderLocal {

    /**
     * Nom du Topic recherché.
     */
    @Resource(mappedName = "DemandesAValider")
    private Queue DemandesAValider;
    /**
     * contexte JMS. Injection auto par serveur d'appli.
     */
    @Inject
    @JMSConnectionFactory("ConnectionFactory")
    private JMSContext context;
    
    private Gson gson;

    public SendDemandeAValider() {
        this.gson = new Gson();
    }

    // Add business logic below. (Right-click in editor and choose
    // "Insert Code > Add Business Method")
    @Override
    public void sendDemande(DemandeExport demande) {
        try {
            JMSProducer producer = context.createProducer();
            TextMessage mess = context.createTextMessage();
            mess.setText(this.gson.toJson(demande));
            mess.setJMSType("DemandeExport");
            context.createProducer().send(DemandesAValider, mess);
            System.out.println(demande + " envoyée.");

        } catch (JMSException ex) {
            Logger.getLogger(SendDemandeAValider.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
}
