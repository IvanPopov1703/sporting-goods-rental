<!DOCTYPE HTML>
<html>
<head>
    <meta charset="UTF-8"/>
    <title>Описание выбранного товара</title>
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
    <h1>Описание выбранного товара</h1>
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
        <br/>
        <#if allowDelete>
            <form action="${'/admin/items/' + items.id + '/delete'}" method="POST" style="font-size: 18px;">
                Удалить запись? <input style="font-size: 15px;" type="submit" value="Да"/>
            </form>
        <#else>
            <div>
                <a href="${'/admin/items/' + items.id + '/edit'}">
                    <button type="submit">Редактировать</button>
                </a>
                &nbsp;&nbsp;
                <a href="${'/admin/items/' + items.id + '/delete'}">
                    <button type="submit">Удалить</button>
                </a><br><br>
                <a href="/admin/items">
                    <button type="submit" style="width: 210px">Вернуться к списку типов</button>
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