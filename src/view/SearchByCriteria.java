package view;

import bean.Categorie;
import bean.Locale;
import bean.Quartier;
import bean.Secteur;
import bean.Taxe;
import helper.LocaleFxHelper;
import helper.TaxeFxHelper;
import helper.TaxeFxHelper2;
import java.util.List;
import javafx.application.Application;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.geometry.HPos;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.Label;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;
import service.CategorieService;
import service.LocaleService;
import service.QuartierService;
import service.SecteurService;
import service.TaxeService;

public final class SearchByCriteria extends Application implements EventHandler<ActionEvent> {

    public static void main(String[] args) {
        Application.launch(args);
    }

    Stage s1;

    public SearchByCriteria() {
        init();
    }

    CategorieService cs = new CategorieService();
    LocaleService ls = new LocaleService();
    SecteurService ss = new SecteurService();
    QuartierService qs = new QuartierService();
    TaxeService ts = new TaxeService();
    private List<Categorie> categories;
    private List<Secteur> secteurs;
    private List<Quartier> quartiers;
    private List<Locale> locales;
    private List<Taxe> taxes;
    LocaleFxHelper localeHelper;
    TaxeFxHelper th;
    TaxeFxHelper2 th2;

    @Override
    public void init() {
        initComponents();
        initjComboBoxCategorie1();
        initjComboBoxCategorie();
        initjComboBoxSecteur();
        initHelper();
    }

    private void initComponents() {
        // Labels
        codeLocaleLabel = new Label("Code locale");
        trimMinLabel = new Label("Trim Min");
        anneeMinLabel = new Label("Annee Min");
        categorieLabel = new Label("Categorie");
        trimMaxLabel = new Label("Trim Max");
        anneeMaxLabel = new Label("Annee Max");
        //Label1
        codeLocaleLabel1 = new Label("Code locale");
        secteurLabel = new Label("Secteur");
        quartierLabel = new Label("Quartier");
        categorieLabel1 = new Label("Categorie");
        codeLocaleField = new TextField();
        trimMinField = new TextField();
        anneeMinField = new TextField();
        categorieBox = new ComboBox();
        trimMaxField = new TextField();
        anneeMaxField = new TextField();
        //Buttons
        search1Btn = new Button("Search");
        //Tables
        table1 = new TableView();
        table2 = new TableView();
        //table3 = new TableView();
        //Fields1
        codeLocaleField1 = new TextField();
        secteurBox = new ComboBox();
        quartierBox = new ComboBox();
        categorieBox1 = new ComboBox();
        //Buttons
        prevBtn = new Button("<-");
        search2Btn = new Button("Search");
        //setPromText
        categorieBox.setPromptText("--------SELECT-------");
        categorieBox1.setPromptText("--------SELECT-------");
        secteurBox.setPromptText("--------SELECT-------");
        quartierBox.setPromptText("--------SELECT-------");
    }

