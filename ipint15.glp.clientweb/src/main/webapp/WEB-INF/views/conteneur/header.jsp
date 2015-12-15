<%@page import="ipint15.glp.api.dto.EtudiantDTO"%>
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
	href="http://maxcdn.bootstrapcdn.com/bootstrap/3.3.5/css/bootstrap.min.css"
	rel="stylesheet" type="text/css">
<%
	EtudiantDTO etudiant;
	String deco = (String) request.getAttribute("deco");
	if ("deco".equals(deco)) {
		etudiant = null;
		session.setAttribute("etudiant", null);
	} else {
		etudiant = (EtudiantDTO) session.getAttribute("etudiant");
	}
%>
<div class="navbar navbar-default navbar-static-top">
	<div class="container">
		<div class="navbar-header">
			<button type="button" class="navbar-toggle" data-toggle="collapse"
				data-target="#navbar-ex-collapse">
				<span class="sr-only">Toggle navigation</span> <span
					class="icon-bar"></span> <span class="icon-bar"></span> <span
					class="icon-bar"></span>
			</button>
			<a class="navbar-brand" href="#">Annuaire des anciens de
				l'universit� de Lille<br>
			</a>
		</div>

		<div class="collapse navbar-collapse" id="navbar-ex-collapse">
			<ul class="nav navbar-nav navbar-right">
				<%
					if (etudiant == null) {
				%>
				<li class="active"><a
					href="${pageContext.request.contextPath}/">Accueil</a></li>
				<li><a href="${pageContext.request.contextPath}/connexion">Connexion<br></a>
				</li>
				<li><a href="${pageContext.request.contextPath}/inscription">Inscription<br></a>
				</li>
				<%
					} else {
				%>
					<!-- /.col-lg-5 -->
					<div class="col-lg-5">
						<div class="input-group">
							<input type="text" class="form-control"
								placeholder="Search for..."> <span
								class="input-group-btn">
								<a href="resultResearch" target="_blank"> <button class="btn btn-default" type="button">Go!</button></a>
							</span>
						</div>
						<!-- /input-group -->
					</div>
					<!-- /.col-lg-5 -->
				<!-- /.row -->
				<li class="active"><a
					href="${pageContext.request.contextPath}/fil-actualite">Fil
						d'actualit�</a></li>
				<li><a href="${pageContext.request.contextPath}/deconnection">D�connexion<br></a></li>
				<li><a href="${pageContext.request.contextPath}/profil">Profil<br></a></li>
				<%
					}
				%>
				<li><a href="${pageContext.request.contextPath}/contact">Contact<br></a>
				</li>
			</ul>
		</div>
	</div>
</div>
