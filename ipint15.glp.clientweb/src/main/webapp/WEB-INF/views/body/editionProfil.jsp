<%@page import="ipint15.glp.api.dto.HobbieDTO"%>
<%@page import="ipint15.glp.api.dto.EcoleDTO"%>
<%@page import="ipint15.glp.api.dto.CompetenceDTO"%>
<%@page import="java.util.ArrayList"%>
<%@page import="ipint15.glp.api.dto.ExperienceDTO"%>
<%@page import="java.util.List"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn"%>

<%
        List<ExperienceDTO> listExperience = new ArrayList<ExperienceDTO>();
        /*ExperienceDTO e = new ExperienceDTO();
        e.setLibelle("Grand vainqueur de la belette de Winchester");
        ExperienceDTO e2 = new ExperienceDTO();
        e2.setLibelle("Auteur du livre \"Le druidisme expliqué aux personnes âgées\"");
        ExperienceDTO e3 = new ExperienceDTO();
        e3.setLibelle("Test");
        listExperience.add(e);
        listExperience.add(e2);
        listExperience.add(e3);*/
        request.setAttribute("expList", listExperience);

        List<CompetenceDTO> listCompetence = new ArrayList<CompetenceDTO>();
        /*CompetenceDTO c1 = new CompetenceDTO();
        c1.setLibelle("Faire des crepes");
        listCompetence.add(c1);*/
        request.setAttribute("compList", listCompetence);

        List<EcoleDTO> listFormation = new ArrayList<EcoleDTO>();
        request.setAttribute("formList", listFormation);

        List<HobbieDTO> listLoisirs = new ArrayList<HobbieDTO>();
        request.setAttribute("loisirList", listLoisirs);
%>

<div class="section">
	<div class="container">
		<div class="row">
			<div class="col-md-4">
				<h1 class="col-md-2 text-success">Profil</h1>
				<p>
					<br> <br>
				</p>
			</div>
		</div>
	</div>
</div>
<div class="section">
	<div class="container">
		<div class="row">
			<div class="col-md-6">
				<h2 id="UserName">${etudiant.prenom} ${etudiant.nom}</h2>
				<div class="text-success">
					<form method="post" action="saveProfile">
						<ul class="list-group">
							<li><label>Poste actuel :</label></br> <input type="text"
								id="posteActu" name="posteActu" /></li>
							<li><label>Lieu:</label></br> <input type="text" id="lieu"
								name="lieu" placeholder="Tizi-Ouzou" /></li>
							<li><label>Nom de l'entreprise :</label></br> <input type="text"
								id="entreprise" name="entreprise" /></li>
							<li><label>Mail :</label></br> <input type="mail" id="mail"
								name="mail" readOnly="readOnly" value="${etudiant.email}" /></li>
						</ul>
						<input type="submit" value="Envoyer" />
					</form>
				</div>
			</div>
			<div class="col-md-6">
				<h2 class="text-success">
					Expériences Professionnelles <br>
				</h2>

				<div class="col-md-12">
					<div id="lesExpPro">

						<form method="post" action="saveExpPro">
							<input type="hidden" id="mailEtu" name="mail"
								value="${etudiant.email}" /> <input type="text" id="expPro1"
								name="expPro1" value="${profil.profil.mesExperiences[0].libelle}" /> <input
								type="text" id="expPro2" name="expPro2"
								value="${profil.profil.mesExperiences[1].libelle}" /> <input type="text"
								id="expPro3" name="expPro3" value="${profil.profil.mesExperiences[2].libelle}" />
							<input type="text" id="expPro4" name="expPro4"
								value="${profil.profil.mesExperiences[3].libelle}" /> <input type="text"
								id="expPro5" name="expPro5" value="${profil.profil.mesExperiences[4].libelle}" />
							<input type="submit" value="Envoyer" />
						</form>

						<div>
							<ul class="list-group"></ul>
						</div>
					</div>
				</div>
			</div>
		</div>
		<div class="section">
			<div class="container">
				<div class="row">
					<div class="col-md-6">
						<h2 class="text-success">Compétences</h2>
						<div id="lesCompetences" class="col-md-12">

							<form method="post" action="saveCompetence">
								<input type="hidden" id="mailEtuCompetence" name="mail"
									value="${etudiant.email}" /> <input type="text" id="comp1"
									name="comp1" value="${profil.profil.mesCompetences[0].libelle}" /> <input
									type="text" id="comp2" name="comp2"
									value="${profil.profil.mesCompetences[1].libelle}" /> <input type="text"
									id="comp3" name="comp3" value="${profil.profil.mesCompetences[2].libelle}" />
								<input type="text" id="comp4" name="comp4"
									value="${profil.profil.mesCompetences[3].libelle}" /> <input type="text"
									id="comp5" name="comp5" value="${profil.profil.mesCompetences[4].libelle}" />
								<input type="submit" value="Envoyer" />
							</form>

						</div>
					</div>
					<div class="col-md-6">
						<h2 class="text-success">Formation</h2>
						<div id="lesFormations" class="col-md-12">

							<form method="post" action="saveFormation">
								<input type="hidden" id="mailEtuFormation" name="mail"
									value="${etudiant.email}" /> <input type="text"
									id="formation1" name="formation1"
									value="${profil.profil.mesEcoles[0].libelle}" /> <input type="text"
									id="formation2" name="formation2"
									value="${profil.profil.mesEcoles[1].libelle}" /> <input type="text"
									id="formation3" name="formation3"
									value="${profil.profil.mesEcoles[2].libelle}" /> <input type="text"
									id="formation4" name="formation4"
									value="${profil.profil.mesEcoles[3].libelle}" /> <input type="text"
									id="formation5" name="formation5"
									value="${profil.profil.mesEcoles[4].libelle}" /> <input type="submit"
									value="Envoyer" />
							</form>

						</div>
					</div>
				</div>
			</div>
		</div>
		<div class="section">
			<div class="container">
				<div class="row">
					<div class="col-md-6">
						<h2 class="text-success">Loisirs</h2>
						<div id="lesLoisirs" class="col-md-12">

							<form method="post" action="saveLoisir">
								<input type="hidden" id="mailEtuFormation" name="mail"
									value="${etudiant.email}" /> <input type="text" id="loisir1"
									name="loisir1" value="${profil.profil.mesHobbies[0].libelle}" /> <input
									type="text" id="loisir2" name="loisir2"
									value="${profil.profil.mesHobbies[1].libelle}" /> <input type="text"
									id="loisir3" name="loisir3" value="${profil.profil.mesHobbies[2].libelle}" />
								<input type="text" id="loisir4" name="loisir4"
									value="${profil.profil.mesHobbies[3].libelle}" /> <input type="text"
									id="loisir5" name="loisir5" value="${profil.profil.mesHobbies[4].libelle}" />
								<input type="submit" value="Envoyer" />
							</form>

						</div>
					</div>
				</div>
			</div>
		</div>