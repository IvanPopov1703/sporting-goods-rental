<!DOCTYPE HTML>
<html>
<head>
    <meta charset="UTF-8" />
    <title>Тип определённого товара</title>
</head>
<body>
<h1>Тип товара</h1>
<a href="/typeOfItem">Вернуться к списку типов товара</a>
<br/><br/>
<#if typeOfItem??>
    <table border="0">
        <tr>
            <td>ID</td>
            <td>:</td>
            <td>${typeOfItem.id}</td>
        </tr>
        <tr>
            <td>Название</td>
            <td>:</td>
            <td>${typeOfItem.name}</td>
        </tr>
    </table>
    <br/><br/>
    <#if allowDelete>
        <form action="${'/typeOfItem/' + typeOfItem.id + '/delete'}" method="POST">
            Удалить запись? <input type="submit" value="Yes" />
        </form>
    <#else>
        <div>
            <a href="${'/typeOfItem/' + typeOfItem.id + '/edit'}">Редактировать</a> |
            <a href="${'/typeOfItem/' + typeOfItem.id + '/delete'}">Удалить</a>
        </div>
    </#if>
</#if>
<#if errorMessage?has_content>
    <div class="error">${errorMessage}</div>
</#if>
</body>
</html>