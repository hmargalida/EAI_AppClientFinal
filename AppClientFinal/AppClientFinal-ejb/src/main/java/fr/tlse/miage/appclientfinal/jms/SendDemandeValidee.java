/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package fr.tlse.miage.appclientfinal.jms;

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

    public SendDemandeValidee() {

    }

    @Override
    public void sendDemande(String demande, String niveau) {
        try {
            JMSProducer producer = context.createProducer();

            ObjectMessage mess = context.createObjectMessage();
            mess.setJMSType(niveau);
            mess.setObject(demande);
            context.createProducer().send(DemandeValidee, mess);
            System.out.println(demande + " envoyée.");

        } catch (JMSException ex) {
            Logger.getLogger(SendDemandeAValider.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

}
