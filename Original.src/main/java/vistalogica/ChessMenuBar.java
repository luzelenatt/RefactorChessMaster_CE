package vistalogica;

import java.awt.Component;
import java.awt.event.*;
import javax.swing.*;

import vistainterfaz.ChessPanel;

// -------------------------------------------------------------------------
/**
 * Representa la barra de menú norte que contiene varios controles para el juego.
 *
 */
public class ChessMenuBar
        extends JMenuBar {

	
	
	/**
     * Crea un nuevo objeto ChessMenuBar.
     */
    public ChessMenuBar() {
        String[] menuCategories = { "File", "Options", "Help" };
        String[] menuItemLists = { "New game/restart,Exit", "Toggle graveyard,Toggle game log",
                "About" };
        for (int i = 0; i < menuCategories.length; i++) {
            JMenu currMenu = new JMenu(menuCategories[i]);
            String[] currMenuItemList = menuItemLists[i].split(",");
            for (int j = 0; j < currMenuItemList.length; j++) {
                JMenuItem currItem = new JMenuItem(currMenuItemList[j]);
                currItem.addActionListener(new MenuListener());
                currMenu.add(currItem);
            }
            this.add(currMenu);
        }
    }

    /**
     * Listener (Oyente) para la barra de menú norte.
     */
    private class MenuListener
            implements ActionListener {
        /**
         * Realiza una acción apropiada en función del botón de menú pulsado.
         *
         * @param event
         *              el evento del ratón de la fuente
         */
        @Override
        public void actionPerformed(ActionEvent event) {
            String buttonName = ((JMenuItem) event.getSource()).getText();
            if (buttonName.equals("About")) {
                aboutHandler();
            } else if (buttonName.equals("New game/restart")) {
                restartHandler();
            } else if (buttonName.equals("Toggle game log")) {
                toggleGameLogHandler();
            } else if (buttonName.equals("Exit")) {
                exitHandler();
            } else {
                toggleGraveyardHandler();
            }
        }
    }


    /**
     * Realiza una acción apropiada si se hace clic en el botón acerca de.
     */
    private void aboutHandler() {
        JOptionPane.showMessageDialog(
                this.getParent(),
                "YetAnotherChessGame v1.0 by:\nBen Katz\nMyles David\n"
                        + "Danielle Bushrow\n\nFinal Project for CS2114 @ VT");
    }

    
    
    /**
     * Realiza una acción apropiada si se pulsa el botón de reinicio.
     */
    private void restartHandler() {
        ((ChessPanel) this.getParent()).getGameEngine().reset();
    }

    /**
     * Realiza una acción apropiada si se pulsa el botón de salida.
     * Utiliza el código de Tony Allevato para salir de una aplicación GUI sin llamadas a System.exit()
     * llamadas.
     */
    private void exitHandler() {
        JOptionPane.showMessageDialog(this.getParent(), "Thanks for leaving"
                + ", quitter! >:(");
        Component possibleFrame = this;
        while (possibleFrame != null && !(possibleFrame instanceof JFrame)) {
            possibleFrame = possibleFrame.getParent();
        }
        JFrame frame = (JFrame) possibleFrame;
        if (frame != null) {
            frame.setVisible(false);
            frame.dispose();
        }
    }

    /**
     * Realiza una acción apropiada si se pulsa el botón de alternar cementerio.
     */
    private void toggleGraveyardHandler() {
        ((ChessPanel) this.getParent()).getGraveyard(1).setVisible(
                !((ChessPanel) this.getParent()).getGraveyard(1).isVisible());
        ((ChessPanel) this.getParent()).getGraveyard(2).setVisible(
                !((ChessPanel) this.getParent()).getGraveyard(2).isVisible());
    }

    /**
     * Realiza una acción apropiada si se pulsa el botón de registro de partidas.
     */
    private void toggleGameLogHandler() {
        ((ChessPanel) this.getParent()).getGameLog().setVisible(
                !((ChessPanel) this.getParent()).getGameLog().isVisible());
        ((ChessPanel) this.getParent()).revalidate();
    }
}
