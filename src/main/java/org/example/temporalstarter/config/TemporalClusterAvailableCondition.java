package org.example.temporalstarter.config;

import org.springframework.context.annotation.Condition;
import org.springframework.context.annotation.ConditionContext;
import org.springframework.core.type.AnnotatedTypeMetadata;

//@DependsOn(TemporalClusterChecker.class)
public class TemporalClusterAvailableCondition implements Condition {
//public class TemporalClusterAvailableCondition implements Condition, ApplicationContextAware {

//    @Autowired
//    private TemporalClusterChecker temporalClusterChecker;

//    private static ApplicationContext context;
//
//    @Override
//    public void setApplicationContext(ApplicationContext applicationContext) {
//        context = applicationContext;
//    }

    @Override
    public boolean matches(ConditionContext context,
                           AnnotatedTypeMetadata metadata) {
//        TemporalClusterChecker checker = TemporalClusterAvailableCondition.context.getBean(TemporalClusterChecker.class);
//        return checker.isTemporalClusterAvailable();
        TemporalClusterAvailabilityService checker = context.getBeanFactory().getBean(TemporalClusterAvailabilityService.class);
        return checker.isTemporalClusterAvailable();
    }
}
