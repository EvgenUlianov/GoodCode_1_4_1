import MyEnterprise.GoodsInfo.CatalogGoods;
import MyEnterprise.NameException;
import MyEnterprise.Shops.CatalogShops;

import java.util.HashMap;
import java.util.Map;
import java.util.Scanner;
import java.util.function.Supplier;

public class Main {

    public static void main(String[] args) {
        System.out.println("Задача Магазин");
        Scanner scanner = new Scanner(System.in);

        Map<String, Supplier<Boolean>> commands = new HashMap<>();
//        commands.put("createGoods", () -> {
//            System.out.println("type goods name");
//            String name = scanner.nextLine();
//            try {
//                CatalogGoods.getCatalog().createGoods(name);
//            } catch (NameException e) {
//                e.printStackTrace();
//            }
//            return true;
//        });
        commands.put("setPrice", () -> {
            try {
                int index = findCreateGoods();
                System.out.println("type price");
                String price = scanner.nextLine();
                CatalogGoods.getCatalog().setPrice(index, Integer.parseInt(price));
            } catch (NameException e) {
                e.printStackTrace();
            }
            return true;
        });
//        commands.put("createShop", () -> {
//            System.out.println("type shop name");
//            String name = scanner.nextLine();
//            try {
//                CatalogShops.getCatalog().createShop(name);
//            } catch (NameException e) {
//                e.printStackTrace();
//            }
//            return true;
//        });
        commands.put("addGood", () -> {
            try {
                int indexShop =  findCreateShop();
                int indexGoods = findCreateGoods();
                System.out.println("type quantity");
                String quantity = scanner.nextLine();
                CatalogShops.getCatalog().addGood(indexShop, indexGoods, Integer.parseInt(quantity));
            } catch (NameException e) {
                e.printStackTrace();
            }
            return true;
        });
        commands.put("subtractGoodGood", () -> {
            try {
                int indexShop =  findCreateShop();
                int indexGoods = findCreateGoods();
                System.out.println("type quantity");
                String quantity = scanner.nextLine();
                CatalogShops.getCatalog().subtractGood(indexShop, indexGoods, Integer.parseInt(quantity));
            } catch (NameException e) {
                e.printStackTrace();
            }
            return true;
        });
        commands.put("printAllPrice", () -> {
            CatalogGoods.getCatalog().printPrice();
            return true;
        });
        commands.put("printShopPrice", () -> {
            try {
                int indexShop =  findCreateShop();
                CatalogShops.getCatalog().getShop(indexShop).printPrice();
            } catch (NameException e) {
                e.printStackTrace();
            }

            return true;
        });
        commands.put("printShopReport", () -> {
            try {
                int indexShop =  findCreateShop();
                CatalogShops.getCatalog().getShop(indexShop).printShopReport();
            } catch (NameException e) {
                e.printStackTrace();
            }

            return true;
        });

        commands.keySet().forEach(System.out::println);

        while (true) {
            System.out.println("type command name");
            String commandName = scanner.nextLine();
            Supplier<Boolean> command  = commands.get(commandName);
            if (command == null)
                break;
            command.get();
        }

    }

    private static  int findCreateGoods() throws NameException {
        Scanner scanner = new Scanner(System.in);
        System.out.println("type goods name");
        String nameGood = scanner.nextLine();
        return CatalogGoods.getCatalog().findCreateGoods(nameGood);

    }
    private static int findCreateShop() throws NameException {
        Scanner scanner = new Scanner(System.in);
        System.out.println("type shop name");
        String nameShop = scanner.nextLine();
        return CatalogShops.getCatalog().findCreateShop(nameShop);

    }

}
