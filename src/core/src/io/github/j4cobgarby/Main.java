package io.github.j4cobgarby;

import java.util.ArrayList;

import com.badlogic.gdx.ApplicationAdapter;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input.Keys;
import com.badlogic.gdx.assets.AssetManager;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.PerspectiveCamera;
import com.badlogic.gdx.graphics.Pixmap;
import com.badlogic.gdx.graphics.Texture.TextureFilter;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.g2d.freetype.FreeTypeFontGenerator;
import com.badlogic.gdx.graphics.g2d.freetype.FreeTypeFontGenerator.FreeTypeFontParameter;
import com.badlogic.gdx.graphics.g3d.Environment;
import com.badlogic.gdx.graphics.g3d.ModelBatch;
import com.badlogic.gdx.graphics.g3d.ModelInstance;
import com.badlogic.gdx.graphics.g3d.attributes.ColorAttribute;
import com.badlogic.gdx.graphics.g3d.environment.DirectionalLight;
import com.badlogic.gdx.graphics.g3d.utils.ModelBuilder;
import com.badlogic.gdx.graphics.glutils.FrameBuffer;
import com.badlogic.gdx.graphics.glutils.GLFrameBuffer;
import com.badlogic.gdx.math.MathUtils;

public class Main extends ApplicationAdapter {
	boolean inGame = false;
	
	FrameBuffer fbo;
	SpriteBatch fboBatch;
	
	private int fboSize = 6;
	
	public static boolean debug = false;
	
	ModelInstance sword;
		
	public static Environment environment;
	public static PerspectiveCamera cam;
	public static ModelBatch modelBatch;
	public static ModelBatch itemBatch;
	public static ModelBuilder modelBuilder;
	public static AssetManager assets;
	
	private final Color bg = new Color(0x2e3652ff);
	
	public static BitmapFont monoFont;
	SpriteBatch spriteBatch;
	
	ArrayList<Cube> worldCubes = new ArrayList<Cube>();
	ArrayList<Wall> walls = new ArrayList<Wall>();
	ArrayList<Decor> decors = new ArrayList<Decor>();
	public static ArrayList<Floor> floor = new ArrayList<Floor>();
		
	public static Player player;
	
	Level lvl;

	@Override
	public void create() {
		Gdx.input.setCursorCatched(true);
		
		/*
		 * Initialize the FrameBuffer. This is used to shrink the screen
		 * resolution for the low-res effect.
		 */
		initializeFBO();
		
		modelBuilder = new ModelBuilder();
		assets = new AssetManager();
		
		Initialization.Assets.load();
				
		environment = new Environment();
		environment.set(new ColorAttribute(ColorAttribute.AmbientLight, 0.4f, 0.4f, 0.4f, 1f));
		environment.add(new DirectionalLight().set(0.8f, 0.8f, 0.8f, -1f, -0.8f, -0.2f));
		
		modelBatch = new ModelBatch();
		itemBatch = new ModelBatch();
		spriteBatch = new SpriteBatch();
		
		player = new Player(0, 0, 0);
				
		lvl = new Level(Level.Levels.level1, 2, 0, 2);
		
		changeLevel(lvl);
				
		Axes.Components.init();
		
		cam = new PerspectiveCamera(80, // FOV
				Gdx.graphics.getWidth(), Gdx.graphics.getHeight());
		
		cam.near = 0.01f;
		cam.far = 400f;
		cam.update();
		
		FreeTypeFontGenerator fontGen = new FreeTypeFontGenerator
				(Gdx.files.internal("JAI.TTF"));
		FreeTypeFontParameter fontPar = new FreeTypeFontParameter();
		fontPar.size = 13;
		fontPar.color = Color.WHITE;
		fontPar.borderWidth = 1;
		monoFont = fontGen.generateFont(fontPar);
		fontGen.dispose();
	}

	@Override
	public void render() {
		cam.update();
		
		player.update();
		cam.position.set(player.getPos().add(0, 1.5f, 0));
		
		fbo.begin();
		
		Gdx.gl.glClearColor(bg.r, bg.g, bg.b, bg.a);
        Gdx.gl.glViewport(0, 0, fbo.getWidth(), fbo.getHeight());
        Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT | GL20.GL_DEPTH_BUFFER_BIT);
        Gdx.gl20.glEnable(GL20.GL_BLEND);
        Gdx.gl20.glBlendFunc(GL20.GL_SRC_ALPHA, GL20.GL_ONE_MINUS_SRC_ALPHA);
        Gdx.gl20.glEnable(GL20.GL_TEXTURE_2D);
        Gdx.gl20.glBlendEquation(GL20.GL_BLEND);
 
