package com.example.anirudh.scarnesdice;

import android.os.Handler;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import java.util.Random;

public class MainActivity extends AppCompatActivity {

    TextView yourScore;
    TextView computerScore;
    Button roll;
    Button hold;
    Button reset;
    ImageView img;

    Random rand;
    int checkTurn = 0;
    int number=0;
    int userTotal=0;
    int userTurn=0;
    int computerTotal=0;
    int computerTurn=0;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        rand =  new Random();
        yourScore = (TextView)findViewById(R.id.yourScore);
        computerScore = (TextView)findViewById(R.id.computerScore);
        roll = (Button)findViewById(R.id.rollbtn);
        hold = (Button)findViewById(R.id.holdbtn);
        reset = (Button)findViewById(R.id.resetbtn);
        img = (ImageView)findViewById(R.id.dice);

        roll.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                number = getDice();

                    if(number!=1){
                        userTurn+=number;
                        yourScore.setText("Your Score: " + userTotal + "\t Turn Score: "+ userTurn);
                    }
                    else{
                        userTurn =0;
                        checkTurn++;
                        Toast.makeText(getApplicationContext(),"Turn has ended",Toast.LENGTH_SHORT).show();
                        yourScore.setText("Your Score: " + userTotal);
                        roll.setEnabled(false);
                        computerTurnDice();
                    }

//                else{
//                    if(number!=1){
//                        computerTurn+=number;
//                        computerScore.setText("Computer's Score: " + computerTotal + "\t Turn Score: "+ computerTurn);
//                    }
//                    else{
//                        computerTurn =0;
//                        checkTurn++;
//                        Toast.makeText(getApplicationContext(),"Turn has ended", Toast.LENGTH_SHORT).show();
//                        computerScore.setText("Computer's Score: " + computerTotal);
//
//                    }
//
//                }
            }
        });

        hold.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                    userTotal +=userTurn;
                    userTurn = 0;
                    yourScore.setText("Your Score: " + userTotal);
                Toast.makeText(getApplicationContext(), "Turn has ended", Toast.LENGTH_SHORT).show();

                    computerTurnDice();

                }

        });

        reset.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                userTurn = 0;
                userTotal = 0;
                computerTotal = 0;
                computerTurn = 0;
                checkTurn=0;
                yourScore.setText("Your Score : 0");
                computerScore.setText("Computer's Score : 0");

            }
        });
    }

    public void computerTurnDice(){
        roll.setEnabled(false);
        hold.setEnabled(false);
        roll.setClickable(false);
        hold.setClickable(false);

        final Handler h1 = new Handler();
        final Runnable runnable = new Runnable() {
            @Override
            public void run() {
                Log.d("asd1", "inside runnable");
                int number = getDice();

                    if (number != 1) {
                        computerTurn += number;
                        computerScore.setText("Computer's Score: " + computerTotal + "\t Turn Score: " + computerTurn);
                    } else {
                        computerTurn = 0;
                        checkTurn++;
                        Toast.makeText(getApplicationContext(), "Turn has ended", Toast.LENGTH_SHORT).show();
                        computerScore.setText("Computer's Score: " + computerTotal);
                    }

                if (computerTurn <= 10 && number != 1) {
                    h1.postDelayed(this, 1000);
                }
                else {
                    computerTotal += computerTurn;
                    computerTurn = 0;
                    computerScore.setText("Computer's Score: " + computerTotal);
                    Toast.makeText(getApplicationContext(), "Turn has ended", Toast.LENGTH_SHORT).show();
                }
            }
//        roll.setEnabled(false);
//        hold.setEnabled(false);
        };
        h1.post(runnable);
        roll.setEnabled(true);
        hold.setEnabled(true);
        roll.setClickable(true);
        hold.setClickable(true);
    }

    public int getDice() {
        number = rand.nextInt(6) + 1;

        switch (number) {
            case 1:
                img.setImageDrawable(getResources().getDrawable(R.drawable.dice1));
                break;
            case 2:
                img.setImageDrawable(getResources().getDrawable(R.drawable.dice2));
                break;
            case 3:
                img.setImageDrawable(getResources().getDrawable(R.drawable.dice3));
                break;
            case 4:
                img.setImageDrawable(getResources().getDrawable(R.drawable.dice4));
                break;
            case 5:
                img.setImageDrawable(getResources().getDrawable(R.drawable.dice5));
                break;
            case 6:
                img.setImageDrawable(getResources().getDrawable(R.drawable.dice6));
                break;
        }
        return number;

    }
}
