<#-- @ftlvariable name="chatCredentials" type="ru.seflet.chat.form.ChatCredentials" -->
<#import "/spring.ftl" as spring>
<#import "commons.ftlh" as c>

<@c.page>
    <div class="chat creation form">
        <form method="post" action="">
            <input type="hidden" name="action" value="chat/create"/>
            <p>
                <label for="title">Название чата:</label>
            </p>
            <textarea id="title" name="title"></textarea>
            <p>
                <input type="submit" value="Create">
            </p>
        </form>
    </div>
</@c.page>
