package com.weibi.wallet.rest.sdk.vo;



import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

//("coin info")
public class CoinConfigVo {

    //("币种名称")
    private String code;

    //("描述")
    private String desc;

    //("币种名称")
    private int blockConfirm;

    //("块时间")
    private int blockTime;

    //("主币种")    
    private String mainCoinType;

    //("是否支持充值")
    private boolean isSupportDeposit;

    //("是否支持提现")
    private boolean isSupportWithdraw;

    //("是否支持交易")
    private boolean isSupportTrade;

    //("是否支持红包")
    private boolean isSupportRedPacket;

    /**
     * 默认都展示
     */
    //("是否展示")
    private boolean shouldDisplay = true;

    //("币种全名称")
    private String coinFullName;

    //("币种展示名称")
    private String showCoinName;

    //("单次最小提现额度")
    private BigDecimal minWithdrawSingle;

    //("单次最大提现额度")
    private BigDecimal maxWithdrawSingle;

    //("一天的最大提现额度")
    private BigDecimal maxWithdrawOneDay;

    //("提现手续费")
    private BigDecimal withdrawFee;

    //("最小成交数量")
    private BigDecimal minimumTradeAmount;

    // 最小充值额度
    //("最小充值额度")
    private BigDecimal minimumDepositAmount;

    //("最小红包数量")
    private BigDecimal minRedPacketAmount;

    //("最大红包数量")
    private BigDecimal maxRedPacketAmount;

    //("红包数量精度")
    private int redPacketAmountPrecision;

    //内部转账手续费
    //("内部转账手续费")
    private BigDecimal internalWithdrawFee;

    /**
     * 币种是否需要 memo
     */
    //("币种是否需要 memo")
    private boolean memoNeeded;

    /**
     * 默认值 是 4
     */
    //("最大精度")
    private int maxPrecision = 4;

    //("主区还是创新区还是 其他区")
    private int areaType;

    //("币种排序序号")
    private int orderNo;

    //("logo")
    private String logo;


    //("是否支持地址正则表达式效验")
    private boolean supportAddrVerify;

    //("地址表达式格式")
    private String addrRegexpExpress;


    //("是否支持Memo正则表达式效验")
    private boolean supportMemoVerify;
    //("memo表达式格式")
    private String memoRegexpExpress;

    /* 区块链浏览器 */
    //("块链hash")
    private String blockChainHashBuilder;
    //("币种名称")
    private String blockChainAddrBuilder;

    //("是否上/下架")
    private boolean isPutOnShelves = true;

    //("社区url")
    private String communityURL;

    //("聊天室id")
    private String groupId;

    //("币种名称")
    private boolean isFrozenTransfer;

    //("充值方式")
    private List<String> chains;

    //("币种来源wallet / cobo")
    private String source;

    //("地址源")
    private String addressSource;

    // 确认数
    //("币种名称")
    private Integer confirmNum;

    //("是否确认")
    private Boolean confirm;

    //("免审核额度")
    private BigDecimal unExamineAmount = BigDecimal.ZERO;

    //("免审核次数")
    private Integer unExamineNum = 0;

    //("是否是代币")
    private boolean serious = false;

    //("代币类型")
    List<String> seriousTypes= new ArrayList<>();


    public boolean isSerious() {
        return serious;
    }

    public void setSerious(boolean serious) {
        this.serious = serious;
    }


    public List<String> getSeriousTypes() {
        return seriousTypes;
    }

    public void setSeriousTypes(List<String> seriousTypes) {
        this.seriousTypes = seriousTypes;
    }


    public Integer getConfirmNum() {
        return confirmNum;
    }

    public void setConfirmNum(Integer confirmNum) {
        this.confirmNum = confirmNum;
    }

    public Boolean getConfirm() {
        return confirm;
    }

    public void setConfirm(Boolean confirm) {
        this.confirm = confirm;
    }

    public BigDecimal getUnExamineAmount() {
        return unExamineAmount;
    }

    public void setUnExamineAmount(BigDecimal unExamineAmount) {
        this.unExamineAmount = unExamineAmount;
    }

