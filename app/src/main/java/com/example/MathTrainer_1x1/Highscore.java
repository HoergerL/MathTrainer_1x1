package com.example.MathTrainer_1x1;

import android.content.Intent;
import android.content.SharedPreferences;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;

public class Highscore extends AppCompatActivity {

    public void weiterspielen (View view){
        finish();
    }

    public void schließen(View view){
        this.finishAffinity();
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_highscore);


        final Intent main = getIntent();
        int ergebnis = main.getIntExtra("punktestand", 0);


        TextView text = (TextView) findViewById(R.id.text);
        TextView highscore = (TextView) findViewById(R.id.highscore);
        if(ergebnis<0){
            text.setText("Da hast du dich aber nicht genug angestrengt :(");
        }
        else if(ergebnis<=10){
            text.setText("Streng dich an, das geht besser!");
        }
        else {
            text.setText("super Leistung, weiter so ;)");
        }

        highscore.setText("dein Ergebnis ist: " + String.valueOf(ergebnis));

        vergleichen(ergebnis);


    }

    public void vergleichen(int ergebnis) {

        TextView highscore = (TextView) findViewById(R.id.highscore);
        TextView bewertung = (TextView) findViewById(R.id.bewertung);

        SharedPreferences sp = getSharedPreferences("your_prefs", MODE_PRIVATE);
        int highscore1 = sp.getInt("highscore1", 0);
        int highscore2 = sp.getInt("highscore2", 0);
        int highscore3 = sp.getInt("highscore3", 0);

        if (highscore1 <= ergebnis) {
            bewertung.setText("super, du hast "+ ergebnis + " Punkte und damit dein bestes Ergebnis bisher erzielt!!");
            highscore1 = ergebnis;
        }
        else if (highscore2<= ergebnis){
            bewertung.setText("super, du hast "+ ergebnis + " Punkte und damit dein zweitbestes Ergebnis bisher erzielt!!");
            highscore2 = ergebnis;
        }
        else if (highscore3<=ergebnis){
            bewertung.setText("super, du hast "+ ergebnis + " Punkte und damit dein drittbestes Ergebnis bisher erzielt!!");
            highscore3 = ergebnis;
        }
        else {
            bewertung.setText("schade, du hast " + ergebnis + " Punkte und damit den Higscore nicht gebrochen, probiere es weiter");
        }

        highscore.setText("Highscore: \n 1: " + highscore1 +"\n" + " 2: " + highscore2 + "\n" + " 3: " + highscore3);

        SharedPreferences sharedp = getSharedPreferences("your_prefs",MODE_PRIVATE);
        SharedPreferences.Editor editor = sharedp.edit();
        editor.putInt("highscore1", highscore1);
        editor.putInt("highscore2", highscore2);    
        editor.putInt("highscore3", highscore3);
        editor.commit();
    }
}
