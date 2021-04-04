package com.example.MathTrainer_1x1;

import android.content.Intent;
import android.graphics.Color;
import android.os.CountDownTimer;
import android.support.constraint.ConstraintLayout;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;
import android.widget.Toast;

import java.util.Arrays;
import java.util.Random;

public class MainActivity extends AppCompatActivity {

    int[] button;
    int richtigeAntwort;
    int punktestand;
    CountDownTimer count;
    boolean fertig;
    TextView punkte;



    public void prüfen(View view){
        TextView punkte = (TextView) findViewById(R.id.punktestand);
        count.cancel();

        if(Integer.parseInt(view.getTag().toString())==richtigeAntwort){
            Toast.makeText(this, "Gut gemacht, weiter so", Toast.LENGTH_SHORT).show();
            punktestand++;
            hintergrund(true);


        }
        else {
            Toast.makeText(this, "schade, leider falsch", Toast.LENGTH_SHORT).show();
            punktestand--;
            hintergrund(false);
        }

        punkte.setText("Punktestand: " + punktestand);
    }
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        punkte = (TextView) findViewById(R.id.punktestand);
    }

    @Override
    protected void onStart() {
        super.onStart();

        punktestand = 0;
        punkte.setText("Punktestand: " + String.valueOf(punktestand));

        gesamtCounterStarten();
        zahlenGenerieren();
        fertig = false;
    }

    @Override
    protected void onPause() {
        super.onPause();
        count.cancel();
        fertig = false;
    }

    public void zahlenGenerieren(){
        if(fertig == false) {

            count = new CountDownTimer(5000, 1000) {

                @Override
                public void onTick(long millisUntilFinished) {

                }

                @Override
                public void onFinish() {
                    Toast.makeText(MainActivity.this, "schade, du warst zu langsam...schneller!!!!", Toast.LENGTH_SHORT).show();
                    hintergrund(false);
                    punktestand--;
                    punkte.setText("Punktestand: " + punktestand);
                }
            }.start();

            TextView aufgabe = (TextView) findViewById(R.id.aufgabe);
            Random r = new Random();
            int ersteZahl = r.nextInt(10) + 1;
            int zweiteZahl = r.nextInt(10) + 1;
            richtigeAntwort = r.nextInt(4);
            int ergebnis = ersteZahl * zweiteZahl;
            int falscheZahlEins = ergebnis - 1;
            int falscheZahlZwei = (ersteZahl - 1) * zweiteZahl;
            int falscheZahlDrei = (ersteZahl + 1) * zweiteZahl;
            System.out.println(falscheZahlZwei + "##########" + falscheZahlDrei);
            button = new int[4];
            button[richtigeAntwort] = ergebnis;
            ZahlenRandomVerteilen(falscheZahlEins);
            ZahlenRandomVerteilen(falscheZahlZwei);
            ZahlenRandomVerteilen(falscheZahlDrei);

            System.out.println(Arrays.toString(button) + "##########");


            aufgabe.setText(ersteZahl + " * " + zweiteZahl);
            zahlenAufButton();
        }

    }

    public int ZahlenRandomVerteilen(int zahl){
        int i = 0;
        while (i<4){
            if(button[i]==0) {
                button[i] = zahl;
                return i;
            }
            else {
                i++;
            }

        }
        return 0;
    }

    public void zahlenAufButton(){
        TextView nulll = (TextView) findViewById(R.id.nulll);
        TextView eins = (TextView) findViewById(R.id.eins);
        TextView zwei = (TextView) findViewById(R.id.zwei);
        TextView drei = (TextView) findViewById(R.id.drei);
       for (int i = 0; i<=3; i++){
           if(i==0){
               nulll.setText(Integer.toString(button[i]));
           }
           else if(i==1){
               eins.setText(Integer.toString(button[i]));
           }
           else if(i==2){
               zwei.setText(Integer.toString(button[i]));
           }
           else if(i==3){
               drei.setText(Integer.toString(button[i]));
           }
       }
    }

    public void hintergrund(final boolean richtig){
        final TextView nulll = (TextView) findViewById(R.id.nulll);
        final TextView eins = (TextView) findViewById(R.id.eins);
        final TextView zwei = (TextView) findViewById(R.id.zwei);
        final TextView drei = (TextView) findViewById(R.id.drei);
        final ConstraintLayout con = (ConstraintLayout) findViewById(R.id.constraint);
        final TextView zeit = findViewById(R.id.zeit);
        final TextView punktestand = findViewById(R.id.punktestand);
        final TextView aufgabe = findViewById(R.id.aufgabe);

        new CountDownTimer(500, 250){

            @Override
            public void onTick(long millisUntilFinished) {
                if(richtig ==true){
                    con.setBackgroundColor(Color.GREEN);
                    nulll.setBackgroundColor(Color.GREEN);
                    eins.setBackgroundColor(Color.GREEN);
                    zwei.setBackgroundColor(Color.GREEN);
                    drei.setBackgroundColor(Color.GREEN);

                    nulll.setTextColor(Color.GREEN);
                    eins.setTextColor(Color.GREEN);
                    zwei.setTextColor(Color.GREEN);
                    drei.setTextColor(Color.GREEN);

                    zeit.setTextColor(Color.WHITE);
                    punktestand.setTextColor(Color.WHITE);
                    aufgabe.setTextColor(Color.WHITE);

                }
                else {
                    con.setBackgroundColor(Color.RED);
                    nulll.setBackgroundColor(Color.RED);
                    eins.setBackgroundColor(Color.RED);
                    zwei.setBackgroundColor(Color.RED);
                    drei.setBackgroundColor(Color.RED);

                    nulll.setTextColor(Color.RED);
                    eins.setTextColor(Color.RED);
                    zwei.setTextColor(Color.RED);
                    drei.setTextColor(Color.RED);

                    zeit.setTextColor(Color.WHITE);
                    punktestand.setTextColor(Color.WHITE);
                    aufgabe.setTextColor(Color.WHITE);
                }
            }

            @Override
            public void onFinish() {
                con.setBackgroundColor(Color.WHITE);
                eins.setBackgroundResource(R.drawable.button_background);
                zwei.setBackgroundResource(R.drawable.button_background);
                drei.setBackgroundResource(R.drawable.button_background);
                nulll.setBackgroundResource(R.drawable.button_background);

                nulll.setTextColor(getResources().getColor(R.color.darkPink));
                eins.setTextColor(getResources().getColor(R.color.darkPink));
                zwei.setTextColor(getResources().getColor(R.color.darkPink));
                drei.setTextColor(getResources().getColor(R.color.darkPink));

                zeit.setTextColor(getResources().getColor(R.color.darkPink));
                punktestand.setTextColor(getResources().getColor(R.color.darkPink));
                aufgabe.setTextColor(getResources().getColor(R.color.darkPink));

                zahlenGenerieren();

            }
        }.start();

    }

    public void gesamtCounterStarten(){
        punktestand = 0;
        final TextView zeit = (TextView) findViewById(R.id.zeit);

        new CountDownTimer(30000, 1000){

            @Override
            public void onTick(long millisUntilFinished) {
                if(millisUntilFinished/1000<10){
                    zeit.setText("0:0"+String.valueOf(millisUntilFinished/1000));
                }
                else {
                    zeit.setText("0:" + String.valueOf(millisUntilFinished / 1000));
                }
            }

            @Override
            public void onFinish() {
                count.cancel();
                fertig = true;
                System.out.println("#######" + punktestand + "    on finish");
                Intent intent = new Intent(getApplicationContext(), Highscore.class);
                intent.putExtra("punktestand", punktestand);
                startActivity(intent);
                //punktestand = 0;

            }
        }.start();
    }

}
