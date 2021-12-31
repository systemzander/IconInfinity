package com.j3a.iconinfinity;

import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;

import android.animation.Animator;
import android.animation.AnimatorListenerAdapter;
import android.animation.ObjectAnimator;
import android.content.Context;
import android.content.Intent;
import android.graphics.drawable.Drawable;
import android.os.Build;
import android.os.Bundle;
import android.os.Handler;
import android.util.Log;
import android.view.View;
import android.view.animation.AccelerateDecelerateInterpolator;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.view.animation.DecelerateInterpolator;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import com.j3a.iconinfinity.cards.AniCards;
import com.j3a.iconinfinity.cards.Dogge;
import com.j3a.iconinfinity.cards.Kenshin;
import com.j3a.iconinfinity.cards.Killua;
import com.j3a.iconinfinity.cards.Meme;
import com.j3a.iconinfinity.cards.Sailor;
import com.j3a.iconinfinity.cards.Valo;
import com.j3a.iconinfinity.enemyhud.EnemyCards;
import com.j3a.iconinfinity.playerhud.PlayerCards;

import java.util.Random;

public class GameActivity extends AppCompatActivity {

    Animation gameover_anim, aidraw, draw, player_bounce, field_bounce, attack;

    Handler handler = new Handler();
    FragmentTransaction ft;
    FragmentManager manager;
    Fragment clickedlayout;

    Context context;

    View inplay, gameover;
    ImageView clicked;
    TextView playerArmor, playerEnergy, playerHP;
    TextView enemyArmor, enemyEnergy, enemyHP;

    Button ready, clear,
            close, summon,
            exit, retry;

    PlayerCards player = new PlayerCards();
    EnemyCards enemy = new EnemyCards();

    Random random = new Random();
    Dogge dogge = new Dogge();
    Killua killua = new Killua();
    Valo valo = new Valo();
    Sailor sailor = new Sailor();
    Kenshin kenshin = new Kenshin();
    AniCards anicards = new AniCards();
    Meme meme = new Meme();
    SelectScreen deck = new SelectScreen();

    Game field = new Game();
    int aisummoned = 0, summoned = 0, tempPlayerCard = 0, tempFieldCard = 0, count = 0;



    int[] cardsDeck = dogge.drawcards;
    ImageView tempBackCard, tempCard1, tempCard2, tempCard3, tempCard4, tempCard5;
    ImageView enemyBack1,enemyBack2,enemyBack3,enemyBack4,enemyBack5,enemyBack6,enemyBack7,enemyBack8,enemyBack9,enemyBack10;

    ImageView[] tempPlayerCards = {tempCard1, tempCard2, tempCard3, tempCard4, tempCard5};
    ImageView[] enemyBackCards = {
            enemyBack1,
            enemyBack2,
            enemyBack3,
            enemyBack4,
            enemyBack5,
            enemyBack6,
            enemyBack7,
            enemyBack8,
            enemyBack9,
            enemyBack10};

    int[] enemyBackCardsID = {
            R.id.enemy_card1,
            R.id.enemy_card2,
            R.id.enemy_card3,
            R.id.enemy_card4,
            R.id.enemy_card5,
            R.id.enemy_card6,
            R.id.enemy_card7,
            R.id.enemy_card8,
            R.id.enemy_card9,
            R.id.enemy_card10};

    int[] tempPlayerCardsID={
            R.id.tempCard1,
            R.id.tempCard2,
            R.id.tempCard3,
            R.id.tempCard4,
            R.id.tempCard5};

    Boolean initial_draw = true;
    public int enemyDeck = deck.enemyDeck,
            playerDeck = deck.playerDeck;

    int curAttack, curArmor, curEnergy = 3, curHP = 450;
    int aiAttack, aiArmor, aiEnergy = 3, aiHP = 450;

    void showhideFrag(Fragment frag){
        ft = manager.beginTransaction();
        if(frag.isHidden()){
            ft.show(frag);
            ready.setEnabled(false);
            clear.setEnabled(false);
            summon.setEnabled(true);
            close.setEnabled(true);
        }
        else if (frag != null) {
            ft.hide(frag);
            ready.setEnabled(true);
            clear.setEnabled(true);
            summon.setEnabled(false);
            close.setEnabled(false);
        }
        else{
        }
        ft.addToBackStack(null);
        ft.commit();
    }

