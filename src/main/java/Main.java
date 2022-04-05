import sun.misc.FloatingDecimal;

import java.util.Random;
import java.util.Scanner;

public class Main {

    private static Random rnd = new Random();
    private static Scanner sc = new Scanner(System.in);
    private static char[][] map = new char[3][3];

    private static final char EMPTY_DOT = '*';
    private static final char PLAYER_DOT = 'x';
    private static final char AI_DOT = 'O';

    private static void initMap() {
        for (int i = 0; i < map.length; i++) {
            for (int j = 0; j < map[i].length; j++) {
                map[i][j] = EMPTY_DOT;
            }
        }
    }

    private static void printMap() {
        System.out.println("0 1 2 3");
        for (int i = 0; i < map.length; i++) {
            System.out.print((i + 1) + " ");
            for (int j = 0; j < map[i].length; j++) {
                System.out.print(map[i][j] + " ");
            }
            System.out.println();
        }
        System.out.println();
    }

    private static void playerTurn() {
        int x = -1;
        int y = -1;
        do {
            x = sc.nextInt() - 1;
            y = sc.nextInt() - 1;
        } while (!isMapEmpty(x, y));
        map[x][y] = PLAYER_DOT;
    }

    private static void aiTurn() {
        int x = -1;
        int y = -1;
        do {
            x = rnd.nextInt(3);
            y = rnd.nextInt(3);
        } while (!isMapEmpty(x, y));
        map[x][y] = AI_DOT;
    }

    private static boolean isMapEmpty(int x, int y) {
        if (x < 0 || y < 0 || x > 2 || y > 2) {
            return false;
        }
        if (map[x][y] == EMPTY_DOT) {
            return true;
        }
        return false;
    }

    public static boolean isMapFull() {
        for (int i = 0; i < map.length; i++) {
            for (int j = 0; j < map[i].length; j++) {
                if (map[i][j] == EMPTY_DOT) {
                    return false;
                }
            }
        }
        return true;
    }





    static boolean checkWin(char dot) {
        int counter = 0;
        for (int i = 0; i <= 2; i++) {
            if (map[0][i] == dot) {
                counter++;
                if (counter == 3) {
                    return true;
                }
            }
            if (map[1][i] == dot) {
                counter++;
                if (counter == 3) {
                    return true;
                }
            }

            if (map[2][i] == dot) {
                counter++;
                if (counter == 3) {
                    return true;
                }
            }
        }

        for (int i = 0; i <=2 ; i++) {
            int counter1 = 0;
            if (map[i][0] == dot){
                counter1++;
                if (counter1 == 3){
                    return true;
                }
            }
            if (map[i][1] == dot){
                counter1++;
                if (counter1 == 3){
                    return true;
                }
            }
            if (map[i][2] == dot){
                counter1++;
                if (counter1 == 3){
                    return true;
                }
            }
        }

        for (int i = 0; i <= 2; i++) {
            int counter2 = 0;
            if(map[i][i] == dot){
                counter2++;
                if (counter2 == 3){
                    return true;
                }
            }

            if (map[i][2] == dot && map[i][1] == dot && map[i][0] == dot){
                return true;
            }
        }


//
//            if (map[0][2] == dot && map[1][1] == dot && map[2][0] == dot) {
//                return true;
//            }

        return false;
}

    public static void main(String[] args) {
        checkWin(PLAYER_DOT);
        initMap();
        printMap();
        do {
            playerTurn();
            if (checkWin(PLAYER_DOT)){
                System.out.println("Player win");
                break;
            }
            if (isMapFull()){
                System.out.println("Ничья");
                break;
            }
            printMap();
            aiTurn();
            if (isMapFull()){
                System.out.println("Ничья");
                break;
            }
            printMap();
        }while (!isMapFull());
    }
}