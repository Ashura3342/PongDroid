/**
 * 
 */
package com.broteam.metier;

import com.broteam.game.GameView;

import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.util.Log;

/**
 * @author giallo_n
 *
 */
public class Ball extends GameObject implements ActivateColision {

	private float angleIncidence = 0;
	public static final int WIDTH = 10;
	public static final int HEIGHT = 10;
	private float vx;
	private float vy;
	
	/**
	 * @param x
	 * @param y
	 * @param width
	 * @param height
	 */
	public Ball(float x, float y, float vx, float vy, GameView screen) {
		super(x, y, WIDTH, HEIGHT, new Paint(), screen);
		this.vx = vx;
		this.vy = vy;
		paint.setColor(Color.GREEN);
	}

	/* (non-Javadoc)
	 * @see com.broteam.metier.ActivateColision#answerColision(com.broteam.metier.GameObject)
	 */
	@Override
	public void answerColision(GameObject go) {
		Log.d("Ball", "colision raquete "+((((Raquet)go).isTop()) ? "top" : "bas")
				+" ("+((Raquet)go).getX()+", "+((Raquet)go).getY()+")"
				+" en ("+getX()+", "+getY()+")");
		calcSpeedVector(go);
	}

	/* (non-Javadoc)
	 * @see com.broteam.metier.GameObject#update()
	 */
	@Override
	public void update() {
		for (ActivateColision ac : objects) {
				if(ac.isColision(this)) {
					this.answerColision((GameObject)ac);
				}
		}
		x = x + vx;
		y = y + vy;
	}

	/* (non-Javadoc)
	 * @see com.broteam.metier.GameObject#render()
	 */
	@Override
	public void render(Canvas c) {
		c.drawCircle(x, y, width / 2, paint);
	}

	public float getAngleIncidence() {
		return angleIncidence;
	}

	@Override
	public boolean isColision(GameObject ac) {
		//nothing
		return false;
	}

	public void calcSpeedVector(GameObject go) {
		if (go.getClass().equals(Raquet.class)) {
			if (vy >= 20)
				vy  = -20;
			else if (vy <= -20)
				vy = 20;
			else
				vy = -vy * 1.5f;
			Log.d("Ball", "change vecteur y "+vy);
		}else{
			vx = -vx * Math.abs(vx);
			Log.d("Ball", "change vecteur x "+vx);
		}
	}
}
