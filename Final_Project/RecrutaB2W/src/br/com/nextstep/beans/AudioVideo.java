package br.com.nextstep.beans;

import java.io.Serializable;

public class AudioVideo implements Serializable{

	private static final long serialVersionUID = 1L;

	private int id;
    private String pathAudio;
    private String pathVideo;


	//CONSTRUTORES	
    public AudioVideo(String pathAudio, String pathVideo, int id) {
		super();
		this.pathAudio = pathAudio;
		this.pathVideo = pathVideo;
		this.id = id;
	}

    public AudioVideo(){}
    
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public String getPathAudio() {
		return pathAudio;
	}
	public void setPathAudio(String pathAudio) {
		this.pathAudio = pathAudio;
	}
	public String getPathVideo() {
		return pathVideo;
	}
	public void setPathVideo(String pathVideo) {
		this.pathVideo = pathVideo;
	}

	@Override
	public String toString() {
		return "AudioVideo [id=" + id + ", pathAudio=" + pathAudio + ", pathVideo=" + pathVideo + "]";
	}


	
 
}