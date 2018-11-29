package gamecentre;

import android.content.Context;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import gamecentre.cardmatching.MatchingBoardManager;
import gamecentre.slidingtiles.BoardManager;


/**
 * Saves and loads (serializes) files.
 */
public class Serializer extends AppCompatActivity {

    /**
     * Load the board manager from fileName.
     *
     * @param fileName the name of the file
     */
    public BoardManager loadBoardManagerFromFile(String fileName) {
        try {
            InputStream inputStream = new FileInputStream(fileName);
            if (inputStream != null) {
                ObjectInputStream input = new ObjectInputStream(inputStream);
                BoardManager tmpBoardManager = (BoardManager) input.readObject();
                inputStream.close();

            }
        } catch (FileNotFoundException e) {
            Log.e("login activity", "File not found: " + e.toString());
        } catch (IOException e) {
            Log.e("login activity", "Can not read file: " + e.toString());
        } catch (ClassNotFoundException e) {
            Log.e("login activity", "File contained unexpected data type: " + e.toString());
        }
        return null;
    }

    /**
     * Save the board manager to fileName.
     *
     * @param filename the name of the file
     */
    public void saveBoardManagerToFile(String filename, BoardManager boardManager) {
        try {
            FileOutputStream fileOutputStream = new FileOutputStream(filename);
            ObjectOutputStream outputStream = new ObjectOutputStream(fileOutputStream);
            outputStream.writeObject(boardManager);
            fileOutputStream.close();
            outputStream.close();
        } catch (IOException e) {
            Log.e("Exception", "File write failed: " + e.toString());
        }
    }

    /**
     * Load a preexisting ScoreBoard from a previous game.
     *
     * @param fileName name of file to load
     */
    public Scoreboard loadScoreboardFromFile(String fileName) {
        try {
            InputStream inputStream = new FileInputStream(fileName);
            ObjectInputStream input = new ObjectInputStream(inputStream);
            Scoreboard tmpScoreboard = (Scoreboard) input.readObject();
            inputStream.close();
            return tmpScoreboard;
        } catch (FileNotFoundException e) {
            Log.e("login activity", "File not found: " + e.toString());
        } catch (IOException e) {
            Log.e("login activity", "Can not read file: " + e.toString());
        } catch (ClassNotFoundException e) {
            Log.e("login activity", "File contained unexpected data type: " + e.toString());
        }
        return null;
    }

    /**
     * Save the current scoreBoard to file for later access.
     *
     * @param filename name of the file to save
     */
    public void saveScoreboardToFile(String filename, Scoreboard scoreboard) {
        try {
            FileOutputStream fileOutputStream = new FileOutputStream(filename);
            ObjectOutputStream outputStream = new ObjectOutputStream(fileOutputStream);
            outputStream.writeObject(scoreboard);
            fileOutputStream.close();
            outputStream.close();
        } catch (IOException e) {
            Log.e("Exception", "File write failed: " + e.toString());
        }
    }

    /**
     * Load the board manager from fileName.
     *
     * @param fileName the name of the file
     */
    public MatchingBoardManager loadMatchingBoardManagerFromFile(String fileName) {
        try {
            InputStream inputStream = new FileInputStream(fileName);
            if (inputStream != null) {
                ObjectInputStream input = new ObjectInputStream(inputStream);
                MatchingBoardManager tmpBoardManager = (MatchingBoardManager) input.readObject();
                inputStream.close();
                return tmpBoardManager;
            }
        } catch (FileNotFoundException e) {
            Log.e("login activity", "File not found: " + e.toString());
        } catch (IOException e) {
            Log.e("login activity", "Can not read file: " + e.toString());
        } catch (ClassNotFoundException e) {
            Log.e("login activity", "File contained unexpected data type: " + e.toString());
        }
        return null;
    }

    /**
     * Save the board manager to fileName.
     *
     * @param fileName the name of the file
     * @param context
     */
    public void saveMatchingBoardManagerToFile(String fileName, MatchingBoardManager matchingBoardManager, Context context) {
        try {
            FileOutputStream fileOutputStream = new FileOutputStream(fileName);
            ObjectOutputStream outputStream = new ObjectOutputStream(fileOutputStream);
            outputStream.writeObject(matchingBoardManager);
            outputStream.close();
        } catch (IOException e) {
            Log.e("Exception", "File write failed: " + e.toString());
        }
    }

    /**
     * Load the user manager from USER_FILENAME.
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
     * Save the user manager to USER_FILENAME.
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
