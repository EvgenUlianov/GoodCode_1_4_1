package MyEnterprise.GoodsInfo;

import MyEnterprise.BasicFunctions;
import MyEnterprise.NameException;
import MyEnterprise.NamedCatalog;
import MyEnterprise.PriceList;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class CatalogGoods implements PriceList, NamedCatalog {
    private List<Goods> listGoods;

    // MAGIC
    private final static int INDEX_NOT_FOUND = -1;

    private CatalogGoods(){
        this.listGoods = new ArrayList<>();
    };

    @Override
    public int findByName(String name, boolean skipNormaliningName)  throws NameException {
        if (!skipNormaliningName)
            name = BasicFunctions.getNormalName(name);
        int i = -1;
        for (Goods goodsItem: listGoods){
            i++;
            if (goodsItem.getName().equals(name))
                return i;
        }
        return INDEX_NOT_FOUND;

    }

    @Override
    public int findByName(String name) throws NameException {
        // DRY: i had two similar variants of function
        return findByName(name, false);
    }

    public int createGoods(String name, int price) throws NameException {
        name = BasicFunctions.getNormalName(name);
        int index = findByName(name, false);
        if (index != INDEX_NOT_FOUND)
            throw new NameException("Товар с таким наименованием уже есть");

        Goods goodsItem = new Goods();
        goodsItem.setName(name);
        goodsItem.price = price;
        listGoods.add(goodsItem);
        return listGoods.size() - 1;
    }

    public int createGoods(String name)  throws NameException  {
        // DRY: i had two similar variants of function
        return createGoods(name, 0);
    }

    public int findCreateGoods(String name) throws NameException {
        // Single Responsibility Principle
        // изначально метод поиска и создания (если не найден) делал в классе Shop
        name = BasicFunctions.getNormalName(name);
        int index = findByName(name, false);
        if (index == INDEX_NOT_FOUND)
            return createGoods(name);

        return index;
    }

    public Map<Integer, Integer> getPrice(List<Integer> listOfIndex){
        // Dependency Inversion Principle
        Map<Integer, Integer> result = new HashMap<>();
        for (int index: listOfIndex) {
            result.put(index, listGoods.get(index).price);
        }
        return result;
    }

    @Override
    public Map<Integer, Integer> getPrice(){
        // Dependency Inversion Principle
        Map<Integer, Integer> result = new HashMap<>();
        int i = -1;
        for (Goods goodsItem: listGoods){
            i++;
            result.put(i, listGoods.get(i).price);
        }
        return result;
    }

    public String getGoodsName(int index) {
        return listGoods.get(index).getName();
    }


    public void setPrice(int index, int price){
        Goods goods = listGoods.get(index);
        if (goods == null)
            return;
        if (price <= 0)
            return;
        goods.price = price;
    }

    private static class GoodsHolder {
        public static final CatalogGoods catalogGoods = new CatalogGoods();
    }
    public static CatalogGoods getCatalog()  {
        return GoodsHolder.catalogGoods;
    }

}
