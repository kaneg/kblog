package gz.aws.blog.action;

import gz.aws.blog.FileDataManager;
import gz.aws.blog.PreferenceManager;
import gz.aws.blog.entity.FileData;
import gz.aws.blog.ioc.WebBlogUtil;

/**
 * User: Kane Gong
 * Date: Nov 27, 2010
 * Time: 7:46:04 PM
 */
public class PrefAction
        extends AuthedAction {
    FileData myPic;
    FileData myMusic;

    public FileData getMyMusic() {
        return myMusic;
    }

    public void setMyMusic(FileData myMusic) {
        this.myMusic = myMusic;
    }

    public FileData getMyPic() {
        return myPic;
    }

    public void setMyPic(FileData myPic) {
        this.myPic = myPic;
    }

    public String updateMyPic() throws Exception {
        FileData fd = myPic;
        if (fd != null) {
            PreferenceManager pm = WebBlogUtil.getPrefManager();
            String fId = pm.getPref("myPicture");
            if (fId != null) {
                FileDataManager fdm = WebBlogUtil.getFileDataManager();
                fdm.remove(Long.parseLong(fId));
                fdm.close();
            }
            pm.setPref("myPicture", String.valueOf(fd.getId()));
            pm.close();
        }
        return SUCCESS;
    }

    public String updateMyMusic() throws Exception {
        FileData fd = myMusic;
        if (fd != null) {
            PreferenceManager pm = WebBlogUtil.getPrefManager();
            String fId = pm.getPref("myMusic");
            if (fId != null) {
                FileDataManager fdm = WebBlogUtil.getFileDataManager();
                fdm.remove(Long.parseLong(fId));
                fdm.close();
            }
            pm.setPref("myMusic", String.valueOf(fd.getId()));
            pm.close();
        }
        return SUCCESS;
    }
}
