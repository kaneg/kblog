package gz.aws.blog.action;

import com.opensymphony.xwork2.ActionSupport;
import com.sun.syndication.feed.synd.SyndFeed;
import gz.aws.blog.feed.RssGenerator;
import org.apache.struts2.ServletActionContext;

import javax.servlet.http.HttpServletRequest;

/**
 * User: Kane Gong
 * Date: Nov 27, 2010
 * Time: 3:38:54 PM
 */
public class CreateFeedAction
        extends ActionSupport {
    SyndFeed syndFeed;
    private String feedType;

    public SyndFeed getSyndFeed() {
        return syndFeed;
    }

    public void setSyndFeed(SyndFeed syndFeed) {
        this.syndFeed = syndFeed;
    }

    public String getFeedType() {
        return feedType;
    }

    public void setFeedType(String feedType) {
        this.feedType = feedType;
    }

    @Override
    public String execute() throws Exception {
        HttpServletRequest request = ServletActionContext.getRequest();
        String serverName = request.getServerName();
        int port = request.getServerPort();
        RssGenerator rg = new RssGenerator(serverName + ":" + port);
        syndFeed = rg.getSyndFeed();
        if (feedType != null) {
            syndFeed.setFeedType(feedType);
        }
        return SUCCESS;
    }
}
