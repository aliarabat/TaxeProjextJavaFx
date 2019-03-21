package view;

import java.util.List;

import bean.Categorie;
import bean.Locale;
import bean.Quartier;
import bean.Redevable;
import bean.Secteur;
import java.util.Optional;
import javafx.application.Application;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.geometry.Pos;
import javafx.scene.Cursor;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.ButtonType;
import javafx.scene.control.ComboBox;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.HBox;
import javafx.stage.Stage;
import service.CategorieService;
import service.LocaleService;
import service.QuartierService;
import service.RedevableService;
import service.SecteurService;
import util.AlertUtil;

public final class CreerLocale extends Application implements EventHandler<ActionEvent> {

    Stage s1;

    public static void main(String[] args) {
        launch(args);
    }

    AlertUtil alertUtil = new AlertUtil();
    LocaleService ls = new LocaleService();
    RedevableService rs = new RedevableService();
    CategorieService cs = new CategorieService();
    SecteurService ss = new SecteurService();
    QuartierService qs = new QuartierService();
    private List<Categorie> categories;
    private List<Secteur> secteurs;
    private List<Quartier> quartiers;

    public CreerLocale() {
        init();
    }

    @Override
    public void init() {
        initComponents();
        initjComboBoxCategorie();
        initjComboBoxSecteur();
    }

    @Override
    public void start(Stage stage) {
        GridPane root = new GridPane();
        root.setHgap(15);
        root.setVgap(20);
        root.setStyle("-fx-padding:100;" + "-fx-border-style: solid inside;" + "-fx-border-width: 2;"
                + "-fx-border-insets: 5;" + "-fx-border-color: blue;");

        HBox box = new HBox();
        box.setSpacing(10);
        box.setAlignment(Pos.CENTER);;
        box.getChildren().addAll(deleteBtn, updateBtn, saveBtn, prevBtn, nextBtn);

        root.addRow(0, codeLocalelabel, codeLocaleField, findBtn);
        root.addRow(1, redevablelabel, redevableField);
        root.addRow(2, dernierTrimlabel, dernierTrimField);
        root.addRow(3, dernierAnneelabel, dernierAnneeField);
        root.addRow(4, categorielabel, categorieComboBox);
        root.addRow(5, secteurlabel, secteurComboBox);
        root.addRow(6, quartierlabel, quartierComboBox);
        root.add(box, 1, 7, 1, 1);

        //Styling button
        deleteBtn.setStyle("-fx-background-color: red;" + "-fx-text-fill: white");
        updateBtn.setStyle("-fx-background-color: dodgerblue;" + "-fx-text-fill: white");
        deleteBtn.setCursor(Cursor.HAND);
        updateBtn.setCursor(Cursor.HAND);
        saveBtn.setCursor(Cursor.HAND);
        findBtn.setCursor(Cursor.HAND);
        nextBtn.setCursor(Cursor.HAND);
        prevBtn.setCursor(Cursor.HAND);

        //impement save button
        findBtn.setOnAction(event -> {
            Locale l = ls.findByCode(codeLocaleField.getText());
            //System.out.println(l.getCode());
            if (l == null || codeLocaleField.getText().equals("")) {
                alertUtil.showAlert(Alert.AlertType.ERROR, "ERROR", "Locale ayant comme code '" + codeLocaleField.getText() + "' n'existe pas");
            } else {
                //codeLocaleField.setText(l.getCode());
                //System.out.println(l.getRedevable().getCin());
                redevableField.setText(l.getRedevable().getCin());
                dernierTrimField.setText(l.getDernierTrime() + "");
                dernierAnneeField.setText(l.getDernierAnne() + "");
                //categorieComboBox.setPromptText(l.getCategorie().getLabel());
                //secteurComboBox.setPromptText(l.getQuartier().getSecteur().getNom());
                //quartierComboBox.setPromptText(l.getQuartier().getNom());
                alertUtil.showAlert(Alert.AlertType.INFORMATION, "INFO", "Locale a été trouvé");
            }
        });

        deleteBtn.setOnAction(e -> {
            Locale l = ls.findByCode(codeLocaleField.getText());
            if (l == null || codeLocaleField.getText().equals("")) {
                alertUtil.showAlert(Alert.AlertType.ERROR, "ERROR", "Locale ayant comme code '" + codeLocaleField.getText() + "' n'existe pas");
            } else {
                Alert dialogC = new Alert(Alert.AlertType.CONFIRMATION, "Confirmation");
                dialogC.setHeaderText("Voulez- vous vraiment supprimer ce locale");
                Optional<ButtonType> reponse = dialogC.showAndWait();
                if (reponse.get() == ButtonType.OK) {
                    ls.remove(l);
                    refreshLocaleTextFields();
                    alertUtil.showAlert(Alert.AlertType.INFORMATION, "INFO", "Locale supprimé");
                } else {

                }
            }
        });

        updateBtn.setOnAction(event -> {
            Locale l = ls.findByCode(codeLocaleField.getText());
            Redevable r = rs.findByCin(redevableField.getText());
            if (l == null || codeLocaleField.getText().equals("")) {
                alertUtil.showAlert(Alert.AlertType.ERROR, "ERROR", "Locale ayant comme code " + codeLocaleField.getText() + "n'existe pas");
            } else if (rs == null || redevableField.getText().equals("")) {
                alertUtil.showAlert(Alert.AlertType.ERROR, "ERROR", "Redevable ayant comme cin " + redevableField.getText() + "n'existe pas");
            } else {
                Categorie cat = categories.get(categorieComboBox.getSelectionModel().getSelectedIndex());
                Quartier quartier = quartiers.get(quartierComboBox.getSelectionModel().getSelectedIndex());
                l.setDernierAnne(new Integer(dernierAnneeField.getText()));
                l.setDernierTrime(new Integer(dernierTrimField.getText()));
                l.setQuartier(quartier);
                l.setCategorie(cat);
                ls.edit(l);
                refreshLocaleTextFields();
                alertUtil.showAlert(Alert.AlertType.INFORMATION, "INFO", "Locale ayant comme cin " + codeLocaleField.getText() + "modifier avec succées");
            }
        });

        saveBtn.setOnAction(e -> {
            Locale locale = ls.find(codeLocaleField.getText());
            Redevable r = rs.find(redevableField.getText());

            if (codeLocaleField.getText().equals("") || redevableField.getText().equals("") || dernierTrimField.getText().equals("")) {
                alertUtil.showAlert(Alert.AlertType.ERROR, "ERROR", "Merci de remplir toutes les champs");
            } else if (locale != null) {
                alertUtil.showAlert(Alert.AlertType.ERROR, "ERROR", "Locale existe déja");
            } else if (r == null) {
                alertUtil.showAlert(Alert.AlertType.ERROR, "ERROR", "Redevable n'éxiste pas");
            } else if (categorieComboBox.getSelectionModel().getSelectedIndex() < 0 || secteurComboBox.getSelectionModel().getSelectedIndex() < 0 || categorieComboBox.getSelectionModel().getSelectedIndex() < 0) {
                alertUtil.showAlert(Alert.AlertType.ERROR, "ERROR", "Merci de marquer tous les choix");
            } else {
                ls.createLocale(codeLocaleField.getText(), new Integer(dernierTrimField.getText()), new Integer(dernierAnneeField.getText()), quartiers.get(secteurComboBox.getSelectionModel().getSelectedIndex() - 1), r, categories.get(categorieComboBox.getSelectionModel().getSelectedIndex()));
                alertUtil.showAlert(Alert.AlertType.INFORMATION, "INFO", "Locale crée avec succés");
            }
        });

        prevBtn.setOnAction(e -> {
            try {
                CreerRedevable s2 = new CreerRedevable();
                s1.close();
                s2.start(new Stage());
            } catch (Exception ex) {
            }
        });

        nextBtn.setOnAction(e -> {
            try {
                SaveTaxe s2 = new SaveTaxe();
                s1.close();
                s2.start(new Stage());
            } catch (Exception ex) {
            }
        });

        Scene scene = new Scene(root);
        stage.setResizable(false);
        stage.setScene(scene);
        stage.setTitle("Creer Locale");
        stage.show();

        s1 = stage;
    }

