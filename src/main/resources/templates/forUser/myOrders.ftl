<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <title>Мои заказы</title>
    <link rel="stylesheet" href="adminStyle.css">
</head>
<body>
<main>
    <#if orders??>
    <h1>Мои заказы</h1>
    <br>
    <h3>Ожидают выдачи</h3>
    <div>
        <table border="1">
            <tr>
                <th>Название товара</th>
                <th>Вид товара</th>
                <th>Тип товара</th>
                <th>Количество арендованных экземпляров</th>
                <th>Стоимость 1 ч проката</th>
                <th>Общая стоимость аренды</th>
                <th>Сумма залога</th>
                <th>Дата аренды</th>
                <th>Планируемая дата сдачи</th>
                <th>Действие</th>
            </tr>
            <#list orders as order>
                <tr>
                    <td><a href="${'/admin/items/' + order.id}">${order.name}</a></td>
                    <td>${order.viewOfItem.name}</td>
                    <td>${order.typeOfItem.name}</td>
                    <td>${order.item.numberOfCopies}</td>
                    <td>${order.item.сostOneHourRental}</td>
                    <td>${order.orderCost}</td>
                    <td>${order.amountOfGuarantee}</td>
                    <td>Дата</td>
                    <td>Дата</td>
                    <td><a href="/">Забрать товар</a></td>
                </tr>
            </#list>
        </table>
    </div>
    <br>
    <h3>Выданные товары</h3>
    <div>
        <table border="1">
            <tr>
                <th>Название товара</th>
                <th>Вид товара</th>
                <th>Тип товара</th>
                <th>Количество арендованных экземпляров</th>
                <th>Стоимость 1 ч проката</th>
                <th>Общая стоимость аренды</th>
                <th>Сумма залога</th>
                <th>Дата аренды</th>
                <th>Планируемая дата сдачи</th>
                <th>Действие</th>
            </tr>
            <#list orders as order>
                <tr>
                    <td><a href="${'/admin/items/' + order.id}">${order.name}</a></td>
                    <td>${order.viewOfItem.name}</td>
                    <td>${order.typeOfItem.name}</td>
                    <td>${order.item.numberOfCopies}</td>
                    <td>${order.item.сostOneHourRental}</td>
                    <td>${order.orderCost}</td>
                    <td>${order.amountOfGuarantee}</td>
                    <td>Дата</td>
                    <td>Дата</td>
                    <td><a href="/">Сдать товар</a></td>
                </tr>
            </#list>
        </table>
    </div>
    <br>
    <h3>Просроченные товары</h3>
    <div>
        <table border="1">
            <tr>
                <th>Название товара</th>
                <th>Вид товара</th>
                <th>Тип товара</th>
                <th>Количество арендованных экземпляров</th>
                <th>Стоимость 1 ч проката</th>
                <th>Общая стоимость аренды</th>
                <th>Сумма залога</th>
                <th>Дата аренды</th>
                <th>Планируемая дата сдачи</th>
                <th>Действие</th>
            </tr>
            <#list orders as order>
                <tr>
                    <td><a href="${'/admin/items/' + order.id}">${order.name}</a></td>
                    <td>${order.viewOfItem.name}</td>
                    <td>${order.typeOfItem.name}</td>
                    <td>${order.item.numberOfCopies}</td>
                    <td>${order.item.сostOneHourRental}</td>
                    <td>${order.orderCost}</td>
                    <td>${order.amountOfGuarantee}</td>
                    <td>Дата</td>
                    <td>Дата</td>
                    <td><a href="/">Сдать товар</a></td>
                </tr>
            </#list>
        </table>
    </div>
    </#if>
    <div>
        <a href="/admin/items/add">
            <button type="submit">Добавить новый товар</button>
        </a>&nbsp;&nbsp;&nbsp;&nbsp;
        <a href="/admin">
            <button type="submit">Вернуться назад</button>
        </a>
    </div>
</main>
</body>
</html>