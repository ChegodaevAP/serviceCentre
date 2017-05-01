package uiController;

import org.zkoss.zk.ui.Component;
import org.zkoss.zk.ui.select.SelectorComposer;
import org.zkoss.zk.ui.select.annotation.Listen;
import org.zkoss.zk.ui.select.annotation.Wire;
import org.zkoss.zkmax.zul.Navbar;
import org.zkoss.zul.Div;
import org.zkoss.zul.Hlayout;

public class SidebarComposer extends SelectorComposer<Component> {
	
	@Wire
    Hlayout main;
	@Wire
    Div sidebar;
	@Wire
    Navbar navbar;

	
	
	public void doAfterCompose(Component comp) throws Exception {
		super.doAfterCompose(comp);
	}
	
	// Toggle sidebar to collapse or expand
	@Listen("onClick = #toggler")
	public void toggleSidebarCollapsed() {
		if (navbar.isCollapsed()) {
			sidebar.setSclass("sidebar");
			navbar.setCollapsed(false);
		} else {
			sidebar.setSclass("sidebar sidebar-min");
			navbar.setCollapsed(true);
		}
	}
}