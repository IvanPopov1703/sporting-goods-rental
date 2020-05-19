<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <title>Товар</title>
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
</#if>
</body>
</html>