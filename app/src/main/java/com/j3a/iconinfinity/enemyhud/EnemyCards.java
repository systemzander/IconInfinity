package com.j3a.iconinfinity.enemyhud;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

import androidx.fragment.app.Fragment;

import com.j3a.iconinfinity.R;

public class EnemyCards extends Fragment {

    public int[] card_id = {
            R.id.enemy_hide1,
            R.id.enemy_hide2,
            R.id.enemy_hide3,
            R.id.enemy_hide4,
            R.id.enemy_hide5,
            R.id.enemy_hide6,
            R.id.enemy_hide7,
            R.id.enemy_hide8,
            R.id.enemy_hide9,
            R.id.enemy_hide10};

    public ImageView enemycard1,
            enemycard2,
            enemycard3,
            enemycard4,
            enemycard5,
            enemycard6,
            enemycard7,
            enemycard8,
            enemycard9,
            enemycard10;

    public ImageView[] card = {
            enemycard1,
            enemycard2,
            enemycard3,
            enemycard4,
            enemycard5,
            enemycard6,
            enemycard7,
            enemycard8,
            enemycard9,
            enemycard10};

    int[] playerback = {
            R.drawable.domgehback,
            R.drawable.killuaback,
            R.drawable.valoback,
            R.drawable.sailorback,
            R.drawable.kenshinback,
            R.drawable.anicardback,
            R.drawable.memeback};

    public void setBack(ImageView c,int i){
        c.setImageResource(playerback[i]);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.enemy_cards, container, false);
    }

}
