package ipint15.glp.domain.util;

import ipint15.glp.api.dto.EtudiantDTO;
import ipint15.glp.domain.entities.Etudiant;

public class ConversionEtudiant {

	public EtudiantDTO toEtudiantDTO(Etudiant e) {
		EtudiantDTO pDTO = new EtudiantDTO();
		pDTO.setId(e.getId());
		pDTO.setPrenom(e.getPrenom());
		pDTO.setNom(e.getNom());
		pDTO.setEmail(e.getEmail());
		pDTO.setPassword("password");
		pDTO.setNaissance(e.getNaissance());
		return pDTO;	
	}

	public Etudiant toEtudiant(EtudiantDTO e) {
		Etudiant p = new Etudiant();
		p.setId(e.getId());
		p.setPrenom(e.getPrenom());
		p.setNom(e.getNom());
		p.setEmail(e.getEmail());
		p.setPassword("password");
		p.setNaissance(e.getNaissance());
		return p;
	}
}
