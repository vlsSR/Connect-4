import java.util.Scanner;

public class Main {
    private final static String EMPTY = "  ";
    private final static String RED = "🔴";
    private final static String BLUE = "🔵";
    static String[][] table = new String[6][7];
    static int[] counters = {5, 5, 5, 5, 5, 5, 5};
    static int player = 1; //1 RED, 2 BLUE
    static Scanner sc = new Scanner(System.in);
    static boolean victory = false;

    public static void createTable() {
        for (int i = 0; i < 6; i++) {
            for (int j = 0; j < 7; j++) {
                table[i][j] = EMPTY;
            }
        }
    }

    public static void showTable() {
        System.out.print(" |1 |2 |3 |4 |5 |6 |7 |");
        for (int i = 0; i < 6; i++) {
            System.out.println("");
            System.out.print("|");
            for (int j = 0; j < 7; j++) {
                System.out.print(table[i][j] + "|");
            }
        }
    }

    public static void changePlayer() {
        if (player == 1) {
            player = 2;
        }
        else {
            player = 1;
        }
    }

    public static void selectPostion(int counter, int position) {
        if (counters[counter] >= 0) {
            table[counters[counter]][position - 1] = player == 1 ? RED : BLUE;
            counters[counter]--;
            changePlayer();

        }
        else {
            System.out.println("This row is already full");
        }
    }

    public static void menu() {
        int player_input;
        showTable();
        System.out.println("");
        do {
            System.out.println("Player: " + player + "\nSelect the position to place your piece (1-7)");
            player_input = sc.nextInt();
        } while (player_input < 1 || player_input > 7);

        switch (player_input) {
            case 1:
                selectPostion(0, player_input);
                break;
            case 2:
                selectPostion(1, player_input);
                break;
            case 3:
                selectPostion(2, player_input);
                break;
            case 4:
                selectPostion(3, player_input);
                break;
            case 5:
                selectPostion(4, player_input);
                break;
            case 6:
                selectPostion(5, player_input);
                break;
            case 7:
                selectPostion(6, player_input);
                break;
        }
    }


    public static boolean checkVictory() {
        victory: //Horizontal Checks
        for (int i = 0; i < 6; i++) {
            for (int j = 0; j < 4; j++) {
                if (table[i][j].equals(table[i][j + 1]) && table[i][j + 1].equals(table[i][j + 2]) && table[i][j + 2].equals(table[i][j + 3])) {
                    if (table[i][j].equals(RED)) {
                        victory = true;
                        return false;
                    }
                    else if (table[i][j].equals(BLUE)) {
                        victory = true;
                        return false;
                    }
                }
            }
        }
        //Vertical Checks
        for (int i = 0; i < 3; i++) {
            for (int j = 0; j < 7; j++) {
                if (table[i][j].equals(table[i+1][j]) && table[i+1][j].equals(table[i+2][j]) && table[i+2][j].equals(table[i+3][j])) {
                    if (table[i][j].equals(RED)) {
                        victory = true;
                        return false;
                    }
                    else if (table[i][j].equals(BLUE)) {
                        victory = true;
                        return false;
                    }
                }
            }
        }
        //Diagonal left bottom to right top Checks
        for (int i = 5; i > 2; i--) {
            for (int j = 0; j < 4; j++) {
                if (table[i][j].equals(table[i-1][j+1]) && table[i-1][j+1].equals(table[i-2][j+2]) && table[i-2][j+2].equals(table[i-3][j+3])) {
                    if (table[i][j].equals(RED)) {
                        victory = true;
                        return false;
                    }
                    else if (table[i][j].equals(BLUE)) {
                        victory = true;
                        return false;
                    }
                }
            }
        }
        //Diagonal right top to left bottom
        for (int i = 5; i > 2; i--) {
            for (int j = 3; j < 7; j++) {
                if (table[i][j].equals(table[i-1][j-1]) && table[i-1][j-1].equals(table[i-2][j-2]) && table[i-2][j-2].equals(table[i-3][j-3])) {
                    if (table[i][j].equals(RED)) {
                        victory = true;
                        return false;
                    }
                    else if (table[i][j].equals(BLUE)) {
                        victory = true;
                        return false;
                    }
                }
            }
        }
        return true;
    }


    public static void main(String[] args) {

        createTable();
        while (!victory) {
            menu();
            checkVictory();
        }

        if(player == 1) {
            player = 2;
        }
        else {
            player = 1;
        }
        showTable();
        System.out.println("");
        System.out.println("Congratulations player " + player + " you won!");
    }
}