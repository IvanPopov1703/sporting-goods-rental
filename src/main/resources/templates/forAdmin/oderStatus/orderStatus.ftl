<#import "/spring.ftl" as spring />
<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <title>Работа со статусам</title>
</head>
<body>
<h2>Form Data Binding and Validation</h2>

<@spring.bind "orderStatus"/>
<#if orderStatus?? && noErrors??>
    <#--Your submitted data<br>
    Role name: ${orderStatus.name}<br>-->
    <ol>
        <li>${orderStatus.name}</li>
    </ol>
<#else>
    <form action="/admin/status/edit" method="post">
        Role name:<br>
        <@spring.formInput "orderStatus.name"/>
        <@spring.showErrors "<br>"/>
        <br><br>
        <input type="submit" value="Submit">
    </form>
</#if>
</body>
</html>