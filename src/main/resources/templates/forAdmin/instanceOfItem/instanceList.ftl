<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <title>Список всех экземпляров товаров</title>
    <link rel="stylesheet" href="adminStyle.css">
</head>
<body>
<main>
    <h1>Список экземпляров</h1>
    <div>
        <table border="1">
            <tr>
                <th>Инвентарный номер</th>
                <th>Название товара</th>
                <th>Вид товара</th>
                <th>Тип товара</th>
                <th>Часы пользования</th>
                <th>Закупочная стоимость</th>
            </tr>
            <#list instance as inst>
                <tr>
                    <td><a href="${'/admin/instance/' + inst.id}">${inst.id}</a></td>
                    <td>${inst.items.name}</td>
                    <td>${inst.items.viewOfItem.name}</td>
                    <td>${inst.items.typeOfItem.name}</td>
                    <td>${inst.hoursOfUse}</td>
                    <td>${inst.purchasePrice}</td>
                </tr>
            </#list>
        </table>
    </div>
    <br>
    <div>
        <a href="/admin/instance/add">
            <button type="submit">Добавить новый экземпляр товара</button>
        </a>&nbsp;&nbsp;&nbsp;&nbsp;
        <a href="/admin">
            <button type="submit">Вернуться назад</button>
        </a>
    </div>
</main>
</body>
</html>