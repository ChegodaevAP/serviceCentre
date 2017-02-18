package org.zkoss.reference.developer.spring.security.ui;


import org.zkoss.bind.annotation.Command;
import org.zkoss.bind.annotation.ContextParam;
import org.zkoss.bind.annotation.ContextType;
import org.zkoss.bind.annotation.Init;
import org.zkoss.reference.developer.spring.security.model.Order;
import org.zkoss.reference.developer.spring.security.model.Place;
import org.zkoss.reference.developer.spring.security.service.DirectoryService;
import org.zkoss.reference.developer.spring.security.service.OrderService;
import org.zkoss.zk.ui.select.annotation.WireVariable;
import org.zkoss.zul.ListModel;
import org.zkoss.zul.ListModelList;
import org.zkoss.zul.Window;

public class OrderListVM {

    private final String CREATE_PLACE = "/create-place.zul";
    private Window window;
    @WireVariable
    private OrderService orderService;

    private ListModel<Order> orderListModel;

    private Order selectedOrder;

    private String keyWord;
    @Init(superclass = true)
    public void init(@ContextParam(ContextType.COMPONENT) Window window) {
        this.window = window;
        orderListModel = new ListModelList<Order>(orderService.getAllOrder());
        ((ListModelList<Order>) orderListModel).setMultiple(true);
    }

    @Command
    public void search() {

    }

    public ListModel<Order> getOrderListModel() {
        return orderListModel;
    }

    public void setOrderListModel(ListModel<Order> orderListModel) {
        this.orderListModel = orderListModel;
    }

    public Order getSelectedOrder() {
        return selectedOrder;
    }

    public void setSelectedOrder(Order selectedOrder) {
        this.selectedOrder = selectedOrder;
    }

    public String getKeyWord() {
        return keyWord;
    }

    public void setKeyWord(String keyWord) {
        this.keyWord = keyWord;
    }
}
