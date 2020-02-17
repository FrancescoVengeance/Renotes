<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding ="ISO-8859-1" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<!DOCTYPE html>
<html lang="it">

<head>
  <meta charset="utf-8">
  <meta name="viewport" content="width=device-width, initial-scale=1, shrink-to-fit=no">
  <meta http-equiv="x-ua-compatible" content="ie=edge">
  <title>Renotes login</title>
  <!-- Font Awesome -->
  <link rel="stylesheet" href="https://use.fontawesome.com/releases/v5.11.2/css/all.css">
  <meta name="google-signin-client_id" content="263763617027-i5vlk7ff7c7snr3r4iig9q31be5acm34.apps.googleusercontent.com">
  <script src="https://apis.google.com/js/platform.js" async defer></script>
  <!-- Bootstrap core CSS -->
  <link href="css/bootstrap.min.css" rel="stylesheet">
  <!-- Material Design Bootstrap -->
  <link href="css/mdb.min.css" rel="stylesheet">
  
  <!-- Your custom styles (optional) -->
  <link href="css/style.min.css" rel="stylesheet" >

  <style type="text/css">
    html,
    body,
    header,
    .view {
      height: 100%;
    }

    @media (max-width: 740px) {
      html,
      body,
      header,
      .view {
        height: 1000px;
      }
    }

    @media (min-width: 800px) and (max-width: 850px) {
      html,
      body,
      header,
      .view {
        height: 650px;
      }
    }
    @media (min-width: 800px) and (max-width: 850px) {
              .navbar:not(.top-nav-collapse) {
                  background: #1C2331!important;
              }
          }
    .tasto{
      background-color: #ffffff;
      border:none;
    }
    #centered-button {
   	margin-left: 35%;
   }
  </style>
</head>

<body>

  <!-- Navbar -->  <!-- Navbar -->

  <!-- Full Page Intro -->
  <div class="view full-page-intro" style="background-image: url('resources/background1.jpg'); background-repeat: no-repeat; background-size: cover;">

    <!-- Mask & flexbox options-->
    <div class="mask rgba-black-light d-flex justify-content-center align-items-center">

      <!-- Content -->
      <div class="container">

        <!--Grid row-->
        <div class="row wow fadeIn">
          
          <!--Grid column-->
          <div class="col-md-6 mb-4 white-text text-center text-md-left">
			  
            <img src= "resources/renotes_logo.png" width="550" height="160" class="img-fluid z-depth-0-half">

			      <div class="card mt-5">
				        <div class="md-form mt-2 mb-2">
          			<button class = "fas grey-text fa-search prefix tasto" onClick="cerca()"></button>
          			<input type="text" class="form-control">
       			  <label for="form3">Cerca anche senza registrarti!</label>
   		 	    </div>
			      </div>  
		      </div>
          <!--Grid column-->

          <!--Grid column-->
          <div class="col-md-6 col-xl-5 mb-4">

            <!--Card-->
            <div class="card">

              <!--Card content-->
              <form class="card-body" id="form-registrazione" action="LoginSevlet" method="post">
                            <h3 class="dark-grey-text text-center" id="scritta-superiore"> <strong>Accedi</strong></h3>
                            <hr>
                            <h6 style="color: red;">${scrittaErrore}</h6>
                  <div id="campiInput">
                    <div id="divisore-mail">
                              <div class="md-form">
                                <i class="fas fa-envelope prefix grey-text"></i>
                                <input type="email" class="form-control" id="mail-accesso" name="mail-accesso" required>
                                <label for="form3" id="mail-label">Email</label>
                              </div>
                            </div>
                            <div id="divisore-password">
                              <div class="md-form">
                                <i class="fas fa-key prefix grey-text"></i>
                                <input type="password" class="form-control" id="password-accesso" name="password-accesso" required>
                                <label for="form2">Password</label>
                              </div>
                            </div>
                          </div>
                   <div id="divisore-tasti">
                    <div class="text-center">
                      <button class="btn btn-brown" id="inviaButton"  type="submit">invia</button>                
                      <pre></pre>
                      <button class="btn alert-secondary" id="registrati-ora" onClick="moduloRegistrazione()">Non hai un account? Registrati</button>
                      <pre></pre> 	
				  	  <div class="g-signin2" id="centered-button" data-onsuccess="onSignIn"></div>
				  	  <pre></pre>
				  	  <div class="fb-login-button" data-size="medium" data-button-type="continue_with" data-auto-logout-link="false" data-use-continue-as="false"></div>
                    </div>
                   </div>

              </form>
                
              <!--form action="LoginSevlet" method="POST" class="card-body">
                
                <h3 class="dark-grey-text text-center" id="scritta-superiore"> <strong>Accedi</strong></h3>
                <hr>
                
                <div>
                  <input type="text" class="form-control" name="mail-accesso" placeholder="Inserisci mail">
                  <label></label>
                  <input type="password" class="form-control" name="password-accesso" placeholder="Inserisci password">
                </div>
                <div>
                  <button class="btn btn-brown" id="inviaButton"  type="submit">invia</button>
                  <button class="btn alert-secondary" id="registrati-ora" onClick="moduloRegistrazione()">Non hai un account? Registrati</button>
                </div>

              </form>-->
			
            </div>
            <!--/.Card-->

          </div>
          <!--Grid column-->

        </div>
        <!--Grid row-->

      </div>
      <!-- Content -->

    </div>
    <!-- Mask & flexbox options-->

  </div>
  <!-- Full Page Intro -->

  <!--Main layout-->
  <main>
    <div class="container">

      <!--Section: Main info-->
      <section class="mt-5 wow fadeIn">

        <!--Grid row-->
        <div class="row">

          <!--Grid column-->
          <div class="col-md-6 mb-4">

            <img src="resources/presentation1.jpg" class="img-fluid z-depth-1-half" alt="">

          </div>
			
          <div class="col-md-6 mb-4">

            <h3 class="h3 mb-3">Perché usare ReNotes?</h3>
            <p>Se sei anche tu uno studente che ha frequentato la cittadella Universitaria, i gruppi Facebook della tua facoltà e via dicendo, sarai sicuramente d’accordo sul fatto che ogni occasione è buona per incontrare qualcuno pronto a condividere appunti, riassunti o addirittura video corsi. </p>


            <hr>

          </div>
        </div>


      </section>
		
	<section class="mt-5 wow fadeIn">

        <!--Grid row-->
        <div class="row">

          <!--Grid column-->

			
          <div class="col-md-6 mb-4">

            <p>
				ReNotes è la piattaforma adatta alle tue esigenze. Non gettare via i tuoi appunti! Riscrivili in digitale o scannerizza le tue pagine. Attenzione a fare un buon lavoro però, i tuoi acquirenti avranno la possibilità di valutarli! 😄
			</p>


            <hr>

          </div>
			
		 <div class="col-md-6 mb-4">

           <img src="resources/presentation2.jpg" class="img-fluid z-depth-1-half" alt="">

          </div>
        </div>


      </section>
