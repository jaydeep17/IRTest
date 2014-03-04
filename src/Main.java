import org.apache.lucene.queryparser.classic.ParseException;

import javax.xml.stream.XMLStreamException;
import java.io.IOException;



public class Main {

    public static void main(String[] args) throws IOException, XMLStreamException, ParseException {
//
//        File f = new File(ErrorWriter.errorFileName);
//        if(f.exists()) f.delete();
//
//        Indxer indxr = new Indxer("/home/jaydeep/IR-data/indxDir/");
//        indxr.indxDir("/home/jaydeep/IR-data/en.docs.2011/en_BDNews24/");
//        indxr.killWriter();
//        System.out.println("Done indexing!");

//        Retriever r = new Retriever("/home/jaydeep/IR-data/indxDir/");
//        r.search("bomb","title", 100);

        double x = Similarity.sentence("Gandhiji's life came to end by Nathuram", "Nathuram assassinated Gandhiji");
        System.out.println(x);
    }
}