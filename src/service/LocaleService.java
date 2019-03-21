/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package service;

import bean.Categorie;
import bean.Locale;
import bean.Quartier;
import bean.Secteur;
import java.util.List;

/**
 *
 * @author ALI
 */
public class LocaleService extends AbstractFacade<Locale> {

    public LocaleService() {
        super(Locale.class);
    }

    public Locale findByCode(String code) {
        String query = "SELECT l FROM Locale l WHERE l.code='" + code + "'";
        return getSingleResult(query);
    }

    public double calcMontantTaxe(Locale locale, double chiffreAffaire) {
        if (locale == null) {
            return -1;
        } else {
            double montanTaxe = (locale.getCategorie().getTaux().getPourcentage() * chiffreAffaire) / 100;
            return montanTaxe;
        }
    }

    public Locale updateLocale(Locale l, int trim, int annee) {
        l.setDernierAnne(annee);
        l.setDernierTrime(trim);
        edit(l);
        return l;
    }

    public List<Locale> findLocaleByCriteria(String codeLocale, Categorie categorie, Secteur secteur, Quartier quartier) {
        String query = constructQuery(codeLocale, categorie, secteur, quartier);
        System.out.println("haa query =>" + query);
        return getMultipleResult(query);
    }

    public String constructQuery(String codeLocale, Categorie categorie, Secteur secteur, Quartier quartier) {
        String query = "SELECT l FROM Locale l WHERE 1=1";
        if (codeLocale != null && !codeLocale.equals("")) {
            query += " AND l.code='" + codeLocale + "'";
            return query;
        }
        if (categorie != null && !categorie.equals("")) {
            query += " AND l.categorie.label='" + categorie.getLabel() + "'";
        }
        if (secteur != null && !secteur.equals("")) {
            query += " AND l.quartier.secteur.nom='" + secteur.getNom() + "'";
        }
        if (quartier != null && !quartier.equals("")) {
            query += " AND l.quartier.nom='" + quartier.getNom() + "'";
        }
        return query;
    }

    public int deleteLocaleByRib(String codeLoc) {
        Locale l = find(codeLoc);
        TaxeService ts = new TaxeService();
        if (l == null) {
            return -1;
        } else {
            ts.deleteTaxesByCode(codeLoc);
            remove(l);
            return 1;
        }
    }
}
