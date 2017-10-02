package uow.acc.tab.asmt1.exts;

/**
 * @author Tab Tu
 */
public class Word implements Comparable<Word>{

    private String word;
    private int freq = 1;

    public Word(String w) {
        word = w;
    }

    public String getWord() {
        return word;
    }

    public void setWord(String word) {
        this.word = word;
    }

    public int getFreq() {
        return freq;
    }

    public void setFreq(int freq) {
        this.freq = freq;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        Word word1 = (Word) o;

        return word != null ? word.equals(word1.word) : word1.word == null;

    }

    @Override
    public int compareTo(Word o) {
        return word.compareTo(o.word);
    }

    @Override
    public String toString() {
        return "Word{word='" + word + '\'' + ", freq=" + freq + '}';
    }
}