    int ChangeCards(int[] cards){
        return random.nextInt(cards.length);
    }

    void changeDeck(int deck){
        switch (deck){
            case 0:
                cardsDeck = dogge.drawcards;
                break;
            case 1:
                cardsDeck = killua.drawcards;
                break;
            case 2:
                cardsDeck = valo.drawcards;
                break;
            case 3:
                cardsDeck = sailor.drawcards;
                break;
            case 4:
                cardsDeck = kenshin.drawcards;
                break;
            case 5:
                cardsDeck = anicards.drawcards;
                break;
            case 6:
                cardsDeck = meme.drawcards;
                break;
        }
        for (int i = 0; i < tempPlayerCards.length; i++){
            tempPlayerCards[i].setImageResource(cardsDeck[i]);
        }
    }

    void setCards(ImageView c, int x){
        clearAnim();
        int d = ChangeCards(cardsDeck);
        switch (x){
            case 0:
                changeDeck(playerDeck);
                if (initial_draw){
                    if (count == 3){
                        initial_draw = false;
                    }
                    flipCard(c, cardsDeck[d]);
                    count++;
                }
                else {
                    c.setImageResource(cardsDeck[d]);
                    c.startAnimation(draw);
                }
                c.startAnimation(draw);
                break;
            case 1:
                changeDeck(enemyDeck);
                if (initial_draw){
                    if (count == 3){
                        initial_draw = false;
                    }
                    flipCard(c, cardsDeck[d]);
                    count++;
                }
                else {
                    c.setImageResource(cardsDeck[d]);
                    c.startAnimation(draw);
                }
                c.startAnimation(aidraw);
                break;
        }

    }

    void viewCards(ImageView c){
        c.setVisibility(View.VISIBLE);
    }

    void findCards(){
        for (int i = 0; i < tempPlayerCardsID.length; i++){
            tempPlayerCards[i] = findViewById(tempPlayerCardsID[i]);
        }
        for (int i = 0; i < player.card_id.length; i++){
            player.card[i] = findViewById(player.card_id[i]);
            player.card[i].setClickable(true);
        }
        for (int i = 0; i < enemy.card_id.length; i++){
            enemy.card[i] = findViewById(enemy.card_id[i]);
        }
        for (int i = 0; i < field.player_pcard.length; i++){
            field.player_card[i] = findViewById(field.player_pcard[i]);
        }
        for (int i = 0; i < field.enemy_pcard.length; i++){
            field.enemy_card[i] = findViewById(field.enemy_pcard[i]);
        }

        for (int i = 0; i < enemyBackCards.length; i++){
            enemyBackCards[i] = findViewById(enemyBackCardsID[i]);
        }

    }

    void hideCards(ImageView[] card, int x){
        card[x].setVisibility(View.GONE);

    }

    void checkCards(){
        for (int i = 0; i < player.card.length; i++){
            if (player.card[i].getVisibility() == View.GONE){
                viewCards(player.card[i]);
                setCards(player.card[i], 0);
                break;
            }
        }for (int i = 0; i < enemy.card.length; i++){
            if (enemy.card[i].getVisibility() == View.GONE){
                viewCards(enemyBackCards[i]);
                setCards(enemy.card[i], 1);
                break;
            }
        }
    }

