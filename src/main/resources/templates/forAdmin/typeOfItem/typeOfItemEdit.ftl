<!DOCTYPE HTML>
<html>
<head>
    <meta charset="UTF-8"/>
    <title><#if add>Добавить тип товара<#else>Редактировать тип товара</#if></title>
    <style>
        body {
            margin: 0;
        }

        main {
            /*border: 1px red solid;*/
            /*width: 500px;*/
            height: 500px;
            margin: 10px;
        }

        .classTest {
            border: 1px red solid;
            width: 500px;
            height: 500px;
            margin: 10px;
        }

        .adminLi {
            padding: 3px;
        }

        table, form {
            font-size: 18px;
        }

        td {
            padding-top: 7px;
        }

        button, input {
            font-size: 15px;
        }
    </style>
</head>
<body>
<main>
    <h1><#if add>Создать новый тип товара<#else>Изменить тип товара</#if></h1>
    <#if add>
        <#assign urlAction>${'/admin/typeOfItem/add'}</#assign>
        <#assign submitTitle>Создать</#assign>
    <#else>
        <#assign urlAction>${'/admin/typeOfItem/' + typeOfItem.id + '/edit'}</#assign>
        <#assign submitTitle>Редактировать</#assign>
    </#if>
    <form action="${urlAction}" name="typeOfItem" method="POST">
        <table border="0">
            <tr>
                <td>Название типа:</td>
                <#if add == false>
                    <td><input required type="text" placeholder="Введите название" name="name" value="${typeOfItem.name}"/></td>
                <#else>
                    <td><input required type="text" placeholder="Введите название" name="name" /></td>
                </#if>
                <td><span>${(Request['validation.name'])!}</span>
                    <#if errorMessage?has_content>
                        <div class="error">${errorMessage}</div>
                    </#if>
                </td>
            </tr>
        </table>
        <br>
        <button type="submit">${submitTitle}</button>&nbsp;&nbsp;&nbsp;&nbsp;
        <a href="/admin/typeOfItem">
            <button type="button">Назад к списку</button>
        </a>
    </form>
</main>
</body>
</html>