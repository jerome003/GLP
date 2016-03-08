<%@page import="java.util.List"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@taglib uri="http://www.springframework.org/tags/form" prefix="form"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<%@page import="ipint15.glp.api.dto.AncienEtudiantDTO"%>

<div class="row">
	<div class="col-md-10">
		<form:form class="form-horizontal" role="form" method="post"
			commandName="command" action="addNewEtudiant">
			<div class="form-group">
				<div class="col-sm-5">
					<div class="home_box well well-lg">

						<h2>
							Inscription<br>
						</h2>
						<div class="form-group">
							<div class="col-sm-2">
								<form:label path="groupe.id" class="control-label">Groupe choisi :</form:label>
							</div>
							<div class="col-sm-10">
								<div class="form-inline">
									<form:select path="groupe.id">
										<form:options items="${groupeList}" itemValue="id"
											itemLabel="name" />
									</form:select>
								</div>
							</div>
						</div>
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