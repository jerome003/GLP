<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<div class="container">
	<div class="row">
		<h1 class="col-md-6">${enseignantConsultation.prenom}
			${enseignantConsultation.nom}</h1>
	</div>
	<div class="well well-g">
		<div class="row">
			<div class="col-md-12">Mail : ${enseignantConsultation.mail}</div>
		</div>
	</div>
	<h3>Anime les groupes :</h3>
	<div class="well well-g">
		<c:forEach items="${enseignantConsultation.listeGroupesAnime}"
			var="results">
			<div class="row">
				<div class="col-md-12"> <a
						href="${pageContext.request.contextPath}/groupe/${results.id}">${results.name}</a> :
					${results.description}</div>
			</div>

		</c:forEach>

	</div>
</div>