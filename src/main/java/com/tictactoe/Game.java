package com.tictactoe;

import java.util.Scanner;

/**
 * Created by Rafal on 2017-12-03.
 */
public class Game {


    public static void main(String[] args) {
        Scanner in = new Scanner(System.in);
        TicTacToe tic = new TicTacToe();
        Character winner = null;
        tic.printGrid();
        while (tic.isEnd() == false) {
            System.out.println("Turn - " + tic.getTurn() + " . Enter field 0-8");
            Integer field = in.nextInt();
            try {
                winner = tic.play(field, tic.getTurn());
            } catch (InsertException ex) {
                System.out.println(ex.getMessage());
            }
            tic.printGrid();
        }
        if (winner == null)
            System.out.println("\nNo winner !");
        else
            System.out.println("\nWinner - " + winner);
    }
}
