import org.apache.lucene.queryparser.classic.ParseException;

import javax.xml.stream.XMLStreamException;
import java.io.IOException;



public class Main {

    public static void main(String[] args) throws IOException, XMLStreamException, ParseException {

//        File f = new File(ErrorWriter.errorFileName);
//        if(f.exists()) f.delete();
//
//        Indxer indxr = new Indxer("/home/jaydeep/IR-data/indxDir/");
//        indxr.indxDir("/home/jaydeep/IR-data/en.docs.2011/en_BDNews24/");
//        indxr.killWriter();
//        System.out.println("Done indexing!");

//        Retriever r = new Retriever("/home/jaydeep/IR-data/indxDir/");
//        r.search("bomb","title", 100);

        String s1 = "let's play", s2 = "let's play";
        double x = Similarity.sentence(s1, s2);
        System.out.println(x);
        Similarity.printSimilarityMatrix(s1, s2);
    }
}