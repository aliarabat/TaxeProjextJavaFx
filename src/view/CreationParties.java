/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package view;

import bean.Categorie;
import bean.Quartier;
import bean.Secteur;
import java.util.List;
import javafx.application.Application;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.geometry.HPos;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.layout.GridPane;
import javafx.stage.Stage;
import service.CategorieService;
import service.QuartierService;
import service.SecteurService;
import service.TauxService;
import util.AlertUtil;

/**
 *
 * @author hp
 */
public final class CreationParties extends Application implements EventHandler<ActionEvent>{

    Stage s1;
    public static void main(String[] args) {
        Application.launch(args);
    }

    AlertUtil alertUtil = new AlertUtil();
    SecteurService ss = new SecteurService();
    QuartierService qs = new QuartierService();
    CategorieService cs = new CategorieService();
    TauxService ts = new TauxService();
    private List<Secteur> secteurs;
    private List<Categorie> categories;

    public CreationParties() throws Exception {
        init();
    }

    @Override
    public void init() throws Exception {
        initComponents();
        initjComboBoxSecteur();
        initjComboBoxCategorie();
    }

    @Override
    public void start(Stage primaryStage) throws Exception {
        GridPane root = new GridPane();
        root.setStyle("-fx-padding:20;" + "-fx-border-style: solid inside;" + "-fx-border-width: 2;"
                + "-fx-border-insets: 5;" + "-fx-border-radius: 5;" + "-fx-border-color: blue;");
        root.setHgap(30);
        root.setVgap(30);
        /*
        GridPane.setHalignment(saveBtn1, HPos.CENTER);
        GridPane.setHalignment(saveBtn2, HPos.CENTER);
        GridPane.setHalignment(saveBtn3, HPos.CENTER);
        GridPane.setHalignment(saveBtn4, HPos.CENTER);*/

        //implemenetation
        /*box1.getChildren().addAll(secteurLabel, secteurField);
        box2.getChildren().addAll(quartierLabel, quartierField);
        box3.getChildren().addAll(categorieLabel, categorieField);
        box4.getChildren().addAll(tauxLabel, tauxField);
        
        vbox1.getChildren().addAll(box1, saveBtn1);
        vbox1.getChildren().addAll(box2, saveBtn2);
        vbox1.getChildren().addAll(box3, saveBtn3);
        vbox1.getChildren().addAll(box4, saveBtn4);
         */
        GridPane gp1 = new GridPane();
        GridPane gp2 = new GridPane();
        GridPane gp3 = new GridPane();
        GridPane gp4 = new GridPane();

        //styling the gridPanes
        gp1.setStyle("-fx-padding:20;" + "-fx-border-style: solid inside;" + "-fx-border-width: 2;"
                + "-fx-border-insets: 5;" + "-fx-border-color: blue;");
        gp2.setStyle("-fx-padding:20;" + "-fx-border-style: solid inside;" + "-fx-border-width: 2;"
                + "-fx-border-insets: 5;" + "-fx-border-color: blue;");
        gp3.setStyle("-fx-padding:20;" + "-fx-border-style: solid inside;" + "-fx-border-width: 2;"
                + "-fx-border-insets: 5;" + "-fx-border-color: blue;");
        gp4.setStyle("-fx-padding:20;" + "-fx-border-style: solid inside;" + "-fx-border-width: 2;"
                + "-fx-border-insets: 5;" + "-fx-border-color: blue;");
        gp1.setHgap(10);
        gp1.setVgap(10);
        gp2.setHgap(10);
        gp2.setVgap(10);
        gp3.setHgap(10);
        gp3.setVgap(10);
        gp4.setHgap(10);
        gp4.setVgap(10);
        GridPane.setHalignment(saveBtn1, HPos.CENTER);
        GridPane.setHalignment(saveBtn2, HPos.CENTER);
        GridPane.setHalignment(saveBtn3, HPos.CENTER);
        GridPane.setHalignment(saveBtn4, HPos.CENTER);

        gp1.addRow(0, secteurLabel, secteurField);
        gp1.add(saveBtn1, 1, 1, 1, 1);

        gp2.addRow(0, secteurLabel1, secteurBox);
        gp2.addRow(1, quartierLabel, quartierField);
        gp2.add(saveBtn2, 1, 2, 1, 1);

        gp3.addRow(0, categorieLabel, categorieField);
        gp3.add(saveBtn3, 1, 1, 1, 1);

        gp4.addRow(0, categorieLabel1, categorieBox);
        gp4.addRow(1, tauxLabel, tauxField);
        gp4.add(saveBtn4, 1, 2, 1, 1);

        prevBtn.setOnAction(e->{
            try {
                MenuChoices s2=new MenuChoices();
                s1.close();
                s2.start(new Stage());
            } catch (Exception ex) {
            }
        });
        saveBtn1.setOnAction(e -> {
            Secteur secteur = ss.find(secteurField.getText());
            if (secteurField.getText().equals("")) {
                alertUtil.showAlert(Alert.AlertType.ERROR, "ERROR", "Merci de saisir un secteur");
            } else if (secteur != null) {
                alertUtil.showAlert(Alert.AlertType.ERROR, "ERROR", "Existe déja");
            } else {
                ss.createSecteur(secteurField.getText());
                alertUtil.showAlert(Alert.AlertType.INFORMATION, "INFO", "Secteur crée avec succés");
            }
        });
        saveBtn2.setOnAction(e -> {
            Quartier q = qs.find(quartierField.getText());
            if (secteurBox.getSelectionModel().getSelectedIndex() == 0) {
                alertUtil.showAlert(Alert.AlertType.ERROR, "ERROR", "Merci de choisir un secteur");
            } else if (quartierField.getText().equals("")) {
                alertUtil.showAlert(Alert.AlertType.ERROR, "ERROR", "Merci de saisir une quartier");
            } else if (q != null) {
                alertUtil.showAlert(Alert.AlertType.ERROR, "ERROR", "Existe déja");
            } else {
                qs.createQuartier(quartierField.getText(), secteurs.get(secteurBox.getSelectionModel().getSelectedIndex()));
                alertUtil.showAlert(Alert.AlertType.INFORMATION, "INFO", "Quartier crée avec succésie");
            }
        });
        saveBtn3.setOnAction(e -> {
            Categorie categorie = cs.find(categorieField.getText());
            if (categorieField.getText().equals("")) {
                alertUtil.showAlert(Alert.AlertType.ERROR, "ERROR", "Merci de saisir une catégorie");
            } else if (categorie != null) {
                alertUtil.showAlert(Alert.AlertType.ERROR, "ERROR", "Existe déja");
            } else {
                cs.createCategorie(categorieField.getText());
                alertUtil.showAlert(Alert.AlertType.INFORMATION, "INFO", "Catégorie crée avec succés");
            }
        });
        saveBtn4.setOnAction(e -> {
            if (categorieBox.getSelectionModel().getSelectedIndex() == 0) {
                alertUtil.showAlert(Alert.AlertType.ERROR, "ERROR", "Merci de saisir une catégorie");
            } else if (tauxField.getText().equals("")) {
                alertUtil.showAlert(Alert.AlertType.ERROR, "ERROR", "Merci de saisir une pourcentage");
            } else {
                ts.createTaux(new Double(tauxField.getText()), categories.get(categorieBox.getSelectionModel().getSelectedIndex()));
                alertUtil.showAlert(Alert.AlertType.INFORMATION, "ERROR", "Taux crée avec succés");
            }
        });

        root.addRow(0, prevBtn);
        root.addRow(1, gp1, gp2);
        root.addRow(2, gp3, gp4);

        primaryStage.setResizable(false);
        primaryStage.setScene(new Scene(root));
        primaryStage.setTitle("Creation d'autres parties");
        primaryStage.show();

        s1=primaryStage;
    }

