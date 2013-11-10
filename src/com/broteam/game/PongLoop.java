/**
 * 
 */
package com.broteam.game;

import com.broteam.metier.Ball;
import com.broteam.metier.GameObject;
import com.broteam.metier.Raquet;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
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
	/**
	 * 
	 */
	public PongLoop() {
		super(TIME);
	}

	@Override
	public void initBefore() {
		ball = new Ball(screen.width / 2, screen.height / 2, 0, 2, screen);
		raqTop = new Raquet(screen.width / 2 + Raquet.WIDTH / 2, Raquet.HEIGHT / 2 + 10, true, screen);
		raqBot = new Raquet(screen.width / 2 + Raquet.WIDTH / 2, screen.height - Raquet.HEIGHT / 2 - 10, false, screen);
		gameObject.add(raqTop);
		gameObject.add(raqBot);
		gameObject.add(ball);
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
		Paint paint = new Paint();
		canvas.drawColor(Color.BLACK);
		paint.setColor(Color.BLUE);
		canvas.drawLine(0, screen.height / 2, screen.width, screen.height / 2, paint);
		for(GameObject go : gameObject) {
			go.render(canvas);
		}
		screen.invalidate();
	}

	@Override
	public void processEvent() {
		if (motionEvent != null) {
		if(motionEvent.getPointerCount() > 1) {
					if (motionEvent.getAction() == MotionEvent.ACTION_MOVE 
							&& motionEvent.getActionMasked() != MotionEvent.ACTION_DOWN) {
						if (motionEvent.getX(0) < screen.height / 2 &&
								motionEvent.getX(1) >= screen.height / 2 ) {
							raqTop.setX(motionEvent.getX(0));
							raqBot.setX(motionEvent.getX(1));
						}else if (motionEvent.getX(1) < screen.height / 2 &&
								motionEvent.getX(0) >= screen.height / 2 ) {
							raqTop.setX(motionEvent.getX(1));
							raqBot.setX(motionEvent.getX(0));
						}	
					}
			}else {
				if (motionEvent.getAction() == MotionEvent.ACTION_MOVE) {
					if (motionEvent.getY() < screen.height / 2) {
						raqTop.setX(motionEvent.getX());
					}else {
						raqBot.setX(motionEvent.getX());
					}
				}
			}
		}
	}
	
	public void initGame(Context context){
		super.initGame(context);
	}
}
