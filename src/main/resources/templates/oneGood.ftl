<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <title>Товар</title>
    <link rel="stylesheet" href="adminStyle.css">
    <style>
        tr {
            font-size: 19px;
        }
        button{
            font-size: 15px;
        }
    </style>
</head>
<body>
<h1>Страница товара</h1>
<#if items??>
    <table border="0">
        <tr>
            <td>Название</td>
            <td>:</td>
            <td>${items.name}</td>
        </tr>
        <tr>
            <td>Вид товара</td>
            <td>:</td>
            <td>${items.viewOfItem.name}</td>
        </tr>
        <tr>
            <td>Тип товара</td>
            <td>:</td>
            <td>${items.typeOfItem.name}</td>
        </tr>
        <tr>
            <td>Количество</td>
            <td>:</td>
            <td>${items.numberOfCopies}</td>
        </tr>
        <tr>
            <td>Цена 1ч</td>
            <td>:</td>
            <td>${items.сostOneHourRental}</td>
        </tr>
        <tr>
            <td>Описание товара</td>
            <td>:</td>
            <td>${items.description}</td>
        </tr>
    </table>
    <br>
    <a href="/goodsPage">
        <button type="submit">Вернуться назад</button>
    </a>
    <#if autoriz>
        <a href="${'/user/registOrder/' + items.id}">
            <button type="button">Оформить заказ</button>
        </a>
    </#if>
</#if>
</body>
</html>