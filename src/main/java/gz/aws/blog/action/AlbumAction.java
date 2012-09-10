/**
 *
 * History:
 *   11-9-20 上午12:18 Created by ZGong
 */
package gz.aws.blog.action;

import gz.aws.blog.AlbumManager;
import gz.aws.blog.entity.Album;
import gz.aws.blog.ioc.WebBlogUtil;

/**
 * Created by ZGong.
 *
 * @version 1.0 11-9-20 上午12:18
 */
public class AlbumAction extends AuthedAction {
    private String name;
    private String description;
    private long aId;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public long getaId() {
        return aId;
    }

    public void setaId(long aId) {
        this.aId = aId;
    }

    public String add() {
        Album album = new Album();
        album.setName(name);
        album.setDescription(description);
        final AlbumManager albumManager = WebBlogUtil.getAlbumManager();
        albumManager.add(album);
        albumManager.close();
        return SUCCESS;
    }

    public String delete() throws Exception {
        AlbumManager albumManager = WebBlogUtil.getAlbumManager();
        albumManager.remove(aId);
        albumManager.close();
        return SUCCESS;
    }

    public String update() throws Exception {
        AlbumManager albumManager = WebBlogUtil.getAlbumManager();
        Album album = albumManager.get(aId);
        if (name != null) {
            album.setName(name);
        }
        if (description != null) {
            album.setDescription(description);
        }
        albumManager.update(album);
        albumManager.close();
        return SUCCESS;
    }
}
