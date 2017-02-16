package io.github.j4cobgarby;

import com.badlogic.gdx.graphics.g3d.Model;
import com.badlogic.gdx.graphics.g3d.ModelBatch;
import com.badlogic.gdx.graphics.g3d.ModelInstance;
import com.badlogic.gdx.math.Vector3;

public class Decor {
	Model model;
	ModelInstance instance;
	
	public Decor(float x, float y, float z, Model model) {
		this.model = model;
		instance = new ModelInstance(model, new Vector3(x, y, z));
	}
	
	public void draw(ModelBatch batch) {
		batch.render(instance);
	}
}
