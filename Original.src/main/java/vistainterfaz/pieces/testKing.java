package vistainterfaz.pieces;

import org.junit.Test;

import vistainterfaz.ChessGamePiece;
import vistainterfaz.board.ChessGameBoard;

import static org.junit.Assert.*;
import java.util.ArrayList;
public class testKing {
    @Test
    public void testCalculatePossibleMoves() {
        ChessGameBoard board = new ChessGameBoard();
        King king = new King(board, 3, 3, ChessGamePiece.WHITE);
        ArrayList<String> expectedMoves = new ArrayList<String>();
        expectedMoves.add("2,4");
        expectedMoves.add("2,2");
        expectedMoves.add("4,2");
        expectedMoves.add("4,4");
        expectedMoves.add("2,3");
        expectedMoves.add("4,3");
        expectedMoves.add("3,2");
        expectedMoves.add("3,4");
        ArrayList<String> actualMoves = king.calculatePossibleMoves(board);
        assertEquals(expectedMoves, actualMoves);
    }
    
}