    void SummonPlayerCard() {
        summon.setText("Summon");
        summon.setOnClickListener(view -> {
            clearAnim();

            if ((summoned < 4 & curEnergy == 0) & (dogge.energy == 0 || anicards.energy == 0 || kenshin.energy == 0 || killua.energy == 0 || meme.energy == 0 || sailor.energy == 0 || valo.energy == 0)){
                field.player_card[summoned].startAnimation(field_bounce);
                hideCards(player.card, tempPlayerCard);
                showhideFrag(clickedlayout);
                field.player_card[summoned].setImageDrawable(clicked.getDrawable());
                field.player_card[summoned].setVisibility(View.VISIBLE);
                checkAttArmEner(1, summoned, playerDeck, "player");
                //checkArmor(1, summoned, playerDeck);
                //checkAttack(1, summoned, playerDeck);
                //checkEnergy(1, summoned, playerDeck);
                summoned ++;
                checkPlayerFront();
                if (summoned != 0){
                    ready.setText("Ready");
                }
            }
            else if (summoned < 4 && curEnergy > 0){
                field.player_card[summoned].startAnimation(field_bounce);
                hideCards(player.card, tempPlayerCard);
                showhideFrag(clickedlayout);
                field.player_card[summoned].setImageDrawable(clicked.getDrawable());
                field.player_card[summoned].setVisibility(View.VISIBLE);
                checkAttArmEner(1, summoned, playerDeck, "player");
                //checkArmor(1, summoned, playerDeck);
                //checkAttack(1, summoned, playerDeck);
                //checkEnergy(1, summoned, playerDeck);
                summoned++;
                checkPlayerFront();
                if (summoned != 0) {
                    ready.setText("Ready");
                }
            }
        });

    }

    void setDesc(int x, int deck){
        switch (deck){
            case 0:
                changeDeck(playerDeck);
                for (int j = 0; j <dogge.drawcards.length;j++) {
                    if (checkImageResource(context, field.player_card[x], cardsDeck[j])) {
                        dogge.setDesc(j);
                    }
                }
                break;
            case 1:
                changeDeck(playerDeck);
                for (int j = 0; j <killua.drawcards.length;j++) {
                    if (checkImageResource(context, field.player_card[x], cardsDeck[j])) {
                        killua.setDesc(j);
                    }
                }
                break;
            case 2:
                changeDeck(playerDeck);
                for (int j = 0; j <valo.drawcards.length;j++) {
                    if (checkImageResource(context, field.player_card[x], cardsDeck[j])) {
                        valo.setDesc(j);
                    }
                }
                break;
            case 3:
                changeDeck(playerDeck);
                for (int j = 0; j <sailor.drawcards.length;j++) {
                    if (checkImageResource(context, field.player_card[x], cardsDeck[j])) {
                        sailor.setDesc(j);
                    }
                }
                break;
            case 4:
                changeDeck(playerDeck);
                for (int j = 0; j <kenshin.drawcards.length;j++) {
                    if (checkImageResource(context, field.player_card[x], cardsDeck[j])) {
                        kenshin.setDesc(j);
                    }
                }
                break;
            case 5:
                changeDeck(playerDeck);
                for (int j = 0; j <anicards.drawcards.length;j++) {
                    if (checkImageResource(context, field.player_card[x], cardsDeck[j])) {
                        anicards.setDesc(j);
                    }
                }
                break;
            case 6:
                changeDeck(playerDeck);
                for (int j = 0; j <meme.drawcards.length;j++) {
                    if (checkImageResource(context, field.player_card[x], cardsDeck[j])) {
                        meme.setDesc(j);
                    }
                }
                break;
        }
    }

    public static boolean checkImageResource(Context ctx, ImageView imageView,
                                             int imageResource) {
        boolean result = false;

        if (ctx != null && imageView != null && imageView.getDrawable() != null) {
            Drawable.ConstantState constantState;

            if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
                constantState = ctx.getResources()
                        .getDrawable(imageResource, ctx.getTheme())
                        .getConstantState();
            } else {
                constantState = ctx.getResources().getDrawable(imageResource)
                        .getConstantState();
            }

            if (imageView.getDrawable().getConstantState() == constantState) {
                result = true;
            }
        }

