package shellCommond;


import org.apache.commons.lang3.StringUtils;

import javax.swing.*;
import java.io.*;
import java.net.InetAddress;
import java.util.*;
import java.util.zip.ZipEntry;
import java.util.zip.ZipFile;
import java.util.zip.ZipOutputStream;

import Thread.Car;

public class PakgeUpload {

//    private static String EMPTY = "";


    public static void main(String[] args) throws Exception{
//
//        List<String> numList = new ArrayList<>(100);
//        numList.add("100");
//        numList.add("99");
//        numList.add("1");
//        numList.add("10");
//        numList.add("9");
//        numList.add("1000");
////        numList.add(100);
////        numList.add(99);
////        numList.add(1);
////        numList.add(10);
////        numList.add(9);
////        numList.add(1000);
//        for (int i = 0; i < 1000000; i++) {
//            numList.add(String.valueOf(i));
//        }
//
//        List<Integer> StringList = new ArrayList<>();
//
//        System.out.println(numList);
//
//        long start = System.currentTimeMillis();
//
////        for (String s : numList) {//1484 1191 1169 //848 874 960
////            StringList.add(Integer.valueOf(s));
////        }
//
////        for (int i = 0; i < numList.size(); i++) {//1138 1114 1146  // 980 911 941
////            StringList.add(Integer.valueOf(numList.get(i)));
////        }
////
////        Collections.sort(StringList);
//        numList.sort(Comparator.comparingInt()); //207 //215
////        System.out.println(StringList +"+++++++++");
//        System.out.println((System.currentTimeMillis() - start));


//        skip();

//
//        try {
//            RemoteShellExecutor executor = new RemoteShellExecutor("135.251.103.208", "root", "accs#2345");
//
//           ///nokia/enms/bin/restart.sh
//            ///nokia/enms/bin/status.sh
////            executor.exec("/Users/zhangzhiqiang/Documents/my_projects/bdexample/springboot-mybatis-demo/apidoc.sh");
////            executor.exec("sh /nokia/enms/bin/restart.sh");
////            executor.exec("sh /nokia/enms/bin/status.sh");
//        } catch (Exception e) {
//            e.printStackTrace();
//        }


        upload();


//        InetAddress ia = InetAddress.getLocalHost();
////        String localname=ia.getHostName();
//        String localip = ia.getHostAddress();
//        System.out.println("localip：" + localip);
    }

    static class ZipCompress {
        private String zipFileName;      // 目的地Zip文件
        private String sourceFileName;   //源文件（带压缩的文件或文件夹）

        public ZipCompress(String zipFileName, String sourceFileName) {
            this.zipFileName = zipFileName;
            this.sourceFileName = sourceFileName;
        }

        public void zip() throws Exception {
            //File zipFile = new File(zipFileName);
            System.out.println("压缩中...");

            //创建zip输出流
            ZipOutputStream out = new ZipOutputStream(new FileOutputStream(zipFileName));

            //创建缓冲输出流
            BufferedOutputStream bos = new BufferedOutputStream(out);

            File sourceFile = new File(sourceFileName);

            //调用函数
            compress(out, bos, sourceFile, sourceFile.getName());

            bos.close();
            out.close();
            System.out.println("压缩完成");

        }

        public void compress(ZipOutputStream out, BufferedOutputStream bos, File sourceFile, String base) throws Exception {
            //如果路径为目录（文件夹）
            if (sourceFile.isDirectory()) {

                //取出文件夹中的文件（或子文件夹）
                File[] flist = sourceFile.listFiles();

                if (flist.length == 0)//如果文件夹为空，则只需在目的地zip文件中写入一个目录进入点
                {
                    System.out.println(base + "/");
                    out.putNextEntry(new ZipEntry(base + "/"));
                } else//如果文件夹不为空，则递归调用compress，文件夹中的每一个文件（或文件夹）进行压缩
                {
                    for (int i = 0; i < flist.length; i++) {
                        compress(out, bos, flist[i], base + "/" + flist[i].getName());
                    }
                }
            } else//如果不是目录（文件夹），即为文件，则先写入目录进入点，之后将文件写入zip文件中
            {
                out.putNextEntry(new ZipEntry(base));
                FileInputStream fos = new FileInputStream(sourceFile);
                BufferedInputStream bis = new BufferedInputStream(fos);

                int tag;
                System.out.println(base);
                //将源文件写入到zip文件中
                while ((tag = bis.read()) != -1) {
                    bos.write(tag);
                }
                bis.close();
                fos.close();

            }
        }
    }

    private static void skip() {
        Map<Car, List<Car>> list = new HashMap<>();

        List<Car> carList = new ArrayList<Car>();
        for (int i = 0; i < 10; i++) {

            Car car = new Car();
            car.setKey(String.valueOf(i));
//            car.setList(new ArrayList<>());

            car.getList().add(String.valueOf(i));
            carList.add(car);
            list.put(car, carList);
        }

        System.out.println(list);
        for (Map.Entry<Car, List<Car>> map : list.entrySet()) {

            Car car = map.getKey();
            List<Car> cars = map.getValue();


            if (!car.getKey().equals("3")) {
                System.out.println(car.getKey() + "*****" + car.getList());
                car.getList().addAll(car.getList());
            }
        }

        for (Map.Entry<Car, List<Car>> map : list.entrySet()) {

            Car car = map.getKey();
            List<Car> cars = map.getValue();

            System.out.println(car.getKey() + "*****" + car.getList());

        }

        List<Car> carList1 = new ArrayList<Car>(list.keySet());
        for (Car car : carList1) {
            System.out.println(car.getKey());
        }


//        skipIDS();
    }

