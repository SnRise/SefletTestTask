<#-- @ftlvariable name="user" type="ru.seflet.chat.domain.User" -->
<#-- @ftlvariable name="message" type="java.lang.String" -->
<#import "/spring.ftl" as spring/>
<#macro page>
    <!DOCTYPE html>
    <html lang="en">
    <head>
        <meta charset="UTF-8">
        <title>Messenger</title>
        <link rel="stylesheet" type="text/css" href="<@spring.url '/css/normalize.css'/>">
        <link rel="stylesheet" type="text/css" href="<@spring.url '/css/style.css'/>">
        <link rel="stylesheet" type="text/css" href="<@spring.url '/css/form.css'/>">
        <link rel="stylesheet" type="text/css" href="<@spring.url '/css/datatable.css'/>">
        <script src="<@spring.url '/js/jquery-3.4.1.min.js'/>"></script>
        <script src="<@spring.url '/js/notify.js'/>"></script>
    </head>
    <body>
    <header>
        <a href="/"><h2>Messenger</h2></a>
        <div class="enter-or-register-box">
            <#if user??>
                ${user.login}
                |
                <a href="/logout">Logout</a>
            <#else>
                <a href="/enter">Enter</a>
                |
                <a href="/register">Register</a>
            </#if>
        </div>
        <nav>
            <ul>
                <li><a href="/">Chats</a></li>
                <li><a href="/users/all">Users</a></li>
            </ul>
        </nav>
    </header>
    <div class="middle">
        <main>
            <#nested/>
        </main>
    </div>
    <footer>
        <a href="/">Messenger</a> &copy; 2019 by Madiyar Nurgazin
    </footer>
    </body>
    <script>
        $(function () {
            <#if message??>
            $.notify("${message?js_string}", {className: "success", position: "bottom right"});
            </#if>
        })
    </script>
    </html>
</#macro>

<#macro error field>
    <@spring.bind field/>
    <#if spring.status.errorMessage??>
        <div class="error">${spring.status.errorMessage}</div>
    </#if>
</#macro>