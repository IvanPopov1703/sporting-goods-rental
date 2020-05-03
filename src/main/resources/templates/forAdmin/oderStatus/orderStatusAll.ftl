<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <title>Статусы товара</title>
</head>
<body>
<h2>Статусы товара:</h2>
<#list status as status>
    <ol>
        <li>${status.name}</li>
    </ol>
</#list>
<br>
<a href="/admin/status/edit"><button type="submit">Добавить</button></a>
<a href="/admin/status/del"><button type="submit">Удалить</button></a>
<button type="submit">Редактировать</button>
</body>
</html>