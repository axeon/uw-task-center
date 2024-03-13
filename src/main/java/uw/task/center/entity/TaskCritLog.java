package uw.task.center.entity;

import java.io.Serializable;
import java.util.HashSet;
import java.util.Set;

import io.swagger.v3.oas.annotations.media.Schema;
import uw.dao.DataEntity;
import uw.dao.annotation.ColumnMeta;
import uw.dao.annotation.TableMeta;

/**
 * TaskCritLog实体类
 * task关键日志
 *
 * @author axeon
 */
@TableMeta(tableName="task_crit_log",tableType="table")
@Schema(title = "task关键日志", description = "task关键日志")
public class TaskCritLog implements DataEntity,Serializable{


	/**
	 * 主键ID
	 */
	@ColumnMeta(columnName="id", dataType="long", dataSize=19, nullable=false, primaryKey=true)
	@Schema(title = "主键ID", description = "主键ID")
	private long id;

	/**
	 * 运营商编号
	 */
	@ColumnMeta(columnName="saas_id", dataType="long", dataSize=19, nullable=false)
	@Schema(title = "运营商编号", description = "运营商编号")
	private long saasId;

	/**
	 * 商户id
	 */
	@ColumnMeta(columnName="mch_id", dataType="long", dataSize=19, nullable=true)
	@Schema(title = "商户id", description = "商户id")
	private long mchId;

	/**
	 * 操作人id
	 */
	@ColumnMeta(columnName="user_id", dataType="long", dataSize=19, nullable=false)
	@Schema(title = "操作人id", description = "操作人id")
	private long userId;

	/**
	 * 用户类型
	 */
	@ColumnMeta(columnName="user_type", dataType="int", dataSize=10, nullable=true)
	@Schema(title = "用户类型", description = "用户类型")
	private int userType;

	/**
	 * 用户的组ID
	 */
	@ColumnMeta(columnName="group_id", dataType="long", dataSize=19, nullable=true)
	@Schema(title = "用户的组ID", description = "用户的组ID")
	private long groupId;

	/**
	 * 操作人名称
	 */
	@ColumnMeta(columnName="user_name", dataType="String", dataSize=100, nullable=true)
	@Schema(title = "操作人名称", description = "操作人名称")
	private String userName;

	/**
	 * 操作人用户昵称
	 */
	@ColumnMeta(columnName="nick_name", dataType="String", dataSize=100, nullable=true)
	@Schema(title = "操作人用户昵称", description = "操作人用户昵称")
	private String nickName;

	/**
	 * 操作人真实名称
	 */
	@ColumnMeta(columnName="real_name", dataType="String", dataSize=100, nullable=true)
	@Schema(title = "操作人真实名称", description = "操作人真实名称")
	private String realName;

	/**
	 * 操作对象id
	 */
	@ColumnMeta(columnName="object_id", dataType="String", dataSize=100, nullable=true)
	@Schema(title = "操作对象id", description = "操作对象id")
	private String objectId;

	/**
	 * 操作对象类型
	 */
	@ColumnMeta(columnName="object_type", dataType="String", dataSize=100, nullable=true)
	@Schema(title = "操作对象类型", description = "操作对象类型")
	private String objectType;

	/**
	 * 请求uri
	 */
	@ColumnMeta(columnName="uri", dataType="String", dataSize=500, nullable=true)
	@Schema(title = "请求uri", description = "请求uri")
	private String uri;

	/**
	 * 方法操作描述
	 */
	@ColumnMeta(columnName="info", dataType="String", dataSize=1000, nullable=true)
	@Schema(title = "方法操作描述", description = "方法操作描述")
	private String info;

	/**
	 * 日志内容
	 */
	@ColumnMeta(columnName="log", dataType="String", dataSize=65535, nullable=true)
	@Schema(title = "日志内容", description = "日志内容")
	private String log;

	/**
	 * 请求参数
	 */
	@ColumnMeta(columnName="request_body", dataType="String", dataSize=65535, nullable=true)
	@Schema(title = "请求参数", description = "请求参数")
	private String requestBody;

	/**
	 * 响应日志
	 */
	@ColumnMeta(columnName="response_body", dataType="String", dataSize=65535, nullable=true)
	@Schema(title = "响应日志", description = "响应日志")
	private String responseBody;

	/**
	 * 请求毫秒数
	 */
	@ColumnMeta(columnName="response_millis", dataType="long", dataSize=19, nullable=true)
	@Schema(title = "请求毫秒数", description = "请求毫秒数")
	private long responseMillis;

	/**
	 * 异常信息
	 */
	@ColumnMeta(columnName="exception", dataType="String", dataSize=65535, nullable=true)
	@Schema(title = "异常信息", description = "异常信息")
	private String exception;

