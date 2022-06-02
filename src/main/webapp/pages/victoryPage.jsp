<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html>
<html>
<style type="text/css">
.victory-page{
text-align: center}

.start-game-form{
display: inline-block;
}

</style>
<head>
<link rel="stylesheet"
href="https://maxcdn.bootstrapcdn.com/bootstrap/4.0.0/css/bootstrap.min.css"
integrity="sha384-Gn5384xqQ1aoWXA+058RXPxPg6fy4IWvTNh0E263XmFcJlSAwiGgFAW/dAiS6JXm"
crossorigin="anonymous">
<meta charset="ISO-8859-1">
<title>Victory</title>
</head>
<body>

<div class="victory-page">
<h1>Congratulations! You have won the game! </h1> <br>


<br>
<form class="start-game-form" method="post" action="/game">
  <button type="submit" >Start a new game</button> </form>
  
  
  </div>
</body>
</html>