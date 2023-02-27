package vistainterfaz.pieces;

import java.util.ArrayList;
import javax.swing.ImageIcon;

import vistainterfaz.ChessGamePiece;
import vistainterfaz.board.ChessGameBoard;

// -------------------------------------------------------------------------
/**
 * Clase para representar la pieza Alfil.
 */
public class Bishop extends ChessGamePiece {
    /**
     * Crea un nuevo objeto Bishop.
     * 
     * @param board
     *              tablero el tablero donde crear el alfil
     * @param row
     *              ubicación de la fila del alfil
     * @param col
     *              col ubicación del alfil
     * @param color
     *              ya sea GamePiece.WHITE, BLACK, o UNASSIGNED
     */
    public Bishop(ChessGameBoard board, int row, int col, int color) {
        super(board, row, col, color);
    }

    /**
     * Calcula los movimientos posibles para esta pieza. Estos son TODOS los posibles
     * movimientos, incluyendo los movimientos ilegales (pero al mismo tiempo válidos).
     * 
     * @param board
     *              el tablero sobre el que calcular los movimientos
     * @return ArrayList<String> los movimientos
     */
    @Override
    protected ArrayList<String> calculatePossibleMoves(ChessGameBoard board) {
        ArrayList<String> northEastMoves = calculateNorthEastMoves(board, 8);
        ArrayList<String> northWestMoves = calculateNorthWestMoves(board, 8);
        ArrayList<String> southEastMoves = calculateSouthEastMoves(board, 8);
        ArrayList<String> southWestMoves = calculateSouthWestMoves(board, 8);
        ArrayList<String> allMoves = new ArrayList<>();
        allMoves.addAll(northEastMoves);
        allMoves.addAll(northWestMoves);
        allMoves.addAll(southEastMoves);
        allMoves.addAll(southWestMoves);
        return allMoves;
    }

    /**
     * Crea un icono para esta pieza dependiendo del color de la pieza.
     * 
     * @return ImageIcon la representación ImageIcon de esta pieza.
     */
    @Override
    public ImageIcon createImageByPieceType() {
        if (getColorOfPiece() == ChessGamePiece.WHITE) {
            return new ImageIcon(
                    getClass().getResource("chessImages/WhiteBishop.gif"));
        } else if (getColorOfPiece() == ChessGamePiece.BLACK) {
            return new ImageIcon(
                    getClass().getResource("chessImages/BlackBishop.gif"));
        } else {
            return new ImageIcon(
                    getClass().getResource("chessImages/BlackBishop.gif"));
        }
    }
}
