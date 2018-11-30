package gamecentre.slidingtiles;

import android.content.Context;
import android.widget.Toast;

import gamecentre.BoardManager;
import gamecentre.MovementController;

/**
 * Process taps of tiles and undo throughout game play.
 */
class SlidingtilesMovementController extends MovementController {
    /**
     * The current board manager.
     */
    private SlidingtilesBoardManager boardManager = null;

    /**
     * Creates a new sliding tiles movement controller.
     */
    SlidingtilesMovementController() {
    }

    /**
     * Set the boardManager for this MovementController to manage.
     *
     * @param boardManager The current BoardManager
     */
    @Override
    public void setBoardManager(BoardManager boardManager) {
        this.boardManager = (SlidingtilesBoardManager) boardManager;
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

            boardManager.updateUndoStack(position);
            boardManager.updateMoves();

            boardManager.touchMove(position);
            if (boardManager.isWin()) {
                Toast.makeText(context, "YOU WIN!", Toast.LENGTH_SHORT).show();
                SlidingtilesScoreboard.setNumMoves(boardManager.getNumMoves());
                SlidingtilesScoreboard.setBoardSize(boardManager.getBoardSize());
                if (onWinListener != null) {
                    onWinListener.onWin();
                }
            }
        } else {
            Toast.makeText(context, "Invalid Tap", Toast.LENGTH_SHORT).show();
        }
    }
}
