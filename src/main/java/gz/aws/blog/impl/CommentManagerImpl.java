/**
 *
 * History:
 *   2010-6-25 23:28:36 Created by ZGong
 */
package gz.aws.blog.impl;

import gz.aws.blog.CommentManager;
import gz.aws.blog.entity.Comment;
import gz.aws.persistence.impl.EntityObjectManagerImpl;

import javax.persistence.Query;
import java.util.Date;
import java.util.List;

/**
 * Created by ZGong.
 *
 * @version 1.0 2010-6-25 23:28:36
 */
public class CommentManagerImpl
        extends EntityObjectManagerImpl<Comment>
        implements CommentManager {
    public void add(Comment comment) {
        comment.setCreateDate(new Date());
        super.add(comment);
    }

    public void update(Comment comment) {
        throw new UnsupportedOperationException();
    }

    public List<Comment> getAllByBlog(long blogId) {
        Query query = em.createQuery("select c from Comment c where c.blogId=:blogId order by c.createDate desc");
        query.setParameter("blogId", blogId);
        return query.getResultList();
    }
}
