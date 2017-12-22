<%@include file="header.jsp" %>
<%@include file="nav.jsp" %>
<!------------------------------------------------ DEBUIT DE LA PAGE PRINCIPALE ------------------------------------------------>
<div id="page-wrapper">
	<div class="container-fluid">
		<!------------------------------ CONTENU HEADING ------------------------------>
		<!-- Page Heading -->
		<div class="row">
			<div class="col-lg-12">
				<h1 class="page-header">
					<i class="fa fa-address-book"></i> Liste des clients
				</h1>
				<ol class="breadcrumb">
					<li>
						<i class="fa fa-dashboard"></i>  <a href="index.jsp">Tableau de bord</a>
					</li>
					<li class="active">
						<i class="fa fa-address-book"></i> Liste des clients
					</li>
				</ol>
			</div>
		</div>
		<!-- /Page heading -->
		<!------------------------------ FIN CONTENU HEADING ------------------------------>
		<!------------------------------ CONTENU PRINCIPAL ------------------------------>             
		<div class="row">
			<div class="col-lg-12">
				<%@include file="etatRequete.jsp" %>

				<c:if test = "${!empty listeClients}">
					<form action="ClientAction" method="Post">
						<table class="table table-striped">						
							<thead>
								<tr>
									<th>Choix</th>
									<th>Nom</th>
									<th>Prénom</th>
									<th>Adressee</th>
									<th>Code Postal</th>
									<th>Ville</th>
									<th>Téléphone</th>
									<th>eMail</th>
								</tr>
							</thead> 
							<tbody>							
								<c:forEach items="${listeClients}" var="client" >
									<tr>
										<td><input class="form-check-input" type="radio" name="idClientSelect" id="${client.idClient}" value="${client.idClient}"></td>
										<td>${client.nom}</td>
										<td>${client.prenom}</td>									
										<td>${client.adresse}</td>
										<td>${client.codePostal}</td>
										<td>${client.ville}</td>
										<td>${client.telephone}</td>
										<td>${client.mail}</td>							
									</tr>
								</c:forEach>								
							</tbody>				
						</table>	

						<div class="row">
							<div class="col-auto">
								<div class="text-center">
									<button type=submit class="btn btn-primary" name="action" value="ClientSelect">Voir les informations du client</button>

									<button type=submit class="btn btn-primary" name="action" value="ModifClient">Modifier le client</button>

									<button type=submit class="btn btn-primary" name="action" value="ListeComptes">Lister les comptes du client</button>
								</div>
							</div>
						</div>
					</form>
				</c:if>											
			</div>
		</div>
		<!-- /.row -->

		<!------------------------------ FIN CONTENU PRINCIPAL ------------------------------>
	</div>
	<!-- /.container-fluid -->
</div>
<!------------------------------------------------ FIN DE LA PAGE PRINCIPALE ------------------------------------------------>
<%@include file="footer.jsp" %>     
