package com.iwamih31.SampleSpringBatch;

import java.nio.charset.Charset;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.List;

import org.springframework.batch.item.ItemReader;
import org.springframework.batch.item.NonTransientResourceException;
import org.springframework.batch.item.ParseException;
import org.springframework.batch.item.UnexpectedInputException;
import org.springframework.stereotype.Component;

@Component
public class SampleReader1 implements ItemReader<ReceiveFileInfo>{

	List<String> lines = null;

	int currentIndex = 0;

	@Override
	public ReceiveFileInfo read()
			throws Exception, UnexpectedInputException, ParseException, NonTransientResourceException {

		if (lines == null) {
			try {
				lines = Files.readAllLines(Paths.get("C:\\work\\sample.ｃｓｖ"),Charset.forName("UTF-8"));
			} catch (Exception e) {
				throw e;
			}
		}

		if (currentIndex < lines.size()) {
			// まだ読み込んでない行が残っている場合
			var arrColumn = lines.get(currentIndex++).split(",");
			var receiveFileInfo = new ReceiveFileInfo();
			receiveFileInfo.setId(arrColumn[0]);
			receiveFileInfo.setName(arrColumn[1]);
			receiveFileInfo.setAge(Integer.parseInt(arrColumn[2]));
			return receiveFileInfo;
		}

		return null;
	}

}
