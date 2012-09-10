/**
 *
 * History:
 *   2010-7-6 21:49:35 Created by ZGong
 */
package gz.aws.blog.ioc;

import gz.aws.blog.*;
import gz.aws.ioc.IocUtil;

import javax.servlet.ServletContext;

/**
 * Created by ZGong.
 *
 * @version 1.0 2010-7-6 21:49:35
 */
public class WebBlogUtil {
    private static ServletContext CONTEXT;

    public static FileDataManager getFileDataManager() {
        assert CONTEXT != null;
//        return (FileDataManager) IocUtil.get(CONTEXT, "cached_file_data_manager");
        return (FileDataManager) IocUtil.get(CONTEXT, "file_data_manager");
    }

    public static void setServletContext(ServletContext context) {
        CONTEXT = context;
    }

    public static BlogManager getBlogManager() {
        assert CONTEXT != null;
        return (BlogManager) IocUtil.get(CONTEXT, "blog_manager");
    }

    public static PhotoManager getPhotoManager() {
        assert CONTEXT != null;
        return (PhotoManager) IocUtil.get(CONTEXT, PhotoManager.class.getSimpleName());
    }

    public static MoodManager getMoodManager() {
        assert CONTEXT != null;
        return (MoodManager) IocUtil.get(CONTEXT, MoodManager.class.getSimpleName());
    }

    public static PreferenceManager getPrefManager() {
        assert CONTEXT != null;
        return (PreferenceManager) IocUtil.get(CONTEXT, PreferenceManager.class.getSimpleName());
    }

    @SuppressWarnings("unchecked")
    public static <T> T getManager(Class<T> t) {
        assert CONTEXT != null;
        return (T) IocUtil.get(CONTEXT, t.getSimpleName());
    }

    public static CommentManager getCommentManager() {
        assert CONTEXT != null;
        return (CommentManager) IocUtil.get(CONTEXT, CommentManager.class.getSimpleName());
    }

    public static UserManager getUserManager() {
        assert CONTEXT != null;
        return (UserManager) IocUtil.get(CONTEXT, UserManager.class.getSimpleName());
    }

    public static AlbumManager getAlbumManager() {
        assert CONTEXT != null;
        return (AlbumManager) IocUtil.get(CONTEXT, "album_manager");
    }
}
