package com.tictactoe;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Rafal on 2017-12-03.
 */
public class TicTacToe {

    private List<Character> grid;

    private Character turn;

    boolean end;

    public TicTacToe() {
        List<Character> expectedGrid = new ArrayList<Character>();
        for (int i = 0; i < 9; i++) {
            expectedGrid.add(i, '1');
        }
        this.grid = expectedGrid;
        this.turn = 'O';
        end = false;
    }

    public List<Character> getGrid() {
        return grid;
    }

    public Character play(int index, Character symbol) {
        if (!symbol.equals(this.turn))
            throw new PlayerTurnException("Now is not Your turn !");
        insert(index, symbol);
        if (checkWinner() == true)
            return symbol;
        this.turn = symbol.equals('O') ? 'X' : 'O';
        return null;
    }

    public void insert(int index, Character symbol) {
        validateGridIndex(index);
        validateSymbol(symbol);
        Character currentCharacter = grid.get(index);
        if (!currentCharacter.equals('1'))
            throw new InsertException("Index is currently occupied ! ");
        grid.set(index, symbol);
    }

    private boolean checkWinner() {
        return checkRowWinner() || checkColumnWinner() || checkDiagonalWinner();
    }

    private boolean checkColumnWinner() {
        boolean winner = false;
        for (int i = 0; i < 3; i++) {
            if (grid.get(i).equals('1'))
                continue;
            if (grid.get(i).equals(grid.get(i + 3)) && grid.get(i).equals(grid.get(i + 6))) {
                winner = true;
                end = true;
                break;
            }
        }
        return winner;
    }

    private boolean checkRowWinner() {
        boolean winner = false;
        for (int i = 0; i < 9; i = i + 3) {
            if (grid.get(i).equals('1'))
                continue;
            if (grid.get(i).equals(grid.get(i + 1)) && grid.get(i).equals(grid.get(i + 2))) {
                winner = true;
                end = true;
                break;
            }
        }
        return winner;
    }

    private boolean checkDiagonalWinner() {
        if (!grid.get(0).equals('1')) {
            if (grid.get(0).equals(grid.get(4)) && grid.get(0).equals(grid.get(8))) {
                end = true;
                return true;
            }
        }
        if (!grid.get(2).equals('1')) {
            if (grid.get(2).equals(grid.get(4)) && grid.get(2).equals(grid.get(6))) {
                end = true;
                return true;
            }
        }
        return false;
    }

    private void validateSymbol(Character symbol) {
        if (!symbol.toString().toUpperCase().equals("X") && !symbol.toString().toUpperCase().equals("O"))
            throw new InsertException("Inappropriate symbol " + symbol);
    }

    private void validateGridIndex(int index) {
        if (index > 8)
            throw new InsertException("Index is above 8 !");
        if (index < 0)
            throw new InsertException("Index is below 0 !");
    }

    public void printGrid() {
        for (int i = 0; i < 9; i++) {
            if (i > 0 && i % 3 == 0)
                System.out.println("");
            System.out.print(grid.get(i) + " ");
        }
    }

    public boolean isEnd() {
        return end;
    }

    public Character getTurn() {
        return turn;
    }


}
