/**
 *
 * History:
 *   2010-6-30 22:30:01 Created by ZGong
 */
package gz.aws.persistence.impl;

import gz.aws.persistence.EntityObjectManager;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;
import java.lang.reflect.ParameterizedType;
import java.util.List;

/**
 * Created by ZGong.
 *
 * @version 1.0 2010-6-30 22:30:01
 */
public abstract class EntityObjectManagerImpl<E>
        implements EntityObjectManager<E> {
    protected EntityManager em;
    private Class<E> entityClass;

    @PersistenceContext
    public void setEm(EntityManager em) {
        this.em = em;
    }

    public EntityObjectManagerImpl() {
        ParameterizedType genericSuperclass = (ParameterizedType) getClass().getGenericSuperclass();
        this.entityClass = (Class<E>) genericSuperclass.getActualTypeArguments()[0];
    }

    protected EntityObjectManagerImpl(Class<E> entityClass) {
        this.entityClass = entityClass;
    }

    public void add(E e) {
        em.persist(e);
    }

    public void remove(long id) {
        E e = this.get(id);
        em.remove(e);
    }

    public E get(long id) {
        return em.find(entityClass, id);
    }

    public void update(E e) {
        em.merge(e);
    }

    public void close() {
//        em.close();
    }

    public Query createQuery(String sql) {
        return em.createQuery(sql);
    }

    @Override
    public List<E> listAll() {
        String sql = "select bt from " + entityClass.getName() + " bt";
        Query query = em.createQuery(sql);
        return query.getResultList();
    }
}
