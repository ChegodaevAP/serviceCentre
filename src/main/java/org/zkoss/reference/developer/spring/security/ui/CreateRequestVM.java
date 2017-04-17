package org.zkoss.reference.developer.spring.security.ui;

import fr.opensagres.xdocreport.core.XDocReportException;
import fr.opensagres.xdocreport.document.IXDocReport;
import fr.opensagres.xdocreport.document.registry.XDocReportRegistry;
import fr.opensagres.xdocreport.template.IContext;
import fr.opensagres.xdocreport.template.TemplateEngineKind;
import org.zkoss.bind.annotation.*;
import org.zkoss.reference.developer.spring.security.model.Client;
import org.zkoss.reference.developer.spring.security.model.Defect;
import org.zkoss.reference.developer.spring.security.model.Request;
import org.zkoss.reference.developer.spring.security.service.ClientService;
import org.zkoss.reference.developer.spring.security.service.DefectService;
import org.zkoss.reference.developer.spring.security.service.RequestService;
import org.zkoss.zk.ui.Executions;
import org.zkoss.zk.ui.select.annotation.VariableResolver;
import org.zkoss.zk.ui.select.annotation.WireVariable;
import org.zkoss.zk.ui.util.Clients;
import org.zkoss.zkplus.spring.DelegatingVariableResolver;
import org.zkoss.zul.Filedownload;
import org.zkoss.zul.ListModel;
import org.zkoss.zul.ListModelList;
import org.zkoss.zul.Window;

import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.List;

@VariableResolver(DelegatingVariableResolver.class)
public class CreateRequestVM {
    public static final String INSERT_ERROR = "Запись уже существует";
    public static final String INSERT_SUCCESS = "Успешно";
    public static final String CREATE_CLIENT = "/create-client.zul";
    public static final String CLIENT_LIST = "/client-list.zul";
    public static final String SELECTED_CLIENT = "refreshSelectedClient";
    private Window window;
    private Request request;
    private Client client;
    private static final int INIT_SIZE_OUT_BUFFER = 30000;
    @WireVariable
    private RequestService requestService;
    @WireVariable
    private ClientService clientService;
    @WireVariable
    private DefectService defectService;
    private ListModel<String> defectListModel;


    @Init(superclass = true)
    public void init(@ContextParam(ContextType.COMPONENT) Window window) {
        List<String> defects = new ArrayList<String>();
        for (Defect defect : defectService.getAllDefect()) {
            defects.add(defect.getName());
        }

        defectListModel = new ListModelList<String>(defects);
        ((ListModelList<String>) defectListModel).setMultiple(true);
        this.window = window;
        request = new Request();
    }

    @Command
    @NotifyChange("requestListModel")
    public void create() {
        request.setClient(client);
        if (requestService.insertRequest(request) == null) {
            Clients.showNotification(INSERT_ERROR);
        } else {
            Clients.showNotification(INSERT_SUCCESS);
            window.detach();
        }
    }

    @Command
    public void selectClient() {
        Window wind = (Window) Executions.createComponents(CLIENT_LIST, window, null);
        wind.setTitle("Выберите клиента");
        wind.setWidth("40%");
        wind.setClosable(true);
        wind.doModal();
    }

    @GlobalCommand(SELECTED_CLIENT)
    @NotifyChange("client")
    public void refreshSelectedClient(@BindingParam("value") Client selectedClient) {
        this.client = selectedClient;
    }

    public Request getRequest() {
        return request;
    }

    public void setRequest(Request request) {
        this.request = request;
    }

    public Client getClient() {
        return client;
    }

    public void setClient(Client client) {
        this.client = client;
    }

    @Command
    public void print() {
        try {
            // 1) Load Docx file by filling Freemarker template engine and cache
            // it to the registry

            InputStream in = ViewRequestVM.class
                    .getResourceAsStream("RequestReport.docx");

            IXDocReport report = XDocReportRegistry.getRegistry().loadReport(
                    in, TemplateEngineKind.Freemarker);
//            // 2) Create context Java model
            IContext context = report.createContext();
            context.put("request", request);
//            // 3) Generate report by merging Java model with the Docx
            ByteArrayOutputStream out = new ByteArrayOutputStream();

//            OutputStream out = new FileOutputStream(new File(
//                    "E:/DocxProjectWithFreemarker_Out.docx"));
            report.process(context, out);

            Filedownload.save(requestService.getReport().getFile(), ".docx", "out");


        } catch (IOException e) {
            e.printStackTrace();
        } catch (XDocReportException e) {
            e.printStackTrace();
        }
    }

    public ListModel<String> getDefectListModel() {
        return defectListModel;
    }

    public void setDefectListModel(ListModel<String> defectListModel) {
        this.defectListModel = defectListModel;
    }
}
