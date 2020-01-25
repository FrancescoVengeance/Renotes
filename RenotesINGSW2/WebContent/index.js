function moduloRegistrazione()
{
    //document.getElementById("form-registrazione").removeAttribute("action");
    document.getElementById("form-registrazione").setAttribute("action", "RegisterServlet");
	
    document.getElementById("campiInput").innerHTML+="<div class=\"md-form\">" +
	 												 "<i class=\"fas fa-key prefix grey-text\"></i>" +
	 												 "<input type=\"password\" class=\"form-control\" id=\"confPass\" name=\"conferma-password\">" +
	 												 "<label for=\"form3\">Conferma password</label>" +
	 												 "</div>";

    document.getElementById("campiInput").innerHTML+="<div class=\"md-form\">" +
		 											 "<i class=\"fas fa-user prefix grey-text\"></i>" +
		 											 "<input type=\"text\" class=\"form-control\" id=\"username\" name=\"username-registrazione\">" +
		 											 "<label for=\"form3\">Username</label>" +
		 											 "</div>";
	
    document.getElementById("divisore-mail").innerHTML="<div class=\"md-form\">" +
    												   "<i class=\"fas fa-envelope prefix grey-text\">" +
	   												   "</i><input type=\"text\" class=\"form-control\" id=\"mail-registrazione\" name=\"mail-registrazione\">" +
	   												   "<label for=\"form3\">Email</label>" +
	   												   "</div>";

    document.getElementById("divisore-password").innerHTML="<div class=\"md-form\">" +
    													   "<i class=\"fas fa-key prefix grey-text\">" +
		   												   "</i><input type=\"password\" class=\"form-control\" id=\"password-registrazione\" name=\"password-registrazione\">" +
		   												   "<label for=\"form2\">Password</label>" +
		   												   "</div>";
	

    

    
    document.getElementById("scritta-superiore").innerHTML="<h3 class=\"dark-grey-text text-center\" id=\"scritta-superiore\"> <strong>Registrati</strong></h3>";
    
    document.getElementById("registrati-ora").disabled = true;
    document.getElementById('registrati-ora').style.visibility = 'hidden';
    
    document.getElementById("divisore-invia").innerHTML="<button class=\"btn btn-brown\" id=\"inviaButton\" type=\"submit\">invia</button>";

}

function accedi()
{
	/*var bottoneRegistra = document.getElementById("inviaButton");
	var mail = $("#mail-accessp").val();
	$.ajax({
		url : "LoginServlet",
        type : "POST",
        async : true,
        contentType : "application/json",
        data : JSON.stringify(mail),
        success : function(data)
        {
            alert("messaggio ricevuto");
        },
        error : function()
        {
            alert("Errore"); 
        }
	});*/
}
function registrati() {alert("effettuo la registrazione")}
function cerca() {alert("cerco le inserzioni");}

/*var bottoneInvio = document.getElementById("bottoneInvio");
bottoneInvio.onclick = function()
{
    var testo = $("#testo").val();
    $.ajax({
        url : "OperatorChatServlet",
        type : "POST",
        async : true,
        contentType : "application/json",
        data : JSON.stringify(testo),
        success : function(data)
        {
            var listaMessaggi = document.getElementById("chatContent");
            var paragrafo = document.createElement("p");
            var messaggio = document.createTextNode(data);
            paragrafo.appendChild(messaggio);
            listaMessaggi.appendChild(paragrafo);
        },
        error : function()
        {
            alert("Errore"); 
        }
    });
}*/