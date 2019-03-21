/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package bean;

import java.io.Serializable;
import java.util.Date;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import javax.persistence.Temporal;

/**
 *
 * @author ALI
 */
@Entity
public class Taxe implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;
    private double chiffreAffaire;
    private int trime;
    private int annee;
    private double montant;
    @Temporal(javax.persistence.TemporalType.DATE)
    private Date dateTaxe;
    private boolean simulation;
    @ManyToOne
    private Locale locale;

    public Taxe() {
    }
    
    public Taxe(double chiffreAffaire, int trime, int annee, double montant, Date dateTaxe) {
        this.chiffreAffaire = chiffreAffaire;
        this.trime = trime;
        this.annee = annee;
        this.montant = montant;
        this.dateTaxe = dateTaxe;
    }

    public Taxe(double chiffreAffaire, int trime, int annee, double montant, Date dateTaxe, boolean simulation, Locale locale) {
        this.chiffreAffaire = chiffreAffaire;
        this.trime = trime;
        this.annee = annee;
        this.montant = montant;
        this.dateTaxe = dateTaxe;
        this.simulation = simulation;
        this.locale = locale;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public double getChiffreAffaire() {
        return chiffreAffaire;
    }

    public void setChiffreAffaire(double chiffreAffaire) {
        this.chiffreAffaire = chiffreAffaire;
    }

    public int getTrime() {
        return trime;
    }

    public void setTrime(int trime) {
        this.trime = trime;
    }

    public int getAnnee() {
        return annee;
    }

    public void setAnnee(int annee) {
        this.annee = annee;
    }

    public double getMontant() {
        return montant;
    }

    public void setMontant(double montant) {
        this.montant = montant;
    }

    public Date getDateTaxe() {
        return dateTaxe;
    }

    public void setDateTaxe(Date dateTaxe) {
        this.dateTaxe = dateTaxe;
    }

    public Locale getLocale() {
        return locale;
    }

    public void setLocale(Locale locale) {
        this.locale = locale;
    }
    
    @Override
    public int hashCode() {
        int hash = 0;
        hash += (id != null ? id.hashCode() : 0);
        return hash;
    }

    public boolean isSimulation() {
        return simulation;
    }

    public void setSimulation(boolean simulation) {
        this.simulation = simulation;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof Taxe)) {
            return false;
        }
        Taxe other = (Taxe) object;
        if ((this.id == null && other.id != null) || (this.id != null && !this.id.equals(other.id))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "bean.Taxe[ id=" + id + " ]";
    }

}
