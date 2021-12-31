package com.j3a.iconinfinity.cards;

import com.j3a.iconinfinity.R;

public class AniCards {

    public String s;

    public Boolean playertimes2 = false, aitimes2 = false;

    public int[] drawcards = {
            R.drawable.anicard0,
            R.drawable.anicard1,
            R.drawable.anicard2,
            R.drawable.anicard3,
            R.drawable.anicard4};

    public int attack = 0, armor = 0, playerhp = 0, aihp = 0, energy = 0;

    public void setDesc(int card){
        switch (card){
            case 0:
                attack = 0;
                armor = 150;
                energy = 1;
                break;
            case 1:
                attack = 105;
                armor = 10;
                energy = 1;
                break;
            case 2:
                attack = 85;
                armor = 35;
                energy = 1;
                break;
            case 3:
                attack = 0;
                armor = 0;
                energy = 0;
                if (s == "player")
                    playertimes2 = true;
                if (s == "ai")
                    aitimes2 = true;
                break;
            case 4:
                attack = 125;
                armor = 30;
                energy = 1;
                break;
        }
    }

}
