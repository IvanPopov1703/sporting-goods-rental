<!DOCTYPE HTML>
<html>
<head>
    <meta charset="UTF-8"/>
    <title><#if add>Добавить статус заказа<#else>Редактировать статус товара</#if></title>
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
    <h1><#if add>Создать новый статус товара<#else>Изменить статус</#if></h1>
    <#if add>
        <#assign urlAction>${'/admin/statusOrder/add'}</#assign>
        <#assign submitTitle>Создать</#assign>
    <#else>
        <#assign urlAction>${'/admin/statusOrder/' + statusOrder.id + '/edit'}</#assign>
        <#assign submitTitle>Редактировать</#assign>
    </#if>
    <form action="${urlAction}" name="statusOrder" method="POST">
        <table border="0">
            <tr>
                <td>Название статуса:</td>
                <#if add == false>
                    <td><input type="text" placeholder="Введите название" name="name" value="${statusOrder.name}"/></td>
                <#else>
                    <td><input type="text" placeholder="Введите название" name="name" /></td>
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
        <a href="/admin/statusOrder">
            <button type="button">Назад к списку</button>
        </a>
    </form>
</main>
</body>
</html>