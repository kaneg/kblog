package gz.aws.blog.action;

import gz.aws.blog.MoodManager;
import gz.aws.blog.entity.Mood;
import gz.aws.blog.ioc.WebBlogUtil;

import java.io.ByteArrayInputStream;
import java.io.InputStream;
import java.util.List;

/**
 * User: Kane Gong
 * Date: Nov 27, 2010
 * Time: 4:14:18 PM
 */
public class MoodAction
        extends AuthedAction {
    String mood;
    String mId;
    List<Mood> moods;
    InputStream inputStream;

    public String getMId() {
        return mId;
    }

    public void setMId(String mId) {
        this.mId = mId;
    }

    public String getMood() {
        return mood;
    }

    public void setMood(String mood) {
        this.mood = mood;
    }

    public List<Mood> getMoods() {
        return moods;
    }


    public InputStream getInputStream() {
        return inputStream;
    }

    public String change() throws Exception {
        getUser();
        if (mood != null && !"".equals(mood.trim())) {
            MoodManager mm = WebBlogUtil.getMoodManager();
            Mood m = new Mood();
            m.setMood(mood);
            mm.add(m);
            mm.close();
            inputStream = new ByteArrayInputStream("OK".getBytes());
        }
        return SUCCESS;
    }

    public String delete() throws Exception {
        if (mId != null && !"".equals(mId.trim())) {
            MoodManager mm = WebBlogUtil.getMoodManager();
            mm.remove(Long.parseLong(mId));
            mm.close();
        }
        return SUCCESS;
    }

    public String list() throws Exception {
        MoodManager moodManager = WebBlogUtil.getMoodManager();
        moods = moodManager.listAll();
        return SUCCESS;
    }
}
