<%@include file="../includes/header.jsp" %>
<%@include file="../includes/nav.jsp" %>
<!------------------------------------------------ DEBUIT DE LA PAGE PRINCIPALE ------------------------------------------------>
<div id="page-wrapper">
	<div class="container-fluid">
		<!------------------------------ CONTENU HEADING ------------------------------>
		<!-- Page Heading -->
		<div class="row">
			<div class="col-lg-12">
				<h1 class="page-header">
					<i class="fa fa-address-card"></i> Client <c:out value="${client.prenom} ${client.nom}"/>
				</h1>
				<ol class="breadcrumb">
					<li>
						<i class="fa fa-dashboard"></i>  <a href="index.jsp">Tableau de bord</a>
					</li>
					<li class="active">
						<i class="fa fa-address-card"></i> Client <c:out value="${client.prenom} ${client.nom}"/>
					</li>
				</ol>
			</div>
		</div>
		<!-- /Page heading -->
		<!------------------------------ FIN CONTENU HEADING ------------------------------>
		<!------------------------------ CONTENU PRINCIPAL ------------------------------>             
		<div class="row">
			<div class="col-lg-12"> 						
				<%@include file="../includes/etatRequete.jsp" %>
				<ul>
					<li><strong>Nom :</strong> <c:out value="${client.nom}"/></li>
					<li><strong>Prénom :</strong> <c:out value="${client.prenom}"/></li>
					<li><strong>Adressee :</strong> <c:out value="${client.adresse}" /></li>
					<li><strong>Code Postal :</strong> <c:out value="${client.codePostal}"/> </li>
					<li><strong>Ville :</strong> <c:out value="${client.ville}" /></li>
					<li><strong>Téléphone :</strong> <c:out value="${client.telephone}" /></li>
					<li><strong>email :</strong> <c:out value="${client.mail}"/></li>
				</ul>						
			</div>	
		</div>

		<div class="row">
			<div class="col-auto">
				<form action="ClientAction" method="Post">
					<div class="text-center">
						<input type="hidden" name="idClientSelect" id="${client.idClient}" value="${client.idClient}">
						<button type=submit class="btn btn-primary" name="action" value="ModifClient">Modifier le client</button>

						<button type=submit class="btn btn-primary" name="action" value="ListeComptes">Lister les comptes du client</button>							
					</div>
				</form>
			</div>					
		</div>
		<!-- /.row -->

		<!------------------------------ FIN CONTENU PRINCIPAL ------------------------------>
	</div>
	<!-- /.container-fluid -->
</div>
<!------------------------------------------------ FIN DE LA PAGE PRINCIPALE ------------------------------------------------>
<%@include file="../includes/footer.jsp" %>     
