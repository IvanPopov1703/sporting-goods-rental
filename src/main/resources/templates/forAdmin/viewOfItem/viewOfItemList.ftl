<!DOCTYPE HTML>
<html>
<head>
    <meta charset="UTF-8"/>
    <link rel="stylesheet" href="adminStyle.css">
    <title>Список всех видов товара</title>
</head>
<body>
<main>
    <h1>Список видов товара</h1>
    <div>
        <ol>
            <#list viewOfItem as view>
                <h4><li class="adminLi"><a href="${'/admin/viewOfItem/' + view.id}">${view.name}</a></li></h4>
            </#list>
        </ol>
    </div>
    <div>
        <a href="/admin/viewOfItem/add"><button type="submit">Добавить новый вид</button></a>&nbsp;&nbsp;&nbsp;&nbsp;
        <a href="/admin"><button type="submit">Вернуться назад</button></a>
    </div>
</main>
</body>
</html>