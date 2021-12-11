package Tools;

import com.gargoylesoftware.htmlunit.WebClient;
import com.gargoylesoftware.htmlunit.html.HtmlDivision;
import com.gargoylesoftware.htmlunit.html.HtmlPage;
import java.io.IOException;
import java.util.logging.Level;

public class WhoIs {

    public WhoIs() {

    }

    public String getdata(String ip) {
        java.util.logging.Logger.getLogger("com.gargoylesoftware").setLevel(Level.OFF);
        System.setProperty("org.apache.commons.logging.Log", "org.apache.commons.logging.impl.NoOpLog");
        String result = "";
        try (final WebClient webClient = new WebClient()) {
            final HtmlPage page = webClient.getPage("https://who.is/whois/" + ip);
            //Thông tin registra
            //Name: /html/body/div[3]/div[2]/div[5]/div[1]/div[3]/div/div[1]/div[2]
            //WhoisURL: /html/body/div[3]/div[2]/div[5]/div[1]/div[3]/div/div[2]/div[2]
            //Referral URL:/html/body/div[3]/div[2]/div[5]/div[1]/div[3]/div/div[3]/div[2]
            //ngày hết hạn: /html/body/div[3]/div[2]/div[5]/div[1]/div[5]/div/div[1]/div[2]
            //ngày đăng ký: /html/body/div[3]/div[2]/div[5]/div[1]/div[5]/div/div[2]/div[2]
            //ngày cập nhật: /html/body/div[3]/div[2]/div[5]/div[1]/div[5]/div/div[3]/div[2]
            try {
                HtmlDivision divname = (HtmlDivision) page.getByXPath("/html/body/div[3]/div[2]/div[5]/div[1]/div[3]/div/div[1]/div[2]").get(0);
                HtmlDivision divwho = (HtmlDivision) page.getByXPath("/html/body/div[3]/div[2]/div[5]/div[1]/div[3]/div/div[2]/div[2]").get(0);
                HtmlDivision divurl = (HtmlDivision) page.getByXPath("/html/body/div[3]/div[2]/div[5]/div[1]/div[3]/div/div[3]/div[2]").get(0);
                HtmlDivision divexpire = (HtmlDivision) page.getByXPath("/html/body/div[3]/div[2]/div[5]/div[1]/div[5]/div/div[1]/div[2]").get(0);
                HtmlDivision divregister = (HtmlDivision) page.getByXPath("/html/body/div[3]/div[2]/div[5]/div[1]/div[5]/div/div[2]/div[2]").get(0);
                HtmlDivision divupdate = (HtmlDivision) page.getByXPath("/html/body/div[3]/div[2]/div[5]/div[1]/div[5]/div/div[3]/div[2]").get(0);

                String name = divname.asNormalizedText();
                String who = divwho.asNormalizedText();
                String url = divurl.asNormalizedText();
                String expire = divexpire.asNormalizedText();
                String register = divregister.asNormalizedText();
                String update = divupdate.asNormalizedText();

                if (name.equals("") && who.equals("") && expire.equals("")) {
                    result = "Không tìm thấy thông tin";
                } else {
                    result = "+Thông tin Whois " + "\r\n"
                            + "   *Registra: \r\n"
                            + "       Tên: " + name + " \r\n"
                            + "       WhoisURL: " + who + " \r\n"
                            + "       URL: " + url + " \r\n"
                            + "   *Thời gian hết hạn: " + expire + " \r\n"
                            + "   *Thời gian đăng ký: " + register + " \r\n"
                            + "   *Thời gian cập nhật: " + update + " \r\n";
                }
            } catch (Exception e) {

            }
        } catch (IOException e) {

        }

        return result;
    }

    public static void main(String[] args) throws IOException {
        WhoIs w = new WhoIs();
        System.out.println(w.getdata("youtube.com"));
    }
}
