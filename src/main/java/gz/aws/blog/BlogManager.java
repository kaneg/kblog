/**
 *
 * History:
 *   2010-6-12 21:42:44 Created by ZGong
 */
package gz.aws.blog;

import gz.aws.blog.entity.Blog;
import gz.aws.blog.user.User;
import gz.aws.persistence.EntityObjectManager;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

/**
 * Created by ZGong.
 *
 * @version 1.0 2010-6-12 21:42:44
 */
public interface BlogManager
        extends EntityObjectManager<Blog> {
    /**
     * delete a blog
     *
     * @param id
     */
    @Transactional
    public void delete(long id);

    /**
     * delete a blog by owner
     *
     * @param id
     * @param owner
     */
    @Transactional
    public void deleteByOwner(long id, User owner);

    /**
     * list all blogs
     *
     * @return
     */
    public List<Blog> getAll();

    public PageList<Blog> getByPage(int pageIndex, int pageSize);

    public List<Blog> getAllByMonth(int month);

    public List<Blog> getAllByMonthOfYear(int year, int month);

    public int getBlogCountByMonthOfYear(int year, int month);

    /**
     * get all blogs of the owner
     *
     * @param owner
     * @return
     */
    public List<Blog> getByOwner(User owner);
}
