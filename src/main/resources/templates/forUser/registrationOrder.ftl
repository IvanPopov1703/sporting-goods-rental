<!DOCTYPE HTML>
<html>
<head>
    <meta charset="UTF-8"/>
    <title>Регистрация заказа</title>
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

        .error {
            color: red;
            font-size: 18px;
        }

        select {
            font-size: 15px;
            width: 280px;
        }
    </style>
</head>
<body>
<main>
    <h1>Регистрация заказа</h1>
    <form action="/user/registOrder/add" name="orders" method="POST">
        <table border="0">
            <#--<tr>
                <td>Номер заказа:</td>
                <td>${orders.id}</td>
            </tr>-->
            <tr>
                <td>Получатель:</td>
                <td>${user.getFullUserName()}</td>
                <td><input type="hidden" name="user" value="${user.id}"/></td>
            </tr>
            <tr>
                <td>Название товара:</td>
                <td>${items.name}</td>
            </tr>
            <tr>
                <td>Вид товара:</td>
                <td>${items.typeOfItem.name}</td>
            </tr>
            <tr>
                <td>Выбор экземпляра</td>
                <td>
                    <select name="instance">
                        <option disabled>Выберите экземпляр товара</option>
                        <#list instance as inst>
                            <option value="${inst.id}">${inst.items.name} / (Часы пользования: ${inst.hoursOfUse})
                            </option>
                        </#list>
                    </select>
                </td>
            </tr>
            <tr>
                <td>Сумма залога:</td>
                <td>${items.amountOfGuarantee}</td>
            </tr>
            <tr>
                <td>Стоимость одного дня:</td>
                <td>${items.сostOneDayRental}</td>
            </tr>
            <tr>
                <td>Дата взятия товара:</td>
                <td>${currentDate}</td>
            </tr>
            <tr>
                <td>Дата возврата товара:</td>
                <td><input type="date" required name="plannedTimeOfReturningProduct" /></td>
                <#if err?has_content>
                    <td><span>${err}</span></td>
                </#if>
            </tr>
        </table>
        <br>
        <button type="submit">Оформить заказ</button>&nbsp;&nbsp;&nbsp;&nbsp;
        <a href="/">
            <button type="button">Назад к списку</button>
        </a>
    </form>
</main>
</body>
</html>