<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1" isELIgnored="false"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<%@taglib uri="http://www.springframework.org/tags/form" prefix="form"%>
<%@taglib  uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@taglib  uri="http://java.sun.com/jsp/jstl/fmt"   prefix="fmt"%>
<meta charset="utf-8">
  <meta name="viewport" content="width=device-width, initial-scale=1">
  <link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.7/css/bootstrap.min.css">
  <script src="https://ajax.googleapis.com/ajax/libs/jquery/3.2.1/jquery.min.js"></script>
  <script src="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.7/js/bootstrap.min.js"></script>
<style>
table{border:1px solid #edeaea;border-collapse:collapse;font-family:lucida;width:90%;margin:0px auto;}
table tr:nth-child(odd) {
    background: #edeaea;
}
tr{height:40px;line-height:40px;text-transform:capitalize;text-align:center}
.update{height:50px;padding:5px 20px;border-radius:5px;color:#fff;background:#07508c;text-align:center;text-decoration:none}
.update:hover{text-decoration:underline;color:#fff}
.delete:hover{text-decoration:underline;color:#fff}
tr:hover{color:#c61003;cursor:pointer;}
.delete{height:35px;padding:5px 20px;border-radius:5px;color:#fff;background:#c61003;text-align:center;text-decoration:none}
.btn1{
font-size:20px;margin:20px auto;text-align:center;color:#fff;display:block;text-decoration:none;width:200px;height:40px;line-height:40px;background:#5a8e07;
}
.b4{
font-size:20px;margin:20px auto;text-align:center;color:#ffg;display:block;text-decoration:none;width:100px;height:40px;line-height:40px;background:#6B8E23;
}
.btn1 :hover{outline:2px solid #5a8e07;background:#fff;color:#5a8e07;text-decoration:none;}
#menu{
height:50px;max-width:100%;background:#113d84}
#menu ul li{display:inline-block;height:50px;line-height:50px;width:auto;margin-left:10px;padding:0px 10px 0px 10px;text-align:center}
#menu ul li:hover{background:red;text-decoration:none;color:#fff}

#menu ul li a{color:#fff;text-decoration:none;font-size:19px}
</style>
<div id="menu">
<ul><li><a href="users_data.htm">All Users Details</a></li><li> <a href="form.htm">Register New User </a></li></ul>
</div>

<!-- <h1 style="text-align: center; color: green">All Registered User
	Details</h1> -->

<c:choose>
	<c:when test="${not empty usersList}">
		  <table border="1">
     <caption> <h1 style="color:#113d84;text-align:left;width:320px;float:left">All Users Details </h1></caption>
       <tr style="background:#3a3a3a;color:white;height:40px;line-height:40px;text-transform:capitalize;font-size:18px">
				<th align="left">UserId</th>
				<th align="left">FirstName</th>
				<th align="left">LastName</th>
				<th align="left">EmailAddres</th>
				<th align="left">DateOfBirth</th>
				<th align="left">Phone</th>
				<th align="left">Address</th>
				<th align="left">Country</th>
				<th align="left">State</th>
				<th align="left">Languages</th>
				<th align="left">Gender</th>
				<th  align="center"  colspan="2">Action</th>
			</tr>
			<c:forEach var="dto" items="${usersList}">
				<tr>
					<td>${dto.userid}</td>
					<td>${dto.firstName}</td>
					<td>${dto.lastName}</td>
					<td>${dto.email}</td>
					<td><fmt:formatDate  pattern="dd-MM-yyyy" value="${dto.dob}"/></td>
					<td>${dto.phone}</td>
					<td>${dto.address}</td>
					<td>${countryMap[dto.countryCode]}</td>
					<td>${stateMap[dto.stateCode]}</td>
					<td>${dto.langDesc}</td>
					<td>${dto.gender}</td>
<%-- 					<td><a style="color:red" href="download.htm?passport_file=${dto.passportfile}">dowloadfile</a></td>
 --%>					 <td width="120px"><a href="edit_user.htm?uId=${dto.userid}" class="update">update</a></td>
            <td width="120px"><a href="delete_user.htm?uId=${dto.userid}" class="delete">delete</a></td>
            </tr>
           
			</c:forEach>
			 <tr><td align="center"  colspan="13"><input type="submit" value="Print" class="b4"><a href="select_option.html" class="btn1">Go Back</a>
 <a href="form.htm" class="btn1" >Register New User </a><td></tr>
		</table>
	</c:when>
	<c:otherwise>
		<p style="color: red">No records found</p>
	</c:otherwise>
</c:choose>
<p style="color:red">${deletedUser_result }</p>




 