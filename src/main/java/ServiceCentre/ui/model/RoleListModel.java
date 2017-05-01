package ServiceCentre.ui.model;


import org.zkoss.zul.AbstractListModel;
import org.zkoss.zul.event.ListDataEvent;
import org.zkoss.zul.ext.Sortable;

import java.util.Comparator;

public class RoleListModel extends AbstractListModel<String> implements Sortable<String> {

    private static final long serialVersionUID = -3086046175152725037L;

    private int _size;
    private boolean _asc = true;

    public RoleListModel() {
        this(10000);
    }
    public RoleListModel(int size) {
        _size = size;
    }

    @Override
    public void sort(Comparator<String> cmpr, boolean asc) {
        _asc = asc;
        invalidate();
    }

    public void invalidate() {
        fireEvent(ListDataEvent.CONTENTS_CHANGED, -1, -1);
    }

    public String getElementAt(int v) {
        String value = "item"+(_asc ? v: _size - v);
        return value;
    }

    public int getSize() {
        return _size;
    }

    public void setSize(int size){
        _size = size;
    }
    @Override
    public String getSortDirection(Comparator<String> arg0) {
        return null;
    }
}