package it.gas.samples.guestbook;

import it.gas.samples.guestbook.admin.AppBean;

import javax.ejb.EJB;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.RequestScoped;

@ManagedBean
@RequestScoped
public class AddBacking {
	@EJB
	private GuestbookEJB gbEJB;
	@EJB
	private AppBean app;
	private String nickname;
	private String message;
	
	public String sign() {
		gbEJB.addSign(nickname, message);
		return "index?faces-redirect=true";
	}
	
	public String getTitle() {
		return app.getTitle();
	}

	public String getNickname() {
		return nickname;
	}

	public void setNickname(String nickname) {
		this.nickname = nickname;
	}

	public String getMessage() {
		return message;
	}

	public void setMessage(String message) {
		this.message = message;
	}

}
