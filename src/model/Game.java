package model;

import enums.CellState;
import enums.GameStatus;
import exceptions.InvalidGameDimensionException;
import exceptions.InvalidNumberOfPlayersException;
import strategies.gamewinningstrategy.GameWinningStrategy;
import strategies.gamewinningstrategy.OrderOneGameWinningStrategy;

import java.util.ArrayList;
import java.util.List;

public class Game {
    //List of action
    //play and display the board
    //at last display the result
    private Board board;
    private List<Player> players;
    private List<Move> moves;
    private GameStatus gameStatus;
    private int nextPlayerIndex;
    private Player winner;

    private GameWinningStrategy gameWinningStrategy;

    public GameWinningStrategy getGameWinningStrategy() {
        return gameWinningStrategy;
    }

    public void setGameWinningStrategy(GameWinningStrategy gameWinningStrategy) {
        this.gameWinningStrategy = gameWinningStrategy;
    }

    public Board getBoard() {
        return board;
    }

    public void setBoard(Board board) {
        this.board = board;
    }

    public List<Player> getPlayers() {
        return players;
    }

    public void setPlayers(List<Player> players) {
        this.players = players;
    }

    public List<Move> getMoves() {
        return moves;
    }

    public void setMoves(List<Move> moves) {
        this.moves = moves;
    }

    public GameStatus getGameStatus() {
        return gameStatus;
    }

    public void setGameStatus(GameStatus gameStatus) {
        this.gameStatus = gameStatus;
    }

    public int getNextPlayerIndex() {
        return nextPlayerIndex;
    }

    public void setNextPlayerIndex(int nextPlayerIndex) {
        this.nextPlayerIndex = nextPlayerIndex;
    }

    public Player getWinner() {
        return winner;
    }

    public void setWinner(Player winner) {
        this.winner = winner;
    }

    private Game() {}

    public static Builder getBuilder() {
        return new Builder();
    }

    public void displayBoard() {
        this.board.displayBoard();
    }

    public void makeNextMove() {
        //Steps:
        //1. Player should be able to decide the Move.
        //2. Check the validation of the move, if move is valid then make the move.

        Player playerToMove = players.get(nextPlayerIndex);

        System.out.println("It is " + playerToMove.getName() + "'s turn");
        Move move = playerToMove.decideMove(board);

        //validate the Move.
        int row = move.getCell().getRow();
        int col = move.getCell().getCol();

        if (board.getBoard().get(row).get(col).getCellState().equals(CellState.EMPTY)) {
            //Move is valid.
            board.applyMove(move);
            moves.add(move);

            //Check the winner. -> Here the game winning strategy is required.
            if (gameWinningStrategy.checkWinner(board, move)) {
                gameStatus = GameStatus.ENDED;
                winner = playerToMove;
            }

            nextPlayerIndex += 1;
            nextPlayerIndex %= players.size();
        } else {
            //throw some exception.
        }
    }

        //We need to use Builder Pattern to build the Game.
    public static class Builder {
        private int dimension;
        private List<Player> players;
        public Builder setDimension(int dimension) {
            this.dimension = dimension;
            return this;
        }
        public Builder setPlayers(List<Player> players) {
            this.players = players;
            return this;
        }

        private boolean isValid() throws InvalidNumberOfPlayersException, InvalidGameDimensionException {
            //Validations.
            if (players.size() != dimension - 1) {
                throw new InvalidNumberOfPlayersException("Number of players should be 1 less than dimension");
            }

            if (dimension < 3) {
                throw new InvalidGameDimensionException("Dimension can't be less than 3");
            }

            //If 2 players have same symbol then invalidate the game.
            return true;
        }

        public Game build() {
            //Before building the game, we should validate the game.
            try {
                isValid();
            } catch (InvalidGameDimensionException e) {
                System.out.println(e.getMessage());
                return null;
            } catch (InvalidNumberOfPlayersException e) {
                System.out.println(e.getMessage());
                return null;
            }

            //Game is valid.
            Game game = new Game();
            game.setGameStatus(GameStatus.IN_PROGRESS);
            game.setPlayers(players);
            game.setBoard(new Board(dimension));
            game.setMoves(new ArrayList<>());
            game.setNextPlayerIndex(0);
            game.setGameWinningStrategy(new OrderOneGameWinningStrategy(dimension));

            return game;
        }
    }

}
