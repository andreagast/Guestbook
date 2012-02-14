package it.gas.samples.guestbook.persistence;

import javax.persistence.Entity;
import javax.persistence.Id;

@Entity
public class Setting {
	@Id
	private String chiave;
	private String value;

	public String getChiave() {
		return chiave;
	}

	public void setChiave(String key) {
		this.chiave = key;
	}

	public String getValue() {
		return value;
	}

	public void setValue(String value) {
		this.value = value;
	}

}
