package gamecentre.cardmatching;

import android.content.Context;
import android.widget.Toast;

import gamecentre.OnWinListener;

/**
 * Process taps of tiles and undo throughout game play.
 */
class MatchingMovementController {

    /**
     * The current board manager.
     */
    private MatchingBoardManager boardManager = null;

    /**
     * The on win listener, will notify when the game is won.
     */
    private OnWinListener onWinListener = null;

    /**
     * Sets the on win listener.
     * @param onWinListener The on win listener to be set.
     */
    public void setOnWinListener(OnWinListener onWinListener){
        this.onWinListener = onWinListener;
    }

    /**
     * The MovementController for this activity.
     */
    MatchingMovementController() {
    }

    /**
     * Set the boardManager for this MovementController to manage.
     *
     * @param boardManager The current board Manager
     */
    void setBoardManager(MatchingBoardManager boardManager) {
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