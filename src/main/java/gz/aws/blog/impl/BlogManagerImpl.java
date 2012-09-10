/**
 *
 * History:
 *   2010-6-12 21:45:24 Created by ZGong
 */
package gz.aws.blog.impl;

import gz.aws.blog.BlogManager;
import gz.aws.blog.PageList;
import gz.aws.blog.entity.Blog;
import gz.aws.blog.entity.UserBean;
import gz.aws.blog.user.User;
import gz.aws.persistence.impl.EntityObjectManagerImpl;

import javax.persistence.Query;
import javax.persistence.TemporalType;
import java.util.Calendar;
import java.util.Date;
import java.util.List;

/**
 * Created by ZGong.
 *
 * @version 1.0 2010-6-12 21:45:24
 */
public class BlogManagerImpl
        extends EntityObjectManagerImpl<Blog>
        implements BlogManager {
    public BlogManagerImpl() {
        super(Blog.class);
    }

    public void add(Blog blog) {
        if (blog.getCreateDate() == null) {
            blog.setCreateDate(new Date());
        }
        blog.setModifyDate(blog.getCreateDate());
        super.add(blog);
    }

    public void delete(long id) {
        Blog blog = get(id);
        blog.setStatus(Blog.Status.DELETED);
        super.update(blog);
    }

    public void deleteByOwner(long id, User owner) {
        Query query = em.createQuery("select b from Blog b where b.id=:id and b.owner=:owner");
        query.setParameter("id", id);
        query.setParameter("owner", new UserBean(owner));
        Blog blog = (Blog) query.getSingleResult();
        blog.setStatus(Blog.Status.DELETED);
        em.merge(blog);
    }

    public void update(Blog blog) {
        blog.setModifyDate(new Date());
        super.update(blog);
    }

    public List<Blog> getAll() {
        Query query = em.createQuery("select b from Blog b where b.status=:status order by b.createDate desc");
        query.setParameter("status", Blog.Status.NORMAL);
        return query.getResultList();
    }

    @Override
    public PageList<Blog> getByPage(int pageIndex, int pageSize) {
        Query query = em.createQuery("select b from Blog b where b.status=:status order by b.createDate desc");
        query.setParameter("status", Blog.Status.NORMAL);
        query.setFirstResult(pageIndex * pageSize);
        query.setMaxResults(pageSize);
        final int totalCount = getTotalCount();
        return new PageListImpl<Blog>(pageIndex,pageSize,totalCount,query.getResultList());
    }

    private int getTotalCount() {
        Query query = em.createQuery("select count(b) from Blog b where b.status=:status");
        query.setParameter("status", Blog.Status.NORMAL);
        return ((Long) query.getSingleResult()).intValue();
    }

    public List<Blog> getAllByMonth(int month) {
        Query query = em.createQuery(
                "select b from Blog b where b.status=:status and b.createDate>=:monthStart and b.createDate<:monthEnd order by b.createDate desc");
        query.setParameter("status", Blog.Status.NORMAL);

        Calendar monthStart = Calendar.getInstance();
        monthStart.set(monthStart.get(Calendar.YEAR), month, 1, 0, 0, 0);

        Calendar monthEnd = (Calendar) monthStart.clone();
        monthEnd.add(Calendar.MONTH, 1);

        query.setParameter("monthStart", monthStart, TemporalType.DATE);
        query.setParameter("monthEnd", monthEnd, TemporalType.DATE);
        return query.getResultList();
    }

    public List<Blog> getAllByMonthOfYear(int year, int month) {
        Query query = em.createQuery(
                "select b from Blog b where b.status=:status and b.createDate>=:monthOfYearStart and b.createDate<:monthOfYearEnd order by b.createDate desc");
        query.setParameter("status", Blog.Status.NORMAL);

        Calendar monthStart = Calendar.getInstance();
        monthStart.set(year, month, 1, 0, 0, 0);

        Calendar monthEnd = (Calendar) monthStart.clone();
        monthEnd.add(Calendar.MONTH, 1);

        query.setParameter("monthOfYearStart", monthStart, TemporalType.DATE);
        query.setParameter("monthOfYearEnd", monthEnd, TemporalType.DATE);
        return query.getResultList();
    }

    @Override
    public int getBlogCountByMonthOfYear(int year, int month) {
        Query query = em.createQuery(
                "select count(b) from Blog b where b.status=:status and b.createDate>=:monthOfYearStart and b.createDate<:monthOfYearEnd order by b.createDate desc");
        query.setParameter("status", Blog.Status.NORMAL);

        Calendar monthStart = Calendar.getInstance();
        monthStart.set(year, month, 1, 0, 0, 0);

        Calendar monthEnd = (Calendar) monthStart.clone();
        monthEnd.add(Calendar.MONTH, 1);

        query.setParameter("monthOfYearStart", monthStart, TemporalType.DATE);
        query.setParameter("monthOfYearEnd", monthEnd, TemporalType.DATE);
        return ((Long) query.getSingleResult()).intValue();
    }

    public List<Blog> getByOwner(User owner) {
        Query query = em.createQuery(
                "select b from Blog b where b.owner=:owner and b.status=0 order by b.createDate desc");
        query.setParameter("owner", new UserBean(owner));
        return query.getResultList();
    }
}
