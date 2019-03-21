/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package view;

import bean.User;
import javafx.application.Application;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;
import util.Session;

/**
 *
 * @author hp
 */
public final class MenuChoices extends Application implements EventHandler<ActionEvent> {

    Stage s1;

    public static void main(String[] args) {
        Application.launch(args);
    }

    public MenuChoices() throws Exception {
        init();
    }

    @Override
    public void init() throws Exception {
        initComponents();
        userLabel.setText("Bienvenue " + ((User) Session.getAttribut("connectedUser")).getId());
    }

    @Override
    public void start(Stage primaryStage) throws Exception {
        GridPane root = new GridPane();
        root.setVgap(10);
        root.setHgap(10);
        root.setStyle("-fx-padding:100;" + "-fx-border-style: solid inside;" + "-fx-border-width: 2;"
                + "-fx-border-insets: 5;" + "-fx-border-color: blue;");
        root.addRow(0, creerRedevableBtn, creerLocaleBtn, creerAutresBtn, saveTaxeBtn, searchByCretiriaBtn);
        //Lable

        //Vbox
        VBox vbox = new VBox(10);
        vbox.getChildren().addAll(userLabel,root);
        //implementation
        creerRedevableBtn.setOnAction(e -> {
            s1.close();
            try {
                CreerRedevable s2 = new CreerRedevable();
                s2.start(new Stage());
            } catch (Exception ex) {
            }
        });

        creerLocaleBtn.setOnAction(e -> {
            s1.close();
            try {
                CreerLocale s2 = new CreerLocale();
                s2.start(new Stage());
            } catch (Exception ex) {
            }
        });

        creerAutresBtn.setOnAction(e -> {
            s1.close();
            try {
                CreationParties s2 = new CreationParties();
                s2.start(new Stage());
            } catch (Exception ex) {
            }
        });

        saveTaxeBtn.setOnAction(e -> {
            s1.close();
            try {
                SaveTaxe s2 = new SaveTaxe();
                s2.start(new Stage());
            } catch (Exception ex) {
            }
        });

        searchByCretiriaBtn.setOnAction(e -> {
            s1.close();
            try {
                SearchByCriteria s2 = new SearchByCriteria();
                s2.start(new Stage());
            } catch (Exception ex) {
            }
        });

        primaryStage.setScene(new Scene(vbox));
        primaryStage.setTitle("Choix");
        primaryStage.show();

        s1 = primaryStage;
    }
    //Buttons
    Button creerRedevableBtn;
    Button creerLocaleBtn;
    Button creerAutresBtn;
    Button saveTaxeBtn;
    Button searchByCretiriaBtn;
    //Labels
    Label userLabel;

    private void initComponents() {
        //Buttons
        creerRedevableBtn = new Button("Creer redevable");
        creerLocaleBtn = new Button("Creer locale");
        creerAutresBtn = new Button("Creer autres");
        saveTaxeBtn = new Button("Payer taxe");
        searchByCretiriaBtn = new Button("Recherches par critère");
        //Labels
        userLabel = new Label();
    }

    @Override
    public void handle(ActionEvent event) {
    }

}
