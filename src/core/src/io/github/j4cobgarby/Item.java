package io.github.j4cobgarby;

import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.g3d.Model;
import com.badlogic.gdx.graphics.g3d.ModelBatch;
import com.badlogic.gdx.graphics.g3d.ModelInstance;
import com.badlogic.gdx.graphics.g3d.attributes.BlendingAttribute;
import com.badlogic.gdx.math.Vector3;

public class Item {
	private Model model;
	public ModelInstance instance;
	private boolean canHit;
	private float minDmg, maxDmg;
	private float currentRotation = 0;
	
	public Item(Model model, boolean canHit, float minDmg, float maxDmg) {
		this.model = model;
		this.canHit = canHit;
		this.minDmg = minDmg;
		this.maxDmg = maxDmg;
		
		this.instance = new ModelInstance(this.model);
		
		this.instance.materials.get(0).set
			(new BlendingAttribute(GL20.GL_SRC_ALPHA, GL20.GL_ONE_MINUS_SRC_ALPHA));
		//this.instance.transform.rotate(1, 0, 0, 90);
	}
	
	public Item(String model, boolean canHit, float minDmg, float maxDmg) {
		this.model = Main.assets.get(model, Model.class);
		this.canHit = canHit;
		this.minDmg = minDmg;
		this.maxDmg = maxDmg;
		
		this.instance = new ModelInstance(this.model);
		
		this.instance.materials.get(0).set
			(new BlendingAttribute(GL20.GL_SRC_ALPHA, GL20.GL_ONE_MINUS_SRC_ALPHA));
		//this.instance.transform.rotate(1, 0, 0, 90);
	}
	
	public static class ItemModels { // All items
		public static Item broadsword = new Item("broadsword.g3dj", true, 4, 6);
		public static Item dagger     = new Item("dagger.g3dj",     true, 2, 3);
	}
	
	public void setPositionFromCamera() {
		Vector3 target = Main.cam.position.cpy();
		target.add(Main.cam.direction.cpy().nor()); // Forward a bit
				
		instance.transform.setTranslation(target);
		
		Vector3 direction = Helpers.invertVector3(Main.cam.direction); // Inverted camera direction
		float yAngle = Helpers.angleFromVector2(direction); // Get the angle
		
		instance.transform.rotate(0, 1, 0, yAngle - currentRotation);
		currentRotation += yAngle - currentRotation;
		// Now it's locked to camera, time to translate it to desired transformation
		
		instance.transform.translate(0.9f, -0.32f, -0.3f); // The position relative to center of screen
		
		/*
		 * INFO: Rotation on y-axis is counter-clockwise
		 */
	}
	
	public void draw(ModelBatch batch) {
		batch.render(instance);
	}
}
