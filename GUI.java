import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.Graphics;
import java.awt.GridLayout;
import java.awt.Image;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JColorChooser;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.border.Border;
import javax.swing.border.LineBorder;

public class GUI implements MouseListener{

	private JFrame frame;
	private Game game;
	private JPanel topPanel;
	private JLabel gameOver;
	//private JPanel panel;
	Board board = new Board(10);
	private int size = board.getSize();
	private int bombCount = board.getBombCount();
	private JLabel [][] boxes = new JLabel[size][size];
	private String [][] tileCondition = new String[size][size];
	private int[][] tileContent = new int[size][size];
	private int [] bombs;
	private JLabel numBombs;
	private JLabel numFlags;
	private boolean gameIsOver=false;
	private int flagsMax = bombCount,flags =0;
	private ImageIcon unclicked = new ImageIcon("bin\\images\\facingDown.png");
	private ImageIcon flagged = new ImageIcon("bin\\images\\flagged.png");
	private ImageIcon empty = new ImageIcon("bin\\images\\0.png");
	private ImageIcon boom = new ImageIcon("bin\\images\\boom.png");
	private ImageIcon one = new ImageIcon("bin\\images\\1.png");
	private ImageIcon two = new ImageIcon("bin\\images\\2.png");
	private ImageIcon three = new ImageIcon("bin\\images\\3.png");
	private ImageIcon four = new ImageIcon("bin\\images\\4.png");
	private ImageIcon five = new ImageIcon("bin\\images\\5.png");
	private ImageIcon six = new ImageIcon("bin\\images\\6.png");
	private ImageIcon seven = new ImageIcon("bin\\images\\7.png");
	private ImageIcon eight = new ImageIcon("bin\\images\\8.png");
	public GUI() {
		bombs = board.getBombs();
		int trash=0;
		for(int i =0; i<size;i++) {
			for(int j =0; j<size;j++) {
				tileContent[i][j] = 0;
			}
		}
		for(int i = 0; i < bombs.length; i++) {
			trash = bombs[i];
			tileContent[trash/10][trash%10]=-1;
			int k = trash/10;
			int j = trash % 10;
			if(k>0) {
				if(k<9) {
					if(j>0) {
						if(j<9) {
							for(int q = k-1; q <= k+1;q++) {
								for(int r = j-1; r <=j+1;r++) {
									if(tileContent[q][r]!=-1) {
										tileContent[q][r]++;
									}
								}
							}
							
						}else if(j==9) {
							for(int q = k-1; q <= k+1;q++) {
								for(int r = j-1; r <=j;r++) {
									if(tileContent[q][r]!=-1) {
										tileContent[q][r]++;
									}
								}
							}
							
						}
					}else if(j==0) {
						for(int q = k-1; q <= k+1;q++) {
							for(int r = j; r <=j+1;r++) {
								if(tileContent[q][r]!=-1) {
									tileContent[q][r]++;
								}
							}
						}
						
					}
				}else if(k==9) {
					if(j>0) {
						if(j<9) {
							for(int q = k-1; q <= k;q++) {
								for(int r = j-1; r <=j+1;r++) {
									if(tileContent[q][r]!=-1) {
										tileContent[q][r]++;
									}
								}
							}
							
						}else if(j==9) {
							for(int q = k-1; q <= k;q++) {
								for(int r = j-1; r <=j;r++) {
									if(tileContent[q][r]!=-1) {
										tileContent[q][r]++;
									}
								}
							}
							
						}
					}else if(j==0) {
						for(int q = k-1; q <= k;q++) {
							for(int r = j; r <=j+1;r++) {
								if(tileContent[q][r]!=-1) {
									tileContent[q][r]++;
								}
							}
						}
						
					}
					
				}
			}else if(k==0) {
				if(j>0) {
					if(j<9) {
						for(int q = k; q <= k+1;q++) {
							for(int r = j-1; r <=j+1;r++) {
								if(tileContent[q][r]!=-1) {
									tileContent[q][r]++;
								}
							}
						}
						
					}else if(j==9) {
						for(int q = k; q <= k+1;q++) {
							for(int r = j-1; r <=j;r++) {
								if(tileContent[q][r]!=-1) {
									tileContent[q][r]++;
								}
							}
						}
						
					}
				}else if(j==0) {
					for(int q = k; q <= k+1;q++) {
						for(int r = j; r <=j+1;r++) {
							if(tileContent[q][r]!=-1) {
								tileContent[q][r]++;
							}
						}
					}
					
				}
			}
		}
		Image image = flagged.getImage(); // transform it 
		Image newimg = image.getScaledInstance(50, 50,  java.awt.Image.SCALE_SMOOTH); // scale it the smooth way  
		flagged = new ImageIcon(newimg);
		image = empty.getImage(); // transform it 
		newimg = image.getScaledInstance(50, 50,  java.awt.Image.SCALE_SMOOTH); // scale it the smooth way  
		empty = new ImageIcon(newimg);
		image = unclicked.getImage(); // transform it 
		newimg = image.getScaledInstance(50, 50,  java.awt.Image.SCALE_SMOOTH); // scale it the smooth way  
		unclicked = new ImageIcon(newimg);
		image = boom.getImage(); // transform it 
		newimg = image.getScaledInstance(50, 50,  java.awt.Image.SCALE_SMOOTH); // scale it the smooth way  
		boom = new ImageIcon(newimg);
		image = one.getImage(); // transform it 
		newimg = image.getScaledInstance(50, 50,  java.awt.Image.SCALE_SMOOTH); // scale it the smooth way  
		one = new ImageIcon(newimg);
		image = two.getImage(); // transform it 
		newimg = image.getScaledInstance(50, 50,  java.awt.Image.SCALE_SMOOTH); // scale it the smooth way  
		two = new ImageIcon(newimg);
		image = three.getImage(); // transform it 
		newimg = image.getScaledInstance(50, 50,  java.awt.Image.SCALE_SMOOTH); // scale it the smooth way  
		three = new ImageIcon(newimg);
		image = four.getImage(); // transform it 
		newimg = image.getScaledInstance(50, 50,  java.awt.Image.SCALE_SMOOTH); // scale it the smooth way  
		four = new ImageIcon(newimg);
		image = five.getImage(); // transform it 
		newimg = image.getScaledInstance(50, 50,  java.awt.Image.SCALE_SMOOTH); // scale it the smooth way  
		five = new ImageIcon(newimg);
		image = six.getImage(); // transform it 
		newimg = image.getScaledInstance(50, 50,  java.awt.Image.SCALE_SMOOTH); // scale it the smooth way  
		six = new ImageIcon(newimg);
		image = seven.getImage(); // transform it 
		newimg = image.getScaledInstance(50, 50,  java.awt.Image.SCALE_SMOOTH); // scale it the smooth way  
		seven = new ImageIcon(newimg);
		image = eight.getImage(); // transform it 
		newimg = image.getScaledInstance(50, 50,  java.awt.Image.SCALE_SMOOTH); // scale it the smooth way  
		eight = new ImageIcon(newimg);
		frame = new JFrame();
		game = new Game();
		numFlags = new JLabel("Flags: "+flags);
		numBombs = new JLabel("Bombs: "+bombCount);
		topPanel=new JPanel();//create top panel
	    topPanel.setLayout(new FlowLayout());//set layout
	    topPanel.add(numFlags);//add button to set 1 player
	    topPanel.add(numBombs);//add button to set 2 players
	    frame.add(topPanel);
	    frame.addMouseListener(this);
		frame.add(game,BorderLayout.SOUTH);
		frame.setPreferredSize(new Dimension(500,575));
		frame.setTitle("Minesweeper");
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.pack();
		frame.setVisible(true);
		
	}
	public class Game extends JPanel{
		public Game() {
			this.setMaximumSize(new Dimension(500,500));
			this.setPreferredSize(new Dimension(500,500));
			this.setLayout(new GridLayout(10,10));
			//this.addMouseListener(this);
			//ImageIcon imageIcon = new ImageIcon("C:\\Users\\Student\\eclipse-workspace\\MinesweeperGUI\\bin\\images\\facingDown.png"); // load the image to a imageIcon
			Image image = unclicked.getImage(); // transform it 
			Image newimg = image.getScaledInstance(50, 50,  java.awt.Image.SCALE_SMOOTH); // scale it the smooth way  
			unclicked = new ImageIcon(newimg);
			//JLabel label = new JLabel();
			//label.setIcon(unclicked);
			//boxes = new JLabel[10][10];
			//tileCondition = new String[10][10];
			for(int i = 0; i < size; i++) {
				for(int j =0; j < size; j++) {
					boxes[i][j]=new JLabel(unclicked);
					tileCondition[i][j] = "unclicked";
					//boxes[i][j].setIcon(unclicked);
					//tiles[i][j].setBorderPainted(false);
					boxes[i][j].setPreferredSize(new Dimension(50,50));
					boxes[i][j].setBorder(new LineBorder(Color.BLACK,1));
					boxes[i][j].setBackground(Color.DARK_GRAY);
					boxes[i][j].setBounds((j*50),(i*50),50,50);
					//tiles[i][j].addActionListener(this);
					this.add(boxes[i][j]);
				}
			}
		}
		/*@Override
		public void actionPerformed(ActionEvent e) {
			// TODO Auto-generated method stub
			Image image = empty.getImage(); // transform it 
			Image newimg = image.getScaledInstance(50, 50,  java.awt.Image.SCALE_SMOOTH); // scale it the smooth way  
			empty = new ImageIcon(newimg);
			for(int i = 0; i < 10; i++) {
				for(int j =0; j < 10; j++) {
					if (e.getSource()==tiles[i][j]) {
						tileCondition[i][j] = "empty";
						tiles[i][j].setIcon(empty);
						tiles[i][j].setBorderPainted(false);
						tiles[i][j].setPreferredSize(new Dimension(50,50));
						tiles[i][j].setBackground(Color.DARK_GRAY);
						tiles[i][j].setBounds((j*50),(i*50),50,50);
						this.add(tiles[i][j]);
					}
				}
			}
		}*/
		public void paintComponent(Graphics g) {
			g.setColor(Color.DARK_GRAY);
			g.fillRect(0, 0, 800, 800);
			g.setColor(Color.GRAY);
			
		}
		
	}
	@Override
	public void mouseClicked(MouseEvent e) {
		// TODO Auto-generated method stub
		
		int x = e.getX();
		int y = e.getY();
		int i = (y-56)/50;
		int j = x/50;
		System.out.println(x+", "+y);
		System.out.println(j+", "+i);
		if(!gameIsOver) {
			if(e.getButton() == MouseEvent.BUTTON1&&!tileCondition[i][j].equals("flagged")) {
				System.out.println("left");
				this.open(i, j);
				this.gameWon();
				//boxes[i][j].setPreferredSize(new Dimension(50,50));
				//boxes[i][j].setBackground(Color.DARK_GRAY);
				//boxes[i][j].setBounds((j*50),(i*50),50,50);
				//board.add(boxes[i][j]);
			}else if (e.getButton()== MouseEvent.BUTTON3) {
				if(tileCondition[i][j].equals("unclicked")&& !(flags == flagsMax)) {
					System.out.println("right");
					tileCondition[i][j] = "flagged";
					//bombs--;
					boxes[i][j].setIcon(flagged);
					//boxes[i][j].setPreferredSize(new Dimension(50,50));
					//boxes[i][j].setBackground(Color.DARK_GRAY);
					//boxes[i][j].setBounds((j*50),(i*50),50,50);
					//board.add(boxes[i][j]);
					flags++;
					numFlags.setText("Flags: "+flags);
					this.gameWon();
				}else if(tileCondition[i][j].equals("flagged")) {
					System.out.println("unclicked");
					tileCondition[i][j] = "unclicked";
					boxes[i][j].setIcon(unclicked);
					flags--;
					//bombs++;
					//tiles[i][j].setBorderPainted(false);
					//boxes[i][j].setPreferredSize(new Dimension(50,50));
					//boxes[i][j].setBorder(new LineBorder(Color.BLACK,1));
					//boxes[i][j].setBackground(Color.DARK_GRAY);
					//boxes[i][j].setBounds((j*50),(i*50),50,50);
					//tiles[i][j].addActionListener(this);
					//board.add(boxes[i][j]);
					//numBombs.setText("Bombs: "+bombs);
					numFlags.setText("Flags: "+flags);
					this.gameWon();
					//numBombs.setText("Bombs: "+bombs);
					//numFlags.setText("Flags: "+flags);
				}
				//boxes[i][j]=new JLabel();
				
			}
		}
	}
	public void bombClicked() {
		System.out.println("game over");
		topPanel.setBackground(new Color(250, 42, 42));
		gameOver = new JLabel("Game Over");
		topPanel.remove(numFlags);
		numBombs.setText("GAME OVER!");
		gameIsOver=true;

		this.openBombs();
	}
	public void openBombs() {
		for(int i=0; i <size;i++) {
			for(int j=0; j<size;j++) {
				if(tileContent[i][j]==-1) {
					this.open(i,j);
				}
			}
		}
	}
	public void open(int i,int j) {
		if(!tileCondition[i][j].equals("unclicked")) {
			return;
		}
		if(tileContent[i][j]==0) {
			tileCondition[i][j] = "empty";
			boxes[i][j].setIcon(empty);
			if(i>0) {
				if(i<9) {
					if(j>0) {
						if(j<9) {
							this.open(i-1, j+1);
							this.open(i-1, j);
							this.open(i-1, j-1);
							this.open(i, j+1);
							this.open(i, j-1);
							this.open(i+1, j+1);
							this.open(i+1, j);
							this.open(i+1, j-1);
							
						}else if(j==9) {
							this.open(i-1, j);
							this.open(i-1, j-1);
							this.open(i, j-1);
							this.open(i+1, j);
							this.open(i+1, j-1);
						}
					}else if(j==0) {
						this.open(i-1, j);
						this.open(i-1, j+1);
						this.open(i, j+1);
						this.open(i+1, j);
						this.open(i+1, j+1);
					}
				}else if(i==9) {
					if(j>0) {
						if(j<9) {
							this.open(i, j+1);
							this.open(i, j-1);
							this.open(i-1, j-1);
							this.open(i-1, j);
							this.open(i-1, j+1);
						}else if(j==9) {
							this.open(i, j-1);
							this.open(i-1, j-1);
							this.open(i-1, j);
						}
					}else if(j==0) {
						this.open(i, j+1);
						this.open(i-1, j+1);
						this.open(i-1, j);
					}
				}
			}else if(i==0) {
				if(j>0) {
					if(j<9) {
						this.open(i, j+1);
						this.open(i, j-1);
						this.open(i+1, j-1);
						this.open(i+1, j);
						this.open(i+1, j+1);
					}else if(j==9) {
						this.open(i, j-1);
						this.open(i+1, j-1);
						this.open(i+1, j);
					}
				}else if(j==0) {
					this.open(i, j+1);
					this.open(i+1, j+1);
					this.open(i+1, j);
				}
			}
		}else if(tileContent[i][j]==-1) {
			tileCondition[i][j]="boom";
			boxes[i][j].setIcon(boom);
			this.bombClicked();
		}else if(tileContent[i][j]==1) {
			tileCondition[i][j]="one";
			boxes[i][j].setIcon(one);
		}
		else if(tileContent[i][j]==2) {
			tileCondition[i][j]="two";
			boxes[i][j].setIcon(two);
		}
		else if(tileContent[i][j]==3) {
			tileCondition[i][j]="three";
			boxes[i][j].setIcon(three);
		}
		else if(tileContent[i][j]==4) {
			tileCondition[i][j]="four";
			boxes[i][j].setIcon(four);
		}
		else if(tileContent[i][j]==5) {
			tileCondition[i][j]="five";
			boxes[i][j].setIcon(five);
		}
		else if(tileContent[i][j]==6) {
			tileCondition[i][j]="six";
			boxes[i][j].setIcon(six);
		}else if(tileContent[i][j]==7) {
			tileCondition[i][j]="seven";
			boxes[i][j].setIcon(seven);
		}else if(tileContent[i][j]==8) {
			tileCondition[i][j]="eight";
			boxes[i][j].setIcon(eight);
		}
	}
	public int checkNeighbors(int i, int j) {
		int count = 0;
		
		
		return count;
	}
	public boolean gameWon() {
		for(int i = 0; i <size; i++) {
			for(int j =0;j<size;j++) {
				if(tileCondition[i][j].equals("unclicked")) {
					return false;
				}
				if(tileCondition[i][j].equals("boom")) {
					return false;
				}
			}
		}
		topPanel.setBackground(new Color(52,250,36));
		//gameOver = new JLabel("You Win");
		topPanel.remove(numFlags);
		numBombs.setText("YOU WIN!");
		gameIsOver=true;
		return true;
	}
	@Override
	public void mousePressed(MouseEvent e) {
		// TODO Auto-generated method stub
		//System.out.println("clicked");
	}
	@Override
	public void mouseReleased(MouseEvent e) {
		// TODO Auto-generated method stub
		//System.out.println("clicked");
	}
	@Override
	public void mouseEntered(MouseEvent e) {
		// TODO Auto-generated method stub
		//System.out.println("clicked");
	}
	@Override
	public void mouseExited(MouseEvent e) {
		// TODO Auto-generated method stub
		//System.out.println("clicked");
	}
	public static void main(String[] args) {
		// TODO Auto-generated method stub
		new GUI();

	}
	
}