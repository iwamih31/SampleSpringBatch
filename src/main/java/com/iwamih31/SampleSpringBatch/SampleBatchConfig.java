package com.iwamih31.SampleSpringBatch;

import java.nio.charset.StandardCharsets;
import java.util.HashMap;
import java.util.Map;

import org.springframework.batch.core.Job;
import org.springframework.batch.core.Step;
import org.springframework.batch.core.job.builder.JobBuilder;
import org.springframework.batch.core.repository.JobRepository;
import org.springframework.batch.core.step.builder.StepBuilder;
import org.springframework.batch.core.step.tasklet.Tasklet;
import org.springframework.batch.item.database.JpaPagingItemReader;
import org.springframework.batch.item.database.builder.JpaPagingItemReaderBuilder;
import org.springframework.batch.item.file.FlatFileItemReader;
import org.springframework.batch.item.file.builder.FlatFileItemReaderBuilder;
import org.springframework.batch.item.file.transform.DelimitedLineTokenizer;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.io.ClassPathResource;
import org.springframework.transaction.PlatformTransactionManager;

import jakarta.persistence.EntityManagerFactory;
import lombok.RequiredArgsConstructor;

@Configuration
@RequiredArgsConstructor
public class SampleBatchConfig {

	private final SampleReader1 reader;
	private final SampleProcessor1 processor;
	private final SampleWriter1 writer;

	private final EntityManagerFactory entityManagerFactory;


    // tag::jobstep[]
    @Bean
    Job sampleJob(JobRepository jobRepository, Step ｓampleStep) {
	  return new JobBuilder("sampleJob", jobRepository)
	    .start(ｓampleStep)
	    .build();
	}

    @Bean
    Step ｓampleStep(JobRepository jobRepository, PlatformTransactionManager transactionManager, Tasklet sampleTasklet) {
	  return new StepBuilder("ｓampleStep", jobRepository)
			  .<ReceiveFileInfo, FileInfo>chunk(9, transactionManager)
			  .reader(reader)
			  .processor(processor)
			  .writer(writer)
			  .build();
	}

    /**
     * CSVからデータ取得を行うItemReaderの定義を行う。
     *
     * @return　ItemReader定義
     */
    FlatFileItemReader<ReceiveFileInfo> csvReader() {
    	return new FlatFileItemReaderBuilder<ReceiveFileInfo>()
		.name("ReceiveFileInfoItemReader")
		.resource(new ClassPathResource("C:\\work\\sample.ｃｓｖ"))
		.encoding(StandardCharsets.UTF_8.name())
		.lineTokenizer(new DelimitedLineTokenizer())
		.fieldSetMapper(new ReceiveFileInfoFieldSetMapper())
		.linesToSkip(0)
		.build();
    }

    /**
     * データベースからデータ取得を行うItemReaderの定義を行う。
     *
     * @return　ItemReader定義
     */
    JpaPagingItemReader<ProcessedFileInfo> tableReader() {

    	Map<String, Object> param = new HashMap<>();
    	param.put("isAdult", true);

    	return new JpaPagingItemReaderBuilder<ProcessedFileInfo>()
    			.entityManagerFactory(entityManagerFactory)
    			.name("ReceiveFileInfoItemReader")
//    			.queryProvider(queryProvider)
    			.queryString("SELECT t FROM ProcessedFileInfo t WHERE t.isAdult = :isAdult")
    			.parameterValues(param)
    			.pageSize(5)
    			.build();
    }
}

