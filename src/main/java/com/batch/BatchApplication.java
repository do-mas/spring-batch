package com.batch;

import com.batch.xlsx.FieldMapper;
import com.batch.xlsx.JobListener;
import com.batch.xlsx.RecordProcessor;
import com.batch.xlsx.RecordWriter;
import exc.RowMapper;
import exc.poi.PoiItemReader;
import org.springframework.batch.core.Job;
import org.springframework.batch.core.JobExecutionListener;
import org.springframework.batch.core.Step;
import org.springframework.batch.core.configuration.annotation.*;
import org.springframework.batch.core.launch.support.RunIdIncrementer;
import org.springframework.batch.core.launch.support.SimpleJobLauncher;
import org.springframework.batch.core.repository.JobRepository;
import org.springframework.batch.core.repository.support.MapJobRepositoryFactoryBean;
import org.springframework.batch.item.ItemProcessor;
import org.springframework.batch.support.transaction.ResourcelessTransactionManager;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.core.io.ClassPathResource;
import org.springframework.scheduling.annotation.EnableAsync;
import springfox.documentation.swagger2.annotations.EnableSwagger2;


@EnableSwagger2
@EnableBatchProcessing
@SpringBootApplication
@EnableAsync
public class BatchApplication {

    public static void main(String[] args) {
        SpringApplication.run(BatchApplication.class, args);
    }


    @Bean
    public ResourcelessTransactionManager transactionManager() {
        return new ResourcelessTransactionManager();
    }

    @Bean
    public JobRepository jobRepository(ResourcelessTransactionManager transactionManager) throws Exception {

        MapJobRepositoryFactoryBean mapJobRepositoryFactoryBean = new MapJobRepositoryFactoryBean(transactionManager);
        mapJobRepositoryFactoryBean.setTransactionManager(transactionManager);
        JobRepository jobRepository = mapJobRepositoryFactoryBean.getObject();
        return jobRepository;

    }

    @Bean
    public SimpleJobLauncher simpleJobLauncher(JobRepository jobRepository) {
        SimpleJobLauncher simpleJobLauncher = new SimpleJobLauncher();
        simpleJobLauncher.setJobRepository(jobRepository);
        return simpleJobLauncher;
    }

//    @Bean
//    public Job importExcelJob(JobBuilderFactory jobBuilderFactory, StepBuilderFactory stepBuilderFactory) throws Exception {
//        return jobBuilderFactory.get("jobLauncher")
//                .incrementer(new RunIdIncrementer())
//                .listener(listener())
//                .flow(importInvoiceStep(stepBuilderFactory))
//                .end()
//                .build();
//
//    }
//
//    private Step importInvoiceStep(StepBuilderFactory stepBuilderFactory) throws Exception {
//        return stepBuilderFactory.get("importStep")
//                .chunk(500)
//                .csvReader(csvReader(null))
//                .processor(processor())
//                .writer(writer())
//                .build();
//    }
//
//    @Bean
//    @StepScope
//    public PoiItemReader csvReader(@Value("#{jobParameters[filePath]}") String filePath) throws Exception {
//
//        PoiItemReader<String> csvReader = new PoiItemReader<>();
//        csvReader.setResource(new ClassPathResource("test.xlsx"));
//        RowMapper<String> rm = new CsvFieldMapper();
//        csvReader.setRowMapper(rm);
//        return csvReader;
//
//    }
//
//    @Bean
//    @StepScope
//    public ItemProcessor processor() {
//        return new RecordProcessor();
//    }
//
//    @Bean
//    @StepScope
//    public RecordWriter writer() {
//        return new RecordWriter();
//    }
//
//    @Bean
//    @JobScope
//    public JobExecutionListener listener() {
//        return new JobListener();
//    }
//
//    @Bean
//    public ResourcelessTransactionManager transactionManager() {
//        return new ResourcelessTransactionManager();
//    }
//
//    @Bean
//    public JobRepository jobRepository(ResourcelessTransactionManager transactionManager) throws Exception {
//        MapJobRepositoryFactoryBean mapJobRepositoryFactoryBean = new MapJobRepositoryFactoryBean(transactionManager);
//        mapJobRepositoryFactoryBean.setTransactionManager(transactionManager);
//        JobRepository jobRepository = mapJobRepositoryFactoryBean.getObject();
//        return jobRepository;
//    }
//
//    @Bean
//    public SimpleJobLauncher simpleJobLauncher(JobRepository jobRepository) {
//        SimpleJobLauncher simpleJobLauncher = new SimpleJobLauncher();
//        simpleJobLauncher.setJobRepository(jobRepository);
//        return simpleJobLauncher;
//    }


}
