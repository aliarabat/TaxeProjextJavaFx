/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package service;

import bean.Categorie;

/**
 *
 * @author ALI
 */
public class CategorieService extends AbstractFacade<Categorie> {

    public CategorieService() {
        super(Categorie.class);
    }

    public Categorie createCategorie(String Label) {
        Categorie c = new Categorie();
        c.setLabel(Label);
        create(c);
        return c;
    }

    public Categorie findByCategorie(Categorie categorie) {
        String query = "SELECT label FROM Categorie WHERE label='" + categorie.getLabel() + "'";
        return getSingleResult(query);
    }
}
