/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mycompany.snake_and_ladder;

/**
 *
 * @author bisht
 */
import javafx.animation.PauseTransition;
import javafx.scene.paint.Color;
import javafx.scene.shape.Circle;
import javafx.animation.TranslateTransition;
import javafx.animation.SequentialTransition;
import javafx.util.Duration;

//player class
public class Player {
    private Circle coin;
    
    private int current_Position;
    
    private String name;
    
    //to access Board class 
    static Board game_Board = new Board();
    
    //player constructor
    public Player(int tileSize, Color coinColor , String PlayerName){
        coin = new Circle(tileSize/2);
        coin.setFill(coinColor);
        current_Position = 0;
        movePlayer(1);
        name = PlayerName;
    }
    
    //movable function it is moving the coin from one position to another position
    public void movePlayer(int diceValue){
        if(current_Position + diceValue <= 100){
            current_Position += diceValue;
            TranslateTransition second_Movement = null , first_Movement = translateAnimation(diceValue);
            
            int new_Position = game_Board.getNewPosition(current_Position);
            if(new_Position != current_Position && new_Position != -1)
            {
                current_Position = new_Position;
                second_Movement = translateAnimation(6);
            }
            
            if(second_Movement == null)
            {
                first_Movement.play();
            }
            else
            {
                SequentialTransition sequence_Transition = new SequentialTransition(first_Movement , 
                new PauseTransition(Duration.millis(500)) , second_Movement);
                sequence_Transition.play();
            }
        }
//        //taking x-coordinates form the board class
//        int x_coordinates = game_Board.getXCoordinate(current_Position);
//        //taking y-cordinates fromt the board class
//        int y_coordinates = game_Board.getYCoordinate(current_Position);
//        coin.setTranslateX(x_coordinates);
//        coin.setTranslateY(y_coordinates);

    }
    
    public TranslateTransition translateAnimation(int diceValue){
        TranslateTransition animation = new TranslateTransition(Duration.millis(diceValue*200),coin);
        animation.setToX(game_Board.getXCoordinate(current_Position));
        animation.setToY(game_Board.getYCoordinate(current_Position));
        animation.setAutoReverse(false);
        return animation;
    }
    
    //getter function
    public Circle getCoin(){
        return coin;
    }
    
    public int getCurrent_Position(){
        return current_Position;
    }
    
    public String getName(){
        return name;
    }
    
    public void bringTostartingPosition(){
        current_Position = 0;
        movePlayer(1);
    }
    
    //Winning Condition
    public boolean isWinner(){
        if(current_Position == 100)
            return true;
        
        return false;
    }
}
