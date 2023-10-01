package json;

import pojo.Store;
import java.util.List;
public class LoadStoreRes extends AbstractRes
{
    List<Store> StoreList;
    public void FillStoreList(List<Store> storeList){this.StoreList = storeList;}
}
