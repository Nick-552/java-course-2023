package edu.hw8.ex3;

import java.nio.charset.StandardCharsets;
import java.security.MessageDigest;
import java.util.Collection;
import java.util.Map;
import lombok.RequiredArgsConstructor;
import org.apache.commons.codec.binary.Hex;
import org.apache.commons.codec.digest.DigestUtils;

@RequiredArgsConstructor
public abstract class AbstractPasswordHacker {

    private static final int DEFAULT_MAX_LENGTH = 6;

    private static final String DEFAULT_HASHING_ALGORITHM = "MD5";

    protected final Character[] alphabet;

    protected final String hashingAlgorithm;

    protected final int maxLength;

    protected Map<String, String> hackedPasswords;

    public AbstractPasswordHacker() {
        alphabet = new Character[] {
            'a', 'b', 'c', 'd', 'e', 'f', 'g', 'h', 'i', 'j',
            'k', 'l', 'm', 'n', 'o', 'p', 'q', 'r', 's', 't',
            'u', 'v', 'w', 'x', 'y', 'z', '0', '1', '2', '3',
            '4', '5', '6', '7', '8', '9'
        };
        this.hashingAlgorithm = DEFAULT_HASHING_ALGORITHM;
        maxLength = DEFAULT_MAX_LENGTH;
    }

    public abstract Map<String, String> hackPasswordsFromHashes(Collection<UserCredentials> passwordDatabase);

    protected abstract void hackPasswordsFixedLength(Map<String, String> passwordUsernameMap, int length);

    public static String getHash(String password, MessageDigest messageDigest) {
        return Hex.encodeHexString(
            DigestUtils.digest(
                messageDigest,
                password.getBytes(StandardCharsets.UTF_8)
            )
        );
    }
}
