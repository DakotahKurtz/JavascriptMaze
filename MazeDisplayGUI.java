package edu.bloomu.homework.projectfive;

import javafx.animation.KeyFrame;
import javafx.animation.Timeline;
import javafx.application.Application;
import javafx.scene.Scene;
import javafx.scene.layout.Pane;
import javafx.scene.paint.Color;
import javafx.scene.shape.Rectangle;
import javafx.stage.Stage;
import javafx.util.Duration;

import java.util.ArrayList;

public class MazeDisplayGUI extends Application
{

    private final Color wallColor = Color.DARKGREY.darker();
    private final Color pathColor = Color.ANTIQUEWHITE;

    GenerationData generationData;
    int count;

    @Override
    public void start(Stage stage)
    {

        int mazeHeight = 40;
        int mazeWidth = 60;
        int rectDimension = 15;

        int width = mazeWidth * rectDimension;
        int height = mazeHeight * rectDimension;

        Pane root = new Pane();

        Rectangle[][] rectangles = new Rectangle[mazeHeight][mazeWidth];

        for (int i = 0; i < mazeHeight; i++) {
            for (int j = 0; j < mazeWidth; j++) {
                rectangles[i][j] = new Rectangle(j * rectDimension, i * rectDimension,
                        rectDimension, rectDimension);
                rectangles[i][j].setFill(pathColor);

                root.getChildren().add(rectangles[i][j]);
            }
        }

        drawBorder(rectangles);

        MazeGenerator generator3 = new MazeGenerator(mazeHeight, mazeWidth);
        generator3.getMaze();

        generationData = generator3.getGenerationData();
        generationData.collate();


        KeyFrame kf = new KeyFrame(Duration.millis(5), event -> {

            if (count < generationData.getSize())
            {
                getNext(rectangles, generationData.get(count));
                count++;

            }
            else {
                int[] exit = generationData.getEnd();
                int[] start = generationData.getStart();

                rectangles[exit[0]][exit[1]].setFill(pathColor);
                rectangles[start[0]][start[1]].setFill(Color.GREEN);

            }




        });



        Timeline animation = new Timeline(kf);
        animation.setCycleCount(generationData.getSize() + 1);

        animation.play();



        Scene scene = new Scene(root, width, height);
        stage.setTitle("");
        stage.setScene(scene);
        stage.setAlwaysOnTop(true);
        stage.show();
    }

    private void drawBorder(Rectangle[][] rectangles) {

        for (int i = 0; i < rectangles.length; i++) {
            rectangles[i][0].setFill(wallColor);
            rectangles[i][rectangles[0].length - 1].setFill(wallColor);
        }

        for (int i = 0; i < rectangles[0].length; i++) {
            rectangles[0][i].setFill(wallColor);
            rectangles[rectangles.length - 1][i].setFill(wallColor);
        }
    }

    private void getNext(Rectangle[][] rectangles, ArrayList<Square> squares)
    {
        for (Square value : squares)
        {
            int[] square = value.getGridLocation();
            rectangles[square[0]][square[1]].setFill(wallColor);
        }
    }

    public static void main(String[] args)
    {
        launch(args);
    }
}
