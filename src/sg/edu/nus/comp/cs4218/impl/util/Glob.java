package sg.edu.nus.comp.cs4218.impl.util;

import java.io.IOException;
import java.nio.file.*;
import java.nio.file.attribute.BasicFileAttributes;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import static java.nio.file.FileVisitResult.CONTINUE;
import static sg.edu.nus.comp.cs4218.impl.util.StringUtils.STRING_FILE_SEP;

public class Glob {

    /**
     *
     * @param dir
     * @param globPattern
     * @return Sorted file paths where
     * @throws IOException
     */
    public static List<String> glob(Path dir, String globPattern) throws IOException {
        ArrayList<String> files = new ArrayList<String>();
        globPattern = dir.toAbsolutePath() + STRING_FILE_SEP + globPattern;
        GlobFileVisitor visitor = new GlobFileVisitor(globPattern) {
            @Override
            void collect(Path file) {
                files.add(file.toString());
            }
        };
        Files.walkFileTree(dir, visitor);
        Collections.sort(files);
        return files;
    }

    public abstract static class GlobFileVisitor
            extends SimpleFileVisitor<Path> {

        private PathMatcher matcher;
        private int numMatches = 0;

        GlobFileVisitor(String pattern) {
            matcher = FileSystems
                    .getDefault()
                    .getPathMatcher("glob:" + pattern);
        }

        // Compares the glob pattern against
        // the file or directory name.
        void find(Path file) {
            Path name = file.toAbsolutePath();
            if (name != null && matcher.matches(name)) {
                collect(file);
            }
        }

        /**
         * Collect function to be
         * @param file
         */
        abstract void collect(Path file);

        // Invoke the pattern matching
        // method on each file.
        @Override
        public FileVisitResult visitFile(Path file,
                                         BasicFileAttributes attrs) {
            find(file);
            return CONTINUE;
        }

        // Invoke the pattern matching
        // method on each directory.
        @Override
        public FileVisitResult preVisitDirectory(Path dir,
                                                 BasicFileAttributes attrs) {
            find(dir);
            return CONTINUE;
        }

        @Override
        public FileVisitResult visitFileFailed(Path file,
                                               IOException exc) {
            return CONTINUE;
        }
    }
}