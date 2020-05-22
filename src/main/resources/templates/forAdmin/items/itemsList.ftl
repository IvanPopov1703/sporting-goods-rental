<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <title>Список всех товаров</title>
    <link rel="stylesheet" href="adminStyle.css">
</head>
<body>
<main>
    <h1>Список товаров</h1>
    <div>
        <table border="1">
            <tr>
                <th>Название товара</th>
                <th>Вид товара</th>
                <th>Тип товара</th>
                <th>Стоимость одного дня:</th>
            </tr>
            <#list items as item>
                <tr>
                    <td><a href="${'/admin/items/' + item.id}">${item.name}</a></td>
                    <td>${item.viewOfItem.name}</td>
                    <td>${item.typeOfItem.name}</td>
                    <td>${item.сostOneDayRental}</td>
                </tr>
            </#list>
        </table>
    </div>
    <br>
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