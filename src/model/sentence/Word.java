package model.sentence;

/**
 * Class Word that implements SentencePart. Consist of String variable word.
 */
public class Word implements SentencePart {
    private String word;

    public Word(String word) {
        this.word = word;
    }

    public String getWord() {
        return word;
    }

    @Override
    public String toString(){
        return word;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        Word word1 = (Word) o;

        return word != null ? word.equals(word1.word) : word1.word == null;

    }


    @Override
    public int hashCode() {
        return word != null ? word.length() : 0;
    }
}
