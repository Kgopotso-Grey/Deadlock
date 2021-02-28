package com.example.myapplication;


import android.os.Build;
import android.os.Bundle;
import android.util.Log;
import android.widget.Button;
import android.widget.ImageView;

import androidx.annotation.RequiresApi;
import androidx.appcompat.app.AppCompatActivity;

public class Resolved extends AppCompatActivity {
    int counter =1;
    int i; private static final int[] IdsNorth = {
            R.id.northpink, R.id.northpink2,R.id.northpink3};
    private static final int[] IdsSouth = {
            R.id.southBlue, R.id.southPink,R.id.southBlue2,R.id.southPink2};
    private static final int[] IdsEast = {
            R.id.blackEast, R.id.greyEast};
    private static final int[] IdsWest = {
            R.id.westblack, R.id.westgrey};

    private Button[] northNumber = new Button[IdsNorth.length];
    private Button[] southNumber = new Button[IdsSouth.length];
    private Button[] EastNumber = new Button[IdsEast.length];
    private Button[] westNumber = new Button[IdsWest.length];

    float northTrans = -1900f;//for north
    float southTrans = 1900f;
    float westTrans = -1500f;
    float eastTrans =1510f;
    long Duration=6000l;//static

    private ImageView light1,light2,light3,light4,
            light5,light6,light7,light8;
    @RequiresApi(api = Build.VERSION_CODES.LOLLIPOP)
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_resolved);
        light1 =(ImageView)findViewById(R.id.green1);light2 =(ImageView)findViewById(R.id.red1);

        light3 =(ImageView)findViewById(R.id.green2);light4 =(ImageView)findViewById(R.id.red2);

        light5 =(ImageView)findViewById(R.id.green3);light6 =(ImageView)findViewById(R.id.red3);

        light7 =(ImageView)findViewById(R.id.green4);light8 =(ImageView)findViewById(R.id.red4);
                                                run();
    }

    @RequiresApi(api = Build.VERSION_CODES.LOLLIPOP)
    private void run() {

        if(counter == 1){
            executeNorth(IdsNorth,northNumber,northTrans);
            executeSouth(IdsSouth,southNumber,southTrans);

        } counter++;
         if(counter ==2){
            //Deactivate 1&2
            light1.animate().alpha(0).setStartDelay(6500);light2
                     .animate().alpha(1).setStartDelay(6500);
            light3.animate().alpha(0).setStartDelay(6500);light4
                     .animate().alpha(1).setStartDelay(6500);
            //Activate 3&4
            light5.animate().alpha(1).setStartDelay(6500);light6
                     .animate().alpha(0).setStartDelay(6500);
            light7.animate().alpha(1).setStartDelay(6500);light8
                     .animate().alpha(0).setStartDelay(6500);
            executeWest(IdsWest,westNumber,westTrans);
            executeEast(IdsEast,EastNumber,eastTrans);
        }
    }

    @RequiresApi(api = Build.VERSION_CODES.LOLLIPOP)
    public void executeSouth(int[] southIds, Button[] numSouth, float southTrans) {
        for (i = 0; i < southIds.length; i++) {
            //formal var
            numSouth[i]= findViewById(southIds[i]);//for all ids assigned to BTno.
            /**
             * ...Restrictions [speedIntervals]*/
            for (long j = 2000; j < 3000; j += 2000) {
                String RS = Long.toString(j);
                Log.d("loop:", RS);
                ///....testing.....
                //constant anim duration
                numSouth[i] .animate().setStartDelay(2000).translationYBy(southTrans).setDuration(Duration);
            }
            southTrans +=3;
        }
    }
    public void executeNorth(int[] NorthIds, Button[] numNorth, float NorthTrans) {
        for (i = 0; i < NorthIds.length; i++) {
            //formal var
            numNorth[i]= findViewById(NorthIds[i]);//for all ids assigned to BTno.
            /**
             * ...Restrictions [speedIntervals]*/
            numNorth[i] .animate().setStartDelay(2000).translationYBy(NorthTrans).setDuration(Duration);


        } }
    public void executeWest(int[] WestIds, Button[] numWest,float WestTrans) {
        for (i = 0; i < WestIds.length; i++) {
            //formal var
            numWest[i]= findViewById(IdsWest[i]);//for all ids assigned to BTno.
            /**
             * ...Restrictions [speedIntervals]*/
            numWest[i] .animate().setStartDelay(7000).translationXBy(WestTrans).setDuration(Duration);
        } }
    public void executeEast(int[] EastIds, Button[] numEast,float EastTrans) {
        for (i = 0; i < EastIds.length; i++) {
            //formal var
            numEast[i]= findViewById(EastIds[i]);//for all ids assigned to BTno.
            /**
             * ...Restrictions [speedIntervals]*/
            numEast[i] .animate().setStartDelay(7000).translationXBy(EastTrans).setDuration(Duration);


        } }
}