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
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;

/**
 *
 * @author ALI
 */
@Entity
public class Categorie implements Serializable {

    @OneToOne(mappedBy = "categorie")
    private Taux taux;

    private static final long serialVersionUID = 1L;
    @Id
    private String label;
    @OneToMany(mappedBy = "categorie")
    private List<Locale> locales;

    public Categorie() {
    }

    public Categorie(String label) {
        this.label=label;
    }

    public String getLabel() {
        return label;
    }

    public void setLabel(String label) {
        this.label = label;
    }

    public List<Locale> getLocales() {
        return locales;
    }

    public void setLocales(List<Locale> locales) {
        this.locales = locales;
    }

    public Taux getTaux() {
        return taux;
    }

    public void setTaux(Taux taux) {
        this.taux = taux;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (label != null ? label.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the label fields are not set
        if (!(object instanceof Categorie)) {
            return false;
        }
        Categorie other = (Categorie) object;
        if ((this.label == null && other.label != null) || (this.label != null && !this.label.equals(other.label))) {
            return false;
        }
        return true;
    }
}
