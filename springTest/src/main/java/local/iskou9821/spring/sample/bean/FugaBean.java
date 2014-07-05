package local.iskou9821.spring.sample.bean;

import java.util.ArrayList;
import java.util.List;

public class FugaBean {
	private String msg;
	private List<HogeBean> hoges = new ArrayList<>();

	public String getMsg() {
		return msg;
	}

	public void setMsg(String msg) {
		this.msg = msg;
	}

	public List<HogeBean> getHoges() {
		return hoges;
	}

	public void setHoges(List<HogeBean> hoges) {
		this.hoges = hoges;
	}
	
}
