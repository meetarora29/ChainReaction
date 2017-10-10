import java.io.*;
import java.util.*;

class Player {
	int color;
	static Grid grid;

	Player(int color) {
		this.color=color;
	}

	void takeTurn(int i, int j) {
		grid.setPosition(i, j, this);
		grid.display();
	}

	int getColor() {
		return color;
	}

	static void undo() {
		grid.undo();
		grid.display();
	}

	static void setGrid(int n, int m) {
		grid=new Grid(n, m);
	}
}

class Ball {
	int mass, color;

	Ball(int color) {
		this.color=color;
		mass=1;
	}

	Ball(Ball b) {
		mass=b.getMass();
		color=b.getColor();
	}

	public String toString() {
		return color+":"+mass;
	}

	public int getMass() {
		return mass;
	}

	public void increaseMass() {
		mass++;
	}

	public int getColor() {
		return color;
	}
}

class Grid {
	int n, m;
	Ball[][] matrix;
	Player current;
	int color;
	Ball[][] prev_state;

	Grid(int n, int m) {
		this.n=n;
		this.m=m;
		this.matrix=new Ball[n][m];
	}

	private void saveState() {
		prev_state=new Ball[n][m];
		for(int i=0;i<n;i++) {
			for(int j=0;j<m;j++) {
				if(matrix[i][j]!=null)
					prev_state[i][j]=new Ball(matrix[i][j]);
			}
		}
	}

	void undo() {
		matrix=prev_state;
	}

	boolean checkWin() {
		return true;
	}

	boolean checkValidity() {
		return true;
	}

	void setPosition(int i, int j, Player current) {
		saveState();
		this.current=current;
		color=current.getColor();
		// Check if valid position
		setMass(i, j);
		// Check if winning condition
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
			setMass(i, j+1);
			setMass(i, j-1);
			setMass(i+1, j);
		}
		else if(val==3) {
			if(i==1) {
				matrix[i-1][j-1]=null;
				setMass(i, j+1);
				setMass(i, j-1);
				setMass(i+1, j);
			}
			else if(i==10) {
				matrix[i-1][j-1]=null;
				setMass(i, j+1);
				setMass(i, j-1);
				setMass(i-1, j);
			}
			if(j==1) {
				matrix[i-1][j-1]=null;
				setMass(i, j+1);
				setMass(i-1, j);
				setMass(i+1, j);
			}
			else if(j==10) {
				matrix[i-1][j-1]=null;
				setMass(i-1, j);
				setMass(i, j-1);
				setMass(i+1, j);
			}
		}
		else if(val==2) {
			if(i==1 && j==1) {
				matrix[i-1][j-1]=null;
				setMass(i+1, j);
				setMass(i, j+1);
			}
			else if(i==1 && j==10) {
				matrix[i-1][j-1]=null;
				setMass(i, j-1);
				setMass(i+1, j);
			}
			else if(i==10 && j==1) {
				matrix[i-1][j-1]=null;
				setMass(i, j+1);
				setMass(i-1, j);
			}
			else if(i==10 && j==10) {
				matrix[i-1][j-1]=null;
				setMass(i, j-1);
				setMass(i-1, j);
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

	void display() {
		for(int i=0;i<n;i++) {
			for(int j=0;j<m;j++)
				System.out.print(matrix[i][j]+" ");
			System.out.println();
		}
	}
}

class Main {
	public static void main(String[] args) {
		Scanner Reader=new Scanner(System.in);
		System.out.println("Enter 10 10");
		int n=Reader.nextInt(); //10
		int m=Reader.nextInt(); //10
		Player.setGrid(n, m);
		Player[] player=new Player[2];
		player[0]=new Player(1);
		player[1]=new Player(2);
		for(int i=0;i<10;i++) {
			System.out.println("Player 1");
			int a=Reader.nextInt();
			int b=Reader.nextInt();
			if(a==0 && b==0) {
				Player.undo();
				continue;
			}
			player[0].takeTurn(a, b);
			System.out.println("Player 2");
			a=Reader.nextInt();
			b=Reader.nextInt();
			if(a==0 && b==0) {
				Player.undo();
				continue;
			}
			player[1].takeTurn(a, b);
		}
	}
}
