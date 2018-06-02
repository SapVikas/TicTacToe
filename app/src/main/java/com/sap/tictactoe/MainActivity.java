package com.sap.tictactoe;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.GridLayout;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity {

    //0=yellow, red=1
    int activePlayer=0;
    boolean gameIsActive = true;

    //2 means unplayed
    int[] gameState={2,2,2,2,2,2,2,2,2};
    int[][] winningPositions={{0,1,2},{3,4,5},{6,7,8},{0,3,6},{1,4,7},{2,5,8},{0,4,8},{2,4,6}};
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
    }
    public void dropIn(View view){
        ImageView counter= (ImageView) view;
        int tappedCounter= Integer.parseInt(counter.getTag().toString());
        if (gameState[tappedCounter]==2){
            gameState[tappedCounter]=activePlayer;
        if (activePlayer==0){
            counter.setImageResource(R.drawable.x);
            activePlayer=1;
        }else if (activePlayer==1){
            counter.setImageResource(R.drawable.o);
            activePlayer=0;
        }
        for (int[]winningPosition:winningPositions){
            if (gameState[winningPosition[0]]==gameState[winningPosition[1]]
                    && gameState[winningPosition[1]]==gameState[winningPosition[2]]
                    &&gameState[winningPosition[0]]!=2){

                String winner="player 2";
                    if (gameState[winningPosition[0]]==0){
                        winner="player 1";
                    }
                //someone has won
                GridLayout gridLayout = findViewById(R.id.gridLayout);
                LinearLayout linearLayout = findViewById(R.id.playAgainLayout);
                linearLayout.setVisibility(View.VISIBLE);
                gridLayout.setVisibility(View.INVISIBLE);
                TextView winnerMessage = findViewById(R.id.winnerMessage);
                winnerMessage.setText(winner+"  has won");
            }else {
                boolean gameIsOver = true;
                for (int counterState : gameState) {
                    if (counterState == 2) gameIsOver = false;
                }
                if (gameIsOver) {
                    TextView winnerMessage = (TextView) findViewById(R.id.winnerMessage);
                    winnerMessage.setText("It's a draw");
                    LinearLayout layout = (LinearLayout)findViewById(R.id.playAgainLayout);
                    layout.setVisibility(View.VISIBLE);
                }
            }
        }

        }
    }
    public void playAgain(View view){
        gameIsActive = true;

        LinearLayout layout = findViewById(R.id.playAgainLayout);
        GridLayout gridLayout = findViewById(R.id.gridLayout);
        gridLayout.setVisibility(View.VISIBLE);
        layout.setVisibility(View.INVISIBLE);

        activePlayer = 0;

        for (int i = 0; i < gameState.length; i++) {

            gameState[i] = 2;

        }


        for (int i = 0; i< gridLayout.getChildCount(); i++) {

            ((ImageView) gridLayout.getChildAt(i)).setImageResource(0);

        }
    }
    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            return true;
        }

        return super.onOptionsItemSelected(item);
    }
}
