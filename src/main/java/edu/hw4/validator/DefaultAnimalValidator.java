package edu.hw4.validator;

import edu.hw4.validation.AnimalAgeValidation;
import edu.hw4.validation.AnimalHeightValidation;
import edu.hw4.validation.AnimalWeightValidation;

public class DefaultAnimalValidator extends AnimalValidator {

    public DefaultAnimalValidator() {
        addValidation(new AnimalAgeValidation());
        addValidation(new AnimalWeightValidation());
        addValidation(new AnimalHeightValidation());
    }


}
