<!DOCTYPE HTML>
<html>
<head>
    <meta charset="UTF-8"/>
    <link rel="stylesheet" href="adminStyle.css">
    <title>Список всех статусов товара</title>
</head>
<body>
<main>
    <h1>Список статусов товара</h1>
    <div>
        <ol>
            <#list statusOrder as status>
                <h4><li class="adminLi"><a href="${'/admin/statusOrder/' + status.id}">${status.name}</a></li></h4>
            </#list>
        </ol>
    </div>
    <div>
        <a href="/admin/statusOrder/add"><button type="submit">Добавить новый статус</button></a>&nbsp;&nbsp;&nbsp;&nbsp;
        <a href="/admin"><button type="submit">Вернуться назад</button></a>
    </div>
</main>
</body>
</html>