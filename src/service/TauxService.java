/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package service;

import bean.Categorie;
import bean.Taux;

/**
 *
 * @author ALI
 */
public class TauxService extends AbstractFacade<Taux> {

    public TauxService() {
        super(Taux.class);
    }

    public Taux createTaux(double pourcentage, Categorie categorie) {
        Taux taux=new Taux();
        taux.setPourcentage(pourcentage);
        taux.setCategorie(categorie);
        create(taux);
        return taux;
    }
}
