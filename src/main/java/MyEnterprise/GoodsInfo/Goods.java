package MyEnterprise.GoodsInfo;

import MyEnterprise.NamedObject;

public class Goods extends NamedObject {
    // Liskov Substitution Principle: MyBaseClass has only name
    protected int price;

    protected Goods(){
        this.price = 0;
    };


}
