<#-- @ftlvariable name="chat" type="ru.seflet.chat.domain.Chat" -->
<#-- @ftlvariable name="chatMessage" type="ru.seflet.chat.form.MessageCredentials" -->
<#import "/spring.ftl" as spring>
<#import "commons.ftlh" as c>

<@c.page>
    <div class="messages">
        <form method="post" action="">
            <input type="hidden" name="action" value="messageTest"/>
            <p>
                <label for="message">Отправить сообщение:</label>
            </p>
            <textarea type=text id="message" name="message"></textarea>
            ${chatMessage.message!}
            <@c.error "chatMessage.message"/>
            <p>
                <input type="submit" value="Send"/>
            </p>
        </form>
    </div>
    <div class="datatable">
        <div class="caption">${chat.title}</div>
        <table>
            <thead>
            <tr>
                <th>From</th>
                <th>Messages</th>
                <th>Date</th>
            </tr>
            </thead>
            <tbody>
            <#list chat.messages as curMessage>
                <tr>
                    <td>${curMessage.sendetLogin}</td>
                    <td>${curMessage.message}</td>
                    <td>${curMessage.creationTime}</td>
                </tr>
            </#list>
            </tbody>
        </table>
    </div>
</@c.page>
