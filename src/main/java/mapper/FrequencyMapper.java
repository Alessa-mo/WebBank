package mapper;

import pojo.Frequency;

import java.util.List;

public interface FrequencyMapper {
    void createFrequency(Frequency frequency);

    void deleteFrequency(String userName, Integer goodsID);

    Frequency getFrequency(String userName, Integer goodsID);

    List<Frequency> getFrequencyOfUser(String userName);

    void updateFrequency(Frequency frequency);

}
