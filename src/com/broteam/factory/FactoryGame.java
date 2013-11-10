/**
 * 
 */
package com.broteam.factory;

import android.content.Context;

import com.broteam.game.GameLoop;
import com.broteam.game.PongLoop;

/**
 * @author giallo_n
 *
 */
public class FactoryGame {

	private static GameLoop game;
	
	public FactoryGame(Context context) {
		game = new PongLoop();
		game.initGame(context);
		
	}

	public static GameLoop getGame(Context context) {
		if (game == null)
			new FactoryGame(context);
		return game;
	}
}
