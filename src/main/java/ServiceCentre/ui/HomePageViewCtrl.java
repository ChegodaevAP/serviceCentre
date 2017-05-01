/**
 *
 */
package ServiceCentre.ui;

import org.zkoss.zk.ui.Component;
import org.zkoss.zk.ui.Executions;
import org.zkoss.zk.ui.select.SelectorComposer;
import org.zkoss.zk.ui.select.annotation.VariableResolver;
import org.zkoss.zk.ui.select.annotation.Wire;
import org.zkoss.zkplus.spring.DelegatingVariableResolver;
import org.zkoss.zul.Grid;

import java.security.Principal;

/**
 * @author Ian YT Tsai (zanyking)
 */
@VariableResolver(DelegatingVariableResolver.class)
public class HomePageViewCtrl extends SelectorComposer<Component> {

    @Wire
    private Grid articlesGrid;


    @Override
    public void doAfterCompose(Component comp) throws Exception {
        super.doAfterCompose(comp);
        Principal p = Executions.getCurrent().getUserPrincipal();

    }
}
