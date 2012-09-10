/**
 *
 * History:
 *   2010-6-30 22:22:12 Created by ZGong
 */
package gz.aws.blog.impl;

import gz.aws.blog.FileDataManager;
import gz.aws.blog.PhotoManager;
import gz.aws.blog.entity.Photo;
import gz.aws.blog.ioc.WebBlogUtil;
import gz.aws.persistence.impl.EntityObjectManagerImpl;
import org.springframework.transaction.annotation.Transactional;

import javax.persistence.Query;
import java.util.List;

/**
 * Created by ZGong.
 *
 * @version 1.0 2010-6-30 22:22:12
 */
@Transactional
public class PhotoManagerImpl
        extends EntityObjectManagerImpl<Photo>
        implements PhotoManager {
    @Override
    public void remove(long id) {
        Photo photo = get(id);
        Long fileId = photo.getFileId();
        FileDataManager fileDataManager = WebBlogUtil.getFileDataManager();
        fileDataManager.remove(fileId);
        fileDataManager.close();
        em.remove(photo);
    }

    public List<Photo> listAllByAlbum(long albumId) {
        Query query = em.createQuery("select p from Photo p where p.album=:album");
        query.setParameter("album", albumId);
        return query.getResultList();
    }
}
