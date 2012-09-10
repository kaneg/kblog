/**
 *
 * History:
 *   11-4-16 下午1:23 Created by ZGong
 */
package gz.aws.blog.user;

/**
 * Created by ZGong.
 *
 * @version 1.0 11-4-16 下午1:23
 */
public class UserServiceFactory {
    private static ThreadLocal<UserService> userServiceThreadLocal = new ThreadLocal<UserService>();

    public static UserService getUserService() {
        UserService userService = userServiceThreadLocal.get();
        if (userService == null) {
            userService = new UserService();
            userServiceThreadLocal.set(userService);
        }
        return userService;
    }

    public static void logout() {
        userServiceThreadLocal.remove();
    }

    public static UserService createUserService(User user) {
        UserService userService = new UserService(user);
        userServiceThreadLocal.set(userService);
        return userService;
    }

    public static void setUserService(UserService userService) {
        userServiceThreadLocal.set(userService);
    }

    public static void clearUserService() {
        userServiceThreadLocal.remove();
    }
}
