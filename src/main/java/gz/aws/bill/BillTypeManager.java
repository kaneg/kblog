/**
 *
 * History:
 *   2010-8-17 22:24:08 Created by ZGong
 */
package gz.aws.bill;

import gz.aws.bill.entity.BillType;
import gz.aws.persistence.EntityObjectManager;

import java.util.List;

/**
 * Created by ZGong.
 *
 * @version 1.0 2010-8-17 22:24:08
 */
public interface BillTypeManager
        extends EntityObjectManager<BillType> {
    public List<BillType> listAll(boolean isIn);
}