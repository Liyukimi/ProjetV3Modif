window.onload = function ()
{
	var btnAction = document.getElementById("validSupp");
	btnAction.onmouseover = afficheSupp;
	btnAction.onmouseout = effaceSupp;
	btnAction.onclick = validerSuppr;	
}

function validerSuppr()
{
	if (confirm('La suppression est definitive. Etes vous sur de vouloir supprimer le client?'))
		return true;
	else
		return false;
}

function afficheSupp()
{
	document.getElementById("validSupp").innerHTML = "Confirmer la suppression?"	
}

function effaceSupp()
{
	document.getElementById("validSupp").innerHTML = "Supprimer le client"	
}

