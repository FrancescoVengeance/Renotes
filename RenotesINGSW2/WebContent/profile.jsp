<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
  <head>
    <meta charset="UTF-8">
    <title>Profilo</title>
    <meta name="viewport" content="width=device-width, initial-scale=1">
    <link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.7/css/bootstrap.min.css">
    <script src="https://ajax.googleapis.com/ajax/libs/jquery/3.3.1/jquery.min.js"></script>
    <script src="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.7/js/bootstrap.min.js"></script>
    <script src="//maxcdn.bootstrapcdn.com/bootstrap/4.1.1/js/bootstrap.min.js"></script>
    <script src="//cdnjs.cloudflare.com/ajax/libs/jquery/3.2.1/jquery.min.js"></script>
  </head>
  
  <body>
    <div class="container bootstrap snippet">
      <div class="row">
        <div class="col-sm-10"><h1><strong>Il mio profilo</strong></h1></div>
        <a href=""><img src= "resources/icon1_black.png" width="60" height="60" class="pull-right"></a>
      </div>
      <div class="row">
        <div class="col-sm-3"><!--left col-->
                
  
        <div class="text-center">
          <form action="ModifyUserImage" method="POST" enctype="multipart/form-data">  
          <img src="http://ssl.gstatic.com/accounts/ui/avatar_2x.png" class="avatar img-circle img-thumbnail" alt="avatar">
          <h6>Scegli la foto di profilo che preferisci</h6>
          <input type="file" name="file" class="text-center center-block file-upload">
          <input type="submit" value="Applica">
          </form>
        </div></hr><br>
  
            <hr>
            <div class="panel panel-default">	
              <div class="panel-heading"><a href=""><strong>I miei acquisti</strong></a><i class="fa fa-link fa-1x"></i></div>
              <div class="panel-heading"><a href=""><strong>Le mie inserzioni</strong></a><i class="fa fa-link fa-1x"></i></div>
              <div class="panel-heading"><a href=""><strong>I miei metodi di pagamento</strong></a><i class="fa fa-link fa-1x"></i></div>
              <div class="panel-heading"><a href=""><font color="00A000"><strong>Carica inserzione</strong></font></a><i class="fa fa-link fa-1x"></i></div>
              <button onclick="logOut()">Disconnettiti</button>
            </div> 
          </div><!--/col-3-->
        
          <div class="col-sm-0">  
            <div class="tab-content">
              <div class="tab-pane active" id="home">
                  <hr>
                    <form class="form" action="ModifyUserServlet" method="POST" id="registrationForm">
                        <div class="form-group">
                            
                            <div class="col-xs-6">
                                <label for="email"><h4>Username</h4></label>
                                <input type="text" class="form-control" name="" id="username-input" placeholder="username">
                            </div>
                        </div>
                        <div class="form-group">
                            
                            <div class="col-xs-6">
                              <label for="email"><h4>Email</h4></label>
                                <input type="text" class="form-control" name="" id="email-input" placeholder="email">
                            </div>
                        </div>
            
                        <script>
                        </script>   <div class="form-group">
                            
                            <div class="col-xs-6">
                                <label for="password"><h4>Password</h4></label>
                                <input type="password" class="form-control" name="password" id="password" placeholder="password">
                            </div>
                        </div>
  
                        <div class="form-group">
                             <div class="col-xs-12">
                                  <br>
                                  <button class="btn btn-lg btn-success pull-right" type="submit"><i class="glyphicon glyphicon-ok-sign"></i> Applica modifiche</button>
                             </div>
                        </div>
                    </form>
                
                <hr>
                
               </div><!--/tab-pane-->
               
            </div><!--/tab-content-->
  
          </div><!--/col-9-->
      </div><!--/row-->
  </body>

  <script type="text/JavaScript" src="profile.js"></script>
  <script>
  
  function logOut()
{
    $.ajax({
        url: "LogoutServlet",
        type: "POST",
        async: true,
        data: JSON.stringify("out"),
        success: function(data)
        {
           window.location.replace("http://localhost:8080/RenotesINGSW2/index.jsp");
        },
        error: function(){}
    });
}
  
  </script>
</html>