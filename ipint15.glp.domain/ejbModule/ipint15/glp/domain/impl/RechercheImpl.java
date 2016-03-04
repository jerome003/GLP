package ipint15.glp.domain.impl;

import java.util.ArrayList;
import java.util.List;

import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

import ipint15.glp.api.dto.AncienEtudiantDTO;
import ipint15.glp.api.dto.EnseignantDTO;
import ipint15.glp.api.dto.EtudiantDTO;
import ipint15.glp.api.dto.GroupeDTO;
import ipint15.glp.api.remote.RechercheRemote;
import ipint15.glp.domain.entities.AncienEtudiant;
import ipint15.glp.domain.entities.Enseignant;
import ipint15.glp.domain.entities.Etudiant;
import ipint15.glp.domain.entities.EtudiantProfil;
import ipint15.glp.domain.entities.Groupe;
import ipint15.glp.domain.util.Conversion;

@Stateless
public class RechercheImpl implements RechercheRemote {

	Conversion ce = new Conversion();
	@PersistenceContext
	EntityManager em;

	@Override
	public List<AncienEtudiantDTO> rechercherAncienEtudiant(String recherche) {
		List<AncienEtudiant> ps = em.createQuery("select o from AncienEtudiant o").getResultList();
		List<AncienEtudiantDTO> psDTO = new ArrayList<AncienEtudiantDTO>();
		String[] recherches = recherche.split(" ");

		for (AncienEtudiant e : ps) {
			for (int i = 0; i < recherches.length; i++) {

				if (e.getNom().toLowerCase().contains(recherches[i].toLowerCase())
						|| e.getPrenom().toLowerCase().contains(recherches[i].toLowerCase())) {
					EtudiantProfil ep = e.getProfil();
					AncienEtudiantDTO eDTO = ce.MappingEtudiantProfil(e, ep);
					if (!psDTO.contains(eDTO))
							psDTO.add(eDTO);
				}
			}
		}
		return psDTO;
	}
	
	@Override
	public List<GroupeDTO> rechercherGroupe(String recherche) {
		List<Groupe> ps = em.createQuery("select o from Groupe o").getResultList();
		List<GroupeDTO> psDTO = new ArrayList<GroupeDTO>();
		String[] recherches = recherche.split(" ");

		for (Groupe g : ps) {
			for (int i = 0; i < recherches.length; i++) {

				if (g.getName().toLowerCase().contains(recherches[i].toLowerCase())) {
					GroupeDTO gDTO = g.toGroupeDTO();
					if (!psDTO.contains(gDTO))
							psDTO.add(gDTO);
				}
			}
		}
		return psDTO;
	}

	@Override
	public List<EtudiantDTO> rechercherEtudiant(String recherche) {
		List<Etudiant> etu = em.createQuery("select o from Etudiant o").getResultList();
		List<EtudiantDTO> etuDTO = new ArrayList<EtudiantDTO>();
		String[] recherches = recherche.split(" ");

		for (Etudiant e : etu) {
			for (int i = 0; i < recherches.length; i++) {

				if (e.getNom().toLowerCase().contains(recherches[i].toLowerCase())
						|| e.getPrenom().toLowerCase().contains(recherches[i].toLowerCase())) {
					EtudiantDTO eDTO = e.toEtudiantDTO();
					if (!etuDTO.contains(eDTO))
							etuDTO.add(eDTO);
				}
			}
		}
		return etuDTO;
	}

	@Override
	public List<EnseignantDTO> rechercherEnseignant(String recherche) {
		List<Enseignant> enseign = em.createQuery("select o from Enseignant o").getResultList();
		List<EnseignantDTO> ensDTO = new ArrayList<EnseignantDTO>();
		String[] recherches = recherche.split(" ");

		for (Enseignant e : enseign) {
			for (int i = 0; i < recherches.length; i++) {

				if (e.getNom().toLowerCase().contains(recherches[i].toLowerCase())
						|| e.getPrenom().toLowerCase().contains(recherches[i].toLowerCase())) {
					EnseignantDTO eDTO = e.toEnseignantDTO();
					if (!ensDTO.contains(eDTO))
							ensDTO.add(eDTO);
				}
			}
		}
		return ensDTO;
	}

}
