/**
 *
 * History:
 *   2010-6-25 23:13:14 Created by ZGong
 */
package gz.aws.blog;

import gz.aws.blog.entity.Comment;
import gz.aws.persistence.EntityObjectManager;

import java.util.List;

/**
 * Created by ZGong.
 *
 * @version 1.0 2010-6-25 23:13:14
 */
public interface CommentManager
        extends EntityObjectManager<Comment> {
    public List<Comment> getAllByBlog(long blogId);
}
