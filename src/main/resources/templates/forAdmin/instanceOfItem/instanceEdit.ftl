<!DOCTYPE HTML>
<html>
<head>
    <meta charset="UTF-8"/>
    <title><#if add>Добавить экземпляр товара<#else>Редактировать экземпляр товара</#if></title>
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

        input{
            width: 250px;
        }

        .error {
            color: red;
            font-size: 18px;
        }

        select {
            font-size: 15px;
            width: 300px;
        }
    </style>
</head>
<body>
<main>
    <h1><#if add>Создать новый экземпляр товара<#else>Изменить экземпляр товара</#if></h1>
    <#if add>
        <#assign urlAction>${'/admin/instance/add'}</#assign>
        <#assign submitTitle>Создать</#assign>
    <#else>
        <#assign urlAction>${'/admin/instance/' + instance.id + '/edit'}</#assign>
        <#assign submitTitle>Редактировать</#assign>
    </#if>
    <form action="${urlAction}" name="instance" method="POST">
        <table border="0">
            <#if errorMessage?has_content>
                <tr>
                    <td>
                        <div class="error">${errorMessage}</div>
                    </td>
                </tr>
            </#if>
            <tr>
                <td>Инвентарный номер:</td>
                <#if add = false>
                    <td>${instance.id}</td>
                </#if>
            </tr>
            <tr>
                <td>Товар:</td>
                <td>
                    <select name="items">
                        <option disabled>Выберите тип товара</option>
                        <#list items as item>
                            <#if instence?? && instence.items.id = item.id>
                                <option selected value="${item.id}">${item.name} / (Вид: ${item.viewOfItem.name})
                                    / (Тип: ${item.typeOfItem.name})
                                </option>
                            <#else>
                                <option value="${item.id}">${item.name} / (Вид: ${item.viewOfItem.name})
                                    / (Тип: ${item.typeOfItem.name})
                                </option>
                            </#if>
                        </#list>
                    </select>
                </td>
            </tr>
            <tr>
                <td>Часы пользования:</td>
                <#if add = false || err?has_content>
                    <td><input required type="text" placeholder="Введите количество часов" name="hoursOfUse"
                               value="${instance.hoursOfUse}"/></td>
                <#else>
                    <td><input required type="number" placeholder="Введите количество часов" name="hoursOfUse"/></td>
                </#if>
                <td><span>${(Request['validation.hoursOfUse'])!}</span></td>
            </tr>
            <tr>
                <td>Закупочная стоимость:</td>
                <#if add = false || err?has_content>
                    <td><input required type="text" placeholder="Введите стоимость" name="purchasePrice"
                               value="${instance.purchasePrice}"/></td>
                <#else>
                    <td><input required type="number" placeholder="Введите стоимость" name="purchasePrice"/></td>
                </#if>
                <td><span>${(Request['validation.purchasePrice'])!}</span></td>
            </tr>
        </table>
        <br>
        <button type="submit">${submitTitle}</button>&nbsp;&nbsp;&nbsp;&nbsp;
        <a href="/admin/instance">
            <button type="button">Назад к списку</button>
        </a>
    </form>
</main>
</body>
</html>