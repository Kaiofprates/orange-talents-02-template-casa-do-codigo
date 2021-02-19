package br.com.zup.desafio1.validate.CnpjORCpf;

import br.com.zup.desafio1.handler.exceptions.CustonMessageException;
import org.springframework.beans.BeanWrapperImpl;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;

import java.util.Arrays;
import java.util.List;

import static br.com.zup.desafio1.validate.CnpjORCpf.Cnpj.isCnpj;
import static br.com.zup.desafio1.validate.CnpjORCpf.Cpf.isCpf;

public class CnpjOrCpfValidator implements ConstraintValidator<CnpjOrCpf, Object > {

    private List<String> fields;
    private Class<?> klass;

    @PersistenceContext
    private EntityManager manager;

    @Override
    public void initialize(CnpjOrCpf params) {
        fields = Arrays.asList(params.fieldName());
        klass = params.domainClass();
    }

    @Override
    public boolean isValid(Object value, ConstraintValidatorContext context) {
        String cnpjOrCpf = (String) new BeanWrapperImpl(value).getPropertyValue(fields.get(0));
        Boolean check =  cnpjOrCpf == null || cnpjOrCpf.isEmpty() || isCpf(cnpjOrCpf) || isCnpj(cnpjOrCpf);

        if (check) {
            throw new CustonMessageException("CPF/CNPJ inválido");
        }
        return true;
    }

}


