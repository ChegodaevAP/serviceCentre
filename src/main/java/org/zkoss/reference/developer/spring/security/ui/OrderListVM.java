package org.zkoss.reference.developer.spring.security.ui;


import org.zkoss.bind.annotation.Command;
import org.zkoss.bind.annotation.ContextParam;
import org.zkoss.bind.annotation.ContextType;
import org.zkoss.bind.annotation.Init;
import org.zkoss.reference.developer.spring.security.model.Request;
import org.zkoss.reference.developer.spring.security.service.OrderService;
import org.zkoss.zk.ui.Executions;
import org.zkoss.zk.ui.select.annotation.WireVariable;
import org.zkoss.zul.ListModel;
import org.zkoss.zul.ListModelList;
import org.zkoss.zul.Window;

public class OrderListVM {

    public static final String CREATE_ORDER = "/create-order.zul";
    private Window window;
    @WireVariable
    private OrderService orderService;

    private ListModel<Request> orderListModel;

    private Request selectedOrder;

    private String keyWord;

    @Init(superclass = true)
    public void init(@ContextParam(ContextType.COMPONENT) Window window) {
        this.window = window;
        orderListModel = new ListModelList<Request>(orderService.getAllOrder());
        ((ListModelList<Request>) orderListModel).setMultiple(true);
    }

    @Command
    public void addOrder() {
        Window wind = (Window) Executions.createComponents(CREATE_ORDER, window, null);
        wind.doModal();
    }

    @Command
    public void search() {

    }

    public ListModel<Request> getOrderListModel() {
        return orderListModel;
    }

    public void setOrderListModel(ListModel<Request> orderListModel) {
        this.orderListModel = orderListModel;
    }

    public Request getSelectedOrder() {
        return selectedOrder;
    }

    public void setSelectedOrder(Request selectedOrder) {
        this.selectedOrder = selectedOrder;
    }

    public String getKeyWord() {
        return keyWord;
    }

    public void setKeyWord(String keyWord) {
        this.keyWord = keyWord;
    }
}
