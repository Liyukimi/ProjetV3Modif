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
					Page courante
				</h1>
				<ol class="breadcrumb">
					<li>
						<i class="fa fa-dashboard"></i>  <a href="index.jsp">Tableau de bord</a>
					</li>
					<li class="active">
						<i class="fa fa-bar-chart-o"></i> Page courante
					</li>
				</ol>
			</div>
		</div>
		<!-- /Page heading -->
		<!------------------------------ FIN CONTENU HEADING ------------------------------>
		<!------------------------------ CONTENU PRINCIPAL ------------------------------>             
		<div class="row">
			<%@include file="etatRequete.jsp" %>
		</div>
		<!-- /.row -->

		<!------------------------------ FIN CONTENU PRINCIPAL ------------------------------>
	</div>
	<!-- /.container-fluid -->
</div>
<!------------------------------------------------ FIN DE LA PAGE PRINCIPALE ------------------------------------------------>
<%@include file="footer.jsp" %>        
