<%-- 
    Document   : index
    Created on : 06-Mar-2013, 21:26:50
    Author     : kavansol
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>JSP Page</title>
        <link rel="stylesheet" type="text/css" href="/photomash/view/css/style.css">
        <script src="//code.jquery.com/jquery-latest.js"></script>  

        <script type="text/javascript" src="/photomash/view/js/initial.js" ></script> 
    </head>
    <body>
        <h1>dynamic jsp index</h1>
        <form action="/photomash/album/create/submit" method="POST">             

            <h3> Description</h3>

            <div class ="form_line" > <span class="form_lab" >Title </span> <input type="text" /> </div>
            <div  class ="form_line"> <span class="form_lab">Tags </span> <input type="text" /> </div>
            <div class ="form_line">  <span class="form_lab">E-Mail </span> <input type="email" /> </div>
            <div class ="form_line"> <span class="form_lab">Password: </span> <input type="password" /></div>
            <div class ="form_line">  <span class="form_lab">Retype password: </span> <input type="password" /></div>

            <div class="form_line"> <input class="form_submit" type="submit" /></div>
        </form><br />
        <hr />
        

      
    </body>
</html>
