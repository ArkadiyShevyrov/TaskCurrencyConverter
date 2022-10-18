<!DOCTYPE html>
<htmnl>
    <head>
        <title>Конвертор Валют</title>
    </head>
    <body>
    <h1>Конвертор Валют</h1>
    <hr>
    <br><a href="/"> Главная страница </a><br>
    <br><a href="/convertor/history"> История конвертаций</a><br>
    <hr>
    <form action="/convertor" method="post" name="form1">
        <select name="idFrom">
            <#list currencies as currency>
                <option value="${currency.id}">
                    ${currency.name}
                </option>
            </#list>
        </select>
        <select name="idTo">
            <#list currencies as currency>
                <option value="${currency.id}">
                    ${currency.name}
                </option>
            </#list>
        </select>
        <p>
            Исходная сумма: <input type="number" step="0.01" name="valueFrom"/><br><br>
        </p>
        <p>
            <input type="hidden" name="_csrf" value="${_csrf.token}"/>
            <input type="submit" value="Конвертировать"/>
        </p>
    </form>
    <#if message??>
        <h2 style="color: green">${message}</h2>
    </#if>
    <#if convertion??>
        <h4>
            ${convertion.valueFrom}
            <${convertion.valueOfDateFrom.currency.name}>
            конвертированно в
            ${convertion.valueTo}
            <${convertion.valueOfDateTo.currency.name}>
        </h4>
    </#if>

    </body>
</htmnl>