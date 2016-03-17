package ipint15.glp.domain.util;

import java.util.ArrayList;
import java.util.List;

import ipint15.glp.api.dto.CompetenceDTO;
import ipint15.glp.api.dto.EcoleDTO;
import ipint15.glp.api.dto.EnseignantDTO;
import ipint15.glp.api.dto.EtudiantDTO;
import ipint15.glp.api.dto.AncienEtudiantDTO;
import ipint15.glp.api.dto.EtudiantProfilDTO;
import ipint15.glp.api.dto.PublicationDTO;
import ipint15.glp.api.dto.ExperienceDTO;
import ipint15.glp.api.dto.GroupeDTO;
import ipint15.glp.api.dto.HobbieDTO;
import ipint15.glp.api.dto.ModerateurDTO;
import ipint15.glp.domain.entities.Competence;
import ipint15.glp.domain.entities.Ecole;
import ipint15.glp.domain.entities.Enseignant;
import ipint15.glp.domain.entities.Etudiant;
import ipint15.glp.domain.entities.AncienEtudiant;
import ipint15.glp.domain.entities.EtudiantProfil;
import ipint15.glp.domain.entities.Publication;
import ipint15.glp.domain.entities.Experience;
import ipint15.glp.domain.entities.Groupe;
import ipint15.glp.domain.entities.Hobbie;
import ipint15.glp.domain.entities.Moderateur;

public class Conversion {

	public AncienEtudiantDTO MappingEtudiantProfilGroupe(AncienEtudiant e, EtudiantProfil ep, Groupe g) {

		// Conversion de l'étudiantProfil en EtudiantProfilDTO et de l'étudiant
		// en EtudiantDTO
		GroupeDTO groupe = g.toGroupeDTO();
		for (Moderateur m : g.getModerateurs()) {
			groupe.getModerateurs().add(MappingGroupeModerateur(m, g));
		}
		EtudiantProfilDTO epDTO = ep.toEtudiantProfilDTO();
		AncienEtudiantDTO eDTO = e.toEtudiantDTO();

		// Mapping de l'étudiantDTO avec son profilDTO
		eDTO.setGroupe(groupe);
		eDTO.setProfil(epDTO);
		epDTO.setEtudiant(eDTO);
		return eDTO;
	}

	public AncienEtudiantDTO MappingEtudiantProfil(AncienEtudiant e, EtudiantProfil ep) {

		// Conversion de l'étudiantProfil en EtudiantProfilDTO et de l'étudiant
		// en EtudiantDTO
		EtudiantProfilDTO epDTO = ep.toEtudiantProfilDTO();
		AncienEtudiantDTO eDTO = e.toEtudiantDTO();

		// Mapping de l'étudiantDTO avec son profilDTO
		eDTO.setProfil(epDTO);
		epDTO.setEtudiant(eDTO);
		return eDTO;
	}

	public CompetenceDTO MappingProfilCompetence(EtudiantProfil ep, Competence c) {

		// Conversion de l'étudiant en EtudiantProfilDTO et de la Competence
		// CompetenceDTO
		EtudiantProfilDTO epDto = ep.toEtudiantProfilDTO();
		epDto.setEtudiant(MappingEtudiantProfil(ep.getEtudiant(), ep));
		CompetenceDTO cDTO = c.toCompetenceDTO();

		// Mapping du profil avec sa compétence
		epDto.getMesCompetences().add(cDTO);
		cDTO.setProfil(epDto);
		return cDTO;
	}

	public PublicationDTO MappingProfilPublication(EtudiantProfil ep, Publication p) {

		// Conversion de l'étudiant en EtudiantProfilDTO et de la Competence
		// CompetenceDTO
		EtudiantProfilDTO epDto = ep.toEtudiantProfilDTO();
		System.out.println(epDto);
		epDto.setEtudiant(MappingEtudiantProfil(ep.getEtudiant(), ep));
		PublicationDTO cDTO = p.toPublicationDTO();
		if (p.getGroupe() == null) {
			cDTO.setGroupeDTO(null);
			System.out.println("No group");
		} else {
			cDTO.setGroupeDTO(p.getGroupe().toGroupeDTO());
			System.out.println("Group");
		}
		cDTO.setPublicationPublic(p.isPublic());
		cDTO.setPostByAnim(p.isPostByAnim());
		// Mapping du profil avec sa compétence
		epDto.getMesPublications().add(cDTO);
		cDTO.setProfil(epDto);
		return cDTO;
	}

