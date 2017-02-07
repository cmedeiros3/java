/**
 * 
 */

package filter;

import java.io.IOException;
import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.annotation.WebFilter;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import model.User;

/**
* 
*/

@WebFilter("*.xhtml")
public class AutorizacaoFilter implements Filter {

 public void destroy() {

 }

 public void doFilter(ServletRequest req, ServletResponse res, FilterChain chain) throws IOException, ServletException {
     HttpServletRequest request = (HttpServletRequest) req;
     HttpServletResponse response = (HttpServletResponse) res;

     if ((!User.isLogged()) && (!request.getRequestURI().endsWith("/pages/login/Login.xhtml"))
             && (!request.getRequestURI().contains("/javax.faces.resource/"))&&(!request.getRequestURI().endsWith("/pages/login/CreateUserOuter.xhtml"))
             ) {
         response.sendRedirect(request.getContextPath() + "/pages/login/Login.xhtml");
     } else {
         chain.doFilter(req, res);
     }

 }

 public void init(FilterConfig arg0) throws ServletException {

 }

}
