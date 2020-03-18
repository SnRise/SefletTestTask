<#-- @ftlvariable name="registerForm" type="ru.seflet.chat.form.UserCredentials" -->
<#import "/spring.ftl" as spring>
<#import "commons.ftlh" as c>

<@c.page>
    <div class="registration form-box">
        <div class="header">Register</div>
        <div class="body">
            <form method="post" action="">
                <input type="hidden" name="action" value="register"/>
                <div class="field">
                    <div class="name">
                        <label for="login">Login</label>
                    </div>
                    <div class="value">
                        <input autofocus id="login" name="login" value="${registerForm.login!}"/>
                    </div>
                    <@c.error "registerForm.login"/>
                </div>
                <div class="button-field">
                    <input type="submit" value="Register">
                </div>
            </form>
        </div>
    </div>
</@c.page>
