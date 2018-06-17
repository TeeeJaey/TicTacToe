package com.example.user.connect3;

import android.content.Intent;
import android.graphics.Color;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.GridLayout;
import android.widget.LinearLayout;
import android.widget.ImageView;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity {

    int player = 0; // 0 = yellow, 1 = red
    int[] gameState = {2,2,2,2,2,2,2,2,2};      // 2 means unp
    int[][] endConditions = {{0,1,2},{3,4,5},{6,7,8},{0,3,6},{1,4,7},{2,5,8},{0,4,8},{2,4,6}};
    boolean end = false;

    public void dropIn(View view){


        ImageView counter = (ImageView) view;
        counter.getTag().toString();

        int tappedCounter = Integer.parseInt(counter.getTag().toString());
        if (gameState[tappedCounter]==2 && end == false){
            gameState[tappedCounter] = player;
            counter.setTranslationY(-1000f);
            if(player==0)
            {
                counter.setImageResource(R.drawable.ycoin);
                counter.animate().translationYBy(1000f).rotationYBy(360).setDuration(300);
                player = 1;
            }
            else
            {
                counter.setImageResource(R.drawable.rcoin);
                counter.animate().translationYBy(1000f).rotationYBy(360).setDuration(300);
                player = 0;
            }

            for(int[] endCondition : endConditions)
            {
                if ((gameState[endCondition[0]] == gameState[endCondition[1]]) &&
                        (gameState[endCondition[1]] == gameState[endCondition[2]]) &&
                        (gameState[endCondition[0]] != 2))
                {   // Someone has won

                    end = true;
                    TextView msg = (TextView) findViewById(R.id.endText);
                    String winner = "Red has won";
                    msg.setBackgroundColor(Color.parseColor("#ff978d"));

                    if(gameState[endCondition[0]] == 0) {
                        winner = "Yellow has won!";
                        msg.setBackgroundColor(Color.parseColor("#fff762"));
                    }

                    msg.setText(winner);
                    msg.setVisibility(View.VISIBLE);

                }
                else
                {
                    boolean gameOver = true;
                    for (int j : gameState)
                    {
                        if(j == 2)
                            gameOver = false;
                    }

                    if (gameOver)
                    {
                        TextView msg = (TextView) findViewById(R.id.endText);
                        String winner = "It's a draw.";
                        msg.setBackgroundColor(Color.parseColor("#ffc598"));
                        msg.setText(winner);
                        msg.setVisibility(View.VISIBLE);
                    }
                }
            }
            }
    }

    public void restart(View view) {
        /*
        end = false;
        TextView msg = (TextView) findViewById(R.id.endText);
        msg.setVisibility(View.INVISIBLE);
        int player = 0; // 0 = yellow, 1 = red
        int[] gameState = {2,2,2,2,2,2,2,2,2};

        for ( int i=0; i < gameState.length; i++)
        {
            gameState[i] = 2;
        }
        GridLayout gridLayout = (GridLayout) findViewById(R.id.board);
        for ( int i=0; i < gridLayout.getChildCount(); i++)
            ((ImageView) gridLayout.getChildAt(i)).setImageResource(0);

        */

        this.recreate();

        /*
        Intent intent = getIntent();
        finish();
        startActivity(intent);
        */
    }
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
    }
}
