package model;

import model.sentence.SentencePart;
import model.sentence.SentenceType;

import java.util.List;

/**
 * Class implements Text.Consist of type:SentenceType and list of SentencePart
 */
public class Sentence implements Text {
    private SentenceType type;
    private List<SentencePart> wordsWithPunctuationMarks;

    public Sentence(List<SentencePart> parsedSentence,SentenceType type) {
        wordsWithPunctuationMarks=parsedSentence;
        this.type=type;
    }

    public List<SentencePart> getWordsWithPunctuationMarks() {
        return wordsWithPunctuationMarks;
    }

    public SentenceType getType() {
        return type;
    }

    @Override
    public String toString() {
        return "Sentence{" + wordsWithPunctuationMarks + type.toString() + '}';
    }
}
