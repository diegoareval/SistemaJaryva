
package com.devs.auxiliar;

import java.awt.event.KeyEvent;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import javax.swing.JTextField;

/**
 *
 * @author Diego Arevalo
 */
public class TextFieldEvent {

    public void textKeyPress(KeyEvent evt) {
        //Declaramos una variable y le asignamos un evento
        char car = evt.getKeyChar();
        //Condición que nos permite ingresar datos de tipo texto
        if ((car < 'a' || car > 'z') && (car < 'A' || car > 'Z')
                && (car != (char) KeyEvent.VK_BACK_SPACE) && (car != (char) KeyEvent.VK_SPACE)) {
            evt.consume();
        }
    }

    public void numberKeyPres(KeyEvent evt) {
        //Declaramos una variable y le asignamos un evento
        char car = evt.getKeyChar();
        //Condición que nos permite ingresar datos numéricos con su punto decimal
        if ((car < '0' || car > '9') && (car != (char) KeyEvent.VK_BACK_SPACE)) {
            evt.consume();
        }// else if ((car < '0' || car > '9') && (car != (char) KeyEvent.VK_BACK_SPACE)) {
//            evt.consume();
//        }
    }

    public void numberDecimalKeyPress(KeyEvent evt, JTextField textField) {
        //Declaramos una variable y le asignamos un evento
        char car = evt.getKeyChar();
        //Condición que nos permite ingresar datos numéricos con su punto decimal
        if ((car < '0' || car > '9') && textField.getText().contains(".")
                && (car != (char) KeyEvent.VK_BACK_SPACE)) {
            evt.consume();
        } else if ((car < '0' || car > '9') && (car != '.')
                && (car != (char) KeyEvent.VK_BACK_SPACE)) {
            evt.consume();
        }
    }

    public boolean isEmail(String correo) {
        Pattern pat = null;
        Matcher mat = null;
        pat = Pattern.compile("^[\\w\\-\\_\\+]+(\\.[\\w\\-\\_]+)*@([A-Za-z0-9-]+\\.)+[A-Za-z]{2,4}$");
        mat = pat.matcher(correo);
        if (mat.find()) {
            return true;
        } else {
            return false;
        }
    }
}
