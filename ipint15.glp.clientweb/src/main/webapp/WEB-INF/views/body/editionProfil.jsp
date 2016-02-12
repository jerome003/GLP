<%@page import="ipint15.glp.api.dto.HobbieDTO"%>
<%@page import="ipint15.glp.api.dto.EcoleDTO"%>
<%@page import="ipint15.glp.api.dto.CompetenceDTO"%>
<%@page import="java.util.ArrayList"%>
<%@page import="ipint15.glp.api.dto.ExperienceDTO"%>
<%@page import="java.util.List"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn"%>
<div class="section">
	<div class="container">
		<form action="editionProfil" method="post"
			onsubmit="return confirm('Voulez vous vraiment enregistrer ?');"
			name="modif_form">
			<div class="row">
				<div class="row">
					<legend>Informations personelles</legend>
					<fieldset>
						<div class='row'>
							<div class='col-sm-4'>
								<div class='form-group'>
									<input type="hidden" id="idEtu" name="idEtu"
										value="${profil.id}" /> <label for="mail">Mail</label> <input
										class="form-control" id="mail" value="${profil.email}"
										name="mail" disabled="true" required="true" size="30"
										type="text" />
								</div>
							</div>
							<div class='col-sm-4'>
								<div class='form-group'>
									<label for="numTelephone">Téléphone</label> <input
										class="form-control" id="numTelephone"
										value="${profil.numTelephone}" name="numTelephone"
										required="true" size="30" type="text" />
								</div>
							</div>
						</div>
						<div class='row'>
							<div class='col-sm-8'>
								<div class="form-group">
									<label for="attentes">Ce que je recherche</label>

									<textarea class="form-control" id="attentes" name="attentes"><c:out
											value="${profil.attentes}" /></textarea>
								</div>
							</div>
						</div>
					</fieldset>
					<legend>Situation actuelle</legend>
					<fieldset>
						<div class="row">
							<div class='col-sm-4'>
								<div class='form-group'>
									<label class="radio-inline"> <input type="radio"
										name="statut" value="En emploi"
										${profil.statut == 'En emploi' ? 'checked' : ''}>En
										emploi
									</label> <label class="radio-inline"> <input type="radio"
										name="statut" value="Sans emploi"
										${profil.statut == 'Sans emploi' ? 'checked' : ''}>Sans
										emploi
									</label> <label class="radio-inline"> <input type="radio"
										name="statut" value="Freelance"
										${profil.statut == 'Freelance' ? 'checked' : ''}>Freelance
									</label>
								</div>
							</div>
						</div>
						<div class='row'>
							<div class='col-sm-4'>
								<div class='form-group'>
									<label for="posteActu">Poste</label> <input
										class="form-control" id="posteActu"
										value="${profil.posteActu}" name="posteActu" size="30"
										type="text" />
								</div>
							</div>
							<div class='col-sm-4'>
								<div class='form-group'>
									<label for="villeActu">Ville actuelle</label> <input
										class="form-control" id="villeActu"
										value="${profil.villeActu}" name="villeActu" size="30"
										type="text" />
								</div>
							</div>
							<div class='col-sm-4'>
								<div class='form-group'>
									<label for="nomEntreprise">Nom de l'entreprise</label> <input
										class="form-control" id="nomEntreprise"
										value="${profil.nomEntreprise}" name="nomEntreprise" size="30"
										type="text" />
								</div>
							</div>
						</div>
					</fieldset>
					<legend>Réseaux sociaux</legend>
					<fieldset>
						<div class='row'>
							<div class='col-sm-3'>
								<div class='form-group'>
									<label for="twitter">Twitter</label> <input
										class="form-control" id="twitter" value="${profil.twitter}"
										name="twitter" size="30" type="text" />
								</div>
							</div>
							<div class='col-sm-3'>
								<div class='form-group'>
									<label for="facebook">Facebook</label> <input
										class="form-control" id="facebook" value="${profil.facebook}"
										name="facebook" size="30" type="text" />
								</div>
							</div>
							<div class='col-sm-3'>
								<div class='form-group'>
									<label for="viadeo">Viadeo</label> <input class="form-control"
										id="viadeo" value="${profil.viadeo}" name="viadeo" size="30"
										type="text" />
								</div>
							</div>
							<div class='col-sm-3'>
								<div class='form-group'>
									<label for="linkedin">Linkedin</label> <input
										class="form-control" id="linkedin" value="${profil.linkedin}"
										name="linkedin" size="30" type="text" />
								</div>
							</div>
						</div>
					</fieldset>
					<div class="row">
						<div class="col-sm-offset-5 col-sm-2 text-center">
							<button type="submit" class="btn btn-success">Enregistrer</button>
						</div>
					</div>
				</div>
			</div>
			<h3>
				Expériences Professionnelles <a
					class="btn btn-default glyphicon glyphicon-plus-sign"
					onClick="AddExpPro();"></a>
			</h3>
			<div class="row" id="Experiences">
				<c:forEach items="${profil.profil.mesExperiences}" var="experience"
					varStatus="loop">
					<div class="well well-lg" id="MonExperience${loop.index}"
						name="ExpPro">
						<legend>
							Experience <a
								class="btn btn-default glyphicon glyphicon-minus-sign"
								onClick="suprrimerExp(${loop.index});"></a>
						</legend>
						<div class='form-group'>
							<div class="row">
								<div class='col-sm-6'>
									<div class='col-sm-2'>
										<label for="poste">Poste</label>
									</div>
									<div class='col-sm-10'>
										<input id="expProPoste${loop.index}" name="poste"
											value="${experience.libelle}" maxlength="15"
											class="form-control">
									</div>
								</div>
								<div class='col-sm-6'>
									<div class='col-sm-2'>
										<label for="entreprise">Entreprise</label>
									</div>
									<div class='col-sm-10'>
										<input id="expProEntreprise${loop.index}" name="entreprise"
											value="${experience.entreprise}" maxlength="15"
											class="form-control">
									</div>
								</div>
							</div>
							<br>
							<div class="row">
								<div class='col-sm-6'>
									<div class='col-sm-2'>
										<label for="ville">Ville</label>
									</div>
									<div class='col-sm-10'>
										<input id="expProVille${loop.index}" name="ville"
											value="${experience.ville}" maxlength="15"
											class="form-control">
									</div>
								</div>
								<div class='col-sm-6'>
									<div class='col-sm-2'>
										<label for="region">Region</label>
									</div>
									<div class='col-sm-10'>
										<input id="expProRegion${loop.index}" name="region"
											value="${experience.region}" maxlength="15"
											class="form-control">
									</div>
								</div>
							</div>
							<div class="row">
								<div class='col-sm-6'>
									<div class='col-sm-2'>
										<label for="pays">Pays</label>
									</div>
									<div class='col-sm-10'>
										<select class="form-control" id="expProPays${loop.index}"
											name="pays" onchange="changementVilleExp(${loop.index})">
											<option value="France"
												${experience.pays == 'France' ? 'selected' : ''}>France</option>
											<option value="Royaume-Unis"
												${experience.pays == 'Royaume-Unis' ? 'selected' : ''}>Royaume-Unis</option>
											<option value="Belgique"
												${experience.pays == 'Belgique' ? 'selected' : ''}>Belgique</option>
										</select>
									</div>
								</div>
							</div>
							<br>
							<div class="row">
								<div class='col-sm-6'>
									<div class='col-sm-2'>
										<label for="dateDebut">Date début</label>
									</div>
									<div class='col-sm-10'>
										<input id="expProDebut${loop.index}" name="debut"
											value="${experience.debut}" maxlength="15"
											class="form-control">
									</div>
								</div>
								<div class='col-sm-6'>
									<div class='col-sm-2'>
										<label for="dateFin">Date fin</label>
									</div>
									<div class='col-sm-10'>
										<input id="expProFin${loop.index}" name="fin"
											value="${experience.fin}" maxlength="15" class="form-control">
									</div>
								</div>
							</div>
							<br>
							<div class="row">
								<div class='col-sm-1'>
									<label for="description">Description</label>
								</div>
								<div class='col-sm-11'>
									<textarea class="form-control"
										id="expProDescription${loop.index}" name="description"><c:out
											value="${experience.description}" /></textarea>
								</div>
							</div>
						</div>
					</div>
				</c:forEach>
			</div>
			<h3>
				Compétences <a class="btn btn-default glyphicon glyphicon-plus-sign"
					onClick="AddComp();"></a>
			</h3>
			<div class="row" id="Competences">
				<c:forEach items="${profil.profil.mesCompetences}" var="competence"
					varStatus="loop">
					<div class="col-sm-5 well well-lg col-sm-offset-1"
						id="MaComp${loop.index}" name="Comp">
						<div class='col-sm-6'>
							<input id="compNom${loop.index}" name="compNom"
								value="${competence.libelle}" class="form-control"
								required="true">
						</div>
						<div class='col-sm-2'>
							<label for="note">Note</label>
						</div>
						<div class='col-sm-3'>
							<select class="form-control" id="compNote${loop.index}"
								name="compNote">
								<option value="1" ${competence.niveau == '1' ? 'selected' : ''}>1</option>
								<option value="2" ${competence.niveau == '2' ? 'selected' : ''}>2</option>
								<option value="3" ${competence.niveau == '3' ? 'selected' : ''}>3</option>
								<option value="4" ${competence.niveau == '4' ? 'selected' : ''}>4</option>
								<option value="5" ${competence.niveau == '5' ? 'selected' : ''}>5</option>
							</select>
						</div>
						<div class='col-sm-1'>
							<a class="btn btn-default glyphicon glyphicon-minus-sign"
								onClick="suprrimerComp(${loop.index});"></a>
						</div>
					</div>
				</c:forEach>
			</div>
			<h3>
				Formations <a class="btn btn-default glyphicon glyphicon-plus-sign"
					onClick="AddFormation();"></a>
			</h3>
			<div class="row" id="Formations">
				<c:forEach items="${profil.profil.mesEcoles}" var="ecole"
					varStatus="loop">
					<div class="well well-lg" id="MaFormation${loop.index}"
						name="Formation">
						<legend>
							Formation <a
								class="btn btn-default glyphicon glyphicon-minus-sign"
								onClick="suprrimerFormation(${loop.index});"></a>
						</legend>
						<div class='form-group'>
							<div class="row">
								<div class='col-sm-6'>
									<div class='col-sm-2'>
										<label for="intitule">Intitulé</label>
									</div>
									<div class='col-sm-10'>
										<input id="formationIntitule${loop.index}" name="intitule"
											value="${ecole.libelle}" maxlength="15" class="form-control">
									</div>
								</div>
								<div class='col-sm-6'>
									<div class='col-sm-3'>
										<label for="etablissement">Etablissement</label>
									</div>
									<div class='col-sm-9'>
										<input id="formationEtablissement${loop.index}"
											name="etablissement" value="${ecole.etablissement}"
											maxlength="15" class="form-control">
									</div>
								</div>
							</div>
							<br>
							<div class="row">
								<div class='col-sm-6'>
									<div class='col-sm-2'>
										<label for="dateDebut">Date début</label>
									</div>
									<div class='col-sm-10'>
										<input id="formationDebut${loop.index}" name="debutForm"
											value="${ecole.debut}" maxlength="15" class="form-control">
									</div>
								</div>
								<div class='col-sm-6'>
									<div class='col-sm-3'>
										<label for="dateFin">Date fin</label>
									</div>
									<div class='col-sm-9'>
										<input id="formationFin${loop.index}" name="finForm"
											value="${ecole.fin}" maxlength="15" class="form-control">
									</div>
								</div>
							</div>
							<br>
							<div class="row">
								<div class='col-sm-6'>
									<div class='col-sm-2'>
										<label for="ville">Ville</label>
									</div>
									<div class='col-sm-10'>
										<input id="formationVille${loop.index}" name="villeForm"
											value="${ecole.ville}" maxlength="15" class="form-control">
									</div>
								</div>
								<div class='col-sm-6'>
									<div class='col-sm-2'>
										<label for="region">Region</label>
									</div>
									<div class='col-sm-10'>
										<input id="formationRegion${loop.index}" name="regionForm"
											value="${ecole.region}" maxlength="15" class="form-control">
									</div>
								</div>
							</div>
							<div class="row">
								<div class='col-sm-6'>
									<div class='col-sm-2'>
										<label for="pays">Pays</label>
									</div>
									<div class='col-sm-10'>
										<input id="formationPays${loop.index}" name="paysForm"
											value="${ecole.pays}" maxlength="15" class="form-control">
									</div>
								</div>
							</div>
						</div>
					</div>
				</c:forEach>
			</div>
			<h3>
				Loisirs <a class="btn btn-default glyphicon glyphicon-plus-sign"
					onClick="AddLoisir();"></a>
			</h3>
			<div class="row" id="Loisirs">
				<c:forEach items="${profil.profil.mesHobbies}" var="loisir"
					varStatus="loop">
					<div class="col-sm-3 well well-lg col-sm-offset-1"
						id="MonLoisir${loop.index}" name="Loisir">
						<div class='col-sm-10'>
							<input id="loisirNom${loop.index}" name="loisirNom"
								value="${loisir.libelle}" class="form-control" required="true">
						</div>
						<div class='col-sm-2'>
							<a class="btn btn-default glyphicon glyphicon-minus-sign"
								onClick="suprrimerLoisir(${loop.index});"></a>
						</div>
					</div>
				</c:forEach>
			</div>
		</form>
	</div>
</div>