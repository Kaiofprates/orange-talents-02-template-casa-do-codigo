package br.com.zup.desafio1.validate;

import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;
import java.time.LocalDate;
import java.time.chrono.ChronoLocalDate;

public class DateFutureValidador implements ConstraintValidator<FutureDate,Object> {
    private  String domainAttribute;
    private Class<?> klass;

    @Override
    public void initialize(FutureDate params) {
        domainAttribute = params.fieldName();
        klass = params.domainClass();
    }

    @Override
    public boolean isValid(Object value, ConstraintValidatorContext context) {
        LocalDate now = LocalDate.now();
        return now.isBefore((ChronoLocalDate) value);
    }
}
