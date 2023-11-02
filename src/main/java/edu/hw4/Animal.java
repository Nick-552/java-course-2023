package edu.hw4;

import lombok.Builder;

@Builder
public record Animal(
    String name,
    Type type,
    Sex sex,
    int age,
    int height,
    int weight,
    boolean bites
) {
    public static final int NUMBER_OF_DOG_CAT_PAWS = 4;

    public static final int NUMBER_OF_BIRD_PAWS = 2;

    public static final int NUMBER_OF_FISH_PAWS = 0;

    public static final int NUMBER_OF_SPIDER_PAWS = 8;

    public enum Type {
        CAT, DOG, BIRD, FISH, SPIDER
    }

    public enum Sex {
        M, F
    }

    public int paws() {
        return switch (type) {
            case CAT, DOG -> NUMBER_OF_DOG_CAT_PAWS;
            case BIRD -> NUMBER_OF_BIRD_PAWS;
            case FISH -> NUMBER_OF_FISH_PAWS;
            case SPIDER -> NUMBER_OF_SPIDER_PAWS;
        };
    }
}
