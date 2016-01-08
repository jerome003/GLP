<%@taglib uri="http://www.springframework.org/tags/form" prefix="form"%>
<link rel="stylesheet"
	href="http://code.jquery.com/ui/1.10.3/themes/smoothness/jquery-ui.css" />
<script src="//code.jquery.com/ui/1.10.4/jquery-ui.js"></script>
<script src="//code.jquery.com/jquery-1.11.0.js"></script>

<script
	src="http://cdn.jsdelivr.net/webshim/1.12.4/extras/modernizr-custom.js"></script>
<!-- polyfiller file to detect and load polyfills -->
<script src="http://cdn.jsdelivr.net/webshim/1.12.4/polyfiller.js"></script>
<script>
	webshims.setOptions('waitReady', false);
	webshims.setOptions('forms-ext', {
		types : 'date'
	});
	webshims.polyfill('forms forms-ext');
</script>
<div class="section">
	<div class="container">
		<div class="row">
			<div class="col-md-12">
				<form:form class="form-horizontal" role="form" method="post"
					commandName="command" action="addEtudiant">
					<div class="form-group">
						<div class="col-sm-2">
							<label class="control-label">Civilité</label>
						</div>
						<div class="col-sm-10">
							<div class="radio">
								<label class="radio-inline"> <form:radiobutton
										path="civilite" checked="checked" value="M" />M.
								</label> <label class="radio-inline"> <form:radiobutton
										path="civilite" value="Mme" />Mme
								</label>
							</div>
						</div>
					</div>

					<div class="form-group">
						<div class="col-sm-2">
							<form:label path="prenom" class="control-label">Prénom</form:label>
						</div>
						<div class="col-sm-10">
							<form:input path="prenom" type="text" class="form-control" />
							<form:errors path="prenom" />
						</div>
					</div>
					<div class="form-group">
						<div class="col-sm-2">
							<form:label path="nom" class="control-label">Nom</form:label>
						</div>
						<div class="col-sm-10">
							<form:input path="nom" type="text" class="form-control" />
							<form:errors path="nom" />
						</div>
					</div>
					<div class="form-group">
						<div class="col-sm-2">
							<form:label path="email" for="inputEmail" class="control-label">Email</form:label>
						</div>
						<div class="col-sm-10">
							<form:input path="email" type="email" class="form-control"
								id="inputEmail" placeholder="Email" />
							<form:errors path="email" />
						</div>
					</div>
					<div class="form-group">
						<div class="col-sm-2">
							<form:label path="naissance" class="control-label">Date de naissance</form:label>
						</div>
						<div class="col-sm-10">
							<div class="form-inline">
								<form:input id="bdField" path="naissance" type="date"
									name="bday" />
								<form:errors path="naissance" />
							</div>
						</div>
					</div>
					<div class="form-group">
						<div class="col-sm-2">
							<form:label path="groupe.id" class="control-label">Groupe choisi :</form:label>
						</div>
						<div class="col-sm-10">
							<div class="form-inline">
								<form:select path="groupe.id">
									<form:options items="${groupeList}" itemValue="id" itemLabel="name"/>
								</form:select>
							</div>
						</div>
					</div>
					</br>
					<div class="form-group">
						<div class="col-sm-2">
							<form:label path="diplome" class="control-label">Diplôme obtenu</form:label>
						</div>
						<div class="col-sm-4">
							<form:input path="diplome" type="text" class="form-control" />
							<form:errors path="diplome" />
						</div>

						<div class="col-sm-2">
							<form:label path="anneeDiplome" class="control-label">Année d'obtention</form:label>
						</div>
						<div class="col-sm-4">
							<form:input type="number" required="required" value="2015"
								path="anneeDiplome" class="form-control" />
							<form:errors path="anneeDiplome" />
						</div>
					</div>

					<div class="form-group">
						<div class="col-sm-offset-2 col-sm-10">
							<button type="submit" value="Add Etudiant"
								class="btn btn-default">
								Valider <br>
							</button>
						</div>
					</div>
				</form:form>
			</div>
		</div>
	</div>
</div>