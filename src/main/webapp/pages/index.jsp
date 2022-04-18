<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib prefix="sec" uri="http://www.springframework.org/security/tags" %>
<!DOCTYPE html>
<html>
<style type="text/css">
.home-page{
text-align: center}

.start-game-form{
display: inline-block;
}

</style>
<head>
<meta charset="UTF-8">
<title>Hangman Game</title>
</head>
<body>
<div class="home-page">
<h1>Welcome to the Hangman Game!</h1>

<sec:authorize access="!isAuthenticated()">
  <p>	<a href="/login" >Log into your account</a> </p>
   </sec:authorize>
<br>
<sec:authorize access="!isAuthenticated()">
<form class="start-game-form" method="post" action="/game">
  <button type="submit" >Start game as Guest</button> </form>
  </sec:authorize>

  <sec:authorize access="isAuthenticated()">
  <form class="start-game-form" method="post" action="/game">
    <button type="submit" >Start game</button> </form>
    </sec:authorize>
<br>
<p>
  <sec:authorize access="hasAnyRole('ADMIN','USER')">
  <a href="/logout"> Logout </a>
  </sec:authorize>
  </p>
  	</div>


</body>
</html>