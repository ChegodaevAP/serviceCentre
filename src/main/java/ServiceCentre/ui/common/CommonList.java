package ServiceCentre.ui.common;


public abstract class CommonList {
    private String keyWord;

    public abstract void search();

    public abstract void add();

    public abstract void delete();

    public abstract void refresh();

    public String getKeyWord() {
        return keyWord;
    }

    public void setKeyWord(String keyWord) {
        this.keyWord = keyWord;
    }
}
