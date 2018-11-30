package gamecentre.cardmatching;

import android.content.Context;
import android.widget.Toast;

import gamecentre.BoardManager;
import gamecentre.MovementController;

/**
 * Process taps of tiles throughout game play.
 */
class MatchingMovementController extends MovementController {

    /**
     * The current board manager.
     */
    private MatchingBoardManager boardManager = null;

    /**
     * Creates a new matching game movement controller.
     */
    MatchingMovementController() {
    }

    /**
     * Set the boardManager for this MovementController to manage.
     *
     * @param boardManager The current board Manager
     */
    @Override
    public void setBoardManager(BoardManager boardManager) {
        this.boardManager = (MatchingBoardManager) boardManager;
    }

    /**
     * Process when a tap is made
     *
     * @param context  used to make a Toast
     * @param position position of the tap
     */
    @Override
    public void processTapMovement(Context context, int position) {
        if (boardManager.isValidTap(position)) {
            boardManager.updateMoves();

            boardManager.touchMove(position);
            if (boardManager.isWin()) {
                Toast.makeText(context, "YOU WIN!", Toast.LENGTH_SHORT).show();
                MatchingScoreboard.setNumMoves(boardManager.getNumMoves());
                if (onWinListener != null){
                    onWinListener.onWin();
                }
            }
        } else {
            Toast.makeText(context, "Invalid Tap", Toast.LENGTH_SHORT).show();
        }
    }
}