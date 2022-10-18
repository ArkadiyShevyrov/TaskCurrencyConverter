<!DOCTYPE html>
<htmnl>
    <head>
        <title>Конвертор валют</title>
    </head>
    <body>
    <h1>Конвертор валют</h1><hr>
    <h4>Регистрация</h4>

    <form action="/registration" method="post">
        Username: <input type="text" name="username"/><br><br>
        Пароль: <input type="password" name="password"/><br><br>
        <input type="hidden" name="_csrf" value="${_csrf.token}"/>
        <input type="submit" value="Зарегистрироваться"/>
    </form>
    <#if errorMessage??>
        <h2 style="color: red">${errorMessage}</h2>
    </#if>
    </body>
</htmnl>