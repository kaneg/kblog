package gz.aws.bill.action;

import com.opensymphony.xwork2.ActionSupport;
import gz.aws.bill.BillManager;
import gz.aws.bill.BillTypeManager;
import gz.aws.bill.WebUtil;
import gz.aws.bill.entity.Bill;
import gz.aws.bill.entity.BillType;
import org.apache.struts2.ServletActionContext;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;

/**
 * User: Kane Gong
 * Date: Nov 28, 2010
 * Time: 10:05:49 PM
 */
public class BillAction
        extends ActionSupport {
    float amount;
    String issueDate;
    String desc;
    boolean isIn;
    String location;
    String billType;
    long bId;
    long btId;

    public long getBId() {
        return bId;
    }

    public void setBId(long bId) {
        this.bId = bId;
    }

    public long getBtId() {
        return btId;
    }

    public void setBtId(long btId) {
        this.btId = btId;
    }

    public float getAmount() {
        return amount;
    }

    public void setAmount(float amount) {
        this.amount = amount;
    }

    public String getIssueDate() {
        return issueDate;
    }

    public void setIssueDate(String issueDate) {
        this.issueDate = issueDate;
    }

    public String getDesc() {
        return desc;
    }

    public void setDesc(String desc) {
        this.desc = desc;
    }

    public boolean getIsIn() {
        return isIn;
    }

    public void setIsIn(boolean in) {
        isIn = in;
    }

    public String getLocation() {
        return location;
    }

    public void setLocation(String location) {
        this.location = location;
    }

    public String getBillType() {
        return billType;
    }

    public void setBillType(String billType) {
        this.billType = billType;
    }

    public String addBill() throws Exception {
        BillManager billManager = WebUtil.getBillManager();

        Bill bill = new Bill();
        bill.setAmount(amount);
        DateFormat df = new SimpleDateFormat("yyyy-MM-dd");
        Date date = df.parse(issueDate);
        bill.setIssueDate(date);
        bill.setDesc(desc);
        bill.setIn(isIn);
        bill.setLocation(location);
        if (billType != null) {
            billType = billType.trim();
            if (!"".equals(billType)) {
                bill.setBillTypeId(Long.parseLong(billType));
            }
        } else {
            bill.setBillTypeId(0);
        }
        billManager.add(bill);
        billManager.close();
        return SUCCESS;
    }

    public String addBillType() throws Exception {
        BillTypeManager billTypeManager = WebUtil.getBillTypeManager();
        if (billType != null) {
            BillType type = new BillType(billType);
            type.setIn(isIn);
            billTypeManager.add(type);
            billTypeManager.close();
            ServletActionContext.getResponse().getWriter().println(type.getId());
        }
        return SUCCESS;
    }

    public String deleteBill() {
        BillManager billManager = WebUtil.getBillManager();
        if (bId > 0) {
            billManager.remove(bId);
        }
        billManager.close();
        return SUCCESS;
    }

    public String deleteBillType() {
        BillTypeManager billTypeManager = WebUtil.getBillTypeManager();
        if (btId > 0) {
            billTypeManager.remove(btId);
        }
        billTypeManager.close();
        return SUCCESS;
    }

    public String editBill() throws Exception {
        BillManager billManager = WebUtil.getBillManager();

        Bill bill = billManager.get(bId);
        isIn = bill.isIn();
        bill.setAmount(amount);
        DateFormat df = new SimpleDateFormat("yyyy-MM-dd");
        Date date = df.parse(issueDate);
        bill.setIssueDate(date);
        bill.setDesc(desc);
        bill.setLocation(location);
        if (billType != null) {
            billType = billType.trim();
            if (!"".equals(billType)) {
                bill.setBillTypeId(Long.parseLong(billType));
            }
        } else {
            bill.setBillTypeId(0);
        }
        billManager.update(bill);
        billManager.close();
        return SUCCESS;
    }

    public String editBillType() {
        BillTypeManager billTypeManager = WebUtil.getBillTypeManager();
        if (btId > 0) {
            BillType billTypeObject = new BillType(billType);
            billTypeObject.setId(btId);
            billTypeObject.setIn(isIn);
            billTypeManager.update(billTypeObject);
        }
        billTypeManager.close();
        return SUCCESS;
    }
}
