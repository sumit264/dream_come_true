<%@ taglib prefix="spring" uri="http://www.springframework.org/tags" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<c:set var="contextPath" value=""/>

<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="utf-8">
    <meta http-equiv="X-UA-Compatible" content="IE=edge">
    <meta name="viewport" content="width=device-width, initial-scale=1">
    <!-- The above 3 meta tags *must* come first in the head; any other head content must come *after* these tags -->
    <meta name="description" content="">
    <meta name="author" content="">

    <title>Sign-Up With Your Details</title>

    <link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.7/css/bootstrap.min.css">
</head>

<body>

<div class="container">

    

    <form method="POST" action="/api/signUp/userSignUp" class="form-signin">
        <h2 class="form-heading">User Sign-Up</h2>

        <div class="form-group ">
            <span></span>
            <input name="email" type="text" class="form-control" placeholder="Email" autofocus="true"/>
            <input name="reEnter_email" type="text" class="form-control" placeholder="ReEnter_email"/>
            <input name="phoneNo" type="text" class="form-control" placeholder="Phone No" autofocus="true"/>
            <input name="userName" type="text" class="form-control" placeholder="UserName" autofocus="true"/>
            <input name="password" type="password" class="form-control" placeholder="Password" autofocus="true"/>
            <input name="gender" type="text" class="form-control" placeholder="Gender" autofocus="true"/>
            <input name="dob" type="text" class="form-control" placeholder="DOB" autofocus="true"/>
            
            <span></span>
            
            <span></span>
            <br></br>

            <button class="btn btn-lg btn-primary btn-block" type="submit">Sign Up</button>
        </div>

    </form>
    
    <form method="POST" action="/logout" class="form-signin">
    
            <button class="btn btn-lg btn-primary btn-block" type="submit">LogOut</button>
    </form>

</div>
<!-- /container -->
<script src="https://ajax.googleapis.com/ajax/libs/jquery/1.11.2/jquery.min.js"></script>
<script src="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.7/js/bootstrap.min.js"></script></body>
</html>