    @Override
    public void start(Stage stage) {
        GridPane root = new GridPane();
        root.setStyle("-fx-padding:5;" + "-fx-border-style: solid inside;" + "-fx-border-width: 2;"
                + "-fx-border-insets: 5;" + "-fx-border-radius: 5;" + "-fx-border-color: blue;");
        GridPane rootPrimary1 = new GridPane();
        rootPrimary1.setStyle("-fx-padding:5;" + "-fx-border-style: solid inside;" + "-fx-border-width: 2;"
                + "-fx-border-insets: 5;" + "-fx-border-radius: 5;" + "-fx-border-color: blue;");
        HBox rootPrimary2 = new HBox();
        rootPrimary2.setStyle("-fx-padding:5;" + "-fx-border-style: solid inside;" + "-fx-border-width: 2;"
                + "-fx-border-insets: 5;" + "-fx-border-radius: 5;" + "-fx-border-color: blue;");

        GridPane root1 = new GridPane();
        root1.setVgap(10);
        root1.setHgap(10);
        GridPane root2 = new GridPane();
        root2.setVgap(10);
        root2.setHgap(10);
        GridPane root3 = new GridPane();
        root3.setVgap(10);
        root3.setHgap(10);
        //HBoxes
        HBox hbox1 = new HBox();
        hbox1.getChildren().addAll(root1, root2);
        hbox1.setSpacing(30);
        hbox1.setStyle("-fx-padding:10;");

        HBox hbox2 = new HBox();
        hbox2.getChildren().addAll(search2Btn);
        hbox2.setSpacing(30);
        hbox2.setAlignment(Pos.CENTER);
        hbox2.setSpacing(10);
        //VBoxes
        VBox vbox1 = new VBox();
        vbox1.getChildren().addAll(search1Btn, table1);
        vbox1.setAlignment(Pos.CENTER);
        vbox1.setSpacing(10);

        // root1
        root1.addRow(0, codeLocaleLabel, codeLocaleField);
        root1.addRow(1, trimMinLabel, trimMinField);
        root1.addRow(2, anneeMinLabel, anneeMinField);
        // root2
        root2.addRow(0, categorieLabel, categorieBox);
        root2.addRow(1, trimMaxLabel, trimMaxField);
        root2.addRow(2, anneeMaxLabel, anneeMaxField);
        //root3
        root3.addRow(0, codeLocaleLabel1, codeLocaleField1);
        root3.addRow(1, secteurLabel, secteurBox);
        root3.addRow(2, quartierLabel, quartierBox);
        root3.addRow(3, categorieLabel1, categorieBox1);
        root3.add(prevBtn, 0, 4);
        root3.add(hbox2, 1, 4, 2, 1);

        VBox vbox2 = new VBox();
        vbox2.getChildren().addAll(table2);
        vbox2.setSpacing(10);
        //table1 desc
        table1.setPrefHeight(100);
        table1.setPrefWidth(250);
        table2.setPrefHeight(150);
        //table3.setMaxHeight(100);
        table2.setPrefWidth(250);
        //table3.setPrefWidth(250);

        search1Btn.setOnAction(e -> {
            Integer trimMin = null;
            Integer trimMax = null;
            Integer anneeMin = null;
            Integer anneMax = null;
            Categorie cat = null;
            int i = categorieBox.getSelectionModel().getSelectedIndex();
            if (i >= 0) {
                cat = categories.get(i);
            }
            if (trimMaxField.getText() != null && !trimMinField.getText().equals("")) {
                trimMin = new Integer(trimMinField.getText());
            }
            if (trimMaxField.getText() != null && !trimMaxField.getText().equals("")) {
                trimMax = new Integer(trimMaxField.getText());
            }
            if (anneeMinField.getText() != null && !anneeMinField.getText().equals("")) {
                anneeMin = new Integer(anneeMinField.getText());
            }
            if (anneeMaxField.getText() != null && !anneeMaxField.getText().equals("")) {
                anneMax = new Integer(anneeMaxField.getText());
            }
            taxes = ts.findByCriteria(codeLocaleField.getText(), cat, anneeMin, anneMax, trimMin, trimMax);
            th.setList(taxes);
        });

        search2Btn.setOnAction(e -> {
            Categorie cat = null;
            Secteur sec = null;
            Quartier quar = null;
            String codeLoc = null;
            int i = categorieBox1.getSelectionModel().getSelectedIndex();
            int j = secteurBox.getSelectionModel().getSelectedIndex();
            int k = quartierBox.getSelectionModel().getSelectedIndex();
            if (codeLocaleField1.getText() != null && !codeLocaleField1.getText().equals("")) {
                codeLoc = codeLocaleField1.getText();
            }
            if (i >= 0) {
                cat = categories.get(i);
            }
            if (j >= 0) {
                sec = secteurs.get(j);
            }
            if (k >= 0) {
                quar = quartiers.get(k);
            }
            locales = ls.findLocaleByCriteria(codeLoc, cat, sec, quar);
            localeHelper.setList(locales);
        });

        prevBtn.setOnAction(e -> {
            s1.close();
            try {
                SaveTaxe s2 = new SaveTaxe();
                s2.start(new Stage());
            } catch (Exception ex) {
            }
        });

        rootPrimary1.addRow(0, hbox1);
        rootPrimary1.addRow(1, vbox1);

        rootPrimary2.getChildren().addAll(root3, vbox2);

        root.addRow(0, rootPrimary1);
        root.addRow(1, rootPrimary2);

        GridPane.setHalignment(rootPrimary1, HPos.CENTER);

        Scene scene = new Scene(root);
        stage.setScene(scene);
        stage.setResizable(false);
        stage.setTitle("Search By criteria");
        stage.show();

        s1 = stage;
    }

    // Labels
    Label codeLocaleLabel;
    Label trimMinLabel;
    Label anneeMinLabel;
    Label categorieLabel;
    Label trimMaxLabel;
    Label anneeMaxLabel;
    //Label1
    Label codeLocaleLabel1;
    Label secteurLabel;
    Label quartierLabel;
    Label categorieLabel1;
    //textfields
    TextField codeLocaleField;
    TextField trimMinField;
    TextField anneeMinField;
    ComboBox categorieBox;
    TextField trimMaxField;
    TextField anneeMaxField;
    //Buttons
    Button search1Btn;
    //Tables
    TableView table1;
    TableView table2;
    //TableView table3;
    //Fields1
    TextField codeLocaleField1;
    ComboBox secteurBox;
    ComboBox quartierBox;
    ComboBox categorieBox1;
    //Buttons
    Button prevBtn;
    Button search2Btn;

    private void initHelper() {
        th = new TaxeFxHelper(table1);
        localeHelper = new LocaleFxHelper(table2);
        //th2 = new TaxeFxHelper2(table3);
    }

    private void initjComboBoxCategorie() {
        categories = cs.findAll();
        categories.forEach((categorie) -> {
            categorieBox.getItems().add(categorie.getLabel());
        });
    }

    private void initjComboBoxCategorie1() {
        categories = cs.findAll();
        categories.forEach((categorie) -> {
            categorieBox1.getItems().add(categorie.getLabel());
        });
    }

    private void initjComboBoxSecteur() {
        secteurs = ss.findAll();
        secteurs.forEach((secteur) -> {
            secteurBox.getItems().add(secteur.getNom());
        });

        secteurBox.valueProperty().addListener((e) -> initComboBoxQuartier());
    }

    private void initComboBoxQuartier() {
        quartierBox.getItems().clear();
        int i = secteurBox.getSelectionModel().getSelectedIndex();
        if (i >= 0) {
            quartiers = qs.findQuartierBySecteur(secteurs.get(i));
            quartiers.forEach((quartier) -> {
                quartierBox.getItems().add(quartier.getNom());
            });
        }
    }

    @Override
    public void handle(ActionEvent event) {
    }

}
