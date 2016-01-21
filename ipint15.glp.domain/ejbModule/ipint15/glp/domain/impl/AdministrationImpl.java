package ipint15.glp.domain.impl;


import java.util.ArrayList;
import java.util.List;

import javax.ejb.Stateless;
import javax.persistence.EntityManager;

import javax.persistence.PersistenceContext;
import javax.persistence.Query;

import ipint15.glp.api.dto.AdminDTO;
import ipint15.glp.api.dto.GroupeDTO;
import ipint15.glp.api.dto.ModerateurDTO;
import ipint15.glp.api.remote.AdministrationRemote;
import ipint15.glp.domain.entities.Admin;
import ipint15.glp.domain.entities.Groupe;
import ipint15.glp.domain.entities.Moderateur;
import ipint15.glp.domain.util.Conversion;

@Stateless
public class AdministrationImpl implements AdministrationRemote {
	Conversion ce = new Conversion();
	@PersistenceContext
	EntityManager em;

	private Moderateur getModerateurById(int id) {
		Query q = em.createQuery("select o from Moderateur o WHERE o.id = :id");
		q.setParameter("id", id);
		Moderateur m = (Moderateur) q.getSingleResult();
		return m;
	}

	private Groupe getGroupeById(int id) {
		Query q = em.createQuery("select o from Groupe o WHERE o.id = :id");
		q.setParameter("id", id);
		Groupe g = (Groupe) q.getSingleResult();
		return g;
	}

	@Override
	public ModerateurDTO createModerateur(String prenom, String nom, String email, String password) {
		Moderateur m = new Moderateur();
		m.setEmail(email);
		m.setNom(nom);
		m.setPassword(password);
		m.setPrenom(prenom);

		em.persist(m);

		ModerateurDTO mDTO = m.toModerateurDTO();
		return mDTO;
	}
	@Override
	public AdminDTO createAdmin(String email, String mdp) {
		Admin a = new Admin();
		a.setEmail(email);
		a.setPassword(mdp);

		em.persist(a);

		AdminDTO aDTO = a.toAdminDTO();
		return aDTO;
	}

	@Override
	public String generatePassword(int length) {
		String chars = "abcdefghijklmnopqrstuvwxyz1234567890"; 
		StringBuffer pass = new StringBuffer();
		for(int x=0;x<length;x++)   {
			int i = (int)Math.floor(Math.random() * (chars.length() -1));
			pass.append(chars.charAt(i));
		}
		return pass.toString();
	}
	@Override
	public ModerateurDTO getModerateurDTOById(int id) {
		Query q = em.createQuery("select o from Moderateur o WHERE o.id = :id");
		q.setParameter("id", id);
		Moderateur m = (Moderateur) q.getSingleResult();
		ModerateurDTO mDTO = m.toModerateurDTO();
		return mDTO;
	}



	@Override
	public ModerateurDTO addGroupetoModo(int id, GroupeDTO groupe) {
		Moderateur m = getModerateurById(id);
		Groupe g = getGroupeById(groupe.getId());
		m.getGroupes().add(g);
		g.getModerateurs().add(m);

		em.merge(m);
		em.merge(g);

		return  ce.MappingGroupeModerateur(m, g);

	}

	@Override
	public List<ModerateurDTO> getAllModerateur() {

		List<Moderateur> mList = em.createQuery("select o from Moderateur o", Moderateur.class).getResultList();
		List<ModerateurDTO> mDTOList = new ArrayList<ModerateurDTO>();
		for(Moderateur m : mList) {
			if (!m.getGroupes().isEmpty()) {
				mDTOList.add(ce.MappingGroupeModerateur(m, m.getGroupes()));
				
			}else {
				mDTOList.add(m.toModerateurDTO());
			}
		}
		return mDTOList;

	}
}