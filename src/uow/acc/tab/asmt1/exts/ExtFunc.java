package uow.acc.tab.asmt1.exts;

public class ExtFunc {
    // Get Random String with length
    public static String getRandomString(int length) {
        String KeyString = "abcdefghijklmnopqrstuvwxyzABCDEFGHIJKLMNOPQRSTUVWXYZ0123456789";
        StringBuffer sb = new StringBuffer();
        int len = KeyString.length();
        for (int i = 0; i < length; i++) {
            sb.append(KeyString.charAt((int) Math.round(Math.random() * (len - 1))));
        }
        return sb.toString();
    }

    // Get Random Integer Maxium for max
    public static int getRandomNumber(int max) {
        return (int)Math.round(Math.random() * max);
    }

}
