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
public class Ball extends AbstractGameObject implements ActivateColision {

	private float angleIncidence = 0;
	public static final int COEF_RAYON = 45;
	public static final int SPEED_BALL = 8;
	private float vx;
	private float vy;
	private float coef;
	
	/**
	 * @param x
	 * @param y
	 * @param width
	 * @param height
	 */
	public Ball(float x, float y, float vx, float vy, GameView screen) {
		super(x + screen.width / (2 * COEF_RAYON), y + screen.width /( 2 * COEF_RAYON), 
				screen.width / COEF_RAYON, screen.width / COEF_RAYON, new Paint(), screen);
		this.vx = vx;
		this.vy = vy;
		this.coef = 1;
		calcCoef();
		paint.setColor(Color.GREEN);
	}

	/* (non-Javadoc)
	 * @see com.broteam.metier.ActivateColision#answerColision(com.broteam.metier.GameObject)
	 */
	@Override
	public void answerColision(GameObject go) {
		if (go.getClass().equals(Raquet.class)) {
			Log.d("Ball", "colision raquete "+((((Raquet)go).isTop()) ? "top" : "bas")
					+ " ("+go.getX()+", "+go.getY()+")"
					+ " en ("+getX()+", "+getY()+")");
		}else if (go.getClass().equals(Scene.class)) {
			Log.d("Ball", "colision Scene "
					+ " ("+go.getX()+", "+go.getY()+")"
					+ " de ("+go.getWidth()+", "+go.getHeight()+")"
					+ " en ("+getX()+", "+getY()+")");
		}
		calcSpeedVector(go);
	}

	/* (non-Javadoc)
	 * @see com.broteam.metier.GameObject#update()
	 */
	@Override
	public void update() {
		for (ActivateColision ac : objects) {
				if(ac.isColision(this)) {
					ac.answerColision(this);
					answerColision((GameObject)ac);
				}
		}
		x += vx * coef;
		y += vy * coef;
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

	public void calcCoef() {
		float speed = (float) (coef * Math.sqrt(Math.pow((float) vx, 2) + Math.pow((float)vx, 2)));
		coef *= SPEED_BALL / speed;
	}
	
	public void calcSpeedVector(GameObject go) { 
		calcCoef();
		if (go.getClass().equals(Raquet.class) )
			vy = -vy;
		else
			vx = -vx;
	}

	public float getCoef() {
		return coef;
	}

	public void setCoef(float coef) {
		this.coef = coef;
	}
	
}