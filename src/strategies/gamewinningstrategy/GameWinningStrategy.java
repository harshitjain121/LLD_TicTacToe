package strategies.gamewinningstrategy;

import model.Board;
import model.Move;

public interface GameWinningStrategy {
    boolean checkWinner(Board board, Move move);
}
