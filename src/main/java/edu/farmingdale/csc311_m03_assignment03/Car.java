package edu.farmingdale.csc311_m03_assignment03;

import javafx.scene.Group;
import javafx.scene.paint.Color;
import javafx.scene.shape.Circle;
import javafx.scene.shape.Polygon;
import javafx.scene.shape.Rectangle;

public class Car extends Group {

    public Car() {

        Polygon carBody = new Polygon();
        carBody.setFill(Color.DARKBLUE);
        carBody.getPoints().addAll(0.0, 0.0,
                40.0, 20.0,
                45.0, 90.0,
                0.0, 100.0);
        carBody.setTranslateX(30);
        carBody.setTranslateY(20);

        Polygon carHood = new Polygon();
        carHood.setFill(Color.DARKBLUE);
        carHood.getPoints().addAll(0.0, 0.0,
                30.0, 0.0,
                20.0, 40.0,
                10.0, 45.0,
                0.0, 45.0);
        carHood.setTranslateX(30);
        carHood.setTranslateY(100);

        Rectangle carWindow1 = new Rectangle(47.0, 50, 15, 12.5);
        carWindow1.setFill(Color.LIMEGREEN);

        Rectangle carWindow2 = new Rectangle(47.5, 80, 12.5, 25);
        carWindow2.setFill(Color.LIMEGREEN);

        Circle carTire1 = new Circle(10);
        carTire1.setFill(Color.BLACK);
        carTire1.setTranslateX(30);
        carTire1.setTranslateY(55);

        Circle carTire2 = new Circle(10);
        carTire2.setFill(Color.BLACK);
        carTire2.setTranslateX(30);
        carTire2.setTranslateY(120);

        getChildren().addAll(carBody, carHood, carWindow1, carWindow2, carTire1, carTire2);
    }
}
