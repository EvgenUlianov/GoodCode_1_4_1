package MyEnterprise;

import MyEnterprise.GoodsInfo.CatalogGoods;

import java.util.Map;
import java.util.function.BiConsumer;

public interface PriceList {
    public Map<Integer, Integer> getPrice();

    public default void printPrice() {
        Map<Integer, Integer> price = this.getPrice();
        for (Map.Entry<Integer, Integer> entry : price.entrySet()) {
           String goodsName = CatalogGoods.getCatalog().getGoodsName(entry.getKey());
           System.out.println(goodsName + " = " + entry.getValue());
        }


    }
}
