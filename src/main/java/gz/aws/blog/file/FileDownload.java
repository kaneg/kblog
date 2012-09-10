/**
 *
 * History:
 *   2010-6-28 0:34:52 Created by ZGong
 */
package gz.aws.blog.file;

import gz.aws.blog.FileDataManager;
import gz.aws.blog.entity.FileData;
import gz.aws.blog.ioc.WebBlogUtil;
import gz.aws.blog.util.FileUtil;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.File;
import java.io.IOException;

/**
 * Created by ZGong.
 *
 * @version 1.0 2010-6-28 0:34:52
 */
public class FileDownload
        extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        long fId = Long.parseLong(request.getParameter("fId"));
        File imageCacheDir = FileUtil.getImageCacheDirectory();
        if (!imageCacheDir.exists()) {
            FileDataManager fdm = WebBlogUtil.getFileDataManager();
            FileData data = fdm.get(fId);
            byte[] buffer = data.getData();
            response.setContentType(data.getContentType());
            response.getOutputStream().write(buffer);
            return;
        }
        File photo = new File(imageCacheDir, fId + ".jpg");
        if (!photo.exists()) {
            FileDataManager fdm = WebBlogUtil.getFileDataManager();
            FileData data = fdm.get(fId);
            byte[] buffer = data.getData();
            FileUtil.writeFile(buffer, photo);
        }
        response.sendRedirect("/imageCache/" + photo.getName());
    }
}
