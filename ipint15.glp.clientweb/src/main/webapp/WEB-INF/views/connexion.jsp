<%@taglib uri="http://www.springframework.org/tags/form" prefix="form"%>
<div class="section">
	<div class="container">
		<div class="row">
			<div class="col-md-12">
				<form:form class="form-horizontal" role="form" method="post" commandName="command" action="connexionProfil">
					<div class="form-group">
						<div class="col-sm-2">
							<form:label path="email" for="inputEmail3" class="control-label">Email</form:label>
						</div>
						<div class="col-sm-10">
							<form:input path="email" type="email" class="form-control" id="inputEmail3"
								placeholder="Email"/>
						</div>
					</div>
					<div class="form-group">
						<div class="col-sm-2">
							<form:label  path="password"  class="control-label">Mot de passe</form:label>
						</div>
						<div class="col-sm-10">
							<form:input path="password" type="text" class="form-control"/>
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