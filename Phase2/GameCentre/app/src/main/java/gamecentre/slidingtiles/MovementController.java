package gamecentre.slidingtiles;

import android.content.Context;
import android.widget.Toast;

import gamecentre.OnWinListener;

/**
 * Process taps of tiles and undo throughout game play.
 */
class MovementController {
    /**
     * The current board manager.
     */
    private SlidingtilesBoardManager boardManager = null;
    /**
     * The on win listener, will notify when the game is won.
     */
    private OnWinListener onWinListener = null;

    /**
     * Sets the on win listener.
     *
     * @param onWinListener The on win listener to be set.
     */
    public void setOnWinListener(OnWinListener onWinListener) {
        this.onWinListener = onWinListener;
    }

    /**
     * The MovementController for this activity.
     */
    MovementController() {
    }

    /**
     * Set the boardManager for this MovementController to manage.
     *
     * @param boardManager The current SlidingtilesBoardManager
     */
    void setBoardManager(SlidingtilesBoardManager boardManager) {
        this.boardManager = boardManager;
    }

    /**
     * Process when a tap is made
     *
     * @param context  used to make a Toast
     * @param position position of the tap
     */
    void processTapMovement(Context context, int position) {
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
