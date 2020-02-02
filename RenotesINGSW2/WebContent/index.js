function moduloRegistrazione()
{
    //document.getElementById("form-registrazione").removeAttribute("action");
    document.getElementById("form-registrazione").setAttribute("action", "RegisterServlet");
	
    document.getElementById("campiInput").innerHTML+="<div class=\"md-form\">" +
	 												 "<i class=\"fas fa-key prefix grey-text\"></i>" +
	 												 "<input type=\"password\" class=\"form-control\" id=\"confPass\" name=\"conferma-password\" required>" +
	 												 "<label for=\"form3\">Conferma password</label>" +
	 												 "</div>";

    document.getElementById("campiInput").innerHTML+="<div class=\"md-form\">" +
		 											 "<i class=\"fas fa-user prefix grey-text\"></i>" +
		 											 "<input type=\"text\" class=\"form-control\" id=\"username\" name=\"username-registrazione\" required>" +
		 											 "<label for=\"form3\">Username</label>" +
		 											 "</div>";
	
    document.getElementById("divisore-mail").innerHTML="<div class=\"md-form\">" +
    												   "<i class=\"fas fa-envelope prefix grey-text\">" +
	   												   "</i><input type=\"email\" class=\"form-control\" id=\"mail-registrazione\" name=\"mail-registrazione\" required>" +
	   												   "<label for=\"form3\">Email</label>" +
	   												   "</div>";

    document.getElementById("divisore-password").innerHTML="<div class=\"md-form\">" +
    													   "<i class=\"fas fa-key prefix grey-text\">" +
		   												   "</i><input type=\"password\" class=\"form-control\" id=\"password-registrazione\" name=\"password-registrazione\" required>" +
		   												   "<label for=\"form2\">Password</label>" +
		   												   "</div>";
	

    

    
    document.getElementById("scritta-superiore").innerHTML="<h3 class=\"dark-grey-text text-center\" id=\"scritta-superiore\"> <strong>Registrati</strong></h3>";
    
    document.getElementById("divisore-tasti").innerHTML="<div class=\"text-center\">" +
														"<button class=\"btn btn-brown\" id=\"inviaButton\" type=\"submit\">invia</button>" +
														"<pre></pre>" +
														"<button class=\"btn alert-secondary\" id=\"accedi-ora\" onClick=\"window.location.reload()\">Hai un account? Accedi</button>" +
														"</div>";

}

if(sessionStorage.getItem("is_reloaded"))
{
    console.log("ricaricata");
}

function validate() {
    var sValue = document.getElementById("password-registrazione").value;
    var pattern = /^(?=.*[0-9]+.*)(?=.*[a-zA-Z.!@#$%^&*()_+-=]+.*)[0-9a-zA-Z.!@#$%^&*()_+-=]{8,}$/;
    if (pattern.test(sValue)) {
        return true;
    }
        alert("Input except Number & Special Characters only !");
        return false;

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