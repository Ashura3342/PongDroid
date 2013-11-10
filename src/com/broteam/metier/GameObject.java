/**
 * 
 */
package com.broteam.metier;

import java.util.ArrayList;
import java.util.List;

import com.broteam.game.GameView;

import android.graphics.Canvas;
import android.graphics.Paint;

/**
 * @author giallo_n
 *
 */
public abstract class GameObject {

	protected float x;
	protected float y;
	protected float width;
	protected float height;
	protected List<ActivateColision> objects;
	protected List<Composant> composants;
	protected Paint paint;
	protected GameView screen;
	
	public GameObject(float x, float y, float width, float height, Paint paint, GameView screen) {
		this.x = x;
		this.y = y;
		this.width = width;
		this.height = height;
		this.objects = new ArrayList<ActivateColision>();
		this.composants = new ArrayList<Composant>();
		this.paint = paint;
		this.screen = screen;
	}
	
	public abstract void update();
	
	public abstract void render(Canvas c);

	public float getX() {
		return x;
	}

	protected void setX(float x) {
		this.x = x;
	}

	public float getY() {
		return y;
	}

	protected void setY(float y) {
		this.y = y;
	}

	public float getWidth() {
		return width;
	}

	public float getHeight() {
		return height;
	}

	public void addObject(ActivateColision ac) {
		objects.add(ac);
	}
	
	public void removeObject(ActivateColision ac) {
		objects.remove(ac);
	}
	public void addComposant(Composant c) {
		composants.add(c);
	}
	
	public void removeComposant(Composant c) {
		composants.remove(c);
	}
}
