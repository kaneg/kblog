/**
 *
 * History:
 *   2010-8-17 22:32:05 Created by ZGong
 */
package gz.aws.bill.dao;

import gz.aws.bill.BillTypeManager;
import gz.aws.bill.entity.BillType;
import gz.aws.persistence.impl.EntityObjectManagerImpl;

import javax.persistence.Query;
import java.util.List;

/**
 * Created by ZGong.
 *
 * @version 1.0 2010-8-17 22:32:05
 */
public class BillTypeManagerImpl
        extends EntityObjectManagerImpl<BillType>
        implements BillTypeManager {
    @Override
    public BillType get(long id) {
        if (id == 0) {
            return null;
        }
        return super.get(id);

    }

    public List<BillType> listAll(boolean isIn) {
        String sql = "select bt from BillType bt where bt.isIn=:isIn";
        Query query = em.createQuery(sql);
        query.setParameter("isIn", isIn);
        return query.getResultList();
    }
}