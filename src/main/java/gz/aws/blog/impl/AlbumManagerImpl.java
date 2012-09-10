/**
 *
 * History:
 *   11-9-20 上午12:21 Created by ZGong
 */
package gz.aws.blog.impl;

import gz.aws.blog.AlbumManager;
import gz.aws.blog.entity.Album;
import gz.aws.persistence.impl.EntityObjectManagerImpl;

import java.util.Date;

/**
 * Created by ZGong.
 *
 * @version 1.0 11-9-20 上午12:21
 */
public class AlbumManagerImpl extends EntityObjectManagerImpl<Album> implements AlbumManager {
    @Override
    public void add(Album album) {
        album.setCreateDate(new Date());
        super.add(album);
    }
}
