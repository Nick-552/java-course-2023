package edu.hw8.ex3;

import java.security.MessageDigest;
import java.util.Arrays;
import java.util.Collection;
import java.util.HashMap;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.TimeUnit;
import java.util.stream.Collectors;
import lombok.RequiredArgsConstructor;
import lombok.SneakyThrows;

@RequiredArgsConstructor
public class MultiThreadingPasswordHacker extends AbstractPasswordHacker {

    private static final int LAST_LETTERS_PER_TASK = 6;

    private final ExecutorService executorService = Executors.newFixedThreadPool(6);

    @SneakyThrows
    @Override
    public Map<String, String> hackPasswordsFromHashes(Collection<UserCredentials> passwordDatabase) {
        hackedPasswords = new ConcurrentHashMap<>();
        Map<String, String> passwordHashesMap = new ConcurrentHashMap<>(
            passwordDatabase.stream()
            .collect(Collectors.toMap(
                UserCredentials::passwordHash,
                UserCredentials::username
            ))
        );
        for (int i = 1; i <= maxLength && !passwordHashesMap.isEmpty(); i++) {
            hackPasswordsFixedLength(passwordHashesMap, i);
        }
        executorService.shutdown();
        executorService.awaitTermination(Integer.MAX_VALUE, TimeUnit.SECONDS);
        return new HashMap<>(hackedPasswords);
    }

    @Override
    @SneakyThrows
    protected void hackPasswordsFixedLength(Map<String, String> passwordUsernameMap, int length) {
        for (int i = 0; i < alphabet.length; i += LAST_LETTERS_PER_TASK) {
            int finalI = i;
            executorService.execute(() -> hackPartOfPasswordsFixedLength(passwordUsernameMap, length, finalI));
        }
    }

    @SuppressWarnings("checkstyle:ReturnCount")
    @SneakyThrows
    private void hackPartOfPasswordsFixedLength(Map<String, String> passwordUsernameMap, int length, int startIdx) {
        MessageDigest messageDigest = MessageDigest.getInstance(hashingAlgorithm);
        int[] indexes = new int[length];
        Arrays.fill(indexes, 0);
        indexes[indexes.length - 1] = startIdx;
        StringBuilder passwordBuilder = new StringBuilder(" ".repeat(length));
        while (!passwordUsernameMap.isEmpty()) {
            for (int i = 0; i < length; i++) {
                passwordBuilder.setCharAt(i, alphabet[indexes[i]]);
            }
            String password = passwordBuilder.toString();
            String usernameWithPassword = passwordUsernameMap.remove(getHash(password, messageDigest));
            if (usernameWithPassword != null) {
                hackedPasswords.put(password, usernameWithPassword);
            }
            indexes[0]++;
            for (int i = 0; indexes[i] == alphabet.length; i++) {
                if (i + 1 == length) {
                    return;
                }
                indexes[i] = 0;
                indexes[i + 1]++;
                if (i + 2 == length && indexes[i + 1] >= startIdx + LAST_LETTERS_PER_TASK) {
                    return;
                }
            }
        }
    }
}
