package com.example.aatman_tictactoe;

import android.content.DialogInterface;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

public class MainActivity extends AppCompatActivity {
    boolean gameActive = true;
    // 0 - x
    // 1 - o
    int activePlayer = 0;
    int[] gameState = {2,2,2,2,2,2,2,2,2};
    // 0 - x
    // 1 - o
    // 2 - Null
    int[][] winPositions = {{0,1,2},{3,4,5},{6,7,8},
                            {0,3,6},{1,4,7},{2,5,8},
                            {0,4,8},{2,4,6}};
    public void playerTap(View view){
        ImageView img = (ImageView) view;
        int tappedImage = Integer.parseInt(img.getTag().toString());
        if(!gameActive){
           return;
        }
        // o's & x's turn
        if(gameState[tappedImage] == 2){
            gameState[tappedImage] = activePlayer;
            img.setTranslationY(-1000f);
            if(activePlayer == 0){
                img.setImageResource(R.drawable.x);
                activePlayer = 1;
                TextView status = findViewById(R.id.status);
                status.setText("O's Turn - Tap to play");
            } else{
                img.setImageResource(R.drawable.o);
                activePlayer = 0;
                TextView status = findViewById(R.id.status);
                status.setText("X's Turn - Tap to play");
            }

            img.animate().translationYBy(1000f).setDuration(300);

        }
        // Winner Prediction
        for(int[] winPosition: winPositions){
            if(gameState[winPosition[0]] == gameState[winPosition[1]] &&
                    gameState[winPosition[1]] == gameState[winPosition[2]] &&
                    gameState[winPosition[0]] !=2) {
                String winnerStr;
                gameActive = false;
                if(gameState[winPosition[0]] == 0) {
                    showdialog("Yay! X has won");
                }
                else{
                    showdialog("Yay! o has won");
                }

            }
        }
    }
     private void showdialog(String winnerStr){
        new AlertDialog.Builder(this)
                .setTitle(winnerStr)
                .setPositiveButton("Restart game", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        gameReset();

                    }
                })
                .show();
     }
    public void gameReset() {
         gameActive = true;
         activePlayer = 0;
         for(int i=0; i<gameState.length; i++){
             gameState[i] = 2;
         }
         ((ImageView)findViewById(R.id.imageView1)).setImageResource(0);
         ((ImageView)findViewById(R.id.imageView2)).setImageResource(0);
         ((ImageView)findViewById(R.id.imageView3)).setImageResource(0);
         ((ImageView)findViewById(R.id.imageView4)).setImageResource(0);
         ((ImageView)findViewById(R.id.imageView5)).setImageResource(0);
         ((ImageView)findViewById(R.id.imageView6)).setImageResource(0);
         ((ImageView)findViewById(R.id.imageView7)).setImageResource(0);
         ((ImageView)findViewById(R.id.imageView8)).setImageResource(0);
         ((ImageView)findViewById(R.id.imageView9)).setImageResource(0);

     }




    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
    }
}