package it.gas.samples.guestbook;

import it.gas.samples.guestbook.persistence.Sign;

import java.util.List;

import javax.ejb.EJB;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.RequestScoped;
import javax.faces.context.FacesContext;
import javax.faces.event.ComponentSystemEvent;

@ManagedBean(name = "gbBacking")
@RequestScoped
public class GuestbookBacking {
	@EJB
	private GuestbookEJB gbEJB;
	private List<Sign> list;
	private int page;
	private long totalPages;
	private boolean nextAvailable;
	private boolean prevAvailable;

	public void onLoad(ComponentSystemEvent cse) {
		//load and fill fields
		long signsCount = gbEJB.getSignsCount();
		if (signsCount == 0) { //handling divide-by-zero case
			totalPages = 1;
		} else {
			totalPages = (long) Math.ceil((double) signsCount
					/ GuestbookEJB.RESULT_PER_PAGE);
		}
		prevAvailable = page > 0;
		nextAvailable = page < totalPages - 1;
		//redirect if wrong number page
		if (page < 0 || page >= totalPages) {
			FacesContext fc = FacesContext.getCurrentInstance();
			fc.getApplication().getNavigationHandler()
					.handleNavigation(fc, null, "index?faces-redirect=true");
		}
		list = gbEJB.getSigns(page);


	}

	public List<Sign> getList() {
		return list;
	}

	public int getPage() {
		return page;
	}

	public void setPage(int page) {
		this.page = page;
	}

	public long getTotalPages() {
		return totalPages;
	}

	public boolean isNextAvailable() {
		return nextAvailable;
	}

	public boolean isPrevAvailable() {
		return prevAvailable;
	}

}
