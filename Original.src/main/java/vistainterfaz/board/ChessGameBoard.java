package vistainterfaz.board;

import java.util.ArrayList;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.awt.Color;
import javax.swing.JPanel;

import vistainterfaz.ChessGamePiece;
import vistainterfaz.ChessPanel;
import vistainterfaz.pieces.Bishop;
import vistainterfaz.pieces.King;
import vistainterfaz.pieces.Knight;
import vistainterfaz.pieces.Pawn;
import vistainterfaz.pieces.Queen;
import vistainterfaz.pieces.Rook;

import java.awt.GridLayout;

public class ChessGameBoard extends JPanel {
    private BoardSquare[][] chessCells;
    private BoardListener listener;


    /**
     * Devuelve el tablero completo.
     *
     * @return BoardSquare[][] el tablero de ajedrez
     */
    public BoardSquare[][] getCells() {
        return chessCells;
    }

    /**
     * Comprueba que la fila y la columna son índices válidos.
     * 
     * @param row la fila a comprobar
     * @param col la columna a comprobar
     * @return boolean true si son válidos, false en caso contrario
     */
    private boolean validateCoordinates(int row, int col) {
        return chessCells.length > 0 && chessCells[0].length > 0 &&
                row < chessCells.length && col < chessCells[0].length
                && row >= 0 && col >= 0;
    }


    /**
     * Obtiene el BoardSquare en la fila 'row' y la columna 'col'.
     * 
     * @param row la fila a mirar
     * @param col la columna a mirar
     * @return BoardSquare el cuadrado encontrado, o null si no existe
     */
    public BoardSquare getCell(int row, int col) {
        if (validateCoordinates(row, col)) {
            return chessCells[row][col];
        }
        return null;
    }


    /**
     * Borra la celda en 'row', 'col'.
     * 
     * @param row la fila a mirar
     * @param col la columna a mirar
     */
    public void clearCell(int row, int col) {
        if (validateCoordinates(row, col)) {
            chessCells[row][col].clearSquare();
        } else {
            throw new IllegalStateException("Row " + row + " and column" +
                    " " + col + " are invalid, or the board has not been" +
                    "initialized. This square cannot be cleared.");
        }
    }


    /**
     * Obtiene todas las piezas de juego blancas en el tablero.
     *
     * @return ArrayList<GamePiece> las piezas
     */
    public ArrayList<ChessGamePiece> getAllWhitePieces() {
        ArrayList<ChessGamePiece> whitePieces = new ArrayList<>();
        for (int i = 0; i < 8; i++) {
            for (int j = 0; j < 8; j++) {
                if (chessCells[i][j].getPieceOnSquare() != null
                        && chessCells[i][j].getPieceOnSquare().getColorOfPiece() == ChessGamePiece.WHITE) {
                    whitePieces.add(chessCells[i][j].getPieceOnSquare());
                }
            }
        }
        return whitePieces;
    }


    /**
     * Obtiene todas las piezas negras del tablero
     *
     * @return ArrayList<GamePiece> las piezas
     */
    public ArrayList<ChessGamePiece> getAllBlackPieces() {
        ArrayList<ChessGamePiece> blackPieces = new ArrayList<>();
        for (int i = 0; i < 8; i++) {
            for (int j = 0; j < 8; j++) {
                if (chessCells[i][j].getPieceOnSquare() != null
                        && chessCells[i][j].getPieceOnSquare().getColorOfPiece() == ChessGamePiece.BLACK) {
                    blackPieces.add(chessCells[i][j].getPieceOnSquare());
                }
            }
        }
        return blackPieces;
    }


    /**
     * Crea un nuevo objeto ChessGameBoard.
     */
    public ChessGameBoard() {
        this.setLayout(new GridLayout(8, 8, 1, 1));
        listener = new BoardListener();
        chessCells = new BoardSquare[8][8];
        initializeBoard();
    }

    

