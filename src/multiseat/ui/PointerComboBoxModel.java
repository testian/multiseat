/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package multiseat.ui;

import java.util.HashSet;
import java.util.Set;
import javax.swing.ComboBoxModel;
import javax.swing.event.ListDataEvent;
import javax.swing.event.ListDataListener;
import multiseat.core.MultiSeatListener;
import multiseat.core.MultiSeatSystem;
import multiseat.core.Pointer;

/**
 *
 * @author testi
 */
public class PointerComboBoxModel implements ComboBoxModel, MultiSeatListener {

    private MultiSeatSystem system;
    private Set<Pointer> pointers;
    private Pointer selected;
    private Set<ListDataListener> listeners;
    private Pointer[] pArray;

    public PointerComboBoxModel(MultiSeatSystem system) {
        this.system = system;
        this.selected = null;
        this.pArray = new Pointer[0];
        listeners = new HashSet<ListDataListener>();
        system.addListener(this);
        onUpdate(system);
    }

    public void dispose() {
        system.removeListener(this);
    }

    final public void onUpdate(MultiSeatSystem system) {
        pointers = system.pointers();
        pArray = pointers.toArray(new Pointer[0]);

        if (pointers.isEmpty()) {
            selected = null;
        } else {

            if (selected == null) {
                selected = pointers.iterator().next();
            } else {
                boolean found = false;
                for (Pointer p : pointers) {

                    if (p.id().equals(selected.id())) {
                        selected = p;
                        found = true;
                    }
                }
                if (!found) {
                    selected = pointers.iterator().next();
                }


            }
        }
        notifyListeners();
    }

    private void notifyListeners() {
        for (ListDataListener l : listeners) {
            l.contentsChanged(new ListDataEvent(this, ListDataEvent.CONTENTS_CHANGED, 0, pointers.size()-1));
        }
    }

    public Object getSelectedItem() {
        return selected;
    }

    public void setSelectedItem(Object anItem) {
        
        Pointer willSelect = (Pointer)anItem;
        for (Pointer p : pointers) {
        if (p.id().equals(willSelect.id())) {
            selected = p;
            break;
        }
        }
    }

    public void addListDataListener(ListDataListener l) {
        listeners.add(l);
    }

    public Object getElementAt(int index) {
        return pArray[index];
    }

    public int getSize() {
        return pArray.length;
    }

    public void removeListDataListener(ListDataListener l) {
        listeners.remove(l);
    }
}
