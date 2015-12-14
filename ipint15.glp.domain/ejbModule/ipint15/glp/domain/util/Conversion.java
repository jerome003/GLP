package ipint15.glp.domain.util;

import ipint15.glp.api.dto.CompetenceDTO;
import ipint15.glp.api.dto.EtudiantDTO;
import ipint15.glp.api.dto.EtudiantProfilDTO;
import ipint15.glp.api.dto.PublicationDTO;
import ipint15.glp.domain.entities.Competence;
import ipint15.glp.domain.entities.Etudiant;
import ipint15.glp.domain.entities.EtudiantProfil;
import ipint15.glp.domain.entities.Publication;

public class Conversion {
	
	
	public EtudiantDTO MappingEtudiantProfil(Etudiant e, EtudiantProfil ep){
		
		// Conversion de l'étudiantProfil en EtudiantProfilDTO et de l'étudiant en EtudiantDTO
		EtudiantProfilDTO epDTO = ep.toEtudiantProfilDTO();
		EtudiantDTO eDTO = e.toEtudiantDTO();
		
		// Mapping de l'étudiantDTO avec son profilDTO
		eDTO.setProfil(epDTO);
		epDTO.setEtudiant(eDTO);
		return eDTO;
	}
	
	public CompetenceDTO MappingProfilCompetence (EtudiantProfil ep, Competence c){
		
		//Conversion de l'étudiant en EtudiantProfilDTO et de la  Competence CompetenceDTO
		EtudiantProfilDTO epDto = ep.toEtudiantProfilDTO();
		CompetenceDTO cDTO = c.toCompetenceDTO();
		
		// Mapping du profil avec sa compétence
		epDto.getMesCompetences().add(cDTO);
		cDTO.setProfil(epDto);
		return cDTO;
	}
	
	public PublicationDTO MappingProfilPublication (EtudiantProfil ep, Publication p){
		
		//Conversion de l'étudiant en EtudiantProfilDTO et de la  Competence CompetenceDTO
		EtudiantProfilDTO epDto = ep.toEtudiantProfilDTO();
		PublicationDTO cDTO = p.toPublicationDTO();
		
		// Mapping du profil avec sa compétence
		epDto.getMesPublications().add(cDTO);
		cDTO.setProfil(epDto);
		return cDTO;
	}
	
}
