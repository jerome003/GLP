<%@ page session="true"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<div class="section">
	<div class="container">
		<div class="row">
			<div class="col-md-8">
				<h1 class="col-md-8">${profil.prenom} ${profil.nom}</h1>
				<p>
					<br> <br>
				</p>
			</div>
			<%
				if ((Boolean) session.getAttribute("consultation") == false) {
			%>

			<div class="col-md-4">
				<a class="btn btn-primary"
					href="${pageContext.request.contextPath}/editionProfil">Editer
					profil<br>
				</a>
			</div>
			<%
				}
			%>
		</div>
		<div class="row">
			<div class="col-md-12">
				<div class="col-md-7">
					<div class="col-md-6">
						<div class="well well-lg">
							<div class="row">
								<label class="control-label">Contact :</label>
							</div>
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
						</div>
					</div>
					<div class="col-md-6">
						<div class="well well-lg">
							<div class="row">
								<label class="control-label">Situation Actuelle :</label>
							</div>
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
						</div>
					</div>
				</div>
				<div class="col-md-5">
					<div class="well well-lg">
						<label class="control-label">Groupes :</label> <br />
						<c:if test="${not empty profil.groupe}">
						${profil.groupe.name}
					</c:if>
					</div>
				</div>
			</div>
		</div>
		<div class="row">
			<div class="col-md-12">
				<div class="well well-lg">
					<label class="control-label">Expériences Professionnelles :</label>
					<table class="table table-striped table-hover">
						<thead>
							<tr>
								<th>Poste</th>
								<th>Entreprise</th>
								<th>Début</th>
								<th>Durée</th>
							</tr>
						</thead>
						<c:forEach items="${profil.profil.mesExperiences}"
							var="experience" varStatus="loop">
							<tr>
								<td>${experience.libelle}</td>
								<td>${experience.entreprise}</td>
								<td>${experience.anneeDebut}</td>
								<td>${experience.duree}</td>
							</tr>
						</c:forEach>
					</table>
				</div>
			</div>
		</div>
		<div class="row">
			<div class="col-md-12">
				<div class="well well-lg">
					<label class="control-label">Formation :</label>
					<table class="table table-striped table-hover">
						<thead>
							<tr>
								<th>Formation</th>
							</tr>
						</thead>
						<c:forEach items="${profil.profil.mesEcoles}" var="ecole"
							varStatus="loop">
							<tr>
								<td>${ecole.libelle}</td>
							</tr>
						</c:forEach>
					</table>
				</div>
			</div>
		</div>
		<div class="row">
			<div class="col-md-6">
				<div class="well well-lg">
					<label class="control-label">Compétences :</label>
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
				<div class="well well-lg">
					<label class="control-label">Loisirs :</label>
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