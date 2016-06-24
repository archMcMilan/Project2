package model.sentence;

/**
 * Interface that implemented by Word and Punctuation
 */
public interface SentencePart {
    String toString();
    int hashCode();
    boolean equals(Object o);
}
