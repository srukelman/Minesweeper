
import java.util.Random;
import java.util.Scanner;
public class Board {
	private int[]bombs;
	private Tile[] tiles;
	private int size;
	private int bombCount;
	private boolean gameIsOver = false;
	
	public Board(int size) {
		
		this.size = size;
		
		tiles = new Tile[size*size];
		
		for(int i=0;i<size*size;i++) {
			tiles[i]=new Tile(i, this);
		}
		
		bombCount = (int)(size*1.2);
		
		bombs = new Random().ints(0,size*size).distinct().limit(bombCount).toArray();
		
		for (int i: bombs) {
			tiles[i].placeMine();
		}
		
		for(int i = 0; i<size*size; i++) {
				if(!tiles[i].isABomb()) {
					for(int j : tiles[i].getNeighbors()) {
					if(tiles[j].getPrint()==-1) {tiles[i].setPrint(1);}
					}
				}
		}
	}

	
	public static Board createNew(int n) {
		Board B = new Board(n);
		return B;
	}
	public void checkGame() {
		for(int i =0;i<size*size;i++) {
			if(///*digger mode:*/!(tiles[i].isBomb ^ tiles[i].isOpen)) 
			/*flagger mode:*/ tiles[i].isABomb() ^ (tiles[i].isAFlag() && !tiles[i].isAOpen()))
			{return;}
			}
		gameIsOver = true;
		System.out.println("YOU WIN!");
	}
	
	@Override
	public String toString() {
		String printer = new String();
		printer += "Game\t";
		for(int i = 0;i<size;i++) {printer+=((i+1)+"\t");}
		printer+="\n";
		for(int i = 0; i<size; i++) {
			printer+=(i*size+"\t");
			for(int j= 0;j<size;j++) {
				printer+= tiles[size*i+j].toString();
			}
			printer+="\n";
		}
		return printer;
	}
	public String help() {
		String printer = new String();
		printer += "Answer\t";
		for(int i =0; i<size;i++) {printer+=((i+1)+"\t");}
		printer += "\n";
		for(int i = 0; i<size; i++) {
			printer+=(i*size+"\t");
			for(int j= 0;j<size;j++) {
				printer+= tiles[size*i+j].getPrint()+"\t";
			}
			printer+="\n";
		}
		return printer;
	}
	public int getSize() {
		return size;
	}
	public int getBombCount() {
		return bombCount;
	}
	public void setGameOver(boolean b) {
		gameIsOver = b;
	}
	public Tile[] getTiles() {
		return tiles;
	}
	public int[] getBombs() {
		return bombs;
	}
	public static void Run(int boardSize) {
		Board B = new Board(boardSize);
		System.out.println(B);
		int spot;
		Scanner s = new Scanner(System.in);
		while(!B.gameIsOver){
			System.out.println("Enter an integer from 1 to "+ boardSize*boardSize+" to open a spot, or type a negative integer from -"+boardSize*boardSize+" to -1 to flag/unflag that spot");
			spot = s.nextInt();
			if(spot ==0) {
				System.out.println(B.help());
			}
			else if(spot>0) {
				B.tiles[spot-1].Open(B);
			}
			else {
				B.tiles[-1-spot].flipFlag();
			}
			System.out.println(B);
			B.checkGame();
		}
		s.close();
	}
}
