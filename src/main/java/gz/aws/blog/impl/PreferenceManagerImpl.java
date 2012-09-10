/**
 *
 * History:
 *   2010-6-16 22:55:38 Created by ZGong
 */
package gz.aws.blog.impl;

import gz.aws.blog.PreferenceManager;
import gz.aws.blog.entity.Preference;
import gz.aws.persistence.impl.EntityObjectManagerImpl;
import org.springframework.transaction.annotation.Transactional;

import javax.persistence.Query;
import java.util.List;

/**
 * Created by ZGong.
 *
 * @version 1.0 2010-6-16 22:55:38
 */
@Transactional
public class PreferenceManagerImpl
        extends EntityObjectManagerImpl<Preference>
        implements PreferenceManager {
    public String getPref(String name) {
        Query query = em.createQuery("select p.value from Preference p where p.name=:name");
        query.setParameter("name", name);
        List list = query.getResultList();
        if (!list.isEmpty()) {
            return (String) list.get(0);
        }
        return null;
    }

    public void setPref(String name, String value) {
        Query query = em.createQuery("select p from Preference p where p.name=:name");
        query.setParameter("name", name);
        List list = query.getResultList();
        if (list.isEmpty()) {
            Preference p = new Preference();
            p.setName(name);
            p.setValue(value);
            em.persist(p);
        } else {
            Preference p = (Preference) list.get(0);
            p.setValue(value);
            em.merge(p);
        }
    }

    public void removePref(String name) {
        Query query = em.createQuery("select p.value from Preference p where p.name=:name");
        query.setParameter("name", name);
        em.remove(query.getSingleResult());
    }

    public void close() {
        em.close();
    }
}
