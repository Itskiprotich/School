/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Alerts;

import com.jfoenix.controls.JFXButton;
import com.jfoenix.controls.JFXDialog;
import com.jfoenix.controls.JFXDialogLayout;
import com.jfoenix.controls.events.JFXDialogEvent;
import java.awt.SystemTray;
import java.awt.TrayIcon;
import java.awt.TrayIcon.MessageType;
import java.awt.image.BufferedImage;
import java.io.IOException;
import java.io.PrintWriter;
import java.io.StringWriter;
import java.util.List;
import javafx.scene.Node;
import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.Label;
import javafx.scene.control.TextArea;
import javafx.scene.effect.BoxBlur;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.Priority;
import javafx.scene.layout.StackPane;
import javafx.stage.Stage;
import javax.imageio.ImageIO;

/**
 *
 * @author Keeprawteach
 */
public class AlertDisplay {
    
    public static final String ICON_IMAGE_LOC = "/resources/icon.png";

    public static void showMaterialDialog(StackPane root, Node node, List<JFXButton> arrayList, String new_book_added, String string) {
        BoxBlur blur = new BoxBlur(3, 3, 3);
        if (arrayList.isEmpty()) {
            arrayList.add(new JFXButton("Ok"));
        }
        JFXDialogLayout dialogLayout = new JFXDialogLayout();
        JFXDialog dialog = new JFXDialog(root, dialogLayout, JFXDialog.DialogTransition.TOP);

        arrayList.forEach(controlButton -> {
            controlButton.getStyleClass().add("dialog-button");
            controlButton.addEventHandler(MouseEvent.MOUSE_CLICKED, (MouseEvent mouseEvent) -> {
                dialog.close();
            });
        });

        dialogLayout.setHeading(new Label(new_book_added));
        dialogLayout.setBody(new Label(string));
        dialogLayout.setActions(arrayList);
        dialog.show();
        dialog.setOnDialogClosed((JFXDialogEvent event1) -> {
            node.setEffect(null);
        });
        node.setEffect(blur);
    }

    public static void showSimpleAlert(String success, String settings_updated) {
        Alert alert = new Alert(AlertType.INFORMATION);
        alert.setTitle(success);
        alert.setHeaderText(null);
        alert.setContentText(settings_updated);
        Stage stage = (Stage) alert.getDialogPane().getScene().getWindow();
        alert.showAndWait();
    }

    public static void showErrorMessage(IOException ex, String failed, String cant_save_configuration_file) {
        Alert alert = new Alert(AlertType.ERROR);
        alert.setTitle("Error occured");
        alert.setHeaderText(failed);
        alert.setContentText(cant_save_configuration_file);

        StringWriter sw = new StringWriter();
        PrintWriter pw = new PrintWriter(sw);
        ex.printStackTrace(pw);
        String exceptionText = sw.toString();

        Label label = new Label("The exception stacktrace was:");

        TextArea textArea = new TextArea(exceptionText);
        textArea.setEditable(false);
        textArea.setWrapText(true);

        textArea.setMaxWidth(Double.MAX_VALUE);
        textArea.setMaxHeight(Double.MAX_VALUE);
        GridPane.setVgrow(textArea, Priority.ALWAYS);
        GridPane.setHgrow(textArea, Priority.ALWAYS);

        GridPane expContent = new GridPane();
        expContent.setMaxWidth(Double.MAX_VALUE);
        expContent.add(label, 0, 0);
        expContent.add(textArea, 0, 1);

        alert.getDialogPane().setExpandableContent(expContent);
        alert.showAndWait();
    }
    
    public static void showTrayMessage(String title, String message) {
        try {
            SystemTray tray = SystemTray.getSystemTray();
            BufferedImage image = ImageIO.read(AlertDisplay.class.getResource(ICON_IMAGE_LOC));
            TrayIcon trayIcon = new TrayIcon(image, "Imeja Developers");
            trayIcon.setImageAutoSize(true);
            trayIcon.setToolTip("Library Management System");
            tray.add(trayIcon);
            trayIcon.displayMessage(title, message, MessageType.INFO);
            tray.remove(trayIcon);
        } catch (Exception exp) {
            exp.printStackTrace();
        }
    }

}
