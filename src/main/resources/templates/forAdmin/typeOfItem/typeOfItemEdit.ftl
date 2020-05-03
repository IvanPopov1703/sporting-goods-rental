<!DOCTYPE HTML>
<html>
<head>
    <meta charset="UTF-8" />
    <title><#if add>Добавить тип товара<#else>Редактировать тип товара</#if></title>
</head>
<body>
<h1><#if add>Создать новый тип товара:<#else>Изменить тип товара:</#if></h1>
<a href="/typeOfItem">Назад к списку</a>
<br/><br/>
<#if add>
    <#assign urlAction>${'/typeOfItem/add'}</#assign>
    <#assign submitTitle>Создать</#assign>
<#else>
    <#assign urlAction>${'/typeOfItem/' + typeOfItem.id + '/edit'}</#assign>
    <#assign submitTitle>Редактировать</#assign>
</#if>
<form action="${urlAction}" name="typeOfItem" method="POST">
    <table border="0">
        <#if add == false>
            <tr>
                <td>ID</td>
                <td>${typeOfItem.id}</td>
            </tr>
        </#if>
        <tr>
            <td>Название типа:</td>
            <td><input type="text" placeholder="Введите название" name="name" /></td>
        </tr>
    </table>
    <input type="submit" value="${submitTitle}" />
</form>

<br/>
<!-- Check if errorMessage is not null and not empty -->
<#if errorMessage?has_content>
    <div class="error">${errorMessage}</div>
</#if>
</body>
</html>