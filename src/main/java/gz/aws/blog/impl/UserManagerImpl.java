/**
 *
 * History:
 *   11-5-1 上午10:41 Created by ZGong
 */
package gz.aws.blog.impl;

import gz.aws.blog.UserManager;
import gz.aws.blog.entity.UserBean;
import gz.aws.blog.user.User;
import gz.aws.persistence.impl.EntityObjectManagerImpl;

import javax.persistence.Query;
import java.util.List;

/**
 * Created by ZGong.
 *
 * @version 1.0 11-5-1 上午10:41
 */
public class UserManagerImpl
        extends EntityObjectManagerImpl<UserBean>
        implements UserManager {

    public User create(String username, String password, String nickname, String email, boolean isAdmin) {
        UserBean userBean = new UserBean();
        userBean.setName(username);
        userBean.setNickname(nickname);
        userBean.setEmail(email);
        userBean.setPassword(password);
        userBean.setAdmin(isAdmin);
        add(userBean);
        return userBean.getUser();
    }

    public User create(User u, String password) {
        UserBean userBean = new UserBean();
        userBean.setPassword(password);
        userBean.setUser(u);
        add(userBean);
        return userBean.getUser();
    }

    public User getUser(String username) {
        Query query = em.createQuery("select u from UserBean u where u.name=:name");
        query.setParameter("name", username);
        List<UserBean> resultList = query.getResultList();
        if (!resultList.isEmpty()) {
            UserBean userBean = resultList.get(0);
            return userBean.getUser();
        }
        return null;
    }

    public User auth(String username, String password) {
        Query query = em.createQuery("select u from UserBean u where u.name=:name and u.password=:password");
        query.setParameter("name", username);
        query.setParameter("password", password);
        List<UserBean> resultList = query.getResultList();
        if (!resultList.isEmpty()) {
            UserBean userBean = resultList.get(0);
            return userBean.getUser();
        }
        return null;
    }

    public User[] listAllUsers() {
        List<UserBean> resultList = listAll();
        if (resultList != null) {
            UserBean[] userBeans = resultList.toArray(new UserBean[resultList.size()]);
            User[] users = new User[userBeans.length];
            for (int i = 0; i < userBeans.length; i++) {
                users[i] = userBeans[i].getUser();
            }
            return users;
        }
        return new User[0];
    }
}
