package com.dm.zos.entity;

import java.util.Date;
import javax.persistence.*;

@Table(name = "affirm_order")
public class AffirmOrder {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    /**
     * 回执单号/运单单号
     */
    private String accountid;

    /**
     * 确认类型:delivery发货,sellapply回购
     */
    private String type;

    /**
     * 订单编号
     */
    private String orderno;

    /**
     * 确认编号
     */
    private String affirmno;

    @Column(name = "creat_time")
    private Date creatTime;

    @Column(name = "update_time")
    private Date updateTime;

    /**
     * 收款凭证图片名称
     */
    @Column(name = "voucher_path")
    private String voucherPath;

    /**
     * @return id
     */
    public Integer getId() {
        return id;
    }

    /**
     * @param id
     */
    public void setId(Integer id) {
        this.id = id;
    }

    /**
     * 获取回执单号/运单单号
     *
     * @return accountid - 回执单号/运单单号
     */
    public String getAccountid() {
        return accountid;
    }

    /**
     * 设置回执单号/运单单号
     *
     * @param accountid 回执单号/运单单号
     */
    public void setAccountid(String accountid) {
        this.accountid = accountid == null ? null : accountid.trim();
    }

    /**
     * 获取确认类型:delivery发货,sellapply回购
     *
     * @return type - 确认类型:delivery发货,sellapply回购
     */
    public String getType() {
        return type;
    }

    /**
     * 设置确认类型:delivery发货,sellapply回购
     *
     * @param type 确认类型:delivery发货,sellapply回购
     */
    public void setType(String type) {
        this.type = type == null ? null : type.trim();
    }

    /**
     * 获取订单编号
     *
     * @return orderno - 订单编号
     */
    public String getOrderno() {
        return orderno;
    }

    /**
     * 设置订单编号
     *
     * @param orderno 订单编号
     */
    public void setOrderno(String orderno) {
        this.orderno = orderno == null ? null : orderno.trim();
    }

    /**
     * 获取确认编号
     *
     * @return affirmno - 确认编号
     */
    public String getAffirmno() {
        return affirmno;
    }

    /**
     * 设置确认编号
     *
     * @param affirmno 确认编号
     */
    public void setAffirmno(String affirmno) {
        this.affirmno = affirmno == null ? null : affirmno.trim();
    }

    /**
     * @return creat_time
     */
    public Date getCreatTime() {
        return creatTime;
    }

    /**
     * @param creatTime
     */
    public void setCreatTime(Date creatTime) {
        this.creatTime = creatTime;
    }

    /**
     * @return update_time
     */
    public Date getUpdateTime() {
        return updateTime;
    }

    /**
     * @param updateTime
     */
    public void setUpdateTime(Date updateTime) {
        this.updateTime = updateTime;
    }

    /**
     * 获取收款凭证图片名称
     *
     * @return voucher_path - 收款凭证图片名称
     */
    public String getVoucherPath() {
        return voucherPath;
    }

    /**
     * 设置收款凭证图片名称
     *
     * @param voucherPath 收款凭证图片名称
     */
    public void setVoucherPath(String voucherPath) {
        this.voucherPath = voucherPath == null ? null : voucherPath.trim();
    }
}