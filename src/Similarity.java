import edu.cmu.lti.jawjaw.pobj.POS;
import edu.cmu.lti.lexical_db.ILexicalDatabase;
import edu.cmu.lti.lexical_db.NictWordNet;
import edu.cmu.lti.lexical_db.data.Concept;
import edu.cmu.lti.ws4j.Relatedness;
import edu.cmu.lti.ws4j.RelatednessCalculator;
import edu.cmu.lti.ws4j.impl.WuPalmer;
import edu.cmu.lti.ws4j.util.WS4JConfiguration;
import org.apache.lucene.analysis.Analyzer;
import org.apache.lucene.analysis.standard.StandardAnalyzer;
import org.apache.lucene.util.Version;

import java.util.List;

public class Similarity {
    public static double sentence(String s1, String s2) {
        Analyzer analyzer = new StandardAnalyzer(Version.LUCENE_46);
        List<String> tokens1 = LuceneUtil.tokenizeString(analyzer, s1);
        List<String> tokens2 = LuceneUtil.tokenizeString(analyzer, s2);
        tokens1 = LuceneUtil.stemWords(tokens1, LuceneUtil.Stemmer.Morpho);
        tokens2 = LuceneUtil.stemWords(tokens2, LuceneUtil.Stemmer.Morpho);

        // FIXME : below just adds up the values in similarity matrix
        // this might need some work
        double sum = 0.0;
        for (String t1 : tokens1) {
            for (String t2 : tokens2) {
                sum += Similarity.word(t1, t2);
            }
        }
        return sum;
    }

    public static double word(String word1, String word2) {
        ILexicalDatabase db = new NictWordNet();
        WS4JConfiguration.getInstance().setMFS(true);
        RelatednessCalculator rc = new WuPalmer(db);
        List<POS[]> posPairs = rc.getPOSPairs();
        double maxScore = -1D;

        for(POS[] posPair: posPairs) {
            List<Concept> synsets1 = (List<Concept>)db.getAllConcepts(word1, posPair[0].toString());
            List<Concept> synsets2 = (List<Concept>)db.getAllConcepts(word2, posPair[1].toString());

            for(Concept synset1: synsets1) {
                for (Concept synset2: synsets2) {
                    Relatedness relatedness = rc.calcRelatednessOfSynset(synset1, synset2);
                    double score = relatedness.getScore();
                    if (score > maxScore)
                        maxScore = score;
                }
            }
        }

        if (maxScore == -1D) maxScore = 0.0;
        return maxScore;
    }
}
