package io.github.j4cobgarby.desktop;

import com.badlogic.gdx.backends.lwjgl.LwjglApplication;
import com.badlogic.gdx.backends.lwjgl.LwjglApplicationConfiguration;
import io.github.j4cobgarby.Main;

public class DesktopLauncher {
	public static void main (String[] arg) {
		LwjglApplicationConfiguration cfg = new LwjglApplicationConfiguration();
		
		cfg.title = "To Tomb it may Concern";
		
		/** width & height of application window **/
		cfg.width = 1080;
		cfg.height = 720;
		
		/** whether or not the window can be resized **/
		cfg.resizable = false;
		
		/** Uncomment these lines for no vsync **/
		//cfg.vSyncEnabled = false;
		//cfg.foregroundFPS = 9999999;
		//cfg.backgroundFPS = 9999999;
		
		new LwjglApplication(new Main(), cfg);
	}
}
