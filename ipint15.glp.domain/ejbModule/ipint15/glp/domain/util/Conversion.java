package ipint15.glp.domain.util;

import ipint15.glp.api.dto.CompetenceDTO;
import ipint15.glp.api.dto.EcoleDTO;
import ipint15.glp.api.dto.EtudiantDTO;
import ipint15.glp.api.dto.EtudiantProfilDTO;
import ipint15.glp.api.dto.ExperienceDTO;
import ipint15.glp.api.dto.HobbieDTO;
import ipint15.glp.domain.entities.Competence;
import ipint15.glp.domain.entities.Ecole;
import ipint15.glp.domain.entities.Etudiant;
import ipint15.glp.domain.entities.EtudiantProfil;
import ipint15.glp.domain.entities.Experience;
import ipint15.glp.domain.entities.Hobbie;

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
	
	public ExperienceDTO MappingProfilExperience (EtudiantProfil ep, Experience exp){
		
		//Conversion de l'étudiant en EtudiantProfilDTO et de la  Competence CompetenceDTO
		EtudiantProfilDTO epDto = ep.toEtudiantProfilDTO();
		ExperienceDTO experienceDTO = exp.toExperienceDTO();
		
		// Mapping du profil avec sa compétence
		epDto.getMesExperiences().add(experienceDTO);
		experienceDTO.setProfil(epDto);
		return experienceDTO;
	}
	
	public HobbieDTO MappingProfilHobbie (EtudiantProfil ep, Hobbie c){
		
		//Conversion de l'étudiant en EtudiantProfilDTO et de la  Competence CompetenceDTO
		EtudiantProfilDTO epDto = ep.toEtudiantProfilDTO();
		HobbieDTO hDTO = c.toHobbieDTO();
		
		// Mapping du profil avec sa compétence
		epDto.getMesHobbies().add(hDTO);
		hDTO.setProfil(epDto);
		return hDTO;
	}
	
	public EcoleDTO MappingProfilEcole (EtudiantProfil ep, Ecole c){
		
		//Conversion de l'étudiant en EtudiantProfilDTO et de la  Competence CompetenceDTO
		EtudiantProfilDTO epDto = ep.toEtudiantProfilDTO();
		EcoleDTO ecoleDTO = c.toEcoleDTO();
		
		// Mapping du profil avec sa compétence
		epDto.getMesEcoles().add(ecoleDTO);
		ecoleDTO.setProfil(epDto);
		return ecoleDTO;
	}
	
	
}
