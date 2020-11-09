package shellCommond;


import org.apache.commons.lang3.StringUtils;

import javax.swing.*;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.TimeZone;

public class PakgeUpload {

    public static void main(String[] args) throws Exception{
        upload();
    }
    private static void upload() {
        try {
            //135.251.103.209
            //135.251.103.226
            //135.251.96.183
            String str = JOptionPane.showInputDialog("请输入IP:","135.251.103.226");
            if (StringUtils.isEmpty(str)) {
                JOptionPane.showMessageDialog(null, "IP不合法！", "提示框", JOptionPane.ERROR_MESSAGE);
                return;
            }
            TestUpload2 upload2 = new TestUpload2();
            TestUpload2.setHost(str);
            TestUpload2.clearLog = false;
            TestUpload2.reStart_Enms_2 = false;
            upload2.doShell("D:/ideaProjects");
            System.exit(0);
        } catch (Exception e) {
            JOptionPane.showMessageDialog(null, e.getMessage(), "提示框", JOptionPane.ERROR_MESSAGE);
        }
    }
}
