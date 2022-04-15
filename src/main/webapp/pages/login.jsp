<h1> Login </h1>
${SPRING_SECURITY_LAST_EXCEPTION.message}

<form action="/login" method="post" >
    <p>
    <label for"yn">User name: </label>
    <input  id="yn" type ="text" name="username"/>
    <br>


    <label for"cn">Password: </label>
    <input  id="cn" type="password" name="password"/>
    <br>

    <input name="login" type="submit" value="submit">
    </form>

    <a href="/play-as-guest"> Play as Guest</a>