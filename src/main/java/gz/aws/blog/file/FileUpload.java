/**
 *
 * History:
 *   2010-6-27 23:52:38 Created by ZGong
 */
package gz.aws.blog.file;

import gz.aws.blog.FileDataManager;
import gz.aws.blog.entity.FileData;
import gz.aws.blog.ioc.WebBlogUtil;
import org.apache.commons.fileupload.FileItemIterator;
import org.apache.commons.fileupload.FileItemStream;
import org.apache.commons.fileupload.servlet.ServletFileUpload;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.logging.Logger;

/**
 * Created by ZGong.
 *
 * @version 1.0 2010-6-27 23:52:38
 */
public class FileUpload
        extends HttpServlet {
    private static final Logger log = Logger.getLogger(FileUpload.class.getName());

    public void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        try {
            ServletFileUpload upload = new ServletFileUpload();
            upload.setHeaderEncoding("utf-8");
            FileItemIterator iterator = upload.getItemIterator(request);
            while (iterator.hasNext()) {
                FileItemStream item = iterator.next();
                InputStream stream = item.openStream();

                if (item.isFormField()) {
                    log.warning("Got a form field: " + item.getFieldName());
                    int len;
                    byte[] buffer = new byte[8192];
                    ByteArrayOutputStream baos = new ByteArrayOutputStream();
                    while ((len = stream.read(buffer, 0, buffer.length)) != -1) {
                        //response.getOutputStream().write(buffer, 0, len);
                        baos.write(buffer, 0, len);
                    }
                    stream.close();
                    baos.close();
                    byte[] bytes = baos.toByteArray();
                    log.info(item.getFieldName() + ":" + new String(bytes, "utf-8"));
                    request.setAttribute(item.getFieldName(), new String(bytes, "utf-8"));
                } else {
                    log.warning("Got an uploaded file: " + item.getFieldName() +
                            ", name = " + item.getName());

                    // You now have the filename (item.getName() and the
                    // contents (which you can read from stream).  Here we just
                    // print them back out to the servlet output stream, but you
                    // will probably want to do something more interesting (for
                    // example, wrap them in a Blob and commit them to the
                    // datastore).
                    int len;
                    byte[] buffer = new byte[8192];
                    ByteArrayOutputStream baos = new ByteArrayOutputStream();
                    while ((len = stream.read(buffer, 0, buffer.length)) != -1) {
                        //response.getOutputStream().write(buffer, 0, len);
                        baos.write(buffer, 0, len);
                    }
                    stream.close();
                    baos.close();
                    byte[] bytes = baos.toByteArray();
                    bytes = process(bytes);
                    FileDataManager fdm = WebBlogUtil.getFileDataManager();
                    FileData fd = new FileData();
                    fd.setContentType(item.getContentType());
                    fd.setFileName(item.getName());
                    fd.setData(bytes);
                    fd.setSize(fd.getData().length);
                    fdm.add(fd);
                    fdm.close();
                    request.setAttribute(item.getFieldName(), fd);
                    String forwardUrl = request.getParameter("forward_url");
                    if (forwardUrl != null) {
                        request.getRequestDispatcher(forwardUrl).forward(request, response);
                    } else {
                        String redirectUrl = request.getParameter("redirect_url");
                        if (redirectUrl != null) {
                            response.sendRedirect(redirectUrl);
                        }
                    }
                }
            }
        } catch (Exception ex) {
            throw new ServletException(ex);
        }
    }

    protected byte[] process(byte[] bytes) {
        return bytes;
    }
}
