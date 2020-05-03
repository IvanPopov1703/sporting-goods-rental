<#import "/spring.ftl" as spring />
<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <title>Работа с ролью пользователя</title>
</head>
<body>
<h2>Form Data Binding and Validation</h2>

<@spring.bind "role"/>
<#if role?? && noErrors??>
    Your submitted data<br>
    Role name: ${role.name}<br>
<#else>
    <form action="/adminTest/role" method="post">
        Role name:<br>
        <@spring.formInput "role.name"/>
        <@spring.showErrors "<br>"/>
        <br><br>
        <input type="submit" value="Submit">
    </form>
</#if>

</body>
</html>