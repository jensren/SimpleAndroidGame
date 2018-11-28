package gamecentre.slidingtiles;

import android.content.Context;
import android.widget.Toast;

import gamecentre.OnWinListener;

/**
 * Process taps of tiles and undo throughout game play.
 */
class MovementController {

    private BoardManager boardManager = null;

    private OnWinListener onWinListener = null;

    public void setOnWinListener(OnWinListener onWinListener){
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
     * @param boardManager The current BoardManager
     */
    void setBoardManager(BoardManager boardManager) {
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
            if (boardManager.puzzleSolved()) {
                Toast.makeText(context, "YOU WIN!", Toast.LENGTH_SHORT).show();
                SlidingtilesScoreboard.setNumMoves(boardManager.getNumMoves());
                SlidingtilesScoreboard.setBoardSize(boardManager.getBoardSize());
                if (onWinListener != null){
                    onWinListener.onWin();
                }
            }
        } else {
            Toast.makeText(context, "Invalid Tap", Toast.LENGTH_SHORT).show();
        }
    }
}
