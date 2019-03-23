package sg.edu.nus.comp.cs4218.impl.util;

import java.io.IOException;
import java.nio.file.*;
import java.nio.file.attribute.BasicFileAttributes;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;

import static java.nio.file.FileVisitResult.CONTINUE;
import static sg.edu.nus.comp.cs4218.impl.util.StringUtils.REGEX_FILE_SEP;
import static sg.edu.nus.comp.cs4218.impl.util.StringUtils.STRING_FILE_SEP;

public final class GlobUtil {
    private static String GLOB_SLASH = "/";

    /**
     *
     * Globs files in with respect to directory
     * @param dir
     * @param globPattern
     * @return Sorted file paths where
     * @throws IOException if dir not found
     */
    public static List<String> glob(Path dir, String globPattern) throws IOException {
        ArrayList<String> files = new ArrayList<String>();
        String fileSep = dir.toAbsolutePath().toString().endsWith(STRING_FILE_SEP) ? "" : STRING_FILE_SEP;
        String fullGlobPattern = dir.toAbsolutePath() + fileSep + globPattern;
        fullGlobPattern = fullGlobPattern.replace(STRING_FILE_SEP, REGEX_FILE_SEP);
        AbstractGlobFileVisitor visitor = new AbstractGlobFileVisitor(fullGlobPattern) {
            @Override
            void collect(Path file) {
                files.add(file.toString());
            }
        };
        Files.walkFileTree(dir, visitor);
        Collections.sort(files, Comparator.<String>naturalOrder());
        return files;
    }

    /**
     * File visitor for globbing files
     */
    public abstract static class AbstractGlobFileVisitor
            extends SimpleFileVisitor<Path> {

        private final PathMatcher matcher;
        private final String pattern;

        /**
         * Represents segments in glob pattern
         */
        private final String[] segments;

        /**
         * @param pattern pattern for matching file and directories
         */
        AbstractGlobFileVisitor(String pattern) {
            matcher = FileSystems
                    .getDefault()
                    .getPathMatcher("glob:" + pattern);
            segments = pattern.split(REGEX_FILE_SEP);
            this.pattern = pattern;
        }

        /**
         * Compares the glob pattern against
         * the file or directory name.
         * @param file
         */
        void find(Path file) {
            Path name = file.toAbsolutePath();
            boolean toCollect = name != null && matcher.matches(name);
            if (toCollect) {
                collect(file);
            }
        }

        /**
         * Collect function to be specified. Files and directories that match
         * pattern will be collected
         *
         * @param file
         */
        abstract void collect(Path file);

        @Override
        public FileVisitResult visitFile(Path file,
                                         BasicFileAttributes attrs) {
            find(file);
            return CONTINUE;
        }

        /**
         * Invoke the pattern matching method on each directory.
         * Stop visiting descendants if dir is end of pattern
         */
        @Override
        public FileVisitResult preVisitDirectory(Path dir,
                                                 BasicFileAttributes attrs) {                find(dir);
            if(toVisitDirDescendants(dir)) {
                return CONTINUE;
            }
            return FileVisitResult.SKIP_SUBTREE;
        }

        /**
         * Whether to visit descendants
         * @param dir
         * @return true if dir is not at the end of pattern
         */
        private boolean toVisitDirDescendants(Path dir) {
            String[] dirSegments = dir.toAbsolutePath().toString().split(REGEX_FILE_SEP);
            return dirSegments.length < segments.length;
        }

        @Override
        public FileVisitResult visitFileFailed(Path file,
                                               IOException exc) {
            return CONTINUE;
        }
    }

    /**
     * Utility class
     */
    private GlobUtil() {

    }
}