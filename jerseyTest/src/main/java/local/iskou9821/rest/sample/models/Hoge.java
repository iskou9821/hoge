package local.iskou9821.rest.sample.models;

import javax.xml.bind.annotation.XmlRootElement;

@XmlRootElement
public class Hoge {
	private Long id;
	private String msg1;
	private String msg2;
	private Integer num1;
	
	public Hoge() {
		super();
	}
	public Hoge(Long id, String msg1, String msg2, Integer num1) {
		super();
		this.id = id;
		this.msg1 = msg1;
		this.msg2 = msg2;
		this.num1 = num1;
	}
	public Long getId() {
		return id;
	}
	public void setId(Long id) {
		this.id = id;
	}
	public String getMsg1() {
		return msg1;
	}
	public void setMsg1(String msg1) {
		this.msg1 = msg1;
	}
	public String getMsg2() {
		return msg2;
	}
	public void setMsg2(String msg2) {
		this.msg2 = msg2;
	}
	public Integer getNum1() {
		return num1;
	}
	public void setNum1(Integer num1) {
		this.num1 = num1;
	}
}
