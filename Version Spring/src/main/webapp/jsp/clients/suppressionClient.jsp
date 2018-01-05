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
					<i class="fa fa-list-trash-o"></i> Suppression d'un client
				</h1>
				<ol class="breadcrumb">
					<li>
						<i class="fa fa-dashboard"></i>  <a href="index.jsp">Tableau de bord</a>
					</li>
					<li class="active">
						<i class="fa fa-list-trash-o"></i> Suppression d'un client
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

				<fieldset>
					<legend>Sélectionner un de vos clients dans la liste ci-dessous pour le supprimer</legend>
					<form class="form" method="post" action="SuppressionClient">
						<div class="form-group">
							<label  for="IdClient">Choisissez votre client :</label>
							<c:if test = "${!empty listeClients}">
								<select class="form-control" required="required" id="IdClient" name="idClientSelect">
									<c:forEach items="${listeClients}" var="client" >
										<option  name="idClientSelect" value="${client.idClient}">${client.prenom} ${client.nom}</option>
									</c:forEach>
								</select>
							</c:if>
							<c:if test = "${empty listeClients}">
								Vous n'avez pas de client !
							</c:if>									    
						</div>
						<button id="validSupp" type=submit value="Ok" class="btn btn-primary">Supprimer le client</button>	   
					</form>
				</fieldset>

			</div>
		</div>
		<!-- /.row -->

		<!------------------------------ FIN CONTENU PRINCIPAL ------------------------------>
    </div>
	<!-- /.container-fluid -->
</div>
<!------------------------------------------------ FIN DE LA PAGE PRINCIPALE ------------------------------------------------>
<%@include file="../includes/footer.jsp" %>   