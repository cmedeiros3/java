/**
 * 
 */

package util;

import javax.faces.context.FacesContext;
import javax.faces.event.PhaseEvent;
import javax.faces.event.PhaseId;
import javax.faces.event.PhaseListener;
import javax.servlet.http.HttpServletResponse;

/**
* 
*/

public class CacheControlPhaseListener implements PhaseListener {
 /**
* 
*/

private static final long serialVersionUID = 4713011778559154436L;

public PhaseId getPhaseId() {
 return PhaseId.RENDER_RESPONSE;
}

public void afterPhase(PhaseEvent event) {
}

public void beforePhase(PhaseEvent event) {
 System.out.print("Cache Class");
 FacesContext facesContext = event.getFacesContext();
 HttpServletResponse response = (HttpServletResponse) facesContext.getExternalContext().getResponse();
 response.addHeader("Pragma", "no-cache");
 response.addHeader("Cache-Control", "no-cache");
 // Stronger according to blog comment below that references HTTP spec
 response.addHeader("Cache-Control", "no-store");
 response.addHeader("Cache-Control", "must-revalidate");
 // some date in the past
 response.addHeader("Expires", "Wed, 22 April 2015 10:00:00 GMT");
}
}


/*<!--  <lifecycle>
<phase-listener id="nocache">util.CacheControlPhaseListener</phase-listener>
</lifecycle> -->*/