<!--Section: Main features & Quick Start-->
      <section>

		  
        <!--Grid row-->
        <div class="row wow fadeIn">
          <div class="col-lg-6 col-md-12">
              <img src="resources/presentation3.png" class="img-fluid z-depth-1-half" alt="">
          </div>
          <!--Grid column-->
          <div class="col-lg-6 col-md-12 px-4">

            <!--First row-->
            <div class="row">
				
              <div class="col-1 mr-3">
                <i class="fas fa-check-circle fa-2x indigo-text"></i>
              </div>
              <div class="col-10">
                <h5 class="feature-title"><strong>RE</strong>USE</h5>
                <p class="grey-text">Riutilizza i tuoi appunti. Magari non serviranno a te, ma potranno far comodo a molti altri studenti universitari!</p>
              </div>
            </div>
            <!--/First row-->

            <div style="height:30px"></div>

            <!--Second row-->
            <div class="row">
              <div class="col-1 mr-3">
                <i class="fas fa-check-circle fa-2x blue-text"></i>
              </div>
              <div class="col-10">
                <h5 class="feature-title"><strong>RE</strong>SELL</h5>
                <p class="grey-text">Rivendi i tuoi appunti. Magari non potrà essere un lavoro a tempo pieno, ma ti permetterà di racimolare qualcosina.</p>
              </div>
            </div>
            <!--/Second row-->

            <div style="height:30px"></div>

            <!--Third row-->
            <div class="row">
              <div class="col-1 mr-3">
                <i class="fas fa-check-circle fa-2x cyan-text"></i>
              </div>
              <div class="col-10">
                <h5 class="feature-title"><strong>RE</strong>CYCLE</h5>
                <p class="grey-text">Ricicla i tuoi vecchi appunti, buttarli non servirebbe a nulla. Approfittane e cerca di ricreare qualcosa di utile per gli altri!.</p>
              </div>
            </div>
			  
			 
			<div style="height:30px"></div>

            <!--Third row-->
            <div class="row">
              <div class="col-1 mr-3">
                <i class="fas fa-check-circle fa-2x light-green-text"></i>
              </div>
              <div class="col-10">
                <h5 class="feature-title"><strong>RE</strong>NOTES</h5>
                <p class="grey-text">ReNotes. E' semplicemente il sito adatto alle tue esigenze 😄</p>
              </div>
            </div>

		  </div>

        </div>

      </section>
