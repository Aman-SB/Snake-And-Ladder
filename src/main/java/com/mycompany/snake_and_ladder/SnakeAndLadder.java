package com.mycompany.snake_and_ladder;

import javafx.application.Application;
import javafx.event.ActionEvent;
import javafx.geometry.Pos;
//import javafx.application.Application.launch;
import javafx.scene.Scene;
import javafx.scene.layout.Pane;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.control.Button; 
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.StackPane;
import javafx.scene.paint.Color;
import javafx.scene.text.Text;
import javafx.stage.Stage;



/**
 * JavaFX SnakeAndLadder
 */
public class SnakeAndLadder extends Application {

        /**
     *this is a variable for our board cell
     */
    public static int tileSize = 40 , width = 10 , height = 10;
    
    public static int total_width_board = (tileSize * width) , total_height_board = (height * tileSize);
    
    public static int button_Line = total_height_board + 50, information_Line = button_Line - 30;
    
    public static Dice dice = new Dice();
    
    private Player player_One , player_Two ;
    
    private static boolean game_Started = false , player_One_Turn = false , player_Two_Turn = false;
    
    GridPane login_Page;
    
    StackPane Mainroot ;
    
    private String player_one_name;
    
    private String player_two_name;

    public SnakeAndLadder() {
        Mainroot = new StackPane(); //parent pane
        //setting prefered size of the pane
        Mainroot.setPrefSize(total_width_board  , total_height_board  + 100);
        loginPage();
    }
    
    //method for login page
    private void loginPage(){ 
        Text user_Name_One = new Text("Player 1");
        Text user_Name_Two = new Text("Player 2");
        
        // username text fields
        TextField userName_one_TextField = new TextField(""); 
        userName_one_TextField.setPromptText("Player one name");
        TextField userName_two_TextField = new TextField("");
        userName_two_TextField.setPromptText("Player two name");
        
        //submit button
        Button submit_Button = new Button("Submit");
        
        login_Page = new GridPane();
        
        Mainroot.getChildren().add(login_Page);
        Mainroot.setAlignment(Pos.CENTER);//aligning the root content to center
        //adding basisc utilities to login page
        login_Page.setAlignment(Pos.CENTER);
        login_Page.setHgap(10);
        login_Page.setVgap(10);
        login_Page.add(user_Name_One, 0, 0);
        login_Page.add(userName_one_TextField , 1 ,0);
        login_Page.add(user_Name_Two , 0, 1);
        login_Page.add(userName_two_TextField, 1, 1);
        login_Page.add(submit_Button, 1, 2);
        
        //action on submit button
        submit_Button.setOnAction((t) -> {
            player_one_name = userName_one_TextField.getText();
            player_two_name = userName_two_TextField.getText();
            Mainroot.getChildren().clear();
            Mainroot.getChildren().add(createContent());
        });
    }
    
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
        Button player_One_Button = new Button(player_one_name);
        Button player_Two_Button = new Button(player_two_name);
        Button start_Button = new Button("Start Button");
        
        player_One_Button.setTranslateY(button_Line);
        player_One_Button.setTranslateX(40);
        player_One_Button.setDisable(true);
        player_Two_Button.setTranslateY(button_Line);
        player_Two_Button.setTranslateX(295);
        player_Two_Button.setDisable(true);
        start_Button.setTranslateY(button_Line);
        start_Button.setTranslateX(155);
        
        // Displaying the Information
        
        Label player_One_Label = new Label("");
        Label player_Two_Label = new Label("");
        Label dice_Label = new Label("Start The Game");
        
        player_One_Label.setTranslateY(information_Line);
        player_One_Label.setTranslateX(40);
        player_Two_Label.setTranslateY(information_Line);
        player_Two_Label.setTranslateX(290);
        dice_Label.setTranslateY(information_Line);
        dice_Label.setTranslateX(158);
        
        //initializing player
        player_One = new Player(tileSize ,Color.BLACK ,player_one_name);
        
        player_Two = new Player(tileSize - 5, Color.BLUE , player_two_name);
        
        //start button function 
        start_Button.setOnAction((ActionEvent actionEvent) -> {
            game_Started = true;
            dice_Label.setText("Game Started");
            start_Button.setDisable(true);
            
            player_One_Turn = true;
            player_One_Label.setText("Your Turn " );
            player_One_Button.setDisable(false);
            player_One.bringTostartingPosition();
            
            player_Two_Turn = false;
            player_Two_Label.setText("");
            player_Two_Button.setDisable(true);
            player_Two.bringTostartingPosition();
        });
        
        //player one button working
        player_One_Button.setOnAction((ActionEvent actionEvent) -> {
            if(game_Started){
                if(player_One_Turn){
                    int dice_Value = dice.getRolledDiceValue();
                    dice_Label.setText("Dice Value : " + dice_Value);
                    player_One.movePlayer(dice_Value);
                    //Winning Condition
                    if(player_One.isWinner()){
                        player_One_Turn = false;
                        player_One_Button.setDisable(true);
                        player_One_Label.setText("");
                        
                        player_Two_Turn = true;
                        player_Two_Button.setDisable(true);
                        player_Two_Label.setText("");
                        
                        start_Button.setDisable(false);
                        start_Button.setText("    Restart    ");
                        dice_Label.setText("Winner " + player_One.getName());
                        game_Started = false;
                    }
                    else{
                        player_One_Turn = false;
                        player_One_Button.setDisable(true);
                        player_One_Label.setText("");
                        
                        player_Two_Turn = true;
                        player_Two_Button.setDisable(false);
                        player_Two_Label.setText("Your Turn" );
                    }
                }
            }
        });
        
        //player two button working
        player_Two_Button.setOnAction((ActionEvent actionEvent) -> {
            if(game_Started){
                if(player_Two_Turn){
                    int dice_Value = dice.getRolledDiceValue();
                    dice_Label.setText("Dice Value : " + dice_Value);
                    player_Two.movePlayer(dice_Value);
                    //Winning Condition
                    if(player_Two.isWinner()){
                        player_Two_Turn = false;
                        player_Two_Button.setDisable(true);
                        player_Two_Label.setText("");
                        
                        player_One_Turn = true;
                        player_One_Button.setDisable(true);
                        player_One_Label.setText("");
                        
                        start_Button.setDisable(false);
                        start_Button.setText("    Restart    ");
                        dice_Label.setText("Winner " + player_Two.getName());
                        game_Started = false;
                    }
                    else
                    {
                        player_Two_Turn = false;
                        player_Two_Button.setDisable(true);
                        player_Two_Label.setText("");
                        
                        player_One_Turn = true;
                        player_One_Button.setDisable(false);
                        player_One_Label.setText("Your Turn ");
                    }
                }
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
        
        Scene scene = new Scene(Mainroot);
        
        stage.setScene(scene); 
        stage.show();
    }

    public static void main(String[] args) {
        launch();
    }

}