<!DOCTYPE HTML>
<html>
<head>
    <meta charset="UTF-8"/>
    <title><#if add>Добавить товар<#else>Редактировать товар</#if></title>
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

        button, input required {
            font-size: 15px;
        }

        .error {
            color: red;
            font-size: 18px;
        }

        select {
            font-size: 15px;
            width: 180px;
        }
    </style>
</head>
<body>
<main>
    <h1><#if add>Создать новый товар<#else>Изменить товар</#if></h1>
    <#if add>
        <#assign urlAction>${'/admin/items/add'}</#assign>
        <#assign submitTitle>Создать</#assign>
    <#else>
        <#assign urlAction>${'/admin/items/' + items.id + '/edit'}</#assign>
        <#assign submitTitle>Редактировать</#assign>
    </#if>
    <#if errorMessage?has_content>
        <div class="error">${errorMessage}</div>
    </#if>
    <form action="${urlAction}" name="items" method="POST">
        <table border="0">
            <tr>
                <td>Название товара:</td>
                <#if add = false || err?has_content>
                    <td><input required type="text" placeholder="Введите название" name="name" value="${items.name}"/>
                    </td>
                <#else>
                    <td><input required type="text" placeholder="Введите название" name="name"/></td>
                </#if>
                <td><span>${(Request['validation.name'])!}</span></td>
            </tr>
            <tr>
                <td>Вид товара:</td>
                <td>
                    <select name="viewOfItem">
                        <option disabled>Выберите вид товара</option>
                        <#list viewOfItem as view>
                            <#if items?? && items.viewOfItem.id = view.id>
                                <option selected value="${view.id}">${view.name}</option>
                            <#else>
                                <option value="${view.id}">${view.name}</option>
                            </#if>
                        </#list>
                    </select>
                </td>
            </tr>
            <tr>
                <td>Тип товара:</td>
                <td>
                    <select name="typeOfItem">
                        <option disabled>Выберите тип товара</option>
                        <#list typeOfItem as type>
                            <#if items?? && items.typeOfItem.id = type.id>
                                <option selected value="${type.id}">${type.name}</option>
                            <#else>
                                <option value="${type.id}">${type.name}</option>
                            </#if>
                        </#list>
                    </select>
                </td>
            </tr>
            <tr>
                <td>Стоимость одного дня:</td>
                <#if add = false || err?has_content>
                    <td><input required type="text" placeholder="Введите стоимость" name="сostOneDayRental"
                               value="${items.сostOneDayRental}"/></td>
                <#else>
                    <td><input required type="number" placeholder="Введите стоимость" name="сostOneDayRental"/></td>
                </#if>
                <td><span>${(Request['validation.сostOneDayRental'])!}</span></td>
            </tr>
            <tr>
                <td>Описание товара:</td>
                <#if add = false || err?has_content>
                    <td><input required type="text" placeholder="Введите описание" name="description"
                               value="${items.description}"/></td>
                <#else>
                    <td><input required type="text" placeholder="Введите описание" name="description"/></td>
                </#if>
                <td><span>${(Request['validation.description'])!}</span></td>
            </tr>
        </table>
        <br>
        <button type="submit">${submitTitle}</button>&nbsp;&nbsp;&nbsp;&nbsp;
        <a href="/admin/items">
            <button type="button">Назад к списку</button>
        </a>
    </form>
</main>
</body>
</html>