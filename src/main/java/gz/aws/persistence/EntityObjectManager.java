/**
 *
 * History:
 *   2010-6-30 22:21:17 Created by ZGong
 */
package gz.aws.persistence;

import org.springframework.transaction.annotation.Transactional;

import javax.persistence.Query;
import java.util.List;

/**
 * Created by ZGong.
 *
 * @version 1.0 2010-6-30 22:21:17
 */
public interface EntityObjectManager<E> {
    @Transactional
    public void add(E e);

    @Transactional
    public void remove(long id);

    public E get(long id);

    @Transactional
    public void update(E e);

    public void close();

    public Query createQuery(String sql);

    public List<E> listAll();
}
