package tictactoe;

import java.lang.*;
import java.util.Arrays;
import java.util.InputMismatchException;
import java.util.Objects;
import java.util.Scanner;

public class Main {
    static int size = 3;
    static String[][] gameBoard = new String[size][size];
    static int i;
    static int j;
    /*static String userInput;
    static int c = 0;*/
    static String winner = "No winners";
    static int xWinner = 0;
    static int oWinner = 0;
    static int Xs = 0;
    static int Os = 0;
    static int blank = 0;
    static boolean gnf = true;
    static int pos1;
    static int pos2;
    static int nr = 2;

    public static void main(String[] args) {
        //Scanner scanner = new Scanner(System.in);
        //userInput = scanner.nextLine();

        initializeBoard();
        hasWinner();
        printBoard();
        checkBoardState();
        returnGameState();
        playX();




    }

    public static void initializeBoard() {
        //while (c < 9) {
            for (i = 0; i < size; i++) {
                for (j = 0; j < size; j++) {
                    /*gameBoard[i][j] = String.valueOf(userInput.charAt(c));
                    c++;*/
                    gameBoard[i][j] = " ";
                }
            }
            // }
        }


    public static void printBoard() {
        System.out.println("---------");
        System.out.println(Arrays.deepToString(gameBoard)
                .replaceAll("\\[\\[", "| ")
                .replaceAll("]]", " |")
                .replaceAll(",", "")
                .replaceAll("]", " |")
                .replaceAll("\\[", "\n| "));
        System.out.println("---------");
    }

    public static void hasWinner() {
        for (i = 0; i < size; i++) {
            if (Objects.equals(gameBoard[i][0], gameBoard[i][1]) && Objects.equals(gameBoard[i][1], gameBoard[i][2]) && !Objects.equals(gameBoard[i][0], "_") && !Objects.equals(gameBoard[i][0], " ")) {
                winner = gameBoard[i][0];
                if(Objects.equals(winner, "X")) {
                    xWinner++;
                } else {
                    oWinner++;
                }
            }
        }
        for (j = 0; j < size; j++) {
            if (Objects.equals(gameBoard[0][j], gameBoard[1][j]) && Objects.equals(gameBoard[1][j], gameBoard[2][j]) && !Objects.equals(gameBoard[0][j], "_") && !Objects.equals(gameBoard[0][j], " ")) {
                winner = gameBoard[0][j];
                if(Objects.equals(winner, "X")) {
                    xWinner++;
                } else {
                    oWinner++;
                }
            }
        }
        if (Objects.equals(gameBoard[0][0], gameBoard[1][1]) && Objects.equals(gameBoard[1][1], gameBoard[2][2]) && !Objects.equals(gameBoard[1][1], "_") && !Objects.equals(gameBoard[1][1], " ")) {
            winner = gameBoard[0][0];
            if(Objects.equals(winner, "X")) {
                xWinner++;
            } else {
                oWinner++;
            }
        }
        if (Objects.equals(gameBoard[0][2], gameBoard[1][1]) && Objects.equals(gameBoard[1][1], gameBoard[2][0]) && !Objects.equals(gameBoard[1][1], "_") && !Objects.equals(gameBoard[1][1], " ")) {
            winner = gameBoard[1][1];
            if(Objects.equals(winner, "X")) {
                xWinner++;
            } else {
                oWinner++;
            }
        }

    }

    public static void returnGameState() {
        if (Math.abs(Xs-Os) == 0 || Math.abs(Xs-Os) == 1) {
            if (!Objects.equals(winner, "No winners")) {
                if (xWinner != oWinner) {
                    System.out.println(winner + " wins");
                    gnf = false;
                } else {
                    System.out.println("Impossible");
                    gnf = false;
                }
            } else if (blank > 0) {
                gnf = true;
            } else if (blank == 0){
                System.out.println("Draw");
                gnf = false;
            }
        } else {
            System.out.println("Impossible");
            gnf = false;
        }
    }

    public static void checkBoardState() {
        Xs = 0;
        Os = 0;
        blank = 0;
        for(i = 0; i < size; i++) {
            for(j = 0; j < size; j++) {
                if (Objects.equals(gameBoard[i][j], "X")) {
                    Xs++;
                } else if (Objects.equals(gameBoard[i][j], "O")) {
                    Os++;
                } else if (Objects.equals(gameBoard[i][j], "_") || Objects.equals(gameBoard[i][j], " ")) {
                    blank++;
                }
            }
        }
    }

    public static void playX() {
        Scanner scanner = new Scanner(System.in);

        while (gnf) {
            checkBoardState();
            if(blank == 0) {
                printBoard();
                returnGameState();
                gnf = false;
                break;
            }
            System.out.print("Enter the coordinates: ");
            try {
                pos1 = scanner.nextInt();
                pos2 = scanner.nextInt();
                if (Objects.equals(gameBoard[pos1 - 1][pos2 - 1], " ") || Objects.equals(gameBoard[pos1 - 1][pos2 - 1], "_")) {
                    if(nr % 2 == 0){
                        gameBoard[pos1 - 1][pos2 - 1] = "X";
                        nr++;
                    } else if (nr % 2 == 1) {
                        gameBoard[pos1 - 1][pos2 - 1] = "O";
                        nr++;
                    }
                    printBoard();
                    checkBoardState();
                    hasWinner();
                    returnGameState();

                } else {
                    System.out.println("This cell is occupied! Choose another one!");
                    playX();
                }
            } catch (InputMismatchException ime) {
                System.out.println("You should enter numbers!");
                playX();
            } catch (ArrayIndexOutOfBoundsException aiobe) {
                System.out.println("Coordinates should be from 1 to 3!");
                playX();
            }
        }

    }

}

