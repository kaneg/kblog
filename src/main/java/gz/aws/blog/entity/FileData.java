/**
 *
 * History:
 *   2010-6-28 0:09:03 Created by ZGong
 */
package gz.aws.blog.entity;

import javax.persistence.*;
import java.io.Serializable;
import java.util.Date;


/**
 * Created by ZGong.
 *
 * @version 1.0 2010-6-28 0:09:03
 */
@Entity
public class FileData
        implements Serializable {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    Long id;
    @OneToOne(cascade = CascadeType.ALL)
    BigBlob bigBlob = new BigBlob();
    String contentType;
    Date createDate;
    String fileName;
    long size;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public byte[] getData() {
        return bigBlob.getData();
    }

    public void setData(byte[] data) {
        this.bigBlob.setData(data);
        setSize(data.length);
    }

    public String getContentType() {
        return contentType;
    }

    public void setContentType(String contentType) {
        this.contentType = contentType;
    }

    public Date getCreateDate() {
        return createDate;
    }

    public void setCreateDate(Date createDate) {
        this.createDate = createDate;
    }

    public String getFileName() {
        return fileName;
    }

    public void setFileName(String fileName) {
        this.fileName = fileName;
    }

    public long getSize() {
        return size;
    }

    public void setSize(long size) {
        this.size = size;
    }

    public BigBlob getBigBlob() {
        return bigBlob;
    }

    public void setBigBlob(BigBlob bigBlob) {
        this.bigBlob = bigBlob;
    }
}
