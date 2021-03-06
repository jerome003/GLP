<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@taglib uri="http://www.springframework.org/tags/form" prefix="form"%>
<div class="section">
	<div class="container">

		<div class="row">
			<div class="col-md-12">
				<h1>R�sultat de la recherche groupe : ${recherche}</h1>
			</div>
		</div>
		<div class="row">
			<table class="table table-striped table-hover table-users">
				<thead>
					<tr>
						<th class="col-md-2">Groupe</th>
						<th class="col-md-2"></th>
						<th class="col-md-4"></th>
					</tr>
				</thead>

				<tbody>
					<c:forEach items="${listeGroupe}" var="results">
						<tr>
							<td class="col-md-2">${results.name}</td>
							<td class="col-md-2"></td>
							<td class="col-md-4"><a
								href="${pageContext.request.contextPath}/groupe/${results.id}"
								class="btn mini blue-stripe" type="submit"><span
									class="glyphicon glyphicon-eye-open"></span> Voir le groupe</a></td>
						</tr>
					</c:forEach>
				</tbody>
			</table>
		</div>
		<div class="row">
			<div class="col-md-12">
				<h1>R�sultat de la recherche ancien etudiant : ${recherche}</h1>
			</div>
		</div>
		<div class="row">
			<table class="table table-striped table-hover table-users">
				<thead>
					<tr>
						<th class="col-md-2">Pr�nom</th>
						<th class="col-md-2">Nom</th>
						<th class="col-md-4"></th>
					</tr>
				</thead>

				<tbody>
					<c:forEach items="${liste}" var="results">
						<tr>
							<td class="col-md-2">${results.prenom}</td>
							<td class="col-md-2">${results.nom}</td>
							<td class="col-md-4"><a class="btn mini blue-stripe"
								href="${pageContext.request.contextPath}/profil/${results.id}"
								type="submit"><span class="glyphicon glyphicon-eye-open"></span>
									Voir le profil</a></td>
						</tr>
					</c:forEach>
				</tbody>
			</table>
		</div>

		<div class="row">
			<div class="col-md-12">
				<h1>R�sultat de la recherche etudiant : ${recherche}</h1>
			</div>
		</div>
		<div class="row">
			<table class="table table-striped table-hover table-users">
				<thead>
					<tr>
						<th class="col-md-2">Pr�nom</th>
						<th class="col-md-2">Nom</th>
						<th class="col-md-4"></th>
					</tr>
				</thead>

				<tbody>
					<c:forEach items="${listeEtu}" var="results">
						<tr>
							<td class="col-md-2">${results.prenom}</td>
							<td class="col-md-2">${results.nom}</td>
							<td class="col-md-4"><a class="btn mini blue-stripe"
								href="${pageContext.request.contextPath}/profilEtudiant/${results.id}"
								type="submit"><span class="glyphicon glyphicon-eye-open"></span>
									Voir le profil</a></td>
						</tr>
					</c:forEach>
				</tbody>
			</table>
		</div>
		<div class="row">
			<div class="col-md-12">
				<h1>R�sultat de la recherche d'enseignant : ${recherche}</h1>
			</div>
		</div>
		<div class="row">
			<table class="table table-striped table-hover table-users">
				<thead>
					<tr>
						<th class="col-md-2">Pr�nom</th>
						<th class="col-md-2">Nom</th>
						<th class="col-md-4"></th>
					</tr>
				</thead>

				<tbody>
					<c:forEach items="${listeEnseign}" var="results">
						<tr>
							<td class="col-md-2">${results.prenom}</td>
							<td class="col-md-2">${results.nom}</td>
							<td class="col-md-4"><a class="btn mini blue-stripe"
								href="${pageContext.request.contextPath}/profilEnseignant/${results.id}"
								type="submit"><span class="glyphicon glyphicon-eye-open"></span>
									Voir le profil</a></td>
						</tr>
					</c:forEach>
				</tbody>
			</table>
		</div>
	</div>
</div>
