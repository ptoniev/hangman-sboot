<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<style type="text/css">
.defeat-header{
  text-align: center;
}
.defeat-page{
   top: 15%;
    left: 46%;
    position: fixed;	
}

.start-game-form{
display: inline-block;
}

</style>
<head>
<link rel="stylesheet"
href="https://maxcdn.bootstrapcdn.com/bootstrap/4.0.0/css/bootstrap.min.css"
integrity="sha384-Gn5384xqQ1aoWXA+058RXPxPg6fy4IWvTNh0E263XmFcJlSAwiGgFAW/dAiS6JXm"
crossorigin="anonymous">
<meta charset="UTF-8">
<title>Loser</title>
</head>
<body>
<h1 class="defeat-header"> You have lost the game and virtual death is upon you!</h1>
<div class="defeat-page">
	 <pre> ${picture} </pre>
	 <br>
<form class="start-game-form" method="post" action="/game">
  <button type="submit" >Start a new game</button> </form>
  
  
  </div>
</body>
</html>