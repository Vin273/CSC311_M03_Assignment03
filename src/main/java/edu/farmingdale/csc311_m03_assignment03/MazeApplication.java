package edu.farmingdale.csc311_m03_assignment03;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.stage.Stage;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import java.io.IOException;
import javafx.scene.Group;


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
        Scene scene = new Scene(root, 700, 500);


//        Image maze2 = new Image("maze2.png");
//        ImageView mazeImageView2 = new ImageView(maze2);
//        mazeImageView2.setFitWidth(700);
//        mazeImageView2.setFitHeight(500);
//        Car car1 = new Car();
//        Group group = new Group(mazeImageView2, car1);
//        Scene scene = new Scene(group);

        final int MOVE = 5;

        scene.setOnKeyPressed(event -> {

            double x = imageView2.getX();
            double y = imageView2.getY();

            switch (event.getCode()) {

                case LEFT:
                    imageView2.setX(x - MOVE);
                    break;

                case RIGHT:
                    imageView2.setX(x + MOVE);
                    break;

                case UP:
                    imageView2.setY(y - MOVE);
                    break;

                case DOWN:
                    imageView2.setY(y + MOVE);
                    break;
            }
        });

        stage.setTitle("Maze");
        stage.setScene(scene);
        stage.show();

    }
    public static void main(String[] args) {
        launch(args);
    }
}
