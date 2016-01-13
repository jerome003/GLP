<%@ page session="true"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<div class="section">
	<div class="container">
		<div class="row">
			<div class="col-md-4">
				<h1 class="col-md-2">Profil</h1>
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
			<div class="col-md-6">
				<div class="well well-lg">
					<h2 id="UserName">${profil.prenom} ${profil.nom}</h2>
						<ul class="list-group">
							<li>
									Poste actuel : <strong>${profil.posteActu}</strong>
							</li>
							<li>
									Ville actuelle : <strong>${profil.villeActu}</strong>
							</li>
							<li>
									Nom de l'entreprise : <strong>${profil.nomEntreprise}</strong>
							</li>
							<li>
									Mail : <strong>${profil.email}</strong>
							</li>
							<li>
									Numéro de téléphone : <strong>${profil.numTelephone}</strong>
							</li>
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
						</ul>
				</div>
			</div>
		</div>
		<div class="row">
			<div class="col-md-6">
				<div class="well well-lg">
					<h2>
						Expériences Professionnelles <br>
					</h2>
					<ul class="list-group">
						<c:forEach items="${profil.profil.mesExperiences}"
							var="experience" varStatus="loop">
							<li>${experience.libelle}</li>
						</c:forEach>

					</ul>
				</div>
			</div>
			<div class="col-md-6">
				<div class="well well-lg">
					<h2>Compétences</h2>
					<ul class="list-group">
						<c:forEach items="${profil.profil.mesCompetences}"
							var="competence" varStatus="loop">
							<li>${competence.libelle}</li>
						</c:forEach>
					</ul>
				</div>
			</div>
		</div>
		<div class="row">
			<div class="col-md-6">
				<div class="well well-lg">
					<h2>Formation</h2>
					<ul class="list-group">
						<c:forEach items="${profil.profil.mesEcoles}" var="ecole"
							varStatus="loop">
							<li>${ecole.libelle}</li>
						</c:forEach>
					</ul>
				</div>
			</div>
			<div class="col-md-6">
				<div class="well well-lg">
					<h2>Loisirs</h2>
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



