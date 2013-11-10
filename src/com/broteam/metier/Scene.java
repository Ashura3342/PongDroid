/**
 * 
 */
package com.broteam.metier;

import android.graphics.Canvas;
import android.graphics.Paint;

import com.broteam.game.GameView;

/**
 * @author giallo_n
 *
 */
public class Scene extends AbstractGameObject implements ActivateColision {

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
			if (go.getX() - go.getWidth() / 2< x)
				go.setX(x + go.getWidth() / 2);
			else if (go.getX() + go.getWidth() / 2> width)
				go.setX(width - go.getWidth() / 2);
			if (go.getY() - go.getHeight() / 2 < y)
				go.setY(x + go.getHeight() / 2);
			else if (go.getY() + go.getHeight() / 2 > height)
				go.setY(width - go.getWidth() / 2);
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
		//nothing
	}

}
