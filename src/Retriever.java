import org.apache.lucene.analysis.Analyzer;
import org.apache.lucene.analysis.standard.StandardAnalyzer;
import org.apache.lucene.index.DirectoryReader;
import org.apache.lucene.index.IndexReader;
import org.apache.lucene.queryparser.classic.ParseException;
import org.apache.lucene.queryparser.classic.QueryParser;
import org.apache.lucene.search.IndexSearcher;
import org.apache.lucene.search.Query;
import org.apache.lucene.search.TopDocs;
import org.apache.lucene.store.FSDirectory;
import org.apache.lucene.util.Version;

import java.io.BufferedReader;
import java.io.File;
import java.io.IOException;
import java.io.InputStreamReader;

public class Retriever {

    public Retriever(String indxDir) throws IOException, ParseException {
        IndexReader reader = DirectoryReader.open(FSDirectory.open(new File(indxDir)));
        IndexSearcher searcher = new IndexSearcher(reader);
        Analyzer analyzer = new StandardAnalyzer(Version.LUCENE_46);

        QueryParser parser = new QueryParser(Version.LUCENE_46, "title", analyzer);
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in, "UTF-8"));
        while(true) {
            System.out.print("Query : ");
            String line = br.readLine();

            if(line == null) break;
            line = line.trim();
            if(line.isEmpty()) break;

            Query qry = parser.parse(line);
            System.out.println("Searching for: " + qry.toString("title"));
            TopDocs results = searcher.search(qry, 100);
        }
    }
}
