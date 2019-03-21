/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package util;

import javafx.scene.control.Alert;

/**
 *
 * @author hp
 */
public class AlertUtil {

    public Alert showAlert(Alert.AlertType at, String title, String bodyMessage) {
        Alert alert = new Alert(at);
        alert.setTitle(title);
        alert.setHeaderText(bodyMessage);
        alert.show();
        return alert;
    }
}
