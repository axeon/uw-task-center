package uw.task.center.vo;

import io.swagger.v3.oas.annotations.media.Schema;

import java.io.Serializable;

/**
 * 主机报告Response。
 *
 * @author axeon
 */
@Schema(title = "主机报告Response", description = "主机报告Response")
public class HostReportResponse implements Serializable{


	/**
	 * id
	 */
	@Schema(title = "id", description = "id")
	private long id;

	/**
	 * hostIp
	 */
	@Schema(title = "hostIp", description = "hostIp")
	private String hostIp;

	/**
	 * 状态
	 */
	@Schema(title = "状态", description = "状态")
	private int state;

	public long getId() {
		return id;
	}

	public void setId(long id) {
		this.id = id;
	}

	public String getHostIp() {
		return hostIp;
	}

	public void setHostIp(String hostIp) {
		this.hostIp = hostIp;
	}

	public int getState() {
		return state;
	}

	public void setState(int state) {
		this.state = state;
	}
}