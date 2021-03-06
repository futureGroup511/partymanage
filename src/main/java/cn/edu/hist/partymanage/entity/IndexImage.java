package cn.edu.hist.partymanage.entity;
/** 
* @author 焦祥宇 
* @version 创建时间：2017年3月7日 下午5:12:23 
* @description 轮播图片类
*/
import static javax.persistence.GenerationType.IDENTITY;

import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name="tb_indexImage")
public class IndexImage {
	@Id
	@GeneratedValue(strategy = IDENTITY)
	private int id;
	
	@Column(length=255)
	private String imgUrl;//图片本身的url，前台前面为${rootPath}+imgUrl
	
	@Column(length=200)
	private String url;//图片对应的链接,需要包括http://
	private Date createTime;
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public String getUrl() {
		return url;
	}
	public void setUrl(String url) {
		this.url = url;
	}
	public Date getCreateTime() {
		return createTime;
	}
	public void setCreateTime(Date createTime) {
		this.createTime = createTime;
	}
	public String getImgUrl() {
		return imgUrl;
	}
	public void setImgUrl(String imgUrl) {
		this.imgUrl = imgUrl;
	}
	@Override
	public String toString() {
		return "IndexImage [id=" + id + ", imgUrl=" + imgUrl + ", url=" + url + ", createTime=" + createTime + "]";
	}
	
}
