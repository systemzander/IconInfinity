package com.j3a.iconinfinity;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

import androidx.fragment.app.Fragment;

public class Game extends Fragment {

    int[] player_pcard = {
            R.id.player_pcard1,
            R.id.player_pcard2,
            R.id.player_pcard3,
            R.id.player_pcard4,

    };

    ImageView card1, card2, card3, card4,
            aicard1, aicard2, aicard3, aicard4;
    ImageView[] player_card = {
            card1,
            card2,
            card3,
            card4};

    ImageView[] enemy_card = {
            aicard1,
            aicard2,
            aicard3,
            aicard4};

    int[] enemy_pcard = {
            R.id.enemy_pcard1,
            R.id.enemy_pcard2,
            R.id.enemy_pcard3,
            R.id.enemy_pcard4};

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.playing_field, container, false);
    }


}
