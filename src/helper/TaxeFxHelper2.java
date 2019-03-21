package helper;

import bean.Taxe;

import java.util.List;

import javafx.scene.control.TableView;

public class TaxeFxHelper2 extends AbstractFxHelper<Taxe> {

    private static AbstractFxHelperItem[] titres;

    static {

        titres = new AbstractFxHelperItem[]{
            new AbstractFxHelperItem("Trim", "trime"),
            new AbstractFxHelperItem("Annee", "annee"),
            new AbstractFxHelperItem("montant", "montant")};
    }

    public TaxeFxHelper2(TableView<Taxe> table, List<Taxe> list) {
        super(titres, table, list);
    }

    public TaxeFxHelper2(TableView<Taxe> table) {
        super(titres, table);
    }
}
