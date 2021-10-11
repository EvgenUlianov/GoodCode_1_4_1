package MyEnterprise;

import MyEnterprise.GoodsInfo.Goods;

public interface NamedCatalog {

    public int findByName(String name, boolean skipNormaliningName)  throws NameException;

    public int findByName(String name)  throws NameException;
}
