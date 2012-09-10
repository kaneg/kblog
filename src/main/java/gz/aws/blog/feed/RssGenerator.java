/**
 *
 * History:
 *   2010-7-11 18:22:59 Created by ZGong
 */
package gz.aws.blog.feed;

import com.sun.syndication.feed.synd.*;
import gz.aws.blog.BlogManager;
import gz.aws.blog.entity.Blog;
import gz.aws.blog.ioc.WebBlogUtil;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by ZGong.
 *
 * @version 1.0 2010-7-11 18:22:59
 */
public class RssGenerator {
    String hostName;

    public RssGenerator(String hostName) {
        this.hostName = hostName;
    }

    public String getHostName() {
        return hostName;
    }

    public void setHostName(String hostName) {
        this.hostName = hostName;
    }

    public SyndFeed getSyndFeed() {
        String feedType = "rss_2.0";
        SyndFeed feed = new SyndFeedImpl();
        feed.setFeedType(feedType);

        feed.setTitle("KBlog");
        feed.setLink("http://" + hostName);
        feed.setDescription("This is kblog");

        List<SyndEntry> entries = new ArrayList<SyndEntry>();
        SyndEntry entry;
        SyndContent description;
        BlogManager bm = WebBlogUtil.getBlogManager();
        List<Blog> list = bm.getAll();
        for (Blog blog : list) {
            String link = "http://" + hostName + "/blog/main.action?pageType=view&bId=" + blog.getId();
            entry = new SyndEntryImpl();
            entry.setTitle(blog.getSubject());
            entry.setLink(link);
            entry.setPublishedDate(blog.getModifyDate());
            description = new SyndContentImpl();
            description.setType("text/html");
            description.setValue(blog.getContent());
            entry.setDescription(description);
            entries.add(entry);
        }

        feed.setEntries(entries);

        return feed;
    }
}
