import org.apache.lucene.analysis.Analyzer;
import org.apache.lucene.analysis.core.WhitespaceAnalyzer;
import org.apache.lucene.analysis.standard.StandardAnalyzer;
import org.apache.lucene.document.Document;
import org.apache.lucene.index.DirectoryReader;
import org.apache.lucene.index.IndexReader;
import org.apache.lucene.queryparser.classic.ParseException;
import org.apache.lucene.queryparser.classic.QueryParser;
import org.apache.lucene.search.*;
import org.apache.lucene.store.FSDirectory;
import org.apache.lucene.util.Version;

import java.io.File;
import java.io.IOException;

public class Retriever {

    private IndexReader reader;
    private IndexSearcher searcher;
    private Analyzer standardAnalyzer;
    private Analyzer whiteSpaceAnalyzer;

    public Retriever(String indxDir) throws IOException, ParseException {
        reader = DirectoryReader.open(FSDirectory.open(new File(indxDir)));
        searcher = new IndexSearcher(reader);
        standardAnalyzer = new StandardAnalyzer(Version.LUCENE_46);
        whiteSpaceAnalyzer = new WhitespaceAnalyzer(Version.LUCENE_46);
    }

    public void search(String searchFor, String searchInField, int maxHits) throws ParseException, IOException {
        QueryParser parser = new QueryParser(Version.LUCENE_46, searchInField, standardAnalyzer);
        Query qry = parser.parse(searchFor);
        System.out.println("Searching for: " + qry.toString());
        TopDocs results = searcher.search(qry, maxHits, new Sort(new SortField("title", SortField.Type.STRING)));
        ScoreDoc[] hits = results.scoreDocs;

        int numTotalHits = results.totalHits;
        System.out.println(numTotalHits + " total matching documents");

        for(int i = 0; i < hits.length; ++i) {
            Document doc = searcher.doc(hits[i].doc);
            System.out.println(doc.get("filename"));
//            System.out.printf("%.10f %s\n", hits[i].score, doc.get("filename"));
        }
    }
}
