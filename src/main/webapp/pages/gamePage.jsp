<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
	<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
	<%@ taglib prefix="sec" uri="http://www.springframework.org/security/tags" %>
	<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
	<%@ page import="bg.petar.springboot.model.HangmanInput"%>
<!DOCTYPE html>
<html>

<style type="text/css">
.menu-header{
  text-align: center;
}
.game-menu{
 top: 10%;
    left: 41%;
    position: fixed;
}

.game-form{
  display: inline-block;
}
</style>

<head>
<link rel="stylesheet"
href="https://maxcdn.bootstrapcdn.com/bootstrap/4.0.0/css/bootstrap.min.css"
integrity="sha384-Gn5384xqQ1aoWXA+058RXPxPg6fy4IWvTNh0E263XmFcJlSAwiGgFAW/dAiS6JXm"
crossorigin="anonymous">
<meta charset="UTF-8">
<title>Play time</title>
</head>
<body>
	<h1 class="menu-header">Guess like your life depends on it</h1>
	
	<div class="game-menu">
	<p> ${censoredWord} </p>

	 <sec:authorize access="hasAnyRole('ADMIN')">
      <form method="get" action="/uncensored-word">
                                       <button type="submit" >Show word</button> </form>
      </sec:authorize>

	<p> The word contains ${lettersNumber} letters in total.
	
	<p> You have ${6 - wrongGuessNumber } tries left!</p>
		
	 <pre> ${picture} </pre>

	<form:form class="game-form" method="post" action="/game/${gameId}" modelAttribute="hangmanInput">
		<table>
			<tr>
				<td>Enter your guessed letter</td>
				<td><form:input path="input" /></td><br>
            <form:errors path="input"/>
			</tr>
		</table>

	</form:form>
	
	<br>
	<br>
<form class="start-game-form" method="post" action="/game">
  <button type="submit" >New game</button> </form>
<br>

  <sec:authorize access="hasAnyRole('ADMIN')">
        <a href="/word-dictionary"> Show all game words</a>
        </sec:authorize>
	</div>

</body>
</html>