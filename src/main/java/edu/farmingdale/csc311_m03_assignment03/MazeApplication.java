package edu.farmingdale.csc311_m03_assignment03;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.stage.Stage;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import java.io.IOException;
import javafx.scene.Group;
import javafx.scene.control.Tab;
import javafx.scene.control.TabPane;

public class MazeApplication extends Application {
    @Override
    public void start(Stage stage) throws IOException {
        FXMLLoader fxmlLoader = new FXMLLoader(MazeApplication.class.getResource("maze-view.fxml"));

        Image maze1 = new Image("maze1.png");
        ImageView imageView1 = new ImageView(maze1);
        imageView1.setFitWidth(700);
        imageView1.setFitHeight(500);

        Image robot = new Image("robot.png");
        ImageView imageView2 = new ImageView(robot);
        imageView2.setX(10);
        imageView2.setY(300);

        Group root = new Group(imageView1, imageView2);

        Image maze2 = new Image("maze2.png");
        ImageView mazeImageView2 = new ImageView(maze2);
        mazeImageView2.setFitWidth(700);
        mazeImageView2.setFitHeight(500);
        Car car1 = new Car();
        Group group = new Group(mazeImageView2, car1);

        Tab tab1 = new Tab("Maze 1");
        tab1.setContent(root);
        tab1.setClosable(false);

        Tab tab2 = new Tab("Maze 2");
        tab2.setContent(group);
        tab2.setClosable(false);

        TabPane tabPane = new TabPane();
        tabPane.getTabs().addAll(tab1, tab2);

        Scene scene = new Scene(tabPane, 700, 500);

        final int MOVE = 5;

        scene.addEventFilter(javafx.scene.input.KeyEvent.KEY_PRESSED, event -> {

            double x = imageView2.getX();
            double y = imageView2.getY();

            switch (event.getCode()) {

                case LEFT:
                    imageView2.setX(x - MOVE);
                    event.consume();
                    break;

                case RIGHT:
                    if (x + MOVE <= 700 - imageView2.getImage().getWidth()) {
                        imageView2.setX(x + MOVE);
                    }
                    event.consume();
                    break;

                case UP:
                    imageView2.setY(y - MOVE);
                    event.consume();
                    break;

                case DOWN:
                    imageView2.setY(y + MOVE);
                    event.consume();
                    break;
            }
        });

        stage.setTitle("Maze");
        stage.setScene(scene);
        stage.show();

        root.requestFocus();
    }

    public static void main(String[] args) {
        launch(args);
    }
}