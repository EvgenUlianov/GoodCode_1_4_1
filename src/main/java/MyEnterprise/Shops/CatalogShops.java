package MyEnterprise.Shops;

import MyEnterprise.BasicFunctions;
import MyEnterprise.GoodsInfo.CatalogGoods;
import MyEnterprise.GoodsInfo.Goods;
import MyEnterprise.NameException;
import MyEnterprise.NamedCatalog;

import java.util.ArrayList;
import java.util.List;

public class CatalogShops implements NamedCatalog {
    private List<Shop> listShops;

    // MAGIC
    private final static int INDEX_NOT_FOUND = -1;

    private CatalogShops(){
        this.listShops = new ArrayList<>();
    };

    @Override
    public int findByName(String name, boolean skipNormaliningName) throws NameException {
        if (!skipNormaliningName)
            name = BasicFunctions.getNormalName(name);
        int i = -1;
        for (Shop Item: listShops){
            i++;
            if (Item.getName().equals(name))
                return i;
        }
        return INDEX_NOT_FOUND;
    }

    @Override
    public int findByName(String name) throws NameException {
        // DRY: i had two similar variants of function
        return findByName(name, false);
    }

    public int createShop(String name) throws NameException {
        name = BasicFunctions.getNormalName(name);
        int index = findByName(name, false);
        if (index != INDEX_NOT_FOUND)
            throw new NameException("Магазин с таким наименованием уже есть");

        Shop shop = new Shop(name);
        listShops.add(shop);
        return listShops.size() - 1;
    }

    public int findCreateShop(String name) throws NameException {
        // Single Responsibility Principle
        // изначально метод поиска и создания (если не найден) делал в классе Shop
        name = BasicFunctions.getNormalName(name);
        int index = findByName(name, false);
        if (index == INDEX_NOT_FOUND)
            return createShop(name);

        return index;
    }

    public void addGood(int indexShop, int indexGoods, int quantity){
        listShops.get(indexShop).addGood(indexGoods, quantity);
    }
    public void subtractGood(int indexShop, int indexGoods, int quantity){
        listShops.get(indexShop).subtractGood(indexGoods, quantity);
    }
    public Shop getShop(int index) {
        return listShops.get(index);
    }

    private static class Holder {
        public static final CatalogShops catalogShops = new CatalogShops();
    }
    public static CatalogShops getCatalog()  {
        return Holder.catalogShops;
    }
}
