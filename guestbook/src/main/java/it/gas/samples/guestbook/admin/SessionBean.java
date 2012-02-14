package it.gas.samples.guestbook.admin;

import it.gas.samples.guestbook.persistence.Setting;

import javax.ejb.Stateful;
import javax.persistence.EntityManager;
import javax.persistence.NoResultException;
import javax.persistence.PersistenceContext;
import javax.persistence.TypedQuery;

@Stateful
public class SessionBean {
	
	@PersistenceContext(unitName = "guestbookPU")
	private EntityManager em;

	private boolean loggedIn = false;
	
	public boolean login(String pass) {
		if (isLoggedIn()) //already logged
			return true;
		if (pass == null) //not null password
			return false;
		
		TypedQuery<Setting> query = em.createNamedQuery("setting.key", Setting.class);
		query.setParameter("key", "adminPassword");
		String found = null;
		try {
			Setting s = query.getSingleResult();
			found = s.getValue();
		} catch (NoResultException e) {
			found = "";
		}
		if (found.compareTo(pass) == 0)
			loggedIn = true;
		return isLoggedIn();
	}
	
	public void logout() {
		loggedIn = false;
		System.out.println("Logged out.");
	}
	
	public boolean isLoggedIn() {
		return loggedIn;
	}
}
