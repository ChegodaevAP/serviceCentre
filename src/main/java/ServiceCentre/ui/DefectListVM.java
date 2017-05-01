package ServiceCentre.ui;

import org.zkoss.bind.annotation.*;
import ServiceCentre.model.Defect;
import ServiceCentre.service.DefectService;
import org.zkoss.zk.ui.Executions;
import org.zkoss.zk.ui.select.annotation.VariableResolver;
import org.zkoss.zk.ui.select.annotation.WireVariable;
import org.zkoss.zkplus.spring.DelegatingVariableResolver;
import org.zkoss.zul.ListModel;
import org.zkoss.zul.ListModelList;
import org.zkoss.zul.Window;

import static ServiceCentre.ui.CreateDefectVM.DEFECT_CHANGE;

@VariableResolver(DelegatingVariableResolver.class)
public class DefectListVM {
    @WireVariable
    private DefectService defectService;
    private Window window;

    private Defect selectedDefect;
    private String keyWord;
    private ListModel<Defect> defectListModel;
    private final String CREATE_DEFECT = "create-defect.zul";

    @Init(superclass = true)
    public void init(@ContextParam(ContextType.COMPONENT) Window window) {
        this.window = window;
        refresh();
    }

    @GlobalCommand(DEFECT_CHANGE)
    @NotifyChange("defectListModel")
    public void refresh() {
        defectListModel = new ListModelList<Defect>(defectService.getAllDefect());
        ((ListModelList<Defect>) defectListModel).setMultiple(true);
    }
    @Command
    public void addClient() {
        Window wind = (Window) Executions.createComponents(CREATE_DEFECT, window, null);
        wind.doModal();
    }

    public Defect getSelectedDefect() {
        return selectedDefect;
    }

    public void setSelectedDefect(Defect selectedDefect) {
        this.selectedDefect = selectedDefect;
    }

    public ListModel<Defect> getDefectListModel() {
        return defectListModel;
    }

    public void setDefectListModel(ListModel<Defect> defectListModel) {
        this.defectListModel = defectListModel;
    }

    public String getKeyWord() {
        return keyWord;
    }

    public void setKeyWord(String keyWord) {
        this.keyWord = keyWord;
    }
}
