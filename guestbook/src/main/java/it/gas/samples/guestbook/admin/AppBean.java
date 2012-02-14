package it.gas.samples.guestbook.admin;

import it.gas.samples.guestbook.persistence.Setting;

import javax.ejb.EJB;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

@Stateless
public class AppBean {
	@EJB
	private SessionBean session;
	@PersistenceContext(unitName = "guestbookPU")
	private EntityManager em;
	
	public SessionBean getSession() {
		return session;
	}
	
	public String getTitle() {
		Setting s = em.find(Setting.class, "title");
		if (s == null)
			return "Guestbook";
		return s.getValue();
	}
}
