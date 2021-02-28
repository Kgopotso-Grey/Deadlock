package com.example.myapplication;

import androidx.annotation.RequiresApi;
import androidx.appcompat.app.AppCompatActivity;

import android.animation.ValueAnimator;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Rect;
import android.os.Build;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {
    int i;
    private static final int[] IdsNorth = {
            R.id.northpink, R.id.northpink2,R.id.northpink3};
    private static final int[] IdsSouth = {
            R.id.southBlue, R.id.southPink,R.id.southBlue2,R.id.southPink2};
    private static final int[] IdsEast = {
            R.id.blackEast, R.id.greyEast};
    private static final int[] IdsWest = {
            R.id.westblack, R.id.westgrey};
    Button resolve;


    private Button[] northNumber = new Button[IdsNorth.length];
    private Button[] southNumber = new Button[IdsSouth.length];
    private Button[] EastNumber = new Button[IdsEast.length];
    private Button[] westNumber = new Button[IdsWest.length];

    float northTrans = -300f;//for north
    float southTrans = 170f;
    float westTrans = -130f;
    float eastTrans =130f;
    long Duration=2000l;//static


    @RequiresApi(api = Build.VERSION_CODES.LOLLIPOP)
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        run();
        resolve = findViewById(R.id.resolveDeadlock);
        CheckCollision(northNumber[0],westNumber[0]);

        //north1 and west 1
        Rect myViewRect = new Rect();
        northNumber[0].getHitRect(myViewRect);

        Rect myViewRect2 = new Rect();
        westNumber[0].getHitRect(myViewRect2);
        if(Rect.intersects(myViewRect,myViewRect2)){

            Toast.makeText(this, "collision!!!", Toast.LENGTH_SHORT).show();
        }
        //...

        Rect rc1 = new Rect();
        northNumber[0].getDrawingRect(rc1);
        Rect rc2 = new Rect();
        westNumber[0].getDrawingRect(rc2);
        //lC
        Rect rc3 = new Rect();
        southNumber[0] .getDrawingRect(rc3);
        Rect rc4 = new Rect();
        EastNumber[0] .getDrawingRect(rc4);
        if (Rect.intersects(rc1, rc2)) {
            // intersection is detected
            Toast.makeText(this, "Blocked", Toast.LENGTH_SHORT).show();
        }
        else if(Rect.intersects(rc3,rc4)){
            Toast.makeText(this, "Blocked", Toast.LENGTH_SHORT).show();
        }
        else{
            resolve.animate().alpha(1f).setStartDelay(5000l);

            resolve.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    startActivity(new Intent(MainActivity.this,Resolved.class));
                    Toast.makeText(MainActivity.this, "BLocked", Toast.LENGTH_SHORT).show();
                }
            });}
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
    public void executeNorth(int[] NorthIds, Button[] numNorth,float NorthTrans) {
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
            numWest[i] .animate().setStartDelay(2000).translationXBy(WestTrans).setDuration(Duration);
        } }
    public void executeEast(int[] EastIds, Button[] numEast,float EastTrans) {
        for (i = 0; i < EastIds.length; i++) {
            //formal var
            numEast[i]= findViewById(EastIds[i]);//for all ids assigned to BTno.
            /**
             * ...Restrictions [speedIntervals]*/
            numEast[i] .animate().setStartDelay(2000).translationXBy(EastTrans).setDuration(Duration);


        } }
    public boolean CheckCollision(View v1,View v2) {
        Rect R1=new Rect(v1.getLeft(), v1.getTop(), v1.getRight(), v1.getBottom());
        Rect R2=new Rect(v2.getLeft(), v2.getTop(), v2.getRight(), v2.getBottom());
        if (Rect.intersects(R1,R2)) {
            Toast.makeText(this, "coliding", Toast.LENGTH_SHORT).show();
    }
        return R1.intersect(R2);
    }
    @RequiresApi(api = Build.VERSION_CODES.LOLLIPOP)
    public void run() {

        executeSouth(IdsSouth,southNumber,southTrans);
        executeNorth(IdsNorth,northNumber,northTrans);
        executeWest(IdsWest,westNumber,westTrans);
        executeEast(IdsEast,EastNumber,eastTrans);
        CheckCollision(northNumber[0],southNumber[0]);
//        if (CheckCollision(northNumber[0],westNumber[0])) {
//            Toast.makeText(this, "deadlock", Toast.LENGTH_SHORT).show();
//        }

    }
    /**
     * Check if two views are colliding in screen based on rectangular
     * shape
     *
     * @param v1 View
     * @param v2 View
     * @return boolean
     */
    public static boolean isCollisionDetected(View v1, View v2) {
        if (v1 == null || v2 == null) {

            throw new IllegalArgumentException("Views mut be not null");
        }
        Rect R1 = new Rect();
        v1.getHitRect(R1);
        Rect R2 = new Rect();
        v2.getHitRect(R2);
        return Rect.intersects(R1, R2);
    }
        //move to resolve
    @RequiresApi(api = Build.VERSION_CODES.LOLLIPOP)
    //request api for a certain animation TransZ
    public void deadlock (int[] Ids, Button[] Num)
    {
        for(long k =0; k<Ids.length; k++){

            Num[(int)k].animate().alpha(0)
                   .setDuration(15000);
            /**
             * ..fadeOUt!**/
        }
    }

    }

