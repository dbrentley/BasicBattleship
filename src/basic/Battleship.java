package basic;


public class Battleship {

    /*
     * Basic Battleship
     * 
     * This does not check for ship overlap.
     */

    final private static int ROWS = 10;
    final private static int COLS = 10;
    private static int[] BOARD;

    public static void main(String[] args) {
        BOARD = new int[ROWS * COLS];

        for (int n = 0; n < BOARD.length; n++) {
            BOARD[n] = 0;
        }

        addShip('c', 3, 4, true);
        addShip('d', 7, 5, false);
        addShip('i', 2, 2, true);
        addShip('b', 9, 2, true);
        addShip('e', 9, 3, false);

        if (checkHit('h', 7)) {
            System.out.println("H,7 is a HIT!");
        } else {
            System.out.println("H,7 is a MISS!");
        }

        if (checkHit('j', 8)) {
            System.out.println("J,8 is a HIT!");
        } else {
            System.out.println("J,8 is a MISS!");
        }

        System.out.println();
        showBoard();
    }

    private static boolean addShip(char column, int row, int size, boolean isVertical) {
        int col = (int) Character.toUpperCase(column) - 65;
        row--; // Compensate for 0 based index
        if (col >= 0 && col < COLS && row < ROWS && row >= 0) {
            if (isVertical) {
                if (row + size > ROWS) {
                    // make sure it doesn't go off the bottom of the board
                    return false;
                }
                for (int v = row; v < row + size; v++) {
                    BOARD[v * COLS + col] = 1;
                }
            } else {
                if (col + size > COLS) {
                    // make sure it doesn't go off the side of the board
                    return false;
                }
                for (int h = col; h < col + size; h++) {
                    BOARD[row * COLS + h] = 1;
                }
            }
        } else {
            return false;
        }
        return true;
    }

    private static boolean checkHit(char column, int row) {
        int col = (int) Character.toUpperCase(column) - 65;
        row--; // Compensate for 0 based index
        if (BOARD[row * COLS + col] == 1) {
            return true;
        }
        return false;
    }

    private static void showBoard() {
        int count = 0;
        int rowCount = 1;

        System.out.print("   ");
        for (int n = 65; n < 65 + ROWS; n++) {
            System.out.print((char) n);
        }
        System.out.print("\n");

        System.out.print(" " + rowCount + " ");
        for (int n = 0; n < BOARD.length; n++) {
            System.out.print(BOARD[n]);
            count++;
            if (count > COLS - 1) {
                System.out.print("\n");
                if (rowCount < ROWS) {
                    rowCount++;
                    if (rowCount < 10) {
                        System.out.print(" " + rowCount + " ");
                    } else {
                        System.out.print(rowCount + " ");
                    }
                }
                count = 0;
            }
        }
    }
}