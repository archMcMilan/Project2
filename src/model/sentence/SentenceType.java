package model.sentence;

/**
 * Enum SentenceType in which 3 types of sentence.
 */
public enum SentenceType {
    DECLARATION,
    INTERROGATIVE,
    EXCLAMATION;

    @Override
    public String toString() {
        return SentenceType.class.getName();
    }
}
