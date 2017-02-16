package io.github.j4cobgarby;

import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.VertexAttributes.Usage;
import com.badlogic.gdx.graphics.g3d.Material;
import com.badlogic.gdx.graphics.g3d.Model;
import com.badlogic.gdx.graphics.g3d.ModelBatch;
import com.badlogic.gdx.graphics.g3d.ModelInstance;
import com.badlogic.gdx.graphics.g3d.attributes.ColorAttribute;

public class Cube {
	private float width, depth, height; 
	private Model model;
	public ModelInstance instance;
	
	public Cube(float width, float depth, float height, Color col, float x, float y, float z) {
		this.width = width;
		this.depth = depth;
		this.height = height;
		
		model = Main.modelBuilder.createBox(width, depth, height, 
				new Material(ColorAttribute.createDiffuse(col)), 
				(long) (Usage.Position | Usage.Normal));
		
		instance = new ModelInstance(model, x, y, z);
	}
	
	public void draw(ModelBatch batch) {
		batch.render(instance, Main.environment);
	}
}
