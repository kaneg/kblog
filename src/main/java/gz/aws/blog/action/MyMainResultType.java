package gz.aws.blog.action;

import org.apache.struts2.dispatcher.ServletActionRedirectResult;

/**
 * User: Kane Gong
 * Date: Nov 27, 2010
 * Time: 4:38:13 PM
 */
public class MyMainResultType
        extends ServletActionRedirectResult {
    @Override
    public void setLocation(String location) {
        this.addParameter("timestamp", System.currentTimeMillis());
        super.setLocation(location);
    }
}
