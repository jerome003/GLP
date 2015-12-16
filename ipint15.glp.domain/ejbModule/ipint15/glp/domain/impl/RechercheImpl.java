package ipint15.glp.domain.impl;

import java.util.ArrayList;
import java.util.List;

import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

import ipint15.glp.api.dto.EtudiantDTO;
import ipint15.glp.api.remote.RechercheRemote;
import ipint15.glp.domain.entities.Etudiant;
import ipint15.glp.domain.entities.EtudiantProfil;
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
					System.out.println(eDTO);
					if (!psDTO.contains(eDTO))
							psDTO.add(eDTO);
				}
			}
		}
		return psDTO;
	}

}