        if (Gdx.input.isKeyJustPressed(Keys.TAB)) debug =! debug;
        
        modelBatch.begin(cam);

        player.draw(modelBatch);
        player.currentItem.draw(modelBatch);
                
        for (Cube c : worldCubes) { // Draw the cubes. These aren't used much
        	c.draw(modelBatch);
        }
        
        for (Decor d : decors) { // Draw all the decors
        	d.draw(modelBatch);
        }
        
        for (Floor f : floor) { // Draw all of the floor tiles
        	f.draw(modelBatch);
        }
        
        for (Wall w : walls) {
        	w.draw(modelBatch);
        }
        
        walls.get(0).draw(modelBatch);
        
        /*
         * Draw axes
         */
        if (debug) Axes.Components.drawAxes();
        
        modelBatch.end();
        
        fbo.end();
        
        fboBatch.begin();
        fboBatch.draw(fbo.getColorBufferTexture(), 0, 0, 
        		Gdx.graphics.getWidth(), Gdx.graphics.getHeight(), 
        		0, 0, 1, 1);
        fboBatch.end();
        
        spriteBatch.begin();
        
        if (debug) {
        monoFont.draw(spriteBatch, 
        		String.format(
        				"+-----CONTROLS----------------+\n" +
        				"| wasd: up/down/left/right\n" +
        				"| <-/->: turning left/right\n" +
        				"| tab: show/hide debug\n" +
        				"| UP/DOWN: increase/decrease fbo size\n" +
        				"+-----PROGRAM VARIABLES-------+\n" +
        				"| FPS : %d\n" +
        				"| player velocity : %s\n" +
        				"| player position : %s\n" +
        				"| player TURN SPEED : %f\n" +
        				"| player SPEED : %f\n" +
        				"| fbo width : %d\n" +
        				"| fbo height : %d\n" +
        				"| fbo status : %s\n" +
        				"| fbo pixel size : %d\n" + 
        				"+-----OTHER STUFF-------------+\n" +
        				"| TAB to close this\n" +
        				"| ESC to close game\n" +
        				"+-----------------------------+"
        				, 
        				Gdx.graphics.getFramesPerSecond(), 
        				player.getVelocity().toString(), 
        				player.getPos(),
        				player.getTurnSpeed(),
        				player.getMoveVelocity(),
        				fbo.getWidth(),
        				fbo.getHeight(),
        				GLFrameBuffer.getManagedStatus(),
        				fboSize), 
        		5, Gdx.graphics.getHeight() - 5);
        } else monoFont.draw(spriteBatch, "press TAB for debug", 5, Gdx.graphics.getHeight() - 5);
        
        spriteBatch.end(); 
        
        if (Gdx.input.isKeyJustPressed(Keys.UP)) {
        	try {
        		fboSize++;
        		initializeFBO();
        	} catch (Exception e) {}
        }
        
        if (Gdx.input.isKeyJustPressed(Keys.DOWN)) {
        	try {
        		fboSize--;
        		initializeFBO();
        	} catch (Exception e) {}
        }
	}
	
	@Override
	public void dispose() {
		modelBatch.dispose();
	}
	
	public void initializeFBO() {
	   if (fbo != null) fbo.dispose();
	   fbo = new FrameBuffer(Pixmap.Format.RGB888, Gdx.graphics.getWidth() / fboSize, Gdx.graphics.getHeight() / fboSize, true);
	   fbo.getColorBufferTexture().setFilter(TextureFilter.Nearest, TextureFilter.Nearest);
	 
	   if (fboBatch != null) fboBatch.dispose();
	   fboBatch = new SpriteBatch();
	}
	
	public float getCameraCurrentXYAngle(PerspectiveCamera cam)
	{
	    return (float)Math.atan2(cam.up.x, cam.up.z)*MathUtils.radiansToDegrees;
	}
	
	public void changeLevel(Level lvl) {
		floor.clear();
		walls.clear();
		
		for (ArrayList<Floor> f : lvl.floors) {
			floor.addAll(f);
		}
		for (ArrayList<Wall> w : lvl.walls) {
			walls.addAll(w);
		}
		
		player.instance.transform.setTranslation(
				lvl.spawnx * 4, lvl.spawny * 4, lvl.spawnz * 4);
	}
}
