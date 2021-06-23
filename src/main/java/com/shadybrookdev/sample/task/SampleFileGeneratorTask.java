package com.shadybrookdev.sample.task;

import com.shadybrookdev.sample.delegate.RunManager;
import com.shadybrookdev.sample.enums.RunType;
import com.shadybrookdev.sample.service.RunDelegate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.ApplicationArguments;
import org.springframework.boot.ApplicationRunner;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import javax.persistence.EntityManager;
import java.time.LocalDate;
import java.util.stream.Stream;

@Component
public class SampleFileGeneratorTask implements ApplicationRunner {

    //command line args
    @Value("${effectiveDate}")
    @DateTimeFormat(pattern = "MM/dd/yyyy")
    LocalDate effectiveDate;

    @Value("${runType}")
    RunType runType;

    final RunDelegate runDelegate;

    final EntityManager entityManager;

    @Autowired
    public SampleFileGeneratorTask(RunDelegate runDelegate, EntityManager entityManager) {
        this.runDelegate = runDelegate;
        this.entityManager = entityManager;
    }

    @Override
    @Transactional(readOnly = true)
    public void run(ApplicationArguments args) throws Exception {
        //determine run type
        RunManager runManager = runDelegate.identifyRun(runType);

        //get data
        Stream data = runManager.getData(effectiveDate);

        //write data
        data.forEach(record -> {
            //run function from manager to get line
            System.out.println(runManager.writeCSVLine().apply(record));
            //detach record to alleviate mem
            entityManager.detach(record);
        });
    }
}
