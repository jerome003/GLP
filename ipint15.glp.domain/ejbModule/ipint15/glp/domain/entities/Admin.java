package ipint15.glp.domain.entities;

import java.io.Serializable;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

import ipint15.glp.api.dto.AdminDTO;


/**
 * @author kabri
 *
 */
@Entity
@Table(name = "ADMIN")
public class Admin implements Serializable {
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private int id;
	
	private String password;
	private String email;
	
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	
	public String getPassword() {
		return password;
	}
	public void setPassword(String password) {
		this.password = password;
	}
	public String getEmail() {
		return email;
	}
	public void setEmail(String email) {
		this.email = email;
	}
	
	public AdminDTO toAdminDTO() {
		AdminDTO aDTO = new AdminDTO();
		aDTO.setId(this.getId());
		aDTO.setEmail(this.getEmail());
		aDTO.setPassword("password");
	

		return aDTO;
	}



	
	
}