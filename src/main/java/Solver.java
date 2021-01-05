public class Solver {
    private boolean solved = false;
    private int[][] sudoku;


    /**
     * Constructor for the Solver
     *
     * @param sudoku The sudoku puzzle to solve
     */
    public Solver(int[][] sudoku) {
        this.sudoku = sudoku;
    }

    /**
     * Solves the sudoku puzzle.
     */
    public void solve() {
        solve(0, 0);
    }

    /**
     * Prints the current state of the sudoku puzzle to the console.
     */
    public void print() {
        System.out.println("----------------------");
        for (int i = 0; i < Sudoku.SIZE; i++) {
            for (int j = 0; j < Sudoku.SIZE; j++) {
                if (j == 0) {
                    System.out.print("|");
                }
                System.out.print(sudoku[i][j] + " ");
                if (j % 3 == 2 && j != 0) {
                    System.out.print("|");
                }
            }
            System.out.println();
            if (i % 3 == 2 && i != 0) {
                System.out.println("----------------------");
            }
        }
        System.out.println();
    }

    /**
     * Helper method to solve(). Solves the sudoku puzzle.
     *
     * @param row The current row to solve
     * @param col The current column to solve
     */
    private void solve(int row, int col) {
        if (solved) {
            return;
        }
        if (row >= Sudoku.SIZE) {
            solved = true;
        }

        for (int i = 1; i <= Sudoku.SIZE; i++) {
            if (isSafe(row, col) && checkSurrounding(i, row, col)) {
                sudoku[row][col] = i;

                int nextrow = nextPos(sudoku) / Sudoku.SIZE;
                int nextcol = nextPos(sudoku) % Sudoku.SIZE;
                solve(nextrow, nextcol);
                if (!solved) {
                    sudoku[row][col] = 0;
                }
            }
        }

    }

    /**
     * Checks if the x value can be placed in the sudoku puzzle.
     *
     * @param x   The value to check if it can be placed
     * @param row The row to place it in
     * @param col The column to place it in
     * @return True if it can be placed. False otherwise
     */
    private boolean checkSurrounding(int x, int row, int col) {
        for (int j = 0; j < Sudoku.SIZE; j++) {
            if (sudoku[row][j] == x) {
                return false;
            }
        }
        for (int i = 0; i < Sudoku.SIZE; i++) {
            if (sudoku[i][col] == x) {
                return false;
            }
        }
        for (int i = row - row % 3; i < row - row % 3 + 3; i++) {
            for (int j = col - col % 3; j < col - col % 3 + 3; j++) {
                if (sudoku[i][j] == x) {
                    return false;
                }
            }
        }
        return true;
    }

    /**
     * Checks if the row and column are within the bounds of hte sudoku puzzle.
     *
     * @param row The row to check
     * @param col The column to check
     * @return True if the row and column are within the bounds of the sudoku puzzle. False otherwise.
     */
    private boolean isSafe(int row, int col) {
        return row < Sudoku.SIZE && col < Sudoku.SIZE && row >= 0 && col >= 0;
    }

    /**
     * Determines the next position to place a number at.
     *
     * @return The integer that represents where to place the next number at.
     * (row = x / SIZE, col = x % SIZE)
     */
    private int nextPos(int[][] sudoku) {
        int track = 0;
        for (int i = 0; i < Sudoku.SIZE; i++) {
            for (int j = 0; j < Sudoku.SIZE; j++) {
                if (sudoku[i][j] == 0) {
                    return track;
                }
                track++;
            }
        }
        return track;
    }
}
