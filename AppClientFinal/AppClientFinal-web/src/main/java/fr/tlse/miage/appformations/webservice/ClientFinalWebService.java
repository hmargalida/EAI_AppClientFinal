/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package fr.tlse.miage.appformations.webservice;

import fr.tlse.miage.appclientfinal.business.GestionClientFinalLocal;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.ws.rs.core.Context;
import javax.ws.rs.core.UriInfo;
import javax.ws.rs.Produces;
import javax.ws.rs.Consumes;
import javax.ws.rs.Path;
import javax.enterprise.context.RequestScoped;
import javax.naming.InitialContext;
import javax.naming.NamingException;
import javax.ws.rs.POST;
import javax.ws.rs.QueryParam;
import javax.ws.rs.core.MediaType;

/**
 * REST Web Service
 *
 * @author SALLABERRYMarion
 */
@Path("clientfinal")
@RequestScoped
public class ClientFinalWebService {

    GestionClientFinalLocal gestionClientFinal = lookupGestionClientFinalLocal();

    @Context
    private UriInfo context;

    /**
     * Creates a new instance of ClientFinalWebService
     */
    public ClientFinalWebService() {
    }

    /**
     * Retrieves representation of an instance of
     * fr.tlse.miage.appformations.webservice.ClientFinalWebService
     *
     * @param idClient
     * @param idFormation
     * @param nomClient
     * @param intitule
     * @param nbParticipants
     * @return an instance of java.lang.String
     */
    @POST
    @Consumes(MediaType.APPLICATION_JSON)
    @Produces(MediaType.APPLICATION_JSON)
    public String postJson(@QueryParam("idClient") long idClient, @QueryParam("idFormation") long idFormation, @QueryParam("nomClient") String nomClient, @QueryParam("intitule") String intitule, @QueryParam("nbParticipants") int nbParticipants ) {
        try {
            return this.gestionClientFinal.demanderFormation(idClient, nomClient, idFormation, intitule, nbParticipants);
        } catch (Exception ex) {
            Logger.getLogger(ClientFinalWebService.class.getName()).log(Level.SEVERE, null, ex);
            return ex.toString();
        }
    }

    private GestionClientFinalLocal lookupGestionClientFinalLocal() {
        try {
            javax.naming.Context c = new InitialContext();
            return (GestionClientFinalLocal) c.lookup("java:global/AppClientFinal-ear/AppClientFinal-ejb-1.0-SNAPSHOT/GestionClientFinal!fr.tlse.miage.appclientfinal.business.GestionClientFinalLocal");
        } catch (NamingException ne) {
            Logger.getLogger(getClass().getName()).log(Level.SEVERE, "exception caught", ne);
            throw new RuntimeException(ne);
        }
    }
}
