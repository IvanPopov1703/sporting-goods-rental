<!DOCTYPE HTML>
<html>
<head>
    <meta charset="UTF-8"/>
    <title>Информация о пользвателе</title>
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
    <h1>Личный кабинет</h1>
    <#if users??>
        <table border="0">
            <tr>
                <td>Фамилия</td>
                <td>:</td>
                <td>${users.surname}</td>
            </tr>
            <tr>
                <td>Имя</td>
                <td>:</td>
                <td>${users.name}</td>
            </tr>
            <tr>
                <td>Отчество</td>
                <td>:</td>
                <td>${users.patronymic}</td>
            </tr>
            <tr>
                <td>Логин</td>
                <td>:</td>
                <td>${users.login}</td>
            </tr>
            <tr>
                <td>Кошелёк</td>
                <td>:</td>
                <td>${users.purse}</td>
            </tr>
            <tr>
                <td>email</td>
                <td>:</td>
                <td>${users.email}</td>
            </tr>
            <tr>
                <td>Телефон</td>
                <td>:</td>
                <td>${users.phoneNumber}</td>
            </tr>
            <tr>
                <td>Паспортные данные</td>
                <td>:</td>
                <td>${users.passportData}</td>
            </tr>
        </table>
        <br/>

        <div>
            <a href="${'/admin/items/' + users.id + '/edit'}">
                <button type="submit">Редактировать</button><br><br>
            </a>
            <a href="/">
                <button type="submit" style="width: 210px">Вернуться к списку товаров</button><br><br>
            </a>
            <a href="/user/myOrders">
                <button type="submit" style="width: 210px">Мои заказы</button><br><br>
            </a>
            <a href="/user/upPurse">
                <button type="submit" style="width: 210px">Пополнить кошелёк</button>
            </a>
            <a href="/user/cashWithdrawal">
                <button type="submit" style="width: 210px">Вывести деньги</button>
            </a>
        </div>
    </#if>
    <#if errorMessage?has_content>
        <div class="error">${errorMessage}</div>
    </#if>
</main>
</body>
</html>