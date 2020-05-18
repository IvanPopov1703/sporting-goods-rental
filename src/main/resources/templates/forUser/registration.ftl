<!DOCTYPE HTML>
<html>
<head>
    <meta charset="UTF-8"/>
    <title><#if reg>Регистрация<#else>Редактирование данных</#if></title>
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
    <h1><#if reg>Регистрация<#else>Редактирование данных</#if></h1>
    <#if reg>
        <#assign urlAction>${'/registr'}</#assign>
        <#assign submitTitle>Зарегистрироваться</#assign>
    <#else>
        <#assign urlAction>${'/admin/items/' + items.id + '/edit'}</#assign>
        <#assign submitTitle>Сохранить</#assign>
    </#if>
    <form action="${urlAction}" name="items" method="POST">
        <table border="0">
            <#if errorMessage?has_content>
                <tr>
                    <td>
                        <div class="error">${errorMessage}</div>
                    </td>
                </tr>
            </#if>
            <tr>
                <td>Фамилия:</td>
                <#if reg = false>
                    <td><input type="text" placeholder="Введите фамилию" name="surname" value="${user.surname}"/></td>
                <#else>
                    <td><input type="text" placeholder="Введите фамилию" name="surname"/></td>
                </#if>
                <td><span>${(Request['validation.surname'])!}</span></td>
            </tr>
            <tr>
                <td>Имя:</td>
                <#if reg = false>
                    <td><input type="text" placeholder="Введите имя" name="name" value="${user.name}"/></td>
                <#else>
                    <td><input type="text" placeholder="Введите имя" name="name"/></td>
                </#if>
                <td><span>${(Request['validation.name'])!}</span></td>
            </tr>
            <tr>
                <td>Отчество:</td>
                <#if reg = false>
                    <td><input type="text" placeholder="Введите отчество" name="patronymic" value="${user.patronymic}"/></td>
                <#else>
                    <td><input type="text" placeholder="Введите отчество" name="patronymic"/></td>
                </#if>
                <td><span>${(Request['validation.patronymic'])!}</span></td>
            </tr>
            <tr>
                <td>Логин:</td>
                <#if reg = false>
                    <td><input type="text" placeholder="Введите логин" name="login" value="${user.login}"/></td>
                <#else>
                    <td><input type="text" placeholder="Введите логин" name="login"/></td>
                </#if>
                <td><span>${(Request['validation.login'])!}</span></td>
            </tr>
            <tr>
                <td>Пароль:</td>
                <#if reg = false>
                    <td><input type="text" placeholder="Введите пароль" name="password" value="${user.password}"/></td>
                <#else>
                    <td><input type="text" placeholder="Введите пароль" name="password"/></td>
                </#if>
                <td><span>${(Request['validation.password'])!}</span></td>
            </tr>
            <tr>
                <td>email:</td>
                <#if reg = false>
                    <td><input type="email" placeholder="Введите email" name="email" value="${user.email}"/></td>
                <#else>
                    <td><input type="email" placeholder="Введите email" name="email"/></td>
                </#if>
                <td><span>${(Request['validation.email'])!}</span></td>
            </tr>
            <tr>
                <td>Телефон:</td>
                <#if reg = false>
                    <td><input type="text" placeholder="Введите телефон" name="phoneNumber" value="${user.phoneNumber}"/></td>
                <#else>
                    <td><input type="text" placeholder="Введите телефон" name="phoneNumber"/></td>
                </#if>
                <td><span>${(Request['validation.phoneNumber'])!}</span></td>
            </tr>
            <tr>
                <td>Паспортные данные:</td>
                <#if reg = false>
                    <td><input type="text" placeholder="Паспортные данные" name="passportData" value="${user.passportData}"/></td>
                <#else>
                    <td><input type="text" placeholder="Паспортные данные" name="passportData"/></td>
                </#if>
                <td><span>${(Request['validation.passportData'])!}</span></td>
            </tr>
        </table>
        <br>
        <button type="submit">${submitTitle}</button>&nbsp;&nbsp;&nbsp;&nbsp;
        <a href="/goodsPage">
            <button type="button">Назад к списку</button>
        </a>
    </form>
</main>
</body>
</html>