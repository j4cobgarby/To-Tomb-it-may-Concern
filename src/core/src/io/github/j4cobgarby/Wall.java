package io.github.j4cobgarby;

import com.badlogic.gdx.graphics.g3d.Model;
import com.badlogic.gdx.graphics.g3d.ModelBatch;
import com.badlogic.gdx.graphics.g3d.ModelInstance;
import com.badlogic.gdx.math.Vector3;

public class Wall {
	private final float size = 4;
	ModelInstance instance;
	
	public static class Types {
		public static Model wall = Main.assets.get("wall.g3dj", Model.class); // c
	}
	
	/*
	 * x and y are ints because this DOES NOT MEAN
	 * THE WORLD COORDINATES!
	 * it means, imagining that everything is in a
	 * grid: the position in the grid
	 */
	public Wall(Model model, int x, int z) {
		instance = new ModelInstance(model, new Vector3(x * size, 2, z * size));
	}
	
	public void draw(ModelBatch batch) {
		batch.render(instance);
	}
	
	public void printInfo() {
		Vector3 tmp = Vector3.Zero;
		instance.transform.getTranslation(tmp);
	}
}
