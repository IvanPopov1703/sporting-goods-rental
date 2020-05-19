<!DOCTYPE HTML>
<html>
<head>
    <meta charset="UTF-8"/>
    <title>Описание выбранного экземпляра</title>
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
    <h1>Описание выбранного экземпляра</h1>
    <#if instance??>
        <table border="0">
            <tr>
                <td>Инвентарный номер</td>
                <td>:</td>
                <td>${instance.id}</td>
            </tr>
            <tr>
                <td>Название товара</td>
                <td>:</td>
                <td>${instance.items.name}</td>
            </tr>
            <tr>
                <td>Вид товара</td>
                <td>:</td>
                <td>${instance.items.viewOfItem.name}</td>
            </tr>
            <tr>
                <td>Тип товара</td>
                <td>:</td>
                <td>${instance.items.typeOfItem.name}</td>
            </tr>
            <tr>
                <td>Часы пользования</td>
                <td>:</td>
                <td>${instance.hoursOfUse}</td>
            </tr>
            <tr>
                <td>Закупочная стоимость</td>
                <td>:</td>
                <td>${instance.purchasePrice}</td>
            </tr>
        </table>
        <br/>
        <#if allowDelete>
            <form action="${'/admin/instance/' + instance.id + '/delete'}" method="POST" style="font-size: 18px;">
                Удалить запись? <input style="font-size: 15px;" type="submit" value="Да"/>
            </form>
        <#else>
            <div>
                <a href="${'/admin/instance/' + instance.id + '/edit'}">
                    <button type="submit">Редактировать</button>
                </a>
                &nbsp;&nbsp;
                <a href="${'/admin/instance/' + instance.id + '/delete'}">
                    <button type="submit">Удалить</button>
                </a><br><br>
                <a href="/admin/instance">
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