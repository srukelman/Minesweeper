
import java.util.ArrayList;

public class Tile {
	private int print=0;
	private ArrayList<Integer> neighbors;
	private boolean isBomb = false;
	private boolean isOpen = false;
	private boolean isFlag = false;
	private int[] bombs;
	int spot;
	private Tile[] tiles;
	
	public Tile(int spot, Board B) {
		tiles = B.getTiles();
		bombs = B.getBombs();
		this.spot=spot;
		int size = B.getSize();
		neighbors = new ArrayList<Integer>();
		//if(spot>=size*size){do nothing;}
		if(spot/size!=0) {neighbors.add(spot-size);}
		if(spot/size!=size-1) {neighbors.add(spot+size);}
		if(spot%size!=0) {neighbors.add(spot-1);}
		if(spot%size!=size-1) {neighbors.add(spot+1);}
		if(spot/size!=0&&spot%size!=0) {neighbors.add(spot-size-1);}
		if(spot/size!=size-1&&spot%size!=size-1) {neighbors.add(spot+size+1);}
		if(spot/size!=0&&spot%size!=size-1) {neighbors.add(spot-size+1);}
		if(spot/size!=size-1&&spot%size!=0) {neighbors.add(spot+size-1);}
		}
	
	public void Open(Board B) {
		if(isOpen||isFlag) {return;}
		if(isBomb) {
			for(int j : bombs) {
				tiles[j].isOpen=true;
			}
			B.setGameOver(true);
			System.out.println("YOU LOSE.");
		}
		else if(print==0) {
			isOpen = true;
			for(int j : neighbors) {
				tiles[j].Open(B);
			}
		}
		else {
			isOpen=true;
		}
	}
	
	public void Flag() {
		isFlag=true;
	}
	public void unFlag() {
		isFlag=false;
	}
	public void flipFlag() {
		isFlag = !isFlag;
	}
	public void placeMine() {
		isBomb = true;
		print = -1;
	}
	public boolean isABomb() {
		return isBomb;
	}
	public boolean isAFlag() {
		return isFlag;
	}
	public boolean isAOpen() {
		return isOpen;
	}
	public int getPrint() {
		return print;
	}
	public void setPrint(int x) {
		print+=x;
	}
	public ArrayList<Integer> getNeighbors(){
		return neighbors;
	}
	@Override
	public String toString() {
		if(isFlag) {return "F\t";}
		if(!isOpen) {return "?\t";}
		else {
			return print+"\t";
		}
	}
	
}
