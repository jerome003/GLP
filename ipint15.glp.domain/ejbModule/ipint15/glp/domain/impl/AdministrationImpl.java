package ipint15.glp.domain.impl;


import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.Properties;

import javax.ejb.Stateless;
import javax.mail.PasswordAuthentication;
import javax.mail.Session;
import javax.persistence.EntityManager;
import javax.persistence.NoResultException;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;

import javax.mail.Message;
import javax.mail.MessagingException;

import javax.mail.Transport;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;

import ipint15.glp.api.dto.AdminDTO;
import ipint15.glp.api.dto.AncienEtudiantDTO;
import ipint15.glp.api.dto.EnseignantDTO;
import ipint15.glp.api.dto.GroupeDTO;
import ipint15.glp.api.dto.ModerateurDTO;
import ipint15.glp.api.remote.AdministrationRemote;
import ipint15.glp.domain.entities.Admin;
import ipint15.glp.domain.entities.AncienEtudiant;
import ipint15.glp.domain.entities.Enseignant;
import ipint15.glp.domain.entities.EtudiantProfil;
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

	private Enseignant getEnseignantById(int id) {
		Query q = em.createQuery("select o from Enseignant o WHERE o.id = :id");
		q.setParameter("id", id);
		Enseignant e = (Enseignant) q.getSingleResult();
		return e;
	}
	
	private Admin getAdminByMail(String mail) {

		Query q = em.createQuery("select o from Admin o WHERE o.email = :email");
		q.setParameter("email", mail);
		Admin a = (Admin) q.getSingleResult();

		return a;
	}

	private Moderateur getModerateurByMail(String mail) {

		Query q = em.createQuery("select o from Moderateur o WHERE o.email = :email");
		q.setParameter("email", mail);
		Moderateur m = (Moderateur) q.getSingleResult();

		return m;
	}

	@Override
	public ModerateurDTO createModerateur(String prenom, String nom, String email, String password) {
		Moderateur m = new Moderateur();
		m.setEmail(email);
		m.setNom(nom);
		m.setPassword(password);

		m.setPrenom(prenom);

		System.out.println("Create Modo " + nom + " : " + password);

		em.persist(m);

		ModerateurDTO mDTO = m.toModerateurDTO();
		return mDTO;
	}

	@Override
	public void sendMailModoAssign(ModerateurDTO modo, GroupeDTO groupe) {
		final String username = "maxime.gidon";
		final String password = "Miage2016";

		Properties props = new Properties();
		props.put("mail.smtp.host", "smtps.univ-lille1.fr");
		props.put("mail.smtp.starttls.enable", "true");
		props.put("mail.smtp.socketFactory.port", "587");
		props.put("mail.smtp.socketFactory.class",
				"javax.net.ssl.SSLSocketFactory");
		props.put("mail.smtp.auth", "true");
		props.put("mail.smtp.port", "587");

		Session session = Session.getInstance(props,
				new javax.mail.Authenticator() {
			protected PasswordAuthentication getPasswordAuthentication() {
				return new PasswordAuthentication(username, password);
			}
		});

		try {

			Message message = new MimeMessage(session);
			message.setFrom(new InternetAddress(username + "@etudiant.univ-lille1.fr"));
			message.setRecipients(Message.RecipientType.TO,
					InternetAddress.parse(modo.getEmail()));
			message.setSubject("[Lille1] Modération du groupe " + groupe.getName());
			message.setText("Bonjour, "
					+ "\n\nVous venez d'être désigné modérateur pour le groupe " + groupe.getName() +
					" : " + groupe.getDescription()+ " \nVotre mot de passe pour vous connecter est : " +
					modo.getPassword() +". \n\n A bientot sur le réseau d'ancien de Lille 1 !");

			Transport.send(message);


		} catch (MessagingException e) {
			throw new RuntimeException(e);
		}
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
	public EnseignantDTO getEnseignantDTOById(int id) {
		Query q = em.createQuery("select o from Enseignant o WHERE o.id = :id");
		q.setParameter("id", id);
		Enseignant e = (Enseignant) q.getSingleResult();
		EnseignantDTO eDTO = e.toEnseignantDTO();
		return eDTO;
	}

	@Override
	public boolean isMailExistsForAdmin(String mail) {
		Query q = em.createQuery("select o from Admin o WHERE o.email = :email");
		q.setParameter("email", mail);
		Admin a;
		try {
			a = (Admin) q.getSingleResult();
		} catch (NoResultException e1) {
			return false;
		}
		return true;
	}

	@Override
	public boolean isPasswordIsGoodForAdmin(String mail, String password) {
		Query q = em.createQuery("select o from Admin o WHERE o.email = :email and o.password = :password ");
		q.setParameter("email", mail);
		q.setParameter("password", password);
		Admin a;
		try {
			a = (Admin) q.getSingleResult();
		} catch (NoResultException e1) {
			return false;
		}
		return true;
	}

	@Override
	public boolean connexionAdmin(String email, String password) {
		Admin a = getAdminByMail(email);
		if (a != null && (a.getPassword().equals(password))) {
			return true;
		}
		return false;
	}

	@Override
	public boolean connexionModerateur(String email, String password) {
		Moderateur m = getModerateurByMail(email);
		if (m != null && (m.getPassword().equals(password))) {
			return true;
		}
		return false;
	}


	public boolean isThereAnAdmin() {
		Query q = em.createQuery("select o from Admin o  ");

		Admin a ;
		try {
			a = (Admin) q.getSingleResult();
		} catch (NoResultException e1) {
			return false;
		}
		return true;
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

	@Override
	public AdminDTO getAdmin(String email) {
		Admin a = getAdminByMail(email);

		if (a != null) {
			AdminDTO aDTO = a.toAdminDTO();
			return aDTO;
		}

		// a remplacer par le renvoie d'une exception lorsqu'aucun email ne
		// correspond à celui en parametre
		return null;
	}

	@Override
	public boolean isMailExistsForModerateur(String mail) {
		Query q = em.createQuery("select o from Moderateur o WHERE o.email = :email");
		q.setParameter("email", mail);
		try {
			Moderateur m = (Moderateur) q.getSingleResult();
		} catch (NoResultException e1) {
			return false;
		}
		return true;
	}

	@Override
	public boolean isPasswordIsGoodForModerateur(String mail, String password) {
		Query q = em.createQuery("select o from Moderateur o WHERE o.email = :email and o.password = :password ");
		q.setParameter("email", mail);
		q.setParameter("password", password);
		try {
			Moderateur m = (Moderateur) q.getSingleResult();
		} catch (NoResultException e1) {
			return false;
		}
		return true;
	}

	@Override
	public ModerateurDTO getModerateur(String email) {
		Moderateur m = getModerateurByMail(email);

		if (m != null) {
			return ce.MappingGroupeModerateur(m, m.getGroupes());
		}
		return null;
	}

	@Override
	public void sendMailEtudiantOK(AncienEtudiantDTO etu) {
		final String username = "maxime.gidon";
		final String password = "Miage2016";

		Properties props = new Properties();
		props.put("mail.smtp.host", "smtps.univ-lille1.fr");
		props.put("mail.smtp.starttls.enable", "true");
		props.put("mail.smtp.socketFactory.port", "587");
		props.put("mail.smtp.socketFactory.class",
				"javax.net.ssl.SSLSocketFactory");
		props.put("mail.smtp.auth", "true");
		props.put("mail.smtp.port", "587");


		Session session = Session.getInstance(props,
				new javax.mail.Authenticator() {
			protected PasswordAuthentication getPasswordAuthentication() {
				return new PasswordAuthentication(username, password);
			}
		});

		try {

			Message message = new MimeMessage(session);
			message.setFrom(new InternetAddress(username + "@etudiant.univ-lille1.fr"));
			message.setRecipients(Message.RecipientType.TO,
					InternetAddress.parse(etu.getEmail()));
			message.setSubject("[Lille1] Validation de votre inscription");
			message.setText("Bonjour, "
					+ "\n\nVotre inscription vient d'être validé pour le groupe " + etu.getGroupe().getName() +
					" : " + etu.getGroupe().getDescription()+ " \nVotre mot de passe pour vous connecter est : " +
					etu.getPassword() +". \n\nA bientot sur le réseau d'ancien de Lille 1 !");

			Transport.send(message);


		} catch (MessagingException e) {
			throw new RuntimeException(e);
		}

	}

	@Override
	public void sendMailEtudiantKO(AncienEtudiantDTO etu, String motif) {
		final String username = "maxime.gidon";
		final String password = "Miage2016";

		Properties props = new Properties();
		props.put("mail.smtp.host", "smtps.univ-lille1.fr");
		props.put("mail.smtp.starttls.enable", "true");
		props.put("mail.smtp.socketFactory.port", "587");
		props.put("mail.smtp.socketFactory.class",
				"javax.net.ssl.SSLSocketFactory");
		props.put("mail.smtp.auth", "true");
		props.put("mail.smtp.port", "587");


		Session session = Session.getInstance(props,
				new javax.mail.Authenticator() {
			protected PasswordAuthentication getPasswordAuthentication() {
				return new PasswordAuthentication(username, password);
			}
		});

		try {

			Message message = new MimeMessage(session);
			message.setFrom(new InternetAddress(username + "@etudiant.univ-lille1.fr"));
			message.setRecipients(Message.RecipientType.TO,
					InternetAddress.parse(etu.getEmail()));
			message.setSubject("[Lille1] Refus de votre inscription");
			message.setText("Bonjour, "
					+ "\n\nVotre inscription vient d'être refusé pour le groupe " + etu.getGroupe().getName() +
					" : " + etu.getGroupe().getDescription()+ "\n\nMotif : "+ motif + " \n\nVotre compte a été supprimé, vous pouvez me contacter pour plus de détails." +
					". \n\nA bientot sur le réseau d'ancien de Lille 1 !");

			Transport.send(message);


		} catch (MessagingException e) {
			throw new RuntimeException(e);
		}

	}

	@Override
	public List<AncienEtudiantDTO> getEtudiantsNonInscritByIdGroupe(int id){

		Groupe g = getGroupeById(id);
		List<AncienEtudiant> EtudiantList = g.getAncienEtudiants();
		List<AncienEtudiantDTO> EtudiantListDTO = new ArrayList<AncienEtudiantDTO>();


		Iterator<AncienEtudiant> iter = EtudiantList.iterator();

		while (iter.hasNext()) {
			AncienEtudiant e = iter.next();
			if (e.getValidation()){
				iter.remove();
			}
		}

		for (AncienEtudiant e : EtudiantList){
			EtudiantListDTO.add(e.toEtudiantDTO());
		}

		return EtudiantListDTO;


	}
	
	@Override
	public int getEtudiantsNonInscritByIdGroupeSize(int id){

		return getEtudiantsNonInscritByIdGroupe(id).size();


	}

	@Override
	public void sendMailNewEtudiant(AncienEtudiantDTO etu) {
		final String username = "maxime.gidon";
		final String password = "Miage2016";

		Properties props = new Properties();
		props.put("mail.smtp.host", "smtps.univ-lille1.fr");
		props.put("mail.smtp.starttls.enable", "true");
		props.put("mail.smtp.socketFactory.port", "587");
		props.put("mail.smtp.socketFactory.class",
				"javax.net.ssl.SSLSocketFactory");
		props.put("mail.smtp.auth", "true");
		props.put("mail.smtp.port", "587");


		Session session = Session.getInstance(props,
				new javax.mail.Authenticator() {
			protected PasswordAuthentication getPasswordAuthentication() {
				return new PasswordAuthentication(username, password);
			}
		});

		for (ModerateurDTO modo : etu.getGroupe().getModerateurs() ) {

			try {

				Message message = new MimeMessage(session);
				message.setFrom(new InternetAddress(username + "@etudiant.univ-lille1.fr"));
				message.setRecipients(Message.RecipientType.TO,
						InternetAddress.parse(modo.getEmail()));
				message.setSubject("[Lille1] Nouvelle demande d'inscription");
				message.setText("Bonjour, "
						+ "\n\n" + etu.getPrenom() + " " + etu.getNom() + " souhaite rejoindre le groupe " + etu.getGroupe().getName() +
						" : " + etu.getGroupe().getDescription()+ " \nVotre mot de passe pour vous connecter est : " +
						modo.getPassword() +". \n\nA bientot sur le réseau d'ancien de Lille 1 !");

				Transport.send(message);

			} catch (MessagingException e) {
				throw new RuntimeException(e);
			}

		}

	}

	@Override
	public void validationInscription (AncienEtudiantDTO etudiantDTO){
		AncienEtudiant etu = getEtudiantById(etudiantDTO.getId());
		sendMailEtudiantOK(etudiantDTO);
		etu.setValidation(true);
		em.merge(etu);
	}

	private AncienEtudiant getEtudiantById(int id) {
		Query q = em.createQuery("select o from AncienEtudiant o WHERE o.id = :id");
		q.setParameter("id", id);
		AncienEtudiant e = (AncienEtudiant) q.getSingleResult();
		return e;
	}



	@Override
	public void refusInscription (AncienEtudiantDTO etudiantDTO, int idGroupe, String motif){
		sendMailEtudiantKO(etudiantDTO, motif);
		AncienEtudiant etu = getEtudiantById(etudiantDTO.getId());
		Groupe g = getGroupeById(idGroupe);
		g.getAncienEtudiants().remove(etu);
		em.remove(etu);

	}

	@Override
	public boolean removeModerateur(int id) {
		Moderateur m = getModerateurById(id);
		List<Groupe> listeGroupe = m.getGroupes();
		if (listeGroupe.isEmpty()){
			em.remove(m);
			return true;
		}
		return false;

	}

	@Override
	public List<ModerateurDTO> getModerateursDuGroupe(int id) {
		Groupe groupe = getGroupeById(id);
		List<Moderateur> mList = groupe.getModerateurs();
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

	@Override
	public List<EnseignantDTO> getAnimateursDuGroupe(int id) {
		Groupe groupe = getGroupeById(id);
		List<Enseignant> eList = groupe.getAnimateur();
		List<EnseignantDTO> eDTOList = new ArrayList<EnseignantDTO>();
		for(Enseignant e : eList) {
			if (!e.getGroupesAnimes().isEmpty()) {
				eDTOList.add(ce.MappingGroupeAnimateur(e, e.getGroupesAnimes()));

			}else {
				eDTOList.add(e.toEnseignantDTO());
			}
		}
		return eDTOList;

	}

	@Override
	public boolean isModerateurOfGroupe(int idModo, int idGroupe) {
		Groupe g = getGroupeById(idGroupe);
		Moderateur m = getModerateurById(idModo);
		if (g.getModerateurs().contains(m)){
			return true;
		}

		return false;
	}

	@Override
	public boolean removeModerateurFromGroupe(int idModo, int idGroupe) {
		Moderateur m = getModerateurById(idModo);
		Groupe g = getGroupeById(idGroupe);
		List<Moderateur> listeModo = g.getModerateurs();
		List<Groupe> listeGroupe = m.getGroupes();
		if (listeModo.size()>1 && listeModo.contains(m)){
			listeGroupe.remove(g);
			listeModo.remove(m);
			em.merge(m);
			return true;
		}
		return false;
	}
	
	@Override
	public boolean removeAnimateurFromGroupe(int idAnim, int idGroupe) {
		Enseignant e = getEnseignantById(idAnim);
		Groupe g = getGroupeById(idGroupe);
		List<Enseignant> listeAnim = g.getAnimateur();
		List<Groupe> listeGroupe = e.getGroupesAnimes();
		if (listeAnim.contains(e)){
			listeGroupe.remove(g);
			listeAnim.remove(e);
			em.merge(e);
			return true;
		}
		return false;
	}

	@Override
	public void sendMailModoUnassign(ModerateurDTO modo, GroupeDTO groupe) {
		final String username = "maxime.gidon";
		final String password = "Miage2016";

		Properties props = new Properties();
		props.put("mail.smtp.host", "smtps.univ-lille1.fr");
		props.put("mail.smtp.starttls.enable", "true");
		props.put("mail.smtp.socketFactory.port", "587");
		props.put("mail.smtp.socketFactory.class",
				"javax.net.ssl.SSLSocketFactory");
		props.put("mail.smtp.auth", "true");
		props.put("mail.smtp.port", "587");

		Session session = Session.getInstance(props,
				new javax.mail.Authenticator() {
			protected PasswordAuthentication getPasswordAuthentication() {
				return new PasswordAuthentication(username, password);
			}
		});

		try {

			Message message = new MimeMessage(session);
			message.setFrom(new InternetAddress(username + "@etudiant.univ-lille1.fr"));
			message.setRecipients(Message.RecipientType.TO,
					InternetAddress.parse(modo.getEmail()));
			message.setSubject("[Lille1] Modération du groupe " + groupe.getName());
			message.setText("Bonjour, "
					+ "\n\nVous venez d'être désassigner comme étant modérateur pour le groupe " + groupe.getName() +
					" : " + groupe.getDescription()+". \n\n A bientot sur le réseau d'ancien de Lille 1 !");

			Transport.send(message);


		} catch (MessagingException e) {
			throw new RuntimeException(e);
		}
	}

	@Override
	public boolean isAnimateurOfGroupe(int anim, int id) {
		Groupe g = getGroupeById(id);
		Enseignant e = getEnseignantById(anim);
		if (g.getAnimateur().contains(e)){
			return true;
		}

		return false;
	}

	@Override
	public EnseignantDTO addGroupetoAnim(int anim, GroupeDTO groupe) {
		Enseignant e = getEnseignantById(anim);
		Groupe g = getGroupeById(groupe.getId());
		e.getGroupesAnimes().add(g);
		g.getAnimateur().add(e);

		em.merge(e);
		em.merge(g);

		return  ce.MappingGroupeAnimateur(e, g);
	}
	
	@Override
	public boolean addGroupetoEnseign(int enseign, GroupeDTO groupe) {
		Enseignant e = getEnseignantById(enseign);
		Groupe g = getGroupeById(groupe.getId());
		
		if (!e.getGroupes().contains(g)) {
			e.getGroupes().add(g);
			g.getEnseignant().add(e);
			
			em.merge(e);
			em.merge(g);
			return true;
		}else {
			return false;
		}
	}

	@Override
	public List<EnseignantDTO> getAllAnimateur() {
		List<Enseignant> eList = em.createQuery("select o from Enseignant o", Enseignant.class).getResultList();
		List<EnseignantDTO> eDTOList = new ArrayList<EnseignantDTO>();
		for(Enseignant e : eList) {
			if (!e.getGroupesAnimes().isEmpty()) {
				eDTOList.add(ce.MappingGroupeAnimateur(e, e.getGroupesAnimes()));

			}else {
				eDTOList.add(e.toEnseignantDTO());
			}
		}
		return eDTOList;
	}

}
