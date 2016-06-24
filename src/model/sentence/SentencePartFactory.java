package model.sentence;

import java.util.HashMap;

/**
 * Factory for Word creation
 */
public class SentencePartFactory {
    private static final HashMap<String, SentencePart> wordHashMap = new HashMap<>();

    public static SentencePart getWord(String symbols) {
        Word word = (Word) wordHashMap.get(symbols);

        if (word == null) {
            word = new Word(symbols);
            wordHashMap.put(symbols, word);
        }
        return word;
    }

}
