package ipint15.glp.api.dto;


import org.hibernate.validator.constraints.NotEmpty;

public class ConnexionCommand {
	
	
	@NotEmpty( message = "Veuillez saisir votre adresse email" )
	private String email;
	@NotEmpty( message = "Veuillez saisir votre mot de passe" )
	private String password;
	
	public ConnexionCommand() {
		
	}
	
	public String getEmail() {
		return email;
	}
	public void setEmail(String email) {
		this.email = email;
	}
	public String getPassword() {
		return password;
	}
	public void setPassword(String password) {
		this.password = password;
	}
	
	

}
