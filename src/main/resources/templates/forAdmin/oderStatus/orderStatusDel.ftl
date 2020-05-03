<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <title>Удаление статуса товара</title>
</head>
<body>
<h2>Статусы товара:</h2>
<#list status as status>
    <ol>
        <input type="checkbox" name="status" value="${status.id}" />
        <li>${status.name}</li>
    </ol>
</#list>
<br>
<button type="submit">Удалить</button>
</body>
</html>