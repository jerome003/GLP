<%@taglib uri="http://www.springframework.org/tags/form" prefix="form"%>
<%@page import="ipint15.glp.api.dto.AncienEtudiantDTO"%>
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

<link rel="stylesheet"
	href="${pageContext.request.contextPath}/resources/css/moncss.css" />
<!-- libraries pour les alertes  -->
<script
	src="${pageContext.request.contextPath}/resources/js/alertify.js"></script>
<link rel="stylesheet"
	href="${pageContext.request.contextPath}/resources/css/alertify.core.css" />
<!-- include a theme, can be included into the core instead of 2 separate files -->
<link rel="stylesheet"
	href="${pageContext.request.contextPath}/resources/css/alertify.default.css" />

<link
	href="http://cdnjs.cloudflare.com/ajax/libs/font-awesome/4.3.0/css/font-awesome.min.css"
	rel="stylesheet" type="text/css">
<link
	href="http://maxcdn.bootstrapcdn.com/bootstrap/3.3.5/css/bootstrap.min.css"
	rel="stylesheet" type="text/css">
<link href="${pageContext.request.contextPath}/resources/css/moncss.css" rel="stylesheet" >
<!-- libraries pour les alertes  -->
<script
	src="${pageContext.request.contextPath}/resources/js/alertify.js"></script>
<link rel="stylesheet"
	href="${pageContext.request.contextPath}/resources/css/alertify.core.css" />
<!-- include a theme, can be included into the core instead of 2 separate files -->
<link rel="stylesheet"
	href="${pageContext.request.contextPath}/resources/css/alertify.default.css" />
<!-- Begin page content -->

	
	  
<div class="navbar navbar-default">
	
		<div class="navbar-header">
			<button type="button" class="navbar-toggle" data-toggle="collapse"
				data-target="#navbar-ex-collapse">
				<span class="sr-only">Toggle navigation</span> <span
					class="icon-bar"></span> <span class="icon-bar"></span> <span
					class="icon-bar"></span>
			</button>
			<a class="navbar-brand" href="#">
				Cité Scientifique 59655 Villeneuve d'Ascq Cedex Tél. +33 (0) 3.20.43.43.43 <br>
			</a>
		</div>


		<div class="collapse navbar-collapse" id="navbar-ex-collapse">
			<ul class="nav navbar-nav navbar-right">
			<li><a class="btn mini blue-stripe"
				href="${pageContext.request.contextPath}/connexionAdmin">Connexion
					Admin</a></li>
			<li><a class="btn mini blue-stripe"
				href="${pageContext.request.contextPath}/connexionModerateur">Connexion
					Moderateur</a></li>
			</ul>

	</div>

	
	
<!--
	
	<button type="button" class="navbar-toggle" data-toggle="collapse"
				data-target="#navbar-ex-collapse">
				<span class="sr-only">Toggle navigation</span> <span
					class="icon-bar"></span> <span class="icon-bar"></span> <span
					class="icon-bar"></span>
	</button>
	<a class="navbar-brand" href="#">
	Cité Scientifique 59655 Villeneuve d'Ascq Cedex Tél. +33 (0) 3.20.43.43.43 
	</a>
	
	
	
	
	
	
<div class="navbar navbar-default navbar-static-top"> 
 
	<div class="collapse navbar-collapse" id="navbar-ex-collapse">
		<ul class="nav navbar-nav navbar-right">
			<li><a class="btn mini blue-stripe"
				href="${pageContext.request.contextPath}/connexionAdmin">Connexion
					Admin</a></li>
			<li><a class="btn mini blue-stripe"
				href="${pageContext.request.contextPath}/connexionModerateur">Connexion
					Moderateur</a></li>
		</ul>



	</div>
</div>	
  -->	
</div>
