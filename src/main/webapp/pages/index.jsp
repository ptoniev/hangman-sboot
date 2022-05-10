<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib prefix="sec" uri="http://www.springframework.org/security/tags" %>
<%@ page import="java.util.ArrayList"%>
<%@ page import="bg.petar.springboot.utils.TableEntry"%>
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
<link rel="stylesheet"
href="https://maxcdn.bootstrapcdn.com/bootstrap/4.0.0/css/bootstrap.min.css"
integrity="sha384-Gn5384xqQ1aoWXA+058RXPxPg6fy4IWvTNh0E263XmFcJlSAwiGgFAW/dAiS6JXm"
crossorigin="anonymous">
<meta charset="UTF-8">
<title>Hangman Game</title>
</head>
<body>
<div class="home-page">
<h1>Welcome to the Hangman Game, ${username}!</h1>

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


 <div align="center">
        <table border="1" cellpadding="5">
            <caption><h2>Rankings</h2></caption>
            <tr>
                <th>Name</th>
                <th>Games Won</th>
            </tr>

            <%ArrayList<TableEntry> std =
                        (ArrayList<TableEntry>)session.getAttribute("listOfUsers");
                    for(TableEntry s:std){%>
                    <%-- Arranging data in tabular form
                    --%>
                        <tr>
                            <td><%=s.name%></td>
                            <td><%=s.gamesWon%></td>
                        </tr>
                        <%}%>
        </table>
    </div>

</body>
</html>