<!--Section: Not enough-->
      <section> <!--First row-->
        
        <!--/First row-->

      </section>
      <!--Section: Not enough-->

      <hr class="mb-5">

      <!--Section: More-->
      <section>
<!--First row-->
        
        <!--/First row-->

        <!--Second row-->
        
        <!--/Second row-->

      </section>
      <!--Section: More-->

    </div>
  </main>
  <!--Main layout-->

  <!--Footer-->
  <footer class="page-footer text-center font-small mt-4 wow fadeIn">



	<p class="white-text">ReNotes® è un progetto universitario (Università della Calabria) a cura degli studenti Gena Davide, Iaconantonio Alessia, Rocca Alessandro e Torcasio Leonardo.</p>
	

    <!--Copyright-->
    <div class="footer-copyright py-3">
      © 2019 Copyright:
      <a href="https://mdbootstrap.com/education/bootstrap/" target="_blank"> MDBootstrap.com </a>
    </div>
    <!--/.Copyright-->

  </footer>
  <!--/.Footer-->

  <!-- SCRIPTS -->
  <!-- JQuery -->
  <script type="text/javascript" src="js/jquery-3.4.1.min.js"></script>
  <!-- Bootstrap tooltips -->
  <script type="text/javascript" src="js/popper.min.js"></script>
  <!-- Bootstrap core JavaScript -->
  <script type="text/javascript" src="js/bootstrap.min.js"></script>
  <!-- MDB core JavaScript -->
  <script type="text/javascript" src="js/mdb.min.js"></script>
  <!-- Initializations -->
  <script type="text/javascript">
    // Animations initialization
    new WOW().init();
  </script>

<script>
	function onSignIn(googleUser) 
	{
    var profile = googleUser.getBasicProfile();
    console.log(profile.getEmail());
    var dati = [profile.getEmail(), profile.getName()];
		$.ajax({
			url: "GoogleLogin",
			type: "POST",
			async: true,
			contentType: "application/json",
			data: JSON.stringify(profile.getEmail()),
			success: function(data)
			{
        window.location.replace("http://localhost:8080/RenotesINGSW2/profile.jsp");
			},
			error: function()
			{
				alert("Impossibile effettuare il login");
			}
		});

		  /*var profile = googleUser.getBasicProfile();
		  console.log('ID: ' + profile.getId()); // Do not send to your backend! Use an ID token instead.
		  console.log('Name: ' + profile.getName());
		  console.log('Image URL: ' + profile.getImageUrl());
		  console.log('Email: ' + profile.getEmail()); // This is null if the 'email' scope is not present.
		  */
	}
	</script>

<script>window.fbAsyncInit = function() {
  FB.init({
      appId      : '179061136835560',
      cookie     : true,
      xfbml      : true,
      version    : 'v5.0'
    });
      
    FB.getLoginStatus(function(response) {
        statusChangeCallback(response);
    });  
      
  };

  (function(d, s, id){
     var js, fjs = d.getElementsByTagName(s)[0];
     if (d.getElementById(id)) {return;}
     js = d.createElement(s); js.id = id;
     js.src = "https://connect.facebook.net/en_US/sdk.js";
     fjs.parentNode.insertBefore(js, fjs);
   }(document, 'script', 'facebook-jssdk'));
  
  function statusChangeCallback(response){
    if(response.status==='connected'){
      console.log('connesso');
      }else{
        console.log('disconnesso');
      }
  
}

  function checkLoginState() {
    FB.getLoginStatus(function(response) {
      statusChangeCallback(response);
    });
  }
  
  function logout() {
  FB.logout(function(respons) {
  })
}
</script>

</body>
  <script type="text/JavaScript" src="index.js"></script>
  <script src="https://ajax.googleapis.com/ajax/libs/jquery/3.4.1/jquery.min.js"></script>
</html>
