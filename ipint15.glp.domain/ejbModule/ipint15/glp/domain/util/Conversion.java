package ipint15.glp.domain.util;

import ipint15.glp.api.dto.CompetenceDTO;
import ipint15.glp.api.dto.EtudiantDTO;
import ipint15.glp.api.dto.EtudiantProfilDTO;
import ipint15.glp.domain.entities.Competence;
import ipint15.glp.domain.entities.Etudiant;
import ipint15.glp.domain.entities.EtudiantProfil;

public class Conversion {
	

	public EtudiantDTO toEtudiantDTO(Etudiant e) {
		EtudiantDTO pDTO = new EtudiantDTO();
		pDTO.setId(e.getId());
		pDTO.setPrenom(e.getPrenom());
		pDTO.setNom(e.getNom());
		pDTO.setCivilite(e.getCivilite());
		pDTO.setEmail(e.getEmail());
		pDTO.setPassword("password");
		pDTO.setNaissance(e.getNaissance());
		return pDTO;
		
		//EtudiantDTO eDTO = modelMapper.map(e, EtudiantDTO.class);
		//return eDTO;
	}

	public Etudiant toEtudiant(EtudiantDTO e) {
		Etudiant p = new Etudiant();
		p.setId(e.getId());
		p.setPrenom(e.getPrenom());
		p.setNom(e.getNom());
		p.setCivilite(e.getCivilite());
		p.setEmail(e.getEmail());
		p.setPassword("password");
		p.setNaissance(e.getNaissance());
		return p; 
		

	}
}
