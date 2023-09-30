package mapper;

import pojo.Store;
public interface StoreMapper {
    void createStore(Store store);

    void deleteStore(String name);

    Integer getLocationMemByName(String name);

    String getDetailLocationByName(String name);

    String getDescribeByName(String name);

    Store getStoreByName(String name);

    void updateStore(Store store);

}
