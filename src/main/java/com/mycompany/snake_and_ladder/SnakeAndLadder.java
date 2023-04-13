package com.mycompany.snake_and_ladder;

import javafx.application.Application;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
//import javafx.application.Application.launch;
import javafx.scene.Scene;
import javafx.scene.layout.Pane;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.control.Button; 
import javafx.scene.control.Label;
import javafx.scene.paint.Color;
import javafx.stage.Stage;



/**
 * JavaFX SnakeAndLadder
 */
public class SnakeAndLadder extends Application {

        /**
     *this is a variable for our board cell
     */
    public static final int tileSize = 40 , width = 10 , height = 10;
    
    public static final int total_width_board = (width * tileSize) , total_height_board = (height * tileSize);
    
    public static final int button_Line = total_height_board + 50, information_Line = button_Line - 30;
    
    public static Dice dice = new Dice();
    
    private Player player_One , player_Two ;
    
    private static boolean game_Started = false , player_One_Turn = false , player_Two_Turn = false; 
    
    private Pane createContent(){
        /* 
        *This is for creation of board 
         */
        Pane root = new Pane();
        root.setPrefSize(total_width_board  , total_height_board  + 100);
        
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
        
        Image img = new Image("file:///E:/Java/learning_application/Snake_And_Ladder/src/main/image/last-Snake-ladder-image.png");
        ImageView board = new ImageView();
        board.setImage(img);
        board.setFitHeight(total_height_board);
        board.setFitWidth(total_width_board);
        
        //button 
        Button player_One_Button = new Button("Player one");
        Button player_Two_Button = new Button("Player Two");
        Button start_Button = new Button("Start Button");
        
        player_One_Button.setTranslateY(button_Line);
        player_One_Button.setTranslateX(40);
        player_Two_Button.setTranslateY(button_Line);
        player_Two_Button.setTranslateX(295);
        start_Button.setTranslateY(button_Line);
        start_Button.setTranslateX(165);
        
        // Displaying the Information
        
        Label player_One_Label = new Label("Player 1 Turn");
        Label player_Two_Label = new Label("Player 2 Turn");
        Label dice_Label = new Label("Start The Game");
        
        player_One_Label.setTranslateY(information_Line);
        player_One_Label.setTranslateX(41);
        player_Two_Label.setTranslateY(information_Line);
        player_Two_Label.setTranslateX(295);
        dice_Label.setTranslateY(information_Line);
        dice_Label.setTranslateX(166);
        
        //initializing player
        player_One = new Player(tileSize ,Color.BLACK ," Aman");
        
        player_Two = new Player(tileSize - 5, Color.BLUE , " Ankit");
        
        player_One_Button.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent actionEvent){
                if(game_Started){
                    if(player_One_Turn){
                        int dice_Value = dice.getRolledDiceValue();
                        dice_Label.setText("Dice Value : " + dice_Value);
                        //Winning Condition
                        
                        player_One.movePlayer(dice_Value);
                        player_One_Turn = false;
                        player_One_Button.setDisable(true);
                        player_One_Label.setText("");
                        
                        player_Two_Turn = true;
                        player_Two_Button.setDisable(false);
                        player_Two_Label.setText("Your Turn" + player_Two.getName());
                    }
                }
            }
        });
        
        //player two button working
        player_Two_Button.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent actionEvent){
                if(game_Started){
                    if(player_Two_Turn){
                        int dice_Value = dice.getRolledDiceValue();
                        dice_Label.setText("Dice Value : " + dice_Value);
                        //Winning Condition
                        
                        player_Two.movePlayer(dice_Value);
                        player_Two_Turn = false;
                        player_Two_Button.setDisable(true);
                        player_Two_Label.setText("");
                        
                        player_One_Turn = true;
                        player_One_Button.setDisable(false);
                        player_One_Label.setText("Your Turn " + player_One.getName());
                    }
                }
            }
        });
        
        //start button
        
        start_Button.setOnAction(new EventHandler<ActionEvent>(){
            @Override
            public void handle(ActionEvent actionEvent){
                game_Started = true;
                dice_Label.setText("Game Started");
                start_Button.setDisable(true);
                player_One_Turn = true;
                player_One_Label.setText("Your Turn " + player_One.getName());
                player_Two_Turn = false; 
                player_Two_Label.setText("");
                player_Two_Button.setDisable(true);
            }
        });
        
        root.getChildren().addAll(board , 
                player_One_Button , player_Two_Button , start_Button ,
                player_One_Label , player_Two_Label , dice_Label ,
                player_One.getCoin() , player_Two.getCoin());
        
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