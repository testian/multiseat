/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package multiseat.ui;

import java.awt.Color;
import java.awt.Component;
import javax.swing.DefaultListCellRenderer;
import javax.swing.JList;
import multiseat.core.InputDevice;

/**
 *
 * @author testi
 */
public class InUseCellRenderer extends DefaultListCellRenderer {

    @Override
    public Component getListCellRendererComponent(JList list, Object value, int index, boolean isSelected, boolean cellHasFocus) {
        super.getListCellRendererComponent(list, value, index, isSelected, cellHasFocus);
        this.setText(value.toString());
        this.setBackground( ((InputDevice)value).links().isEmpty() ? Color.WHITE : Color.LIGHT_GRAY);
        return this;
    }

}
