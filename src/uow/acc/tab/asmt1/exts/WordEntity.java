package uow.acc.tab.asmt1.exts;

/**
 * @author Tab Tu
 */
public class WordEntity implements Comparable<WordEntity> {
    private String key;
    private Integer count;
    public WordEntity (String key, Integer count) {
        this.key = key;
        this.count = count;
    }
    public int compareTo(WordEntity o) {
        int cmp = count.intValue() - o.count.intValue();
        return (cmp == 0 ? key.compareTo(o.key) : -cmp);
    }

    @Override
    public String toString() {
        return key + " Count：" + count;
    }

    public String getKey() {
        return key;
    }

    public Integer getCount() {
        return count;
    }
}