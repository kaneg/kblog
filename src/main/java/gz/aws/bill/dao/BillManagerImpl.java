/**
 *
 * History:
 *   2010-8-17 22:32:05 Created by ZGong
 */
package gz.aws.bill.dao;

import gz.aws.bill.BillManager;
import gz.aws.bill.entity.Bill;
import gz.aws.persistence.impl.EntityObjectManagerImpl;

import javax.persistence.Query;
import java.util.Date;
import java.util.List;

/**
 * Created by ZGong.
 *
 * @version 1.0 2010-8-17 22:32:05
 */
public class BillManagerImpl
        extends EntityObjectManagerImpl<Bill>
        implements BillManager {
    @Override
    public void add(Bill bill) {
        bill.setCreateDate(new Date());
        super.add(bill);
    }

    public List<Bill> listAllByInOrOut(boolean isIn) {
        String sql = "select b from Bill b where b.isIn=:isIn";
        Query query = em.createQuery(sql);
        query.setParameter("isIn", isIn);
        return query.getResultList();
    }
}
