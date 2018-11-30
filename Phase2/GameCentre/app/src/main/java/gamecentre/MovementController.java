package gamecentre;

import android.content.Context;

public abstract class MovementController {
    /**
     * The on win listener, will notify when the game is won.
     */
    public OnWinListener onWinListener = null;

    /**
     * Sets the on win listener.
     *
     * @param onWinListener The on win listener to be set.
     */
    public void setOnWinListener(OnWinListener onWinListener) {
        this.onWinListener = onWinListener;
    }
    abstract public void processTapMovement(Context context, int position);

    abstract public void setBoardManager(BoardManager boardManager);
}
