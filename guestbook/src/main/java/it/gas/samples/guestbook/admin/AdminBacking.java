package it.gas.samples.guestbook.admin;

import javax.ejb.EJB;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.RequestScoped;
import javax.faces.context.FacesContext;
import javax.faces.event.ComponentSystemEvent;

@ManagedBean(name = "admin")
@RequestScoped
public class AdminBacking {
	@EJB
	private AppBean app;

	public void checkLoginStatus(ComponentSystemEvent cse) {
		System.out.println(app.getSession().isLoggedIn());
		if (app.getSession().isLoggedIn() == false) {
			System.err.println("Not logged in, redirecting...");
			FacesContext fc = FacesContext.getCurrentInstance();
			fc.getApplication()
					.getNavigationHandler()
					.handleNavigation(fc, null,
							"login.xhtml?faces-redirect=true");
		}
	}
	
	public String logout() {
		app.getSession().logout();
		return "login.xhtml?faces-redirect=true";
	}

}
