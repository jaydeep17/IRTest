import javax.xml.stream.XMLStreamException;
import java.io.IOException;
import java.io.*;

public class Main {

    public static void main(String[] args) throws IOException, XMLStreamException {

        Indxer indxr = new Indxer("/home/jaydeep/IR-data/indxDir/");
//        indxr.indxDir("/home/jaydeep/IR-data/en.docs.2011/en_BDNews24/1/");
        indxr.indxDir("/home/jaydeep/IR-data/en.docs.2011/en_BDNews24/1/");
        indxr.killWriter();
        System.out.println("Done indexing!");

//        try {
//            XmlDocument xdoc = new XmlDocument("/home/jaydeep/en.docs.2011/en_TheTelegraph_2001-2010/telegraph_1st_jan_2002_to_31st_dec_2002/nation/1020823_nation_story_1129065.utf8");
//            System.out.println(xdoc.getFilename());
//            System.out.println(xdoc.getContent());
//        } catch (XMLStreamException e) {
//            e.printStackTrace();
//        }

//        String arr[] = IRUtils.extractDate("Prakash Biswas bdnews24.com senior legal affairs correspondent Dhaka, Sep 30 (bdnews24.com)A plaintiff has accused a speedy tribunal judge of delaying a murder trial by repeatedly sending back witnesses from court. Shyamol Chandra Sutradhar has brought the allegation against judge Mohammed Mohsin of Speedy Tribunal-2 in Dhaka. The judge sent the case last week to the home ministry on the excuse that he felt embarrassed, a bench clerk of the court said on Thursday. The case involves charges of killing three members of a Hindu family at Dhobaura in Mymensingh. Plaintiff's lawyer Kazi Mohammed Nazibullah Hiru, from the solicitor wing of the law ministry, has also confirmed the allegation. According to the case statement, there were land disputes between the families of Haridas Sutradhar and Omar Ali at Chorer Bhita village of Char Kendua union under Dhobaura upazila. On Jan 22, 2006, Pro-BNPJubo Dal leader Motahar Hossain Pradip attacked Haridas on behalf of Ali when Haridas had gobe to sow paddy on the disputed land with others. Haridas and Bimal Sutradhar were killed while another man, Niranjan Sutradhar, was injured. Niranjan later died from the wounds. The next day, their relative Shyamol filed a case with Dhobaura Police Station. On June 8, 2006, Golam Sarwar, sub-inspector of Dhobaura, submitted charge sheet accusing Pradip, Ali, Abdur Rahim, Hosne Ara, Ashraf Ali, Abdur Razzak, Rahim, Al-Amin, Hanif, Ziaul Haque, Ershad Ali, Anisur Rahman Manik, Rafiqul, Shafiqul, Rokeya and 'Bulbul' of the killings. On July 22, 2006, the case was transferred to the Speedy Tribunal-2 in Dhaka from Mymensingh District Judge's Court for quick disposal. But, judge Mohsin framed charges on May 7 after 41 dates on different excuses. Of them, 19 dates were fixed for forming charge only. \"The judge delays taking deposition and keeps sending back the witnesses from the prosecution side time and again so that the case goes back to its former court, the District Judge's Court of Mymensingh,\" said plaintiff Sutradhar in a written statement to bdnews24.com. According to the rules of speedy trial tribunal, if a case is stayed over 135 working days, it goes back to the former court. Though over a 100 working days have passed, deposition of only five of 32 prosecution witnesses have been taken. \"If the case goes back to the Mymensingh court, then the defendants will prevent the plaintiff to give depositions. In that case, it will not be possible to prove the allegations,\" the plaintiff feared. The files of the case show that the sixth witness, Khitish Chandra Sutradhar, came to give deposition on Sunday, the day fixed for it. But his deposition was not taken. Also, the cause list register of cases shows that no other cases were due on that day, which could have been a reason for not taking the deposition. After the case came to trial, the judge has approved time petition 42 times on behalf of the defendants until Aug 3, according to court documents. Mohammed Borhan Uddin, the lawyer of defendants Rahim, Razzak and Pradip, also criticised the judge. \"The judge behaves like this. He declines to take depositions in almost every case. Both sides are poor in this case. It is not possible for them to influence the trial,\" he told bdnews24.com. Iftekhar Ali Bhuiyan, bench clerk of the court, said the deposition did not took place as special public prosecutor of the court Rafikul Islam Benu was indisposed because of illness. Nazibullah Hiru, the plaintiff's lawyer, told bdnews24.com: \"The judge could allow me to question the prosecution witnesses. But he did not do so.\" bdnews2", "en.13.1.3.2009.6.1");
//        for (String x : arr) {
//            System.out.println(x);
//        }

    }
}