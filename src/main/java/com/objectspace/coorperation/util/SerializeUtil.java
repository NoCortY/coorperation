package com.objectspace.coorperation.util;

import java.io.*;

/**
* @Description: 序列化工具类,必须实现了Serializable接口的对象才可以进行序列化
* @Author: NoCortY
* @Date: 2019/10/4
*/
public class SerializeUtil {

    /**
     * @Description:  序列化对象
     * @Param: [obj]
     * @return: byte[]
     * @Author: NoCortY
     * @Date: 2019/10/4
     */
    public byte[] serialize(Object obj){
        ObjectOutputStream oos = null;
        ByteArrayOutputStream baos = null;
        try{
            baos = new ByteArrayOutputStream();
            oos = new ObjectOutputStream(baos);

            oos.writeObject(obj);
            byte[] byteArray = baos.toByteArray();
            return byteArray;

        }catch(IOException e){
            e.printStackTrace();
        }
        return null;
    }


    /**
     * @Description:  反序列化对象
     * @Param: [byteArray]
     * @return: java.lang.Object
     * @Author: NoCortY
     * @Date: 2019/10/4
     */
    public Object unSerialize(byte[] byteArray){
        ByteArrayInputStream bais = null;
        try {
            //反序列化为对象
            bais = new ByteArrayInputStream(byteArray);
            ObjectInputStream ois = new ObjectInputStream(bais);
            return ois.readObject();

        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }

}