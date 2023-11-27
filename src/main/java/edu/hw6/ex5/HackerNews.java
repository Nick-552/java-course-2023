package edu.hw6.ex5;

import java.io.IOException;
import java.net.URI;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;
import java.util.Arrays;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import lombok.experimental.UtilityClass;

@UtilityClass
public class HackerNews {

    private static final String TOP_STORIES_URI_STRING = "https://hacker-news.firebaseio.com/v0/topstories.json";

    private static final String NEWS_URI_STRING = "https://hacker-news.firebaseio.com/v0/item/%s.json";

    private static final Pattern TITLE_PATTERN = Pattern.compile("\"title\":\"([^\"]*)\",");

    public static long[] hackerNewsTopStories() {
        String responseBody = get(TOP_STORIES_URI_STRING);
        if (responseBody == null) {
            return new long[0];
        }
        return Arrays.stream(responseBody
            .replace("[", "")
            .replace("]", "")
            .split(","))
            .mapToLong(Long::valueOf)
            .toArray();
    }

    public static String news(long id) {
        String newsUri = String.format(NEWS_URI_STRING, id);
        String responseBody = get(newsUri);
        if (responseBody == null) {
            return null;
        }
        Matcher matcher = TITLE_PATTERN.matcher(responseBody);
        matcher.find();
        return matcher.group(1);
    }

    public static String get(String uri) {
        try (HttpClient httpClient = HttpClient.newHttpClient()) {
            HttpResponse<String> response;
            try {
                 response = httpClient.send(
                    HttpRequest.newBuilder().GET().uri(URI.create(uri)).build(),
                    HttpResponse.BodyHandlers.ofString()
                );
            } catch (IOException | InterruptedException e) {
                throw new RuntimeException(e);
            }
            return response.body();
        }
    }
}
