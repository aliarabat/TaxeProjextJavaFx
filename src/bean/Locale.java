/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package bean;

import java.io.Serializable;
import java.util.List;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;

/**
 *
 * @author ALI
 */
@Entity
public class Locale implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    private String code;
    private int dernierTrime;
    private int dernierAnne;
    @ManyToOne
    private Quartier quartier;
    @OneToOne
    private Redevable redevable;
    @ManyToOne
    private Categorie categorie;
    @OneToMany(mappedBy = "locale")
    private List<Taxe> taxes;

    public Locale() {
    }

    public Locale(String code, int dernierTrime, int dernierAnne, Quartier quartier, Redevable redevable, Categorie categorie) {
        this.code = code;
        this.dernierTrime = dernierTrime;
        this.dernierAnne = dernierAnne;
        this.quartier = quartier;
        this.redevable = redevable;
        this.categorie = categorie;
    }

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }

    public int getDernierTrime() {
        return dernierTrime;
    }

    public void setDernierTrime(int dernierTrime) {
        this.dernierTrime = dernierTrime;
    }

    public int getDernierAnne() {
        return dernierAnne;
    }

    public void setDernierAnne(int dernierAnne) {
        this.dernierAnne = dernierAnne;
    }

    public Quartier getQuartier() {
        return quartier;
    }

    public void setQuartier(Quartier quartier) {
        this.quartier = quartier;
    }

    public Redevable getRedevable() {
        return redevable;
    }

    public void setRedevable(Redevable redevable) {
        this.redevable = redevable;
    }

    public Categorie getCategorie() {
        return categorie;
    }

    public void setCategorie(Categorie categorie) {
        this.categorie = categorie;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (code != null ? code.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the code fields are not set
        if (!(object instanceof Locale)) {
            return false;
        }
        Locale other = (Locale) object;
        if ((this.code == null && other.code != null) || (this.code != null && !this.code.equals(other.code))) {
            return false;
        }
        return true;
    }

}
