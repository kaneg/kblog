/**
 *
 * History:
 *   11-5-4 下午1:00 Created by ZGong
 */
package gz.aws.blog.util;

import gz.aws.blog.entity.UserBean;
import gz.aws.blog.user.User;

import java.lang.reflect.Array;
import java.util.List;

/**
 * Created by ZGong.
 *
 * @version 1.0 11-5-4 下午1:00
 */
public class FooUtil {
    public static <A, B> B[] AtoB(A[] as,To<A,B> to, Class b) {
        B[] users = (B[]) Array.newInstance(b, as.length);
        for (int i = 0; i < as.length; i++) {
            users[i] = to.to(as[i]);
        }
        return users;
    }

    public static <T> T getT(T c) {
        T[] users = (T[]) Array.newInstance(c.getClass(), 10);
        return c;
    }

    public static <T extends Comparable<? super T>> void sort(List<T> list) {

    }

    public static void main(String[] args) {
        String o = getT("o");

        UserBean[] ubs = new UserBean[5];
        for (int i = 0; i < ubs.length; i++) {
            ubs[i] = new UserBean(new User(null,"a", "b", "c",false));
        }
        User[] users = FooUtil.AtoB(ubs, new To<UserBean, User>() {
            public User to(UserBean userBean) {
                return userBean.getUser();
            }
        }, User.class);
        System.out.println(users);
    }
}

interface To<A, B> {
    public B to(A a);
}
