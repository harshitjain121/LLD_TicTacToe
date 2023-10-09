import controllers.GameController;
import enums.BotDifficultyLevel;
import enums.GameStatus;
import enums.PlayerType;
import model.*;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class TicTacToeGame {

    public static void main(String[] args) {
        System.out.println("Game is Starting....");
        Scanner scanner = new Scanner(System.in);

        System.out.println("What is the dimension of the game : ");
        int dimension = scanner.nextInt(); //N

        List<Player> players = new ArrayList<>();
        //Take player information in the input.

        System.out.println("Will there be any bot ? y/n");
        String isBot = scanner.next();
        int noOfHumanPlayers = dimension - 1;
        if (isBot.equals("y")) { // y or n
            noOfHumanPlayers = dimension - 2;

            System.out.println("Enter the name of the Bot: ");
            String name = scanner.next();

            System.out.println("Enter the symbol of the Bot: ");
            String botSymbol = scanner.next();

            players.add(new Bot(botSymbol.charAt(0), name, BotDifficultyLevel.EASY));
        }

        for (int i = 0; i < noOfHumanPlayers; i++) {
            System.out.println("What is the name of the player: " + (i+1));
            String name = scanner.next();

            System.out.println("What is the symbol for player: " + (i+1));
            String symbol = scanner.next(); //Assumption : Single character.

            Player player = new Player(symbol.charAt(0), name, PlayerType.HUMAN);
            players.add(player);
        }

//        Game game = Game.getBuilder()
//                        .setDimension(dimension)
//                        .setPlayers(players)
//                        .build();

        GameController gameController = new GameController();
        Game game = gameController.createGame(dimension, players);

        while (gameController.getGameStatus(game).equals(GameStatus.IN_PROGRESS)) {
            //Players will the play the game.
            System.out.println("This is the current Board: ");
            gameController.displayBoard(game);

            System.out.println("Do you want to undo ? y/n");
            String input = scanner.next();

            if (input.equals("y")) {
                gameController.undo(game);
            } else {
                gameController.executeNextMove(game);
            }
        }

        if (gameController.getGameStatus(game).equals(GameStatus.DRAW)) {
            System.out.println("GAME HAS DRAWN");
        } if (gameController.getGameStatus(game).equals(GameStatus.ENDED)) {
            //Someone has won the game.
            System.out.println("GAME ENDED");
            System.out.println("Winner is " + gameController.getWinner(game).getName());
        }
    }
}