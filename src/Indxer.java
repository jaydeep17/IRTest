import org.apache.lucene.analysis.Analyzer;
import org.apache.lucene.analysis.standard.StandardAnalyzer;
import org.apache.lucene.document.*;
import org.apache.lucene.index.IndexWriter;
import org.apache.lucene.index.IndexWriterConfig;
import org.apache.lucene.index.Term;
import org.apache.lucene.store.Directory;
import org.apache.lucene.store.FSDirectory;
import org.apache.lucene.util.Version;

import javax.xml.stream.XMLStreamException;
import java.io.*;

public class Indxer {

    private IndexWriter indxWriter = null;

    public Indxer(String indxDir) throws IOException {
        Directory dir = FSDirectory.open(new File(indxDir));
        Analyzer analyzer = new StandardAnalyzer(Version.LUCENE_46);
        IndexWriterConfig iwc = new IndexWriterConfig(Version.LUCENE_46, analyzer);

        iwc.setOpenMode(IndexWriterConfig.OpenMode.CREATE); // remove any previous indxes
        iwc.setOpenMode(IndexWriterConfig.OpenMode.CREATE_OR_APPEND);   // keep prev indxes

        indxWriter = new IndexWriter(dir, iwc);
    }


    public void indxDir(String dataDir) throws IOException, XMLStreamException {
        final File docDir = new File(dataDir);
        if (!docDir.canRead()) {
            System.out.println("Can't read data Directory");
            return;
        }

        if (!docDir.isDirectory()) {
            System.out.println("[ "+dataDir+" ] is not a Directory");
            return;
        }

        File[] files = docDir.listFiles();
        if(files == null) {
            System.out.println("[ "+dataDir+" ] is empty!");
            return;
        }

        for (File f : files) {
//            if (f.getName().equals("en.13.1.415.2009.6.2"))
//                continue;
            System.out.println(f.getName());
            indxFile(f);
        }
    }


    public void indxFile(File file) throws IOException, XMLStreamException {
//        System.out.println(file);
        FileInputStream fis = new FileInputStream( file );
        Document doc = new Document();
        XmlDocument xmldoc = new XmlDocument(file.getAbsolutePath());

        String[] dates = IRUtils.extractDate(xmldoc.getContent(), xmldoc.getFilename());
        String delim = "";
        StringBuilder sb = new StringBuilder();
        for (String s : dates) {
            sb.append(delim).append(s);
            delim = ",";
        }
        String dateData = sb.toString();
        doc.add(new StringField("filename", xmldoc.getFilename(), Field.Store.YES));
        doc.add(new StringField("title", xmldoc.getTitle(), Field.Store.YES));
        doc.add(new TextField("date", dateData, Field.Store.YES));
        doc.add(new TextField("contents", xmldoc.getContent(), Field.Store.YES));

        if (indxWriter.getConfig().getOpenMode() == IndexWriterConfig.OpenMode.CREATE) {
            indxWriter.addDocument(doc);
        } else {
            indxWriter.updateDocument(new Term("title", xmldoc.getFilename()), doc);
        }
        fis.close();
    }

    public void killWriter() throws IOException {
        if(indxWriter != null) indxWriter.close();
    }
}
