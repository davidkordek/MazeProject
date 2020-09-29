package maze;
import java.awt.*;
import java.util.ArrayList;

import javax.swing.*;

public class MazeBuilt extends JFrame{

	/**
     *
     */
    private static final long serialVersionUID = 1L;

    private JFrame frmMaze;
	private JTextField textFieldStatus;
	MazeReader maz;
	MazeSolver mazSolv;
	
	//The maze to be displayed
	
	private char [][] maze;
	
	//Path
	private final ArrayList<Node> path = new ArrayList<Node>();
	private int pathIndex;
	/**
	 * Draw the maze
	 */
	@Override
    public void paint(Graphics g) {
		super.paint(g);
		
		maz = new MazeReader();
		maze = maz.getMaze();
		mazSolv = new MazeSolver(maze);
		
		mazSolv.findStart();
		g.translate(50, 50);
		for (int row = 0; row < maze.length; row++) {
			for (int col = 0; col < maze[0].length; col++) {
				Color color;
				switch (maze[row][col]) {
					case '#': color = Color.BLACK; break;
					case 'o': color = Color.BLUE; break;
					case '*': color = Color.GREEN; break;
					default: color = Color.WHITE; 
				}
				g.setColor(color);
				g.fillRect(50 * col, 50 * row, 50, 50);
				g.setColor(Color.BLACK);
				g.drawRect(50 * col, 50 * row, 50, 50);
			}
		}
		Node n = mazSolv.solveMaze(path);

		for (int p = 0; p < path.size();p++) {
			int pathX = path.get(p).x;
			int pathY = path.get(p).y;
			g.setColor(Color.LIGHT_GRAY);
			g.fillRect(pathX * 50, pathY * 50, 50, 50);
		}
		
		while(n.getN() != null){
            System.out.println(n);
			n = n.getN();
			int pathX = n.x;
			int pathY = n.y;
			g.setColor(Color.red);
			g.fillOval(pathX * 50, pathY * 50, 50, 50);
        }
		
	}
	
	/**
	 * Initialize the contents of the frame.
	 */
	private void initialize() {
		frmMaze = new JFrame();
		frmMaze.setTitle("Maze");
		frmMaze.setBounds(100, 100, 450, 300);
		frmMaze.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frmMaze.getContentPane().setLayout(null);
		
		JButton btnLoad = new JButton("Load");
		btnLoad.setFont(new Font("Tahoma", Font.BOLD, 11));
		btnLoad.setBounds(22, 11, 69, 23);
		frmMaze.getContentPane().add(btnLoad);
		
		JButton btnStart = new JButton("Start");
		btnStart.setFont(new Font("Tahoma", Font.BOLD, 11));
		btnStart.setBounds(101, 11, 69, 23);
		frmMaze.getContentPane().add(btnStart);
		
		textFieldStatus = new JTextField();
		textFieldStatus.setFont(new Font("Tahoma", Font.BOLD, 12));
		textFieldStatus.setEditable(false);
		textFieldStatus.setBounds(195, 11, 206, 20);
		frmMaze.getContentPane().add(textFieldStatus);
		textFieldStatus.setColumns(10);
    }
    
    	/**
	 * Create the application.
	 */
	public MazeBuilt() {
		
		setTitle("Maze");
		setBounds(100, 100, 750, 650);
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		textFieldStatus = new JTextField("Hello");
		textFieldStatus.setFont(new Font("Tahoma", Font.BOLD, 12));
		textFieldStatus.setEditable(false);
		textFieldStatus.setBounds(0, 0, 0, 0);
		
		//DFS.searchPath(maze, 1, 1, path);
		pathIndex = path.size() - 2;
		System.out.println(path);
	}
 
    /**
    *  Launch the application.
    */
    public static void main(String[] args) {
        SwingUtilities.invokeLater(new Runnable() {
            public void run() {
                try {
                    MazeBuilt window = new MazeBuilt();
					window.setVisible(true);
					
                    
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
        });
    }
}

