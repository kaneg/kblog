package gz.aws.blog.impl;

import gz.aws.blog.BlogManager;
import gz.aws.blog.PageList;
import gz.aws.blog.entity.Blog;
import gz.aws.blog.user.User;
import gz.aws.blog.user.UserService;
import gz.aws.blog.user.UserServiceFactory;

import java.util.ArrayList;
import java.util.List;

/**
 * User: kane
 * Date: Sep 4, 2010
 * Time: 8:28:10 PM
 */
public class PermissionBlogManager
        extends BlogManagerImpl
        implements BlogManager {
    User user;

    public PermissionBlogManager() {
        UserService userService = UserServiceFactory.getUserService();
        user = userService.getCurrentUser();
    }

    private List<Blog> filterByPermission(List<Blog> list) {
        List<Blog> list2 = new ArrayList<Blog>();
        for (Blog blog : list) {
            Blog.Permission permission = blog.getPermission();
            if (permission == null || permission == Blog.Permission.PUBLIC) {
                list2.add(blog);
            } else if (permission == Blog.Permission.SECRET) {
                if (blog.getOwner().getUser().equals(user)) {
                    list2.add(blog);
                }
            }
        }
        return list2;
    }

    public List<Blog> getAll() {
        return filterByPermission(super.getAll());
    }

    @Override
    public List<Blog> getAllByMonth(int month) {
        return filterByPermission(super.getAllByMonth(month));
    }

    @Override
    public List<Blog> getAllByMonthOfYear(int year, int month) {
        return filterByPermission(super.getAllByMonthOfYear(year, month));
    }

    public List<Blog> getByOwner(User owner) {
        return filterByPermission(super.getByOwner(owner));
    }

    @Override
    public PageList<Blog> getByPage(int pageIndex, int pageSize) {
        final PageList<Blog> byPage = super.getByPage(pageIndex, pageSize);
        return new PageListImpl<Blog>(byPage.getPageIndex(), byPage.getPageSize(), byPage.getTotalCount(), filterByPermission(byPage));
    }
}
