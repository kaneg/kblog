/**
 *
 * History:
 *   2010-6-28 0:15:12 Created by ZGong
 */
package gz.aws.blog.impl;

import gz.aws.blog.FileDataManager;
import gz.aws.blog.entity.FileData;
import gz.aws.persistence.impl.EntityObjectManagerImpl;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import java.util.Date;

/**
 * Created by ZGong.
 *
 * @version 1.0 2010-6-28 0:15:12
 */
@Transactional(propagation = Propagation.REQUIRES_NEW)
public class FileDataManagerImpl
        extends EntityObjectManagerImpl<FileData>
        implements FileDataManager {
    protected FileDataManagerImpl() {
        super(FileData.class);
    }

    @Override
    public void add(FileData fileData) {
        fileData.setCreateDate(new Date());
        super.add(fileData);
    }
}
