package br.com.zup.desafio1.validate.CnpjORCpf;

import javax.validation.Constraint;
import javax.validation.Payload;
import java.lang.annotation.Documented;
import java.lang.annotation.Retention;
import java.lang.annotation.Target;

import static java.lang.annotation.ElementType.TYPE;
import static java.lang.annotation.RetentionPolicy.RUNTIME;

@Documented
@Constraint(validatedBy = CnpjOrCpfValidator.class)
@Target({ TYPE })
@Retention(RUNTIME)

public @interface CnpjOrCpf {

    /**
     * Dê a Cezar o que é de Cezar
     * Essa annotation usa o algoritmo do desenvolvedor abaixo
     * @author Clairton Luz - clairton.c.l@gmail.com
     *
     */

    String message() default "CPF/CNPJ inválido";
    Class<?>[] groups() default {};

    Class<? extends Payload>[] payload() default {};
    String[] fieldName();
    Class<?> domainClass();

}
