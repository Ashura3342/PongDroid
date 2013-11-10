/**
 * 
 */
package com.broteam.metier;

import com.broteam.game.GameView;

import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;

/**
 * @author giallo_n
 *
 */
public class Raquet extends GameObject implements ActivateColision {

	public static final int WIDTH = 100;
	public static final int HEIGHT = 10;
	private boolean isTop;
	
	/**
	 * @param x
	 * @param y
	 * @param width
	 * @param height
	 */
	public Raquet(float x, float y, boolean isTop, GameView screen) {
		super(x, y, screen.width / 4, HEIGHT, new Paint(), screen);
		paint.setColor(Color.WHITE);
		this.isTop = isTop;
	}

	@Override
	public void update() {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void render(Canvas c) {
		c.drawRect(x - width / 2, y - height / 2, x + width / 2, y + height / 2, paint);
	}

	@Override
	public boolean isColision(GameObject ball) {
		if (ball.getX() >= (getX() - getWidth() / 2) && ball.getX() <= (getX() + getWidth() / 2))
		{
				if (isTop)
					return (ball.getY() < getY() + getHeight());
				else		
					return (ball.getY() > getY() - getHeight());
		}else
			return false;
	}

	public boolean isTop() {
		return isTop;
	}
	
	@Override
	public void setX(float x) {
		if (x < (screen.width - width / 2) && x > (width / 2))
			super.setX(x);
	}

	@Override
	public void answerColision(GameObject go) {
		//nothing
	}

}
