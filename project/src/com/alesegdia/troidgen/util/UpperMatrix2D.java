package com.alesegdia.troidgen.util;

public class UpperMatrix2D<T> extends Matrix2D<T> {
	
	public UpperMatrix2D( int rows, int cols, T def )
	{
		super(rows, cols, def);
	}

	public UpperMatrix2D(UpperMatrix2D<T> simplifiedConnectionMatrix) {
		super(simplifiedConnectionMatrix);
	}

	public T GetUpper( int row, int col ) {
		if( row < col ) {
			return super.Get(row, col);
		} else {
			return super.Get(col, row);
		}
	}
	
	public void SetUpper( int row, int col, T val ) {
		if( row < col ) {
			super.Set(row, col, val);
		} else {
			super.Set(col, row, val);
		}
	}

	public void Debug() {
		for( int i = 0; i < cols; i++ ) {
			for( int j = 0; j < rows; j++ ) {
				System.out.print(super.Get(i,j) + "\t");
			}
			System.out.println();
		}
	}

	public UpperMatrix2D<T> Clone() {
		return new UpperMatrix2D<T>(this);
	}

}
