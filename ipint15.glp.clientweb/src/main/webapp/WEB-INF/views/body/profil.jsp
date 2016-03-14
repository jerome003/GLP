<%@ page session="true"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<div class="section">
	<div class="container">
		<div class="row">
			<div class="col-md-12">
				<h1>${profil.prenom}${profil.nom}</h1>
				<%
					if ((Boolean) session.getAttribute("consultation") == false) {
				%>

				<a class="btn btn-primary"
					href="${pageContext.request.contextPath}/editionProfil">Editer
					profil<br>
				</a>
				<%
					}
				%>
				<br> <br>
			</div>
			<div class="row">
				<div class="col-md-12">
					<div class="col-md-6">
						<div class="row">
							<label class="col-md-4 intitule">Mail :</label> <label
								class="col-md-8">${profil.email}</label>
						</div>
						<div class="row">
							<label class="col-md-4 intitule">Tel :</label> <label
								class="col-md-8">${profil.numTelephone}</label>
						</div>
						<div class="row">
							<c:if test="${not empty profil.facebook}">
								<a href="${profil.facebook}"><img
									src="${pageContext.request.contextPath}/resources/img/facebook.png"
									alt="facebook" /></a>
							</c:if>
							<c:if test="${not empty profil.twitter}">
								<a href="${profil.twitter}"><img
									src="${pageContext.request.contextPath}/resources/img/twitter.jpg"
									alt="twitter" /></a>
							</c:if>
							<c:if test="${not empty profil.viadeo}">
								<a href="${profil.viadeo}"><img
									src="${pageContext.request.contextPath}/resources/img/viadeo.png"
									alt="viadeo" /></a>
							</c:if>
							<c:if test="${not empty profil.linkedin}">
								<a href="${profil.linkedin}"><img
									src="${pageContext.request.contextPath}/resources/img/linkedin.png"
									alt="linkedin" /></a>
							</c:if>
						</div>
						<div class="row">
							<label class="col-md-4 intitule">Ce que je recherche :</label> <label
								class="col-md-8">${profil.attentes}</label>
						</div>
					</div>
					<div class="col-md-6">
						<c:if test="${profil.statut == 'En emploi'}">
							<div class="row">
								<label class="col-md-5 intitule">Poste :</label> <label
									class="col-md-7">${profil.posteActu}</label>
							</div>
							<div class="row">
								<label class="col-md-5 intitule">Ville : </label> <label
									class="col-md-7">${profil.villeActu}</label>
							</div>
							<div class="row">
								<label class="col-md-5 intitule">Entreprise : </label> <label
									class="col-md-7">${profil.nomEntreprise}</label>
							</div>
						</c:if>
						<c:if test="${profil.statut == 'Freelance'}">
							<div class="row">
								<label class="col-md-5 intitule">Situation Actuelle :</label> <label
									class="col-md-7">${profil.statut}</label>
							</div>
						</c:if>
						<c:if test="${profil.statut == 'Sans emploi'}">
							<div class="row">
								<label class="col-md-5 intitule">Situation Actuelle :</label> <label
									class="col-md-7">${profil.statut}</label>
							</div>
						</c:if>
					</div>
				</div>
			</div>
			<div class="row"></div>
			<h3>Mes groupes</h3>
			<div class="well well-g">
				<c:forEach items="${listeGroupes}" var="results">
					<div class="row">
						<div class="col-md-12">
							<a href="${pageContext.request.contextPath}/groupe/${results.id}">${results.name}</a>
							: ${results.description}
							<c:if test="${results.institutionnel}"> (Institutionnel)</c:if>
						</div>
					</div>

				</c:forEach>

			</div>
			<div class="col-md-12"></div>
			<div class="row">
				<div class="col-md-12">
					<h3>Expériences Professionnelles</h3>
					<div class="well well-lg">
						<c:forEach items="${profil.profil.mesExperiences}"
							var="experience" varStatus="loop">
							<div class=experiencePro>
								<p>De ${experience.debut} à ${experience.fin} :
									${experience.libelle} chez ${experience.entreprise},
									${experience.ville} (${experience.region},${experience.pays} )</p>
								<p>${experience.description}</p>
							</div>

						</c:forEach>
					</div>
				</div>
			</div>
			<div class="row">
				<div class="col-md-12">
					<h3>Formation</h3>
					<div class="well well-lg">
						<table class="table table-striped table-hover">
							<thead>
								<tr>
									<th>Formation</th>
									<th>Etablissement</th>
									<th>Début</th>
									<th>Fin</th>
									<th>Ville</th>
									<th>Région</th>
									<th>Pays</th>
								</tr>
							</thead>
							<c:forEach items="${profil.profil.mesEcoles}" var="ecole"
								varStatus="loop">
								<tr>
									<td>${ecole.libelle}</td>
									<td>${ecole.etablissement}</td>
									<td>${ecole.debut}</td>
									<td>${ecole.fin}</td>
									<td>${ecole.ville}</td>
									<td>${ecole.region}</td>
									<td>${ecole.pays}</td>
								</tr>
							</c:forEach>
						</table>
					</div>
				</div>
			</div>
			<div class="row">
				<div class="col-md-6">
					<h3>Compétences</h3>
					<div class="well well-lg">
						<table class="table table-striped table-hover">
							<thead>
								<tr>
									<th>Compétence</th>
									<th>Niveau</th>
								</tr>
							</thead>
							<c:forEach items="${profil.profil.mesCompetences}"
								var="competence" varStatus="loop">
								<tr>
									<td>${competence.libelle}</td>
									<td>${competence.niveau}</td>
								</tr>
							</c:forEach>
						</table>
					</div>
				</div>
				<div class="col-md-6">
					<h3>Loisirs</h3>
					<div class="well well-lg">
						<ul class="list-group">
							<c:forEach items="${profil.profil.mesHobbies}" var="hobbie"
								varStatus="loop">
								<li>${hobbie.libelle}</li>
							</c:forEach>
						</ul>
					</div>
				</div>
			</div>
		</div>
	</div>
</div>