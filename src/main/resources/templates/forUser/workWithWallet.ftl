<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <title><#if up>Пополнение кошелька<#else>Вывод средств</#if></title>
</head>
<body>
    <h1><#if up>Пополнение кошелька<#else>Вывод средств</#if></h1>
    <#if up>
        <#assign urlAction>${'/user/upPurse'}</#assign>
        <#assign submitTitle>Пополнить</#assign>
    <#else>
        <#assign urlAction>${'/user/cashWithdrawal'}</#assign>
        <#assign submitTitle>Вывести</#assign>
    </#if>
    <#if errorMessage?has_content>
        <div class="error">${errorMessage}</div>
    </#if>
    <h3>Текущий баланс: ${users.purse}</h3>
    <form name="upPurse" method="POST" action="${urlAction}">
        <input required type="number" placeholder="Введите сумму" name="purse"/>
        <br><br><button type="submit">${submitTitle}</button>&nbsp;&nbsp;&nbsp;
        <a href="/user/usersOne"><button type="button">Вернуться назад</button></a>
    </form>
</body>
</html>