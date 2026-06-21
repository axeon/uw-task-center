package uw.task.center.vo;

import io.swagger.v3.oas.annotations.media.Schema;

import java.io.Serializable;

/**
 * 主机上报响应。
 *
 * <p>{@code /rpc/task/host/report} 的返回体，告知客户端其在本中心的身份（id）、注册 IP 与当前生效状态。
 * 客户端拿到 id 后在后续上报中回传，实现幂等更新。</p>
 *
 * @author axeon
 */
@Schema(title = "主机报告Response", description = "主机报告Response")
public class HostReportResponse implements Serializable{


	/**
	 * 主机记录主键（首次上报时由中心分配，后续上报由客户端回传）。
	 */
	@Schema(title = "id", description = "id")
	private long id;

	/**
	 * 主机注册 IP（取自 RPC 远端地址）。
	 */
	@Schema(title = "hostIp", description = "hostIp")
	private String hostIp;

	/**
	 * 主机状态：1 正常 / 0 已屏蔽（被 OPS disable）。
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