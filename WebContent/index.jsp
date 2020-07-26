<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="ISO-8859-1">
<title>Index Page</title>
</head>
<body>



<section style="text-align: center">
<div style="border-style: groove; ;max-width: 500px;">
<h2> upload csv file ...</h2><br><br>
<form action="/com.digilytics.javaAssignment/register" method="post" enctype="multipart/form-data">
  <input type="file" name="file" /><br><br>
  <input type="submit"  value="Submit"/>
  <br>
</form>
<br><br><br><br><br><br>
</div>
</section>
</body>
</html>