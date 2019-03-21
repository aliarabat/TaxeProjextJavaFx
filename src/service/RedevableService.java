/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package service;

import bean.Redevable;
import java.util.Date;

/**
 *
 * @author ALI
 */
public class RedevableService extends AbstractFacade<Redevable>{
    
    public RedevableService() {
        super(Redevable.class);
    }
    
    public Redevable createRedevable(String cin, String prenom, String nom, Long numero, String email, Date naissance){
        Redevable r=new Redevable(cin, prenom, nom, numero, email, naissance);
        create(r);
        return r;
    }
    
    public Redevable findByCin(String cin){
        String query="SELECT r FROM Redevable r WHERE r.cin='"+cin+"'";
        return getSingleResult(query);
    }
}
