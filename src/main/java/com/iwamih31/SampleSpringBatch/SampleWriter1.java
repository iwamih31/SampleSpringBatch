package com.iwamih31.SampleSpringBatch;

import org.springframework.batch.item.Chunk;
import org.springframework.batch.item.ItemWriter;
import org.springframework.stereotype.Component;

@Component
public class SampleWriter1 implements ItemWriter<FileInfo> {

	@Override
	public void write(Chunk<? extends FileInfo> chunk) throws Exception {
		// TODO 自動生成されたメソッド・スタブ

	}

}
