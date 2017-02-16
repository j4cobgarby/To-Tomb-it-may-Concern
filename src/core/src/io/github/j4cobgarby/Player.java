package io.github.j4cobgarby;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input.Keys;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.math.Quaternion;
import com.badlogic.gdx.math.Vector3;

public class Player extends Cube {
	/*
	 * MOVEMENT
	 */
	/*
	 * This is the velocity added on move. */
	private final float moveVelocity = 0.038f;
	/*
	 * This is what the velocity is multiplied by each frame
	 * so that the player slows down */
	private final float velocityScalar = 0.78f;
	/*
	 * This is how fast the player turns */ 
	private final float turnSpeed = 2.35f;
	/*
	 * Decreases how fast one strafes */
	private final float strafeScalar = 0.8f;
	/*
	 * Decreases how fast one moves backwards */
	private final float backScalar = 0.79f;
	/*
	 * If x y or z velocity are less than this, they're set
	 * to 0 to save processing time for low numbers */
	private final float zeroThreshold = 0.00001f;
	
	/*
	 * ITEM STUFF
	 */
	public Item currentItem;
	
	/*
	 * KEYS
	 */
	/*
	 * It should be obvious what these do */
	private int leftKey,
				rightKey,
				forwardKey,
				backKey,
	            rotRightKey,
				rotLeftKey;
	/*
	 * VECTORS
	 */
	/*
	 * Each frame, the player is translated by velocity */
	private Vector3 velocity;
	/*
	 * This variable is returned (and set by) the getPos()
	 * method. */
	private Vector3 position = new Vector3();
	
	public Player(float x, float y, float z) {
		super(1.5f, 3, 1, // Width/depth/height
				Color.BLACK, x, y, z);
		
		leftKey =    Keys.A;
		rightKey =   Keys.D;
		forwardKey = Keys.W;
		backKey =    Keys.S;
		
		rotRightKey = Keys.RIGHT;
		rotLeftKey =  Keys.LEFT;
		
		velocity = new Vector3(0, 0, 0);
		
		currentItem = Item.ItemModels.broadsword;
	}

	public void handleInput() {
		if (Gdx.input.isKeyPressed(Keys.ESCAPE)) Gdx.app.exit();
		
		if (velocity.x < zeroThreshold && velocity.x > -zeroThreshold) velocity.x = 0;
		if (velocity.y < zeroThreshold && velocity.y > -zeroThreshold) velocity.y = 0;
		if (velocity.z < zeroThreshold && velocity.z > -zeroThreshold) velocity.z = 0;
		
		if (Gdx.input.isKeyPressed(forwardKey)) {
			velocity.z -= moveVelocity;
		}
		if (Gdx.input.isKeyPressed(backKey)) {
			velocity.z += moveVelocity * backScalar;
		}
		if (Gdx.input.isKeyPressed(leftKey)) {
			velocity.x -= moveVelocity * strafeScalar;
		}
		if (Gdx.input.isKeyPressed(rightKey)) {
			velocity.x += moveVelocity * strafeScalar;
		}
		if (Gdx.input.isKeyPressed(rotLeftKey)) {
			instance.transform.rotate(new Vector3(0, 1, 0), turnSpeed);
			Main.cam.rotate(new Vector3(0, 1, 0), turnSpeed);	
		}
		if (Gdx.input.isKeyPressed(rotRightKey)) {
			instance.transform.rotate(new Vector3(0, 1, 0), -turnSpeed);
			Main.cam.rotate(new Vector3(0, 1, 0), -turnSpeed);
		}
				
		velocity.scl(velocityScalar);
	
		instance.transform.translate(velocity);
		
		currentItem.setPositionFromCamera();
	}
	
	public Vector3 getPos() {
		instance.transform.getTranslation(position);
		return position;
	}
	
	public float getAngle() {
		Quaternion tmp = new Quaternion();
		instance.transform.getRotation(tmp);
		return tmp.getAngle();
	}
	
	public Vector3 getVelocity() {
		return velocity;
	}

	public void setVelocity(Vector3 velocity) {
		this.velocity = velocity;
	}

	public int getLeftKey() {
		return leftKey;
	}

	public void setLeftKey(int leftKey) {
		this.leftKey = leftKey;
	}

	public int getRightKey() {
		return rightKey;
	}

	public void setRightKey(int rightKey) {
		this.rightKey = rightKey;
	}

	public int getForwardKey() {
		return forwardKey;
	}

	public void setForwardKey(int forwardKey) {
		this.forwardKey = forwardKey;
	}

	public int getBackKey() {
		return backKey;
	}

	public void setBackKey(int backKey) {
		this.backKey = backKey;
	}

	public int getRotRightKey() {
		return rotRightKey;
	}

	public void setRotRightKey(int rotRightKey) {
		this.rotRightKey = rotRightKey;
	}

	public int getRotLeftKey() {
		return rotLeftKey;
	}

	public void setRotLeftKey(int rotLeftKey) {
		this.rotLeftKey = rotLeftKey;
	}

	public Vector3 getPosition() {
		return position;
	}

	public void setPosition(Vector3 position) {
		this.position = position;
	}

	public float getMoveVelocity() {
		return moveVelocity;
	}

	public float getVelocityScalar() {
		return velocityScalar;
	}

	public float getTurnSpeed() {
		return turnSpeed;
	}

	public float getStrafeScalar() {
		return strafeScalar;
	}

	public float getBackScalar() {
		return backScalar;
	}
}