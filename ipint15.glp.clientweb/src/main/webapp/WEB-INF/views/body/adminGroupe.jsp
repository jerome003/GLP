<div class="section">
	<div class="container">
		<div class="row">
			<div class="col-md-4">
				<h1>Création groupe</h1>
			</div>
		</div>
		<div class="row">
			<form method="post" action="saveGroupe">
				<div class="col-md-6">Nom du groupe : <input type="text" id="monGroupe" name="monGroupe"/> 
				</div>
				<div class="col-md-3">
					<input type="submit" value="Enregistrer" />
				</div>
			</form>
		</div>
		<div class="row">
			<div class="col-md-4">
				<h1>Liste des groupes</h1>
			</div>
		</div>
		<div class="row">
			<table class="table table-striped table-hover table-users">
    			<thead>
    				<tr>
    					<th>Nom du groupe</th>
    					<th></th>
    					<th></th>
    				</tr>
    			</thead>

    			<tbody>
    				
    				<tr>
    					<td>Miage </td>					
    					<td><a class="btn mini blue-stripe" href="${pageContext.request.contextPath}/admin/editGroupe/"><span class="glyphicon glyphicon-pencil"></span> Edit</a></td>
                        <td><a href="${pageContext.request.contextPath}/admin/removeGroupe/" class="confirm-delete btn mini red-stripe" role="button""><span class="glyphicon glyphicon-trash"></span> Delete</a></td>
                    </tr>
					<tr>
    					<td>Bio </td>					
    					<td><a class="btn mini blue-stripe" href="${pageContext.request.contextPath}/admin/editGroupe/"><span class="glyphicon glyphicon-pencil"></span> Edit</a></td>
                        <td><a href="${pageContext.request.contextPath}/admin/removeGroupe/" class="confirm-delete btn mini red-stripe" role="button""><span class="glyphicon glyphicon-trash"></span> Delete</a></td>
                    </tr>
                    <tr>
    					<td>Informatique</td>					
    					<td><a class="btn mini blue-stripe" href="${pageContext.request.contextPath}/admin/editGroupe/"><span class="glyphicon glyphicon-pencil"></span> Edit</a></td>
                        <td><a href="${pageContext.request.contextPath}/admin/removeGroupe/" class="confirm-delete btn mini red-stripe" role="button""><span class="glyphicon glyphicon-trash"></span> Delete</a></td>
                    </tr>
                
	               </tbody>

    		</table>
		</div>
	</div>
</div>