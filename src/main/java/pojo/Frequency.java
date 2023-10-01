package pojo;

// 数据库表条目对象
//mysql构建:
//create table frequency (
//        frequency_name varchar(20) not null,
//        frequency_goods int not null,
//        frequency_num int default 0,
//        primary key(frequency_name,frequency_goods)
//        );

public class Frequency {
    protected String frequencyName;
    protected Integer frequencyGoods;
    protected Integer frequencyNum;

    public Frequency(String frequencyName, Integer frequencyGoods, Integer frequencyNum) {
        this.frequencyName = frequencyName;
        this.frequencyGoods = frequencyGoods;
        this.frequencyNum = frequencyNum;
    }

    public String getFrequencyName() {
        return frequencyName;
    }

    public void setFrequencyName(String frequencyName) {
        this.frequencyName = frequencyName;
    }

    public Integer getFrequencyGoods() {
        return frequencyGoods;
    }

    public void setFrequencyGoods(Integer frequencyGoods) {
        this.frequencyGoods = frequencyGoods;
    }

    public Integer getFrequencyNum() {
        return frequencyNum;
    }

    public void setFrequencyNum(Integer frequencyNum) {
        this.frequencyNum = frequencyNum;
    }

    @Override
    public String toString() {
        return "Frequency{" +
                "frequencyName='" + frequencyName + '\'' +
                ", frequencyGoods=" + frequencyGoods +
                ", frequencyNum=" + frequencyNum +
                '}';
    }
}
