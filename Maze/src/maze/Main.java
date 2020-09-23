package maze;


public class Main {

    public static void main(String[] args) {
        MazeReader mazeReader = new MazeReader();

        MazeSolver mazeSolver = new MazeSolver(mazeReader.getMaze());

        System.out.println(mazeReader.toString());
        System.out.println(mazeReader.fromChar('#'));
        System.out.println(mazeReader.toString(0,4));
        mazeSolver.findStart();
        mazeSolver.solveMaze(mazeReader.getMaze());
        System.out.println(mazeReader.toString());
        Node n = mazeSolver.solveMaze(mazeReader.getMaze());
        mazeSolver.printPath(n);
    }
}
