package MyEnterprise.Shops;

import MyEnterprise.GoodsInfo.CatalogGoods;
import MyEnterprise.NameException;
import MyEnterprise.NamedObject;
import MyEnterprise.PriceList;

import java.util.HashMap;
import java.util.Map;

public class Shop extends NamedObject implements PriceList  {
    // Liskov Substitution Principle: MyBaseClass has only name
    private Map<Integer, Integer> goodsQuantity;

    public Shop(String name) {
        this.goodsQuantity = new HashMap<>();
        super.name = name;
    }

    public boolean addGood(String name, Integer quantity) {
        Integer goodsIndex;
        try {
            goodsIndex = CatalogGoods.getCatalog().findCreateGoods(name);
            return addGood(goodsIndex, quantity);
        } catch (NameException e) {
            e.printStackTrace();
            return false;
        }

    }

    public boolean addGood(Integer goodsIndex, Integer quantity) {
        if (quantity == 0)
            return false;
        Integer currentQuantity = goodsQuantity.get(goodsIndex);
        if (currentQuantity == null)
            currentQuantity = Integer.valueOf(0);
        currentQuantity += quantity;
        goodsQuantity.put(goodsIndex, currentQuantity);
        return true;
    }

    public boolean subtractGood(String name, Integer quantity) {
        int goodsIndex;
        try {
            goodsIndex = CatalogGoods.getCatalog().findCreateGoods(name);

            return subtractGood(goodsIndex, quantity);

        } catch (NameException e) {
            e.printStackTrace();
            return false;
        }
    }

    public boolean subtractGood(Integer goodsIndex, Integer quantity) {
        if (quantity == 0)
            return false;
        Integer currentQuantity = goodsQuantity.get(goodsIndex);
        if (currentQuantity == null)
            return false;

        currentQuantity -= quantity;
        if (currentQuantity <= 0)
            return false;
        goodsQuantity.put(goodsIndex, currentQuantity);
        return true;


    }

    @Override
    public Map<Integer, Integer> getPrice() {
        return CatalogGoods.getCatalog().getPrice(goodsQuantity.keySet().stream().toList());
    }

    public void printShopReport() {
        Map<Integer, Integer> price = this.getPrice();
        for (Map.Entry<Integer, Integer> entry : price.entrySet()) {
            String goodsName = CatalogGoods.getCatalog().getGoodsName(entry.getKey());
            int goodsPrice = entry.getValue();
            int quantity = goodsQuantity.get(entry.getKey());
            System.out.println(new StringBuilder()
                    .append(goodsName)
                    .append(": ")
                    .append(goodsPrice)
                    .append(" * ")
                    .append(quantity)
                    .append(" = ")
                    .append(quantity * goodsPrice)
                    .toString());

        }


    }
}
