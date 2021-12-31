package com.j3a.iconinfinity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import java.util.Random;

public class SelectScreen extends AppCompatActivity {

    private ImageButton hxh, dogge, sailormoon, memeface, anime, valo, samurai, playerready1, aiready1;
    private ImageView player1, ai;
    boolean playerready = false, aiready = false;

    Random random = new Random();
    public static int playerDeck, enemyDeck;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_second);

        playerready1 = (ImageButton) findViewById(R.id.playerready);
        aiready1 = (ImageButton) findViewById(R.id.aiready);
        player1 = (ImageView) findViewById(R.id.player1);
        ai = (ImageView) findViewById(R.id.ai);

        playerDeck = random.nextInt(6);
        enemyDeck = random.nextInt(6);

        hxh = (ImageButton) findViewById(R.id.hxh);
        player1 = (ImageView) findViewById(R.id.player1);

        hxh.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (playerready == true){
                    ai.setImageResource(R.mipmap.killuachar);
                    enemyDeck = 1;
                }
                else{
                    player1.setImageResource(R.mipmap.killuachar);
                    playerDeck = 1;
                }
            }
        });

        sailormoon = (ImageButton) findViewById(R.id.sailorlogo);
        sailormoon.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (playerready == true){
                    ai.setImageResource(R.mipmap.sailormoonchar);
                    enemyDeck = 3;
                }
                else{
                    player1.setImageResource(R.mipmap.sailormoonchar);
                    playerDeck = 3;
                }

            }
        });

        dogge = (ImageButton) findViewById(R.id.doggelogo);
        dogge.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (playerready == true){
                    ai.setImageResource(R.mipmap.doggechar);
                    enemyDeck = 0;
                }
                else{
                    player1.setImageResource(R.mipmap.doggechar);
                    playerDeck = 0;
                }

            }
        });

        memeface = (ImageButton) findViewById(R.id.memelord);
        memeface.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (playerready == true){
                    ai.setImageResource(R.mipmap.trollchar);
                    enemyDeck = 6;
                }
                else{
                    player1.setImageResource(R.mipmap.trollchar);
                    playerDeck = 6;
                }

            }
        });

        anime = (ImageButton) findViewById(R.id.anime);
        anime.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (playerready == true){
                    ai.setImageResource(R.mipmap.itachichar);
                    enemyDeck = 5;
                }
                else{
                    player1.setImageResource(R.mipmap.itachichar);
                    playerDeck = 5;
                }

            }
        });

        valo = (ImageButton) findViewById(R.id.valorant);
        valo.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (playerready == true){
                    ai.setImageResource(R.mipmap.jettchar);
                    enemyDeck = 2;
                }
                else{
                    player1.setImageResource(R.mipmap.jettchar);
                    playerDeck = 2;
                }

            }
        });

        samurai = (ImageButton) findViewById(R.id.samurai);
        samurai.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                if (playerready == true){
                    ai.setImageResource(R.mipmap.kenshinchar);
                    enemyDeck = 4;
                }
                else{
                    player1.setImageResource(R.mipmap.kenshinchar);
                    playerDeck = 4;
                }

            }
        });

        playerready1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                playerready = true;
            }
        });
        aiready1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                Intent intent = new Intent(SelectScreen.this, GameActivity.class);
                startActivity(intent);

            }
        });

    }

    protected void onResume(){
        super.onResume();

        View decorView = getWindow().getDecorView();
        decorView.setSystemUiVisibility(
                View.SYSTEM_UI_FLAG_LAYOUT_STABLE
                        | View.SYSTEM_UI_FLAG_LAYOUT_HIDE_NAVIGATION
                        | View.SYSTEM_UI_FLAG_LAYOUT_FULLSCREEN
                        | View.SYSTEM_UI_FLAG_HIDE_NAVIGATION
                        | View.SYSTEM_UI_FLAG_FULLSCREEN
                        | View.SYSTEM_UI_FLAG_IMMERSIVE_STICKY);

    }

}
