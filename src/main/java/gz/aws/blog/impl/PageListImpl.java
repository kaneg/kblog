/**
 *
 * History:
 *   11-8-19 下午9:41 Created by ZGong
 */
package gz.aws.blog.impl;

import gz.aws.blog.PageList;

import java.util.AbstractList;
import java.util.List;

/**
 * Created by ZGong.
 *
 * @version 1.0 11-8-19 下午9:41
 */
public class PageListImpl<E> extends AbstractList<E> implements PageList<E> {
    private int totalCount;
    private List<E> list;
    private int pageIndex;
    private int pageSize;

    public PageListImpl(int pageIndex, int pageSize, int totalCount, List<E> list) {
        this.pageIndex = pageIndex;
        this.totalCount = totalCount;
        this.pageSize = pageSize;
        this.list = list;
    }

    @Override
    public int getPageIndex() {
        return pageIndex;
    }

    @Override
    public int getPageSize() {
        return pageSize;
    }

    @Override
    public int getTotalCount() {
        return totalCount;
    }

    @Override
    public int getTotalPage() {
        return (totalCount - 1) / pageSize + 1;
    }

    @Override
    public E get(int index) {
        return list.get(index);
    }

    @Override
    public int size() {
        return list.size();
    }

    @Override
    public String toString() {
        return "PageListImpl{" +
                "totalCount=" + totalCount +
                ", pageIndex=" + pageIndex +
                ", pageSize=" + pageSize +
                '}';
    }
}
