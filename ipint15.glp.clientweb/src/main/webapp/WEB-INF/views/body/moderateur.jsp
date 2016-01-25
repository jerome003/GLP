<%@page import="ipint15.glp.api.dto.ModerateurDTO"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@taglib uri="http://www.springframework.org/tags/form" prefix="form"%>

<div class="section">
	<div class="container">
		<div class="row">
			<div class="col-md-6">
				<h1>Interface Modo ${user.prenom}</h1>
			</div>
			<table class="table table-striped table-hover table-users">
				<thead>
					<tr>
						<th>Nom du groupe</th>
						<th>Description</th>
						<th></th>
					</tr>
				</thead>

				<tbody>
					<c:forEach items="${user.groupes}" var="groupe">

						<tr>
							<td>${groupe.name}</td>
							<td>${groupe.description}</td>
							<td><a class="btn mini blue-stripe"
								href="${pageContext.request.contextPath}/moderateur/validationGroup/${groupe.id}"><span
									class="glyphicon glyphicon-pencil"></span> Validation
									Inscription</a></td>

						</tr>
					</c:forEach>
				</tbody>

			</table>
		</div>

	</div>
</div>