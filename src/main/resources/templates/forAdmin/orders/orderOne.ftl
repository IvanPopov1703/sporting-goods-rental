<!DOCTYPE HTML>
<html>
<head>
    <meta charset="UTF-8"/>
    <title>Описание выбранного заказа</title>
    <style>
        body {
            margin: 0;
        }

        main {
            /*border: 1px red solid;*/
            width: 500px;
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

        table {
            font-size: 18px;
        }

        td {
            padding-top: 7px;
        }

        button {
            font-size: 15px;
        }
    </style>
</head>
<body>
<main>
    <h1>Описание выбранного заказа</h1>
    <#if orders??>
        <table border="0">
            <tr>
                <td>Номер заказа</td>
                <td>:</td>
                <td>${orders.id}</td>
            </tr>
            <tr>
                <td>Название товара</td>
                <td>:</td>
                <td>${orders.instance.items.name}</td>
            </tr>
            <tr>
                <td>Вид товара</td>
                <td>:</td>
                <td>${orders.instance.items.viewOfItem.name}</td>
            </tr>
            <tr>
                <td>Тип товара</td>
                <td>:</td>
                <td>${orders.instance.items.typeOfItem.name}</td>
            </tr>
            <tr>
                <td>Инвентарный номер экземпляра</td>
                <td>:</td>
                <td>${orders.instance.id}</td>
            </tr>
            <tr>
                <td>Стоимость 1 дня проката</td>
                <td>:</td>
                <td>${orders.instance.items.сostOneDayRental}</td>
            </tr>
            <tr>
                <td>Сумма залога</td>
                <td>:</td>
                <td>${orders.amountOfGuarantee}</td>
            </tr>
            <tr>
                <td>Общая стоимость аренды</td>
                <td>:</td>
                <td>${orders.orderCost}</td>
            </tr>
            <tr>
                <td>Штраф</td>
                <td>:</td>
                <td>${orders.fine}</td>
            </tr>
            <tr>
                <td>Дата аренды</td>
                <td>:</td>
                <td>${orders.timeOfReceiptOfItem}</td>
            </tr>
            <tr>
                <td>Планируемая дата сдачи</td>
                <td>:</td>
                <td>${orders.plannedTimeOfReturningProduct}</td>
            </tr>
        </table>
        <br/>
        <#if allowDelete>
            <form action="${'/admin/orders/' + orders.id + '/delete'}" method="POST" style="font-size: 18px;">
                Удалить запись? <input style="font-size: 15px;" type="submit" value="Да"/>
            </form>
        <#else>
            <div>
                <a href="${'/admin/orders/' + orders.id + '/delete'}">
                    <button type="submit">Удалить</button>
                </a>&nbsp;&nbsp;
                <a href="${'/admin/ordersAll/' + orders.user.id}">
                    <button type="button" style="width: 210px">Вернуться к списку заказов</button>
                </a>
            </div>
        </#if>
    </#if>
    <#if errorMessage?has_content>
        <div class="error">${errorMessage}</div>
    </#if>
</main>
</body>
</html>