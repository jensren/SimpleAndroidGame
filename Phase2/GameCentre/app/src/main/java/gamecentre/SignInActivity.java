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
import gamecentre.slidingtiles.SlidingtilesScoreboard;
import gamecentre.slidingtiles.SlidingtilesStartingActivity;

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
    /**
     * The serializer for this activity.
     */
    Serializer serializer = new Serializer();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        userManager = new UserManager();
        //Serializer.saveUserManagerToFile(USER_FILENAME,userManager);

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

        userManager = Serializer.loadUserManagerFromFile(USER_FILENAME, this);

        if (userManager.getPassword(username) == null) {
            Toast.makeText(this, "Username does not exist", Toast.LENGTH_LONG).show();
        } else if (userManager.getPassword(username).equals(password)) {
            setFileNames(username);
            Serializer.saveUserManagerToFile(USER_FILENAME, userManager, this);
            SlidingtilesScoreboard.setUser(username);
            MatchingScoreboard.setUser(username);
            BattleScoreboard.setUser(username);
            switchToGameChoiceActivity();
        } else {
            Toast.makeText(this, "Invalid password", Toast.LENGTH_LONG).show();
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
    private void addSignInButtonListener() {
        Button startButton = findViewById(R.id.okin);
        startButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                userManager = Serializer.loadUserManagerFromFile(USER_FILENAME, SignInActivity.this);
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

    @Override
    public void onBackPressed() {
        NavUtils.navigateUpFromSameTask(this);
    }
}

