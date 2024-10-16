<html>
<%@taglib uri="http://www.springframework.org/tags/form" prefix="form"%>
<br>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<head>
<title>User Registration Form</title>
<style type="text/css">
h3 {
	font-family: Calibri;
	font-size: 22pt;
	font-style: normal;
	font-weight: bold;
	color: SlateBlue;
	text-align: center;
	text-decoration: underline
}

table {
	font-family: Calibri;
	color: white;
	font-size: 11pt;
	font-style: normal;
	text-align:;
	background-color: SlateBlue;
	border-radius: 8px;
	border-collapse: collapse;
	border: 2px solid navy
}

table.inner {
	border: 5px
}
</style>
<script type="text/javascript">
	function validate() {
		var vfirstname = document.getElementById("firstname").value;
		var vlastname = document.getElementById("lastname").value;
		var vEmail = document.getElementById("email").value;
	    var atpos =  vEmail.indexOf("@");
        var dotpos =  vEmail.lastIndexOf(".");
        var phoneno = /^\+?([0-9]{2})\)?[-. ]?([0-9]{10})$/; 
        var phone=document.getElementById("phone").value;
        var password=document.getElementById("password").value;
        var confirmPassword = document.getElementById("confirmPassword").value;
		if (vfirstname == "") {
			document.getElementById("firstname").focus();
			alert("First Name should Not be left blank");
			return false;
		}
		 if (vlastname == "") {
			document.getElementById("lastname").focus();
			alert("Last Name should Not be left blank");
			return false;
		}
	  if (vEmail == "") {
			document.getElementById("email").focus();
			alert("Email should Not be left blank");
		return false;
	}
	  if (atpos<1 || dotpos<atpos+2 || dotpos+2>= vEmail.length) {
               alert("Not a valid e-mail address");
			return false;
		  }
		  
	if((phone.match(phoneno))  
        {  
      return true;  
        }  
      else  
        {  
        alert("Please enter valid phone number");  
        return false;  
        }  
        if(password==null ||password.length<6){
        alert("Please enter min 6 characters");  
         return false; 
        }
         else if(confirmPassword==null ||confirmPassword.length<6){
        alert("Please enter min 6 characters");  
         return false; 
        }
        
        if (password != confirmPassword){
            alert("Passwords do not match.");
            return false;
        }
        return true; 
  }