	/**
	 * 响应状态码
	 */
	@ColumnMeta(columnName="status_code", dataType="int", dataSize=10, nullable=true)
	@Schema(title = "响应状态码", description = "响应状态码")
	private int statusCode;

	/**
	 * 应用信息
	 */
	@ColumnMeta(columnName="app_info", dataType="String", dataSize=100, nullable=true)
	@Schema(title = "应用信息", description = "应用信息")
	private String appInfo;

	/**
	 * 应用主机
	 */
	@ColumnMeta(columnName="app_host", dataType="String", dataSize=100, nullable=true)
	@Schema(title = "应用主机", description = "应用主机")
	private String appHost;

	/**
	 * 操作人ip
	 */
	@ColumnMeta(columnName="user_ip", dataType="String", dataSize=50, nullable=true)
	@Schema(title = "操作人ip", description = "操作人ip")
	private String userIp;

	/**
	 * 创建时间
	 */
	@ColumnMeta(columnName="request_date", dataType="java.util.Date", dataSize=23, nullable=false)
	@Schema(title = "创建时间", description = "创建时间")
	private java.util.Date requestDate;

	/**
	 * 轻量级状态下更新列表list.
	 */
	public transient Set<String> UPDATED_COLUMN = null;

    /**
	 * 更新的信息.
	 */
    private transient StringBuilder UPDATED_INFO = null;

	/**
	 * 获得更改的字段列表.
	 */
    @Override
	public Set<String> GET_UPDATED_COLUMN() {
        return UPDATED_COLUMN;
	}

	/**
	 * 得到_INFO.
	 */
	@Override
	public String GET_UPDATED_INFO() {
        if (this.UPDATED_INFO == null) {
			return null;
		} else {
            return this.UPDATED_INFO.toString();
		}
	}

    /**
     * 清理_INFO和UPDATED_COLUMN信息.
     */
    public void CLEAR_UPDATED_INFO() {
        UPDATED_COLUMN = null;
        UPDATED_INFO = null;
	}

	/**
	 * 初始化set相关的信息.
	 */
	private void _INIT_UPDATE_INFO() {
		this.UPDATED_COLUMN = new HashSet<String>();
		this.UPDATED_INFO = new StringBuilder("表task_crit_log主键\"" + 
		this.id+ "\"更新为:\r\n");
	}


	/**
	 * 获得主键ID。
	 */
	public long getId(){
		return this.id;
	}

	/**
	 * 获得运营商编号。
	 */
	public long getSaasId(){
		return this.saasId;
	}

	/**
	 * 获得商户id。
	 */
	public long getMchId(){
		return this.mchId;
	}

	/**
	 * 获得操作人id。
	 */
	public long getUserId(){
		return this.userId;
	}

	/**
	 * 获得用户类型。
	 */
	public int getUserType(){
		return this.userType;
	}

	/**
	 * 获得用户的组ID。
	 */
	public long getGroupId(){
		return this.groupId;
	}

	/**
	 * 获得操作人名称。
	 */
	public String getUserName(){
		return this.userName;
	}

	/**
	 * 获得操作人用户昵称。
	 */
	public String getNickName(){
		return this.nickName;
	}

	/**
	 * 获得操作人真实名称。
	 */
	public String getRealName(){
		return this.realName;
	}

	/**
	 * 获得操作对象id。
	 */
	public String getObjectId(){
		return this.objectId;
	}

	/**
	 * 获得操作对象类型。
	 */
	public String getObjectType(){
		return this.objectType;
	}

	/**
	 * 获得请求uri。
	 */
	public String getUri(){
		return this.uri;
	}

	/**
	 * 获得方法操作描述。
	 */
	public String getInfo(){
		return this.info;
	}

	/**
	 * 获得日志内容。
	 */
	public String getLog(){
		return this.log;
	}

	/**
	 * 获得请求参数。
	 */
	public String getRequestBody(){
		return this.requestBody;
	}

	/**
	 * 获得响应日志。
	 */
	public String getResponseBody(){
		return this.responseBody;
	}

	/**
	 * 获得请求毫秒数。
	 */
	public long getResponseMillis(){
		return this.responseMillis;
	}

	/**
	 * 获得异常信息。
	 */
	public String getException(){
		return this.exception;
	}

	/**
	 * 获得响应状态码。
	 */
	public int getStatusCode(){
		return this.statusCode;
	}

	/**
	 * 获得应用信息。
	 */
	public String getAppInfo(){
		return this.appInfo;
	}

