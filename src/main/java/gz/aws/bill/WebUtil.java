/**
 *
 * History:
 *   2010-7-6 21:49:35 Created by ZGong
 */
package gz.aws.bill;

import gz.aws.ioc.IocUtil;

/**
 * Created by ZGong.
 *
 * @version 1.0 2010-7-6 21:49:35
 */
public class WebUtil {
    public static BillTypeManager getBillTypeManager() {
        return (BillTypeManager) IocUtil.get("BillTypeManager");
    }

    public static BillManager getBillManager() {
        return (BillManager) IocUtil.get("BillManager");
    }
}