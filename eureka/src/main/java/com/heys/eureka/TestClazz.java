package com.heys.eureka;

import org.gjt.jclasslib.io.ClassFileWriter;
import org.gjt.jclasslib.structures.CPInfo;
import org.gjt.jclasslib.structures.ClassFile;
import org.gjt.jclasslib.structures.constants.ConstantUtf8Info;

import java.io.DataInput;
import java.io.DataInputStream;
import java.io.File;
import java.io.FileInputStream;

/**
 * @author heys
 * @email HeYisheng_style@yeah.net
 * @create 2018/4/10
 * @company kingxunlian
 * <p>
 * <p>test
 * <p>
 */

public class TestClazz {

    public static void main(String[] args) throws Exception {

        //String filePath = "C:\\work\\file\\pms\\WEB-INF\\classes\\com\\moreyou\\pms\\business\\service\\impl\\SupplierServiceImpl.class";
        String filePath = "C:\\work\\file\\pms\\WEB-INF\\classes\\com\\moreyou\\pms\\business\\service\\impl\\CgOrderInDetailServiceImpl.class";
        //String filePath = "C:\\work\\file\\pms\\WEB-INF\\classes\\com\\moreyou\\core\\security\\LoginListener.class";
        FileInputStream fis = new FileInputStream(filePath);

        DataInput di = new DataInputStream(fis);
        ClassFile cf = new ClassFile();
        cf.read(di);
        CPInfo[] infos = cf.getConstantPool();

        int count = infos.length;
        for (int i = 0; i < count; i++) {
            if (infos[i] != null) {
                System.out.print(i);
                System.out.print(" = ");
                System.out.print(infos[i].getVerbose());
                System.out.print(" = ");
                System.out.println(infos[i].getTagVerbose());
                if(i == 548){
                    ConstantUtf8Info uInfo = (ConstantUtf8Info)infos[i];
                    uInfo.setBytes("http://172.18.16.10:8195/order".getBytes());
                    infos[i]=uInfo;
                }

                if(i == 1132){
                    ConstantUtf8Info uInfo = (ConstantUtf8Info)infos[i];
                    uInfo.setBytes("http://172.18.16.10:8195/register".getBytes());
                    infos[i]=uInfo;
                }
            }
        }
        cf.setConstantPool(infos);
        fis.close();
        File f = new File(filePath);
        ClassFileWriter.writeToFile(f, cf);
    }
}
