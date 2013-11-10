/**
 * 
 */
package com.broteam.metier;

import android.graphics.Canvas;

/**
 * @author giallo_n
 *
 */
public interface GameObject {
	public void update();
	public void render(Canvas c);
	public float getX();
	public void setX(float x);
	public float getY();
	public void setY(float y);
	public int getWidth();
	public int getHeight();
}