	/**
	 * 获得应用主机。
	 */
	public String getAppHost(){
		return this.appHost;
	}

	/**
	 * 获得操作人ip。
	 */
	public String getUserIp(){
		return this.userIp;
	}

	/**
	 * 获得创建时间。
	 */
	public java.util.Date getRequestDate(){
		return this.requestDate;
	}


	/**
	 * 设置主键ID。
	 */
	public void setId(long id){
		if ((!String.valueOf(this.id).equals(String.valueOf(id)))) {
			if (this.UPDATED_COLUMN == null) {
				_INIT_UPDATE_INFO();
			}
			this.UPDATED_COLUMN.add("id");
			this.UPDATED_INFO.append("id:\"" + this.id+ "\"=>\""
                + id + "\"\r\n");
			this.id = id;
		}
	}

	/**
	 * 设置运营商编号。
	 */
	public void setSaasId(long saasId){
		if ((!String.valueOf(this.saasId).equals(String.valueOf(saasId)))) {
			if (this.UPDATED_COLUMN == null) {
				_INIT_UPDATE_INFO();
			}
			this.UPDATED_COLUMN.add("saas_id");
			this.UPDATED_INFO.append("saas_id:\"" + this.saasId+ "\"=>\""
                + saasId + "\"\r\n");
			this.saasId = saasId;
		}
	}

	/**
	 * 设置商户id。
	 */
	public void setMchId(long mchId){
		if ((!String.valueOf(this.mchId).equals(String.valueOf(mchId)))) {
			if (this.UPDATED_COLUMN == null) {
				_INIT_UPDATE_INFO();
			}
			this.UPDATED_COLUMN.add("mch_id");
			this.UPDATED_INFO.append("mch_id:\"" + this.mchId+ "\"=>\""
                + mchId + "\"\r\n");
			this.mchId = mchId;
		}
	}

	/**
	 * 设置操作人id。
	 */
	public void setUserId(long userId){
		if ((!String.valueOf(this.userId).equals(String.valueOf(userId)))) {
			if (this.UPDATED_COLUMN == null) {
				_INIT_UPDATE_INFO();
			}
			this.UPDATED_COLUMN.add("user_id");
			this.UPDATED_INFO.append("user_id:\"" + this.userId+ "\"=>\""
                + userId + "\"\r\n");
			this.userId = userId;
		}
	}

	/**
	 * 设置用户类型。
	 */
	public void setUserType(int userType){
		if ((!String.valueOf(this.userType).equals(String.valueOf(userType)))) {
			if (this.UPDATED_COLUMN == null) {
				_INIT_UPDATE_INFO();
			}
			this.UPDATED_COLUMN.add("user_type");
			this.UPDATED_INFO.append("user_type:\"" + this.userType+ "\"=>\""
                + userType + "\"\r\n");
			this.userType = userType;
		}
	}

	/**
	 * 设置用户的组ID。
	 */
	public void setGroupId(long groupId){
		if ((!String.valueOf(this.groupId).equals(String.valueOf(groupId)))) {
			if (this.UPDATED_COLUMN == null) {
				_INIT_UPDATE_INFO();
			}
			this.UPDATED_COLUMN.add("group_id");
			this.UPDATED_INFO.append("group_id:\"" + this.groupId+ "\"=>\""
                + groupId + "\"\r\n");
			this.groupId = groupId;
		}
	}

	/**
	 * 设置操作人名称。
	 */
	public void setUserName(String userName){
		if ((!String.valueOf(this.userName).equals(String.valueOf(userName)))) {
			if (this.UPDATED_COLUMN == null) {
				_INIT_UPDATE_INFO();
			}
			this.UPDATED_COLUMN.add("user_name");
			this.UPDATED_INFO.append("user_name:\"" + this.userName+ "\"=>\""
                + userName + "\"\r\n");
			this.userName = userName;
		}
	}

	/**
	 * 设置操作人用户昵称。
	 */
	public void setNickName(String nickName){
		if ((!String.valueOf(this.nickName).equals(String.valueOf(nickName)))) {
			if (this.UPDATED_COLUMN == null) {
				_INIT_UPDATE_INFO();
			}
			this.UPDATED_COLUMN.add("nick_name");
			this.UPDATED_INFO.append("nick_name:\"" + this.nickName+ "\"=>\""
                + nickName + "\"\r\n");
			this.nickName = nickName;
		}
	}

	/**
	 * 设置操作人真实名称。
	 */
	public void setRealName(String realName){
		if ((!String.valueOf(this.realName).equals(String.valueOf(realName)))) {
			if (this.UPDATED_COLUMN == null) {
				_INIT_UPDATE_INFO();
			}
			this.UPDATED_COLUMN.add("real_name");
			this.UPDATED_INFO.append("real_name:\"" + this.realName+ "\"=>\""
                + realName + "\"\r\n");
			this.realName = realName;
		}
	}

