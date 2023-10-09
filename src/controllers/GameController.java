package controllers;

import model.Game;
import enums.GameStatus;
import model.Player;

import java.util.List;

public class GameController {
    public Game createGame(int dimension, List<Player> players) {
        Game game = Game.getBuilder()
                .setDimension(dimension)
                .setPlayers(players)
                .build();
        return game;
    }

    public void undo(Game game) {
    }

    public void executeNextMove(Game game) {
        game.makeNextMove();
    }

    public Player getWinner(Game game) {
        return game.getWinner();
    }

    public GameStatus getGameStatus(Game game) {
        return game.getGameStatus();
    }

    public void displayBoard(Game game) {
        game.displayBoard();
    }
}
