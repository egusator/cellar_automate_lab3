package com.example.cellar_automate_lab3;

import javafx.animation.KeyFrame;
import javafx.animation.Timeline;
import javafx.fxml.FXML;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.layout.ColumnConstraints;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.RowConstraints;
import javafx.scene.paint.Color;
import javafx.scene.shape.Rectangle;
import javafx.util.Duration;

public class HelloController {
    @FXML
    private Label welcomeText;

    private static final int COLUMNS_NUMBER = 50;

    @FXML
    private TextField ruleTextField;

    @FXML
    private GridPane mainGrid;

    private Row row;

    private Timeline timeline;

    @FXML
    protected void onStartStopButtonClick() {
        if (timeline != null) {
            timeline.stop();
        }

        row = new Row(Integer.parseInt(ruleTextField.getText()), COLUMNS_NUMBER);

        mainGrid.getChildren().clear();

        timeline = new Timeline(new KeyFrame(
                Duration.millis(200),
                ae -> {
                    displayNextRow();
                    row.evolve();
                }));
        timeline.setCycleCount(10000);
        timeline.play();
    }

    private void displayNextRow() {
        for (int col = 0; col < COLUMNS_NUMBER; col++) {
            Rectangle rectangle = new Rectangle(10, 10); // Width and height of each rectangle
            if (row.getValues().get(col)) {
                rectangle.setFill(Color.GREEN);
            } else {
                rectangle.setFill(Color.RED);
            }
            mainGrid.add(rectangle, col, row.getRowNumber());
        }
    }
}