<%@taglib uri="http://www.springframework.org/tags/form" prefix="form"%>
<div class="section">
	<div class="container">
		<div class="row">
			<div class="col-md-12">
				<form:form class="form-horizontal" role="form" method="post" commandName="command" action="addEtudiant">
					<%-- <div class="form-group">
						<div class="col-sm-2">
							<label class="control-label">Civilité</label>
						</div>
						  <div class="col-sm-10">
							<div class="radio">
								<form:label path="toto" class="radio-inline"> <input type="radio">Mme
								</form:label> <label class="radio-inline"> <input type="radio">M.
								</label>
							</div>
						</div>
					</div> --%>
					<div class="form-group">
						<div class="col-sm-2">
							<form:label path="nom" class="control-label">Nom</form:label>
						</div>
						<div class="col-sm-10">
							<form:input path="nom" type="text" class="form-control"/>
						</div>
					</div>
					<div class="form-group">
						<div class="col-sm-2">
							<form:label  path="prenom"  class="control-label">Prénom</form:label>
						</div>
						<div class="col-sm-10">
							<form:input path="prenom" type="text" class="form-control"/>
						</div>
					</div>
					<div class="form-group">
						<div class="col-sm-2">
							<form:label path="email" for="inputEmail" class="control-label">Email</form:label>
						</div>
						<div class="col-sm-10">
							<form:input path="email" type="email" class="form-control" id="inputEmail"
								placeholder="Email"/>
						</div>
					</div>
					<div class="form-group">
						<div class="col-sm-2">
							<form:label path="naissance" class="control-label">Date d'anniversaire</form:label>
						</div>
						<div class="col-sm-10">
							<div class="form-inline">
									<input type="date" name="bday">
							</div>
						</div>
					</div>

					<div class="form-group">
						<div class="col-sm-offset-2 col-sm-10">
							<button type="submit" value="Add Etudiant" class="btn btn-default">
								Valider <br>
							</button>
						</div>
					</div>
				</form:form>
			</div>
		</div>
	</div>
</div>