package it.gas.samples.guestbook.admin;

import javax.ejb.EJB;
import javax.ejb.Stateless;

@Stateless
public class AppBean {
	@EJB
	private SessionBean session;
	
	public SessionBean getSession() {
		return session;
	}
}
