<!DOCTYPE HTML>
<html>
<head>
    <meta charset="UTF-8" />
    <title>Список всех типов товара</title>
</head>
<body>
<h1>Список типов товара</h1>

<div>
    <nobr>
        <a href="/typeOfItem/add">Добавить новый</a>
        <a href="/index">Вернуться назад</a>
    </nobr>
</div>
<br/><br/>
<div>
    <table border="1">
        <tr>
            <th>Id</th>
            <th>Название</th>
            <th>Редактирование</th>
        </tr>
        <#list typeOfItem as type>
            <tr>
                <td><a href="${'typeOfItem/' + type.id}">${type.id}</a></td>
                <td><a href="${'typeOfItem/' + type.id}">${type.name}</a></td>
                <td><a href="${'typeOfItem/' + type.id + '/edit'}">Редактировать</a></td>
            </tr>
        </#list>
    </table>
</div>
</body>
</html>