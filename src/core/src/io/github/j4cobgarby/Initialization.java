package io.github.j4cobgarby;

import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g3d.Model;

public class Initialization {
	public static class Assets {
		public static void load() {
			// Textures
			Main.assets.load("broadsword.png", Texture.class);
			Main.assets.load("cobble-floor.png", Texture.class);
			Main.assets.load("cobble-floor.png", Texture.class);
			Main.assets.load("cobble.png", Texture.class);
			Main.assets.load("dagger.png", Texture.class);
			Main.assets.load("door-blue.png", Texture.class);
			Main.assets.load("door-green.png", Texture.class);
			Main.assets.load("door-purple.png", Texture.class);
			Main.assets.load("door-red.png", Texture.class);
			
			// Models
			Main.assets.load("broadsword.g3dj", Model.class);
			Main.assets.load("dagger.g3dj", Model.class);
			Main.assets.load("bonepile.g3dj", Model.class);
			Main.assets.load("cobble-floor.g3dj", Model.class);
			Main.assets.load("wall.g3dj", Model.class);
			Main.assets.load("door-red.g3dj", Model.class);
			Main.assets.load("door-green.g3dj", Model.class);
			Main.assets.load("door-blue.g3dj", Model.class);
			Main.assets.load("door-purple.g3dj", Model.class);
			
			Main.assets.finishLoading(); // Pause until all assets are loaded
		}
	}
}
