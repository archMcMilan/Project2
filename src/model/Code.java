package model;

/**
 * Class Code implements Text
 */
public class Code implements Text {
    private String code;

    public Code(String code) {
        this.code=code;
    }


    @Override
    public String toString() {
        return "Code{" +
                "code='" + code + '\'' +
                '}';
    }

}
