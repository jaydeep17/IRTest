import edu.cmu.lti.ws4j.util.PorterStemmer;
import edu.washington.cs.knowitall.morpha.MorphaStemmer;
import org.apache.lucene.analysis.Analyzer;
import org.apache.lucene.analysis.TokenStream;
import org.apache.lucene.analysis.tokenattributes.CharTermAttribute;

import java.io.IOException;
import java.io.StringReader;
import java.util.ArrayList;
import java.util.List;

public final class LuceneUtil {

    public enum Stemmer {Porter, Morpho}

    private LuceneUtil() {}

    public static List<String> tokenizeString(Analyzer analyzer, String string) {
        List<String> result = new ArrayList<String>();
        TokenStream stream  = null;
        try {
            stream  = analyzer.tokenStream(null, new StringReader(string));
            stream.reset();
            while (stream.incrementToken()) {
                result.add(stream.getAttribute(CharTermAttribute.class).toString());
            }
        } catch (IOException e) {
            // not thrown b/c we're using a string reader...
            throw new RuntimeException(e);
        } finally {
            if(stream != null) try {
                stream.close();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
        return result;
    }


    public static List<String> stemWords(List<String> lst, Stemmer stmr) {
        switch (stmr) {
            case Porter:
                PorterStemmer stemmer = new PorterStemmer();
                for(int i = 0; i < lst.size(); ++i) {
                    lst.set(i, stemmer.stemWord(lst.get(i)));
                }
                break;
            case Morpho:
                // Morphological Stemmer
                for(int i = 0; i < lst.size(); ++i) {
                    lst.set(i, MorphaStemmer.stemToken(lst.get(i)));
                }
                break;
        }
        return lst;
    }
}
