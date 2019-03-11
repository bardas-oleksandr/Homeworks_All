package Problem_01_FileVisitor;

import java.io.IOException;
import java.nio.file.FileVisitResult;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.SimpleFileVisitor;
import java.nio.file.attribute.BasicFileAttributes;
import java.util.concurrent.ExecutorService;

public class SearcherFileVisitor extends SimpleFileVisitor<Path> {
    private ExecutorService executor;
    private String text;
    private SynchronizedOutputStream out;

    SearcherFileVisitor(ExecutorService executor, String text, SynchronizedOutputStream out){
        this.executor = executor;
        this.text = text;
        this.out = out;
    }

    public FileVisitResult visitFile(Path path, BasicFileAttributes attribs)throws IOException{
        if(!Files.isDirectory(path)){
            executor.execute(new MappedTextSearcher(path,text,out));
        }
        return FileVisitResult.CONTINUE;
    }
}
