package com.example.android.pingpongscoreboard;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity {

    final int POINT = 1;

    int gameNumber = 1; // android:id="@+id/game_number"

    int scorePlayerA = 0; // android:id="@+id/team_a_overall_score"
    int scorePlayerB = 0; // android:id="@+id/team_b_overall_score"

    int pointsPlayerA = 0; // android:id="@+id/game_points_player_a"
    int pointsPlayerB = 0; // android:id="@+id/game_points_player_b"

    boolean isServerPlayerA = true;
    boolean isServerPlayerB = true;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
    }

    public void addPointForPlayerA(View v){
        scorePlayerA++;
        displayForPlayerA(scorePlayerA);
    }

    public void addPointForPlayerB(View v){
        scorePlayerB++;
        displayForPlayerB(scorePlayerB);
    }

    /**
     * Displays the current game points for Player B
     */
    public void displayForTeamB(int gamePoints) {
        TextView scoreView = (TextView) findViewById(R.id.team_b_score);
        scoreView.setText(String.valueOf(score));
    }

    /**
     * Displays the current game number within the match
     */
    public void displayGameNumber() {
        TextView scoreView = (TextView) findViewById(R.id.team_b_score);
        scoreView.setText(String.valueOf(score));
    }


}
