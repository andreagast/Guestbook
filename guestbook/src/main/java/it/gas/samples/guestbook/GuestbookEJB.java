package it.gas.samples.guestbook;

import it.gas.samples.guestbook.persistence.Sign;

import java.util.Calendar;
import java.util.List;

import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.TypedQuery;

@Stateless
public class GuestbookEJB {
	public static int RESULT_PER_PAGE = 25;
	@PersistenceContext(unitName = "guestbookPU")
	private EntityManager em;
	
	public long getSignsCount() {
		return em.createNamedQuery("sign.count", Long.class).getSingleResult();
	}
	
	public List<Sign> getSigns(int page) {
		if (page < 0)
			page = 0;
		TypedQuery<Sign> query = em.createNamedQuery("sign.all", Sign.class);
		query.setFirstResult(page * RESULT_PER_PAGE);
		query.setMaxResults(RESULT_PER_PAGE);
		return query.getResultList();
	}
	
	public void addSign(String nick, String message) {
		Sign sign = new Sign();
		sign.setCreated(Calendar.getInstance().getTime());
		sign.setNickname(nick);
		sign.setMessage(message);
		em.persist(sign);
	}
	
}
