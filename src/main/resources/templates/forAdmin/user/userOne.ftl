<!DOCTYPE HTML>
<html>
<head>
    <meta charset="UTF-8"/>
    <title>Вид определённого товара</title>
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
    <h1>Вид товара</h1>
    <#if viewOfItem??>
        <table border="0">
            <tr>
                <td>Название</td>
                <td>:</td>
                <td>${viewOfItem.name}</td>
            </tr>
        </table>
        <br/>
        <#if allowDelete>
            <form action="${'/admin/viewOfItem/' + viewOfItem.id + '/delete'}" method="POST" style="font-size: 18px;">
                Удалить запись? <input style="font-size: 15px;" type="submit" value="Да"/>
            </form>
        <#else>
            <div>
                <a href="${'/admin/viewOfItem/' + viewOfItem.id + '/edit'}"><button type="submit">Редактировать</button></a>
                &nbsp;&nbsp;
                <a href="${'/admin/viewOfItem/' + viewOfItem.id + '/delete'}"><button type="submit">Удалить</button></a><br><br>
                <a href="/admin/viewOfItem"><button type="submit" style="width: 210px">Вернуться к списку видов</button></a>
            </div>
        </#if>
    </#if>
    <#if errorMessage?has_content>
        <div class="error">${errorMessage}</div>
    </#if>
</main>
</body>
</html>