    private static void skipIDS() {
        String str = "Root/1/11/5";
        String st = "Root/1/11/6";
        String s = "Root/1/11/7";
        String str2 = "Root/2/12/a";
        String st2 = "Root/2/12/b";
        String s22 = "Root/2/12/c";

        String s1 = "Root/1/13/999";
        String s2 = "Root/1/12/6666";
        String s3 = "Root/3/5e";
        String s4 = "Root/4/5";
        String s5 = "Root/5/7";
        String s6 = "Root/6/6";

        List<String> list = new ArrayList<>();
        list.add(s);
        list.add(st);
        list.add(s1);
        list.add(s2);
        list.add(s3);
        list.add(str);
        list.add(str2);
        list.add(st2);
        list.add(s22);
        list.add(s4);
        list.add(s5);
        list.add(s6);


        System.out.println(list);

        List<String> openList = new ArrayList<>();
        openList.add("Root");
        openList.add("Root/1");
        openList.add("Root/2");
        openList.add("Root/1/13");
        openList.add("Root/1/11");
//        Pattern p = Pattern.compile("(\\d+\\.\\d+)");


        Map<String, List<String>> skip = new HashMap<>();

        for (String open : openList) {

            List<String> skipID = new ArrayList<>();
            skip.put(open, skipID);

            for (String opensub : openList) {

                if (!open.equals(opensub) && opensub.startsWith(open)) {
                    skip.get(open).add(opensub);
                }

            }
        }


        System.out.println(skip);
//            for (Map<String, List<String>> stringListMap : skip) {
//                if (null == stringListMap) {
//
//                } else {
//
//                }
//            }


        Set<String> stringSet = new HashSet<>();
        for (Map.Entry<String, List<String>> map : skip.entrySet()) {

            String key = map.getKey();
            System.out.println("**********************key: " + key);

            List<String> skipList = map.getValue();
            for (int i = 0; i < list.size(); i++) {

                String db = list.get(i);

                if (db.startsWith(key)) {

                    boolean f = true;
                    for (String breaKKK : skipList) {
                        if (db.startsWith(breaKKK)) {
                            f = false;
                            break;
                        }

                    }
                    if (f) {
                        boolean add = stringSet.add(db);
//                list.remove(db);
                        if (add) {
                            System.out.println(db);
                        }
                    }

                }
                ;
            }
        }


//        Pattern p = Pattern.compile("(\\d+\\.\\d+)");
//
//        Matcher matcher = p.matcher(str);
//
//        if (matcher.find()) {
//            str = matcher.group(1) == null ? "" : matcher.group(1);
//        } else {
//            p = Pattern.compile("(\\d+)");
//            matcher = p.matcher(str);
//            if (matcher.find()) {
//                //如果有整数相匹配
//                str = matcher.group(1) == null ? "" : matcher.group(1);
//            } else {
//                //如果没有小数和整数相匹配,即字符串中没有整数和小数，就设为空
//                str = "";
//            }
//        }
//        System.out.println(PakgeUpload.class.getTypeName());


    }

    private static void upload() {

        try {

//            String time  = "201910311100";
//            TimeZone.setDefault(TimeZone.getTimeZone("GMT"));
//            Calendar calendar  = Calendar.getInstance();
//            int year = Integer.valueOf(time.substring(0,4));
//            int moth = Integer.valueOf(time.substring(4,6));
//            int day = Integer.valueOf(time.substring(6,8));
//            int hour = Integer.valueOf(time.substring(8,10));
//            int min = Integer.valueOf(time.substring(10,12));
//            System.out.println(year+" "+moth+" "+day+" "+hour+" "+min);
//            calendar.set(Calendar.MONTH,moth-1);
//            calendar.set(Calendar.YEAR,year);
//            calendar.set(Calendar.DAY_OF_MONTH,day);
//            calendar.set(Calendar.HOUR_OF_DAY,hour);
//
//            TimeZone.setDefault(TimeZone.getTimeZone("GMT"));
//            System.out.println(calendar.getTime());
//
//            long epochTime = calendar.getTimeInMillis();
//            epochTime -= epochTime % 1000;
//            calendar.setTimeInMillis(epochTime);
//
//            System.out.println(calendar.getTime().toString());
//
//            System.out.println(epochTime);
//            System.out.println(String.valueOf(epochTime));

            //135.251.103.209
            //135.251.103.226
            //135.251.96.183
            String str = JOptionPane.showInputDialog("请输入IP:","135.251.103.226");
            if (StringUtils.isEmpty(str)) {
                JOptionPane.showMessageDialog(null, "IP不合法！", "提示框", JOptionPane.ERROR_MESSAGE);
                return;
            }

            TestUpload2 upload2 = new TestUpload2();
//

            TestUpload2.setHost(str);


            ////        TestUpload2.setHost("135.251.103.207");
////        TestUpload2.setHost("135.251.103.214");
////        TestUpload2.setHost("135.251.96.232");
////        TestUpload2.setHost("135.251.103.208");
////        TestUpload2.setHost("135.251.103.159");
////        TestUpload2.setHost("135.251.96.92");
////        TestUpload2.setHost("135.251.96.93");

//            TestUpload2.setHost("135.251.96.224");
            TestUpload2.clearLog = false;
            TestUpload2.reStart_Enms_2 = false;

//        TestUpload2.getConnect();
//        upload2.download("controller.jar");

            upload2.doShell("D:/gitRepository");

//        upload2.doShell("D:/Workspace/ENMS2.0/");
//            JOptionPane.showMessageDialog(null, "OK!", "OK!", JOptionPane.PLAIN_MESSAGE);
            System.exit(0);
        } catch (Exception e) {
            JOptionPane.showMessageDialog(null, e.getMessage(), "提示框", JOptionPane.ERROR_MESSAGE);
        }

    }

}
