<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <title>Все товары</title>
    <link rel="stylesheet" href="style.css">
    <style>
        body {
            margin: 0;
        }

        /*Вся страница*/
        main {
            /*border: black solid 1px;*/
            /*height: 600px;*/
            min-height: 600px;
            max-height: 5000px;
            /*background: ghostwhite;*/
        }
        a {
            font-size: 20px;
        }

        button {
            font-size: 15px;
        }

        /**************************************Описание блока сортировок НАЧАЛО***************************************/
        /*Левый контейнер для сортировки */
        .leftSort {
            /*border: 1px solid blue;*/
            width: 300px;
            /*height: 700px;*/
            min-height: 300px;
            max-height: 600px;
            float: left;
            margin-left: 32px;
            margin-top: 30px;
            border-radius: 10px;
            background: rgba(219, 219, 219, 0.74);
        }

        .leftHead {
            border-radius: 10px;
            height: 35px;
            margin-top: -20px;
            background: rgba(199, 199, 199, 0.74);
            text-align: center;
        }

        .leftHeadOneFilter {
            padding-left: 10px;
        }

        p {
            font-size: 20px;
        }

        .leftButtonSort {
        / / border: 1 px solid darkorange;
            margin-left: 75px;
            margin-top: 40px;
        }

        /**************************************Описание блока сортировок КОНЕЦ***************************************/

        /*Контент*/
        .content {
            /*border: green 1px solid;*/
            /*height: 220px;*/
            min-height: 220px;
            max-height: 2000px;
            width: 1130px;
            float: left;
            margin-left: 20px;
            margin-top: 40px;
            border-radius: 10px;
            background: rgba(219, 219, 219, 0.74);
        }

        /**************************************Описание блока контейнера с товаром начало********************************/
        .containerOneGood {
            /*border: 1px solid black;*/
            height: 200px;
            /*background: rgba(219, 219, 219, 0.74);*/
            background: rgba(199, 199, 199, 0.74);
            border-radius: 10px;
            margin-bottom: 10px;
        }

        .titleOneGood {
            color: red;
        }

        .textOneGood {
            /*border: blue 1px solid;*/
            height: 172px;
            width: 850px;
            float: left;
            margin-top: 10px;
            margin-left: 10px;
        }

        .costGood {
            color: #b46d3e;
        }

        .butt{
            /*border: 1px solid red;*/
            width: 200px;
            height: 100px;
            float: left;
            margin-left: 20px;
            margin-top: 10px;
        }
    </style>
</head>
<body>
<main>
    <div class="leftSort">
        <div class="leftHead">
            <h2>Фильтры</h2>
        </div>
        <div class="leftHeadOneFilter">
            <h3>Вид товара</h3>
            <form method="get" action="${'/sort/'}">
                <#list viewItem as view>
                    <p><input checked type="radio" name="viewItem" value="${view.id}">${view.name}</p>
                </#list>
                <h3>Тип товара</h3>
                <#list typeItem as type>
                    <p><input checked type="radio" name="typeItem" value="${type.id}">${type.name}</p>
                </#list>
                <div class="leftButtonSort">
                    <button type="submit">Сортировать</button>
                </div>
            </form>
        </div>
    </div>
    <div class="content">
        <#list items as item>
            <div class="containerOneGood">
                <div class="textOneGood">
                    <h2><span class="titleOneGood"><a href="${'/goodOnePage/items/' + item.id}">${item.name}</a></span>
                    </h2>
                    <p class="descriptGood">${item.description}</p>
                    <p class="costGood">${item.сostOneDayRental} в сутки</p>
                </div>
            </div>
        </#list>
    </div>
    <div class="butt">
        <a href="/">
            <button type="button">Вернуться назад</button>
        </a>
    </div>
</main>
</body>
</html>