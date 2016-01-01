package com.alesegdia.troidgen;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import com.alesegdia.troidgen.util.UpperMatrix2D;

public class FloydWarshallSolver {


	public FloydWarshallSolver() {
	}

	UpperMatrix2D<Float> min_distances;
	private int spawn_room;
	private int goal_room;
	private float distance;
	private List<Integer> path;
	
	public void Solve(UpperMatrix2D<Float> matrix) {
		min_distances = matrix;
		UpperMatrix2D<Integer> next = new UpperMatrix2D<Integer>(matrix.cols, matrix.rows, -1);

		List<Integer> used = new ArrayList<Integer>();
		boolean ok = false;
		for( int i = 0; i < matrix.cols; i++ ) {
			ok = false;
			for( int j = 0; j < matrix.cols; j++ ) {
				if( i != j && matrix.GetUpper(i, j) != Float.MAX_VALUE ) {
					if( !ok ) used.add(i);
					next.Set(i,j,j);
					ok = true;
				}
			}
		}
		
		for( int i = 0; i < matrix.cols; i++ ) {
			min_distances.Set(i, i, 0.f);
		}
		
		// no debería ser necesario, ordenado por definición
		Collections.sort(used);
		
		for( int ki = 0; ki < used.size(); ki++ ) { int k = used.get(ki);
			for( int ii = 0; ii < used.size(); ii++ ) { int i = used.get(ii);
				for( int ji = ii; ji < used.size(); ji++ ) { int j = used.get(ji);
					if( min_distances.GetUpper(i, j) > min_distances.GetUpper(i, k) + min_distances.GetUpper(k, j)) {
						float newvalue =              min_distances.GetUpper(i, k) + min_distances.GetUpper(k, j);
						min_distances.SetUpper(i, j, newvalue);
						next.Set(i, j, next.Get(i, k));
						next.Set(j, i, next.Get(j, k));
					}
				}
			}
		}
		
		int biggest_r1 = 0, biggest_r2 = 0;
		float biggest_value = Float.MIN_VALUE;
		for( int i = 0; i < matrix.cols; i++ ) {
			for( int j = 0; j < matrix.cols; j++ ) {
				float val = min_distances.GetUpper(i, j);
				if( val > 0.1f && val < 100000000f && val > biggest_value ) {
					biggest_r1 = i;
					biggest_r2 = j;
					biggest_value = val;
				}
			}
		}
		this.spawn_room = biggest_r1;
		this.goal_room = biggest_r2;
		this.distance = biggest_value;
		
		int u = this.spawn_room;
		this.path = new ArrayList<Integer>();
		path.add(u);
		final int v = this.goal_room;
		while( u != v ) {
			u = next.Get(u, v);
			path.add(u);
		}

		//System.out.println("PATH: " + path);
	}

	public int GetSpawnRoom() {
		return spawn_room;
	}

	public int GetGoalRoom() {
		return goal_room;
	}

	public float GetDistance() {
		return distance;
	}
	
	public List<Integer> GetPath() {
		return path;
	}

}
