package com.j3a.iconinfinity.cards;

import com.j3a.iconinfinity.R;

import java.util.Random;

public class Meme {

    public String s;

    public int[] drawcards = {
            R.drawable.meme0,
            R.drawable.meme1,
            R.drawable.meme2,
            R.drawable.meme3,
            R.drawable.meme4};

    Random random = new Random();

    public int attack = 0, armor = 0, playerhp = 0, aihp = 0, energy = 0;

    public void setDesc(int card){
        switch (card){
            case 0:
                attack = 0;
                armor = 69;
                energy = 0;
                break;
            case 1:
                attack = 80;
                armor = 60;
                if (random.nextInt(20) == 6)
                    attack *= 2;
                energy = 1;
                break;
            case 2:
                attack = 100;
                armor = 45;
                energy = 1;
                break;
            case 3:
                attack = 100;
                armor = 45;
                energy = 1;
                break;
            case 4:
                attack = 150;
                armor = 95;
                energy = 1;
                break;
        }
    }

}
