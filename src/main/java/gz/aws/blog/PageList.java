/**
 *
 * History:
 *   11-8-19 下午9:40 Created by ZGong
 */
package gz.aws.blog;

import java.util.List;

/**
 * Created by ZGong.
 *
 * @version 1.0 11-8-19 下午9:40
 */
public interface PageList<E> extends List<E> {
    public int getPageIndex();

    public int getPageSize();

    public int getTotalCount();

    public int getTotalPage();
}
