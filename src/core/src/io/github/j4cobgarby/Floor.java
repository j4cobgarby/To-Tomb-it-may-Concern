package io.github.j4cobgarby;

import com.badlogic.gdx.graphics.g3d.Model;
import com.badlogic.gdx.graphics.g3d.ModelBatch;
import com.badlogic.gdx.graphics.g3d.ModelInstance;
import com.badlogic.gdx.math.Vector3;

public class Floor {
	private final float size = 4;
	ModelInstance instance;
	
	public static class Types {
		public static Model cobble = Main.assets.get("cobble-floor.g3dj", Model.class);
	}
	
	public static void addFloors(int amountX, int amountY) {
		for (int r = 0; r < amountY; r++) {
			for (int c = 0; c < amountX; c++) {
				Main.floor.add(new Floor(Floor.Types.cobble, c, r));
			}
		}
	}
	
	/*
	 * x and y are ints because this DOES NOT MEAN
	 * THE WORLD COORDINATES!
	 * it means, imagining that everything is in a
	 * grid: the position in the grid
	 */
	public Floor(Model model, int x, int z) {
		instance = new ModelInstance(model, new Vector3(x * size, 0, z * size));
	}
	
	public void draw(ModelBatch batch) {
		batch.render(instance);
	}
}