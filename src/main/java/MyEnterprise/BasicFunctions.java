package MyEnterprise;

public class BasicFunctions {
    // MAGIC
    final static int RED_SYMBOL_LENGTH = 1;

    // DRY: i had two similar variants of function
    public static String getNormalName(String name)  throws NameException {
        // MAGIC: red symbol only one
        name = name.trim();
        if (name.length() <= RED_SYMBOL_LENGTH)
            throw new NameException("Слишком короткое наименование");

        StringBuilder stringBuilder = new StringBuilder();
        stringBuilder.append(name
                .substring(0, RED_SYMBOL_LENGTH)
                .toUpperCase());
        stringBuilder.append(name
                .substring(RED_SYMBOL_LENGTH, name.length())
                .toLowerCase());

        return stringBuilder.toString();
    }

    protected BasicFunctions(){};
}
