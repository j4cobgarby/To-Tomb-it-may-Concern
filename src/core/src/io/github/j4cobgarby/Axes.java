package io.github.j4cobgarby;

import java.util.ArrayList;

import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.VertexAttributes.Usage;
import com.badlogic.gdx.graphics.g3d.Material;
import com.badlogic.gdx.graphics.g3d.ModelInstance;
import com.badlogic.gdx.graphics.g3d.attributes.ColorAttribute;
import com.badlogic.gdx.math.Vector3;

public class Axes {
	public static class Components {
		private static final int DIVISIONS = 20;
		private static final int SIZE = 1;
		
		private static ModelInstance grid;
		private static ModelInstance xAxis;
		private static ModelInstance yAxis;
		private static ModelInstance zAxis;
		static ModelInstance r;
		private static ArrayList<ModelInstance> axes = new ArrayList<ModelInstance>();
		
		public static void init() {
			grid = new ModelInstance(
					Main.modelBuilder.createLineGrid(
							DIVISIONS, DIVISIONS,
							SIZE, SIZE,
							new Material(ColorAttribute.createDiffuse(Color.LIGHT_GRAY)), 
							Usage.Position | Usage.Normal));
			
			
			/*  X | Y | Z
			 * ---+---+---
			 *  R | G | B
			 *  e   r   l
			 *  d   e   u
			 *      e   e
			 *      n
			 *      
			 *  A handy(?) table for the colour of each axis */
			xAxis = new ModelInstance( Main.modelBuilder.createArrow(
						new Vector3(0,0,0), new Vector3(10,0,0),
						new Material(ColorAttribute.createDiffuse(Color.RED)), Usage.Position | Usage.Normal));
			yAxis = new ModelInstance( Main.modelBuilder.createArrow(
						new Vector3(0,0,0), new Vector3(0,10,0),
						new Material(ColorAttribute.createDiffuse(Color.GREEN)), Usage.Position | Usage.Normal));
			zAxis = new ModelInstance( Main.modelBuilder.createArrow(
						new Vector3(0,0,0), new Vector3(0,0,10),
						new Material(ColorAttribute.createDiffuse(Color.BLUE)), Usage.Position | Usage.Normal));
			r = new ModelInstance(Main.modelBuilder.createRect(0, 0, 0, 0, 0, 0, 2, 2, 2, 2, 2, 2, 0, 1, 0, new Material(ColorAttribute.createDiffuse(Color.VIOLET)), Usage.Position | Usage.Normal));
			axes.add(xAxis);
			axes.add(yAxis);
			axes.add(zAxis);
			axes.add(r);
		}
		
		public static void drawAxes() {
			//Main.modelBatch.render(grid);
			Main.modelBatch.render(axes);
		}
	}
}