    /**
     * @param addAfterReset 
     */
    public void resetBoard(boolean addAfterReset) {
        chessCells = new BoardSquare[8][8];
        this.removeAll();
        if (getParent() instanceof ChessPanel) {
            ((ChessPanel) getParent()).getGraveyard(1).clearGraveyard();
            ((ChessPanel) getParent()).getGraveyard(2).clearGraveyard();
            ((ChessPanel) getParent()).getGameLog().clearLog();
        }
        for (int i = 0; i < chessCells.length; i++) {
            for (int j = 0; j < chessCells[0].length; j++) {
                chessCells[i][j] = new BoardSquare(i, j, null);
                if ((i + j) % 2 == 0) {
                    chessCells[i][j].setBackground(Color.WHITE);
                } else {
                    chessCells[i][j].setBackground(Color.BLACK);
                }
                if (addAfterReset) {
                    chessCells[i][j].addMouseListener(listener);
                    this.add(chessCells[i][j]);
                }
            }
        }
        repaint();
        // only the combination of these two calls work...*shrug*
    }

    /**
     * (Re)inicializa este ChessGameBoard a su diseño por defecto con todas las 32
     * piezas añadidas.
     */
    public void initializeBoard() {
        resetBoard(false);
        for (int i = 0; i < chessCells.length; i++) {
            for (int j = 0; j < chessCells[0].length; j++) {
                ChessGamePiece pieceToAdd;
                if (i == 1) // black pawns
                {
                    pieceToAdd = new Pawn(this, i, j, ChessGamePiece.BLACK);
                } else if (i == 6) // white pawns
                {
                    pieceToAdd = new Pawn(this, i, j, ChessGamePiece.WHITE);
                } else if (i == 0 || i == 7) // main rows
                {
                    int colNum = i == 0 ? ChessGamePiece.BLACK : ChessGamePiece.WHITE;
                    if (j == 0 || j == 7) {
                        pieceToAdd = new Rook(this, i, j, colNum);
                    } else if (j == 1 || j == 6) {
                        pieceToAdd = new Knight(this, i, j, colNum);
                    } else if (j == 2 || j == 5) {
                        pieceToAdd = new Bishop(this, i, j, colNum);
                    } else if (j == 3) {
                        pieceToAdd = new King(this, i, j, colNum);
                    } else {
                        pieceToAdd = new Queen(this, i, j, colNum);
                    }
                } else {
                    pieceToAdd = null;
                }
                chessCells[i][j] = new BoardSquare(i, j, pieceToAdd);
                if ((i + j) % 2 == 0) {
                    chessCells[i][j].setBackground(Color.WHITE);
                } else {
                    chessCells[i][j].setBackground(Color.BLACK);
                }
                chessCells[i][j].addMouseListener(listener);
                this.add(chessCells[i][j]);
            }
        }
    }


    /**
     * Borra los colores del tablero.
     */
    public void clearColorsOnBoard() {
        for (int i = 0; i < chessCells.length; i++) {
            for (int j = 0; j < chessCells[0].length; j++) {
                if ((i + j) % 2 == 0) {
                    chessCells[i][j].setBackground(Color.WHITE);
                } else {
                    chessCells[i][j].setBackground(Color.BLACK);
                }
            }
        }
    }

    
    
    /**
     * Escucha los clics en BoardSquares.
     */
    private class BoardListener
            implements MouseListener {
    	/**
         * Realiza una acción cuando se pulsa el botón izquierdo del ratón.
         *
         * @param e
         * el evento del listener
         */
        public void mouseClicked(MouseEvent e) {
            if (e.getButton() == MouseEvent.BUTTON1 &&
                    getParent() instanceof ChessPanel) {
                ((ChessPanel) getParent()).getGameEngine()
                        .determineActionFromSquareClick(e);
            }
        }

        /**
         * Método no utilizado.
         *
         * @param e
         * el evento de ratón del listener
         */
        public void mouseEntered(MouseEvent e) { /* not used */
        }

        /**
         * Método no utilizado.
         *
         * @param e
         * el evento de ratón del listener
         */
        public void mouseExited(MouseEvent e) { /* not used */
        }

        /**
         * Método no utilizado.
         *
         * @param e
         * el evento de ratón del listener
         */
        public void mousePressed(MouseEvent e) { /* not used */
        }

        /**
         * Método no utilizado.
         *
         * @param e
         * el evento de ratón del listener
         */
        public void mouseReleased(MouseEvent e) { /* not used */
        }
    }
}
