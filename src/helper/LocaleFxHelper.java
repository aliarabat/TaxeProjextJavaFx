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

    @Override
    public Object getValueAt(int rowIndex, int columnIndex) {
        if (list != null && rowIndex < list.size()) {
            for (int i = 0; i < abstractFxHelperItem.length; i++) {
                switch (columnIndex) {
                    case 3:
                        return list.get(rowIndex).getQuartier();
                    //case 4:
                    //    return list.get(rowIndex).getCategorie().getLabel();
                    default:
                        return super.getValueAt(rowIndex, columnIndex);
                }
            }
        }
        return null;
    }

    @Override
    public int getRowCount() {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public int getColumnCount() {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }
}
