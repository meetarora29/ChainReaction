import java.io.*;
import java.util.*;

class Ball {
	int mass, color;

	Ball(int color) {
		this.color=color;
		mass=1;
	}

	public String toString() {
		return color+":"+mass;
	}
}

class Grid {
	int i, j;
	Ball[][] matrix;
	int color;

	Grid(Ball[][] matrix, int color) {
		this.matrix=matrix;
		this.color=color;
	}

	void setPosition(int i, int j) {
		this.i=i;
		this.j=j;
		setMass(i, j);
	}

	void setMass(int i, int j) {
		if(matrix[i-1][j-1]!=null){
			if(matrix[i-1][j-1].color!=color)
				matrix[i-1][j-1].color=color;
			matrix[i-1][j-1].mass++;
		}
		else
			matrix[i-1][j-1]=new Ball(color);
		checkMass(i, j);
	}

	int getMass(int i, int j) {
		if(matrix[i-1][j-1]==null)
			matrix[i-1][j-1]=new Ball(color);
		return matrix[i-1][j-1].mass;
	}

	void burst(int val, int i, int j) {
		if(val==4) {
			matrix[i-1][j-1]=null;
			setMass(i-1, j);
			checkMass(i-1, j);
			setMass(i, j+1);
			checkMass(i, j+1);
			setMass(i, j-1);
			checkMass(i, j-1);
			setMass(i+1, j);
			checkMass(i+1, j);
		}
		else if(val==3) {
			if(i==1) {
				matrix[i-1][j-1]=null;
				setMass(i, j+1);
				checkMass(i, j+1);
				setMass(i, j-1);
				checkMass(i, j-1);
				setMass(i+1, j);
				checkMass(i+1, j);
			}
			else if(i==10) {
				matrix[i-1][j-1]=null;
				setMass(i, j+1);
				checkMass(i, j+1);
				setMass(i, j-1);
				checkMass(i, j-1);
				setMass(i-1, j);
				checkMass(i-1, j);
			}
			if(j==1) {
				matrix[i-1][j-1]=null;
				setMass(i, j+1);
				checkMass(i, j+1);
				setMass(i-1, j);
				checkMass(i-1, j);
				setMass(i+1, j);
				checkMass(i+1, j);
			}
			else if(j==10) {
				matrix[i-1][j-1]=null;
				setMass(i-1, j);
				checkMass(i-1, j);
				setMass(i, j-1);
				checkMass(i, j-1);
				setMass(i+1, j);
				checkMass(i+1, j);
			}
		}
		else if(val==2) {
			if(i==1 && j==1) {
				matrix[i-1][j-1]=null;
				setMass(i+1, j);
				checkMass(i+1, j);
				setMass(i, j+1);
				checkMass(i, j+1);
			}
			else if(i==1 && j==10) {
				matrix[i-1][j-1]=null;
				setMass(i, j-1);
				checkMass(i, j-1);
				setMass(i+1, j);
				checkMass(i+1, j);
			}
			else if(i==10 && j==1) {
				matrix[i-1][j-1]=null;
				setMass(i, j+1);
				checkMass(i, j+1);
				setMass(i-1, j);
				checkMass(i-1, j);
			}
			else if(i==10 && j==10) {
				matrix[i-1][j-1]=null;
				setMass(i, j-1);
				checkMass(i, j-1);
				setMass(i-1, j);
				checkMass(i-1, j);
			}
		}
	}

	void checkMass(int i, int j) {
		if(i>1 && i<10 && j>1 && j<10) {
			if(getMass(i, j)==4)
				burst(4, i, j);
		}
		else if((i==1 && (j==1 || j==10)) || (i==10 && (j==1 || j==10))) {
			if(getMass(i, j)==2)
				burst(2, i, j);
		}
		else {
			if(getMass(i, j)==3)
				burst(3, i, j);
		}
	}
}

class Main {
	public static void display(Ball[][] grid) {
		for(int i=0;i<10;i++) {
			for(int j=0;j<10;j++)
				System.out.print(grid[i][j]+" ");
			System.out.println();
		}
	}

	public static void main(String[] args) {
		Scanner Reader=new Scanner(System.in);
		Ball[][] grid=new Ball[10][10];
		Grid[] player=new Grid[2];
		player[0]=new Grid(grid, 1);
		player[1]=new Grid(grid, 2);
		for(int i=0;i<10;i++) {
			System.out.println("Player 1");
			int a=Reader.nextInt();
			int b=Reader.nextInt();
			player[0].setPosition(a, b);
			display(grid);
			System.out.println("Player 2");
			a=Reader.nextInt();
			b=Reader.nextInt();
			player[1].setPosition(a, b);
			display(grid);
		}
	}
}
