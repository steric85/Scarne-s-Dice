    package com.example.android.scarnesdie;

    import android.os.Bundle;
    import android.os.Handler;
    import android.os.SystemClock;
    import android.support.v7.app.AppCompatActivity;
    import android.view.View;
    import android.view.animation.TranslateAnimation;
    import android.widget.Button;
    import android.widget.ImageView;
    import android.widget.TextView;
    import android.widget.Toast;

    import java.util.Random;
    import java.util.concurrent.TimeUnit;

    public class MainActivity extends AppCompatActivity {

        int totalUser, totalComp, currUser, currComp;
        ImageView dice ;
        TextView totalUSER,totalCOMP,currSCORE;
        Button reset,hold,roll;
        boolean userTurn;
        TranslateAnimation anim1,anim2;
        Handler handler = new Handler();

        @Override
        protected void onCreate(Bundle savedInstanceState) {
            super.onCreate(savedInstanceState);
            setContentView(R.layout.activity_main);
            totalUser = 0;
            totalComp = 0;
            currUser = 0;
            currComp = 0;
            userTurn = true;
            dice = (ImageView)findViewById(R.id.dice_iv);
            totalCOMP = (TextView)findViewById(R.id.comp_score_tv);
            totalUSER = (TextView)findViewById(R.id.your_score_tv);
            currSCORE = (TextView)findViewById(R.id.curr_score_tv);
            reset = (Button)findViewById(R.id.reset_btn);
            hold = (Button)findViewById(R.id.hold_btn);
            roll = (Button)findViewById(R.id.roll_btn);

            anim1 = new TranslateAnimation(0.0f,200.0f, 0.0f,0.0f);
            anim1.setDuration(200);

            anim2 = new TranslateAnimation(0.0f,-200.0f, 0.0f,0.0f);
            anim2.setDuration(200);

            reset.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                   ResetButton();
                }
            });

            hold.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    HoldButton();
                }
            });

            roll.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    rollDice();
                }
            });
        }

        public void rollDice(){

            Random rand = new Random();
            int diceValue = rand.nextInt(6)+1;

            if (dice != null) {
                switch (diceValue){
                    case 1: dice.setImageResource(R.drawable.dice1);
                        break;
                    case 2: dice.setImageResource(R.drawable.dice2);
                        break;
                    case 3: dice.setImageResource(R.drawable.dice3);
                        break;
                    case 4: dice.setImageResource(R.drawable.dice4);
                        break;
                    case 5: dice.setImageResource(R.drawable.dice5);
                        break;
                    case 6: dice.setImageResource(R.drawable.dice6);
                }
            }

            dice.setAnimation(anim1);
            handler.postDelayed(new Runnable() {
                @Override
                public void run() {
                    dice.setAnimation(null);
                }
            },200);

            if(userTurn){

                if(diceValue == 1){
                    currUser = 0;
                    currSCORE.setText("Your current score : 0");
                    userTurn = false;
                    computerTurn();
                }
                else{
                    currUser += diceValue;
                        currSCORE.setText("Your current score : "+currUser);
                        if(currUser + totalUser >=100){

                            Toast.makeText(this,"",Toast.LENGTH_SHORT).show();
                            Toast.makeText(this,"",Toast.LENGTH_SHORT).show();
                            ResetButton();
                        }
               }
            }
        }

        public void ResetButton(){
            totalComp = totalUser = currUser = currComp = 0;
            totalCOMP.setText(totalComp+"");
            totalUSER.setText(totalUser+"");
            currSCORE.setText("Your current score : 0");
            userTurn=true;  // assuming user has first turn
            roll.setClickable(true);
            hold.setClickable(true);
        }

        public void HoldButton(){
            userTurn=false;
            totalUser += currUser;
            currUser = currComp = 0;
            totalUSER.setText(totalUser+"");
            currSCORE.setText("Your current score : 0");
            computerTurn();
        }

        public void computerTurn(){

            if(userTurn){
                roll.setClickable(true);
                hold.setClickable(true);
                return;
            }

            roll.setClickable(false);
            hold.setClickable(false);

            while(currComp<20){

                Random rand = new Random();
                int diceValue = rand.nextInt(6)+1;

                if (dice != null) {
                    switch (diceValue){
                        case 1: dice.setImageResource(R.drawable.dice1);
                            break;
                        case 2: dice.setImageResource(R.drawable.dice2);
                            break;
                        case 3: dice.setImageResource(R.drawable.dice3);
                            break;
                        case 4: dice.setImageResource(R.drawable.dice4);
                            break;
                        case 5: dice.setImageResource(R.drawable.dice5);
                            break;
                        case 6: dice.setImageResource(R.drawable.dice6);
                    }
                }


                    dice.setAnimation(anim2);
                    handler.postDelayed(new Runnable() {
                        @Override
                        public void run() {
                            dice.setAnimation(null);
                        }
                    },3000);


                if(diceValue == 1){

                    currComp = 0;
                    roll.setClickable(true);
                    hold.setClickable(true);
                    currSCORE.setText("Comp current score : 0");
                    userTurn = true;
                    break;
                }
                else{
                    currComp += diceValue;
                    currSCORE.setText("Comp current score : "+currComp);
                    if(currComp + totalComp >=100){
                        Toast.makeText(this,"",Toast.LENGTH_SHORT).show();
                        Toast.makeText(this,"",Toast.LENGTH_SHORT).show();
                        ResetButton();
                    }

                }

            }

            try{
                TimeUnit.MILLISECONDS.sleep(500);
            }catch (InterruptedException e){
                e.printStackTrace();
            }

            totalComp += currComp;
            currSCORE.setText("Your current score : 0");
            totalCOMP.setText(totalComp+"");
            userTurn = true;
            roll.setClickable(true);
            hold.setClickable(true);

        }

    }
