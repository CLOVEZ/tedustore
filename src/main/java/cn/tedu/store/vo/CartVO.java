package cn.tedu.store.vo;

import java.io.Serializable;

/**
 * 购物车数据的Value Object类
 */
public class CartVO implements Serializable {
	
	private static final long serialVersionUID = -2149265607062947685L;
	
	private Integer cid;
	private Long gid;
	private String image;
	private Integer num;
	private Long price;
	private String title;

	public Integer getCid() {
		return cid;
	}

	public Long getGid() {
		return gid;
	}

	public String getImage() {
		return image;
	}

	public Integer getNum() {
		return num;
	}

	public Long getPrice() {
		return price;
	}

	public String getTitle() {
		return title;
	}

	public void setCid(Integer cid) {
		this.cid = cid;
	}

	public void setGid(Long gid) {
		this.gid = gid;
	}

	public void setImage(String image) {
		this.image = image;
	}

	public void setNum(Integer num) {
		this.num = num;
	}

	public void setPrice(Long price) {
		this.price = price;
	}

	public void setTitle(String title) {
		this.title = title;
	}

	@Override
	public String toString() {
		return "CartVO [cid=" + cid + ", gid=" + gid + ", title=" + title + ", image=" + image + ", price=" + price
				+ ", num=" + num + "]";
	}
}
