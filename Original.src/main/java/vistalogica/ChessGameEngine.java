package vistalogica;

import java.util.ArrayList;
import java.awt.Color;
import javax.swing.JOptionPane;

import vistainterfaz.ChessGamePiece;
import vistainterfaz.ChessPanel;
import vistainterfaz.board.BoardSquare;
import vistainterfaz.board.ChessGameBoard;
import vistainterfaz.pieces.King;

import java.awt.event.MouseEvent;

// -------------------------------------------------------------------------
/**
* Este es el backend detrás del juego de ajedrez. Maneja los aspectos basados en turnos de
* el juego, eventos de clic, y determina las condiciones de ganar/perder.
 */
public class ChessGameEngine {
    private ChessGamePiece currentPiece;
    private boolean firstClick;
    private int currentPlayer;
    private ChessGameBoard board;
    private King king1;
    private King king2;

    // ----------------------------------------------------------
    /**
     * Crea un nuevo objeto ChessGameEngine. Acepta un
     * ChessGameBoard. (es decir, todos los componentes renderizados)
     *
     * @param board
     *              the reference ChessGameBoard
     */
    public ChessGameEngine(ChessGameBoard board) {
        firstClick = true;
        currentPlayer = 1;
        this.board = board;
        this.king1 = (King) board.getCell(7, 3).getPieceOnSquare();
        this.king2 = (King) board.getCell(0, 3).getPieceOnSquare();
        ((ChessPanel) board.getParent()).getGameLog().clearLog();
        ((ChessPanel) board.getParent()).getGameLog().addToLog(
                "A new chess "
                        + "game has been started. Player 1 (white) will play "
                        + "against Player 2 (black). BEGIN!");
    }

    
    
    // ----------------------------------------------------------
    /**
     * Restablece el juego a su estado original.
     */
    public void reset() {
        firstClick = true;
        currentPlayer = 1;
        ((ChessPanel) board.getParent()).getGraveyard(1).clearGraveyard();
        ((ChessPanel) board.getParent()).getGraveyard(2).clearGraveyard();
        ((ChessPanel) board.getParent()).getGameBoard().initializeBoard();
        ((ChessPanel) board.getParent()).revalidate();
        this.king1 = (King) board.getCell(7, 3).getPieceOnSquare();
        this.king2 = (King) board.getCell(0, 3).getPieceOnSquare();
        ((ChessPanel) board.getParent()).getGameLog().clearLog();
        ((ChessPanel) board.getParent()).getGameLog().addToLog(
                "A new chess "
                        + "game has been started. Player 1 (white) will play "
                        + "against Player 2 (black). BEGIN!");
    }

    
    /**
     * Cambia el turno para que sea el turno del siguiente jugador.
     */
    private void nextTurn() {
        currentPlayer = (currentPlayer == 1) ? 2 : 1;
        ((ChessPanel) board.getParent()).getGameLog().addToLog(
                "It is now Player " + currentPlayer + "'s turn.");
    }

    
    
    
    // ----------------------------------------------------------
    /**
     * Obtiene el jugador actual. Se utiliza para determinar el turno.
     *
     * @return int el jugador actual (1 o 2)
     */
    public int getCurrentPlayer() {
        return currentPlayer;
    }

    
    
    
    
    /**
     * Determina si el jugador solicitado tiene movimientos legales.
     *
     * @param playerNum
     *                  el jugador a comprobar
     * @return boolean true si el jugador tiene movimientos legales, false en caso contrario
     */
    public boolean playerHasLegalMoves(int playerNum) {
        ArrayList<ChessGamePiece> pieces;
        if (playerNum == 1) {
            pieces = board.getAllWhitePieces();
        } else if (playerNum == 2) {
            pieces = board.getAllBlackPieces();
        } else {
            return false;
        }
        for (ChessGamePiece currPiece : pieces) {
            if (currPiece.hasLegalMoves(board)) {
                return true;
            }
        }
        return false;
    }

    
    
    
    /**
     * Comprueba si la última pieza pulsada es una pieza válida (es decir, si es del color correcto y si el usuario ha pulsado sobre una pieza).
     * el color correcto y si el usuario realmente hizo clic en una pieza).
     * 
     * @return boolean true si la pieza es válida, false en caso contrario
     */
    private boolean selectedPieceIsValid() { //REFACTORIZADO
        if (currentPiece == null) // user tried to select an empty square
        {   return false;
        }
        if (currentPlayer == 2) // black player
        {   return currentPiece.getColorOfPiece() == ChessGamePiece.BLACK; 
        
        } else {
             return currentPiece.getColorOfPiece() == ChessGamePiece.WHITE; 
        }
    }

    
    
    
    /**
     * Determina si el Rey solicitado está en jaque.
     *
     * @param checkCurrent
     *                     si es true, comprobará si el rey actual está en jaque si
     *                     falso,
     *                     comprobará si el rey del otro jugador está en jaque.
     * @return true si el rey está en jaque, false en caso contrario
     */
    public boolean isKingInCheck(boolean checkCurrent) {
        if (checkCurrent) {
            if (currentPlayer == 1) {
                return king1.isChecked(board);
            }
            return king2.isChecked(board);
        } else {
            if (currentPlayer == 2) {
                return king1.isChecked(board);
            }
            return king2.isChecked(board);
        }
    }

    
    
    
    /**
     * Pregunta al usuario si quiere volver a jugar - si no, el juego sale.
     *
     * @param endGameStr
     * la cadena para mostrar al usuario (es decir, tablas,
     * jaque mate,
     * etc)
     */
    private void askUserToPlayAgain(String endGameStr) {
        int resp = JOptionPane.showConfirmDialog(board.getParent(), endGameStr
                + " Do you want to play again?");
        if (resp == JOptionPane.YES_OPTION) {
            reset();
        } else {
            board.resetBoard(false);

        }
    }

    
    
    
    /**
     * Determina si el juego debe continuar (es decir, el juego está en jaque o es
     * 'normal'). Si no debe, se pide al usuario que juegue de nuevo (véase más arriba
     * método).
     */
    private void checkGameConditions() {
        int origPlayer = currentPlayer;
        for (int i = 0; i < 2; i++) {
            int gameLostRetVal = determineGameLost();
            if (gameLostRetVal < 0) {
                askUserToPlayAgain("Game over - STALEMATE. You should both go"
                        + " cry in a corner!");
                return;
            } else if (gameLostRetVal > 0) {
                askUserToPlayAgain("Game over - CHECKMATE. " + "Player "
                        + gameLostRetVal + " loses and should go"
                        + " cry in a corner!");
                return;
            } else if (isKingInCheck(true)) {
                JOptionPane.showMessageDialog(
                        board.getParent(),
                        "Be careful player " + currentPlayer + ", " +
                                "your king is in check! Your next move must get " +
                                "him out of check or you're screwed.",
                        "Warning",
                        JOptionPane.WARNING_MESSAGE);
            }
            currentPlayer = currentPlayer == 1 ? 2 : 1;

        }
        currentPlayer = origPlayer;
        nextTurn();
    }

    
    
    
    