    private void initjComboBoxCategorie() {
        categories = cs.findAll();
        categories.forEach((categorie) -> {
            categorieComboBox.getItems().add(categorie.getLabel());
        });
    }

    private void initjComboBoxSecteur() {
        secteurs = ss.findAll();
        secteurs.forEach((secteur) -> {
            secteurComboBox.getItems().add((secteur.getNom()));
        });

        secteurComboBox.valueProperty().addListener(e -> initComboBoxQuartier());
    }

    private void initComboBoxQuartier() {
        quartierComboBox.getItems().clear();
        int i = secteurComboBox.getSelectionModel().getSelectedIndex();
        if (i >= 0) {
            quartiers = qs.findQuartierBySecteur(secteurs.get(i));
            quartiers.forEach((quartier) -> {
                quartierComboBox.getItems().add(quartier.getNom());
            });
        }
    }

    //Labels
    Label codeLocalelabel;
    Label redevablelabel;
    Label dernierTrimlabel;
    Label dernierAnneelabel;
    Label categorielabel;
    Label secteurlabel;
    Label quartierlabel;
    // textfields
    TextField codeLocaleField;
    TextField redevableField;
    TextField dernierTrimField;
    TextField dernierAnneeField;
    // buttons
    Button findBtn;
    Button updateBtn;
    Button deleteBtn;
    Button saveBtn;
    Button prevBtn;
    Button nextBtn;
    // Comboboxes
    ComboBox<String> categorieComboBox;
    ComboBox<String> secteurComboBox;
    ComboBox<String> quartierComboBox;

    private void initComponents() {
        //Labels
        codeLocalelabel = new Label("Code Locale:");
        redevablelabel = new Label("Redevable:");
        dernierTrimlabel = new Label("Dernier trim:");
        dernierAnneelabel = new Label("Dernier annee:");
        categorielabel = new Label("Categorie:");
        secteurlabel = new Label("Secteur:");
        quartierlabel = new Label("Quartier:");
        // textfields
        codeLocaleField = new TextField();
        redevableField = new TextField();
        dernierTrimField = new TextField();
        dernierAnneeField = new TextField();
        //buttons
        findBtn = new Button("find");
        deleteBtn = new Button("Delete");
        updateBtn = new Button("Update");
        saveBtn = new Button("Save");
        prevBtn = new Button("<-");
        nextBtn = new Button("->");
        // Comboboxes
        categorieComboBox = new ComboBox<>();
        secteurComboBox = new ComboBox<>();
        quartierComboBox = new ComboBox<>();
        //implement select
        categorieComboBox.setPromptText("---------SELECT-------");
        secteurComboBox.setPromptText("---------SELECT-------");
        quartierComboBox.setPromptText("---------SELECT-------");
        //setSize
        categorieComboBox.setPrefWidth(250);
        secteurComboBox.setPrefWidth(250);
        quartierComboBox.setPrefWidth(250);
    }

    @Override
    public void handle(ActionEvent event) {
    }

    private void refreshLocaleTextFields() {
        codeLocaleField.setText("");
        redevableField.setText("");
        dernierAnneeField.setText("");
        dernierTrimField.setText("");
        init();
    }

}
