/**
 *
 * History:
 *   11-4-17 下午7:41 Created by ZGong
 */
package gz.aws.blog.entity;

import javax.persistence.Column;
import javax.persistence.Embeddable;

/**
 * Created by ZGong.
 *
 * @version 1.0 11-4-17 下午7:41
 */
@Embeddable
public class Text
{
    @Column(length = 64*1024)
    private String content;

    public Text(String content)
    {
        this.content = content;
    }

    public Text()
    {
    }

    public String getValue()
    {
        return content;
    }

    public void setValue(String content)
    {
        this.content = content;
    }
}
