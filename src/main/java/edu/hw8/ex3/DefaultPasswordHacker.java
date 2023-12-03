package edu.hw8.ex3;

import java.security.MessageDigest;
import java.util.Arrays;
import java.util.Collection;
import java.util.HashMap;
import java.util.Map;
import java.util.stream.Collectors;
import lombok.RequiredArgsConstructor;
import lombok.SneakyThrows;

@RequiredArgsConstructor
public class DefaultPasswordHacker extends AbstractPasswordHacker {

    @SneakyThrows
    @Override
    public Map<String, String> hackPasswordsFromHashes(Collection<UserCredentials> passwordDatabase) {
        hackedPasswords = new HashMap<>();
        Map<String, String> passwordHashesMap = passwordDatabase.stream()
            .collect(Collectors.toMap(UserCredentials::passwordHash, UserCredentials::username));
        for (int i = 1; i <= maxLength && !passwordHashesMap.isEmpty(); i++) {
            hackPasswordsFixedLength(passwordHashesMap, i);
        }
        return new HashMap<>(hackedPasswords);
    }

    @SneakyThrows
    protected void hackPasswordsFixedLength(Map<String, String> passwordUsernameMap, int length) {
        MessageDigest messageDigest = MessageDigest.getInstance(hashingAlgorithm);
        int[] indexes = new int[length];
        Arrays.fill(indexes, 0);
        StringBuilder passwordBuilder = new StringBuilder(" ".repeat(length));
        while (true) {
            for (int i = 0; i < length; i++) {
                passwordBuilder.setCharAt(i, alphabet[indexes[i]]);
            }
            String password = passwordBuilder.toString();
            String usernameWithPassword = passwordUsernameMap.remove(getHash(password, messageDigest));
            if (usernameWithPassword != null) {
                hackedPasswords.put(password, usernameWithPassword);
                if (passwordUsernameMap.isEmpty()) {
                    break;
                }
            }
            indexes[0]++;
            for (int i = 0; indexes[i] == alphabet.length; i++) {
                if (i + 1 == length) {
                    return;
                }
                indexes[i] = 0;
                indexes[i + 1]++;
            }
        }
    }
}
