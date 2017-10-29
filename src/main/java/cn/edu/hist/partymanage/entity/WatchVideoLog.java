package cn.edu.hist.partymanage.entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

/*
* @author 宋
* @mail 860080937@qq.com
* @time 2017年3月5日 下午2:32:29
* 类说明
*/
@Entity
@Table(name="tb_watchVideoLog")
public class WatchVideoLog {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private int id;
	private int userId;
	private int videoId;
	@Column(length=50)
	private String videoName;
	@Column(length=10)
	private String userName;//更改用户名时记得修改
	private long watchSecond;//观看时长,second,秒
	public WatchVideoLog(){
		
	}
	
	/**
	 * @param userId
	 * @param videoId
	 * @param videoName
	 * @param userName
	 * @param watchSecond
	 */
	public WatchVideoLog( int userId, int videoId, String videoName, String userName, long watchSecond) {
		super();	
		this.userId = userId;
		this.videoId = videoId;
		this.videoName = videoName;
		this.userName = userName;
		this.watchSecond = watchSecond;
	}

	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public int getUserId() {
		return userId;
	}
	public void setUserId(int userId) {
		this.userId = userId;
	}
	public int getVideoId() {
		return videoId;
	}
	public void setVideoId(int videoId) {
		this.videoId = videoId;
	}
	public String getVideoName() {
		return videoName;
	}
	public void setVideoName(String videoName) {
		this.videoName = videoName;
	}
	public String getUserName() {
		return userName;
	}
	public void setUserName(String userName) {
		this.userName = userName;
	}
	public long getWatchSecond() {
		return watchSecond;
	}
	public void setWatchSecond(long watchSecond) {
		this.watchSecond = watchSecond;
	}
	@Override
	public String toString() {
		return "WatchVideoLog [id=" + id + ", userId=" + userId + ", videoId=" + videoId + ", videoName=" + videoName
				+ ", userName=" + userName + ", watchSecond=" + watchSecond + "]";
	}
}
