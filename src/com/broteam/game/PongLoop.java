/**
 * 
 */
package com.broteam.game;

import com.broteam.metier.Ball;
import com.broteam.metier.GameObject;
import com.broteam.metier.Raquet;

import android.graphics.Canvas;
import android.graphics.Color;
import android.view.MotionEvent;

/**
 * @author giallo_n
 *
 */
public class PongLoop extends GameLoop {

	public static final int TIME = 20;
	private Raquet raqTop;
	private Raquet raqBot;
	private Ball ball;
	
	public PongLoop() {
		super(TIME);
	}

	@Override
	public void initBefore() {
		ball = new Ball(screen.width / 2, screen.height / 2, -0.5f, 1, screen);
		raqTop = new Raquet(screen.width /2, 
				10 + screen.height / (2 * Raquet.COEF_HEIGHT), true, screen);
		raqBot = new Raquet(screen.width / 2, 
				screen.height - 10 - screen.height / (2 * Raquet.COEF_HEIGHT), false, screen);
		gameObject.add(raqTop);
		gameObject.add(raqBot);
		gameObject.add(ball);
		gameObject.add(screen.getScene());
		ball.addObject(screen.getScene()); 
		ball.addObject(raqTop);
		ball.addObject(raqBot);
	}
	
	@Override
	public void update() {
		for(GameObject go : gameObject) {
			go.update();
		}
	}

	@Override
	public void render(Canvas canvas) {
		canvas.drawColor(Color.BLACK);
		for(GameObject go : gameObject) {
			go.render(canvas);
		}
		screen.invalidate();
	}

	@Override
	public void processEvent() {
		if (motionEvent != null) {
			if (motionEvent.getPointerCount() == 1)
				singleTouch(motionEvent);
			else if (motionEvent.getPointerCount() == 2) {
				multiTouch(motionEvent);
			}
		}
	}
	
	public void singleTouch(MotionEvent event) {
		if (event.getAction() == MotionEvent.ACTION_MOVE) {
			if (event.getY() >= screen.height / 2)
				raqBot.setX(event.getX());
			else
				raqTop.setX(event.getX());
		}
	}
	
	public void multiTouch(MotionEvent event) {
		if (event.getAction() == MotionEvent.ACTION_MOVE) {
			if (event.getY(0) >= screen.getHeight() / 2 && event.getY(1) < screen.getHeight() / 2)  {
				raqBot.setX(event.getX(0));
				raqTop.setX(event.getX(1));
			}else if (event.getY(1) >= screen.getHeight() / 2 && event.getY(0) < screen.getHeight() / 2) {
				raqBot.setX(event.getX(1));
				raqTop.setX(event.getX(0));
			}
		}
	}
}
