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
    /*
    @Override
    public Object getValueAt(int rowIndex, int columnIndex) {
        if (list != null && rowIndex < list.size()) {
            for (int i = 0; i < abstractHelperItem.length; i++) {
                switch (columnIndex) {
                    case 3:
                        return list.get(rowIndex).getQuartier().getNom();
                    case 4:
                        return list.get(rowIndex).getCategorie().getLabel();
                    default:
                        return super.getValueAt(rowIndex, columnIndex);
                }
            }
        }
        return null;
    }
     */

    @Override
    public int getRowCount() {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public int getColumnCount() {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }
}
