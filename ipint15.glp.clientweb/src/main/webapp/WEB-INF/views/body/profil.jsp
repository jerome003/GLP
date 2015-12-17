<%@ page session="true"%>
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
	</div>
	<div class="section">
		<div class="container">
			<div class="row">
				<div class="col-md-6">
					<h2 id="UserName">${profil.prenom}${profil.nom}</h2>
					<div class="col-md-12">
						<ul class="list-group">
							<li>
								<p>
									Poste actuel : <strong>jardinier</strong>
								</p>
							</li>
							<li>
								<p>
									Lieu: <strong>yolo</strong>
								</p>
							</li>
							<li>
								<p>
									Nom de l'entreprise : <strong></strong>
								</p>
							</li>
							<li>
								<p>
									Mail : <strong>${profil.email}</strong>
								</p>
							</li>
						</ul>
					</div>
				</div>
			</div>
			<div class="col-md-6">
				<h2 class="text-success">
					Expériences Professionnelles <br>
				</h2>
				<div class="col-md-12">
					<%
						if ((Boolean) session.getAttribute("consultation") == false) {
					%>
					<ul class="list-group">
						<li class="list-group-item">${etudiant.profil.mesExperiences[0].libelle}</li>
						<li class="list-group-item">${etudiant.profil.mesExperiences[1].libelle}</li>
						<li class="list-group-item">${etudiant.profil.mesExperiences[2].libelle}</li>
						<li class="list-group-item">${etudiant.profil.mesExperiences[3].libelle}</li>
						<li class="list-group-item">${etudiant.profil.mesExperiences[4].libelle}</li>
					</ul>
					<%
						} else {
					%>
					<ul class="list-group">
						<li class="list-group-item">${profilRecherche.profil.mesExperiences[0].libelle}</li>
						<li class="list-group-item">${profilRecherche.profil.mesExperiences[1].libelle}</li>
						<li class="list-group-item">${profilRecherche.profil.mesExperiences[2].libelle}</li>
						<li class="list-group-item">${profilRecherche.profil.mesExperiences[3].libelle}</li>
						<li class="list-group-item">${profilRecherche.profil.mesExperiences[4].libelle}</li>
					</ul>
					<%
						}
					%>
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
				<div class="col-md-12">
					<%
						if ((Boolean) session.getAttribute("consultation") == false) {
					%>
					<ul class="list-group">
						<li class="list-group-item">${etudiant.profil.mesCompetences[0].libelle}</li>
						<li class="list-group-item">${etudiant.profil.mesCompetences[1].libelle}</li>
						<li class="list-group-item">${etudiant.profil.mesCompetences[2].libelle}</li>
						<li class="list-group-item">${etudiant.profil.mesCompetences[3].libelle}</li>
						<li class="list-group-item">${etudiant.profil.mesCompetences[4].libelle}</li>
					</ul>
					<%
						} else {
					%>
					<ul class="list-group">
						<li class="list-group-item">${profilRecherche.profil.mesCompetences[0].libelle}</li>
						<li class="list-group-item">${profilRecherche.profil.mesCompetences[1].libelle}</li>
						<li class="list-group-item">${profilRecherche.profil.mesCompetences[2].libelle}</li>
						<li class="list-group-item">${profilRecherche.profil.mesCompetences[3].libelle}</li>
						<li class="list-group-item">${profilRecherche.profil.mesCompetences[4].libelle}</li>
					</ul>
					<%
						}
					%>
				</div>
			</div>
			<div class="col-md-6">
				<h2 class="text-success">Formation</h2>
				<div class="col-md-12">
					<%
						if ((Boolean) session.getAttribute("consultation") == false) {
					%>
					<ul class="list-group">
						<li class="list-group-item">${etudiant.profil.mesEcoles[0].libelle}</li>
						<li class="list-group-item">${etudiant.profil.mesEcoles[1].libelle}</li>
						<li class="list-group-item">${etudiant.profil.mesEcoles[2].libelle}</li>
						<li class="list-group-item">${etudiant.profil.mesEcoles[3].libelle}</li>
						<li class="list-group-item">${etudiant.profil.mesEcoles[4].libelle}</li>
					</ul>
					<%
						} else {
					%>
					<ul class="list-group">
						<li class="list-group-item">${profilRecherche.profil.mesEcoles[0].libelle}</li>
						<li class="list-group-item">${profilRecherche.profil.mesEcoles[1].libelle}</li>
						<li class="list-group-item">${profilRecherche.profil.mesEcoles[2].libelle}</li>
						<li class="list-group-item">${profilRecherche.profil.mesEcoles[3].libelle}</li>
						<li class="list-group-item">${profilRecherche.profil.mesEcoles[4].libelle}</li>
					</ul>
					<%
						}
					%>
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
				<div class="col-md-12">
					<%
						if ((Boolean) session.getAttribute("consultation") == false) {
					%>
					<ul class="list-group">
						<li class="list-group-item">${etudiant.profil.mesHobbies[0].libelle}</li>
						<li class="list-group-item">${etudiant.profil.mesHobbies[1].libelle}</li>
						<li class="list-group-item">${etudiant.profil.mesHobbies[2].libelle}</li>
						<li class="list-group-item">${etudiant.profil.mesHobbies[3].libelle}</li>
						<li class="list-group-item">${etudiant.profil.mesHobbies[4].libelle}</li>
					</ul>
					<%
						} else {
					%>
					<ul class="list-group">
						<li class="list-group-item">${profilRecherche.profil.mesHobbies[0].libelle}</li>
						<li class="list-group-item">${profilRecherche.profil.mesHobbies[1].libelle}</li>
						<li class="list-group-item">${profilRecherche.profil.mesHobbies[2].libelle}</li>
						<li class="list-group-item">${profilRecherche.profil.mesHobbies[3].libelle}</li>
						<li class="list-group-item">${profilRecherche.profil.mesHobbies[4].libelle}</li>
					</ul>
					<%
						}
					%>
				</div>
			</div>
		</div>
	</div>
</div>

