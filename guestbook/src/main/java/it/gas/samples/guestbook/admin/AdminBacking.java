package it.gas.samples.guestbook.admin;

import javax.ejb.EJB;
import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.RequestScoped;
import javax.faces.context.FacesContext;
import javax.faces.event.ComponentSystemEvent;

@ManagedBean(name = "admin")
@RequestScoped
public class AdminBacking {
	@EJB
	private AppBean app;
	private String password;
	private String title;

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
	
	public void loadData(ComponentSystemEvent cse) {
		title = app.getTitle();
	}

	public void doChangePassword() {
		app.getSession().changePassword(password);
		FacesContext cx = FacesContext.getCurrentInstance();
		cx.addMessage(null, new FacesMessage("Password successfully changed."));
	}
	
	public void doChangeTitle() {
		app.getSession().changeTitle(title);
		FacesContext cx = FacesContext.getCurrentInstance();
		cx.addMessage(null, new FacesMessage("Title successfully changed."));
	}
	
	public String doLogout() {
		app.getSession().logout();
		return "login.xhtml?faces-redirect=true";
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public String getTitle() {
		return title;
	}

	public void setTitle(String title) {
		this.title = title;
	}

}