	/**
	 * 设置操作对象id。
	 */
	public void setObjectId(String objectId){
		if ((!String.valueOf(this.objectId).equals(String.valueOf(objectId)))) {
			if (this.UPDATED_COLUMN == null) {
				_INIT_UPDATE_INFO();
			}
			this.UPDATED_COLUMN.add("object_id");
			this.UPDATED_INFO.append("object_id:\"" + this.objectId+ "\"=>\""
                + objectId + "\"\r\n");
			this.objectId = objectId;
		}
	}

	/**
	 * 设置操作对象类型。
	 */
	public void setObjectType(String objectType){
		if ((!String.valueOf(this.objectType).equals(String.valueOf(objectType)))) {
			if (this.UPDATED_COLUMN == null) {
				_INIT_UPDATE_INFO();
			}
			this.UPDATED_COLUMN.add("object_type");
			this.UPDATED_INFO.append("object_type:\"" + this.objectType+ "\"=>\""
                + objectType + "\"\r\n");
			this.objectType = objectType;
		}
	}

	/**
	 * 设置请求uri。
	 */
	public void setUri(String uri){
		if ((!String.valueOf(this.uri).equals(String.valueOf(uri)))) {
			if (this.UPDATED_COLUMN == null) {
				_INIT_UPDATE_INFO();
			}
			this.UPDATED_COLUMN.add("uri");
			this.UPDATED_INFO.append("uri:\"" + this.uri+ "\"=>\""
                + uri + "\"\r\n");
			this.uri = uri;
		}
	}

	/**
	 * 设置方法操作描述。
	 */
	public void setInfo(String info){
		if ((!String.valueOf(this.info).equals(String.valueOf(info)))) {
			if (this.UPDATED_COLUMN == null) {
				_INIT_UPDATE_INFO();
			}
			this.UPDATED_COLUMN.add("info");
			this.UPDATED_INFO.append("info:\"" + this.info+ "\"=>\""
                + info + "\"\r\n");
			this.info = info;
		}
	}

	/**
	 * 设置日志内容。
	 */
	public void setLog(String log){
		if ((!String.valueOf(this.log).equals(String.valueOf(log)))) {
			if (this.UPDATED_COLUMN == null) {
				_INIT_UPDATE_INFO();
			}
			this.UPDATED_COLUMN.add("log");
			this.UPDATED_INFO.append("log:\"" + this.log+ "\"=>\""
                + log + "\"\r\n");
			this.log = log;
		}
	}

	/**
	 * 设置请求参数。
	 */
	public void setRequestBody(String requestBody){
		if ((!String.valueOf(this.requestBody).equals(String.valueOf(requestBody)))) {
			if (this.UPDATED_COLUMN == null) {
				_INIT_UPDATE_INFO();
			}
			this.UPDATED_COLUMN.add("request_body");
			this.UPDATED_INFO.append("request_body:\"" + this.requestBody+ "\"=>\""
                + requestBody + "\"\r\n");
			this.requestBody = requestBody;
		}
	}

	/**
	 * 设置响应日志。
	 */
	public void setResponseBody(String responseBody){
		if ((!String.valueOf(this.responseBody).equals(String.valueOf(responseBody)))) {
			if (this.UPDATED_COLUMN == null) {
				_INIT_UPDATE_INFO();
			}
			this.UPDATED_COLUMN.add("response_body");
			this.UPDATED_INFO.append("response_body:\"" + this.responseBody+ "\"=>\""
                + responseBody + "\"\r\n");
			this.responseBody = responseBody;
		}
	}

	/**
	 * 设置请求毫秒数。
	 */
	public void setResponseMillis(long responseMillis){
		if ((!String.valueOf(this.responseMillis).equals(String.valueOf(responseMillis)))) {
			if (this.UPDATED_COLUMN == null) {
				_INIT_UPDATE_INFO();
			}
			this.UPDATED_COLUMN.add("response_millis");
			this.UPDATED_INFO.append("response_millis:\"" + this.responseMillis+ "\"=>\""
                + responseMillis + "\"\r\n");
			this.responseMillis = responseMillis;
		}
	}

	/**
	 * 设置异常信息。
	 */
	public void setException(String exception){
		if ((!String.valueOf(this.exception).equals(String.valueOf(exception)))) {
			if (this.UPDATED_COLUMN == null) {
				_INIT_UPDATE_INFO();
			}
			this.UPDATED_COLUMN.add("exception");
			this.UPDATED_INFO.append("exception:\"" + this.exception+ "\"=>\""
                + exception + "\"\r\n");
			this.exception = exception;
		}
	}

