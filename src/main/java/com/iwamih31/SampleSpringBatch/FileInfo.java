package com.iwamih31.SampleSpringBatch;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.Data;

@Entity
@Table(name = "file_info")
@Data
public class FileInfo {

	@Id
	private String id;

	private String name;

	private int age;

	@Column(name = "is_adult")
	private boolean isAdult;

}
