package vistainterfaz;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

class test1ChessPanel {

    private ChessPanel chessPanel;

    @BeforeEach
    void setUp() {
        chessPanel = new ChessPanel();
    }

    @Test
    void testConstruction() {
        assertNotNull(chessPanel);
        assertNotNull(chessPanel.getGameLog());
        assertNotNull(chessPanel.getGameBoard());
        assertNotNull(chessPanel.getGraveyard(1));
        assertNotNull(chessPanel.getGraveyard(2));
        assertNotNull(chessPanel.getGameEngine());
    }

}