	public PublicationDTO MappingEtudiantPublication(Etudiant e, Publication p) {

		// Conversion de l'étudiant de la publication
		
		EtudiantDTO eDto = e.toEtudiantDTO();
		PublicationDTO pDTO = p.toPublicationDTO();
		if (p.getGroupe() == null) {
			pDTO.setGroupeDTO(null);
			System.out.println("No group");
		} else {
			pDTO.setGroupeDTO(p.getGroupe().toGroupeDTO());
			System.out.println("Group");
		}
		pDTO.setPublicationPublic(p.isPublic());
		// Mapping de l'etudiant avec sa publication
		eDto.getMesPublications().add(pDTO);
		pDTO.setEtudiant(eDto);
		return pDTO;
	}
	
	public PublicationDTO MappingEnseignantPublication(Enseignant e, Publication p) {

		// Conversion de l'enseignant de la publication
		
		EnseignantDTO eDto = e.toEnseignantDTO();
		PublicationDTO pDTO = p.toPublicationDTO();
		System.out.println(p);
		if (p.getGroupe() == null) {
			pDTO.setGroupeDTO(null);
			System.out.println("No group");
		} else {
			pDTO.setGroupeDTO(p.getGroupe().toGroupeDTO());
			System.out.println("Group");
		}
		pDTO.setPublicationPublic(p.isPublic());
		// Mapping de l'enseignant avec sa publication
		eDto.getMesPublications().add(pDTO);
		pDTO.setEnseignant(eDto);
		return pDTO;
	}
	
	
	public ExperienceDTO MappingProfilExperience(EtudiantProfil ep, Experience exp) {

		// Conversion de l'étudiant en EtudiantProfilDTO et de la Competence
		// CompetenceDTO
		EtudiantProfilDTO epDto = ep.toEtudiantProfilDTO();
		epDto.setEtudiant(MappingEtudiantProfil(ep.getEtudiant(), ep));
		ExperienceDTO experienceDTO = exp.toExperienceDTO();

		// Mapping du profil avec sa compétence
		epDto.getMesExperiences().add(experienceDTO);
		experienceDTO.setProfil(epDto);
		return experienceDTO;
	}

	public HobbieDTO MappingProfilHobbie(EtudiantProfil ep, Hobbie c) {

		// Conversion de l'étudiant en EtudiantProfilDTO et de la Competence
		// CompetenceDTO
		EtudiantProfilDTO epDto = ep.toEtudiantProfilDTO();
		epDto.setEtudiant(MappingEtudiantProfil(ep.getEtudiant(), ep));
		HobbieDTO hDTO = c.toHobbieDTO();

		// Mapping du profil avec sa compétence
		epDto.getMesHobbies().add(hDTO);
		hDTO.setProfil(epDto);
		return hDTO;
	}

	public EcoleDTO MappingProfilEcole(EtudiantProfil ep, Ecole c) {

		// Conversion de l'étudiant en EtudiantProfilDTO et de la Competence
		// CompetenceDTO
		EtudiantProfilDTO epDto = ep.toEtudiantProfilDTO();
		epDto.setEtudiant(MappingEtudiantProfil(ep.getEtudiant(), ep));
		EcoleDTO ecoleDTO = c.toEcoleDTO();

		// Mapping du profil avec sa compétence
		epDto.getMesEcoles().add(ecoleDTO);
		ecoleDTO.setProfil(epDto);
		return ecoleDTO;
	}

	public GroupeDTO MappingEtudiantGroupe(AncienEtudiant e, Groupe g) {

		AncienEtudiantDTO eDto = e.toEtudiantDTO();
		GroupeDTO gDTO = g.toGroupeDTO();

		// Mapping du profil avec sa compétence
		eDto.setGroupe(gDTO);
		gDTO.getAncienEtudiants().add(eDto);
		return gDTO;
	}

	public GroupeDTO MappingEtudiantGroupe(Etudiant e, Groupe g) {

		EtudiantDTO eDto = e.toEtudiantDTO();
		GroupeDTO gDTO = g.toGroupeDTO();

		// Mapping du profil avec sa compétence
		eDto.setGroupe(gDTO);
		gDTO.getEtudiants().add(eDto);
		return gDTO;
	}
	
	/**
	 * 
	 * @param e
	 * @param lesGroupes
	 * @return
	 */
	
