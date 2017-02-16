package io.github.j4cobgarby;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.math.Vector3;

public class Helpers {
	public static Vector2 angleToVector2(float angle, float magnitude) {
		Vector2 result;
		
		result = new Vector2(
				(float) Math.cos(angle) * magnitude,
				(float) Math.sin(angle) * magnitude
				);
		
		return result;
	}
	
	public static Vector3 Vector2toVector3OnYAxis(Vector2 v2) {
		return new Vector3(v2.x, 0, v2.y);
	}
	
	public static Vector2 rotateVector2(Vector2 v, float radians) {
		Vector2 result = new Vector2(0, 0);
		
		result.x = (float) ((v.x) * Math.cos(radians) -
				(v.y) * Math.sin(radians));
		
		result.y = (float) ((v.x) * Math.sin(radians) +
				(v.y) * Math.cos(radians));
		
		
		//float ca = (float) Math.cos(radians);
		//float sa = (float) Math.cos(radians);
		
		//return new Vector2(ca * v.x - sa*v.y, sa*v.x + ca*v.y);
		return result;
	}
	
	/*
	 * This function will likely be used a LOT
	 * It takes floats x and y
	 * assumes they are a two dimensional vector
	 * calculates an angle based on this, in degrees
	 */
	public static float angleFromVector2(float x, float y) {
		return (float) (Math.toDegrees(Math.atan2(y, x)));
	}
	
	public static float angleFromVector2(Vector2 v) {
		return angleFromVector2(v.y, v.x);
	}
	
	public static float angleFromVector2(Vector3 v) {
		return angleFromVector2(v.z, v.x);
	}
	
	public static Vector3 invertVector3(float x, float y, float z) {
		return new Vector3(x, y, z).scl(-1);
	}	
		
	public static Vector3 invertVector3(Vector3 v) {
		return invertVector3(v.x, v.y, v.z);
	}
	
	public static String[] readLines(String filename) throws IOException {
        FileReader fileReader = new FileReader(filename);
        BufferedReader bufferedReader = new BufferedReader(fileReader);
        List<String> lines = new ArrayList<String>();
        String line = null;
        while ((line = bufferedReader.readLine()) != null) {
            lines.add(line);
        }
        bufferedReader.close();
        return lines.toArray(new String[lines.size()]);
    }
}
