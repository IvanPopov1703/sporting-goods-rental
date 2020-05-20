<!DOCTYPE HTML>
<html>
<head>
    <meta charset="UTF-8"/>
    <title><#if add>Добавить вид товара<#else>Редактировать вид товара</#if></title>
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
    <h1><#if add>Создать новый вид товара<#else>Изменить вид товара</#if></h1>
    <#if add>
        <#assign urlAction>${'/admin/viewOfItem/add'}</#assign>
        <#assign submitTitle>Создать</#assign>
    <#else>
        <#assign urlAction>${'/admin/viewOfItem/' + viewOfItem.id + '/edit'}</#assign>
        <#assign submitTitle>Редактировать</#assign>
    </#if>
    <form action="${urlAction}" name="viewOfItem" method="POST">
        <table border="0">
            <tr>
                <td>Название вида:</td>
                <#if add == false>
                    <td><input type="text" placeholder="Введите название" name="name" value="${viewOfItem.name}"/></td>
                <#else>
                    <td><input type="text" placeholder="Введите название" name="name" /></td>
                </#if>
                <td><span>${(Request['validation.name'])!}</span>
                    <#if errorMessage?has_content>
                        <div class="error">${errorMessage}</div>
                    </#if>
                </td>
            </tr>
            <tr>
                <td>День рождения:</td>
                <#if add == false>
                    <td><input type="date" placeholder="Введите название" name="birchDay" value="${viewOfItem.birchDay}"/></td>
                <#else>
                    <td><input type="date" placeholder="Введите название" name="birchDay" /></td>
                </#if>
                <td><span>${(Request['validation.birchDay'])!}</span>
                    <#if errorMessage?has_content>
                        <div class="error">${errorMessage}</div>
                    </#if>
                </td>
            </tr>
        </table>
        <br>
        <button type="submit">${submitTitle}</button>&nbsp;&nbsp;&nbsp;&nbsp;
        <a href="/admin/viewOfItem">
            <button type="button">Назад к списку</button>
        </a>
    </form>
</main>
</body>
</html>