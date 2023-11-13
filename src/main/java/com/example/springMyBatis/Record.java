package com.example.springMyBatis;

import java.io.Serializable;
import java.util.Date;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class Record implements Serializable{
	private static final long serialVersionUID = 1L;
	private Long id;
	private String title;
	private Date releaseDate;
	private Long artistId;
	private Long labelId;

	@Override
	public String toString() {
		return "Record{" +
				"id=" + id +
				", title='" + title + '\'' +
				", releaseDate=" + releaseDate +
				", artistId=" + artistId +
				", labelId=" + labelId +
				'}';
	}
}