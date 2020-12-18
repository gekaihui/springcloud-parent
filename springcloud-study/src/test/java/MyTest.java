/**
 * @author gekaihui
 * @Description
 * @date 2020/11/6
 */
public class MyTest {
    public static void main (String[] args) {
        StringBuffer qrCodeStr = new StringBuffer();
        qrCodeStr.append("123").append("abc").append("ABC");
        if (true) {
            qrCodeStr.append("&jumpUrl=").append("jumpUrl")
                    .append("&jumpStart=").append("jumpStart")
                    .append("&jumpEnd=").append("jumpEnd");
        }
        System.out.println(qrCodeStr);
    }
}