</script>
</head>
<body>
	<h3>USER REGISTRATION FORM</h3>
	<form:form name="frm" id="register" action="form.htm" method="post"
		modelAttribute="userRegistrationCmd" cssStyle="color:blue"
		cssClass="color" >
		<table align="center" cellpadding="10">
			<tr>
				<td>UserID:&nbsp;</td>
				<td><form:input path="userid" readonly="true" disabled="true" />
					<form:hidden path="userid" /></td>
			</tr>
			<!----- First Name ---------------------------------------------------------->
			<tr>
				<td>FirstName:&nbsp;<b style="color: red">*</b></td>
				<td><form:input path="firstName" 
						id="firstname" placeholder="enter firstname" maxlength="50" /> <span><form:errors
							path="firstName" cssClass="errors" id="firstNameError" /></span></td>
			</tr>
			<!----- Last Name ---------------------------------------------------------->
			<tr>
				<td>LastName:&nbsp;<b style="color: red">*</b></td>
				<td><form:input path="lastName" id="lastname" maxlength="50"
				         placeholder="enter lastname"/>
					<span><form:errors path="lastName" cssClass="errors" /></span></td>
			</tr>

			<!----- Email Id ---------------------------------------------------------->
			<tr>
				<td>Email:&nbsp;<b style="color: red">*</b></td>
				<td><form:input path="email" id="email"  placeholder="ex:abc@gmail.com"/> <span><form:errors
							path="email" cssClass="errors" /></span></td>
			</tr>
			<!----- Date Of Birth -------------------------------------------------------->
			<tr>
				<td>Date Of Birth :</td>
				<td><form:input path="dob" id="datepicker" /></td>
			</tr>
			<tr>
			<tr>
				<td>PhoneNumber :</td>
				<td><form:input path="phone" id="phone" size="15"  placeholder="Ex:+91 7685845678"/></td>
			</tr>
			<tr>
				<td>Address :</td>
				<td><form:textarea path="address" id="address" maxlength="200" /></td>
			</tr>
			<tr>
				<td>Select Your Country :</td>
				<td><form:select path="countryCode">
						<form:options items="${countryList}" itemLabel="countryDesc"
							itemValue="countryCode" />
					</form:select></td>
			</tr>
			<tr>
				<td>Select Your State :</td>
				<td><form:select path="stateCode">
						<form:options items="${stateList}" itemLabel="stateDesc"
							itemValue="stateCode" />
					</form:select></td>
			</tr>
			<tr>
				<td>Select languages You know :</td>
				<td><form:checkboxes items="${langList}" path="langId" /></td>
			</tr>
			<tr>
				<!----- Gender ----------------------------------------------------------->
			<tr>
				<td>Select Gender :</td>
				<td><form:radiobutton path="gender" value="M" checked="true" />M
					<form:radiobutton path="gender" value="F" />F</td>
			</tr>
			<tr>
				<td>New PassWord :&nbsp;<b style="color: red">*</b></td>
				<td><form:password path="password" id="password" /> <span><form:errors
							path="password" cssClass="errors" /></span></td>
			</tr>
			<tr>
				<td>Conform PassWord:&nbsp;<b style="color: red">*</b></td>
				<td><form:password path="conformPassword" id="conformpassword" />
					<span><form:errors path="conformPassword" cssClass="errors" /></span></td>
			</tr>

			<!----- Submit and Reset ------------------------------------------------->
			<tr>
				<td colspan="2" align="center"><c:if
						test="${ userRegistrationCmd.userid==0}">
						<input type="submit" value="Register" class="b1"
							onclick="return validate()">
					</c:if> <c:if test="${ userRegistrationCmd.userid ne 0 }">
						<input type="submit" value="Update" class="b2">
					</c:if> <input type="reset" value="Reset" class="b3"></td>
			</tr>
		</table>
		<link rel="stylesheet"
			href="http://code.jquery.com/ui/1.9.2/themes/base/jquery-ui.css" />
		<script src="http://code.jquery.com/jquery-1.8.3.js"></script>
		<script src="http://code.jquery.com/ui/1.9.2/jquery-ui.js"></script>
		<script>
			$(function() {
				$("#datepicker").datepicker({
					changeMonth : true,
					changeYear : true,
					yearRange : "c-125:c+25"
				});

			});
		</script>
		<style>
.b1 {
	background-color: #4CAF50;
	border: 2;
	color: white;
	padding: 1px 3px;
	text-align: center;
	text-decoration: none;
	display: inline-block;
	font-size: 16px;
	margin: 8px 4px;
	cursor: pointer;
}

.b2 {
	background-color: orange;
	border: 2;
	color: white;
	padding: 1px 3px;
	text-align: center;
	text-decoration: none;
	display: inline-block;
	font-size: 16px;
	margin: 8px 4px;
	cursor: pointer;
}

.b3 {
	background-color: red;
	border: 2;
	color: white;
	padding: 1px 3px;
	text-align: center;
	text-decoration: none;
	display: inline-block;
	font-size: 16px;
	margin: 8px 4px;
	cursor: pointer;
}

.b4 {
	background-color: blue;
	border: 2;
	color: white;
	padding: 1px 3px;
	text-align: center;
	text-decoration: none;
	display: inline-block;
	font-size: 16px;
	margin: 8px 4px;
	cursor: pointer;
}

.btn1 {
	font-size: 20px;
	margin: 20px auto;
	text-align: center;
	color: #fff;
	display: block;
	text-decoration: none;
	width: 300px;
	height: 40px;
	line-height: 40px;
	background: #5a8e07;
}
</style>

	</form:form>
	<a href="users_data.htm" class="btn1">All Users</a>
</body>
</html>