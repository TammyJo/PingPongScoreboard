package com.example.android.pingpongscoreboard;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity {

    final int POINT = 1;
    final String SERVER_MSG = "SERVER";

    int gameNumber; // android:id="@+id/game_number"
    int scorePlayerA; // android:id="@+id/team_a_overall_score"
    int scorePlayerB; // android:id="@+id/team_b_overall_score"
    int pointsPlayerA; // android:id="@+id/game_points_player_a"
    int pointsPlayerB; // android:id="@+id/game_points_player_b"
    boolean playerAisServer;
    boolean playerBisServer;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        setupNewMatch(); // start a brand new match
    }

    /**
     * Set all values to the desired state at the beginning of a match and display the values on the screen
     */
    private void setupNewMatch() {
        // Initialize the initial ping pong match values
        gameNumber = 1;
        scorePlayerA = 0;
        scorePlayerB = 0;
        pointsPlayerA = 0;
        pointsPlayerB = 0;
        playerAisServer = true;
        playerBisServer = false;
        // Display the initial ping pong match values
        displayGameNumber();
        displayForPlayerA(scorePlayerA, pointsPlayerA);
        displayForPlayerB(scorePlayerB, pointsPlayerB);
        displayServerText();
    }

    /**
     * Add a point for Player A while the game is still in progress
     */
    public void addPointForPlayerA(View v) {
        pointsPlayerA++;
        if (!matchIsOver()) {
            if (!gameIsOver()) {
                identifyServer();
                displayForPlayerA(scorePlayerA, pointsPlayerA);
            } else {
                startNewGame();
            }
        }
    }

    /**
     * Add a point for Player B while the game is still in progress
     */
    public void addPointForPlayerB(View v) {
        pointsPlayerB++;
        if (!matchIsOver()) {
            if (!gameIsOver()) {
                identifyServer();
                displayForPlayerB(scorePlayerB, pointsPlayerB);
            } else {
                startNewGame();
            }
        }
    }

    /**
     * Returns whether a single game has finished
     */
    private boolean gameIsOver() {
        if ((pointsPlayerA >= 11 || pointsPlayerB >= 11) && Math.abs(pointsPlayerA - pointsPlayerB) > 1) {
            return true;
        }
        return false;
    }

    /**
     * Returns whether an entire match is over
     */
    private boolean matchIsOver() {
        // If either team has overallscore of 3
        if (scorePlayerA == 3 || scorePlayerB == 3) {
            return true;
        }
        return false;
    }

    /**
     * Identifies which player should be serving
     */
    private void identifyServer() {
        if ((pointsPlayerA + pointsPlayerB) % 2 == 0) {
            switchAndDisplayServer();
        }
    }

    /**
     * Switches the server to the other player
     */
    private void switchAndDisplayServer() {
        // If current server is Player A, switch to Player B
        if (playerAisServer) {
            playerAisServer = false;
            playerBisServer = true;
        }
        // Else current server must be Player B, so switch to Player A
        else {
            playerBisServer = false;
            playerAisServer = true;
        }
        displayServerText();
    }

    /**
     * Updates the overall score (number of games won in the match) for the player with the most game points
     */
    private void updateOverallScore() {
        // Increase the overall score of the player with the most game points by one
        if (pointsPlayerA > pointsPlayerB) {
            scorePlayerA++;
        } else {
            scorePlayerB++;
        }
    }

    /**
     * Starts a new game within the match, resets individual game point scores to 0
     */
    private void startNewGame() {
        updateOverallScore();
        pointsPlayerA = 0;
        pointsPlayerB = 0;
        gameNumber++;
        displayForPlayerA(scorePlayerA, pointsPlayerA);
        displayForPlayerB(scorePlayerB, pointsPlayerB);
        displayGameNumber();
    }

    /**
     * Reset score board so that all scores are 0 and Player A is the server
     */
    public void reset(View v) {
        setupNewMatch();
    }

    /**
     * Displays the current game number within the match
     */
    public void displayGameNumber() {
        TextView gameNumberView = (TextView) findViewById(R.id.game_number);
        gameNumberView.setText(String.valueOf(gameNumber));
    }

    /**
     * Displays the current game points for Player A
     */
    public void displayForPlayerA(int overallScore, int gamePoints) {
        TextView overallScoreView = (TextView) findViewById(R.id.team_a_overall_score);
        overallScoreView.setText(String.valueOf(overallScore));
        TextView scoreView = (TextView) findViewById(R.id.game_points_player_a);
        scoreView.setText(String.valueOf(gamePoints));
    }

    /**
     * Displays the current game points for Player B
     */
    public void displayForPlayerB(int overallScore, int gamePoints) {
        TextView overallScoreView = (TextView) findViewById(R.id.team_b_overall_score);
        overallScoreView.setText(String.valueOf(overallScore));
        TextView scoreView = (TextView) findViewById(R.id.game_points_player_b);
        scoreView.setText(String.valueOf(gamePoints));
    }

    /**
     * Displays a message under the Player that is the server
     */
    public void displayServerText() {
        TextView serverViewA = (TextView) findViewById(R.id.server_player_a);
        TextView servervViewB = (TextView) findViewById(R.id.server_player_b);
        if (playerAisServer) {
            serverViewA.setText(SERVER_MSG);
            servervViewB.setText(String.valueOf(""));
        } else {
            serverViewA.setText("");
            servervViewB.setText(String.valueOf(SERVER_MSG));
        }
    }
}
