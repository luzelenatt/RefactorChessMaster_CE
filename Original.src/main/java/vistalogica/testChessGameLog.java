package vistalogica;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.Before;
import org.junit.jupiter.api.Test;

class testChessGameLog {

    @Test
    void testClearLog() {
        ChessGameLog log = new ChessGameLog();
        log.addToLog("Primer movimiento");
        log.addToLog("Segundo movimiento");
        log.addToLog("Tercer movimiento");
        log.clearLog();
        assertEquals("", log.getLastLog());
    }
}
