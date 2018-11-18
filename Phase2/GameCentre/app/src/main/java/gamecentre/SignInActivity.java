package gamecentre;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;

import slidingtiles.R;
import slidingtiles.SlidingtilesScoreboardActivity;
import slidingtiles.SlidingtilesStartingActivity;

/**
 * Allow the user to sign in.
 */
public class SignInActivity extends AppCompatActivity {

    /**
     * The filename to save users to.
     */
    public static final String USER_FILENAME = "user_file.ser";

    /**
     * The user manager.
     */
    private UserManager userManager;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        userManager = new UserManager();

        setContentView(R.layout.activity_signin);
        addSignInButtonListener();
    }

    /**
     * Sign in as a user if the user exists.
     */
    private void signIn() {
        EditText usernameEditText = findViewById(R.id.UsernameSignIn);
        EditText passwordEditText = findViewById(R.id.PasswordSignIn);
        String username = usernameEditText.getText().toString();
        String password = passwordEditText.getText().toString();

        loadFromFile(USER_FILENAME);

        if (userManager.getPassword(username) == null) {
            Toast.makeText(this, "Username does not exist", Toast.LENGTH_LONG).show();
        } else if (userManager.getPassword(username).equals(password)) {
            SlidingtilesStartingActivity.saveFileName = username + ".ser";
            SlidingtilesStartingActivity.autoSaveFileName = username + "_autosave.ser";
            SlidingtilesStartingActivity.tempSaveFileName = username + "_tmp.ser";
            saveToFile(USER_FILENAME);
            SlidingtilesScoreboardActivity.user = username;
            switchToGameChoiceActivity();
        } else {
            Toast.makeText(this, "Invalid password", Toast.LENGTH_LONG).show();
        }
    }

    /**
     * Activate the ok button.
     */
    private void addSignInButtonListener() {
        Button startButton = findViewById(R.id.okin);
        startButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                loadFromFile(USER_FILENAME);
                signIn();
            }
        });
    }

    /**
     * Display the game choice activity.
     */
    private void switchToGameChoiceActivity() {
        Intent tmp = new Intent(this, GameChoiceActivity.class);
        startActivity(tmp);
    }

    /**
     * Load the user manager from fileName.
     *
     * @param fileName the name of the file
     */
    private void loadFromFile(String fileName) {
        try {
            InputStream inputStream = this.openFileInput(fileName);
            if (inputStream != null) {
                ObjectInputStream input = new ObjectInputStream(inputStream);
                userManager = (UserManager) input.readObject();
                inputStream.close();
                if (userManager == null) {
                    userManager = new UserManager();
                }
            }
        } catch (FileNotFoundException e) {
            Log.e("login activity", "File not found: " + e.toString());
        } catch (IOException e) {
            Log.e("login activity", "Can not read file: " + e.toString());
        } catch (ClassNotFoundException e) {
            Log.e("login activity", "File contained unexpected data type: " + e.toString());
        }
    }

    /**
     * Save the user manager to fileName.
     *
     * @param fileName the name of the file
     */
    public void saveToFile(String fileName) {
        try {
            ObjectOutputStream outputStream = new ObjectOutputStream(
                    this.openFileOutput(fileName, MODE_PRIVATE));
            outputStream.writeObject(userManager);
            outputStream.close();
        } catch (IOException e) {
            Log.e("Exception", "File write failed: " + e.toString());
        }
    }
}

