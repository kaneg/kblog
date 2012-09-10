/**
 *
 * History:
 *   11-5-1 上午10:40 Created by ZGong
 */
package gz.aws.blog;

import gz.aws.blog.entity.UserBean;
import gz.aws.blog.user.User;
import gz.aws.persistence.EntityObjectManager;
import org.springframework.transaction.annotation.Transactional;

/**
 * Created by ZGong.
 *
 * @version 1.0 11-5-1 上午10:40
 */
public interface UserManager extends EntityObjectManager<UserBean> {
    @Transactional
    User create(String username, String password, String nickname, String email, boolean isAdmin);

    @Transactional
    User create(gz.aws.blog.user.User u, String password);

    User getUser(String username);

    User auth(String username, String password);

    User[] listAllUsers();
}
