<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<div class="container">
	<div class="row">
		<h1 class="col-md-6">${etudiantConsultation.prenom} ${etudiantConsultation.nom}</h1>
	</div>
	<div class="well well-g">
		<div class="row">
			<div class="col-md-12">Mail : ${etudiantConsultation.mail}</div>
		</div>
	</div>
	<h3>Mes groupes : </h3>
	<div class="well well-g">
		<c:forEach items="${listeGroupes}" var="results">
			<div class="row">
				<div class="col-md-12">
					<a href="${pageContext.request.contextPath}/groupe/${results.id}">${results.name}</a>
					: ${results.description}
					<c:if test="${results.institutionnel}"> (Institutionnel)</c:if>
				</div>
			</div>

		</c:forEach>

	</div>
</div>