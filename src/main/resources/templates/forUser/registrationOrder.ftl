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
            width: 180px;
        }
    </style>
</head>
<body>
<main>
    <h1>Регистрация заказа</h1>
    <form action="" name="registrationOrder" method="POST">
        <table border="0">
            <#--<tr>
                <td>Номер заказа:</td>
                <td>${orders.id}</td>
            </tr>-->
            <tr>
                <td>Получатель:</td>
                <td>${users.name}</td>
            </tr>
            <tr>
                <td>Название товара:</td>
                <td>${items.name}</td>
            </tr>
            <tr>
                <td>Выбор экземпляра</td>
                <td>
                    <select name="items">
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
                <td>${items.сostOneHourRental}</td>
            </tr>
            <tr>
                <td>Дата взятия товара:</td>
            </tr>
            <tr>
                <td>Дата возврата товара:</td>
            </tr>
            <tr>
                <td>Общая стоимость:</td>
            </tr>
        </table>
        <br>
        <button type="submit">qq</button>&nbsp;&nbsp;&nbsp;&nbsp;
        <a href="/goodsPage">
            <button type="button">Назад к списку</button>
        </a>
    </form>
</main>
</body>
</html>