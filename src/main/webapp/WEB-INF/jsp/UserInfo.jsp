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
  <script>
async function uploadFile() {
  let formData = new FormData(); 
  formData.append("image", fileupload.files[0]);
  let response = await fetch('/image', {
    method: "POST", 
   body: formData
  }); 

  if (response.status == 200) {
    //alert("File successfully uploaded.");
  }
}
</script>
<body>

<div class="container">

    

    <form method="POST" action="/api/userInfoDelivery/messageInfo" class="form-signin">
        <h2 class="form-heading">User Information</h2>

        <div class="form-group ">
            <span></span>
            <input name="name" type="text" class="form-control" placeholder="Name" autofocus="true"/>
            <textarea name="address" class="form-control" placeholder="Address"></textarea>
            <input name="country" type="text" class="form-control" placeholder="Country" autofocus="true"/>
            <input name="state" type="text" class="form-control" placeholder="State" autofocus="true"/>
            <input name="city" type="text" class="form-control" placeholder="City" autofocus="true"/>
            <input name="email" type="text" class="form-control" placeholder="Email" autofocus="true"/>
            <input name="verifyemailotp" type="text" class="form-control" placeholder="Verify Email Otp" autofocus="true"/>
            <input name="phoneNo" type="text" class="form-control" placeholder="PhoneNo" autofocus="true"/>
            <input name="verifyphoneotp" type="text" class="form-control" placeholder="Verify Phone Otp" autofocus="true"/>
                       
            <span></span>
            <br></br>
            <h2 class="form-heading">Message Delivery Information</h2>
            
            
            <input name="nameOfReciever" type="text" class="form-control" placeholder="Name Of Reciever" autofocus="true"/>
            <input name="emailOfReciever" type="text" class="form-control" placeholder="Email Of Reciever" autofocus="true"/>
            <textarea name="postalAddress" class="form-control" placeholder="Postal Address" autofocus="true"></textarea>
            <input name="messageToBeDelivery" type="text" class="form-control" placeholder="Message To Be Deliver" autofocus="true"/>
            <input name="attachments" type="text" class="form-control" placeholder="Any Attachments To Be Deliver" autofocus="true"/>
          <input id="fileupload" type="file" name="fileupload" /> 
           <!-- <button id="upload-button" onclick="uploadFile()"> Upload </button> -->
            <input name="deliveryDate" type="text" class="form-control" placeholder="Delivery Date" autofocus="true"/>
                        
            <span></span>
            <span></span>
            <br></br>
     
            
            <button class="btn btn-lg btn-primary btn-block" onclick="uploadFile()" type="submit">Upload & Submit</button>
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