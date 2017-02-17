package io.github.j4cobgarby;

import java.util.ArrayList;

import com.badlogic.gdx.graphics.g3d.ModelBatch;

public class Level {
	public ArrayList<ArrayList<Wall>> walls = new ArrayList<ArrayList<Wall>>();
	public ArrayList<ArrayList<Floor>> floors = new ArrayList<ArrayList<Floor>>();
	
	public int spawnx, spawnz, spawny;
	
	public static class Levels {
		public static char[][][] level1 = 
			{
				{ // Walls
					"cccccccccc".toCharArray(),
					"c        c".toCharArray(),
					"cccccrccbc".toCharArray(),
					"c   c  c c".toCharArray(),
					"c   g  c c".toCharArray(),
					"ccccc  c c".toCharArray(),
					"c   c  c c".toCharArray(),
					"c   ccc  c".toCharArray(),
					"c   p    c".toCharArray(),
					"cccccccccc".toCharArray()
					},
				{ // Floor
					"cccccccccc".toCharArray(),
					"cccccccccc".toCharArray(),
					"cccccccccc".toCharArray(),
					"cccccccccc".toCharArray(),
					"cccccccccc".toCharArray(),
					"cccccccccc".toCharArray(),
					"cccccccccc".toCharArray(),
					"cccccccccc".toCharArray(),
					"cccccccccc".toCharArray(),
					"cccccccccc".toCharArray(),
				}
			};
	}
	
	public Level(char[][][] layouts, int spawnx, int spawny, int spawnz) {
		char[][] tmpwalls = layouts[0];
		walls = wallsFromArray(tmpwalls);
		
		char[][]  tmpfloor= layouts[1];
		floors = floorFromArray(tmpfloor);
		
		this.spawnx = spawnx;
		this.spawny = spawny;
		this.spawnz = spawnz;
	}
	
	public void draw(ModelBatch batch) {
		
	}
	
	private ArrayList<ArrayList<Wall>> wallsFromArray(char[][] arr) {
		ArrayList<ArrayList<Wall>> result = new ArrayList<ArrayList<Wall>>();
		for (int i = 0; i < arr.length; i++) {
			ArrayList<Wall> tmp = new ArrayList<Wall>();
			for (int n = 0; n < arr[i].length; n++) {
				int posx, posz;
				posx = arr.length - i;
				posz = n;
				char c = arr[i][n];
				switch (c) {
				case 'c':
					tmp.add(new Wall(Wall.Types.wall, posx, posz));
					break;
				case 'r':
					tmp.add(new Wall(Wall.Types.redDoor, posx, posz));
					break;
				case 'g':
					tmp.add(new Wall(Wall.Types.greenDoor, posx, posz));
					break;
				case 'b':
					tmp.add(new Wall(Wall.Types.blueDoor, posx, posz));
					break;
				case 'p':
					tmp.add(new Wall(Wall.Types.purpleDoor, posx, posz));
					break;
				default: break;
				}
			}
			result.add(tmp);
		}
		
		return result;
	}
	
	private ArrayList<ArrayList<Floor>> floorFromArray(char[][] arr) {
		ArrayList<ArrayList<Floor>> result = new ArrayList<ArrayList<Floor>>();
		for (int i = 0; i < arr.length; i++) {
			ArrayList<Floor> tmp = new ArrayList<Floor>();
			for (int n = 0; n < arr[i].length; n++) {
				int posx, posz;
				posx = arr.length - i;
				posz = n;
				char c = arr[i][n];
				switch (c) {
				case 'c':
					tmp.add(new Floor(Floor.Types.cobble, posx, posz));
					break;
				default: break;
				}
			}
			result.add(tmp);
		}
		
		return result;
	}
}
