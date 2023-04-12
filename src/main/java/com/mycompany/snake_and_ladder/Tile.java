/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mycompany.snake_and_ladder;

/**
 *
 * @author bisht
 */
import javafx.scene.shape.*;
import javafx.scene.paint.Color;


/**
 *
 * @author bisht
 */
public class Tile extends Rectangle{
    public Tile (int tileSize){
        setWidth(tileSize);
        setHeight(tileSize);
        setFill(Color.AZURE);
        setStroke(Color.BLACK);
    }
}

