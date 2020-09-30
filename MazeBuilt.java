package maze;

import java.awt.*;
import java.util.ArrayList;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.*;

public class MazeBuilt{
	private JFrame  frame;
	private JTextField textFieldStatus= null;
	private JButton btnLoad = null;
	private JButton btnStart = null;
	private MazeReader maz;
	private MazeSolver mazSolv;

	// The maze to be displayed

	private char[][] maze;

	// Path
	private final ArrayList<Node> path = new ArrayList<Node>();
	private MazePanel maze1 = new MazePanel();
	private int pathIndex;
	private int draw;

	/**
	 * Create the application.
	 */
	public MazeBuilt() {
		System.out.println("Hi");
		btnLoad = new JButton("Load");
		btnLoad.setFont(new Font("Tahoma", Font.BOLD, 11));
		btnLoad.setBounds(22, 11, 69, 23);
		btnLoad.addActionListener(new ActionListener(){
            @Override
            public void actionPerformed(ActionEvent e) {
			   draw = 2;
			   maze1.repaint();

            }
		});	
		System.out.println("Hi1");

		btnStart = new JButton("Start");
		btnStart.setFont(new Font("Tahoma", Font.BOLD, 11));
		btnStart.setBounds(101, 11, 69, 23);

		btnStart.addActionListener(new ActionListener(){
            @Override
            public void actionPerformed(ActionEvent e) {
				draw = 1;
				maze1.repaint();

            }
		});	
		System.out.println("Hi3");

		frame = new JFrame("Maze");
		
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.setBounds(100, 100, 750, 650);

		textFieldStatus = new JTextField();
		textFieldStatus.setFont(new Font("Tahoma", Font.BOLD, 12));
		textFieldStatus.setEditable(false);
		textFieldStatus.setBounds(195, 11, 206, 20);
		textFieldStatus.setColumns(10);

		frame.add(textFieldStatus);
		frame.add(btnLoad,BorderLayout.BEFORE_FIRST_LINE);
		frame.add(btnStart,BorderLayout.PAGE_START);
		frame.add(maze1);
		frame.setVisible(true);
		System.out.println("Hi4");


	}

	/**
	 * Draw the maze
	 */
	private class MazePanel extends JPanel{

		private static final long serialVersionUID = 1L;

		@Override
		public void paintComponent(Graphics g) {
			super.paintComponent(g);
	
			if(draw == 2){

				maz = new MazeReader();
				maze = maz.getMaze();
				mazSolv = new MazeSolver(maze);
		
				mazSolv.findStart();
				g.translate(50, 50);
				for (int row = 0; row < maze.length; row++) {
					for (int col = 0; col < maze[0].length; col++) {
						Color color;
						switch (maze[row][col]) {
							case '#':
								color = Color.BLACK;
								break;
							case 'o':
								color = Color.BLUE;
								break;
							case '*':
								color = Color.GREEN;
								break;
							default:
								color = Color.WHITE;
						}
						g.setColor(color);
						g.fillRect(50 * col, 50 * row, 50, 50);
						g.setColor(Color.BLACK);
						g.drawRect(50 * col, 50 * row, 50, 50);
					}
				}
			}
			if(draw == 1){
				Node n = mazSolv.solveMaze(path);
	
				for (int p = 0; p < path.size(); p++) {
					final int pathX = path.get(p).x;
					final int pathY = path.get(p).y;
					g.setColor(Color.LIGHT_GRAY);
					g.fillRect(pathX * 50, pathY * 50, 50, 50);
				}
		
				while (n.getN() != null) {
					//System.out.println(n);
					n = n.getN();
					final int pathX = n.x;
					final int pathY = n.y;
					g.setColor(Color.red);
					g.fillOval(pathX * 50, pathY * 50, 50, 50);
				}
			}
			
			
		}

	}
	/**
	 * Launch the application.
	 */
	public static void main(final String[] args) {
		SwingUtilities.invokeLater(new Runnable() {
			public void run() {
				try {
					MazeBuilt m = new MazeBuilt();
					System.out.println("mazebuilt finished");

				} catch (final Exception e) {
					e.printStackTrace();
				}
			}
		});
	}
}

