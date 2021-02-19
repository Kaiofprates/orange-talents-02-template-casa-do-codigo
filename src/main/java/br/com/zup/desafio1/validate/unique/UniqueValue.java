package br.com.zup.desafio1.validate.unique;

import javax.validation.Constraint;
import javax.validation.Payload;
import java.lang.annotation.*;

import static java.lang.annotation.ElementType.FIELD;
import static java.lang.annotation.RetentionPolicy.*;

@Documented
@Constraint(validatedBy = {UniqueValueValidator.class})
@Target({ FIELD })
@Retention(RUNTIME)

public @interface  UniqueValue {

    String message() default "duplicate values";

    Class<?>[] groups() default {};

    Class<? extends Payload>[] payload() default {};
    String fieldName();
    Class<?> domainClass();

}
