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
						if ((Boolean)session.getAttribute("consultation") == false) {
					%>

				<div class="col-md-4">
					<a class="btn btn-primary"
						href="${pageContext.request.contextPath}/editionProfil">Editer
						profil<br>
					</a>
				</div>
				   <% } %>
			</div>
		</div>
	</div>
	<div class="section">
		<div class="container">
			<div class="row">
				<div class="col-md-6">
					<h2 id="UserName">${etudiant.prenom} ${etudiant.nom}</h2>
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
									Mail : <strong>${etudiant.email}</strong>
								</p>
							</li>
						</ul>
					</div>
				</div>
				<div class="col-md-6">
					<h2 class="text-success">
						Expériences Professionnelles <br>
					</h2>
					<div class="col-md-12">
						<ul class="list-group">
							<li class="list-group-item">${listExpPro[0].libelle}</li>
							<li class="list-group-item">${listExpPro[1].libelle}</li>
							<li class="list-group-item">${listExpPro[2].libelle}</li>
							<li class="list-group-item">${listExpPro[3].libelle}</li>
							<li class="list-group-item">${listExpPro[4].libelle}</li>
						</ul>
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
						<ul class="list-group">
							<li class="list-group-item">${listCompetence[0].libelle}</li>
							<li class="list-group-item">${listCompetence[1].libelle}</li>
							<li class="list-group-item">${listCompetence[2].libelle}</li>
							<li class="list-group-item">${listCompetence[3].libelle}</li>
							<li class="list-group-item">${listCompetence[4].libelle}</li>
						</ul>
					</div>
				</div>
				<div class="col-md-6">
					<h2 class="text-success">Formation</h2>
					<div class="col-md-12">
						<ul class="list-group">
							<li class="list-group-item">${listEcole[0].libelle}</li>
							<li class="list-group-item">${listEcole[1].libelle}</li>
							<li class="list-group-item">${listEcole[2].libelle}</li>
							<li class="list-group-item">${listEcole[3].libelle}</li>
							<li class="list-group-item">${listEcole[4].libelle}</li>
						</ul>
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
						<ul class="list-group">
							<li class="list-group-item">${listLoisir[0].libelle}</li>
							<li class="list-group-item">${listLoisir[1].libelle}</li>
							<li class="list-group-item">${listLoisir[2].libelle}</li>
							<li class="list-group-item">${listLoisir[3].libelle}</li>
							<li class="list-group-item">${listLoisir[4].libelle}</li>
						</ul>
					</div>
				</div>
			</div>
		</div>
	</div>

