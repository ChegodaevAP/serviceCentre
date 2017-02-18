/**
 * 
 */
package org.zkoss.reference.developer.spring.security.ui.error;

import java.util.Map;

import org.zkoss.reference.developer.spring.security.ui.security.SecurityUtil;
import org.zkoss.zk.ui.Execution;
import org.zkoss.zk.ui.Executions;
import org.zkoss.zk.ui.Page;
import org.zkoss.zk.ui.util.GenericInitiator;

public class AjaxAccessDeniedHandler extends GenericInitiator {

	public void doInit(Page page, Map<String, Object> args) throws Exception {
		
		Execution exec = Executions.getCurrent();
		
		if (null == SecurityUtil.getUser()){ //unauthenticated user
			exec.sendRedirect("/login.zul");
		}else{
			Executions.createComponents("/WEB-INF/errors/displayAccessDeniedException.zul", null, args);
		}
	}
}
