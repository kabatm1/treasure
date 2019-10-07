package treasure.validator

import treasure.model.TreasureMap

import javax.inject.Inject
import javax.inject.Singleton
import javax.validation.ConstraintValidator
import javax.validation.ConstraintValidatorContext

@Singleton
class MatrixScopeValidator implements ConstraintValidator<MatrixScopeValid, Integer> {

    @Inject
    TreasureMap map

    @Override
    void initialize(MatrixScopeValid constraintAnnotation) {
    }

    @Override
    boolean isValid(Integer value, ConstraintValidatorContext context) {
        return value != null &&
                value >= 11 &&
                (value / 10).intValue() <= map.map.length &&
                (value % 10) <= map.map[0].length
    }
}

