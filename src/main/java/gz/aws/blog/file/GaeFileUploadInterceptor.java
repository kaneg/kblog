package gz.aws.blog.file;

import com.opensymphony.xwork2.ActionContext;
import com.opensymphony.xwork2.ActionInvocation;
import com.opensymphony.xwork2.interceptor.AbstractInterceptor;
import org.apache.struts2.ServletActionContext;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.Enumeration;
import java.util.Map;

/**
 * User: Kane Gong
 * Date: Nov 28, 2010
 * Time: 12:54:55 PM
 */
public class GaeFileUploadInterceptor
        extends AbstractInterceptor {
    @Override
    public String intercept(ActionInvocation invocation) throws Exception {
        ActionContext ac = invocation.getInvocationContext();
        HttpServletRequest request = ServletActionContext.getRequest();
        HttpServletResponse response = ServletActionContext.getResponse();
        FileUpload fu = getFileUploader();
        fu.doPost(request, response);
        Map<String, Object> map = ac.getParameters();
        Enumeration attributeNames = request.getAttributeNames();
        while (attributeNames.hasMoreElements()) {
            String o = (String) attributeNames.nextElement();
            map.put(o, request.getAttribute(o));
        }
        // invoke action
        String result = invocation.invoke();

        // cleanup

        return result;
    }

    protected FileUpload getFileUploader() {
        return new FileUpload();
    }
}
