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
    <table>
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
            <h3>У вас ещё нет конвертаций</h3>
        </#list>
    </body>
</htmnl>