/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package service;

import bean.Secteur;

/**
 *
 * @author ALI
 */
public class SecteurService extends AbstractFacade<Secteur>{
    
    public SecteurService() {
        super(Secteur.class);
    }
    
    public Secteur createSecteur(String nom){
        Secteur s=new Secteur();
        s.setNom(nom);
        create(s);
        return s;
    }
}
