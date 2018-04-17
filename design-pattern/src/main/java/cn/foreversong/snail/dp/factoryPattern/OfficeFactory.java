package cn.foreversong.snail.dp.factoryPattern;

/**
 * Created with IntelliJ IDEA.
 * User: 长歌
 * Date: 2018/3/31
 * Time: 17:33
 * Description: 工厂类
 */
public class OfficeFactory {
    public Office newInstance(String type){
        if(type == null){
            return null;
        }
        if(type.equalsIgnoreCase("Word")){
            return new Word();
        } else if(type.equalsIgnoreCase("Excel")){
            return new Excel();
        } else if(type.equalsIgnoreCase("Powerpoint")){
            return new Powerpoint();
        }
        return null;
    }
}