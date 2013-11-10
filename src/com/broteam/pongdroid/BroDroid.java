package com.broteam.pongdroid;

import com.broteam.game.PongLoop;

import android.os.Bundle;
import android.app.Activity;
import android.view.Window;
import android.view.WindowManager;

public class BroDroid extends Activity {

	private PongLoop game;
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN,
        		WindowManager.LayoutParams.FLAG_FULLSCREEN);
        getWindow().setFlags(WindowManager.LayoutParams.FLAG_KEEP_SCREEN_ON,
        		WindowManager.LayoutParams.FLAG_KEEP_SCREEN_ON);
        requestWindowFeature(Window.FEATURE_NO_TITLE);
        game = new PongLoop();  
        game.initGame(this);
        setContentView(game.getScreen());
	}
	
	@Override
    protected void onDestroy() {
		game.stop();
		super.onDestroy();
    }
}
