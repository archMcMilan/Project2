package model.sentence;

/**
 * Enum that contains available punctuation marks
 */
public enum Punctuation implements SentencePart {
    DOT("\\."),
    QUESTION("\\?"),
    EXCLAMANTORY("\\!"),
    THREE_DOTS("\\.\\.\\."),
    COMA(","),
    DOUBLE_DOT(":"),
    SEMICOLON(";"),
    QUOTE("'"),
    DOUBLE_QUOTE("\"");

    private String symbol;

    public String getSymbol() {
        return symbol;
    }

    public static Punctuation getBySymbol(String symbol){
        for(Punctuation p:Punctuation.values()){
            if(p.getSymbol().equals(symbol)){
                return p;
            }
        }
        return null;
    }

    Punctuation(String symbol) {
        this.symbol = symbol;
    }
}
