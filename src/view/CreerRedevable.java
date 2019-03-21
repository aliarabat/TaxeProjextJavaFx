/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package view;

import util.LocalDateStringConverter;
import bean.Redevable;
import java.time.LocalDate;
import javafx.application.Application;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.geometry.Pos;
import javafx.scene.Cursor;
import javafx.scene.Scene;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.Button;
import javafx.scene.control.DatePicker;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.HBox;
import javafx.stage.Stage;
import service.RedevableService;
import util.AlertUtil;

/**
 *
 * @author hp
 */
public final class CreerRedevable extends Application implements EventHandler<ActionEvent> {

    Stage s1;

    public static void main(String[] args) {
        Application.launch(args);
    }

    RedevableService rs = new RedevableService();
    AlertUtil alertUtil = new AlertUtil();
    LocalDateStringConverter localDateStringConverter = new LocalDateStringConverter();

    public CreerRedevable() throws Exception {
        init();
    }

    @Override
    public void init() throws Exception {
        initComponents();
    }

    private void initComponents() {
        cinLabel = new Label("Cin");
        prenomLabel = new Label("Prenom");
        nomLabel = new Label("Nom");
        telLabel = new Label("Tel");
        emailLabel = new Label("Email");
        naissanceLabel = new Label("Naissance");
        //fields
        cinField = new TextField();
        prenomField = new TextField();
        nomField = new TextField();
        telField = new TextField();
        emailField = new TextField();
        naissancePicker = new DatePicker();
        //Buttons
        findBtn = new Button("find");
        deleteBtn = new Button("Delete");
        prevtBtn = new Button("<-");
        nextBtn = new Button("->");
        updateBtn = new Button("Update");
        saveBtn = new Button("Save");

    }

    @Override
    public void start(Stage primaryStage) throws Exception {
        //Gridpane principale
        GridPane root = new GridPane();
        root.setStyle("-fx-padding:100;" + "-fx-border-style: solid inside;" + "-fx-border-width: 2;"
                + "-fx-border-insets: 5;" + "-fx-border-color: blue;");
        root.setHgap(20);
        root.setVgap(20);
        HBox box1 = new HBox();
        box1.setAlignment(Pos.CENTER_RIGHT);
        box1.setSpacing(10);

        //implementation
        box1.getChildren().addAll(deleteBtn, updateBtn, saveBtn, prevtBtn, nextBtn);
        root.addRow(0, cinLabel, cinField);
        root.add(findBtn, 2, 0, 2, 1);
        root.addRow(1, prenomLabel, prenomField);
        root.addRow(2, nomLabel, nomField);
        root.addRow(3, telLabel, telField);
        root.addRow(4, emailLabel, emailField);
        root.addRow(5, naissanceLabel, naissancePicker);
        root.add(box1, 1, 6, 1, 1);

        //Styling button
        deleteBtn.setStyle("-fx-background-color: red;" + "-fx-text-fill: white");
        updateBtn.setStyle("-fx-background-color: dodgerblue;" + "-fx-text-fill: white");
        deleteBtn.setCursor(Cursor.HAND);
        updateBtn.setCursor(Cursor.HAND);
        saveBtn.setCursor(Cursor.HAND);
        findBtn.setCursor(Cursor.HAND);
        nextBtn.setCursor(Cursor.HAND);
        prevtBtn.setCursor(Cursor.HAND);

        //Action on buttons
        findBtn.setPrefWidth(50);
        findBtn.setOnAction(event -> {
            Redevable red = rs.findByCin(cinField.getText());
            if (red == null || cinField.getText().equals("")) {
                alertUtil.showAlert(AlertType.ERROR, "ERROR", "Redevable ayant comme cin '" + cinField.getText() + "' n'existe pas");
            } else {
                cinField.setText(red.getCin());
                prenomField.setText(red.getPrenom());
                nomField.setText(red.getNom());
                telField.setText(red.getNumero() + "");
                emailField.setText(red.getEmail());
                naissancePicker.setValue(localDateStringConverter.convertToLocalDateViaInstant(red.getNaissance()));
                alertUtil.showAlert(AlertType.INFORMATION, "Infos", "Redevable a été trouvé");
            }
        });

        deleteBtn.setOnAction(e -> {
            Redevable r = rs.findByCin(cinField.getText());
            if (r == null) {
                alertUtil.showAlert(AlertType.ERROR, "ERROR", "Redevable ayant comme cin '" + cinField.getText() + "' n'existe pas");
            } else {
                rs.remove(r);
                alertUtil.showAlert(AlertType.INFORMATION, "Infos", "Redevable a été supprimé");
            }
        });

        updateBtn.setOnAction(event -> {
            Redevable redevable = rs.findByCin(cinField.getText());
            if (redevable == null) {
                alertUtil.showAlert(AlertType.ERROR, "ERROR", "Redevable ayant comme cin " + cinField.getText() + "n'existe pas");
            } else {
                redevable.setCin(cinField.getText());
                redevable.setEmail(emailField.getText());
                redevable.setNaissance(localDateStringConverter.toDate(naissancePicker.getValue()));
                redevable.setNumero(new Long(telField.getText()));
                redevable.setNom(nomField.getText());
                redevable.setPrenom(prenomField.getText());
                rs.edit(redevable);
                alertUtil.showAlert(AlertType.INFORMATION, "Infos", "Redevable ayant comme cin " + cinField.getText() + "modifier avec succées");
            }
        });

        saveBtn.setOnAction(e -> {
            Redevable red = rs.find(cinField.getText());
            if (cinField.getText().equals("")) {
                alertUtil.showAlert(AlertType.ERROR, "ERROR", "Merci de saisir le cin");
            } else if (red != null) {
                alertUtil.showAlert(AlertType.ERROR, "ERROR", "Redevable éxiste déja");
            } else if (telField.getText().equals("")) {
                alertUtil.showAlert(AlertType.ERROR, "ERROR", "Merci de saisir le tel");
            } else {
                LocalDate dateNaiss = naissancePicker.getValue();
                LocalDateStringConverter dateConverter = new LocalDateStringConverter();
                rs.createRedevable(cinField.getText(), prenomField.getText(), nomField.getText(), new Long(telField.getText()), emailField.getText(), dateConverter.toDate(dateNaiss));
                alertUtil.showAlert(AlertType.INFORMATION, "ERROR", "Redevable crée avec succés");
            }
        });

        prevtBtn.setOnAction(e -> {
            s1.close();
            try {
                MenuChoices s2 = new MenuChoices();
                s2.start(new Stage());
            } catch (Exception ex) {
            }
        });

        nextBtn.setOnAction(e -> {
            CreerLocale s2 = new CreerLocale();
            s1.close();
            try {
                s2.start(new Stage());
            } catch (Exception ex) {
            }
        });

        Scene scene = new Scene(root);
        primaryStage.setResizable(false);
        primaryStage.setScene(scene);
        primaryStage.setTitle("Creer Redevable");
        primaryStage.show();

        s1 = primaryStage;
    }

    //Labels
    Label cinLabel;
    Label prenomLabel;
    Label nomLabel;
    Label telLabel;
    Label emailLabel;
    Label naissanceLabel;
    //fields
    TextField cinField;
    TextField prenomField;
    TextField nomField;
    TextField telField;
    TextField emailField;
    DatePicker naissancePicker;
    //Buttons
    Button findBtn;
    Button saveBtn;
    Button prevtBtn;
    Button nextBtn;
    Button updateBtn;
    Button deleteBtn;

    @Override
    public void handle(ActionEvent event) {
    }

}
