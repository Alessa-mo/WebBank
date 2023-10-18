package json;

import org.apache.ibatis.annotations.Delete;
import pojo.Frequency;
import java.util.List;
public class LoadFrequencyRes extends AbstractRes
{
    List<Frequency> FrequencyList;

    public void FillFrequencyList(List<Frequency> frequencyList)
    {
        this.FrequencyList = frequencyList;
    }
    public List<Frequency> getFrequencyList() { return this.FrequencyList;}
}
