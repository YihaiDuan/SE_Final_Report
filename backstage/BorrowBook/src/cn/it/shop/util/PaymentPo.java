package cn.it.shop.util;


/** 
* @author 
* @version ����ʱ�䣺2017��1��21�� ����4:20:52 
* ��˵�� 
*/
public class PaymentPo {
    private String appid;//С����ID
    private String mch_id;//�̻���
    private String device_info;//�豸��
    private String nonce_str;//����ַ���
    private String sign;//ǩ��
    private String body;//��Ʒ����  
    private String detail;//��Ʒ����    
    private String attach;//��������
    private String out_trade_no;//�̻�������
    private String fee_type;//��������
    private String spbill_create_ip;//�ն�IP
    private String time_start;//������ʼʱ��
    private String time_expire;//���׽���ʱ�� 
    private String goods_tag;//��Ʒ���
    private String total_fee;//�ܽ��
    private String notify_url;//֪ͨ��ַ    
    private String trade_type;//��������    
    private String limit_pay;//ָ��֧����ʽ
    private String openid;//�û���ʶ
    public String getAppid() {
        return appid;
    }
    public void setAppid(String appid) {
        this.appid = appid;
    }
    public String getMch_id() {
        return mch_id;
    }
    public void setMch_id(String mch_id) {
        this.mch_id = mch_id;
    }
    public String getNonce_str() {
        return nonce_str;
    }
    public void setNonce_str(String nonce_str) {
        this.nonce_str = nonce_str;
    }
    public String getSign() {
        return sign;
    }
    public void setSign(String sign) {
        this.sign = sign;
    }
    public String getBody() {
        return body;
    }
    public void setBody(String body) {
        this.body = body;
    }
    public String getOut_trade_no() {
        return out_trade_no;
    }
    public void setOut_trade_no(String out_trade_no) {
        this.out_trade_no = out_trade_no;
    }
    public String getTotal_fee() {
        return total_fee;
    }
    public void setTotal_fee(String total_fee) {
        this.total_fee = total_fee;
    }
    public String getNotify_url() {
        return notify_url;
    }
    public void setNotify_url(String notify_url) {
        this.notify_url = notify_url;
    }
    public String getTrade_type() {
        return trade_type;
    }
    public void setTrade_type(String trade_type) {
        this.trade_type = trade_type;
    }
    public String getOpenid() {
        return openid;
    }
    public void setOpenid(String openid) {
        this.openid = openid;
    }
    public String getSpbill_create_ip() {
        return spbill_create_ip;
    }
    public void setSpbill_create_ip(String spbill_create_ip) {
        this.spbill_create_ip = spbill_create_ip;
    }
    public String getDevice_info() {
        return device_info;
    }
    public void setDevice_info(String device_info) {
        this.device_info = device_info;
    }
    public String getDetail() {
        return detail;
    }
    public void setDetail(String detail) {
        this.detail = detail;
    }
    public String getAttach() {
        return attach;
    }
    public void setAttach(String attach) {
        this.attach = attach;
    }
    public String getFee_type() {
        return fee_type;
    }
    public void setFee_type(String fee_type) {
        this.fee_type = fee_type;
    }
    public String getTime_start() {
        return time_start;
    }
    public void setTime_start(String time_start) {
        this.time_start = time_start;
    }
    public String getTime_expire() {
        return time_expire;
    }
    public void setTime_expire(String time_expire) {
        this.time_expire = time_expire;
    }
    public String getGoods_tag() {
        return goods_tag;
    }
    public void setGoods_tag(String goods_tag) {
        this.goods_tag = goods_tag;
    }
    public String getLimit_pay() {
        return limit_pay;
    }
    public void setLimit_pay(String limit_pay) {
        this.limit_pay = limit_pay;
    }
}