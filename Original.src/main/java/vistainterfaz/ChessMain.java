package vistainterfaz;

import javax.swing.*;

// -------------------------------------------------------------------------
/**
 * Muestra la GUI del juego de Ajedrez.
 */
public class ChessMain {
    // ----------------------------------------------------------
    /**
     * Crea la interfaz gráfica de Chess.
     * 
     * @param args
     *             argumentos de línea de comandos, no utilizados
     */
    public static void main(String[] args) {
        JFrame frame = new JFrame("YetAnotherChessGame 1.0");
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.getContentPane().add(new ChessPanel());
        frame.pack();
        frame.setVisible(true);
    }
}
