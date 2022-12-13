package agh.ics.oop.gui;

import agh.ics.oop.*;
import javafx.application.Application;
import javafx.geometry.HPos;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.scene.layout.ColumnConstraints;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.RowConstraints;
import javafx.stage.Stage;

public class App extends Application {

    private final static int MAP_DIMS = 400;
    private AbstractWorldMap map;

    public void init() {
        String[] args = getParameters().getRaw().toArray(new String[0]);
        try {
            MoveDirection[] directions = OptionsParser.parse(args);
            this.map = new GrassField(10);
            Vector2d[] positions = {new Vector2d(2, 2), new Vector2d(3, 4)};
            IEngine engine = new SimulationEngine(directions, map, positions);
            engine.run();
        } catch(IllegalArgumentException exception) {
            throw exception;
        }
    }

    public void start(Stage primaryStage) {

        GridPane grid = new GridPane();
        grid.setGridLinesVisible(true);

        Label t1 = new Label("y/x");
        GridPane.setHalignment(t1, HPos.CENTER);
        grid.add(t1, 0, 0);
        int gridCellDims = 20;
        grid.getColumnConstraints().add(new ColumnConstraints(gridCellDims));
        grid.getRowConstraints().add(new RowConstraints(gridCellDims));

        Vector2d upperRightMapCorner = this.map.calcUpperRight();
        Vector2d lowerLeftMapCorner = this.map.calcLowerLeft();

        for (int i = 0; i < upperRightMapCorner.getY() - lowerLeftMapCorner.getY() + 1; i++) {
            grid.getRowConstraints().add(new RowConstraints(gridCellDims));
            Label t = new Label(String.valueOf(upperRightMapCorner.getY() - i));
            GridPane.setHalignment(t, HPos.CENTER);
            grid.add(t, 0, i + 1);
        }

        for (int i = 0; i < upperRightMapCorner.getX() - lowerLeftMapCorner.getX() + 1; i++) {
            grid.getColumnConstraints().add(new ColumnConstraints(gridCellDims));
            Label t = new Label(String.valueOf(lowerLeftMapCorner.getX() + i));
            GridPane.setHalignment(t, HPos.CENTER);
            grid.add(t, i + 1, 0);
        }

        for (IMapElement element: this.map.getElements().values()) {
            Label t = new Label(element.toString());
            GridPane.setHalignment(t, HPos.CENTER);
            grid.add(t, element.getPosition().getX() - lowerLeftMapCorner.getX() + 1, upperRightMapCorner.getY() - element.getPosition().getY() + 1);
        }

        Scene scene = new Scene(grid, MAP_DIMS, MAP_DIMS);
        primaryStage.setScene(scene);
        primaryStage.show();

    }

}
