package controller;

import model.Text;
import model.Sentence;
import model.sentence.SentencePart;
import model.sentence.SentenceType;
import model.sentence.Word;
import view.View;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

/**
 * Class Controller that contains Parser object and parsed incoming text into list of Text's objects
 * Contain findWordsInInterrogativeSentence(int length) method that find word and processUser() in which parsing
 * executed
 */
public class Controller {
    private Parser parser=new Parser();
    private List<Text> parsedText;
    private View view;

    public Controller() throws IOException {
        parsedText=parser.parseTextIntoSentenceAndCodeList(parser.read());
    }

    public void processUser(){
        printWordList(findWordsInInterrogativeSentence(5));
    }

    /**
     * @param length
     * @return list of Word from interrogative sentences with incoming length
     */
    public List<Word> findWordsInInterrogativeSentence(int length){
        List<Word> words=new ArrayList<>();
        for(Text text:parsedText){
            if(text instanceof Sentence){
                if(((Sentence) text).getType()== SentenceType.INTERROGATIVE){
                    for(SentencePart sp:((Sentence) text).getWordsWithPunctuationMarks()){
                        if(sp instanceof Word){
                            if(((Word) sp).getWord().length()==length){
                                words.add((Word) sp);
                            }
                        }
                    }
                }
            }
        }
        return words;
    }

    public void printWordList(List<Word> words){
        for(Word w:words){
            view.print(w.toString());
        }
    }
}
