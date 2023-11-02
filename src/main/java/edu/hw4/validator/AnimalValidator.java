package edu.hw4.validator;

import edu.hw4.Animal;
import edu.hw4.validation.Validation;
import edu.hw4.validation.ValidationError;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;
import java.util.Set;
import java.util.stream.Collectors;

public class AnimalValidator {
    protected final List<Validation> validations = new ArrayList<>();

    public void addValidation(Validation validation) {
        validations.add(validation);
    }

    public Set<ValidationError> validate(Animal animal) {
        return validations.stream()
            .map(validation -> validation.perform(animal).orElse(null))
            .filter(Objects::nonNull)
            .collect(Collectors.toSet());
    }
}
