package ipint15.glp.domain.impl;

import java.util.ArrayList;
import java.util.List;

import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
import javax.persistence.PersistenceContext;

import ipint15.glp.api.dto.EtudiantDTO;
import ipint15.glp.api.dto.GroupeDTO;
import ipint15.glp.api.remote.RechercheRemote;
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
	public List<EtudiantDTO> rechercherEtudiant(String recherche) {
		List<Etudiant> ps = em.createQuery("select o from Etudiant o").getResultList();
		List<EtudiantDTO> psDTO = new ArrayList<EtudiantDTO>();
		String[] recherches = recherche.split(" ");

		for (Etudiant e : ps) {
			for (int i = 0; i < recherches.length; i++) {

				if (e.getNom().toLowerCase().contains(recherches[i].toLowerCase())
						|| e.getPrenom().toLowerCase().contains(recherches[i].toLowerCase())) {
					EtudiantProfil ep = e.getProfil();
					EtudiantDTO eDTO = ce.MappingEtudiantProfil(e, ep);
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

}
