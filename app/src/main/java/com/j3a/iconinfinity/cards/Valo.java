package com.j3a.iconinfinity.cards;

import com.j3a.iconinfinity.R;

import java.util.Random;

public class Valo {

    Random random = new Random();

    public String s;

    public int attack = 0, armor = 0, playerhp = 0, aihp = 0, energy = 0;

    public int[] drawcards = {
            R.drawable.valo0,
            R.drawable.valo1,
            R.drawable.valo2,
            R.drawable.valo3,
            R.drawable.valo4};

    public void setDesc(int card){
        switch (card){
            case 0:
                attack = 120;
                armor = 25;
                energy = 1;
                break;
            case 1:
                attack = 150;
                armor = 25;
                energy = 0;
                break;
            case 2:
                attack = 125;
                armor = 20;
                energy = 1;
                break;
            case 3:
                attack = 25;
                armor = 10;
                energy = 0;
                if (s == "player")
                    playerhp = 100;
                if (s == "ai")
                    aihp = 100;
                break;
            case 4:
                attack = 160;
                armor = 20;
                energy = 1;
                break;
        }
    }

}
