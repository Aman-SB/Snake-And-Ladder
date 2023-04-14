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
    
    ArrayList<Integer> snake_And_Ladder_Position ;    
    
    public Board(){
        position_cordinates = new ArrayList<>();
        populatePositionCordinates();
        populateSnakeAndLadder();
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
    
    private void populateSnakeAndLadder(){
        snake_And_Ladder_Position = new ArrayList<Integer>();
        for (int i = 0; i <= 100; i++) {
            snake_And_Ladder_Position.add(i);
        }
        
        //Ladder positioning
        snake_And_Ladder_Position.set(4,25);
        snake_And_Ladder_Position.set(21,39);
        snake_And_Ladder_Position.set(29,74);
        snake_And_Ladder_Position.set(43,76);
        snake_And_Ladder_Position.set(63,80);
        snake_And_Ladder_Position.set(71,89);
        
        //Snake Positioning
        snake_And_Ladder_Position.set(30,7);
        snake_And_Ladder_Position.set(47,15);
        snake_And_Ladder_Position.set(56,19);
        snake_And_Ladder_Position.set(82,42);
        snake_And_Ladder_Position.set(92,75);
        snake_And_Ladder_Position.set(98,55);
    }
    
    public int getNewPosition(int current_Position){
        if(current_Position >= 0 && current_Position <= 100)
        {
            return snake_And_Ladder_Position.get(current_Position);
        }
        return -1;
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
