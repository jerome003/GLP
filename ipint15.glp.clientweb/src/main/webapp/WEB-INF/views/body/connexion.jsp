<%@taglib uri="http://www.springframework.org/tags/form" prefix="form"%>
<div class="section">
	<div class="container">
		<div class="row">
			<div class="col-sm-offset-2 col-md-8">
				<form:form class="form-horizontal" role="form" method="post" commandName="command" action="connexionProfil">
					<div class="form-group">
						<div class="col-sm-2">
							<form:label path="email" for="inputEmail" class="control-label">Email</form:label>
						</div>
						<div class="col-sm-6">
							<form:input path="email" type="email" class="form-control" id="inputEmail"
								placeholder="Email"/>
							<form:errors path="email"/>	
						</div>
					</div>
					<div class="form-group">
						<div class="col-sm-2">
							<form:label  path="password"  class="control-label">Mot de passe</form:label>
						</div>
						<div class="col-sm-6">
							<form:input path="password" type="password" class="form-control"/>
							<form:errors path="password"/>
						</div>
					</div>

					<div class="form-group">
						<div class="col-sm-offset-2 col-sm-6">
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