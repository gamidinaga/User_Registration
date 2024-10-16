<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1" isELIgnored="false"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<%@taglib uri="http://www.springframework.org/tags/form" prefix="form"%>
<br>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%><h1
	style="text-align: center; color: green">New Registration Form</h1>

<p style="color: red">* Mandator</p>
<link href="${pageContext.request.contextPath}/css/style.css"
	rel="stylesheet" type="text/style">
<script type="text/javascript"
	src="${ pageContext.request.contextPath }/js/jquery-3.2.1.js"></script>
<script type="text/javascript"
	src="${ pageContext.request.contextPath }/js/jquery.validate.js"></script>
<script type="text/javascript">
	$(document).ready(function() {
		$('#register').validate({
			rules : {
				firstname : {
					required : true,
					maxlength : 15
				},
				lastname : {
					required : true
				},
				email : {
					required : true,
					email : true
				},
				password : {
					required : true,
					minlength : 8,
					maxlength : 10
				},
				conformpassword : {
					required : true,
					minlength : 8,
					maxlength : 10
				}
			}
		});
	});
</script>
<form:form id="register" action="form.htm" method="post"
	modelAttribute="userRegistrationCmd" cssStyle="color:blue"
	cssClass="color">
	<table class="tb">
		<tr>
			<td>UserID:&nbsp;</td>
			<td><form:input path="userid" readonly="true" disabled="true" />
			       <form:hidden path="userid" /> </td>
		</tr>
		<tr>
			<td>FirstName:&nbsp;<b style="color: red">*</b></td>
			<td><form:input path="firstName" id="firstname" /><span><form:errors
						path="firstName" cssClass="errors" /></span></td>
		</tr>
		<tr>
			<td>LastName:&nbsp;<b style="color: red">*</b></td>
			<td><form:input path="lastName" id="lastname" /><span><form:errors
						path="lastName" cssClass="errors" /></span></td>
		</tr>
		<tr>
			<td>Email:&nbsp;<b style="color: red">*</b></td>
			<td><form:input path="email" name="email" id="email" /><span><form:errors
						path="email" cssClass="errors" /></span></td>
		</tr>
		<tr>
			<td><form:label path="dob">Date Of Birth :</form:label></td>
			<td><form:input path="dob" id="datepicker" /></td>
		</tr>
		<tr>
			<td>select your country :</td>
			<td><form:select path="countryCode">
					<form:options items="${countryList}" itemLabel="countryDesc"
						itemValue="countryCode" />
				</form:select></td>
		</tr>
		<tr>
			<td>select your state :</td>
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
			<td>Select Gender :</td>
			<td><form:radiobutton path="gender" value="M" checked="true" />M
				<form:radiobutton path="gender" value="F" />F</td>
		</tr>
		<%-- <tr>
			<td>UploadYour passport :</td>
			<td><form:input path="passportfile" type="file"
					name="passportfile" /></td>
		</tr> --%>
		<tr>
			<td>New PassWord :&nbsp;<b style="color: red">*</b></td>
			<td><form:password path="password" id="password" /><span><form:errors
						path="password" cssClass="errors" /></span></td>
		</tr>
		<tr>
			<td>Conform PassWord:&nbsp;<b style="color: red">*</b></td>
			<td><form:password path="conformPassword" id="conformpassword" /><span><form:errors
						path="conformPassword" cssClass="errors" /></span></td>
		</tr>
	</table>
	<c:if test="${empty userRegistrationCmd.firstName}">
		<input type="submit" value="Register" class="b1">
	</c:if>
	<c:if test="${!empty userRegistrationCmd.userid }">
		<input type="submit" value="Update" class="b2">
	</c:if>
	<!--  <input type="button" value="Cancel" class="b3" >
       <input type="button" value="GetAllRecords" class="b4" src="/users_data.htm" > -->

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
/* .tb{
 background-color: gray;
    border: 2;
    color: white;
    padding: 1px 3px;
    text-align: center;
    text-decoration: none;
    display: inline-block;
} */
.color {
	background-color: lightblue;
}

.errors {
	color: red;
}
</style>
	<br>
	<a href="select_option.html">Go Back To Home page</a>
	<br>
	<a href="users_data.htm">getAllUsers</a>
	<br>
</form:form>
<p style="color: green">${createUser_result}</p>