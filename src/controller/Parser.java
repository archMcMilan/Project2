package controller;

import model.Code;
import model.Sentence;
import model.Text;
import model.sentence.*;
import view.View;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Properties;

/**
 * Parser contains method to parse incoming text from file
 */
public class Parser {
    private Properties prop = new Properties();

    public Parser() throws IOException {
        prop.load(new FileInputStream(new File(View.PATH_PROP)));
    }

    public Parser(String filePath) throws IOException {
        prop.load(new FileInputStream(new File(filePath)));
    }

    /**
     * Method read file with text. Use file path from property file.
     * @return read file in String
     * @throws IOException if file don't find
     */
    public String read() throws IOException {
        String path = prop.getProperty(View.FILE_PATH_PROP);
        return Reader.readFile(path);
    }

    /**
     * Method parse incoming String into Text array by splitTag from property file. Than go through this array and create
     * objects of the classes (Sentence and Code) that realized interface Text. For Sentence object creation used
     * special method parseBlockOfTextIntoSentenceList(String s)
     * @param text
     * @return list of Text
     */
    public List<Text> parseTextIntoSentenceAndCodeList(String text) {
        List<Text> parsedText = new ArrayList<>();
        String[] parsedTextIntoSentenceAndCode = text.split(prop.getProperty(View.SPLIG_TAG_PROP));
        for (String s : parsedTextIntoSentenceAndCode) {
            if (s.contains(prop.getProperty(View.TEXT_TAG_PROP))) {
                parsedText.addAll(parseBlockOfTextIntoSentenceList(s));
            } else if(s.contains(prop.getProperty(View.CODE_TAG_PROP))){
                parsedText.add(new Code(s));
            }else{
                throw new RuntimeException(View.UNKNOWN_TAG);
            }
        }
        return parsedText;
    }

    /**
     * Method parse incoming String into SentencePart list. Firstly, it split String by View.SPACE and than divide each
     * received String after splitting into Word object and Punctuation
     * @param sentence
     * @return SentencePart list
     */
    public List<SentencePart> parseSentenceIntoWordsAndPunctuations(String sentence) {
        List<SentencePart> parsedSentence = new ArrayList<>();
        String[] parsedSentenceIntoLexems=sentence.split(View.SPACE);//Lexems it's mean words and punctuation marks
                                                                     // without separation
        for(String s:parsedSentenceIntoLexems){
            Word word;
            if(s.length()>0){
                if(s.contains(prop.getProperty(View.TEXT_TAG_PROP))){
                    s=s.substring(Integer.parseInt(prop.getProperty(View.TAG_LENGTH_PROP)));
                }
                if((s.charAt(s.length()-1)+"").matches(View.TOKENS)){
                    word=(Word)SentencePartFactory.getWord(s.substring(0,s.length()-1));
                    Punctuation punctuation=Punctuation.getBySymbol(s.charAt(s.length()-1)+"");
                    parsedSentence.add(word);
                    parsedSentence.add(punctuation);
                }else{
                    word= (Word) SentencePartFactory.getWord(s);
                    parsedSentence.add(word);
                }
            }

        }
        return parsedSentence;
    }

    /**
     * Method parse incoming textBlock into sentences. Choose with type has each sentence.
     * @param textBlock
     * @return Sentence objects list by dint of parseSentenceIntoWordsAndPunctuations method
     */
    public List<Sentence> parseBlockOfTextIntoSentenceList(String textBlock){
        if(textBlock.contains(Punctuation.QUESTION.getSymbol())){
            textBlock=textBlock.replaceAll(Punctuation.QUESTION.getSymbol(),
                    Punctuation.QUESTION.getSymbol()+Punctuation.DOT.getSymbol());
        }
        if(textBlock.contains(Punctuation.EXCLAMANTORY.getSymbol())){
            textBlock=textBlock.replaceAll(Punctuation.EXCLAMANTORY.getSymbol(),
                    Punctuation.EXCLAMANTORY.getSymbol()+Punctuation.DOT.getSymbol());
        }

        String[] sentences=textBlock.split(Punctuation.DOT.getSymbol()+View.SPACE);

        List<Sentence> sentencesList=new ArrayList<>();

        for(String s:sentences){
            SentenceType sentenceType=null;
            if(s.length()>0){
                if(s.contains(Punctuation.QUESTION.getSymbol())){
                    sentenceType=SentenceType.INTERROGATIVE;
                    s=s.substring(0,s.length()-2);
                }else if(s.contains(Punctuation.EXCLAMANTORY.getSymbol())){
                    sentenceType=SentenceType.EXCLAMATION;
                    s=s.substring(0,s.length()-2);
                }else{
                    sentenceType=SentenceType.DECLARATION;
                }
            }
            List<SentencePart> sentenceParts=parseSentenceIntoWordsAndPunctuations(s);
            sentencesList.add(new Sentence(sentenceParts,sentenceType));
        }
        return sentencesList;
    }
}
