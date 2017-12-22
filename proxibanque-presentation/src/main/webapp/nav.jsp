<!------------------------------------------------ NAVIGATION ------------------------------------------------>
<nav class="navbar navbar-inverse navbar-fixed-top" role="navigation">
	<!-- Brand and toggle get grouped for better mobile display -->
	<div class="navbar-header">
		<button type="button" class="navbar-toggle" data-toggle="collapse" data-target=".navbar-ex1-collapse">
			<span class="sr-only">Menu</span>
			<span class="icon-bar"></span>
			<span class="icon-bar"></span>
			<span class="icon-bar"></span>
		</button>
		<a class="navbar-brand" href="index.jsp"><i class="fa fa-fw fa-bank"></i>	PROXIBANQUE</a>
	</div>
	<!-- Top Menu Items -->
	<ul class="nav navbar-right top-nav">
		<c:if test = "${!empty listeClients}">
			<li class="dropdown">                    
				<a href="#" class="dropdown-toggle" data-toggle="dropdown">
					<i class="fa fa-fw fa-arrows-v"></i> Mes clients <b class="caret"></b>
				</a>
				<ul class="dropdown-menu">
					<c:forEach items="${listeClients}" var="client" >
						<li>
							<a href="ClientSelect?idClientSelect=${client.idClient}">
								<option value="${client.idClient}">${client.prenom} ${client.nom}</option>
							</a>
						</li>						
					</c:forEach>								
				</ul>							
			</li>					
		</c:if>
		<li class="dropdown">
			<a href="#" class="dropdown-toggle" data-toggle="dropdown">
				<i class="fa fa-user"></i> ${conseiller.prenom} ${conseiller.nom} <b class="caret"></b>
			</a>
			<ul class="dropdown-menu">
				<li>
					<a href="index.jsp"><i class="fa fa-fw fa-user"></i> Mon compte</a>
				</li> 
				<li class="divider"></li>
				<li>
					<a href="DeconnexionConseiller"><i class="fa fa-fw fa-power-off"></i> Se déconnecter</a>
				</li>                        
			</ul>
		</li>
	</ul>
	<!-- /Top Menu Items -->

	<!-- Sidebar Menu Items - These collapse to the responsive navigation menu on small screens -->
	<div class="collapse navbar-collapse navbar-ex1-collapse">
		<ul class="nav navbar-nav side-nav">

			<li class="divider">Edition des clients</li>
			<li>
				<a href="ListeClients"><!--class="active"--><i class="fa fa-fw fa-address-book"></i> Liste des clients</a>
			</li>
			<li>
				<a href="creationClient.jsp"><i class="fa fa-fw fa-edit"></i> Création d'un client</a>
			</li>
			<li>
				<a href="suppressionClient.jsp"><i class="fa fa-fw fa-trash-o"></i> Suppression un client</a>
			</li>

			<li class="divider">Comptes et Virements</li>
			<li>
				<a href="listeComptes.jsp"><i class="fa fa-fw fa-list-alt"></i> Liste des comptes</a>
			</li>
			<li>
				<a href="PreparerVirement"><i class="fa fa-fw fa-credit-card"></i> Virement compte à compte</a>
			</li>	
			<li>
				<a href="ListeComptesPourVirement"><i class="fa fa-fw fa-calculator"></i> Liste des virements</a>
			</li>

		</ul>
	</div>
	<!-- /.navbar-collapse -->
</nav>
<!------------------------------------------------ /NAVIGATION ------------------------------------------------>