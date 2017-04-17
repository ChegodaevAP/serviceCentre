package org.zkoss.reference.developer.spring.security.ui;

import org.zkoss.bind.BindUtils;
import org.zkoss.bind.annotation.Command;
import org.zkoss.bind.annotation.ContextParam;
import org.zkoss.bind.annotation.ContextType;
import org.zkoss.bind.annotation.Init;
import org.zkoss.reference.developer.spring.security.model.Defect;
import org.zkoss.reference.developer.spring.security.service.DefectService;
import org.zkoss.zk.ui.select.annotation.VariableResolver;
import org.zkoss.zk.ui.select.annotation.WireVariable;
import org.zkoss.zk.ui.util.Clients;
import org.zkoss.zkplus.spring.DelegatingVariableResolver;
import org.zkoss.zul.Window;

import java.util.Collections;

import static org.zkoss.reference.developer.spring.security.ui.CreateRequestVM.INSERT_ERROR;
import static org.zkoss.reference.developer.spring.security.ui.CreateRequestVM.INSERT_SUCCESS;

@VariableResolver(DelegatingVariableResolver.class)
public class CreateDefectVM {
    @WireVariable
    private DefectService defectService;
    private Defect defect;
    private Window window;
    public static final String DEFECT_CHANGE = "defect_change";


    @Init(superclass = true)
    public void init(@ContextParam(ContextType.COMPONENT) Window window) {
        this.window = window;
        defect = new Defect();
    }

    @Command
    public void create() {
        if (defectService.addNewDefect(defect) == null) {
            Clients.showNotification(INSERT_ERROR);
        } else {
            Clients.showNotification(INSERT_SUCCESS);
            close();
        }
    }

    @Command
    public void close() {
        BindUtils.postGlobalCommand(null, null, DEFECT_CHANGE,
                Collections.<String, Object>singletonMap("value", defect));
        window.onClose();
    }

    public Defect getDefect() {
        return defect;
    }

    public void setDefect(Defect defect) {
        this.defect = defect;
    }
}