	public List<GroupeDTO> MappingEtudiantLesGroupes(AncienEtudiant e, List<Groupe> lesGroupes) {
		List<GroupeDTO> lesGroupesDTO = new ArrayList<GroupeDTO>();
		AncienEtudiantDTO eDto = e.toEtudiantDTO();
		
		for(Groupe g : lesGroupes){
		lesGroupesDTO.add(g.toGroupeDTO());
		}
		// Mapping du profil avec sa compétence
		eDto.setLesGroupes(lesGroupesDTO);
		
		for(GroupeDTO g : lesGroupesDTO){
		g.getAncienEtudiants().add(eDto);
		}
		return lesGroupesDTO;
	}
	
	public List<GroupeDTO> MappingEtuLesGroupes(Etudiant e, List<Groupe> lesGroupes) {
		List<GroupeDTO> lesGroupesDTO = new ArrayList<GroupeDTO>();
		EtudiantDTO eDto = e.toEtudiantDTO();
		
		for(Groupe g : lesGroupes){
		lesGroupesDTO.add(g.toGroupeDTO());
		}
		// Mapping du profil avec sa compétence
		eDto.setListeGroupes(lesGroupesDTO);
		
		for(GroupeDTO g : lesGroupesDTO){
		g.getEtudiants().add(eDto);
		}
		return lesGroupesDTO;
	}
	
	public List<GroupeDTO> MappingEnseignantLesGroupes(Enseignant e, List<Groupe> lesGroupes) {
		List<GroupeDTO> lesGroupesDTO = new ArrayList<GroupeDTO>();
		EnseignantDTO eDto = e.toEnseignantDTO();
		
		for(Groupe g : lesGroupes){
		lesGroupesDTO.add(g.toGroupeDTO());
		}
		// Mapping du profil avec sa compétence
		eDto.setListeGroupes(lesGroupesDTO);
		
		for(GroupeDTO g : lesGroupesDTO){
			g.getEnseignants().add(eDto);
		}
		return lesGroupesDTO;
	}


	public GroupeDTO MappingModerateurGroupe(Moderateur m, Groupe g) {

		ModerateurDTO mDto = m.toModerateurDTO();
		GroupeDTO gDTO = g.toGroupeDTO();

		// Mapping du profil avec sa compétence
		mDto.getGroupes().add(gDTO);
		gDTO.getModerateurs().add(mDto);
		return gDTO;
	}

	public ModerateurDTO MappingGroupeModerateur(Moderateur m, Groupe g) {

		ModerateurDTO mDTO = m.toModerateurDTO();
		GroupeDTO gDTO = g.toGroupeDTO();

		// Mapping du profil avec sa compétence
		mDTO.getGroupes().add(gDTO);
		gDTO.getModerateurs().add(mDTO);
		return mDTO;
	}

	public ModerateurDTO MappingGroupeModerateur(Moderateur m, List<Groupe> gList) {

		ModerateurDTO mDTO = m.toModerateurDTO();
		List<GroupeDTO> gDTO = new ArrayList<GroupeDTO>();

		for (Groupe g : gList) {
			gDTO.add(g.toGroupeDTO());
		}

		// Mapping du profil avec sa compétence
		mDTO.setGroupes(gDTO);

		for (GroupeDTO g : gDTO) {
			g.getModerateurs().add(mDTO);
		}

		return mDTO;
	}

	public EnseignantDTO MappingGroupeAnimateur(Enseignant e, List<Groupe> groupes) {
		EnseignantDTO eDTO = e.toEnseignantDTO();
		List<GroupeDTO> gDTO = new ArrayList<GroupeDTO>();

		for (Groupe g : groupes) {
			gDTO.add(g.toGroupeDTO());
		}

		// Mapping du profil avec sa compétence
		eDTO.setListeGroupesAnime(gDTO);

		for (GroupeDTO g : gDTO) {
			g.getAnimateurs().add(eDTO);
		}

		return eDTO;
	}
	
	public EnseignantDTO MappingGroupeAnimateur(Enseignant e, Groupe g) {

		EnseignantDTO eDTO = e.toEnseignantDTO();
		GroupeDTO gDTO = g.toGroupeDTO();

		// Mapping du profil avec sa compétence
		eDTO.getListeGroupesAnime().add(gDTO);
		gDTO.getAnimateurs().add(eDTO);
		return eDTO;
	}

}
