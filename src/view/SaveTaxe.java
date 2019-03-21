/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package view;

import bean.Locale;
import bean.Taxe;
import helper.TaxeFxHelper;
import java.util.Date;
import javafx.application.Application;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.HBox;
import javafx.stage.Stage;
import service.LocaleService;
import service.TaxeService;
import util.AlertUtil;

/**
 *
 * @author hp
 */
public final class SaveTaxe extends Application implements EventHandler<ActionEvent> {

    Stage s1 = new Stage();

    public static void main(String[] args) {
        Application.launch(args);
    }
    
    AlertUtil alertUtil=new AlertUtil();
    LocaleService ls = new LocaleService();
    TaxeService ts = new TaxeService();
    TaxeFxHelper taxeFxHelper;

    public SaveTaxe() throws Exception {
        init();
    }

    private void initComponents() {
        //Labels
        codeLocaleLabel = new Label("Code Locale");
        trimLabel = new Label("Trim");
        anneeLabel = new Label("Annee");
        chiffreAffaireLabel = new Label("Chiffre affaire");
        montantLabel = new Label("Montant");
        //Fields
        codeLocaleField = new TextField();
        trimField = new TextField();
        anneeField = new TextField();
        chiffreAffaireField = new TextField();
        montantField = new TextField();
        //Buttons
        verifierBtn = new Button("Verifier");
        simulerBtn = new Button("Simuler");
        saveBtn = new Button("Save");
        prevBtn = new Button("<-");
        searchBtn = new Button("Recherches");
        //TableView
        table1 = new TableView();
        //disabled buttons
        trimField.setDisable(true);
        anneeField.setDisable(true);
        montantField.setDisable(true);
    }

    @Override
    public void init() throws Exception {
        initComponents();
        initHelper();
    }

    @Override
    public void start(Stage primaryStage) throws Exception {
        //gridPane
        GridPane gridPane1 = new GridPane();
        gridPane1.setHgap(15);
        gridPane1.setVgap(20);
        gridPane1.setStyle("-fx-padding:10;");
        //Hbox
        HBox box1 = new HBox();
        box1.setAlignment(Pos.CENTER);
        box1.setSpacing(10);
        box1.getChildren().addAll(prevBtn, simulerBtn, saveBtn, searchBtn);
        gridPane1.addRow(0, codeLocaleLabel, codeLocaleField, verifierBtn);
        gridPane1.addRow(1, trimLabel, trimField);
        gridPane1.addRow(2, anneeLabel, anneeField);
        gridPane1.addRow(3, chiffreAffaireLabel, chiffreAffaireField);
        gridPane1.addRow(4, montantLabel, montantField);
        gridPane1.add(box1, 1, 5, 1, 1);
        //gridPane1.addRow(5, box1, searchBtn);
        //tableView
        table1.setMaxSize(700, 150);
        //Vbox
        GridPane root = new GridPane();
        root.addRow(0, gridPane1);
        root.addRow(1, table1);

        root.setStyle("-fx-padding:50;" + "-fx-border-style: solid inside;" + "-fx-border-width: 2;"
                + "-fx-border-insets: 5;" + "-fx-border-color: blue;");
        //root.getChildren().addAll(gridPane1, tableView);

        prevBtn.setOnAction(e -> {
            CreerLocale s2 = new CreerLocale();
            s1.close();
            try {
                s2.start(new Stage());
            } catch (Exception ex) {
            }
        });
        verifierBtn.setOnAction(event -> {
            Locale loc = ls.find(codeLocaleField.getText());
            if (loc == null || codeLocaleField.getText().equals("")) {
                alertUtil.showAlert(AlertType.ERROR, "ERROR", "Locale invalide");
            } else {
                int[] res = ts.nextTrim(loc.getDernierTrime(), loc.getDernierAnne());
                trimField.setText(res[0] + "");
                anneeField.setText(res[1] + "");
            }
        });

        simulerBtn.setOnAction(event -> {
            Locale loc = ls.find(codeLocaleField.getText());
            if (loc == null || codeLocaleField.getText().equals("")) {
                alertUtil.showAlert(AlertType.ERROR, "ERROR", "Locale invalide");
            } else if (chiffreAffaireField.getText().equals("")) {
                alertUtil.showAlert(AlertType.ERROR, "ERROR", "Merci de saisir le chiffre");
            } else {
                double montantTaxe = ls.calcMontantTaxe(loc, new Double(chiffreAffaireField.getText()));
                montantField.setText(montantTaxe + "");
            }
        });

        saveBtn.setOnAction(event -> {
            Locale loc = ls.find(codeLocaleField.getText());
            if (loc == null || codeLocaleField.getText().equals("")) {
                alertUtil.showAlert(AlertType.ERROR, "ERROR", "Locale invalide");
            } else if (trimField.getText().equals("") || anneeField.getText().equals("")
                    || chiffreAffaireField.getText().equals("") || montantField.getText().equals("")) {
                alertUtil.showAlert(AlertType.ERROR, "ERROR", "Merci de vérifier les autres champs");
            } else {

                Taxe taxe = new Taxe(new Double(chiffreAffaireField.getText()), new Integer(trimField.getText()), new Integer(anneeField.getText()), new Double(montantField.getText()), new Date(), true, loc);
                ts.saveTaxe(codeLocaleField.getText(), new Double(chiffreAffaireField.getText()), new Integer(trimField.getText()), new Integer(anneeField.getText()), false, new Date());
                taxeFxHelper.create(taxe);
                alertUtil.showAlert(AlertType.INFORMATION, "Infos", "Taxe payé avec succés");
            }
        });

        searchBtn.setOnMouseClicked(event -> addSearchBtnClickedEvent(event));

        primaryStage.setScene(new Scene(root));
        primaryStage.setResizable(false);
        primaryStage.setTitle("Creation taxe");
        primaryStage.setResizable(false);
        primaryStage.show();

        s1 = primaryStage;
    }

    //Labels
    Label codeLocaleLabel;
    Label trimLabel;
    Label anneeLabel;
    Label chiffreAffaireLabel;
    Label montantLabel;
    //Fields
    TextField codeLocaleField;
    TextField trimField;
    TextField anneeField;
    TextField chiffreAffaireField;
    TextField montantField;
    //Buttons
    Button verifierBtn;
    Button simulerBtn;
    Button saveBtn;
    Button prevBtn;
    Button searchBtn;
    //TablesViews
    TableView table1;

    private void initHelper() {
        taxeFxHelper = new TaxeFxHelper(table1);
    }
    
    private void addSearchBtnClickedEvent(MouseEvent event) {
        SearchByCriteria s2 = new SearchByCriteria();
        s1.close();
        try {
            s2.start(new Stage());
        } catch (Exception e) {
        }
    }

    @Override
    public void handle(ActionEvent event) {
    }
}
