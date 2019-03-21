package helper;

import bean.Locale;

import java.util.List;

import javafx.scene.control.TableView;

public class LocaleFxHelper extends AbstractFxHelper<Locale> {

    private static AbstractFxHelperItem[] titres;

    static {

        titres = new AbstractFxHelperItem[]{
            new AbstractFxHelperItem("Code", "code"),
            new AbstractFxHelperItem("Dernier Trim", "dernierTrime"),
            new AbstractFxHelperItem("Dernier Annee", "dernierAnne")};
    }

    public LocaleFxHelper(TableView<Locale> table, List<Locale> list) {
        super(titres, table, list);
    }

    public LocaleFxHelper(TableView<Locale> table) {
        super(titres, table);
    }
}
