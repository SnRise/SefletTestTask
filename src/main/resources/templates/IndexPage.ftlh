<#-- @ftlvariable name="chats" type="ru.seflet.chat.domain.Chat[]" -->

<#import "commons.ftlh" as c>
<@c.page>
    <#if user??>
        <a href="chat/create">Create new chat</a>
        <#list chats as chat>
            <p>
                <a href="chat/${chat.id}">${chat.title}</a>
            </p>
        </#list>
    </#if>
</@c.page>
