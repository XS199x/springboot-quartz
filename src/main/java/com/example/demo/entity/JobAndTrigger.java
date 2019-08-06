package com.example.demo.entity;

import lombok.Getter;
import lombok.Setter;

import java.math.BigInteger;

public class JobAndTrigger {
	@Getter @Setter private String JOB_NAME;
	@Getter @Setter private String JOB_GROUP;
	@Getter @Setter private String JOB_CLASS_NAME;
	@Getter @Setter private String JOB_DATA;
	@Getter @Setter private String TRIGGER_NAME;
	@Getter @Setter private String TRIGGER_GROUP;
	@Getter @Setter private BigInteger REPEAT_INTERVAL;
	@Getter @Setter private BigInteger TIMES_TRIGGERED;
	@Getter @Setter private String CRON_EXPRESSION;
	@Getter @Setter private String TIME_ZONE_ID;
}
