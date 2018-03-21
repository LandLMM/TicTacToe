import com.tictactoe.InsertException;
import com.tictactoe.PlayerTurnException;
import com.tictactoe.TicTacToe;
import org.junit.Assert;
import org.junit.Test;

import java.util.ArrayList;
import java.util.List;

import static junit.framework.Assert.*;

/**
 * Created by Rafal on 2017-12-03.
 */
public class TicTacToeTest {


    @Test
    public void shouldCreateEmptyGridWhenCreatingNewTicTacToeGame() {
        //given
        TicTacToe ticTacToe = new TicTacToe();
        //when
        List<Character> grid = ticTacToe.getGrid();
        //then
        List<Character> expectedGrid = anExpectedEmptyGrid();

        assertEquals("The grid should contains only 1 ", expectedGrid, grid);
    }


    @Test
    public void shouldBePossibleToAddXorOToTheEmptyPlaceInTheGrid() {
        //given
        TicTacToe ticTacToe = new TicTacToe();
        //when
        ticTacToe.insert(0, 'O');
        //then
        List<Character> expectedGrid = anExpectedEmptyGrid();
        expectedGrid.set(0, 'O');
        assertEquals("The grid should be filled with O in the first column and first row", expectedGrid, ticTacToe.getGrid());
    }

    @Test(expected = InsertException.class)
    public void shouldThrowInsertExceptionWhenIndexOfTheGridIsAbove8() {
        //given
        TicTacToe ticTacToe = new TicTacToe();
        //when
        ticTacToe.insert(9, 'X');
        //then exception should be thrown
    }

    @Test(expected = InsertException.class)
    public void shouldThrowInsertExceptionWhenIndexOfTheGridIsBelow0() {
        //given
        TicTacToe ticTacToe = new TicTacToe();
        //when
        ticTacToe.insert(-1, 'O');
        //then exception should be thrown
    }

    @Test(expected = InsertException.class)
    public void shouldThrowInsertExceptionWhenSymbolIsDifferentThanOorX() {
        //given
        TicTacToe ticTacToe = new TicTacToe();
        //when
        ticTacToe.insert(3, 'h');
        //then exception should be thrown
    }


    @Test(expected = InsertException.class)
    public void shouldThrowInsertExceptionWhenTryingToInsertIntoOccupiedIndex() {
        //given
        TicTacToe ticTacToe = new TicTacToe();
        ticTacToe.insert(2, 'O');
        //when
        ticTacToe.insert(2, 'O');
        //then exception should be thrown
    }

    @Test(expected = PlayerTurnException.class)
    public void shouldThrowPlayerTurnExceptionWhenTryingToInsertXTwiceInARow() {
        //given
        TicTacToe ticTacToe = new TicTacToe();
        ticTacToe.play(2, 'O');
        //when
        ticTacToe.play(4, 'O');
        //then exception should be thrown
    }

    @Test
    public void shouldBeAvailableToPlayByTurns() {
        //given
        TicTacToe ticTacToe = new TicTacToe();
        //when
        ticTacToe.play(2, 'O');
        ticTacToe.play(3, 'X');
        //then no exception should be thrown
        Assert.assertEquals("The field should be O", new Character('O'), ticTacToe.getGrid().get(2));
        Assert.assertEquals("The field should be X", new Character('X'), ticTacToe.getGrid().get(3));
    }

    @Test
    public void shouldReturnTrueForWinWhen3SymbolsOneLineAreEqual() {
        //given
        TicTacToe ticTacToe = new TicTacToe();
        ticTacToe.play(0, 'O');
        ticTacToe.play(3, 'X');
        ticTacToe.play(1, 'O');
        ticTacToe.play(4, 'X');
        //when
        Character result = ticTacToe.play(2, 'O');
        //then
        assertEquals("O should be the winner !", new Character('O'), result);
    }

    @Test
    public void shouldReturnTrueForWinWhen3SymbolsInColumnAreEqual() {
        //given
        TicTacToe ticTacToe = new TicTacToe();
        ticTacToe.play(0, 'O');
        ticTacToe.play(1, 'X');
        ticTacToe.play(3, 'O');
        ticTacToe.play(4, 'X');
        //when
        Character result = ticTacToe.play(6, 'O');
        //then
        assertEquals("O should be the winner !", new Character('O'), result);
    }

    @Test
    public void shouldNotBeWinnerForRowOrColumnWithEmptyFields() {
        //given
        TicTacToe ticTacToe = new TicTacToe();
        //when
        Character result = ticTacToe.play(6, 'O');
        //then
        assertNull("Result should be null !", result);
    }

    private List<Character> anExpectedEmptyGrid() {
        List<Character> expectedGrid = new ArrayList<Character>();
        expectedGrid.add(0, '1');
        expectedGrid.add(1, '1');
        expectedGrid.add(2, '1');
        expectedGrid.add(3, '1');
        expectedGrid.add(4, '1');
        expectedGrid.add(5, '1');
        expectedGrid.add(6, '1');
        expectedGrid.add(7, '1');
        expectedGrid.add(8, '1');
        return expectedGrid;
    }


}
