/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mycompany.snake_and_ladder;

/**
 *
 * @author bisht
 */
import java.util.ArrayList;

public class Board {
    public class Pair{
        int x_cordinate;
        int y_cordinate;
        
        Pair(int x_cord ,int y_cord){
            this.x_cordinate = x_cord;
            this.y_cordinate = y_cord;
        }
    }
    
    ArrayList<Pair> position_cordinates;
    
    public Board(){
        position_cordinates = new ArrayList<>();
        populatePositionCordinates();
    }
    
    private void populatePositionCordinates(){
        position_cordinates.add(new Pair(0,0)); //dummy value which is of no use
        for (int row = 0; row < SnakeAndLadder.height ; row++) {
            for (int col = 0; col < SnakeAndLadder.width ; col++) {
                //X cordinates are changing when we are getting even row it changing the direction of traversing
                int curr_x_cord ;
                if(row % 2 == 0)
                {
                    curr_x_cord =  (col * SnakeAndLadder.tileSize) + SnakeAndLadder.tileSize/2;
                }
                else
                {
                    curr_x_cord = SnakeAndLadder.total_width_board - (col * SnakeAndLadder.tileSize) - SnakeAndLadder.tileSize/2;
                }
                
                // Y cordinates will not changing 
                int curr_y_cord = SnakeAndLadder.total_height_board - (row * SnakeAndLadder.tileSize) - SnakeAndLadder.tileSize/2;
                
                position_cordinates.add(new Pair(curr_x_cord,curr_y_cord));
            }
        }
        
    }
    
    int getXCoordinate(int position){
        if(position >= 1 && position <= 100){
            return position_cordinates.get(position).x_cordinate;
        }
        return -1;
    }
    
    int getYCoordinate(int position){
        if(position >= 1 && position <= 100){
            return position_cordinates.get(position).y_cordinate;
        }
        return -1;
    }
    
//    public static void main(String[] args) {
//        Board board = new Board();
//        for(int curr_Position = 1 ; curr_Position < board.position_cordinates.size() ; curr_Position++)
//        {
//            System.out.println(curr_Position + " x_cord : " + board.position_cordinates.get(curr_Position).x_cordinate +
//                    " y_cord : " + board.position_cordinates.get(curr_Position).y_cordinate );
//        }
//    }
}
