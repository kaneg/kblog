/**
 *
 * History:
 *   11-9-17 上午12:00 Created by ZGong
 */
package gz.aws.blog.util;

import org.apache.struts2.ServletActionContext;

import java.io.*;

/**
 * Created by ZGong.
 *
 * @version 1.0 11-9-17 上午12:00
 */
public class FileUtil {
    public static void writeFile(InputStream is, File outFile) throws IOException {
        BufferedOutputStream bos = null;
        try {
            bos = new BufferedOutputStream(new FileOutputStream(outFile));
            byte[] buffer = new byte[4096];
            int len;
            BufferedInputStream bis = new BufferedInputStream(is);
            while ((len = bis.read(buffer)) != -1) {
                bos.write(buffer, 0, len);
            }
        } finally {
            if (bos != null) {
                bos.close();
            }
        }
    }

    public static void writeFile(byte[] buffer, File outFile) throws IOException {
        BufferedOutputStream bos = null;
        try {
            bos = new BufferedOutputStream(new FileOutputStream(outFile));
            bos.write(buffer);
        } finally {
            if (bos != null) {
                bos.close();
            }
        }
    }

    public static File getTmpDirectory() {
        try {
            final File abc = File.createTempFile("abc", ".tmp");
            if (abc.exists()) {
                return abc.getParentFile();
            }
        } catch (IOException e) {
            return new File(System.getProperty("java.io.tmpdir"));
        }
        return new File(System.getProperty("java.io.tmpdir"));
    }

    public static File getImageCacheDirectory() {
        final String realPath = ServletActionContext.getServletContext().getRealPath("/");
        final File imageCache = new File(realPath, "imageCache");
        if (!imageCache.exists()) {
            imageCache.mkdir();
        }
        return imageCache;
    }
}
