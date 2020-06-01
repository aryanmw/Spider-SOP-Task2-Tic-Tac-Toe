package com.wadhavekar.tictactoe;

import android.content.Context;
import android.media.AudioManager;
import android.media.SoundPool;

public class SoundPlayer {
    private static SoundPool soundPool;
    private static int victoryXsound,victoryOSound,draw;

    public SoundPlayer(Context context){
        soundPool = new SoundPool(2, AudioManager.STREAM_MUSIC,0);
        victoryXsound = soundPool.load(context,R.raw.victory,1);
        victoryOSound = soundPool.load(context,R.raw.ovictory,1);
        draw = soundPool.load(context,R.raw.draw,1);
    }

    public void playXSound(){
        soundPool.play(victoryXsound,1.0f,1.0f,1,1,1.0f);
    }
    public void playOSound(){
        soundPool.play(victoryOSound,1.0f,1.0f,1,1,1.0f);
    }
    public void draw(){
        soundPool.play(draw,1.0f,1.0f,1,1,1.0f);
    }
}
