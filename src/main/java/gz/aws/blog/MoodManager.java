package gz.aws.blog;

import gz.aws.blog.entity.Mood;
import gz.aws.persistence.EntityObjectManager;

/**
 * User: Kane Gong
 * Date: Sep 18, 2010
 * Time: 11:30:12 AM
 */
public interface MoodManager
        extends EntityObjectManager<Mood> {
    public Mood getLatestMood();
}
