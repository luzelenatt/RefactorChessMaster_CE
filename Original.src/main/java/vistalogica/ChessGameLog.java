package vistalogica;

import javax.swing.JTextArea;
import javax.swing.JScrollPane;
import java.util.Date;

// -------------------------------------------------------------------------
/**
 * Un área de texto desplazable que representa el registro del juego. (es decir, los movimientos realizados por cada
 * jugador)
 *
 */
public class ChessGameLog
        extends JScrollPane {
    private JTextArea textArea;

    // ----------------------------------------------------------
    /**
     * Crea un nuevo objeto ChessGameLog.
     */
    public ChessGameLog() {
        super(
                new JTextArea("", 5, 30),
                JScrollPane.VERTICAL_SCROLLBAR_ALWAYS,
                JScrollPane.HORIZONTAL_SCROLLBAR_ALWAYS);
        textArea = ((JTextArea) this.getViewport().getView());
    }

    // ----------------------------------------------------------
    /**
     * Añade una nueva línea de texto al registro.
     * 
     * @param s
     * la línea de texto a añadir
     */
    public void addToLog(String s) {
        if (textArea.getText().length() > 0) {
            textArea.setText(textArea.getText() + "\n" + new Date() + " - "
                    + s);
        } else {
            textArea.setText(new Date() + " - " + s);
        }
    }

    /**
     * Borra el registro.
     */
    public void clearLog() {
        textArea.setText("");
    }

    // ----------------------------------------------------------
    /**
     * Obtiene la declaración más reciente añadida al registro.
     * 
     * @return String la sentencia más reciente del registro
     */
    public String getLastLog() {
        int indexOfLastNewLine = textArea.getText().lastIndexOf("\n");
        if (indexOfLastNewLine < 0) {
            return textArea.getText();
        }
        return textArea.getText().substring(indexOfLastNewLine + 1);
    }


}
