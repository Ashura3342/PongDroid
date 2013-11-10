/**
 * 
 */
package com.broteam.game;

import java.util.ArrayList;
import java.util.List;

import android.content.Context;
import android.graphics.Canvas;
import android.view.MotionEvent;

import com.broteam.metier.GameObject;

/**
 * @author giallo_n
 *
 */
public abstract class GameLoop implements Runnable {

	public static final String tag = "GameLoop";
	private Thread thread;
	protected boolean running;
	protected List<GameObject> gameObject;
	public long sleepTime;
	private int fps;
	protected MotionEvent motionEvent;
	protected GameView screen;
	/**
	 * 
	 */
	public GameLoop(long sleepTime) {
		this.sleepTime = sleepTime;
		thread = new Thread(this, tag);
		gameObject = new ArrayList<GameObject>();
		running = false;
	}
 
	public void initGame(Context context) {
		this.screen = new GameView(context);
	}
	
	public void start() {
		if (!running) {
			running = true;
			initBefore();
			thread.start();
		}
	}
	
	public void stop() {
		if (running) {
			running = false;
			try {
				thread.join();
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
		}
	}
	
	/* (non-Javadoc)
	 * @see java.lang.Runnable#run()
	 */
	@Override
	public void run() {
		long startTime;
		long elapsedTime;
		long sleepCorrected;
		while (running) {
			startTime = System.currentTimeMillis();
			processEvent();
			update();
			render(screen.getCanvas());
			elapsedTime = System.currentTimeMillis() - startTime;
			sleepCorrected = sleepTime - elapsedTime;
			// on remet à 1 si sleepCorrected devient négatif
			if (sleepCorrected < 0) {
				sleepCorrected = 1;
			}
			try {
				Thread.sleep(sleepCorrected > 0 ? sleepCorrected : 1);
			} catch (InterruptedException e) {
				
			}
			// calculer le FSP
			fps = (int) (1000/(System.currentTimeMillis() - startTime));
		}
	}
	
	
	public int getFps() {
		return fps;
	}

	public void setMotionEvent(MotionEvent motionEvent) {
		this.motionEvent = motionEvent;
	}

	public GameView getScreen() {
		return screen;
	}
	public abstract void processEvent();
	public abstract void update();
	public abstract void render(Canvas canvas);
	public abstract void initBefore();
}
