<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@taglib uri="http://www.springframework.org/tags/form" prefix="form"%>

<div class="section">
	<div class="container">
		<div class="row">
			<div class="col-md-4">
				<h1>Cr�ation groupe</h1>
			</div>
		</div>
		<div class="row">
			<form class="form-horizontal" role="form" method="post"
				action="saveGroupe">
				<div class="form-group">
					<div class="col-sm-2">
						<label class="control-label">Nom du groupe :</label>
					</div>
					<div class="col-sm-6">
						<input id="nameGroupe" name="nameGroupe" type="text"
							class="form-control" />
					</div>
				</div>
				<div class="form-group">
					<div class="col-sm-2">
						<label class="control-label">Description du groupe :</label>
					</div>
					<div class="col-sm-6">
						<input id="descriptionGroupe" name="descriptionGroupe" type="text"
							class="form-control" />
					</div>
				</div>
				<div class="form-group">
					<div class="col-sm-2">
						<label class="control-label">Moderateur :</label>
					</div>
					<div class="col-sm-2">
						<div class="form-inline">
							<select id="modo" name="modo">
								<c:forEach items="${moderateurList}" var="item"
									varStatus="status">
									<option value="${item.id}">${item.email}</option>
								</c:forEach>
							</select>
						</div>
					</div>
					<div class="col-sm-2">
						<a class="btn mini blue-stripe"
							href="${pageContext.request.contextPath}/admin/moderateurs">Besoin
							d'ajouter un mod�rateur ?</a>
					</div>

					<div class="form-group">
						<div class="col-sm-2"></div>
						<div class="col-sm-3">
							<input type="submit" value="Enregistrer" />
						</div>
					</div>
			</form>
		</div>
	</div>
	<div class="row">
		<div class="col-md-4">
			<h1>Liste des groupes</h1>
		</div>
	</div>
	<div class="row">
		<table class="table table-striped table-hover table-users">
			<thead>
				<tr>
					<th>Nom du groupe (nb)</th>
					<th>Description</th>
					<th></th>
					<th></th>
				</tr>
			</thead>

			<tbody>
				<c:forEach items="${liste}" var="results">
					<tr>
						<td>${results.name}
							(${myInjectedBean.getGroupeSize(results.id)})</td>
						<td>${results.description}</td>
						<td><a class="btn mini blue-stripe"
							href="${pageContext.request.contextPath}/admin/editGroupe/${results.id}"><span
								class="glyphicon glyphicon-pencil"></span> Edit</a></td>
						<td><a
							href="${pageContext.request.contextPath}/admin/removeGroupe/${results.id}"
							class="confirm-delete btn mini red-stripe" role="button"><span
								class="glyphicon glyphicon-trash"></span> Delete</a></td>
					</tr>
				</c:forEach>
			</tbody>

		</table>
	</div>
</div>
</div>