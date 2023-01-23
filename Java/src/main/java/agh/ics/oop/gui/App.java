package agh.ics.oop.gui;

import agh.ics.oop.*;
import javafx.application.Application;
import javafx.application.Platform;
import javafx.geometry.HPos;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.*;
import javafx.stage.Stage;

import java.io.FileInputStream;
import java.io.FileNotFoundException;

public class App extends Application implements IPositionChangeObserver {

    private static class GuiElementBox {

        private final VBox container;

        public GuiElementBox(IMapElement element) throws FileNotFoundException {
            ImageView image = new ImageView(new Image(new FileInputStream(element.getImageUrl())));
            image.setFitWidth(20);
            image.setFitHeight(20);
            Label label = new Label(element.getDesc());
            this.container = new VBox(2, image, label);
            this.container.setAlignment(Pos.CENTER);
        }

    }

    private final static int MAP_DIMS = 600;
    private final static int CELL_DIMS = 40;
    private AbstractWorldMap map;
    private GridPane grid;
    private IEngine engine;

    public void init() {
        this.map = new GrassField(10);
        Vector2d[] positions = {new Vector2d(2, 2), new Vector2d(3, 4)};
        this.engine = new SimulationEngine(this.map, positions);
        this.engine.getAnimals().forEach(animal -> animal.addObserver(this));
    }

    public void start(Stage primaryStage) {

        TextField directionsField = new TextField();
        TextField delayField = new TextField();
        Label currentDelay = new Label("Current delay: " + 500);

        Button delayButton = new Button("Set delay (milliseconds)");
        delayButton.setOnAction(actionEvent -> {
            int newDelay = Integer.parseInt(delayField.getText());
            this.engine.setMoveDelay(newDelay);
            currentDelay.setText("Current delay: " + newDelay);
            delayField.setText("");
        });

        Button startButton = new Button("Start");
        startButton.setOnAction(actionEvent -> {
            MoveDirection[] directions = OptionsParser.parse(directionsField.getText().split("\\W+"));
            directionsField.setText("");
            this.engine.setDirections(directions);
            Thread engineThread = new Thread(this.engine);
            engineThread.start();
        });

        this.grid = new GridPane();
        this.grid.setGridLinesVisible(true);
        HBox directionControls = new HBox(8, directionsField, startButton);
        HBox delayControls = new HBox(8, delayField, delayButton, currentDelay);
        VBox ui = new VBox(4, directionControls, delayControls, grid);

        try {
            this.renderGrid();
        } catch (FileNotFoundException ex) {
            ex.printStackTrace();
            throw new RuntimeException(ex);
        }

        Scene scene = new Scene(ui, MAP_DIMS, MAP_DIMS);
        primaryStage.setScene(scene);
        primaryStage.show();

    }

    public void positionChanged(Vector2d oldPosition, Vector2d newPosition) {
        Platform.runLater(() -> {
            this.grid.getChildren().clear();
            this.grid.setGridLinesVisible(false);
            this.grid.setGridLinesVisible(true);

            try {
                this.renderGrid();
            } catch (FileNotFoundException ex) {
                ex.printStackTrace();
                throw new RuntimeException(ex);
            }

        });
    }

    private void renderGrid() throws FileNotFoundException {

        Label coordinates = new Label("y/x");
        GridPane.setHalignment(coordinates, HPos.CENTER);
        this.grid.add(coordinates, 0, 0);
        this.grid.getColumnConstraints().add(new ColumnConstraints(CELL_DIMS));
        this.grid.getRowConstraints().add(new RowConstraints(CELL_DIMS));

        Vector2d upperRightMapCorner = this.map.calcUpperRight();
        Vector2d lowerLeftMapCorner = this.map.calcLowerLeft();

        for (int i = 0; i < upperRightMapCorner.getY() - lowerLeftMapCorner.getY() + 1; i++) {
            this.grid.getRowConstraints().add(new RowConstraints(CELL_DIMS));
            Label t = new Label(String.valueOf(upperRightMapCorner.getY() - i));
            GridPane.setHalignment(t, HPos.CENTER);
            this.grid.add(t, 0, i + 1);
        }

        for (int i = 0; i < upperRightMapCorner.getX() - lowerLeftMapCorner.getX() + 1; i++) {
            this.grid.getColumnConstraints().add(new ColumnConstraints(CELL_DIMS));
            Label t = new Label(String.valueOf(lowerLeftMapCorner.getX() + i));
            GridPane.setHalignment(t, HPos.CENTER);
            this.grid.add(t, i + 1, 0);
        }

        for (IMapElement element: this.map.getElements().values()) {
            VBox box = new GuiElementBox(element).container;
            GridPane.setHalignment(box, HPos.CENTER);
            this.grid.add(box, element.getPosition().getX() - lowerLeftMapCorner.getX() + 1, upperRightMapCorner.getY() - element.getPosition().getY() + 1);
        }

    }

}
