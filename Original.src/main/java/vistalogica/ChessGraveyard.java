package vistalogica;

import java.awt.GridLayout;
import javax.swing.*;

import vistainterfaz.ChessGamePiece;

// -------------------------------------------------------------------------
/**
 * Representa el cementerio donde yacen las piezas después de ser capturadas.
 *
 */
public class ChessGraveyard extends JPanel {
    private String title;

    // ----------------------------------------------------------
    /**
     * Crea un nuevo objeto ChessGraveyard.
     *
     * @param title
     * el título del cementerio
     */
    public ChessGraveyard(String title) {
        this.title = title;
        this.add(new JLabel(title));
        this.setLayout(new GridLayout(8, 0));
    }

    
    
    // ----------------------------------------------------------
    /**
     * Añade una pieza al cementerio.
     *
     * @param pieza
     * la GamePiece a añadir
     */
    public void addPiece(ChessGamePiece piece) {
        piece.setPieceLocation(-1, -1);
        JLabel pieceLabel = new JLabel();
        pieceLabel.setIcon(piece.getImage());
        this.add(pieceLabel);
    }

    // ----------------------------------------------------------
    /**
     * Elimina todas las piezas del cementerio.
     */
    public void clearGraveyard() {
    	
        this.removeAll();
        this.add(new JLabel(title));
        
    }
}
