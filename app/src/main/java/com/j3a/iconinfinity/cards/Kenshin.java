package com.j3a.iconinfinity.cards;

import com.j3a.iconinfinity.R;

public class Kenshin {

    public String s;

    public int[] drawcards = {
            R.drawable.kenshin0,
            R.drawable.kenshin1,
            R.drawable.kenshin2,
            R.drawable.kenshin3,
            R.drawable.kenshin4};

    public int attack = 0, armor = 0, playerhp = 0, aihp = 0, energy = 0;

    public void setDesc(int card){
        switch (card){
            case 0:
                attack = 0;
                armor = 100;
                energy = 0;
                break;
            case 1:
                attack = 45;
                armor = 15;
                energy = 1;
                break;
            case 2:
                attack = 150;
                armor = 10;
                energy = 1;
                break;
            case 3:
                attack = 0;
                armor = 50;
                energy = 0;
                break;
            case 4:
                attack = 95;
                armor = 20;
                energy = 1;
                break;
        }
    }

}
