package com.example.connectgame;

import androidx.appcompat.app.AppCompatActivity;

import android.media.Image;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.GridLayout;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {


    // 0->Yellow 1->Red 2->empty
    int activePlayer=0;
    int[] gameState = {2,2,2,2,2,2,2,2,2};
    boolean gameActive=true;
    int[][] winningPositions= { {0,1,2}, {3,4,5}, {6,7,8}, {0,3,6}, {1,4,7}, {2,5,8}, {0,4,8}, {2,4,6}};

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
    }

    public void dropIn(View view) {
        ImageView counter = (ImageView) view;

//        Log.i("Tag", counter.getTag().toString());
        int tappedCounter = Integer.parseInt(counter.getTag().toString());

        if(gameState[tappedCounter]==2 && gameActive) {

            gameState[tappedCounter] = activePlayer;


            counter.setTranslationY(-1500);


            if (activePlayer == 0) {
                counter.setImageResource(R.drawable.yellow);
                activePlayer = 1;
            } else {
                counter.setImageResource(R.drawable.red);
                activePlayer = 0;
            }

            counter.animate().translationYBy(1500).rotation(3600).setDuration(300);
            for (int[] winningPosition : winningPositions) {
                if (gameState[winningPosition[0]] == gameState[winningPosition[1]]
                        && gameState[winningPosition[1]] == gameState[winningPosition[2]]
                        && gameState[winningPosition[2]] != 2) {
//                Toast.makeText(this,"someone Has One", Toast.LENGTH_SHORT).show();
                    gameActive=false;
                    String winner = "";
                    if (activePlayer == 1) {
                        winner = "Yellow";
                    } else {
                        winner = "Red";
                    }
//                    Toast.makeText(this, winner + " has won", Toast.LENGTH_SHORT).show();
                    Button playAgain = findViewById(R.id.button);
                    TextView winnerText= findViewById((R.id.textView));
                    winnerText.setText(winner + " has Won");

                    playAgain.setVisibility((view.VISIBLE));
                    winnerText.setVisibility(View.VISIBLE);;

                    Button button = findViewById(R.id.button);
                }
            }
        }
    }

    public void playAgain(View view){
        Button playAgain = findViewById(R.id.button);
        TextView winnerText= findViewById((R.id.textView));
        GridLayout grid = findViewById(R.id.grid_layout);

        playAgain.setVisibility((view.INVISIBLE));
        winnerText.setVisibility(View.INVISIBLE);

        for(int i=0;i<grid.getChildCount();i++){
            ImageView counter = (ImageView) grid.getChildAt(i);
            counter.setImageDrawable(null);
        }
        for (int i=0;i<gameState.length;i++){
            gameState[i]=2;
        }
        activePlayer=0;
        gameActive=true;
    }
}