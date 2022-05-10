<link rel="stylesheet"
href="https://maxcdn.bootstrapcdn.com/bootstrap/4.0.0/css/bootstrap.min.css"
integrity="sha384-Gn5384xqQ1aoWXA+058RXPxPg6fy4IWvTNh0E263XmFcJlSAwiGgFAW/dAiS6JXm"
crossorigin="anonymous">

<div align = "center">
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

    <a href="/"> Navigate to home page</a>

    </div>