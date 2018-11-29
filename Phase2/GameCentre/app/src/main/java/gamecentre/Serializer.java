package gamecentre;

import android.annotation.SuppressLint;
import android.content.Context;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import gamecentre.cardmatching.MatchingBoardManager;
import gamecentre.slidingtiles.BoardManager;


/**
 * Saves and loads (serializes) files.
 */
@SuppressLint("Registered")
public class Serializer extends AppCompatActivity {

    /**
     * Load the board manager from fileName.
     *
     * @param filename the name of the file
     * @param context the context of the activity
     * @return the board manager
     */
    public BoardManager loadBoardManagerFromFile(String filename, Context context) {
        BoardManager tmpBoardManager = new BoardManager();
        try {
            InputStream inputStream = context.openFileInput(filename);
            if (inputStream != null) {
                ObjectInputStream input = new ObjectInputStream(inputStream);
                tmpBoardManager = (BoardManager) input.readObject();
                inputStream.close();
            }
        } catch (FileNotFoundException e) {
            Log.e("login activity", "File not found: " + e.toString());
        } catch (IOException e) {
            Log.e("login activity", "Can not read file: " + e.toString());
        } catch (ClassNotFoundException e) {
            Log.e("login activity", "File contained unexpected data type: " + e.toString());
        }
        return tmpBoardManager;
    }

    /**
     * Save the board manager to fileName.
     *
     * @param filename the name of the file
     * @param context the context of the activity
     * @param boardManager the board manager
     */
    public void saveBoardManagerToFile(String filename, BoardManager boardManager, Context context) {
        try {
            ObjectOutputStream outputStream = new ObjectOutputStream(
                    context.openFileOutput(filename, MODE_PRIVATE));
            outputStream.writeObject(boardManager);
            outputStream.close();
            outputStream.close();
        } catch (IOException e) {
            Log.e("Exception", "File write failed: " + e.toString());
        }
    }

    /**
     * Load a preexisting ScoreBoard from a previous game.
     *
     * @param filename name of file to load
     * @param context the context of the activity
     * @return the scoreboard
     */
    public Scoreboard loadScoreboardFromFile(String filename, Context context) {
        Scoreboard tmpScoreboard = null;
        try {
            InputStream inputStream = context.openFileInput(filename);
            if (inputStream != null) {
                ObjectInputStream input = new ObjectInputStream(inputStream);
                tmpScoreboard = (Scoreboard) input.readObject();
                inputStream.close();
            }
        } catch (FileNotFoundException e) {
            Log.e("login activity", "File not found: " + e.toString());
        } catch (IOException e) {
            Log.e("login activity", "Can not read file: " + e.toString());
        } catch (ClassNotFoundException e) {
            Log.e("login activity", "File contained unexpected data type: " + e.toString());
        }
        return tmpScoreboard;
    }

    /**
     * Save the current scoreBoard to file for later access.
     *
     * @param filename name of the file to save
     * @param context the context of the activity
     * @param scoreboard the scoreboard to save to file
     */
    public void saveScoreboardToFile(String filename, Scoreboard scoreboard, Context context) {
        try {
            ObjectOutputStream outputStream = new ObjectOutputStream(
                    context.openFileOutput(filename, MODE_PRIVATE));
            outputStream.writeObject(scoreboard);
            outputStream.close();
            outputStream.close();
        } catch (IOException e) {
            Log.e("Exception", "File write failed: " + e.toString());
        }
    }

    /**
     * Load the board manager from fileName.
     *
     * @param filename the name of the file
     * @param context the context of the activity
     * @return the matching board manager
     */
    public MatchingBoardManager loadMatchingBoardManagerFromFile(String filename, Context context) {
        MatchingBoardManager tmpBoardManager = new MatchingBoardManager();
        try {
            InputStream inputStream = context.openFileInput(filename);
            if (inputStream != null) {
                ObjectInputStream input = new ObjectInputStream(inputStream);
                tmpBoardManager = (MatchingBoardManager) input.readObject();
                inputStream.close();
            }
        } catch (FileNotFoundException e) {
            Log.e("login activity", "File not found: " + e.toString());
        } catch (IOException e) {
            Log.e("login activity", "Can not read file: " + e.toString());
        } catch (ClassNotFoundException e) {
            Log.e("login activity", "File contained unexpected data type: " + e.toString());
        }
        return tmpBoardManager;
    }

    /**
     * Save the board manager to fileName.
     *
     * @param filename the name of the file
     * @param context the context of the activity
     * @param matchingBoardManager the matching board manager to save to file
     */
    public void saveMatchingBoardManagerToFile(String filename, MatchingBoardManager matchingBoardManager, Context context) {
        try {
            ObjectOutputStream outputStream = new ObjectOutputStream(
                    context.openFileOutput(filename, MODE_PRIVATE));
            outputStream.writeObject(matchingBoardManager);
            outputStream.close();
        } catch (IOException e) {
            Log.e("Exception", "File write failed: " + e.toString());
        }
    }

    /**
     * Load the user manager from USER_FILENAME.
     *
     * @param filename the name of the file to load to
     * @param context the context of the activity
     * @return the user manager
     */
    public static UserManager loadUserManagerFromFile(String filename, Context context) {
        UserManager tmpUserManager = new UserManager();
        try {
            InputStream inputStream = context.openFileInput(filename);
            if (inputStream != null) {
                ObjectInputStream input = new ObjectInputStream(inputStream);
                tmpUserManager = (UserManager) input.readObject();
                inputStream.close();
            }
        } catch (FileNotFoundException e) {
            Log.e("login activity", "File not found: " + e.toString());
        } catch (IOException e) {
            Log.e("login activity", "Can not read file: " + e.toString());
        } catch (ClassNotFoundException e) {
            Log.e("login activity", "File contained unexpected data type: " + e.toString());
        }
        return tmpUserManager;
    }

    /**
     * Save the user manager to filename
     *
     * @param filename the name of hte file
     * @param userManager the userrmanager to save
     * @param context the context of the activity
     */
    public static void saveUserManagerToFile(String filename, UserManager userManager, Context context) {
        try {
            ObjectOutputStream outputStream = new ObjectOutputStream(
                    context.openFileOutput(filename, MODE_PRIVATE));
            outputStream.writeObject(userManager);
            outputStream.close();
        } catch (IOException e) {
            Log.e("Exception", "File write failed: " + e.toString());
        }
    }
}
