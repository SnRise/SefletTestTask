package ru.seflet.chat.form.validator;

import org.springframework.stereotype.Component;
import org.springframework.validation.Errors;
import org.springframework.validation.Validator;
import ru.seflet.chat.form.UserCredentials;
import ru.seflet.chat.service.UserService;

@Component
public class UserCredentialsEnterValidator implements Validator {
    private final UserService userService;

    public UserCredentialsEnterValidator(UserService userService) {
        this.userService = userService;
    }

    public boolean supports(Class<?> clazz) {
        return UserCredentials.class.equals(clazz);
    }

    public void validate(Object target, Errors errors) {
        if (!errors.hasErrors()) {
            UserCredentials enterForm = (UserCredentials) target;
            if (userService.findByLogin(enterForm.getLogin()) == null) {
                errors.rejectValue("login", "login.invalid-login", "invalid login");
            }
        }
    }
}