	/**
	 * 设置响应状态码。
	 */
	public void setStatusCode(int statusCode){
		if ((!String.valueOf(this.statusCode).equals(String.valueOf(statusCode)))) {
			if (this.UPDATED_COLUMN == null) {
				_INIT_UPDATE_INFO();
			}
			this.UPDATED_COLUMN.add("status_code");
			this.UPDATED_INFO.append("status_code:\"" + this.statusCode+ "\"=>\""
                + statusCode + "\"\r\n");
			this.statusCode = statusCode;
		}
	}

	/**
	 * 设置应用信息。
	 */
	public void setAppInfo(String appInfo){
		if ((!String.valueOf(this.appInfo).equals(String.valueOf(appInfo)))) {
			if (this.UPDATED_COLUMN == null) {
				_INIT_UPDATE_INFO();
			}
			this.UPDATED_COLUMN.add("app_info");
			this.UPDATED_INFO.append("app_info:\"" + this.appInfo+ "\"=>\""
                + appInfo + "\"\r\n");
			this.appInfo = appInfo;
		}
	}

	/**
	 * 设置应用主机。
	 */
	public void setAppHost(String appHost){
		if ((!String.valueOf(this.appHost).equals(String.valueOf(appHost)))) {
			if (this.UPDATED_COLUMN == null) {
				_INIT_UPDATE_INFO();
			}
			this.UPDATED_COLUMN.add("app_host");
			this.UPDATED_INFO.append("app_host:\"" + this.appHost+ "\"=>\""
                + appHost + "\"\r\n");
			this.appHost = appHost;
		}
	}

	/**
	 * 设置操作人ip。
	 */
	public void setUserIp(String userIp){
		if ((!String.valueOf(this.userIp).equals(String.valueOf(userIp)))) {
			if (this.UPDATED_COLUMN == null) {
				_INIT_UPDATE_INFO();
			}
			this.UPDATED_COLUMN.add("user_ip");
			this.UPDATED_INFO.append("user_ip:\"" + this.userIp+ "\"=>\""
                + userIp + "\"\r\n");
			this.userIp = userIp;
		}
	}

	/**
	 * 设置创建时间。
	 */
	public void setRequestDate(java.util.Date requestDate){
		if ((!String.valueOf(this.requestDate).equals(String.valueOf(requestDate)))) {
			if (this.UPDATED_COLUMN == null) {
				_INIT_UPDATE_INFO();
			}
			this.UPDATED_COLUMN.add("request_date");
			this.UPDATED_INFO.append("request_date:\"" + this.requestDate+ "\"=>\""
                + requestDate + "\"\r\n");
			this.requestDate = requestDate;
		}
	}

	/**
	 * 重载toString方法.
	 */
    @Override
	public String toString() {
		StringBuilder sb = new StringBuilder();
		sb.append("id:\"" + this.id + "\"\r\n");
		sb.append("saas_id:\"" + this.saasId + "\"\r\n");
		sb.append("mch_id:\"" + this.mchId + "\"\r\n");
		sb.append("user_id:\"" + this.userId + "\"\r\n");
		sb.append("user_type:\"" + this.userType + "\"\r\n");
		sb.append("group_id:\"" + this.groupId + "\"\r\n");
		sb.append("user_name:\"" + this.userName + "\"\r\n");
		sb.append("nick_name:\"" + this.nickName + "\"\r\n");
		sb.append("real_name:\"" + this.realName + "\"\r\n");
		sb.append("object_id:\"" + this.objectId + "\"\r\n");
		sb.append("object_type:\"" + this.objectType + "\"\r\n");
		sb.append("uri:\"" + this.uri + "\"\r\n");
		sb.append("info:\"" + this.info + "\"\r\n");
		sb.append("log:\"" + this.log + "\"\r\n");
		sb.append("request_body:\"" + this.requestBody + "\"\r\n");
		sb.append("response_body:\"" + this.responseBody + "\"\r\n");
		sb.append("response_millis:\"" + this.responseMillis + "\"\r\n");
		sb.append("exception:\"" + this.exception + "\"\r\n");
		sb.append("status_code:\"" + this.statusCode + "\"\r\n");
		sb.append("app_info:\"" + this.appInfo + "\"\r\n");
		sb.append("app_host:\"" + this.appHost + "\"\r\n");
		sb.append("user_ip:\"" + this.userIp + "\"\r\n");
		sb.append("request_date:\"" + this.requestDate + "\"\r\n");
		return sb.toString();
	}

}