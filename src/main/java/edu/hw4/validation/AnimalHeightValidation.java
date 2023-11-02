package edu.hw4.validation;

import edu.hw4.Animal;
import java.util.Optional;

public class AnimalHeightValidation implements Validation {
    @Override
    public Optional<ValidationError> perform(Animal animal) {
        if (animal.height() <= 0) {
            return Optional.of(new ValidationError("height", "Height should be greater than zero"));
        }
        return Optional.empty();
    }
}
