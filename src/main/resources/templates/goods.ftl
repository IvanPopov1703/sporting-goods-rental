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
        </header>
        <div class="leftSort">
            <div class="leftHead">
                <h2>Фильтры</h2>
            </div>
            <div class="leftHeadOneFilter">
                <h3>Вид товара</h3>
                <!-- name - для идентификации на форме, value - значение которое будет отправлено на сервер -->
                <p><input type="checkbox" name="checkViewOfItem" value="Test">Test вид товара 1</p>
                <h3>Тип товара</h3>
                <#list typeItem as typeOfItem>
                    <!-- name - для идентификации на форме, value - значение которое будет отправлено на сервер -->
                    <p><input type="checkbox" name="checkViewOfItem" value="Test">${typeOfItem.name}</p>
                </#list>

                <div class="leftButtonSort">
                    <p><input class="leftButton" type="submit" value="Сортировать"></p>
                </div>
            </div>
        </div>
        <div class="content">
            <div class="containerOneGood">
                <div class="imageOneGood">
                    <img class="imgOneGood" src="imege/imageOneGood.jpg" alt="Картинка товара">
                </div>
                <div class="textOneGood">
                    <h2><span class="titleOneGood"><a href="oneGood.ftl">Заголовок</a></span></h2>
                    <p class="descriptGood">Двухслойная дуговая палатка с полуавтоматическим каркасом идеальна для похода. <br>
                        Палатка имеет один вход и тамбур, внутреннюю палатку и тент. Палатка легко</p>
                    <p class="costGood">4 150 тыс</p>
                </div>
            </div>
            <div class="containerOneGood">
                <div class="imageOneGood">
                    <img class="imgOneGood" src="imege/imageOneGood.jpg" alt="Картинка товара">
                </div>
                <div class="textOneGood">
                    <h2><span class="titleOneGood"><a href="oneGood.ftl">Заголовок</a></span></h2>
                    <p class="descriptGood">Двухслойная дуговая палатка с полуавтоматическим каркасом идеальна для похода. <br>
                        Палатка имеет один вход и тамбур, внутреннюю палатку и тент. Палатка легко</p>
                    <p class="costGood">4 150 тыс</p>
                </div>
            </div>
        </div>
        <footer>
            <p>© 2020 Все права защищены. Верстку сайта осуществлял Попов Иван и Губанов Павел.</p>
        </footer>
    </main>
</body>
</html>