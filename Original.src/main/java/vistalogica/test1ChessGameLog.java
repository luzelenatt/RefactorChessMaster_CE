package vistalogica;

import static org.junit.jupiter.api.Assertions.*;

import javax.swing.JTextArea;

import org.junit.jupiter.api.Test;

class test1ChessGameLog {

	@Test
	void test() {
	       ChessGameLog log = new ChessGameLog();
	        JTextArea textArea = (JTextArea)log.getViewport().getView();
	        
	        // Agregar algunas líneas al registro
	        log.addToLog("Primer movimiento");
	        log.addToLog("Segundo movimiento");
	        log.addToLog("Tercer movimiento");
	        
	        // Verificar que el registro no está vacío
	        assertEquals(false, textArea.getText().isEmpty());
	        
	        // Borrar el registro
	        log.clearLog();
	        
	        // Verificar que el registro está vacío
	        assertEquals(true, textArea.getText().isEmpty());
	    }
	}


