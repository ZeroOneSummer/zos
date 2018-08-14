package com.dm.zos.entity;

import java.math.BigDecimal;
import java.util.Date;
import javax.persistence.*;

@Table(name = "b_bill")
public class BBill {
    /**
     * 主键id
     */
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    /**
     * 用户资金账户ID
     */
    @Column(name = "acc_id")
    private Integer accId;

    /**
     * 用户创建时间
     */
    @Column(name = "create_time")
    private Date createTime;

    /**
     * 更新时间
     */
    @Column(name = "update_time")
    private Date updateTime;

    /**
     * 账单关闭时间
     */
    @Column(name = "invalid_time")
    private Date invalidTime;

    /**
     * 本期开始日期
     */
    @Column(name = "begin_date")
    private Date beginDate;

    /**
     * 本期结束日期(账单结算日期)
     */
    @Column(name = "end_date")
    private Date endDate;

    /**
     * 本月即将过期金豆
     */
    @Column(name = "valalid_bounty")
    private BigDecimal valalidBounty;

    /**
     * 当前剩余金豆总额
     */
    @Column(name = "surplus_bounty")
    private BigDecimal surplusBounty;

    /**
     * 本期收入金豆总额
     */
    @Column(name = "bounty_in")
    private BigDecimal bountyIn;

    /**
     * 本期支出金豆总额
     */
    @Column(name = "bounty_pay")
    private BigDecimal bountyPay;

    /**
     * 结算次数
     */
    @Column(name = "settle_accounts")
    private Integer settleAccounts;

    /**
     * 账单是否有效
     */
    @Column(name = "isValid")
    private Integer isvalid;

    /**
     * 获取主键id
     *
     * @return id - 主键id
     */
    public Long getId() {
        return id;
    }

    /**
     * 设置主键id
     *
     * @param id 主键id
     */
    public void setId(Long id) {
        this.id = id;
    }

    /**
     * 获取用户资金账户ID
     *
     * @return acc_id - 用户资金账户ID
     */
    public Integer getAccId() {
        return accId;
    }

    /**
     * 设置用户资金账户ID
     *
     * @param accId 用户资金账户ID
     */
    public void setAccId(Integer accId) {
        this.accId = accId;
    }

    /**
     * 获取用户创建时间
     *
     * @return create_time - 用户创建时间
     */
    public Date getCreateTime() {
        return createTime;
    }

    /**
     * 设置用户创建时间
     *
     * @param createTime 用户创建时间
     */
    public void setCreateTime(Date createTime) {
        this.createTime = createTime;
    }

    /**
     * 获取更新时间
     *
     * @return update_time - 更新时间
     */
    public Date getUpdateTime() {
        return updateTime;
    }

    /**
     * 设置更新时间
     *
     * @param updateTime 更新时间
     */
    public void setUpdateTime(Date updateTime) {
        this.updateTime = updateTime;
    }

    /**
     * 获取账单关闭时间
     *
     * @return invalid_time - 账单关闭时间
     */
    public Date getInvalidTime() {
        return invalidTime;
    }

    /**
     * 设置账单关闭时间
     *
     * @param invalidTime 账单关闭时间
     */
    public void setInvalidTime(Date invalidTime) {
        this.invalidTime = invalidTime;
    }

    /**
     * 获取本期开始日期
     *
     * @return begin_date - 本期开始日期
     */
    public Date getBeginDate() {
        return beginDate;
    }

    /**
     * 设置本期开始日期
     *
     * @param beginDate 本期开始日期
     */
    public void setBeginDate(Date beginDate) {
        this.beginDate = beginDate;
    }

    /**
     * 获取本期结束日期(账单结算日期)
     *
     * @return end_date - 本期结束日期(账单结算日期)
     */
    public Date getEndDate() {
        return endDate;
    }

    /**
     * 设置本期结束日期(账单结算日期)
     *
     * @param endDate 本期结束日期(账单结算日期)
     */
    public void setEndDate(Date endDate) {
        this.endDate = endDate;
    }

    /**
     * 获取本月即将过期金豆
     *
     * @return valalid_bounty - 本月即将过期金豆
     */
    public BigDecimal getValalidBounty() {
        return valalidBounty;
    }

    /**
     * 设置本月即将过期金豆
     *
     * @param valalidBounty 本月即将过期金豆
     */
    public void setValalidBounty(BigDecimal valalidBounty) {
        this.valalidBounty = valalidBounty;
    }

    /**
     * 获取当前剩余金豆总额
     *
     * @return surplus_bounty - 当前剩余金豆总额
     */
    public BigDecimal getSurplusBounty() {
        return surplusBounty;
    }

    /**
     * 设置当前剩余金豆总额
     *
     * @param surplusBounty 当前剩余金豆总额
     */
    public void setSurplusBounty(BigDecimal surplusBounty) {
        this.surplusBounty = surplusBounty;
    }

    /**
     * 获取本期收入金豆总额
     *
     * @return bounty_in - 本期收入金豆总额
     */
    public BigDecimal getBountyIn() {
        return bountyIn;
    }

    /**
     * 设置本期收入金豆总额
     *
     * @param bountyIn 本期收入金豆总额
     */
    public void setBountyIn(BigDecimal bountyIn) {
        this.bountyIn = bountyIn;
    }

    /**
     * 获取本期支出金豆总额
     *
     * @return bounty_pay - 本期支出金豆总额
     */
    public BigDecimal getBountyPay() {
        return bountyPay;
    }

    /**
     * 设置本期支出金豆总额
     *
     * @param bountyPay 本期支出金豆总额
     */
    public void setBountyPay(BigDecimal bountyPay) {
        this.bountyPay = bountyPay;
    }

    /**
     * 获取结算次数
     *
     * @return settle_accounts - 结算次数
     */
    public Integer getSettleAccounts() {
        return settleAccounts;
    }

    /**
     * 设置结算次数
     *
     * @param settleAccounts 结算次数
     */
    public void setSettleAccounts(Integer settleAccounts) {
        this.settleAccounts = settleAccounts;
    }

    /**
     * 获取账单是否有效
     *
     * @return isValid - 账单是否有效
     */
    public Integer getIsvalid() {
        return isvalid;
    }

    /**
     * 设置账单是否有效
     *
     * @param isvalid 账单是否有效
     */
    public void setIsvalid(Integer isvalid) {
        this.isvalid = isvalid;
    }
}