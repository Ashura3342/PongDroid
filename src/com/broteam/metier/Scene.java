/**
 * 
 */
package com.broteam.metier;

import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;

import com.broteam.game.GameView;

/**
 * @author giallo_n
 *
 */
public class Scene extends AbstractGameObject implements ActivateColision {

	private int scorePlayer1 = 0;
	private int scorePlayer2 = 0;
	
	/**
	 * @param x
	 * @param y
	 * @param width
	 * @param height
	 * @param paint
	 * @param screen
	 */
	public Scene(float x, float y, int width, int height, Paint paint,
			GameView screen) {
		super(x, y, width, height, paint, screen);
	}

	/* (non-Javadoc)
	 * @see com.broteam.metier.ActivateColision#answerColision(com.broteam.metier.GameObject)
	 */
	@Override
	public void answerColision(GameObject go) {
		if (go.getClass().equals(Ball.class)) {
			if (go.getX() - go.getWidth() / 2 < x)
				go.setX(x + go.getWidth() / 2);
			else if (go.getX() + go.getWidth() / 2 > width)
				go.setX(width - go.getWidth() / 2);
			if (go.getY() - go.getHeight() / 2 < y) {
				go.setY(height / 2);
				scorePlayer2++;
				((Ball)go).setCoef(1);
			}
			else if (go.getY() + go.getHeight() / 2 > height) {
				go.setY(height / 2);
				scorePlayer1++;
				((Ball)go).setCoef(1);
			}
		}
	}

	/* (non-Javadoc)
	 * @see com.broteam.metier.ActivateColision#isColision(com.broteam.metier.GameObject)
	 */
	@Override
	public boolean isColision(GameObject go) {
		return (go.getY() < y || 
				go.getY() > height || 
				go.getX() < x || 
				go.getX() > width);
	}

	/* (non-Javadoc)
	 * @see com.broteam.metier.AbstractGameObject#update()
	 */
	@Override
	public void update() {
		//nothing
	}

	/* (non-Javadoc)
	 * @see com.broteam.metier.AbstractGameObject#render(android.graphics.Canvas)
	 */
	@Override
	public void render(Canvas c) {
		paint.reset();
		paint.setColor(Color.WHITE);
		paint.setStrokeWidth(5);
		c.drawLine(0, height / 2, width, height / 2, paint);
		paint.reset();
		paint.setColor(Color.WHITE);
		paint.setTextSize(height / 10);
		float scoreWidth = paint.measureText(String.valueOf(scorePlayer1));
		c.save();
		c.rotate(-180, width / 2 , height / 2);
		c.drawText(String.valueOf(scorePlayer1), width / 2 - scoreWidth / 2,  height - height / 4 + height / 20, paint);
		c.restore();
		scoreWidth = paint.measureText(String.valueOf(scorePlayer2));
		c.drawText(String.valueOf(scorePlayer2), width / 2 - scoreWidth / 2, height - height / 4 + height / 20, paint);
	}
}
