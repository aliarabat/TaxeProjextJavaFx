/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package service;

import bean.Categorie;
import bean.Locale;
import bean.Taxe;
import java.util.Date;
import java.util.List;

/**
 *
 * @author ALI
 */
public class TaxeService extends AbstractFacade<Taxe> {

    public TaxeService() {
        super(Taxe.class);
    }

    public List<Taxe> findTaxeByCode(String codeLoc) {
        String query = "SELECT t FROM Taxe t WHERE t.locale.code='" + codeLoc + "' ORDER BY t.annee  DESC, t.trime DESC";
        return getMultipleResult(query);
    }

    public List<Taxe> findByCriteria(String codeLocale, Categorie categorie,
            Integer anneMin, Integer anneMax, Integer trimMin, Integer trimMax) {
        String query = constructQuery(codeLocale, categorie, anneMin, anneMax, trimMin, trimMax);
        return getMultipleResult(query);
    }

    public String constructQuery(String codeLocale, Categorie categorie,
            Integer anneMin, Integer anneMax, Integer trimMin, Integer trimMax) {
        String query = "SELECT t FROM Taxe t WHERE 1=1";
        if (codeLocale != null && !codeLocale.equals((""))) {
            query += " AND t.locale.code='" + codeLocale + "'";
            return query;
        }
        if (categorie != null && !categorie.equals((""))) {
            query += " AND t.locale.categorie.label='" + categorie.getLabel() + "'";
        }
        if (anneMin != null && !anneMin.equals((""))) {
            query += " AND t.annee>=" + anneMin;
        }
        if (anneMax != null && !anneMax.equals((""))) {
            query += " AND t.annee<=" + anneMax;
        }
        if (trimMin != null && !trimMin.equals((""))) {
            query += " AND t.trime>=" + trimMin;
        }
        if (trimMax != null && !trimMax.equals((""))) {
            query += " AND t.trime<=" + trimMax+" ORDER BY t.annee DESC";
        }
        return query;
    }

    public int saveTaxe(String codeLocale, double chiffreAffaire, int trime, int annee, boolean simulation, Date dateTaxe) {
        LocaleService ls = new LocaleService();
        Locale l = ls.find(codeLocale);
        if (l == null || l.getCategorie() == null) {
            return -1;
        } else if (l.getDernierAnne() == annee && l.getDernierTrime() == trime) {
            return -2;
        } else {
            double montanTaxe = ls.calcMontantTaxe(l, chiffreAffaire);
            if (simulation == false) {
                ls.updateLocale(l, trime, annee);
                Taxe taxe = new Taxe(chiffreAffaire, trime, annee, montanTaxe, dateTaxe);
                taxe.setLocale(l);
                taxe.setSimulation(true);
                create(taxe);
                return 1;
            } else {
                return -3;
            }
        }
    }

    public int[] nextTrim(int trime, int annee) {
        int[] res = {trime, annee};
        if (trime >= 0 && trime < 4) {
            res[0] = ++trime;
            res[1] = annee;
        } else if (trime == 4) {
            res[0] = +1;
            res[1] = ++annee;
        }
        return res;
    }

    public int deleteTaxesByCode(String codeLoc) {
        List<Taxe> taxes = findTaxeByCode(codeLoc);
        if (taxes == null) {
            return -1;
        } else {
            taxes.forEach((taxe) -> {
                remove(taxe);
            });
            return 1;
        }
    }
}
