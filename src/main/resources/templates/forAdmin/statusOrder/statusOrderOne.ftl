<!DOCTYPE HTML>
<html>
<head>
    <meta charset="UTF-8"/>
    <title>Статус товара</title>
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

        table{
            font-size: 18px;
        }

        td{
            padding-top: 7px;
        }

        button {
            font-size: 15px;
        }
    </style>
</head>
<body>
<main>
    <h1>Статус товара</h1>
    <#if statusOrder??>
        <table border="0">
            <tr>
                <td>Название статуса</td>
                <td>:</td>
                <td>${statusOrder.name}</td>
            </tr>
        </table>
        <br/>
        <#if allowDelete>
            <form action="${'/admin/statusOrder/' + statusOrder.id + '/delete'}" method="POST" style="font-size: 18px;">
                Удалить запись? <input style="font-size: 15px;" type="submit" value="Да"/>
            </form>
        <#else>
            <div>
                <a href="${'/admin/statusOrder/' + statusOrder.id + '/edit'}"><button type="submit">Редактировать</button></a>
                &nbsp;&nbsp;
                <a href="${'/admin/statusOrder/' + statusOrder.id + '/delete'}"><button type="submit">Удалить</button></a><br><br>
                <a href="/admin/statusOrder"><button type="submit" style="width: 210px">Вернуться к списку статусов</button></a>
            </div>
        </#if>
    </#if>
    <#if errorMessage?has_content>
        <div class="error">${errorMessage}</div>
    </#if>
</main>
</body>
</html>