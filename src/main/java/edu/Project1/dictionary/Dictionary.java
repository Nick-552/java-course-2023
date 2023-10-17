package edu.Project1.dictionary;

import org.jetbrains.annotations.NotNull;

public interface Dictionary {
    @NotNull String getRandomWord();

    Character[] getAlphabet();
}
