package br.com.zup.desafio1.validate.StateDuplicate;

import javax.validation.Constraint;
import javax.validation.Payload;
import java.lang.annotation.Documented;
import java.lang.annotation.Retention;
import java.lang.annotation.Target;

import static java.lang.annotation.ElementType.TYPE;
import static java.lang.annotation.RetentionPolicy.RUNTIME;

@Documented
@Constraint(validatedBy = StateForCountryValidator.class)
@Target({ TYPE })
@Retention(RUNTIME)

public @interface StateForCountryValue {

    String message() default "duplicate values";

    Class<?>[] groups() default {};

    Class<? extends Payload>[] payload() default {};
    String[] fieldName();
    Class<?> domainClass();


}
