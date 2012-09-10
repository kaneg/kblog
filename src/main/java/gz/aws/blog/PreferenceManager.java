/**
 *
 * History:
 *   2010-6-16 22:54:37 Created by ZGong
 */
package gz.aws.blog;

import gz.aws.blog.entity.Preference;
import gz.aws.persistence.EntityObjectManager;
import org.springframework.transaction.annotation.Transactional;

/**
 * Created by ZGong.
 *
 * @version 1.0 2010-6-16 22:54:37
 */
public interface PreferenceManager
        extends EntityObjectManager<Preference>
{
    public String getPref(String key);

    @Transactional
    public void setPref(String key, String value);

    @Transactional
    public void removePref(String key);
}
