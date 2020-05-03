<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <title>Работа с видом товара</title>
</head>
<body>
<ol>
    <#list viewOfItem as view>
        <li>${view.name}</li>
    </#list>
</ol>
</body>
</html>