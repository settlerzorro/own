package shellCommond;

import com.jcraft.jsch.*;
import org.apache.commons.lang3.StringUtils;

import java.io.*;
import java.util.*;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.ConcurrentMap;

import Thread.ThreadPoolUtil;

import javax.swing.*;

public class TestUpload2 {

    private static ChannelSftp sftp = null;

    //账号
    private static String user = "root";
    //主机ip
    private static String host = "";
    //密码
    private static String password = "accs#2345";
    //端口
    private static int port = 22;
    //上传地址
    private static String directory = "/nokia/enms/lib";
    //下载目录
//    private static String saveFile = "D:"+File.separator;
    private static String saveFile = System.getProperty("user.home", "c:" + File.separator + "PM" + File.separator);

    private static Session sshSession;

    public static boolean clearLog = false;
    public static boolean reStart_Enms_2 = false;

    private static Calendar cal = Calendar.getInstance();


    public static TestUpload2 getConnect() {

        cal.add(Calendar.HOUR_OF_DAY, -1);


        TestUpload2 ftp = new TestUpload2();
        try {
            JSch jsch = new JSch();

            //获取sshSession  账号-ip-端口
            sshSession = jsch.getSession(user, host, port);
            //添加密码
            sshSession.setPassword(password);
            Properties sshConfig = new Properties();
            //严格主机密钥检查
            sshConfig.put("StrictHostKeyChecking", "no");

            sshSession.setConfig(sshConfig);
            //开启sshSession链接
            sshSession.connect();
            //获取sftp通道
            Channel channel = sshSession.openChannel("sftp");
            //开启
            channel.connect();
            sftp = (ChannelSftp) channel;

            sftp.cd(directory);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return ftp;
    }

    private static Vector<String> stdout = new Vector<>();

    public static Vector<String> getStandardOutput() {
        return stdout;
    }

    public static void setHost(String h) {
        host = h;
    }

    public static int executeShell(final String command) {
        int returnCode = -1;
        try {

            // Create and connect channel.
            Channel channel = sshSession.openChannel("exec");

            ((ChannelExec) channel).setCommand(command);

            channel.setInputStream(null);
            BufferedReader input = new BufferedReader(new InputStreamReader(channel
                    .getInputStream()));

            channel.connect();

            System.out.println("The remote command is: " + command);

            // Get the output of remote command.
            String line;
            while ((line = input.readLine()) != null) {
                System.out.println(line);
            }
            input.close();

            // Get the return code only after the channel is closed.
            if (channel.isClosed()) {
                returnCode = channel.getExitStatus();
            }

            // Disconnect the channel and session.
//            channel.disconnect();
        } catch (Exception e) {
            System.out.println("executeShell:" + e.getMessage());
        }
        return returnCode;
    }

    /**
     * @param uploadFile 上传文件的路径
     *                   //     * @return 服务器上文件名
     */
    public void doShell(String uploadFile) {
        try {

            Long start = System.currentTimeMillis();

            getConnect();

            upload(uploadFile);

            while (true) {

                try {
                    Thread.sleep(100);
                } catch (Exception e) {
                    System.out.println("sleep:" + e.getMessage());
                }

                boolean a = ThreadPoolUtil.instance().close2();

                if (a) {
//                    System.out.println(TestUpload2.i + "   耗時：" + (System.currentTimeMillis() - start));
                    uploadThread();
                    String info = TestUpload2.i + "   耗時：" + (System.currentTimeMillis() - start);
                    System.out.println(info);
//                    System.out.println(queueFiles);
                    JOptionPane.showMessageDialog(null, info, "upload", JOptionPane.PLAIN_MESSAGE);
                    break;
                }
            }


            if (clearLog) {
                clearLog();
            }

            if (reStart_Enms_2) {
                reStart();
            }
            System.out.println("disconnect！");
//            disconnect();
        } catch (Exception e) {
            System.out.println("doShell" + e.getMessage());
        }
    }

    /**
     * @param uploadFile 上传文件的路径
     *                   //     * @return 服务器上文件名
     */
    public void TestFile(String uploadFile) {
        try {

            Long start = System.currentTimeMillis();

            getConnect();

            upload(uploadFile);

            while (true) {

                try {
                    Thread.sleep(100);
                } catch (Exception e) {
                    System.out.println("sleep:" + e.getMessage());
                }

                boolean a = ThreadPoolUtil.instance().close2();

                if (a) {
//                    System.out.println(TestUpload2.i + "   耗時：" + (System.currentTimeMillis() - start));
                    uploadThread();
                    String info = TestUpload2.i + "   耗時：" + (System.currentTimeMillis() - start);
                    System.out.println(info);
//                    System.out.println(queueFiles);
                    JOptionPane.showMessageDialog(null, info, "upload", JOptionPane.PLAIN_MESSAGE);
                    break;
                }
            }


            if (clearLog) {
                clearLog();
            }
            if (reStart_Enms_2) {
                reStart();
            }

            System.out.println("disconnect！");
            disconnect();
        } catch (Exception e) {
            System.out.println("doShell" + e.getMessage());
        }
    }

    private void uploadThread() {

        for (Iterator<Map.Entry<String, File>> it = queueFiles.entrySet().iterator(); it.hasNext(); ) {
            Map.Entry<String, File> next = it.next();

            ThreadPoolUtil.instance().addTask(() -> {

                //                    System.out.println(formatter.format(cal2.getTime()) + "   >>>>>  " + name);
                synchronized (StringUtils.EMPTY) {
                    i++;
                    try {
                        sftp.put(new FileInputStream(next.getValue()), next.getKey(), ChannelSftp.OVERWRITE);
                    } catch (Exception e) {

                        System.out.println("uploadThread" + e.getMessage());
                    }
                }
            });
        }
        while (true) {

            try {
                Thread.sleep(100);
            } catch (Exception e) {
                System.out.println("sleep:" + e.getMessage());
            }

            boolean a = ThreadPoolUtil.instance().close2();

            if (a) {
                break;

            }
        }
    }

    private static void clearLog() {

            String command = "rm -rf /nokia/enms/log";
            executeShell(command);


        JOptionPane.showMessageDialog(null, "clearLog!", "clearLog", JOptionPane.PLAIN_MESSAGE);
//        getStandardOutput().forEach(s -> {
//                    System.out.println(s);
//                }
//
//        );
    }

    private static void reStart() {

//        String command = "sh /nokia/enms/bin/restart.sh";

        String command = "sh /nokia/enms/bin/restart.sh";


        executeShell(command);

        String com = "sh /nokia/enms/bin/status.sh";

        executeShell(com);

        JOptionPane.showMessageDialog(null, "OK", "reStart", JOptionPane.PLAIN_MESSAGE);

    }

    /**
     * @param uploadFile 上传文件的路径
     *                   //     * @return 服务器上文件名
     */
    public void upload(String uploadFile) {
        try {

            File file = new File(uploadFile);

            if (file.isDirectory()) {

                File[] files = file.listFiles();

                if (files == null || files.length <= 0) {
                    return;
                }

                ThreadPoolUtil.instance().addTask(() -> {
                    try {
                        dealDirectory(files);
                    } catch (Exception e) {
                        System.out.println("dealDirectory:" + e);
                    }
                });
//                dealDirectory(uploadFile);
            } else {
                wetherJarAndPut(file.getName(), file);
            }
        } catch (Exception e) {
            System.out.println("upload" + e.getMessage());
        }
    }

    public void disconnect() {
        sftp.disconnect();
    }

    public Map<String, String> executeCmd(String cmd) {
        Runtime rt = Runtime.getRuntime(); // 运行时系统获取
        Map<String, String> lineMap = new HashMap<String, String>();//存放返回值
        try {
            Process p = rt.exec(cmd);// 执行命令
            InputStream stderr = p.getInputStream();//执行结果 得到进程的标准输出信息流
            InputStreamReader isr = new InputStreamReader(stderr);//将字节流转化成字符流
            BufferedReader br = new BufferedReader(isr);//将字符流以缓存的形式一行一行输出
            String line = null;
            while ((line = br.readLine()) != null) {
                if (!StringUtils.isEmpty(line)) {
                    String[] strLine = line.split(":");
                    if (strLine.length >= 2) {
                        lineMap.put(strLine[0].trim(), strLine[1].trim());
                    }

                }
            }
            br.close();
            isr.close();
            stderr.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
        return lineMap;
    }

    private void dealDirectory(File[] files) {
//        System.out.println(uploadFile);


        for (File f : files) {

            String fp = f.getAbsolutePath();
//            System.out.println("getAbsolutePath:"+fp);
            if (f.isDirectory()) {

                uploadAgain(f, fp);

            } else {

                String name = f.getName();
                wetherJarAndPut(name, f);
            }
        }
    }

    public static int i = 0;
    //    public static Set<String> list = new HashSet<>();
//    public LinkedBlockingQueue<Map<String,File>> queueFiles = new LinkedBlockingQueue<>();
    public ConcurrentMap<String, File> queueFiles = new ConcurrentHashMap<>();

    private void wetherJarAndPut(String name, File f) {
//        System.out.println(name);


        if (name.contains(".") && "jar".equals(name.substring(name.length() - 3))) {


            Calendar cal2 = Calendar.getInstance();
            cal2.setTimeInMillis(f.lastModified());
  //          System.out.println(name);
//            if (cal.after(cal2)) {
//                return;
//            }
            if (queueFiles.containsKey(name)) {

                File old = queueFiles.get(name);

                if (old.lastModified() < f.lastModified()) {
                    queueFiles.put(name, f);
                }

            } else {
                System.out.println(name);
                queueFiles.put(name, f);
            }

        }

    }

    private void uploadAgain(File f, String fp) {
//            String mkdir = directory + "/" + f.getName();
//                System.out.println("fp:" + fp);
        ThreadPoolUtil.instance().addTask(() -> upload(fp));

    }

    /**
     * 下载文件
     * <p>
     * //     * @param directory
     * 下载目录
     * //     * @param downloadFile
     * 下载的文件名
     * //     * @param saveFile
     * 存在本地的路径
     * //     * @param sftp
     */
    public void download(String downloadFileName) {
        try {
            sftp.cd(directory);
            System.out.println("PWD:" + sftp.pwd());
            Vector fileList = sftp.ls(directory);

            for (Object o : fileList) {
                System.out.println(o);
                String fileName = String.valueOf(o);
                String[] strs = fileName.split(" ");
//                System.out.println(strs[strs.length-1]);
                if (strs[strs.length - 1].startsWith("A15")) {
                    downloadFileName = strs[strs.length - 1];
                }
            }

            File file = new File(saveFile + File.separator + downloadFileName);

//            sftp.get(downloadFileName, new FileOutputStream(file));
//            System.out.println("download:"+downloadFileName);
            System.out.println(file.getName());
            String[] names = file.getName().split("_");
            if ("A15".equals(names[0])) {
                System.out.println(0);
            } else {
                System.out.println(1);
            }


            if (null != names[1]) {
                System.out.println(names[1]);
            }
            System.out.println(names.length);


        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    /**
     * 删除文件
     *
     * @param deleteFile 要删除的文件名字
     *                   //     * @param sftp
     */
    public void delete(String deleteFile) {
        try {
            sftp.cd(directory);
            sftp.rm(deleteFile);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    /**
     * 列出目录下的文件
     *
     * @param directory 要列出的目录
     *                  //     * @param sftp
     * @return
     * @throws SftpException
     */
    public Vector listFiles(String directory)
            throws SftpException {
        return sftp.ls(directory);
    }

}