package ru.seflet.chat.form.validator;

import org.springframework.stereotype.Component;
import org.springframework.validation.Errors;
import org.springframework.validation.Validator;
import ru.seflet.chat.form.MessageCredentials;

@Component
public class MessageCredentialsValidator implements Validator {

    public boolean supports(Class<?> clazz) {
        return MessageCredentials.class.equals(clazz);
    }

    public void validate(Object target, Errors errors) {
        if (!errors.hasErrors()) {
            MessageCredentials enterForm = (MessageCredentials) target;
        }
    }
}
