<#-- @ftlvariable name="chat" type="ru.seflet.chat.domain.Chat" -->
<#-- @ftlvariable name="chatMessage" type="ru.seflet.chat.form.MessageCredentials" -->
<#-- @ftlvariable name="userForm" type="ru.seflet.chat.form.UserCredentials" -->
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
    <#if chat.adminId == user.id>
        <div class="deleteChat">
            <form method="post" action="${chat.id}/delete">
                <input type="hidden" name="_method" value="DELETE">
                <p>
                    <input type="submit" value="Delete"/>
                </p>
            </form>
        </div>
        <div class="addUser">
            <form method="post" action="${chat.id}/addUser">
                <input type="hidden" name="action" value=""/>
                <div class="field">
                    <div class="name">
                        <label for="login">Login of adding user</label>
                    </div>
                    <div class="value">
                        <input autofocus id="login" name="login" value=""/>
                    </div>
                </div>
                <div class="button-field">
                    <input type="submit" value="Add user">
                </div>
            </form>
        </div>
    </#if>
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