    //Label
    Label secteurLabel;
    Label quartierLabel;
    Label categorieLabel;
    Label tauxLabel;
    Label secteurLabel1;
    Label categorieLabel1;
    //TextField
    TextField secteurField;
    TextField quartierField;
    TextField categorieField;
    TextField tauxField;
    //Button
    Button prevBtn;
    Button saveBtn1;
    Button saveBtn2;
    Button saveBtn3;
    Button saveBtn4;
    //ComboBoxes
    ComboBox secteurBox;
    ComboBox categorieBox;

    private void initComponents() {
        //Label
        secteurLabel = new Label("Secteur");
        quartierLabel = new Label("Quartier");
        categorieLabel = new Label("Categorie");
        tauxLabel = new Label("Taux");
        secteurLabel1 = new Label("Secteur");
        categorieLabel1 = new Label("Categorie");
        //TextField
        secteurField = new TextField();
        quartierField = new TextField();
        categorieField = new TextField();
        tauxField = new TextField();
        //Button
        prevBtn=new Button("<-");
        saveBtn1 = new Button("Save  Secteur");
        saveBtn2 = new Button("Save Quartier");
        saveBtn3 = new Button("Save Catégorie");
        saveBtn4 = new Button("Save Taux");
        //ComboBoxes
        secteurBox = new ComboBox();
        categorieBox = new ComboBox();
        //Styling ComboBox
        secteurBox.setPromptText("----------SELECT---------");
        categorieBox.setPromptText("----------SELECT---------");
        //styling buttons
        secteurField.setPrefWidth(200);
        quartierField.setPrefWidth(200);
        categorieField.setPrefWidth(200);
        tauxField.setPrefWidth(200);
        secteurBox.setPrefWidth(200);
        categorieBox.setPrefWidth(200);
    }

    private void initjComboBoxSecteur() {
        secteurs = ss.findAll();
        secteurs.forEach((secteur) -> {
            secteurBox.getItems().add(secteur.getNom());
        });
    }

    private void initjComboBoxCategorie() {
        categories = cs.findAll();
        categories.forEach((categorie) -> {
            categorieBox.getItems().add(categorie.getLabel());
        });
    }

    @Override
    public void handle(ActionEvent event) {
    }
}
