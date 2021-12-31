package com.j3a.iconinfinity.cards;

import com.j3a.iconinfinity.R;

public class Killua {

    public String s;

    public Boolean playertimes2 = false, aitimes2 = false;

    public int attack = 0, armor = 0, playerhp = 0, aihp = 0, energy = 0;

    public int[] drawcards = {
            R.drawable.killua0,
            R.drawable.killua1,
            R.drawable.killua2,
            R.drawable.killua3,
            R.drawable.killua4};

    public void setDesc(int card){
        switch (card){
            case 0:
                attack = 95;
                armor = 25;
                energy = 1;
                break;
            case 1:
                attack = 30;
                armor = 100;
                energy = 1;
                if (s == "player")
                    playertimes2 = true;
                if (s == "ai")
                    aitimes2 = true;
                break;
            case 2:
                attack = 0;
                armor = 100;
                energy = 0;
                break;
            case 3:
                attack = 150;
                armor = 15;
                energy = 1;
                break;
            case 4:
                attack = 55;
                armor = 50;
                energy = 1;
                break;
        }
    }

}