        return result;
    }

    void checkAttArmEner(int x, int tempFieldCard, int deck, String s){
        setDesc(tempFieldCard, deck);
        switch (s) {
            case "player":
                switch (x) {
                    case 1:
                        switch (deck) {
                            case 0:
                                curEnergy -= dogge.energy;
                                curArmor += dogge.armor;
                                curAttack += dogge.attack;
                                break;
                            case 1:
                                curEnergy -= killua.energy;
                                curArmor += killua.armor;
                                curAttack += killua.attack;
                                break;
                            case 2:
                                curEnergy -= valo.energy;
                                curArmor += valo.armor;
                                curAttack += valo.attack;
                                break;
                            case 3:
                                curEnergy -= sailor.energy;
                                curArmor += sailor.armor;
                                curAttack += sailor.attack;
                                break;
                            case 4:
                                curEnergy -= kenshin.energy;
                                curArmor += kenshin.armor;
                                curAttack += kenshin.attack;
                                break;
                            case 5:
                                curEnergy -= anicards.energy;
                                curArmor += anicards.armor;
                                curAttack += anicards.attack;
                                break;
                            case 6:
                                curEnergy -= meme.energy;
                                curArmor += meme.armor;
                                curAttack += meme.attack;
                                break;
                        }
                        break;
                    case 0:
                        switch (deck) {
                            case 0:
                                curEnergy += dogge.energy;
                                curArmor -= dogge.armor;
                                curAttack -= dogge.attack;
                                break;
                            case 1:
                                curEnergy += killua.energy;
                                curArmor -= killua.armor;
                                curAttack -= killua.attack;
                                break;
                            case 2:
                                curEnergy += valo.energy;
                                curArmor -= valo.armor;
                                curAttack -= valo.attack;
                                break;
                            case 3:
                                curEnergy += sailor.energy;
                                curArmor -= sailor.armor;
                                curAttack -= sailor.attack;
                                break;
                            case 4:
                                curEnergy += kenshin.energy;
                                curArmor -= kenshin.armor;
                                curAttack -= kenshin.attack;
                                break;
                            case 5:
                                curEnergy += anicards.energy;
                                curArmor -= anicards.armor;
                                curAttack -= anicards.attack;
                                break;
                            case 6:
                                curEnergy += meme.energy;
                                curArmor -= meme.armor;
                                curAttack -= meme.attack;
                                break;
                        }
                        break;
                }
                break;
            case "ai":
                switch (x) {
                    case 1:
                        switch (deck) {
                            case 0:
                                aiEnergy -= dogge.energy;
                                aiArmor += dogge.armor;
                                aiAttack += dogge.attack;
                                break;
                            case 1:
                                aiEnergy -= killua.energy;
                                aiArmor += killua.armor;
                                aiAttack += killua.attack;
                                break;
                            case 2:
                                aiEnergy -= valo.energy;
                                aiArmor += valo.armor;
                                aiAttack += valo.attack;
                                break;
                            case 3:
                                aiEnergy -= sailor.energy;
                                aiArmor += sailor.armor;
                                aiAttack += sailor.attack;
                                break;
                            case 4:
                                aiEnergy -= kenshin.energy;
                                aiArmor += kenshin.armor;
                                aiAttack += kenshin.attack;
                                break;
                            case 5:
                                aiEnergy -= anicards.energy;
                                aiArmor += anicards.armor;
                                aiAttack += anicards.attack;
                                break;
                            case 6:
                                aiEnergy -= meme.energy;
                                aiArmor += meme.armor;
                                aiAttack += meme.attack;
                                break;
                        }
                        break;
                    case 0:
                        switch (deck) {
                            case 0:
                                aiEnergy += dogge.energy;
                                aiArmor -= dogge.armor;
                                aiAttack -= dogge.attack;
                                break;
                            case 1:
                                aiEnergy += killua.energy;
                                aiArmor -= killua.armor;
                                aiAttack -= killua.attack;
                                break;
                            case 2:
                                aiEnergy += valo.energy;
                                aiArmor -= valo.armor;
                                aiAttack -= valo.attack;
                                break;
                            case 3:
                                aiEnergy += sailor.energy;
                                aiArmor -= sailor.armor;
                                aiAttack -= sailor.attack;
                                break;
                            case 4:
                                aiEnergy += kenshin.energy;
                                aiArmor -= kenshin.armor;
                                aiAttack -= kenshin.attack;
                                break;
                            case 5:
                                aiEnergy += anicards.energy;
                                aiArmor -= anicards.armor;
                                aiAttack -= anicards.attack;
                                break;
                            case 6:
                                aiEnergy += meme.energy;
                                aiArmor -= meme.armor;
                                aiAttack -= meme.attack;
                                break;
                        }
                        break;
                }
                break;
        }

        if (deck == enemyDeck && deck != playerDeck) {
            enemyArmor.setText("Energy: " + aiArmor);
        } else {
            playerEnergy.setText("Energy: " + curEnergy);
            playerArmor.setText("Armor: " + curArmor);
        }
    }

    void retrieveCard(){
        clearAnim();
        for (int i = 0; i < player.card.length; i++){
            if (player.card[i].getVisibility() == View.GONE){
                tempPlayerCard = i;
                player.card[i].setImageDrawable(clicked.getDrawable());
                player.card[tempPlayerCard].setVisibility(View.VISIBLE);
                player.card[tempPlayerCard].startAnimation(player_bounce);
                break;
            }
        }
    }

    void clearAnim(){
        for (int i=0; i<player.card.length; i++){
            player.card[i].clearAnimation();
            if (i < 4){
                field.player_card[i].clearAnimation();
            }
        }
    }

    void checkFieldPlayerFront(){
        for (int i=0; i < summoned; i++){
            if(field.player_card[i].getVisibility() == View.INVISIBLE && i+1 != 4 && field.player_card[i+1].getVisibility() == View.VISIBLE) {
                field.player_card[i+1].setVisibility(View.INVISIBLE);
                field.player_card[i].setImageDrawable(field.player_card[i+1].getDrawable());
                field.player_card[i].setVisibility(View.VISIBLE);
            }
        }
    }
    void checkFieldEnemyFront(){
        for (int i=0; i < aisummoned; i++){
            if(field.enemy_card[i].getVisibility() == View.INVISIBLE && i+1 != 4 && field.enemy_card[i+1].getVisibility() == View.VISIBLE) {
                field.enemy_card[i+1].setVisibility(View.INVISIBLE);
                field.enemy_card[i].setImageDrawable(field.enemy_card[i+1].getDrawable());
                field.enemy_card[i].setVisibility(View.VISIBLE);
            }
        }
    }

    void checkPlayerFront(){
        for (int i=0; i < player.card.length; i++){
            if(player.card[i].getVisibility() == View.GONE && i+1 != 10 && player.card[i+1].getVisibility() == View.VISIBLE) {
                player.card[i+1].setVisibility(View.GONE);
                player.card[i].setImageDrawable(player.card[i+1].getDrawable());
                player.card[i].setVisibility(View.VISIBLE);
            }
        }
    }
    void checkEnemyFront(){
        for (int i=0; i < enemy.card.length; i++){
            if(enemy.card[i].getVisibility() == View.GONE && i+1 != 10 && enemy.card[i+1].getVisibility() == View.VISIBLE) {
                enemy.card[i+1].setVisibility(View.GONE);
                enemy.card[i].setImageDrawable(enemy.card[i+1].getDrawable());
                enemy.card[i].setVisibility(View.VISIBLE);
            }
        }
    }

    void ChangePlayerCard(){
        summon.setText("Change");
        summon.setOnClickListener(view -> {
            player.card[tempPlayerCard].clearAnimation();
            hideCards(field.player_card, tempFieldCard);
            showhideFrag(clickedlayout);
            field.player_card[tempFieldCard].setVisibility(View.INVISIBLE);
            retrieveCard();
            checkAttArmEner(0, tempFieldCard, playerDeck, "player");
            //checkArmor(0, tempFieldCard, playerDeck);
            //checkAttack(0, tempFieldCard, playerDeck);
            //checkEnergy(0, tempFieldCard, playerDeck);
            checkFieldPlayerFront();
            summoned --;
            if (summoned == 0){
                ready.setText("Skip");
            }
        });
    }

    void onClick(ImageView[] card, ImageView clicked, int c){
        card[c].setOnClickListener(v -> {
            clicked.setImageDrawable(card[c].getDrawable());
            showhideFrag(clickedlayout);
            if (card == player.card){
                tempPlayerCard = c;
                SummonPlayerCard();
            }
            if (card == field.player_card){
                tempFieldCard = c;
                ChangePlayerCard();
            }
        });
    }

    void flipCard(ImageView c,int cards ){
        final ObjectAnimator oa1 = ObjectAnimator.ofFloat(c, "scaleX", 1f, 0f);
        final ObjectAnimator oa2 = ObjectAnimator.ofFloat(c, "scaleX", 0f, 1f);
        oa1.setInterpolator(new DecelerateInterpolator());
        oa2.setInterpolator(new AccelerateDecelerateInterpolator());
        oa1.addListener(new AnimatorListenerAdapter() {
            @Override
            public void onAnimationEnd(Animator animation) {
                super.onAnimationEnd(animation);
                oa2.start();
                c.setImageResource(cards);
            }
        });
        oa1.start();
    }

    void delay(int ms, String text){
        handler.postDelayed(() -> {
            switch (text) {
                case "flipcard":
                    for (int i = 0; i < player.card.length; i++) {
                        setCards(player.card[i], 0);
                    }
                    for (int i = 0; i < enemy.card.length; i++) {
                        setCards(enemy.card[i], 1);
                    }
                    break;
                case "attack":
                    if (field.player_card[0].getVisibility() == View.VISIBLE) {
                        for (int i = 0; i < summoned; i++) {
                            field.player_card[i].clearAnimation();
                            field.player_card[i].setVisibility(View.INVISIBLE);
                            checkPlayerFront();
                        }
                    }
                    if (field.enemy_card[0].getVisibility() == View.VISIBLE) {
                        for (int i = 0; i < aisummoned + 1; i++) {
                            field.enemy_card[i].clearAnimation();
                            field.enemy_card[i].setVisibility(View.INVISIBLE);
                            checkFieldEnemyFront();
                        }
                    }
                    readyAttack();
            }
        }, ms*10);
    }

    void aiMove(){

        for (int i = 0; i < 4; i++){
            if(aisummoned < 4 && aiEnergy != 0) {
                int attack = random.nextInt(5);
                int tempEnemyCard = random.nextInt(enemy.card.length);

                if (attack == 0 || attack == 1 || attack == 3 || attack == 4) {

                    if (aiEnergy == 0 && (dogge.energy == 0 || anicards.energy == 0 || kenshin.energy == 0 || killua.energy == 0 || meme.energy == 0 || sailor.energy == 0 || valo.energy == 0)) {
                        field.enemy_card[aisummoned].startAnimation(player_bounce);
                        hideCards(enemyBackCards, tempPlayerCard);
                        field.enemy_card[aisummoned].setImageDrawable(enemy.card[tempEnemyCard].getDrawable());
                        field.enemy_card[aisummoned].setVisibility(View.VISIBLE);
                        checkAttArmEner(1, aisummoned, enemyDeck, "ai");
                        aisummoned++;
                        checkEnemyFront();
                    }
                    else if (enemyBackCards[tempEnemyCard].getVisibility() == View.VISIBLE) {
                        hideCards(enemyBackCards, tempEnemyCard);
                        field.enemy_card[aisummoned].setImageDrawable(enemy.card[tempEnemyCard].getDrawable());
                        field.enemy_card[aisummoned].startAnimation(player_bounce);
                        field.enemy_card[aisummoned].setVisibility(View.VISIBLE);
                        checkAttArmEner(1, aisummoned, enemyDeck, "ai");
                        aisummoned++;
                        checkEnemyFront();
                    }
                }
            }
        }

    }

    void hpcheck(){
        if (curHP <= 0) {
            curHP = 0;
            gameover.startAnimation(gameover_anim);
            showhideFrag(clickedlayout);
            playerHP.setText("HP: "+curHP);
            inplay.setVisibility(View.GONE);
            gameover.setVisibility(View.VISIBLE);
            if (inplay.getVisibility() == View.GONE){
                ready.setEnabled(false);
                clear.setEnabled(false);
                summon.setEnabled(false);
                close.setEnabled(false);
            }
        }
        if (aiHP <= 0) {
            aiHP = 0;
            gameover.startAnimation(gameover_anim);
            showhideFrag(clickedlayout);
            enemyHP.setText("HP: "+aiHP);
            inplay.setVisibility(View.GONE);
            gameover.setVisibility(View.VISIBLE);
            if (inplay.getVisibility() == View.GONE){
                ready.setEnabled(false);
                clear.setEnabled(false);
                summon.setEnabled(false);
                close.setEnabled(false);
            }
        }
    }

    void playerattack(){
        int temp;
        temp = aiArmor;
        aiArmor -= curAttack;
        curAttack -= temp;
        if(aiArmor <= 0){
            aiArmor = 0;
            aiHP -= curAttack;
        }
        curAttack = 0;
        if (enemyDeck == 3)
            aiHP += sailor.aihp;
        if (enemyDeck == 2)
            aiHP += valo.aihp;
        enemyHP.setText("HP: "+aiHP);
        enemyEnergy.setText("Energy: ??");
        enemyArmor.setText("Armor: "+aiArmor);
    }
    void aiattack(){
        int temp;
        temp = curArmor;
        curArmor -= aiAttack;
        aiAttack -= temp;
        if(curArmor <= 0){
            curArmor = 0;
            curHP -= aiAttack;
            hpcheck();
        }
        aiAttack = 0;
        if (playerDeck == 3)
            curHP += sailor.playerhp;
        if (playerDeck == 2)
            curHP += valo.playerhp;
        playerHP.setText("HP: "+curHP);
        playerEnergy.setText("Energy: "+curEnergy);
        playerArmor.setText("Armor: "+curArmor);
    }

    void resetStats(){
        aisummoned = 0;
        summoned = 0;
        curArmor = 0;
        curAttack = 0;
        aiAttack = 0;
        aiArmor = 0;

        anicards.aihp = 0;
        dogge.aihp = 0;
        kenshin.aihp = 0;
        killua.aihp = 0;
        meme.aihp = 0;
        sailor.aihp = 0;
        valo.aihp = 0;

        anicards.playerhp = 0;
        dogge.playerhp = 0;
        kenshin.playerhp = 0;
        killua.playerhp = 0;
        meme.playerhp = 0;
        sailor.playerhp = 0;
        valo.playerhp = 0;

        enemyHP.setText("HP: "+aiHP);
        enemyEnergy.setText("Energy: ??");
        enemyArmor.setText("Armor: "+aiArmor);
        playerHP.setText("HP: "+curHP);
        playerEnergy.setText("Energy: "+curEnergy);
        playerArmor.setText("Armor: "+curArmor);
        dogge.playercard2 = false;
        anicards.playertimes2 = false;
        anicards.aitimes2 = false;
        killua.playertimes2 = false;
        killua.playertimes2 = false;
        anicards.s = " ";
        dogge.s = " ";
        kenshin.s = " ";
        killua.s = " ";
        meme.s = " ";
        sailor.s = " ";
        valo.s = " ";
    }

    void readyAttack(){
        int attack = random.nextInt(1);
        if (killua.playertimes2 == true || anicards.playertimes2 == true) {
            curAttack *= 2;
        }
        if (dogge.playercard2 == true) {
            aiArmor -= 200;
            aiAttack -= 150;
        }
        if(field.player_card[0].getVisibility() == View.VISIBLE) {
            for (int i = 0; i < summoned; i++) {
                field.player_card[i].clearAnimation();
                field.player_card[i].setVisibility(View.INVISIBLE);
                checkPlayerFront();
            }
        }
        if(field.enemy_card[0].getVisibility() == View.VISIBLE) {
            for (int i = 0; i < summoned; i++) {
                field.enemy_card[i].clearAnimation();
                field.enemy_card[i].setVisibility(View.INVISIBLE);
                checkPlayerFront();
            }
        }
        playerattack();
        aiattack();
        if (summoned >= 2)
            curEnergy += 2;
        if (summoned >= 0 && summoned <= 1)
            curEnergy += 3;
        if (aisummoned >= 2)
            aiEnergy += 2;
        if (aisummoned >= 0 && summoned <= 1)
            aiEnergy += 3;

        if (curEnergy >= 10)
            curEnergy = 10;
        if (aiEnergy >= 10)
            aiEnergy = 10;

        ready.setText("Skip");
        hpcheck();
        checkCards();
        resetStats();
    }

    void init(){

        context = GameActivity.this;

        enemyDeck = deck.enemyDeck;
        playerDeck = deck.playerDeck;

        manager = getSupportFragmentManager();
        clickedlayout = manager.findFragmentById(R.id.clickedhud_hud);

        clicked = findViewById(R.id.clicked);
        tempBackCard =findViewById(R.id.tempCard);

        inplay = findViewById(R.id.inplay);
        gameover = findViewById(R.id.gameover);

        close = findViewById(R.id.close);
        summon = findViewById(R.id.summon);
        clear = findViewById(R.id.clear);
        ready =  findViewById(R.id.ready);
        exit = findViewById(R.id.exit);
        retry = findViewById(R.id.retry);

        playerArmor = findViewById(R.id.Player_Armor);
        playerEnergy = findViewById(R.id.Player_Energy);
        playerHP = findViewById(R.id.Player_HP);

        enemyArmor = findViewById(R.id.Enemy_Armor);
        enemyEnergy = findViewById(R.id.Enemy_Energy);
        enemyHP = findViewById(R.id.Enemy_HP);

        findCards();

        player.setBack(tempBackCard, playerDeck);

        gameover_anim = AnimationUtils.loadAnimation(getApplicationContext(), R.anim.gameover);
        draw = AnimationUtils.loadAnimation(getApplicationContext(), R.anim.draw);
        aidraw = AnimationUtils.loadAnimation(getApplicationContext(), R.anim.aidraw);
        player_bounce = AnimationUtils.loadAnimation(getApplicationContext(), R.anim.player_bounce);
        field_bounce = AnimationUtils.loadAnimation(getApplicationContext(), R.anim.field_bounce);
        attack = AnimationUtils.loadAnimation(getApplicationContext(), R.anim.attack);

        ready.setText("Skip");
        playerEnergy.setText("Energy: "+curEnergy);
        enemyEnergy.setText("Energy: ??");

        for (int i = 0; i < player.card.length; i++){
            player.setBack(player.card[i], playerDeck);
            enemy.setBack(enemyBackCards[i], enemyDeck);
        }

        for (int i = 0; i < player.card.length; i++){
            onClick(player.card, clicked, i);
            if(i >= 4){
                hideCards(player.card, i);
                hideCards(enemyBackCards, i);
            }
        }

        for (int i = 0; i < field.player_card.length; i++){
            onClick(field.player_card, clicked, i);
        }

        changeDeck(playerDeck);

        showhideFrag(clickedlayout);

        delay(25, "flipcard");

    }

    void click(){

        close.setOnClickListener(v -> {
            showhideFrag(clickedlayout);
        });

        clear.setOnClickListener(v -> {
            clearAnim();
            if(field.player_card[0].getVisibility() == View.VISIBLE) {
                for (int i = 0; i < summoned; i++) {
                    field.player_card[i].setVisibility(View.INVISIBLE);
                    for (int j = 0; j < player.card.length; j++) {
                        if (player.card[j].getVisibility() == View.GONE) {
                            if(tempBackCard.getDrawable() != field.player_card[0].getDrawable()) {
                                player.card[j].setImageDrawable(field.player_card[i].getDrawable());
                                player.card[j].setVisibility(View.VISIBLE);
                                player.card[j].startAnimation(player_bounce);
                                checkAttArmEner(0, j, playerDeck, "player");
                                break;
                            }
                        }
                    }
                }
                aisummoned = 0;
                summoned = 0;
                ready.setText("Skip");
                playerArmor.setText("Armor: "+curArmor);

            }


        });

        ready.setOnClickListener(v -> {
            aiMove();
            delay(100, "attack");
        });

        exit.setOnClickListener(v -> {
            moveTaskToBack(true);
            android.os.Process.killProcess(android.os.Process.myPid());
            System.exit(1);
        });

        retry.setOnClickListener(v -> {
            Intent intent = new Intent(GameActivity.this, SelectScreen.class);
            startActivity(intent);
        });

    }

    public void onBackPressed() {
        Intent intent = new Intent(GameActivity.this, SelectScreen.class);
        startActivity(intent);

    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.game_stitch);

        init();
        Log.i("Start", "Processes have initialized!");

    }

    protected void onStart(){
        super.onStart();

        click();


    }

    protected void onResume(){
        super.onResume();

        View decorView = getWindow().getDecorView();
        decorView.setSystemUiVisibility(
                View.SYSTEM_UI_FLAG_LAYOUT_STABLE
                        | View.SYSTEM_UI_FLAG_LAYOUT_HIDE_NAVIGATION
                        | View.SYSTEM_UI_FLAG_LAYOUT_FULLSCREEN
                        | View.SYSTEM_UI_FLAG_HIDE_NAVIGATION
                        | View.SYSTEM_UI_FLAG_FULLSCREEN
                        | View.SYSTEM_UI_FLAG_IMMERSIVE_STICKY);

    }

}