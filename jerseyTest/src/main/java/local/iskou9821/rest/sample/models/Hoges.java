package local.iskou9821.rest.sample.models;

import javax.xml.bind.annotation.XmlRootElement;

@XmlRootElement(name="hoges") //ルート要素の名前を変えるときは、「name」を付ける。
public class Hoges extends Hoge {
	public Hoges() {
		super();
	}
	
	public Hoges(Long id, String msg1, String msg2, Integer num1) {
		super(id, msg1, msg2, num1);
	}
	
	public Hoges(Hoge hoge) {
		this.setId(hoge.getId());
		this.setMsg1(hoge.getMsg1());
		this.setMsg2(hoge.getMsg2());
		this.setNum1(hoge.getNum1());
	}
}
