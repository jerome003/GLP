<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@taglib uri="http://www.springframework.org/tags/form" prefix="form"%>

<div class="section">
	<div class="container">
				<div class="row">
			<div class="col-md-4">
				<h1>Gestion des Animateurs</h1>
			</div>
		</div>
		<div class="row">
			<table class="table table-striped table-hover table-users">
				<thead>
					<tr>
					<th>Nom du groupe (nb)</th>
					<th>Description</th>
					<th>Animateurs</th>
					<th></th>
					</tr>
				</thead>

				<tbody>
					<c:forEach items="${liste}" var="results">
						<tr>
						<td>${results.name}
							(${myInjectedBean.getGroupeSize(results.id)})</td>
						<td>${results.description}</td>
						<td>
							<c:forEach items="${results.animateurs}" var="resultsAnimateur">
								${resultsAnimateur.name}
							</c:forEach>
						</td>
						<td><a class="btn mini blue-stripe"
							href="${pageContext.request.contextPath}/admin/editerAnimateur/${results.id}"><span
								class="glyphicon glyphicon-pencil"></span> Edit</a></td>
						</tr>
					</c:forEach>
				</tbody>

			</table>
		</div>
	</div>
</div>