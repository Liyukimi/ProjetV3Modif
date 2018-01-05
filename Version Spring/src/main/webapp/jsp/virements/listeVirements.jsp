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
					<i class="fa fa-credit-card"></i> Liste des virements d'un compte
				</h1>
				<ol class="breadcrumb">
					<li>
						<i class="fa fa-dashboard"></i>  <a href="index.jsp">Tableau de bord</a>
					</li>
					<li class="active">
						<i class="fa fa-credit-card"></i> Liste des virements d'un compte
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
					<legend>Choisissez le compte d'un de vos clients dont vous souhaitez afficher la liste des virements</legend>

					<form class="form" method="post" action="ListeVirements">
						<div class="form-group">
							<label  for="numCompte">Compte  :</label>
							<c:if test = "${!empty ListeComptesClients}">
								<select class="form-control" required="required" id="numCompte" name="numCompte">
									<c:forEach items="${ListeComptesClients}" var="compte" >
										<option  name="numCompte" required="required" value="${compte.numeroCompte}">
											n°${compte.numeroCompte} - Client : ${compte.client.prenom} ${compte.client.nom}	
										</option>
									</c:forEach>
								</select>
							</c:if>
							<c:if test = "${empty ListeComptesClients}">
								Vous n'avez pas de client ayant un compte !
								<select class="form-control" required="required"></select>
							</c:if>	
						</div>
						<input type="hidden" name="login" value=${conseiller.login}>
						<input type=submit class="btn btn-primary" value="Afficher les virements"></input>
					</form>
				</fieldset>
				</br></br>               

				<c:if test = "${!empty ListeDebits}">		
					<legend>Ensemble des opérations débitant le compte ${numCompteSelect}</legend>
					<table class="table-striped">                            
						<thead>
							<tr>
								<th>Date du virement</th>
								<th>Compte Créditeur</th>
								<th>Client</th>
								<th>Montant</th>
							</tr>
						</thead> 
						<tbody>
							<c:forEach items="${ListeDebits}" var="debit">
								<tr>
									<td>${debit.dateVirement}</td>
									<td>${debit.compteCrediteur.numeroCompte}</td>
									<td>${debit.compteCrediteur.client.prenom} ${debit.compteCrediteur.client.nom}</td>
									<td>${debit.montant}</td>
								</tr>    
							</c:forEach>
						</tbody>
					</table>
				</c:if>
				<c:if test = "${!empty ListeCredits}">		
					<legend>Ensemble des opérations créditant le compte ${numCompteSelect}</legend>
					<table class="table-striped">                            
						<thead>
							<tr>
								<th>Date du virement</th>
								<th>Compte débiteur</th>
								<th>Client</th>
								<th>Montant</th>
							</tr>
						</thead> 
						<tbody>
							<c:forEach items="${ListeCredits}" var="credit">
								<tr>
									<td>${credit.dateVirement}</td>
									<td>${credit.compteDebiteur.numeroCompte}</td>
									<td>${credit.compteDebiteur.client.prenom} ${credit.compteDebiteur.client.nom}</td>
									<td>${credit.montant}</td>
								</tr>    
							</c:forEach>
						</tbody>
					</table>						
				</c:if>
			</div>
		</div>
		<!------------------------------ FIN CONTENU PRINCIPAL ------------------------------>
	</div>
	<!-- /.container-fluid -->
</div>
<!------------------------------------------------ FIN DE LA PAGE PRINCIPALE ------------------------------------------------>
<%@include file="../includes/footer.jsp" %>  