    public Integer getUnExamineNum() {
        return unExamineNum;
    }

    public void setUnExamineNum(Integer unExamineNum) {
        this.unExamineNum = unExamineNum;
    }

    public String getAddressSource() {
		return addressSource;
	}

	public void setAddressSource(String addressSource) {
		this.addressSource = addressSource;
	}

	public String getSource() {
        return source;
    }

    public void setSource(String source) {
        this.source = source;
    }

    public List<String> getChains() {
        return chains;
    }

    public void setChains(List<String> chains) {
        this.chains = chains;
    }


    public String getCode() {
        return code;
    }


    public String getDesc() {
        return desc;
    }


    public int getBlockTime() {
        return blockTime;
    }


    public int getBlockConfirm() {
        return blockConfirm;
    }

    public void setCode(String code) {
        this.code = code;
    }


    public boolean isSupportDeposit() {
        return isSupportDeposit;
    }

    public void setSupportDeposit(boolean supportDeposit) {
        isSupportDeposit = supportDeposit;
    }

    public boolean isSupportWithdraw() {
        return isSupportWithdraw;
    }

    public void setSupportWithdraw(boolean supportWithdraw) {
        isSupportWithdraw = supportWithdraw;
    }

    public BigDecimal getMaxWithdrawSingle() {
        return maxWithdrawSingle;
    }

    public void setMaxWithdrawSingle(BigDecimal maxWithdrawSingle) {
        this.maxWithdrawSingle = maxWithdrawSingle;
    }

    public BigDecimal getWithdrawFee() {
        return withdrawFee;
    }

    public void setWithdrawFee(BigDecimal withdrawFee) {
        this.withdrawFee = withdrawFee;
    }

    public void setDesc(String desc) {
        this.desc = desc;
    }

    public void setBlockConfirm(int blockConfirm) {
        this.blockConfirm = blockConfirm;
    }

    public void setBlockTime(int blockTime) {
        this.blockTime = blockTime;
    }

    public BigDecimal getMaxWithdrawOneDay() {
        return maxWithdrawOneDay;
    }

    public void setMaxWithdrawOneDay(BigDecimal maxWithdrawOneDay) {
        this.maxWithdrawOneDay = maxWithdrawOneDay;
    }

    public BigDecimal getMinWithdrawSingle() {
        return minWithdrawSingle;
    }

    public String getCoinFullName() {
        return coinFullName;
    }

    public void setCoinFullName(String coinFullName) {
        this.coinFullName = coinFullName;
    }

    public void setMinWithdrawSingle(BigDecimal minWithdrawSingle) {
        this.minWithdrawSingle = minWithdrawSingle;
    }

    public BigDecimal getMinimumTradeAmount() {
        return minimumTradeAmount;
    }

    public void setMinimumTradeAmount(BigDecimal minimumTradeAmount) {
        this.minimumTradeAmount = minimumTradeAmount;
    }

    public boolean isSupportTrade() {
        return isSupportTrade;
    }

    public void setSupportTrade(boolean supportTrade) {
        isSupportTrade = supportTrade;
    }

    public int getMaxPrecision() {
        return maxPrecision;
    }

    public void setMaxPrecision(int maxPrecision) {
        this.maxPrecision = maxPrecision;
    }

    public boolean getShouldDisplay() {
        return shouldDisplay;
    }

    public void setShouldDisplay(boolean shouldDisplay) {
        this.shouldDisplay = shouldDisplay;
    }

    public int getAreaType() {
        return areaType;
    }

    public void setAreaType(int areaType) {
        this.areaType = areaType;
    }

    public boolean getMemoNeeded() {
        return memoNeeded;
    }

    public void setMemoNeeded(boolean memoNeeded) {
        this.memoNeeded = memoNeeded;
    }

    public int getOrderNo() {
        return orderNo;
    }

    public void setOrderNo(int orderNo) {
        this.orderNo = orderNo;
    }

    public String getLogo() {
        return logo;
    }

