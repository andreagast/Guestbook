package it.gas.samples.guestbook.admin;

import it.gas.samples.guestbook.persistence.Setting;

import javax.ejb.Stateful;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

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
		
		Setting s = em.find(Setting.class, "adminPassword");
		String found = null;
		if (s != null) {
			found = s.getValue();
		} else {
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
	
	public void changePassword(String newPassword) {
		if (! isLoggedIn())
			return;
		Setting s = em.find(Setting.class, "adminPassword");
		if (s == null) {
			s = new Setting();
			s.setChiave("adminPassword");
			s.setValue(newPassword);
			em.persist(s);
		} else {
			s.setValue(newPassword);
			em.merge(s);
		}
	}
	
	public void changeTitle(String newTitle) {
		if (! isLoggedIn())
			return;
		Setting s = em.find(Setting.class, "title");
		if (s == null) {
			s = new Setting();
			s.setChiave("title");
			s.setValue(newTitle);
			em.persist(s);
		} else {
			s.setValue(newTitle);
			em.merge(s);
		}
	}
}
