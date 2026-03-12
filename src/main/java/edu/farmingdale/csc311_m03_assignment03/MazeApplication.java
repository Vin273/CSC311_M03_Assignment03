package edu.farmingdale.csc311_m03_assignment03;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.scene.image.PixelReader;
import javafx.scene.paint.Color;
import javafx.stage.Stage;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import java.io.IOException;
import javafx.scene.Group;
import javafx.scene.control.Tab;
import javafx.scene.control.TabPane;

public class MazeApplication extends Application {
    private Image maze1;
    private Image maze2;
    private Image robot;
    private ImageView imageView1;
    private ImageView mazeImageView2;
    private ImageView imageView2;
    private Car car1;
    private Label statusLabel;
    private double robotWidth = 30;
    private double robotHeight = 30;
    private double carWidth = 10;
    private double carHeight = 10;

    @Override
    public void start(Stage stage) throws IOException {

        maze1 = new Image("maze1.png");
        imageView1 = new ImageView(maze1);
        imageView1.setFitWidth(700);
        imageView1.setFitHeight(500);

        robot = new Image("robot.png");
        imageView2 = new ImageView(robot);
        imageView2.setFitWidth(robotWidth);
        imageView2.setFitHeight(robotHeight);
        imageView2.setX(10);
        imageView2.setY(300);

        Group root = new Group(imageView1, imageView2);

        maze2 = new Image("maze2.png");
        mazeImageView2 = new ImageView(maze2);
        mazeImageView2.setFitWidth(700);
        mazeImageView2.setFitHeight(500);

        car1 = new Car();

        Group group = new Group(mazeImageView2, car1);

        Tab tab1 = new Tab("Maze 1");
        tab1.setContent(root);
        tab1.setClosable(false);

        Tab tab2 = new Tab("Maze 2");
        tab2.setContent(group);
        tab2.setClosable(false);

        TabPane tabPane = new TabPane();
        tabPane.getTabs().addAll(tab1, tab2);

        statusLabel = new Label("Use arrow keys to move.");

        Scene scene = new Scene(tabPane, 700, 500);

        final int MOVE = 5;

        scene.addEventFilter(javafx.scene.input.KeyEvent.KEY_PRESSED, event -> {
            Tab selectedTab = tabPane.getSelectionModel().getSelectedItem();

            if (selectedTab == tab1) {
                double x = imageView2.getX();
                double y = imageView2.getY();
                switch (event.getCode()) {

                    case LEFT:
                        moveRobotIfPossible(x-MOVE,y);
                        event.consume();
                        break;

                    case RIGHT:
                        moveRobotIfPossible(x+MOVE,y);
                        event.consume();
                        break;

                    case UP:
                        moveRobotIfPossible(x,y-MOVE);
                        event.consume();
                        break;

                    case DOWN:
                        moveRobotIfPossible(x,y+MOVE);
                        event.consume();
                        break;
                }
            } else if (selectedTab == tab2) {
                double x = car1.getLayoutX();
                double y = car1.getLayoutY();

                switch (event.getCode()) {
                    case LEFT:
                        moveCarIfPossible(x - MOVE, y, 270);
                        event.consume();
                        break;

                    case RIGHT:
                        moveCarIfPossible(x + MOVE, y, 90);
                        event.consume();
                        break;

                    case UP:
                        moveCarIfPossible(x, y - MOVE, 0);
                        event.consume();
                        break;

                    case DOWN:
                        moveCarIfPossible(x, y + MOVE, 180);
                        event.consume();
                        break;
                }
            }
        });

        stage.setTitle("Maze");
        stage.setScene(scene);
        stage.show();
        root.requestFocus();
    }

    private void moveRobotIfPossible(double newX, double newY) {
        if (canMove(maze1, newX, newY, robotWidth, robotHeight)) {
            imageView2.setX(newX);
            imageView2.setY(newY);
            statusLabel.setText("Robot moving...");
        } else {
            statusLabel.setText("Robot blocked by wall.");
        }
    }

    private void moveCarIfPossible(double newX, double newY, double rotation) {
        if (canMove(maze2, newX, newY, carWidth, carHeight)) {
            car1.setLayoutX(newX);
            car1.setLayoutY(newY);
            car1.setRotate(rotation);
            statusLabel.setText("Car moving...");
        } else {
            statusLabel.setText("Car blocked by wall.");
        }
    }

    private boolean canMove(Image maze, double x, double y, double width, double height) {
        if (x < 0 || y < 0 || x + width > 700 || y + height > 500) {
            return false;
        }

        double[][] points = {
                {x + 2, y + 2},
                {x + width - 2, y + 2},
                {x + 2, y + height - 2},
                {x + width - 2, y + height - 2},
                {x + width / 2, y + height / 2}
        };

        for (double[] p : points) {
            if (!isPath(maze, p[0], p[1])) {
                return false;
            }
        }
        return true;
    }
    private boolean isPath(Image maze, double displayX, double displayY) {
        PixelReader pixelReader = maze.getPixelReader();
        if (pixelReader == null) {
            return false;
        }

        int imgX = (int) ((displayX / 700) * maze.getWidth());
        int imgY = (int) ((displayY / 500) * maze.getHeight());

        imgX = Math.max(0, Math.min(imgX, (int) maze.getWidth() - 1));
        imgY = Math.max(0, Math.min(imgY, (int) maze.getHeight() - 1));

        Color color = pixelReader.getColor(imgX, imgY);

        return color.getRed() > 0.6 &&
                color.getGreen() > 0.6 &&
                color.getBlue() > 0.6;
    }

    public static void main(String[] args) {
        launch(args);
    }
}