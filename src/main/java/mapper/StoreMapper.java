package mapper;

import pojo.Store;

import java.util.List;

public interface StoreMapper {
    void createStore(Store store);

    void deleteStore(String storeName);

    Integer getLocationMemByName(String storeName);

    String getDetailLocationByName(String storeName);

    String getDescribeByName(String storeName);

    Store getStoreByName(String storeName);

    void updateStore(Store storeName);

    List<Store> getAllStores();

}
