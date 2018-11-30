package gamecentre;

import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.NavUtils;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import gamecentre.battlegame.BattleScoreboard;
import gamecentre.cardmatching.MatchingScoreboard;
import gamecentre.cardmatching.MatchingStartingActivity;
import gamecentre.slidingtiles.R;
import gamecentre.slidingtiles.SlidingtilesStartingActivity;

/**
 * Allow the user to sign up.
 */
public class SignUpActivity extends AppCompatActivity {

    /**
     * The filename to save users to.
     */
    public static final String USER_FILENAME = "user_file.ser";

    /**
     * The user manager.
     */
    private UserManager userManager;
    /**
     * The serializer for this activity.
     */
    Serializer serializer = new Serializer();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        userManager = new UserManager();
        //  serializer.saveUserManagerToFile(USER_FILENAME,userManager);
        setContentView(R.layout.activity_signup);
        addSignUpButtonListener();
    }

    /**
     * Create a new user if username and password are nonempty and the username is not taken.
     */
    private void createUser() {
        EditText usernameEditText = findViewById(R.id.UsernameSignUp);
        EditText passwordEditText = findViewById(R.id.PasswordSignUp);
        String username = usernameEditText.getText().toString();
        String password = passwordEditText.getText().toString();

        if (username.equals("") || password.equals("")) {
            Toast.makeText(this, "Please enter a username and password", Toast.LENGTH_LONG).show();
        } else if (userManager.getPassword(username) == null) {
            userManager.addUser(username, password);
            setFileNames(username);
            serializer.saveUserManagerToFile(USER_FILENAME, userManager, this);
            serializer.saveUserManagerToFile(USER_FILENAME, userManager, this);
            Scoreboard.setUser(username);
            switchToGameChoiceActivity();
        } else {
            Toast.makeText(this, "Username taken", Toast.LENGTH_LONG).show();
        }
    }

    /**
     * Set the filesnames for the user.
     *
     * @param username the name of the user.
     */
    private void setFileNames(String username) {
        SlidingtilesStartingActivity.saveFileName = username + "_slidingtiles.ser";
        SlidingtilesStartingActivity.autoSaveFileName = username + "_slidingtiles_autosave.ser";
        SlidingtilesStartingActivity.tempSaveFileName = username + "_slidingtiles_tmp.ser";
        MatchingStartingActivity.matchingSaveFileName = username + "_matching.ser";
        MatchingStartingActivity.matchingAutoSaveFileName = username + "_matching_autosave.ser";
        MatchingStartingActivity.matchingTempSaveFileName = username + "_matching_tmp.ser";
    }

    /**
     * Activate the ok button.
     */
    public void addSignUpButtonListener() {
        Button startButton = findViewById(R.id.okup);
        startButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                serializer.loadUserManagerFromFile(USER_FILENAME, SignUpActivity.this);
                createUser();
            }
        });
    }

    /**
     * Display the game choice activity.
     */
    public void switchToGameChoiceActivity() {
        Intent tmp = new Intent(this, GameChoiceActivity.class);
        startActivity(tmp);
    }

    @Override
    public void onBackPressed() {
        NavUtils.navigateUpFromSameTask(this);
    }
}

