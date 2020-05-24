<!DOCTYPE HTML>
<html>
<head>
    <meta charset="UTF-8"/>
    <link rel="stylesheet" href="../../../static/adminStyle.css">
    <title>Список всех пользователей</title>
</head>
<body>
<main>
    <h1>Список пользователей</h1>
    <div>
        <table border="1">
            <tr>
                <th>ФИО</th>
                <th>Логин</th>
                <th>Пароль</th>
                <th>email</th>
                <th>Телефон</th>
                <th>Количество взятого товара</th>
                <th>Паспорт</th>
                <th>Кошелёк</th>
                <th>Роль</th>
            </tr>
            <#list users as user>
                <tr>
                    <td><a href="${'/admin/users/' + user.id}">${user.getFullUserName()}</a></td>
                    <td>${user.login}</td>
                    <td>${user.password}</td>
                    <td>${user.email}</td>
                    <td>${user.phoneNumber}</td>
                    <td>${user.amountOfRentedGoods}</td>
                    <td>${user.passportData}</td>
                    <td>${user.purse}</td>
                    <td>${user.role}</td>
                </tr>
            </#list>
        </table>
    </div>
    <br>
    <div>
        <a href="/users/add"><button type="submit">Добавить нового пользователя</button></a>&nbsp;&nbsp;&nbsp;&nbsp;
        <a href="/admin"><button type="submit">Вернуться назад</button></a>
    </div>
</main>
</body>
</html>