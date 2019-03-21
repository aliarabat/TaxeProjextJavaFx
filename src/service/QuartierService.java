/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package service;

import bean.Quartier;
import bean.Secteur;
import java.util.List;

/**
 *
 * @author ALI
 */
public class QuartierService extends AbstractFacade<Quartier>{
    
    public QuartierService() {
        super(Quartier.class);
    }
    
    public Quartier createQuartier(String nom, Secteur secteur){
        Quartier q=new Quartier();
        q.setNom(nom);
        q.setSecteur(secteur);
        create(q);
        return q;
    }
    
    public List<Quartier> findQuartierBySecteur(Secteur secteur){
        String query= "SELECT q FROM Quartier q WHERE q.secteur.nom='"+secteur.getNom()+"'";
        return getMultipleResult(query);
    }
}
