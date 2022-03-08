package com.j3a.iconinfinity.cards;

import com.j3a.iconinfinity.R;
import com.j3a.iconinfinity.SelectScreen;

public class Sailor {

    public Boolean player = false;
    public Boolean ai = false;

    public int[] drawcards = {
            R.drawable.sailor0,
            R.drawable.sailor1,
            R.drawable.sailor2,
            R.drawable.sailor3,
            R.drawable.sailor4};

    public int attack = 0, armor = 0, playerhp = 0, aihp = 0, energy = 0;

    public void setDesc(int card){
        switch (card){
            case 0:
                attack = 40;
                armor = 25;
                energy = 0;
                break;
            case 1:
                attack = 60;
                armor = 35;
                energy = 1;
                break;
            case 2:
                attack = 20;
                armor = 10;
                energy = 0;
                if (player == true)
                    playerhp = 200;
                if (ai == true)
                    aihp = 200;
                break;
            case 3:
                attack = 80;
                armor = 40;
                energy = 1;
                break;
            case 4:
                attack = 30;
                armor = 20;
                energy = 0;
                break;
        }
    }

}
