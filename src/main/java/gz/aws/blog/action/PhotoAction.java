package gz.aws.blog.action;

import gz.aws.blog.FileDataManager;
import gz.aws.blog.PhotoManager;
import gz.aws.blog.entity.FileData;
import gz.aws.blog.entity.Photo;
import gz.aws.blog.ioc.WebBlogUtil;
import gz.aws.blog.util.FileUtil;
import gz.aws.blog.util.ImageUtil;
import org.apache.struts2.ServletActionContext;

import java.io.*;

/**
 * User: Kane Gong
 * Date: Nov 27, 2010
 * Time: 4:11:15 PM
 */
public class PhotoAction
        extends AuthedAction {
    FileData photo;
    String photoName;
    String photoDesc;
    long pId;
    InputStream inputStream;

    public long getPId() {
        return pId;
    }

    public void setPId(long pId) {
        this.pId = pId;
    }

    public FileData getPhoto() {
        return photo;
    }

    public void setPhoto(FileData photo) {
        this.photo = photo;
    }

    public String getPhotoName() {
        return photoName;
    }

    public void setPhotoName(String photoName) {
        this.photoName = photoName;
    }

    public String getPhotoDesc() {
        return photoDesc;
    }

    public void setPhotoDesc(String photoDesc) {
        this.photoDesc = photoDesc;
    }

    public InputStream getInputStream() {
        return inputStream;
    }

    public String add() throws Exception {
        FileData fd = photo;
        PhotoManager pm = WebBlogUtil.getPhotoManager();
        Photo photo = new Photo();
        photo.setFileId(fd.getId());
        photo.setName(photoName == null || "".equals(photoName) ? fd.getFileName() : photoName);
        photo.setDesc(photoDesc);
        pm.add(photo);
        pm.close();
        return SUCCESS;
    }

    public String delete() throws Exception {
        PhotoManager photoManager = WebBlogUtil.getPhotoManager();
        photoManager.remove(pId);
        photoManager.close();
        return SUCCESS;
    }

    public String update() throws Exception {
        PhotoManager photoManager = WebBlogUtil.getPhotoManager();
        Photo photo = photoManager.get(pId);
        if (photoName != null) {
            photo.setName(photoName);
        }
        if (photoDesc != null) {
            photo.setDesc(photoDesc);
        }
        photoManager.update(photo);
        photoManager.close();
        return SUCCESS;
    }

    public String resize() throws Exception {
        PhotoManager photoManager = WebBlogUtil.getPhotoManager();
        Photo photo1 = photoManager.get(pId);
        Long fileId = photo1.getFileId();
        photoManager.close();
        FileDataManager fileDataManager = WebBlogUtil.getFileDataManager();
        FileData fileData = fileDataManager.get(fileId);
        byte[] data = fileData.getData();
        byte[] newData = ImageUtil.resize(data, 588, 441);
        fileData.setData(newData);
        fileDataManager.update(fileData);
        fileDataManager.close();
        return SUCCESS;
    }

    public String view() throws Exception {
        inputStream = getPhotoInputStream();
        return SUCCESS;
    }

    private InputStream getPhotoInputStream() {
        PhotoManager photoManager = WebBlogUtil.getPhotoManager();
        Photo photo1 = photoManager.get(pId);
        Long fileId = photo1.getFileId();
        photoManager.close();
        FileDataManager fileDataManager = WebBlogUtil.getFileDataManager();
        FileData fileData = fileDataManager.get(fileId);
        byte[] data = fileData.getData();
        fileDataManager.close();
        byte[] newData = ImageUtil.resize(data, 588, 441);
        return new ByteArrayInputStream(newData);
    }


    public String viewWithCache() throws Exception {
        File imageCacheDir = FileUtil.getImageCacheDirectory();
        if (!imageCacheDir.exists()) {
            return view();
        }
        File photo = new File(imageCacheDir, pId + ".jpg");
        if (!photo.exists()) {
            InputStream is=getPhotoInputStream();
            FileUtil.writeFile(is,photo);
            is.close();
        }
        ServletActionContext.getResponse().sendRedirect("/imageCache/" + photo.getName());
        return SUCCESS;
    }
}
