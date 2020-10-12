package shellCommond;

import org.apache.commons.collections4.list.TreeList;

import java.io.File;
import java.util.*;

public class TestZip {

    public static void main(String[] args) {

        List<Double> weight = new ArrayList<>();
        weight.add(0.0);
        weight.add(0.1);
        weight.add(0.3);
        weight.add(0.5);
        weight.add(0.6);
        int timeQuantum = 60;
        TreeMap<String, Double> classes = new TreeMap<>();
        classes.put("R1", (weight.get(0) * timeQuantum));
        classes.put("R2", (weight.get(1) * timeQuantum));
        classes.put("R3", (weight.get(2) * timeQuantum));
        classes.put("R4", (weight.get(3) * timeQuantum));
        classes.put("R5", (weight.get(4) * timeQuantum));

        Set<Map.Entry<String, Double>> keys =  classes.entrySet();
        int count = 30;
        String result = "";
        for (Map.Entry<String, Double> key : keys) {
            System.out.println(key.getKey() + "-----------" + key.getValue());

            if(count<key.getValue()){
                break;
            }
            result = key.getKey();
        }
        System.out.println(result);


////        PakgeUpload.ZipCompress zipCom = new PakgeUpload.ZipCompress("D:\\Workspace\\LiuPeng\\work\\lpTestMongoDB\\.\\log\\tools\\tmp.zip","D:\\Workspace\\LiuPeng\\work\\lpTestMongoDB\\.\\log\\tools\\tools_log - 副本");
////        try
////        {
////            zipCom.zip();
////        }
////        catch(Exception e)
////        {
////            e.printStackTrace();
////        }
//        File file = new File("D:\\\\Workspace\\\\LiuPeng\\\\work\\\\lpTestMongoDB\\\\.\\\\log\\\\tools\\\\tools_log.text");
//        String parent = file.getParent();
//        String temp = parent + File.separator + file.getName() + "tmp";
//        File file1 = new File(temp);
//        boolean flag = file.renameTo(file1);
//        System.out.println(flag);
////        file.setExecutable()
//        if (flag) {
//            System.out.println("ok");
//            try {
//                file.createNewFile();
//            } catch (Exception e) {
//                System.out.println(e);
//            }
//        }

    }
}