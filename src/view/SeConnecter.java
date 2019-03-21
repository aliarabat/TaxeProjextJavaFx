package view;

import bean.User;
import javafx.application.Application;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.geometry.HPos;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.ButtonType;
import javafx.scene.control.Label;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.scene.layout.GridPane;
import javafx.stage.Stage;
import service.UserService;
import util.AlertUtil;
import util.Session;

public final class SeConnecter extends Application implements EventHandler<ActionEvent> {

    Stage s1;

    public static void main(String[] args) {
        Application.launch(args);
    }
    UserService userService = new UserService();

    @Override
    public void init() {
        initComponents();
    }

    @Override
    public void start(Stage stage) {

        GridPane root = new GridPane();
        root.setVgap(10);
        root.setHgap(10);
        root.setStyle("-fx-padding:100;" + "-fx-border-style: solid inside;" + "-fx-border-width: 2;"
                + "-fx-border-insets: 5;" + "-fx-border-radius: 5;" + "-fx-border-color: blue;");
        // Labels
        Label login = new Label("_Utilisateur");
        Label motDePasse = new Label("_Mot De Passe");
        // TextFields

        // labeles for
        login.setLabelFor(loginField);
        login.setMnemonicParsing(true);
        motDePasse.setLabelFor(motDePasseField);
        motDePasse.setMnemonicParsing(true);
        // butoon
        Button btnConnecter = new Button("Se connecter");

        btnConnecter.setOnAction(e -> {
            User user = getParams();
            AlertUtil alertUtil = new AlertUtil();
            int res = userService.seConnecter(user);
            if (res > 0) {
                Session.updateAttribute(user, "connectedUser");
                s1.close();
                Alert a = alertUtil.showAlert(Alert.AlertType.INFORMATION, "INFO", "Connection avec succés, bienvenue");
                a.setOnCloseRequest(event -> {
                    ButtonType result = a.getResult();
                    if (result == ButtonType.OK) {
                        try {
                            MenuChoices s2 = new MenuChoices();
                            s2.start(new Stage());
                        } catch (Exception ex) {
                        }
                    }
                });
            } else {
                alertUtil.showAlert(Alert.AlertType.ERROR, "ERROR", "Connection échoué");
            }
        });

        root.addRow(0, login, loginField);
        root.addRow(1, motDePasse, motDePasseField);
        root.add(btnConnecter, 1, 2, 2, 1);
        GridPane.setHalignment(btnConnecter, HPos.CENTER);

        Scene scene = new Scene(root);
        stage.setResizable(false);
        stage.setScene(scene);
        stage.setTitle("Se connecter");
        stage.show();

        s1 = stage;
    }

    TextField loginField;
    PasswordField motDePasseField;

    private User getParams() {
        User user = new User();
        user.setId(loginField.getText());
        user.setPassword(motDePasseField.getText());
        return user;
    }

    private void initComponents() {
        loginField = new TextField();
        motDePasseField = new PasswordField();
    }

    @Override
    public void handle(ActionEvent event) {
    }
}
