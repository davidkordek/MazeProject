package maze;


public class Main {

    public static void main(String[] args) {
        MazeReader mazeReader = new MazeReader();

        MazeSolver mazeSolver = new MazeSolver(mazeReader.getMaze());

        mazeReader.toString();
        System.out.println();
        mazeSolver.findStart();

        System.out.println("Shortest Path: ");
        System.out.println();
        System.out.println(mazeReader.toString());

    }
}
