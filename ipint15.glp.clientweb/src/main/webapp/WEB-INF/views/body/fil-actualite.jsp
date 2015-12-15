<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@taglib uri="http://www.springframework.org/tags/form" prefix="form"%>
<!DOCTYPE html>
<html>

<head>
<meta charset="utf-8">
<meta name="viewport" content="width=device-width, initial-scale=1">
<script type="text/javascript"
	src="http://cdnjs.cloudflare.com/ajax/libs/jquery/2.0.3/jquery.min.js"></script>
<script type="text/javascript"
	src="http://netdna.bootstrapcdn.com/bootstrap/3.3.4/js/bootstrap.min.js"></script>
<link
	href="http://cdnjs.cloudflare.com/ajax/libs/font-awesome/4.3.0/css/font-awesome.min.css"
	rel="stylesheet" type="text/css">
<link
	href="http://pingendo.github.io/pingendo-bootstrap/themes/default/bootstrap.css"
	rel="stylesheet" type="text/css">
</head>

<body>
	<div class="section">
		<div class="container">
			<div class="row">

				<div class="col-md-12">
					<h1>Fil d'actualité</h1> <br>
					
					<h1>Créer une publication</h1> <br>

					<form:form class="form-horizontal" role="form" method="post"
						commandName="command" action="addPublication">
						<div class="form-group">
						<div class="col-sm-2">
							<form:label path="titre" class="control-label">Titre</form:label>
						</div>
						<div class="col-sm-6">
							<form:input path="titre" type="text" class="form-control"/>
						</div>
					</div>
						<div class="form-group">
						<div class="col-sm-2">
							<form:label path="message" class="control-label">Message</form:label>
						</div>
						<div class="col-sm-6">
							<form:input path="message" type="text" class="form-control"/>
						</div>
					</div>
						<div class="form-group">
							<div class="col-sm-offset-2 col-sm-10">
								<button type="submit" value="Add Publication"
									class="btn btn-default">
									Publier <br>
								</button>
							</div>
						</div>
					</form:form>
				</div>
			</div>
		</div>
	</div>
	<div class="section">
		<div class="container">
			<div class="row">
				<div class="col-md-12">
					<div class="btn-group btn-group-justified">
						<a href="#" class="btn btn-info">Manuel Dupond</a> <a href="#"
							class="btn btn-default">Fans de BD</a>
					</div>
				</div>
			</div>
			<div class="row">
				<div class="col-md-12">
					<img src="26-07-10.jpg" class="center-block img-responsive">
				</div>
			</div>
		</div>
	</div>
	<div class="section">
		<div class="container">
			<div class="row">
				<div class="col-md-12">
					<div class="btn-group btn-group-justified">
						<a href="#" class="btn btn-info">Toto Bernard</a> <a href="#"
							class="btn btn-default">Journée des métiers de l'informatique</a>
					</div>
				</div>
			</div>
		</div>
	</div>
	<div class="section">
		<div class="container">
			<div class="row">
				<div class="col-md-12">
					<p>La journée des métiers de l'informatique se déroulera le 15
						février 2016 dont l'objectif est de faire connaître les diffrents
						métiers de l'informatique aux étudiants</p>
				</div>
			</div>
		</div>
	</div>
	<div class="section">
		<div class="container">
			<div class="row">
				<div class="col-md-6">
					<img src="IMG_4409_twitter.jpg" class="img-responsive">
				</div>
				<div class="col-md-6">
					<img src="informatique.jpg" class="img-responsive">
				</div>
			</div>
		</div>
	</div>
</body>

</html>