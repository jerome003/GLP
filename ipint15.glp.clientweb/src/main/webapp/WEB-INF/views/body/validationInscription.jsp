<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@taglib uri="http://www.springframework.org/tags/form" prefix="form"%>

<div class="section">
	<div class="container">

		<div class="row">
			<div class="col-md-8">
				<h1>Liste des Etudiants non inscrit</h1>
			</div>
		</div>
		<div class="row">
			<table class="table table-striped table-hover table-users">
				<thead>
					<tr>
						<th>Nom</th>
						<th>Prenom</th>
						<th>Dernier diplôme</th>
						<th>Année obtention</th>
						<th></th>
						<th></th>
					</tr>
				</thead>

				<tbody>
					<c:forEach items="${liste}" var="results">
						<tr>
							<td>${results.nom}</td>
							<td>${results.prenom}</td>
							<td>${results.diplome}</td>
							<td>${results.anneeDiplome}</td>
							<td><a href="${pageContext.request.contextPath}/moderateur/validationGroup/${idGroupe}/etudiantOK/${results.id}"><span class="glyphicon glyphicon-ok"></span>
									Valider</a></td>
							<td><a href="#"><span
									class="glyphicon glyphicon-remove"></span> Refuser</a></td>
						</tr>
					</c:forEach>
				</tbody>

			</table>
		</div>
	</div>
</div>