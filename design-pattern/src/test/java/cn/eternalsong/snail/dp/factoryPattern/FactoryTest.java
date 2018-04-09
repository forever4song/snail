package cn.eternalsong.snail.dp.factoryPattern;


/**
 * Created with IntelliJ IDEA.
 * User: 长歌
 * Date: 18-3-31
 * Time: 下午4:55
 * Description: 工厂模式测试类
 */
public class FactoryTest {
    public static void main(String[] args) {
        OfficeFactory officeFactory = new OfficeFactory();

        // 获取 Word 对象
        Word word = (Word) officeFactory.newInstance("Word");
        word.doSomething();

        // 获取Excel对象
        Excel excel = (Excel) officeFactory.newInstance("Excel");
        excel.doSomething();

        // 获取Powerpoint对象
        Powerpoint powerpoint = (Powerpoint) officeFactory.newInstance("Powerpoint");
        powerpoint.doSomething();
    }
}
