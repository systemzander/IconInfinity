package com.j3a.iconinfinity.cards;

import com.j3a.iconinfinity.R;

import java.util.Random;

public class Dogge {

    public String s;
    Random random = new Random();

    public int attack = 0, armor = 0, playerhp = 0, aihp = 0, energy = 0;
    public boolean playercard2 = false, aicard2 = false;

    public int[] drawcards = {
            R.drawable.domgeh0,
            R.drawable.domgeh1,
            R.drawable.domgeh2,
            R.drawable.domgeh3,
            R.drawable.domgeh4};


    public void setDesc(int card){
        switch (card){
            case 0:
                if (random.nextInt(20) == 1){
                    attack = 999999999;
                }else {
                    attack = 69;
                }
                armor = 50;
                energy = 1;
                break;
            case 1:
                attack = 0;
                armor = 0;
                energy = 0;
                if (s == "player")
                    playercard2 = true;
                if (s == "ai")
                    aicard2 = true;
                break;
            case 2:
                attack = 95;
                armor = 15;
                energy = 1;
                break;
            case 3:
                attack = 150;
                armor = 15;
                energy = 1;
                break;
            case 4:
                attack = 45;
                armor = 50;
                energy = 1;
                break;
        }
    }

}
