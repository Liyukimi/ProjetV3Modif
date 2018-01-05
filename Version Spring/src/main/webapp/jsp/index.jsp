<%@include file="includes/header.jsp" %>
<%@include file="includes/nav.jsp" %>
<!------------------------------------------------ DEBUIT DE LA PAGE PRINCIPALE ------------------------------------------------>
<div id="page-wrapper">
	<div class="container-fluid">
		<!------------------------------ CONTENU HEADING ------------------------------>
		<!-- Page Heading -->
		<div class="row">
			<div class="col-lg-12">
				<h1 class="page-header">
					<i class="fa fa-dashboard"></i> Bienvenue ${conseiller.prenom} ${conseiller.nom}	
				</h1>
				<ol class="breadcrumb">
					<li>
						<i class="fa fa-dashboard"></i>  <a href="index.jsp">Tableau de bord</a>
					</li>
				</ol>
			</div>
		</div>
		<!-- /Page heading -->
		<!------------------------------ FIN CONTENU HEADING ------------------------------>
		<!------------------------------ CONTENU PRINCIPAL ------------------------------>             
		<div class="row">
			<div class="col-lg-12">
				<%@include file="includes/etatRequete.jsp" %>

				<div class="panel panel-primary">
					<div class="panel-heading">
						<div class="row">
							<div class="col-xs-3">
								<i class="fa fa-comments fa-5x"></i>
							</div>
							<div class="col-xs-9 text-right">
								<div class="huge">Bonjour !</div>
								<div>
									<strong>Nom :</strong> ${conseiller.nom}
									<strong>Prénom :</strong> ${conseiller.prenom}
									<strong>Login :</strong> ${conseiller.login}											
								</div>
							</div>
						</div>
					</div>                 
				</div>
			</div>
		</div>

		<div class="row">
			<div class="col-lg-12">
				<div class="panel panel-default">
					<div class="panel-heading">
						<h3 class="panel-title"><i class="fa fa-address-book fa-fw"></i> Mes clients</h3>
					</div>
					<div class="panel-body">
						<c:if test = "${!empty listeClients}">                                
							<div class="col-xs-6">
								<table class="table table-striped">						
									<thead>
										<tr>
											<th>Nom</th>
											<th>Prénom</th>
										</tr>
									</thead> 
									<tbody>							
										<c:forEach items="${listeClients}" var="client" >
											<tr>
												<td>${client.nom}</td>
												<td>${client.prenom}</td>									
											</tr>
										</c:forEach>								
									</tbody>				
                                </table>
							</div>
						</c:if>
						<c:if test = "${empty listeClients}">
							Vous n'avez encore aucun clients
						</c:if>
					</div>
					<div class="panel-footer">
						<h5><span class="pull-right"><a href="ListeClients">Détail <i class="fa fa-arrow-circle-right"></i></a></span></h5></br>
						<!-- <div class="clearfix"></div> -->                                    
					</div> 
				</div>
			</div>
		</div>
		<!-- /.row -->
		<!------------------------------ FIN CONTENU PRINCIPAL ------------------------------>
    </div>
	<!-- /.container-fluid -->
</div>
<!------------------------------------------------ FIN DE LA PAGE PRINCIPALE ------------------------------------------------>
<%@include file="includes/footer.jsp" %>     
