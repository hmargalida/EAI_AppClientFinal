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
import javax.jms.Queue;
import fr.tlse.miage.appclientfinal.exports.DemandeExport;
import java.io.Serializable;

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

    public SendDemandeAValider() {

    }

    // Add business logic below. (Right-click in editor and choose
    // "Insert Code > Add Business Method")
    @Override
    public void sendDemande(DemandeExport demande, String niveau) {
        try {
            JMSProducer producer = context.createProducer();

            ObjectMessage mess = context.createObjectMessage();
            mess.setJMSType(niveau);
            mess.setObject((Serializable) demande);
            context.createProducer().send(DemandesAValider, mess);
            System.out.println(demande + " envoyée.");

        } catch (JMSException ex) {
            Logger.getLogger(SendDemandeAValider.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
}