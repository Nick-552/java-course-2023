package edu.hw8.ex1.server;

import java.util.HashMap;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;
import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
public class QuotesStorage {

    public static final Map<String, String> DEFAULT_MAP = Map.of(
        "личности", "Не переходи на личности там, где их нет",
        "оскорбления", "Если твои противники перешли на личные оскорбления, будь уверена — твоя победа не за горами",
        "интеллект", "Чем ниже интеллект, тем громче оскорбления",
        "глупый", "А я тебе говорил, что ты глупый? Так вот, я забираю свои слова обратно... Ты просто бог идиотизма."
    );

    public final Map<String, String> keywordQuoteMap;

    public String getQuote(String keyword) {
        return keywordQuoteMap.getOrDefault(keyword, "No quote for this keyword");
    }

    public static QuotesStorage getConcurrent() {
        return new QuotesStorage(new ConcurrentHashMap<>());
    }

    public static QuotesStorage getDefault() {
        return new QuotesStorage(new HashMap<>());
    }

    public QuotesStorage filledWithEntriesOf(Map<String, String> map) {
        keywordQuoteMap.putAll(map);
        return this;
    }
}
