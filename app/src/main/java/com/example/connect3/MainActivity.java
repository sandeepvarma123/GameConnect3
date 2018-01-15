package com.example.connect3;

import android.graphics.Color;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutCompat;
import android.view.View;
import android.widget.GridLayout;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity {

     // 0 = yellow  1 = red
       int activePlayer=0;
       boolean gameActive = true;
       int[] gameState = {2,2,2,2,2,2,2,2,2}; // 2 means unplayed
       int[][] winningPositions={{0,1,2},{3,4,5},{6,7,8},{0,3,6},{1,4,7},{2,5,8},{0,4,8},{2,4,6}};

    public void dropIn(View view) {

        ImageView counter = (ImageView) view;
        System.out.println(counter.getTag().toString());
        int tappedCounter = Integer.parseInt(counter.getTag().toString());

        if (gameState[tappedCounter] == 2 && gameActive) {

            gameState[tappedCounter]= activePlayer;

            counter.setTranslationY(-1000f);
            if (activePlayer == 0) {
                counter.setImageResource(R.drawable.yellow);
                activePlayer = 1;

            } else {
                counter.setImageResource(R.drawable.red);
                activePlayer = 0;

            }
            counter.animate().translationYBy(1000f).rotation(360).setDuration(300);

            for(int[] win:winningPositions){

                if(gameState[win[0]]==gameState[win[1]]&& gameState[win[1]]==gameState[win[2]] && gameState[win[0]]!=2){

                     gameActive = false;

                     String winner = "red";
                    LinearLayout lay = (LinearLayout)findViewById(R.id.playAgainLayout);
                    lay.setBackgroundColor(Color.RED);

                    if(gameState[win[0]]==0) {
                        winner = "yellow";
                        lay.setBackgroundColor(Color.YELLOW);
                    }

                    TextView winnerMessage = (TextView)findViewById(R.id.winnerMessage);
                    winnerMessage.setText(winner+" has won");

                    LinearLayout layout= (LinearLayout)findViewById(R.id.playAgainLayout);
                    layout.setVisibility(View.VISIBLE);
                }

                else{

                    boolean isGameOver=true;

                    for(int counterState: gameState)
                    {
                        if(counterState==2)
                            isGameOver=false;
                    }

                    if(isGameOver){

                        TextView winnerMessage = (TextView)findViewById(R.id.winnerMessage);
                        winnerMessage.setText("Its Draw.. Sandeep Judgment Is Final..");

                        LinearLayout lay = (LinearLayout)findViewById(R.id.playAgainLayout);
                        lay.setBackgroundColor(Color.GREEN);


                        LinearLayout layout= (LinearLayout)findViewById(R.id.playAgainLayout);
                        layout.setVisibility(View.VISIBLE);
                    }
                }
            }
        }
    }

    public void playAgain(View view){

        gameActive=true;

        LinearLayout layout= (LinearLayout)findViewById(R.id.playAgainLayout);
        layout.setVisibility(View.INVISIBLE);
        activePlayer=0;

        for(int i=0;i<gameState.length;i++)
            gameState[i]=2;

        GridLayout gridLayout = (GridLayout)findViewById(R.id.gridLayout);

        for(int i=0 ; i< gridLayout.getChildCount();i++){

            ((ImageView)gridLayout.getChildAt(i)).setImageResource(0);
        }

    }
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
    }
}