    /**
     * Determina si se ha perdido la partida. Devuelve 1 o 2 para el jugador perdedor, -1
     * para el estancamiento, o 0 para un juego todavía válido.
     *
     * @return int 1 o 2 para la jugada perdedora, -1 para tablas, o 0 para una
     * partida aún válida.
     */
    public int determineGameLost() {
        if (king1.isChecked(board) && !playerHasLegalMoves(1)) // player 1

        {
            return 1;
        }
        if (king2.isChecked(board) && !playerHasLegalMoves(2)) // player 2

        {
            return 2;
        }
        if ((!king1.isChecked(board) && !playerHasLegalMoves(1))
                || (!king2.isChecked(board) && !playerHasLegalMoves(2))
                || (board.getAllWhitePieces().size() == 1 &&
                        board.getAllBlackPieces().size() == 1)) // stalemate
        {
            return -1;
        }
        return 0; 
    }

    
    
    
    
    // ----------------------------------------------------------
    /**
     * Dado un MouseEvent de un usuario haciendo click en un cuadrado, se determina la acción apropiada.
     * acción apropiada. Las acciones incluyen: mover una pieza, mostrar los posibles movimientos de una pieza, o finalizar la partida después de comprobar las condiciones del juego.
     * posibles movimientos de una pieza, o terminar el juego después de comprobar las condiciones de juego.
     *
     * @param e
     * el evento de ratón del oyente
     */
    public void determineActionFromSquareClick(MouseEvent e) {
        BoardSquare squareClicked = (BoardSquare) e.getSource();
        ChessGamePiece pieceOnSquare = squareClicked.getPieceOnSquare();
        board.clearColorsOnBoard();
        if (firstClick) {
            handleFirstClick(squareClicked);
        	
        } else {
        	handleSecondClick(squareClicked, pieceOnSquare);
        }    
    }
    
    
    private void handleFirstClick(BoardSquare squareClicked) {
    	currentPiece = squareClicked.getPieceOnSquare();
        
    	if (selectedPieceIsValid()) {
            currentPiece.showLegalMoves(board);
            squareClicked.setBackground(Color.GREEN);
            firstClick = false;
        } else {
        	String message;
            if (currentPiece != null) {
                    message = "You tried to pick up the other player's piece! " + "Get some glasses and pick a valid square.";
                          //  "Illegal move",
                          //  JOptionPane.ERROR_MESSAGE);
                } else {
                   // JOptionPane.showMessageDialog(
                     //squareClicked,
                    message = "You tried to pick up an empty square! " + "Get some glasses and pick a valid square.";
                            //"Illegal move",
                            //JOptionPane.ERROR_MESSAGE);
                }
               JOptionPane.showMessageDialog(squareClicked, message,
               "Illegal move", JOptionPane.ERROR_MESSAGE);
            }
        } 
    	
    
    private void handleSecondClick(BoardSquare squareClicked, ChessGamePiece pieceOnSquare)
    	{
            if (pieceOnSquare == null || 
                    !pieceOnSquare.equals(currentPiece)) // moving
            {
                boolean moveSuccessful = currentPiece.move(
                        board,
                        squareClicked.getRow(),
                        squareClicked.getColumn());
                if (moveSuccessful) {
                    checkGameConditions();
                } else {
                    int row = squareClicked.getRow();
                    int col = squareClicked.getColumn();
                    //JOptionPane.showMessageDialog(
                    //squareClicked,
                    String message = "The move to row " + (row + 1) + " and column "
                                    + (col + 1)
                                    + " is either not valid or not legal "
                                    + "for this piece. Choose another move location, "
                                    + "and try using your brain this time!";
                            JOptionPane.showMessageDialog(squareClicked, message, "Invalid move",
                            JOptionPane.ERROR_MESSAGE);
                }
                firstClick = true;
            } else
            // user is just unselecting the current piece
            {
                firstClick = true;
            }
        }
    
}
