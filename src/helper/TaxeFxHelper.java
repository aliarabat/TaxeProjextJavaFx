package helper;

import bean.Taxe;

import java.util.List;

import javafx.scene.control.TableView;

public class TaxeFxHelper extends AbstractFxHelper<Taxe> {

    private static AbstractFxHelperItem[] titres;

    static {

        titres = new AbstractFxHelperItem[]{
            new AbstractFxHelperItem("ChiffreAffaire", "chiffreAffaire"),
            new AbstractFxHelperItem("Trim", "trime"),
            new AbstractFxHelperItem("Annee", "annee"),
            new AbstractFxHelperItem("montant", "montant"),
            new AbstractFxHelperItem("DateTaxe", "dateTaxe")};
    }

    public TaxeFxHelper(TableView<Taxe> table, List<Taxe> list) {
        super(titres, table, list);
    }

    public TaxeFxHelper(TableView<Taxe> table) {
        super(titres, table);
    }

}
