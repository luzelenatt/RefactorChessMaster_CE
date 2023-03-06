package vistainterfaz.board;

import javax.swing.JLabel;
import javax.swing.JPanel;

import vistainterfaz.ChessGamePiece;

// -------------------------------------------------------------------------
/**
 * Representa una casilla en el tablero de ajedrez. Contiene una pieza de juego.
 *
 */
public class BoardSquare
        extends JPanel {
    private int row;
    private int col;
    private ChessGamePiece piece;
    private JLabel imageLabel;

    
    
    
    // ----------------------------------------------------------
    /**
     * Crea un nuevo objeto BoardSquare.
     *
     * @param row
     * la fila
     * @param col
     * la columna
     * @param piece
     * la pieza del juego
     */
    public BoardSquare(int row, int col, ChessGamePiece piece) {
        super();
        this.row = row;
        this.col = col;
        this.piece = piece;
        updateImage();
    }

    
    
    
    /**
     * Actualiza la imagen de este BoardSquare.
     */
    private void updateImage() {
        if (imageLabel != null) {
            removeAll();
        }
        if (piece != null) {
            imageLabel = new JLabel();
            imageLabel.setIcon(piece.getImage());
            add(imageLabel);
        }
        revalidate(); // repaint wasn't working, gotta force the window manager
        // to redraw...
    }

    
    
    // ----------------------------------------------------------
    /**
     * Obtiene el número de fila.
     *
     * @return int el número de fila
     */
    public int getRow() {
        return row;
    }

    // ----------------------------------------------------------
    /**
     * Obtiene el número de columna.
     *
     * @return int el número de columna
     */
    public int getColumn() {
        return col;
    }

    
    
    // ----------------------------------------------------------
    /**
     * Obtiene la pieza en esta casilla
     *
     * @return GamePiece la pieza
     */
    public ChessGamePiece getPieceOnSquare() {
        return piece;
    }

    // ----------------------------------------------------------
    /**
     * Establece la pieza en esta casilla
     *
     * @param p
     * la pieza
     */
    public void setPieceOnSquare(ChessGamePiece p) {
        piece = p;
        updateImage();
    }

    
    
    // ----------------------------------------------------------
    /**
     * Borra esta casilla, eliminando el icono y la pieza.
     */
    public void clearSquare() {
        piece = null;
        removeAll();
    }
}
