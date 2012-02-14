package it.gas.samples.guestbook.admin;

import javax.ejb.EJB;
import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.RequestScoped;
import javax.faces.context.FacesContext;
import javax.faces.event.ComponentSystemEvent;

@ManagedBean(name = "login")
@RequestScoped
public class LoginBacking {
	@EJB
	private AppBean app;
	private String password;

	public void checkLoginStatus(ComponentSystemEvent cse) {
		if (app.getSession().isLoggedIn()) {
			FacesContext fc = FacesContext.getCurrentInstance();
			fc.getApplication()
					.getNavigationHandler()
					.handleNavigation(fc, null,
							"index.xhtml?faces-redirect=true");
		}
	}
	
	public String doLogin() {
		if (app.getSession().login(password))
			return "index.xhtml?faces-redirect=true";

		FacesContext fc = FacesContext.getCurrentInstance();
		fc.addMessage("", new FacesMessage("Password not recognized."));
		return null;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

}
