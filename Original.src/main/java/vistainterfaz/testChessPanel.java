package vistainterfaz;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import vistalogica.ChessGameEngine;
import vistalogica.ChessGraveyard;

class testChessPanel {

    private ChessPanel chessPanel;

    @BeforeEach
    void setUp() {
        chessPanel = new ChessPanel();
    }

    @Test
    void testGetGameLog() {
        assertNotNull(chessPanel.getGameLog());
    }

    @Test
    void testGetGameBoard() {
        assertNotNull(chessPanel.getGameBoard());
    }

    @Test
    void testGetGameEngine() {
        assertNotNull(chessPanel.getGameEngine());
        assertTrue(chessPanel.getGameEngine() instanceof ChessGameEngine);
    }

    @Test
    void testGetGraveyard() {
        assertNotNull(chessPanel.getGraveyard(1));
        assertNotNull(chessPanel.getGraveyard(2));
        assertNull(chessPanel.getGraveyard(3));
    }

}
