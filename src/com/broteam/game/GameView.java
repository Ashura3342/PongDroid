/**
 * 
 */
package com.broteam.game;

import com.broteam.metier.Scene;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.Canvas;
import android.graphics.Bitmap.Config;
import android.graphics.Paint;
import android.util.AttributeSet;
import android.view.MotionEvent;
import android.view.SurfaceHolder;
import android.view.SurfaceHolder.Callback;
import android.view.SurfaceView;

/**
 * @author giallo_n
 *
 */
public class GameView extends SurfaceView implements Callback {
	public int width;	
	public int height;
	private Scene scene;
	public GameLoop gameLoop;
	private Bitmap buffer;
	private Canvas canvas;
	private SurfaceHolder holder;
	
	
	public GameView(Context context, AttributeSet attrs) {
		super(context, attrs);
		holder = getHolder();
		holder.addCallback(this);
	}

	/**
	 * @param context
	 */
	public GameView(Context context) {
		super(context);
		holder = getHolder();
		holder.addCallback(this);
	}
	
	
	
	public GameView(Context context, AttributeSet attrs, int defStyle) {
		super(context, attrs, defStyle);
		holder = getHolder();
		holder.addCallback(this);
	}

	/*
	 * (non-Javadoc)
	 * @see android.view.View#invalidate()
	 */
	@Override
	public void invalidate() {
		if (holder != null) {
			Canvas c = holder.lockCanvas();
			if (c != null) {
				c.drawBitmap(buffer, 0, 0, null);
				holder.unlockCanvasAndPost(c);
			}
		}
	}
	
	/*
 	* (non-Javadoc)
 	* @see android.view.View#onTouchEvent(android.view.MotionEvent)
 	*/	
	@Override
	public boolean onTouchEvent(MotionEvent event) {
		gameLoop.setMotionEvent(event);
		return true;
	}
	/* (non-Javadoc)
	 * @see android.view.SurfaceHolder.Callback#surfaceChanged(android.view.SurfaceHolder, int, int, int)
	 */
	@Override
	public void surfaceChanged(SurfaceHolder holder, int format, int width,
			int height) {
		this.width = width;
		this.height = height;
		this.scene = new Scene(0, 0, width, height, new Paint(), this);
		this.buffer = Bitmap.createBitmap(width, height, Config.ARGB_8888);
		this.canvas = new Canvas(buffer);
		gameLoop = new PongLoop();
		gameLoop.initGame(this);
		gameLoop.start();
	}

	/* (non-Javadoc)
	 * @see android.view.SurfaceHolder.Callback#surfaceCreated(android.view.SurfaceHolder)
	 */
	@Override
	public void surfaceCreated(SurfaceHolder holder) {
	}

	/* (non-Javadoc)
	 * @see android.view.SurfaceHolder.Callback#surfaceDestroyed(android.view.SurfaceHolder)
	 */
	@Override
	public void surfaceDestroyed(SurfaceHolder holder) {
		while (gameLoop.alive()) {
        	gameLoop.stop();
        }
	}

	public Canvas getCanvas() {
		return canvas;
	}
	
	public Scene getScene() {
		return scene;
	}
}
