package it.gas.samples.guestbook.persistence;

import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.NamedQuery;
import javax.persistence.NamedQueries;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

@Entity
@NamedQueries({
	@NamedQuery(name = "sign.all", query = "SELECT s FROM Sign s"),
	@NamedQuery(name = "sign.count", query = "SELECT COUNT(s) FROM Sign s")
})
public class Sign {
	private int id;
	private Date created;
	private String nickname;
	private String message;

	@Id @GeneratedValue(strategy = GenerationType.IDENTITY)
	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	@Column(nullable = false)
	@Temporal(TemporalType.TIMESTAMP)
	public Date getCreated() {
		return created;
	}

	public void setCreated(Date created) {
		this.created = created;
	}

	@Column(nullable = false, length = 25)
	public String getNickname() {
		return nickname;
	}

	public void setNickname(String nickname) {
		this.nickname = nickname;
	}
	
	@Column(nullable = false, length = 2000)
	public String getMessage() {
		return message;
	}

	public void setMessage(String message) {
		this.message = message;
	}

}
