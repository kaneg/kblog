/**
 *
 * History:
 *   2010-7-4 23:41:39 Created by ZGong
 */
package gz.aws.blog.entity;


import javax.persistence.*;
import java.io.Serializable;

/**
 * Created by ZGong.
 *
 * @version 1.0 2010-7-4 23:41:39
 */
@Entity
public class BigBlob
        implements Serializable
{
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;
    @Lob
    @Basic(fetch = FetchType.LAZY)
    byte[] data;

    public long getId()
    {
        return id;
    }

    public void setId(long id)
    {
        this.id = id;
    }

    public byte[] getData()
    {
        return data;
    }

    public void setData(byte[] data)
    {
        this.data = data;
    }
}
