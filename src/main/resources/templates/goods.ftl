<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <title>Все товары</title>
    <link rel="stylesheet" href="style.css">
</head>
<body>
<main>
    <header>
        <img class="imgHead" src="imege/GoodsHead.png" alt="Зоголовок">
        <div class="login">
            <#if isAut?has_content>
                <a href="/user/users">
                    Моя страница
                </a>&nbsp;&nbsp;&nbsp;&nbsp;
                <a style="font-size: 20px;">Кошелёк: ${users.purse} руб.</a>
                &nbsp;&nbsp;&nbsp;&nbsp;
                <a href="/logout">
                    <button>Выйти</button>
                </a>
            <#else >
                <a href="/login">
                    <button>Войти</button>
                </a>
                &nbsp;&nbsp;
                <a href="/registr">
                    <button>Регистрация</button>
                </a>
            </#if>
        </div>
    </header>
    <div class="leftSort">
        <div class="leftHead">
            <h2>Фильтры</h2>
        </div>
        <div class="leftHeadOneFilter">
            <h3>Вид товара</h3>
            <#list viewItem as view>
                <p><input type="checkbox" name="checkViewOfItem" value="${view.id}">${view.name}</p>
            </#list>
            <h3>Тип товара</h3>
            <#list typeItem as type>
                <!-- name - для идентификации на форме, value - значение которое будет отправлено на сервер -->
                <p><input type="checkbox" name="checkTypeOfItem" value="Test">${type.name}</p>
            </#list>
            <div class="leftButtonSort">
                <p><input class="leftButton" type="submit" value="Сортировать"></p>
            </div>
        </div>
    </div>
    <div class="content">
        <#list items as item>
            <div class="containerOneGood">
                <div class="imageOneGood">
                    <img class="imgOneGood" src="imege/imageOneGood.jpg" alt="Картинка товара">
                </div>
                <div class="textOneGood">
                    <h2><span class="titleOneGood"><a href="${'/goodOnePage/items/' + item.id}">${item.name}</a></span></h2>
                    <p class="descriptGood">${item.description}</p>
                    <p class="costGood">${item.сostOneHourRental} в час</p>
                </div>
            </div>
        </#list>
    </div>
    <footer>
        <p>© 2020 Все права защищены. Верстку сайта осуществлял Попов Иван.</p>
    </footer>
</main>
</body>
</html>