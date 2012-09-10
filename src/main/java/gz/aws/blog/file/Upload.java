/**
 *
 * History:
 *   2010-6-16 22:49:45 Created by ZGong
 *//*

package gz.aws.blog.file;

import com.google.appengine.api.blobstore.BlobKey;
import com.google.appengine.api.blobstore.BlobstoreService;
import com.google.appengine.api.blobstore.BlobstoreServiceFactory;
import gz.aws.blog.PreferenceManager;
import gz.aws.blog.ioc.WebBlogUtil;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.Map;

*/
/**
 * Created by ZGong.
 *
 * @version 1.0 2010-6-16 22:49:45
 *//*

public class Upload
        extends HttpServlet {
    @Override
    protected void doPost(HttpServletRequest httpServletRequest, HttpServletResponse httpServletResponse)
            throws ServletException, IOException {
        BlobstoreService blobstoreService = BlobstoreServiceFactory.getBlobstoreService();
        Map<String, BlobKey> blobs = blobstoreService.getUploadedBlobs(httpServletRequest);
        BlobKey blobKey = blobs.get("myFile");
        PreferenceManager pm = WebBlogUtil.getPrefManager();
        pm.setPref(httpServletRequest.getParameter("key"), blobKey.getKeyString());
        pm.close();
    }
}
*/
