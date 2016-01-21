package ipint15.glp.domain.util;

import java.util.ArrayList;
import java.util.List;

import ipint15.glp.api.dto.CompetenceDTO;
import ipint15.glp.api.dto.EcoleDTO;
import ipint15.glp.api.dto.EtudiantDTO;
import ipint15.glp.api.dto.EtudiantProfilDTO;
import ipint15.glp.api.dto.PublicationDTO;
import ipint15.glp.api.dto.ExperienceDTO;
import ipint15.glp.api.dto.GroupeDTO;
import ipint15.glp.api.dto.HobbieDTO;
import ipint15.glp.api.dto.ModerateurDTO;
import ipint15.glp.domain.entities.Competence;
import ipint15.glp.domain.entities.Ecole;
import ipint15.glp.domain.entities.Etudiant;
import ipint15.glp.domain.entities.EtudiantProfil;
import ipint15.glp.domain.entities.Publication;
import ipint15.glp.domain.entities.Experience;
import ipint15.glp.domain.entities.Groupe;
import ipint15.glp.domain.entities.Hobbie;
import ipint15.glp.domain.entities.Moderateur;
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
		epDto.setEtudiant(MappingEtudiantProfil(ep.getEtudiant(),ep));
		CompetenceDTO cDTO = c.toCompetenceDTO();
		
		// Mapping du profil avec sa compétence
		epDto.getMesCompetences().add(cDTO);
		cDTO.setProfil(epDto);
		return cDTO;
	}
	
	public PublicationDTO MappingProfilPublication (EtudiantProfil ep, Publication p){
		
		//Conversion de l'étudiant en EtudiantProfilDTO et de la  Competence CompetenceDTO
		EtudiantProfilDTO epDto = ep.toEtudiantProfilDTO();
		epDto.setEtudiant(MappingEtudiantProfil(ep.getEtudiant(),ep));
		PublicationDTO cDTO = p.toPublicationDTO();
		
		// Mapping du profil avec sa compétence
		epDto.getMesPublications().add(cDTO);
		cDTO.setProfil(epDto);
		return cDTO;
	}
	public ExperienceDTO MappingProfilExperience (EtudiantProfil ep, Experience exp){
		
		//Conversion de l'étudiant en EtudiantProfilDTO et de la  Competence CompetenceDTO
		EtudiantProfilDTO epDto = ep.toEtudiantProfilDTO();
		epDto.setEtudiant(MappingEtudiantProfil(ep.getEtudiant(),ep));
		ExperienceDTO experienceDTO = exp.toExperienceDTO();
		
		// Mapping du profil avec sa compétence
		epDto.getMesExperiences().add(experienceDTO);
		experienceDTO.setProfil(epDto);
		return experienceDTO;
	}
	
	public HobbieDTO MappingProfilHobbie (EtudiantProfil ep, Hobbie c){
		
		//Conversion de l'étudiant en EtudiantProfilDTO et de la  Competence CompetenceDTO
		EtudiantProfilDTO epDto = ep.toEtudiantProfilDTO();
		epDto.setEtudiant(MappingEtudiantProfil(ep.getEtudiant(),ep));
		HobbieDTO hDTO = c.toHobbieDTO();
		
		// Mapping du profil avec sa compétence
		epDto.getMesHobbies().add(hDTO);
		hDTO.setProfil(epDto);
		return hDTO;
	}
	
	public EcoleDTO MappingProfilEcole (EtudiantProfil ep, Ecole c){
		
		//Conversion de l'étudiant en EtudiantProfilDTO et de la  Competence CompetenceDTO
		EtudiantProfilDTO epDto = ep.toEtudiantProfilDTO();
		epDto.setEtudiant(MappingEtudiantProfil(ep.getEtudiant(),ep));
		EcoleDTO ecoleDTO = c.toEcoleDTO();
		
		// Mapping du profil avec sa compétence
		epDto.getMesEcoles().add(ecoleDTO);
		ecoleDTO.setProfil(epDto);
		return ecoleDTO;
	}
	
	public GroupeDTO MappingEtudiantGroupe (Etudiant e, Groupe g){
		
		EtudiantDTO eDto = e.toEtudiantDTO();
		GroupeDTO gDTO = g.toGroupeDTO();
		
		// Mapping du profil avec sa compétence
		eDto.setGroupe(gDTO);
		gDTO.getEtudiants().add(eDto);
		return gDTO;
	}
	
	public GroupeDTO MappingModerateurGroupe (Moderateur m, Groupe g){
		
		ModerateurDTO mDto = m.toModerateurDTO();
		GroupeDTO gDTO = g.toGroupeDTO();
		
		// Mapping du profil avec sa compétence
		mDto.getGroupes().add(gDTO);
		gDTO.getModerateurs().add(mDto);
		return gDTO;
	}
	
	public ModerateurDTO MappingGroupeModerateur (Moderateur m, Groupe g){
		
		ModerateurDTO mDTO = m.toModerateurDTO();
		GroupeDTO gDTO = g.toGroupeDTO();
		
		// Mapping du profil avec sa compétence
		mDTO.getGroupes().add(gDTO);
		gDTO.getModerateurs().add(mDTO);
		return mDTO;
	}	
	public ModerateurDTO MappingGroupeModerateur (Moderateur m, List<Groupe> gList){
		
		ModerateurDTO mDTO = m.toModerateurDTO();
		List<GroupeDTO> gDTO = new ArrayList<GroupeDTO>();
		
		for(Groupe g : gList) {
			gDTO.add(g.toGroupeDTO());
		}
		
		
		// Mapping du profil avec sa compétence
		mDTO.setGroupes(gDTO);
		
		for(GroupeDTO g : gDTO) {
			g.getModerateurs().add(mDTO);
		}
		
		return mDTO;
	}
	
}
