package com.iwamih31.SampleSpringBatch;

import org.springframework.batch.item.file.mapping.FieldSetMapper;
import org.springframework.batch.item.file.transform.FieldSet;
import org.springframework.validation.BindException;

public class ReceiveFileInfoFieldSetMapper implements FieldSetMapper<ReceiveFileInfo> {

	@Override
	public ReceiveFileInfo mapFieldSet(FieldSet fieldSet) throws BindException {
		var receiveFileInfo = new ReceiveFileInfo();

		receiveFileInfo.setId(fieldSet.readString(0));
		receiveFileInfo.setName(fieldSet.readString(1));
		receiveFileInfo.setAge(Integer.parseInt(fieldSet.readString(2)));

		return receiveFileInfo;
	}

}
