package com.tarun.tic_tac_toe;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;
import android.content.DialogInterface;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity {

    boolean executed;
    String winnerStr;
    boolean gameActive = true;
    int i = 0, j = 0;
    public int activePlayer = 1;
    int[] gameState = {2, 2, 2, 2, 2, 2, 2, 2, 2};

    //state meaning
    //0 - 0
    //1 - X
    //2 - Null

    int[][] winPositions = {{0, 1, 2}, {3, 4, 5}, {6, 7, 8}, //horizontal win positions
            {0, 3, 6}, {1, 4, 7}, {2, 5, 8}, //vertical win positions
            {0, 4, 8}, {2, 4, 6}}; //diagonal win positions


    public void playerTap(View view) {

        ImageView img = (ImageView) view;
        int tappedImage = Integer.parseInt(img.getTag().toString()); //getting tag of tapped img
        if (gameState[tappedImage] == 2 && gameActive) { //if tapped img is empty and game is active
            gameState[tappedImage] = activePlayer; //set active player's sign on tapped img
            if (activePlayer == 1) { //if x is active player
                img.setImageResource(R.drawable.newx); //set img as x
                activePlayer = 0; // change active player to 0
                TextView status = findViewById(R.id.status);
                String StatusTxt = "0's turn - Tap to play";
                status.setText(StatusTxt); //setting status

            } else { //if 0 is active player
                img.setImageResource(R.drawable.whiteo); //set img a 0
                activePlayer = 1; //change active player to x
                TextView status = findViewById(R.id.status);
                String StatusTxt = "X's turn - Tap to play";
                status.setText(StatusTxt); //setting status
            }
        }

        // checking if anyone won the game

        for (int[] winPosition : winPositions) { //iterating winPositions array

            if (gameState[winPosition[0]] == gameState[winPosition[1]]  //checking win positions
                    && gameState[winPosition[1]] == gameState[winPosition[2]]
                    && gameState[winPosition[0]] != 2) {

                // someone has won

                gameActive = false; //setting gameActive as false because someone has won
                if (gameState[winPosition[0]] == 1) { //if X at first win position
                    // X has won
                    if (!executed) { //to ensure counter do not execute more than once
                        winX(); //calling winX() method
                        executed = true; //setting executed as true so is doesn't execute more than once
                    }
                }
                if (gameState[winPosition[0]] == 0) { //if 0 at first win position
                    // 0 has won
                    if (!executed) { //to ensure counter do not execute more than once
                        winO(); //calling winX() method
                        executed = true; //setting executed as true so is doesn't execute more than once
                    }
                }
            }
        }
        //following code block checks if all boxes are occupied
         boolean emptySqr = false;
           for(int counterState : gameState){ //iterating gameState array
               if(counterState == 2){ //if any box is empty
                   emptySqr = true; //then set emptySqr as true
                   break;
               }
           }
          if(!emptySqr && gameActive){ // if any box is not empty and gameActive is true then its a draw
              TextView status = findViewById(R.id.status);
              String statusTxt = "It's a draw";
              status.setText(statusTxt); //setting status
          }
        }

        public void winX(){
            i++; //incrementing counter variable for X
            winnerStr = "X has won!";
            TextView status = findViewById(R.id.status);
            status.setText(winnerStr);   //setting status
            String counter = " X = " + i;
            TextView xCounter = findViewById(R.id.xCounter);
            xCounter.setText(counter);   //setting counter status

        }
    public void winO(){
            j++; //incrementing counter variable for 0
            winnerStr = "0 has won!";
            TextView status = findViewById(R.id.status);
            status.setText(winnerStr);  //setting status
            String counter = " 0 = " + j;
            TextView oCounter = findViewById(R.id.oCounter);
            oCounter.setText(counter); //setting counter status

    }





    public void resetGame(View view){ //reset button onClick method
        gameActive = true; //setting game as active
        activePlayer = 1; //setting X as activePlayer
        for(int i=0; i<gameState.length; i++){ //setting all boxes as empty
            gameState[i]=2;
        }
        ImageView imageView0 = findViewById(R.id.imageView0); //setting all boxes img as null
        imageView0.setImageResource(0);
        ImageView imageView1 = findViewById(R.id.imageView1);
        imageView1.setImageResource(0);
        ImageView imageView2 = findViewById(R.id.imageView2);
        imageView2.setImageResource(0);
        ImageView imageView3 = findViewById(R.id.imageView3);
        imageView3.setImageResource(0);
        ImageView imageView4 = findViewById(R.id.imageView4);
        imageView4.setImageResource(0);
        ImageView imageView5 = findViewById(R.id.imageView5);
        imageView5.setImageResource(0);
        ImageView imageView6 = findViewById(R.id.imageView6);
        imageView6.setImageResource(0);
        ImageView imageView7 = findViewById(R.id.imageView7);
        imageView7.setImageResource(0);
        ImageView imageView8 = findViewById(R.id.imageView8);
        imageView8.setImageResource(0);
        TextView status = findViewById(R.id.status);
        String statusTxt = "X's turn - Tap to play";
        status.setText(statusTxt); //setting status
        executed=false; // to increment winner counter variable again
    }
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        TextView xCounter = findViewById(R.id.xCounter);
        xCounter.setText("X = 0");
        TextView oCounter = findViewById(R.id.oCounter);
        oCounter.setText("0 = 0");

    }
@Override
    public void onBackPressed(){
        final AlertDialog.Builder builder = new AlertDialog.Builder(MainActivity.this);
        builder.setCancelable(true);
        builder.setMessage("Are you sure you want to exit ?");
        builder.setPositiveButton("No", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                dialog.cancel();
            }
        });
        builder.setNegativeButton("Exit", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                finish();
            }
        });
        AlertDialog alert = builder.create();
        alert.show();
}
}
