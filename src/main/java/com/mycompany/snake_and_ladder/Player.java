/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mycompany.snake_and_ladder;

/**
 *
 * @author bisht
 */
import javafx.scene.paint.Color;
import javafx.scene.shape.Circle;
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
    public void movePlayer(int discValue){
        if(current_Position + discValue <= 100){
            current_Position += discValue;
        }
        //taking x-coordinates form the board class
        int x_coordinates = game_Board.getXCoordinate(current_Position);
        //taking y-cordinates fromt the board class
        int y_coordinates = game_Board.getYCoordinate(current_Position);
        coin.setTranslateX(x_coordinates);
        coin.setTranslateY(y_coordinates);
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
}
