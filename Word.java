package dict;

/**
 *
 * @author Nguyen Hoang Tuyen
 */
public class Word {
    private String word_target; //Tu can tra
    private String word_explain; //Nghia cua tu
    private String sound; //Phat am
    private String type; //Tu loai
    //getter, setter
    public void setWord_target(String w) {
        this.word_target = w;
    }
    public String getWord_target() {
        return this.word_target;
    }
    public void setWord_explain(String w) {
        this.word_explain = w;
    }
    public String getWord_explain() {
        return this.word_explain;
    }
   
    public String getSound() {
        return this.sound;
    }
    
    public void setSound(String s) {
        this.sound = s;
    }

    public String getType() {
        return this.type;
    }
    
    public void setType(String type) {
        this.type = type;
    }

    public Word(String target, String explain) {
        this.setWord_explain(explain);
        this.setWord_target(target);
    }
    
    public Word() {
        this.setWord_explain("explain");
        this.setWord_target("target");
    }
}
