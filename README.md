# cc3002-breakout
Project in the course cc3002, Design and Programming Methodologies

Second homework of the cc3002 course consisting in the creation of the logic and rules for a functioning Breakout game in java.

The project implements the following specifications:

## Structure

* The game code inside the logic package describes all game interacting elements so far.
* In logic.gameelements the game's interacting elements are implemented.
* In controller the Game class can be found. This object acts as the game's controller and describes all interactions of it's elements
* In facade the class HomeworkTwoFacade can be found serves as a demonstrator of the game's workings.

* The game code inside the gui package describes all game view classes and methods.
* For the gui, the class PinballGameApplication can be found. This class sets up the game's view and manages [FXGL](https://github.com/AlmasB/FXGL) entities and their interactions.
* PinballEntityFactory, it has the job of storing methods that create entities for the game.

## Features 
The Breakout game implements the following interactions:

* Inside the running app the N key adds levels to the game. If there were no levels initialized it positions brick elements in order to start playing.

* The SPACE key launches a ball towards the bricks, as long as there was not one in play.

* The A and D keys move the player to the left and to the right respectively. If the player hasn't launched a ball, the keys move the ball also.

* Textures are applied when the Wooden and Metal bricks are hit a certain number of times.

* Sparks are emmited when a brick is hit.

* Different sounds are played when bricks are hit. Also, there's a special sound when a brick is destroyed.
# Getting Started

* Clone the project wherever you like in your computer.

## Prerequisites

* A java IDE is recommended for running automated tests for the project. The recommended IDE (the one used to make this) is JetBrain's IntelliJ Idea IDE.


## Installing

* Find and select the Import Project option in your IDE.
* Make sure to configure the import to expect a Maven project structure.
* Find and select your new cc3002-breakout directory as the project's root.
* Name the project as you please.
* If you're using the recommended IntelliJ IDE, allow it to overwrite the existing .idea folder.
* And you're done!

## Running the game

Find and run the Main.main() method by right clicking on it and selecting run, and so the game can be played.

A window with the interactable app should open.

## Working on the GUI

Implementing the GUI in the provided Base code (including all the logic and game controller finished). I'll try to combine my previous code with the one provided later.

For the time being, the GUI uses the provided base code.


# Built With

* JetBrain's IntelliJ Idea - The IDE used
* Maven - Dependency Management
* Overleaf - PDF creation
* [FXGL](https://github.com/AlmasB/FXGL) - Game making framework used for view and it's interactions

# Authors 

* Victor Faraggi - Implementation and testing - [Stepp1](https://github.com/Stepp1)
* Juan-Pablo Silva - Initial project structure template and later a base code used to implement the GUI - [juanpablos](https://github.com/juanpablos)
* Almas Baimagambetov - FXGL Framework - [AlmasB](https://github.com/AlmasB/FXGL)
