package gz.aws.blog.impl;

import gz.aws.blog.MoodManager;
import gz.aws.blog.entity.Mood;
import gz.aws.persistence.impl.EntityObjectManagerImpl;

import javax.persistence.Query;
import java.util.Date;
import java.util.List;

/**
 * User: Kane Gong
 * Date: Sep 18, 2010
 * Time: 11:29:51 AM
 */
public class MoodManagerImpl
        extends EntityObjectManagerImpl<Mood>
        implements MoodManager {
    @Override
    public void add(Mood mood) {
        mood.setCreateDate(new Date());
        super.add(mood);
    }

    public Mood getLatestMood() {
        String sql = "Select m from Mood m order by m.id desc";
        Query query = createQuery(sql);
        List resultList = query.getResultList();
        if (resultList != null && resultList.size() > 0) {
            return (Mood) resultList.get(0);
        }
        return null;
    }

    public List<Mood> listAll() {
        String sql = "Select m from Mood m order by m.id desc";
        Query query = createQuery(sql);
        return query.getResultList();
    }
}
