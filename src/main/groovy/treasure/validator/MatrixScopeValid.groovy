package treasure.validator

import javax.validation.Constraint
import javax.validation.Payload
import java.lang.annotation.Retention

import static java.lang.annotation.RetentionPolicy.RUNTIME

@Retention(RUNTIME)
@Constraint(validatedBy = [MatrixScopeValidator.class])
@interface MatrixScopeValid {
    String message() default "value out of matrix scope"
    Class<?>[] groups() default []
    Class<? extends Payload>[] payload() default []
}
