<!DOCTYPE html>
<htmnl>
    <head>
        <title>Конвертор Валют</title>
    </head>
    <body>
    <h1>Конвертор Валют</h1>
    <hr>
    <br><a href="/convertor"> Конвертор валют</a><br>
    <br><a href="/"> Главная страница</a><br>
    <h4>История конвертаций:</h4>
    <form action="/convertor/history" method="post" name="filter">
        <p>
            <input type="date" name="date">
        </p>
        <select name="idFrom">
            <option value="">
                Не определено
            </option>
            <#list currencyList as currency>
                <option value="${currency.id}">
                    ${currency.name}
                </option>
            <#else>
                <h3>Оштбкай</h3>
            </#list>
        </select>
        <select name="idTo">
            <option value="">
                Не определено
            </option>
            <#list currencyList as currency>
                <option value="${currency.id}">
                    ${currency.name}
                </option>
            <#else>
                <h3>Оштбкай</h3>
            </#list>
        </select>
        <p>
            <input type="hidden" name="_csrf" value="${_csrf.token}"/>
            <input type="submit" value="Отфильтровать"/>
        </p>
    </form>
    <table border="1">
        <tr>
            <th>Исходящая валюта</th>
            <th>Целевая валюта</th>
            <th>Исходная сумма</th>
            <th>Получаемая сумма</th>
            <th>Дата</th>
        </tr>
        <#list convertors as convertor>
            <tr>
                <th>${convertor.valueOfDateFrom.currency.name}</th>
                <th>${convertor.valueOfDateTo.currency.name}</th>
                <th>${convertor.valueFrom}</th>
                <th>${convertor.valueTo}</th>
                <th>${convertor.date}</th>
            </tr>
        <#else>
            <tr>
                <h3>Не найдено</h3>
            </tr>
        </#list>
    </body>
</htmnl>