package com.j3a.iconinfinity.playerhud;

import android.graphics.drawable.Drawable;
import android.os.Bundle;

import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

import com.j3a.iconinfinity.R;

public class PlayerCards extends Fragment {

    public int[] play_card = {
            R.id.player_pcard1,
            R.id.player_pcard2,
            R.id.player_pcard3,
            R.id.player_pcard4};

    public int[] card_id = {
            R.id.player_card1,
            R.id.player_card2,
            R.id.player_card3,
            R.id.player_card4,
            R.id.player_card5,
            R.id.player_card6,
            R.id.player_card7,
            R.id.player_card8,
            R.id.player_card9,
            R.id.player_card10};

    public ImageView playercard1,
            playercard2,
            playercard3,
            playercard4,
            playercard5,
            playercard6,
            playercard7,
            playercard8,
            playercard9,
            playercard10;
    public ImageView[] card = {
            playercard1,
            playercard2,
            playercard3,
            playercard4,
            playercard5,
            playercard6,
            playercard7,
            playercard8,
            playercard9,
            playercard10};

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

    public Drawable getBack(int i){
        return getResources().getDrawable(playerback[i]);
    }



    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.player_cards, container, false);

    }
}