    public void setLogo(String logo) {
        this.logo = logo;
    }

    public boolean isSupportAddrVerify() {
        return supportAddrVerify;
    }

    public void setSupportAddrVerify(boolean supportAddrVerify) {
        this.supportAddrVerify = supportAddrVerify;
    }

    public String getAddrRegexpExpress() {
        return addrRegexpExpress;
    }

    public void setAddrRegexpExpress(String addrRegexpExpress) {
        this.addrRegexpExpress = addrRegexpExpress;
    }

    public boolean isSupportMemoVerify() {
        return supportMemoVerify;
    }

    public void setSupportMemoVerify(boolean supportMemoVerify) {
        this.supportMemoVerify = supportMemoVerify;
    }

    public String getMemoRegexpExpress() {
        return memoRegexpExpress;
    }

    public void setMemoRegexpExpress(String memoRegexpExpress) {
        this.memoRegexpExpress = memoRegexpExpress;
    }

    public String getBlockChainHashBuilder() {
        return blockChainHashBuilder;
    }

    public void setBlockChainHashBuilder(String blockChainHashBuilder) {
        this.blockChainHashBuilder = blockChainHashBuilder;
    }

    public String getBlockChainAddrBuilder() {
        return blockChainAddrBuilder;
    }

    public void setBlockChainAddrBuilder(String blockChainAddrBuilder) {
        this.blockChainAddrBuilder = blockChainAddrBuilder;
    }

    public boolean isShouldDisplay() {
        return shouldDisplay;
    }

    public boolean isMemoNeeded() {
        return memoNeeded;
    }

    public boolean isPutOnShelves() {
        return isPutOnShelves;
    }

    public void setPutOnShelves(boolean putOnShelves) {
        isPutOnShelves = putOnShelves;
    }

    public BigDecimal getMinimumDepositAmount() {
        return minimumDepositAmount;
    }

    public void setMinimumDepositAmount(BigDecimal minimumDepositAmount) {
        this.minimumDepositAmount = minimumDepositAmount;
    }

    public BigDecimal getInternalWithdrawFee() {
        return internalWithdrawFee;
    }

    public void setInternalWithdrawFee(BigDecimal internalWithdrawFee) {
        this.internalWithdrawFee = internalWithdrawFee;
    }

    public String getShowCoinName() {
        return showCoinName;
    }

    public void setShowCoinName(String showCoinName) {
        this.showCoinName = showCoinName;
    }

    public boolean isSupportRedPacket() {
        return isSupportRedPacket;
    }

    public void setSupportRedPacket(boolean supportRedPacket) {
        this.isSupportRedPacket = supportRedPacket;
    }

    public BigDecimal getMinRedPacketAmount() {
        return minRedPacketAmount;
    }

    public void setMinRedPacketAmount(BigDecimal minRedPacketAmount) {
        this.minRedPacketAmount = minRedPacketAmount;
    }

    public BigDecimal getMaxRedPacketAmount() {
        return maxRedPacketAmount;
    }

    public void setMaxRedPacketAmount(BigDecimal maxRedPacketAmount) {
        this.maxRedPacketAmount = maxRedPacketAmount;
    }

    public int getRedPacketAmountPrecision() {
        return redPacketAmountPrecision;
    }

    public void setRedPacketAmountPrecision(int redPacketAmountPrecision) {
        this.redPacketAmountPrecision = redPacketAmountPrecision;
    }

    public String getCommunityURL() {
        return communityURL;
    }

    public void setCommunityURL(String communityURL) {
        this.communityURL = communityURL;
    }

    public String getGroupId() {
        return groupId;
    }

    public void setGroupId(String groupId) {
        this.groupId = groupId;
    }

    public boolean isFrozenTransfer() {
        return isFrozenTransfer;
    }

    public void setFrozenTransfer(boolean frozenTransfer) {
        isFrozenTransfer = frozenTransfer;
    }

	public String getMainCoinType() {
		return mainCoinType;
	}

	public void setMainCoinType(String mainCoinType) {
		this.mainCoinType = mainCoinType;
	}


}
