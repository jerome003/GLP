<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ page session="false"%>
<div class="container">
	<div class="row">
		
		<div class="col-md-12">

			<img class="img-responsive"
				src="${pageContext.request.contextPath}/resources/img/student-life-header.jpg">
		</div>
	</div>
	<br>
	<div class="row">
		<div class="col-md-4">
			<div class="home_box well well-lg">

					<h2>
						Etudiants <br>
					</h2>
					<p>Étudiants de l'université de Lille, développer votre réseau
						:</p>
					<ul>
						<li>Recherchez des anciens étudiants</li>
						<li>Contactez les pour obtenir des informations</li>
					</ul>
					<a class="btn btn-lg btn-primary  btn-block"
						href="${pageContext.request.contextPath}/connexionEtudiant"><span
						class="glyphicon glyphicon-log-in"></span> Identifiez-vous !</a>
			</div>
		</div>
		<div class="col-md-4">
			<div class="home_box well well-lg">
				<h2>Anciens étudiants</h2>
				<p>Diplômés de l'Université de Lille, gardez le lien avec vos
					anciens camarades</p>
				<ul>
					<li>Recherchez des anciens camarades</li>
					<li>Contactez les pour obtenir des informations</li>
					<li>Partciper aux différents groupes</li>

				</ul>
				<a class="btn btn-lg btn-primary  btn-block"
					href="${pageContext.request.contextPath}/connexion"><span
					class="glyphicon glyphicon-log-in"></span> Identifiez-vous !</a> <br>
				<br> <a class="btn btn-lg btn-primary  btn-block"
					href="${pageContext.request.contextPath}/inscription"><span
					class="glyphicon glyphicon-pencil"></span> Pas encore de compte ?<br>
				</a>
			</div>
		</div>
		<div class="col-md-4 ">
			<div class="home_box well well-lg">
				<h2>Personnels Lille</h2>
				<p>Personnels de l'Université de Lille, garder le contact avec
					vos anciens élèves</p>
				<ul>
					<li>Recherchez des anciens élèves</li>
					<li>Contactez les pour obtenir des informations</li>
					<li>Partciper aux différents groupes</li>

				</ul>
				<a class="btn btn-lg btn-primary  btn-block"
					href="${pageContext.request.contextPath}/connexion"> <span
					class="glyphicon glyphicon-log-in"></span> Identifiez-vous !
				</a>
			</div>
		</div>
	</div>
</div>

