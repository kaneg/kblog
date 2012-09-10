/**
 *
 * History:
 *   2010-6-30 22:20:51 Created by ZGong
 */
package gz.aws.blog;

import gz.aws.blog.entity.Photo;
import gz.aws.persistence.EntityObjectManager;

import java.util.List;

/**
 * Created by ZGong.
 *
 * @version 1.0 2010-6-30 22:20:51
 */
public interface PhotoManager
        extends EntityObjectManager<Photo> {
    public List<Photo> listAllByAlbum(long albumId);
}
