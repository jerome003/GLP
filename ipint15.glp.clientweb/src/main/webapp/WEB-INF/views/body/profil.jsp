<%@ page session="true"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<div class="section">
	<div class="container">
		<div class="row">
			<div class="col-md-4">
				<h1 class="col-md-2 text-success">Profil</h1>
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
				<h2 id="UserName">${profil.prenom}${profil.nom}</h2>
				<div class="col-md-12">
					<ul class="list-group">
						<li>
							<p>
								Poste actuel : <strong>${profil.posteActu}</strong>
							</p>
						</li>
						<li>
							<p>
								Ville actuelle : <strong>${profil.villeActu}</strong>
							</p>
						</li>
						<li>
							<p>
								Nom de l'entreprise : <strong>${profil.nomEntreprise}</strong>
							</p>
						</li>
						<li>
							<p>
								Mail : <strong>${profil.email}</strong>
							</p>
						</li>
						<li>
							<p>
								Numéro de téléphone : <strong>${profil.numTelephone}</strong>
							</p>
						</li>
					</ul>
				</div>
			</div>
		</div>
		<div class="row">
			<div class="col-md-6">
				<h2 class="text-success">
					Expériences Professionnelles <br>
				</h2>
				<ul class="list-group">
					<c:forEach items="${profil.profil.mesExperiences}" var="experience"
						varStatus="loop">
						<li>${experience.libelle}</li>
					</c:forEach>

				</ul>
			</div>
			<div class="col-md-6">
				<h2 class="text-success">Compétences</h2>
				<ul class="list-group">
					<c:forEach items="${profil.profil.mesCompetences}" var="competence"
						varStatus="loop">
						<li>${competence.libelle}</li>
					</c:forEach>
				</ul>
			</div>
		</div>
		<div class="row">
			<div class="col-md-6">
				<h2 class="text-success">Formation</h2>
				<ul class="list-group">
					<c:forEach items="${profil.profil.mesEcoles}" var="ecole"
						varStatus="loop">
						<li>${ecole.libelle}</li>
					</c:forEach>
				</ul>
			</div>
			<div class="col-md-6">
				<h2 class="text-success">Loisirs</h2>
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



