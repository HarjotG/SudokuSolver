import java.util.Scanner;

public class Sudoku {

    public final static int SIZE = 9;

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        System.out.println("If the sudoku is solved with zeros, please be sure to re-run the program");
        System.out.println("Do you want to input the solved sudoku puzzle in the website? (Y/N)");
        String in = scanner.nextLine();
        Web website = new Web();
        int[][] sudoku = website.getSudoku();

        Solver sudokuSolver = new Solver(sudoku);
        sudokuSolver.solve();
        System.out.println("The solved sudoku puzzle is:");
        sudokuSolver.print();
        if (in.toUpperCase().equals("Y")) {
            website.solve(sudoku);
        }
    }
}
