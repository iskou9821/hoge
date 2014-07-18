package local.iskou9821.hibernate.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import com.google.common.base.Objects;

@Entity
public class Hoge extends AbsreactModel {
	@Column(nullable=false, length=128)
	private String msg;
	
	@Column(nullable=false, length=512)
	private String comment;

	public String getMsg() {
		return msg;
	}

	public void setMsg(String msg) {
		this.msg = msg;
	}

	public String getComment() {
		return comment;
	}

	public void setComment(String comment) {
		this.comment = comment;
	}

	@Override
	public String toString() {
		return Objects.toStringHelper(this)
			.add("super", super.toString())
			.add("msg", msg)
			.add("comment", comment)
			.toString();
	}
}
