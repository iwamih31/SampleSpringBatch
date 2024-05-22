package com.iwamih31.SampleSpringBatch;

import org.springframework.batch.item.ItemProcessor;
import org.springframework.stereotype.Component;

@Component
public class SampleProcessor1 implements ItemProcessor<ReceiveFileInfo, FileInfo>{


	@Override
	public FileInfo process(ReceiveFileInfo item) throws Exception {
		var processedFileInfo = new FileInfo();
		processedFileInfo.setId(item.getId());
		processedFileInfo.setName(item.getName());
		processedFileInfo.setAge(item.getAge());
		processedFileInfo.setAdult(item.getAge() > 19);

		return processedFileInfo;

	}

}
