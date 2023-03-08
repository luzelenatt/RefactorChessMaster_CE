package vistainterfaz;

import java.awt.*;
import javax.swing.*;

import vistainterfaz.board.ChessGameBoard;
import vistalogica.ChessGameEngine;
import vistalogica.ChessGameLog;
import vistalogica.ChessGraveyard;
import vistalogica.ChessMenuBar;

// -------------------------------------------------------------------------
/**
 * El panel principal del juego Ajedrez.
 */
public class ChessPanel
        extends JPanel {
    private ChessMenuBar menuBar;
    private ChessGameBoard gameBoard;
    private ChessGameLog gameLog;
    private ChessGraveyard playerOneGraveyard;
    private ChessGraveyard playerTwoGraveyard;
    private transient ChessGameEngine gameEngine;


    /**
     * Crea un nuevo objeto ChessPanel.
     */
    public ChessPanel() {
        this.setLayout(new BorderLayout());
        menuBar = new ChessMenuBar();
        gameBoard = new ChessGameBoard();
        gameLog = new ChessGameLog();
        playerOneGraveyard = new ChessGraveyard("Player 1's graveyard");
        playerTwoGraveyard = new ChessGraveyard("Player 2's graveyard");
        this.add(menuBar, BorderLayout.NORTH);
        this.add(gameBoard, BorderLayout.CENTER);
        this.add(gameLog, BorderLayout.SOUTH);
        this.add(playerOneGraveyard, BorderLayout.WEST);
        this.add(playerTwoGraveyard, BorderLayout.EAST);
        this.setPreferredSize(new Dimension(800, 600));
        gameEngine = new ChessGameEngine(gameBoard); // start the game
    }

    
    /**
     * Obtiene el objeto logger para su uso en otras clases.
     * 
     * @return ChessGameLog el objeto ChessGameLog
     */
    public ChessGameLog getGameLog() {
        return gameLog;
    }

    /**
     * Obtiene el objeto tablero para su uso en otras clases.
     * 
     * @return ChessGameBoard el objeto ChessGameBoard
     */
    public ChessGameBoard getGameBoard() {
        return gameBoard;
    }

    
    /**
     * Obtiene el objeto motor de juego para su uso en otras clases.
     * 
     * @return ChessGameEngine el objeto ChessGameEngine
     */
    public ChessGameEngine getGameEngine() {
        return gameEngine;
    }


    /**
     * Obtiene el objeto cementerio apropiado para su uso en otras clases.
     * 
     * @param whichPlayer
     * el número del jugador (1 o 2)
     * @return ChessGraveyard el cementerio solicitado
     */
    public ChessGraveyard getGraveyard(int whichPlayer) {
        if (whichPlayer == 1) {
            return playerOneGraveyard;
        } else if (whichPlayer == 2) {
            return playerTwoGraveyard;
        } else {
            return null;
        }
    }
}
