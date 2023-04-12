package com.mycompany.snake_and_ladder;

import javafx.application.Application;
import javafx.application.Application.launch;
import javafx.scene.Scene;
import javafx.scene.layout.Pane;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.stage.Stage;



/**
 * JavaFX SnakeAndLadder
 */
public class SnakeAndLadder extends Application {

        /**
     *this is a variable for our board cell
     */
    public static final int tileSize = 40 , width = 10 , height = 10;
    public static final int total_width_board = 10 * tileSize, total_height_board = 10 * tileSize;
    
    
    private Pane createContent(){
        /* 
        *This is for creation of board 
         */
        Pane root = new Pane();
        root.setPrefSize(total_width_board  , total_height_board  + 50);
        
        /* basic use of dsa to replicate multiple cell in a board */
        for( int board_one_cell_height=0 ; board_one_cell_height < total_height_board ; board_one_cell_height+=tileSize )
        {
            for( int board_one_Cell_width=0 ; board_one_Cell_width < total_width_board ; board_one_Cell_width+=tileSize )
            {
                Tile tile = new Tile(tileSize);
                tile.setTranslateX(board_one_Cell_width);
                tile.setTranslateY(board_one_cell_height);
                root.getChildren().add(tile);
            }
        }
        
        Image img = new Image("file:///E:/Java/learning_application/Snake_And_Ladder/src/main/image/snakes-ladders-board-game-start-600w-163384724.png");
        ImageView board = new ImageView();
        board.setImage(img);
        board.setFitHeight(total_height_board);
        board.setFitWidth(total_width_board);
        
        root.getChildren().add(board);
        
        return root;
    }
    
    @Override
    public void start(Stage stage) {
          
        stage.setTitle("Snake & Ladder");
        
        Scene scene = new Scene(createContent());
        
        stage.setScene(scene); 
        stage.show();
    }

    public static void main(String[] args) {
        launch();
    }

}