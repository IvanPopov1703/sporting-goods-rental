<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <title>Мои заказы</title>
    <link rel="stylesheet" href="adminStyle.css">
    <style>
        .error {
            color: red;
        }
    </style>
</head>
<body>
<main>
    <#if ordersPending??>
        <h1>Мои заказы</h1>
        <br>
        <h3>Ожидают выдачи</h3>
        <div>
            <table border="1">
                <tr>
                    <th>Номер заказа</th>
                    <th>Название товара</th>
                    <th>Вид товара</th>
                    <th>Тип товара</th>
                    <th>Инвентарный номер экземпляра</th>
                    <th>Стоимость 1 дня проката</th>
                    <th>Сумма залога</th>
                    <th>Общая стоимость аренды</th>
                    <th>Дата аренды</th>
                    <th>Планируемая дата сдачи</th>
                    <th>Действие</th>
                </tr>
                <#list ordersPending as order>
                    <tr>
                        <td>${order.id}</td>
                        <td>${order.instance.items.name}</td>
                        <td>${order.instance.items.viewOfItem.name}</td>
                        <td>${order.instance.items.typeOfItem.name}</td>
                        <td>${order.instance.id}</td>
                        <td>${order.instance.items.сostOneDayRental}</td>
                        <td>${order.amountOfGuarantee}</td>
                        <td>${order.orderCost}</td>
                        <td>${order.timeOfReceiptOfItem}</td>
                        <td>${order.plannedTimeOfReturningProduct}</td>
                        <td>
                            <#if admin?has_content>
                                <a href="${'/admin/orders/' + order.id}">
                                    <button type="button">К заказу</button>
                                </a>
                            <#else>
                                <a href="${'/user/pickUpOrder/' + order.id}">
                                    <button type="button">Забрать товар</button>
                                </a>
                            </#if>
                        </td>
                    </tr>
                </#list>
            </table>
        </div>
    </#if>
    <br>
    <#if ordersIssued??>
        <h3>Выданные товары</h3>
        <div>
            <table border="1">
                <tr>
                    <th>Номер заказа</th>
                    <th>Название товара</th>
                    <th>Вид товара</th>
                    <th>Тип товара</th>
                    <th>Инвентарный номер экземпляра</th>
                    <th>Стоимость 1 дня проката</th>
                    <th>Сумма залога</th>
                    <th>Общая стоимость аренды</th>
                    <th>Дата аренды</th>
                    <th>Планируемая дата сдачи</th>
                    <th>Действие</th>
                </tr>
                <#list ordersIssued as order>
                    <tr>
                        <td>${order.id}</td>
                        <td>${order.instance.items.name}</td>
                        <td>${order.instance.items.viewOfItem.name}</td>
                        <td>${order.instance.items.typeOfItem.name}</td>
                        <td>${order.instance.id}</td>
                        <td>${order.instance.items.сostOneDayRental}</td>
                        <td>${order.amountOfGuarantee}</td>
                        <td>${order.orderCost}</td>
                        <td>${order.timeOfReceiptOfItem}</td>
                        <td>${order.plannedTimeOfReturningProduct}</td>
                        <td>
                            <#if admin?has_content>
                                <a href="${'/admin/orders/' + order.id}">
                                    <button type="button">К заказу</button>
                                </a>
                            <#else>
                                <a href="${'/user/returnWithIssuedStatus/' + order.id}">
                                    <button type="button">Сдать товар</button>
                                </a>
                            </#if>
                        </td>
                    </tr>
                </#list>
            </table>
        </div>
    </#if>
    <br>
    <#if ordersExpired??>
        <h3>Просроченные товары</h3>
        <div>
            <table border="1">
                <tr>
                    <th>Номер заказа</th>
                    <th>Название товара</th>
                    <th>Вид товара</th>
                    <th>Тип товара</th>
                    <th>Инвентарный номер экземпляра</th>
                    <th>Стоимость 1 дня проката</th>
                    <th>Сумма залога</th>
                    <th>Общая стоимость аренды</th>
                    <th>Штраф</th>
                    <th>Дата аренды</th>
                    <th>Планируемая дата сдачи</th>
                    <th>Действие</th>
                </tr>
                <#list ordersExpired as order>
                    <tr>
                        <td>${order.id}</td>
                        <td>${order.instance.items.name}</td>
                        <td>${order.instance.items.viewOfItem.name}</td>
                        <td>${order.instance.items.typeOfItem.name}</td>
                        <td>${order.instance.id}</td>
                        <td>${order.instance.items.сostOneDayRental}</td>
                        <td>${order.amountOfGuarantee}</td>
                        <td>${order.orderCost}</td>
                        <td>${order.fine}</td>
                        <td>${order.timeOfReceiptOfItem}</td>
                        <td>${order.plannedTimeOfReturningProduct}</td>
                        <td>
                            <#if admin?has_content>
                                <a href="${'/admin/orders/' + order.id}">
                                    <button type="button">К заказу</button>
                                </a>
                            <#else>
                                <a href="${'/user/returnWithExpiredStatus/' + order.id}">
                                    <button type="button">Сдать товар</button>
                                </a>
                            </#if>
                        </td>
                    </tr>
                </#list>
            </table>
        </div>
    </#if>
    <#if err?has_content>
        <br>
        <tr>
            <td>
                <div class="error">${err}</div>
            </td>
        </tr>
        <br>
    </#if>
    <br>
    <#if ordersHistory??>
        <h3>Сданные товары</h3>
        <div>
            <table border="1">
                <tr>
                    <th>Номер заказа</th>
                    <th>Название товара</th>
                    <th>Вид товара</th>
                    <th>Тип товара</th>
                    <th>Инвентарный номер экземпляра</th>
                    <th>Стоимость 1 дня проката</th>
                    <th>Сумма залога</th>
                    <th>Общая стоимость аренды</th>
                    <th>Штраф</th>
                    <th>Дата аренды</th>
                    <th>Планируемая дата сдачи</th>
                    <th>Реальная дата сдачи</th>
                    <#if admin?has_content>
                        <th>Действие</th>
                    </#if>
                </tr>
                <#list ordersHistory as order>
                    <tr>
                        <td>${order.id}</td>
                        <td>${order.instance.items.name}</td>
                        <td>${order.instance.items.viewOfItem.name}</td>
                        <td>${order.instance.items.typeOfItem.name}</td>
                        <td>${order.instance.id}</td>
                        <td>${order.instance.items.сostOneDayRental}</td>
                        <td>${order.amountOfGuarantee}</td>
                        <td>${order.orderCost}</td>
                        <td>${order.fine}</td>
                        <td>${order.timeOfReceiptOfItem}</td>
                        <td>${order.plannedTimeOfReturningProduct}</td>
                        <td>${order.realTimeOfReturningProduct}</td>
                        <td>
                            <#if admin?has_content>
                                <a href="${'/admin/orders/' + order.id}">
                                    <button type="button">К заказу</button>
                                </a>
                            </#if>
                        </td>
                    </tr>
                </#list>
            </table>
        </div>
    </#if>
    <br>
    <div>
        <a href="/">
            <br>
            <button type="submit">Вернуться назад</button>
        </a>
    </div>
    <br>
</main>
</body>
</html>