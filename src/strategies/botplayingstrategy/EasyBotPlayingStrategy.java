package strategies.botplayingstrategy;

import enums.CellState;
import model.*;

public class EasyBotPlayingStrategy implements BotPlayingStrategy {
    //BOT will make a move for EMPTY CELL
    @Override
    public Move decideMove(Player player, Board board) {
        for (int i = 0; i < board.getBoard().size(); i++) {
            for (int j = 0; j < board.getBoard().size(); j++) {
                if (board.getBoard().get(i).get(j).getCellState().equals(CellState.EMPTY)) {
                    //Bot will make a move here.
                    return new Move(player, new Cell(i, j));
                }
            }
        }
        return null;
    }
}