# Tic-Tac-Toe
> It's my first project in Java with using JavaFX technology. 

## Table of contents
* [General info](#general-info)
* [Screenshots](#screenshots)
* [Technologies](#technologies)
* [Setup](#setup)
* [Features](#features)
* [Status](#status)
* [Contact](#contact)

## General info
My motivation to create this project was to learn more about Java and teach how to create application with GUI with JavaFX.

## Screenshots
![Example screenshot](./img/screenshot.png)

## Technologies
* Java 8
* JavaFX

## Setup
It's easy to start play in Tic-Tac-Toe. After cloning repository compile project and run it. Now it's a small problem with *JAR file. I'm working on it.

## Code Examples
Here we have code which generates order of players moves:
We have 9 moves and first we must to realize who is starting, after that we can easy take control of another moves with specific conditional statement.

` public static Player whoIsNext(Board board) {
        int numberOfMoves = board.getGameState().getRoundState().getNumberOfMoves();

        if (numberOfMoves == 0) {
            if (whichPlayerIsStarting(board) == board.getGameState().getPlayerOne()) {
                playerFirst = board.getGameState().getPlayerOne();
            } else {
                playerFirst = board.getGameState().getPlayerTwo();
            }
            return playerFirst;
        }
        else if (numberOfMoves % 2 == 0)
        {
            return playerFirst;
        }
        else if (numberOfMoves % 2 == 1)
        {
            if (playerFirst == board.getGameState().getPlayerOne())
                return board.getGameState().getPlayerTwo();
            else
                return board.getGameState().getPlayerOne();
        }
        else if (numberOfMoves >= 10)
        {
            return null;
        }
        else return null;
    }`

## Features
List of features ready and TODOs for future development:
* Add computer AI
* Add save/load
* Make Tic-Tac-Toe for 5 FIGURES TO WIN!

To-do list:
* improve working of move back arrow

## Status
Project is in progress (but you can play with pleasure)

## Contact
Created by [KamilMateuszGreń](Kamilogren@gmail.com)- feel free to contact me!
