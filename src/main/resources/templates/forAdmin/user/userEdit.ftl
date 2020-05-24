<!DOCTYPE HTML>
<html>
<head>
    <meta charset="UTF-8"/>
    <title><#if add>Регистрация<#else>Редактирование</#if></title>
    <style>
        body {
            margin: 0;
        }

        main {
            /*border: 1px red solid;*/
            /*width: 500px;*/
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

        table, form {
            font-size: 18px;
        }

        td {
            padding-top: 7px;
        }

        button, input {
            font-size: 15px;
        }

        .error {
            color: red;
            font-size: 18px;
        }

        select {
            font-size: 15px;
            width: 180px;
        }
    </style>
</head>
<body>
<main>
    <h1><#if add>Регистрация<#else>Редактирование</#if></h1>
    <#if add>
        <#assign urlAction>${'/users/add'}</#assign>
        <#assign submitTitle>Зарегистрироваться</#assign>
    <#else>
        <#assign urlAction>${'/users/' + users.id + '/edit'}</#assign>
        <#assign submitTitle>Сохранить</#assign>
    </#if>
    <#if errorMessage?has_content>
        <div class="error">${errorMessage}</div>
    </#if>
    <form action="${urlAction}" name="items" method="POST">
        <table border="0">
            <tr>
                <td>Фамилия:</td>
                <#if add = false || err?has_content>
                    <td><input required type="text" placeholder="Введите фамилию" name="surname"
                               value="${users.surname}"/></td>
                <#else>
                    <td><input required type="text" placeholder="Введите фамилию" name="surname"/></td>
                </#if>
                <td><span>${(Request['validation.surname'])!}</span></td>
            </tr>
            <tr>
                <td>Имя:</td>
                <#if add = false || err?has_content>
                    <td><input required type="text" placeholder="Введите имя" name="name" value="${users.name}"/></td>
                <#else>
                    <td><input required type="text" placeholder="Введите имя" name="name"/></td>
                </#if>
                <td><span>${(Request['validation.name'])!}</span></td>
            </tr>
            <tr>
                <td>Отчество:</td>
                <#if add = false || err?has_content>
                    <td><input required type="text" placeholder="Введите отчество" name="patronymic"
                               value="${users.patronymic}"/></td>
                <#else>
                    <td><input required type="text" placeholder="Введите отчество" name="patronymic"/></td>
                </#if>
                <td><span>${(Request['validation.patronymic'])!}</span></td>
            </tr>
            <tr>
                <td>Логин:</td>
                <#if add = false || err?has_content>
                    <td><input required type="text" placeholder="Введите логин" name="login" value="${users.login}"/>
                    </td>
                <#else>
                    <td><input required type="text" placeholder="Введите логин" name="login"/></td>
                </#if>
                <td><span>${(Request['validation.login'])!}</span></td>
            </tr>
            <tr>
                <td>Пароль:</td>
                <#if add = false || err?has_content>
                    <td><input required type="text" placeholder="Введите пароль" name="password"
                               value="${users.password}"/></td>
                <#else>
                    <td><input required type="text" placeholder="Введите пароль" name="password"/></td>
                </#if>
                <td><span>${(Request['validation.password'])!}</span></td>
            </tr>
            <tr>
                <td>email:</td>
                <#if add = false || err?has_content>
                    <td><input required type="email" placeholder="Введите email" name="email" value="${users.email}"/>
                    </td>
                <#else>
                    <td><input required type="email" placeholder="Введите email" name="email"/></td>
                </#if>
                <td><span>${(Request['validation.email'])!}</span></td>
            </tr>
            <tr>
                <td>Телефон:</td>
                <#if add = false || err?has_content>
                    <td><input required type="text" placeholder="Введите телефон" name="phoneNumber"
                               value="${users.phoneNumber}"/></td>
                <#else>
                    <td><input required type="text" placeholder="Введите телефон" name="phoneNumber"/></td>
                </#if>
                <td><span>${(Request['validation.phoneNumber'])!}</span></td>
            </tr>
            <tr>
                <td>Паспортные данные:</td>
                <#if add = false || err?has_content>
                    <td><input required type="text" placeholder="Паспортные данные" name="passportData"
                               value="${users.passportData}"/></td>
                <#else>
                    <td><input required type="text" placeholder="Паспортные данные" name="passportData"/></td>
                </#if>
                <td><span>${(Request['validation.passportData'])!}</span></td>
            </tr>
            <#if add = false>
                <tr>
                    <td>Кошелёк:</td>
                    <td><input type="text" name="purse" value='${users.purse}'/></td>
                </tr>
            </#if>
            <#if admin?has_content>
                <p>Выберите роль создаваемого пользователя:</p>
                <p><input required checked name="role" type="radio" value="BUYER">Пользователь</p>
                <p><input required name="role" type="radio" value="ADMIN">Админ</p>
            </#if>
            <#if add = false>
                <td><input type="hidden" name="role" value="${users.role}"/></td>
            </#if>
            <#if add = false>
                <input type="hidden" name="amountOfRentedGoods" value="${users.amountOfRentedGoods}"/>
            </#if>
        </table>
        <br>
        <button type="submit">${submitTitle}</button>&nbsp;&nbsp;&nbsp;&nbsp;
        <a href="/admin/users">
            <button type="button">Назад к списку</button>
        </a>
    </form>
</main>
</body>
</html>