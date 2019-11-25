package br.com.cefsa.ec6.measy.infrastructure.util;

import javafx.scene.control.Alert;

public class AlertUtil {

  public static void showGenericServiceError() {
    show(
        "Could not comply with request: \nService may be unavailable",
        "Error",
        Alert.AlertType.ERROR);
  }

  public static void showError(String message) {
    show(message, "Error", Alert.AlertType.ERROR);
  }

  public static void showInfo(String message) {
    show(message, "Info", Alert.AlertType.INFORMATION);
  }

  public static void showGenericServiceErrorAsync() {
    FXThreadHelper.run(() -> showGenericServiceError());
  }

  public static void showErrorAsync(String message) {
    FXThreadHelper.run(() -> showError(message));
  }

  public static void showInfoAsync(String message) {
    FXThreadHelper.run(() -> showInfo(message));
  }

  private static void show(String message, String title, Alert.AlertType alertType) {
    Alert alert = new Alert(alertType);
    alert.setTitle(title);
    alert.setHeaderText(title);
    alert.setContentText(message);
    alert.setResizable(true);
    alert.showAndWait();
  }
}
