package cloud.ffeng.cat.spring.strategy.context;


import cloud.ffeng.cat.spring.strategy.annotation.Strategy;
import cloud.ffeng.cat.spring.strategy.base.StrategyHandler;
import org.springframework.beans.BeansException;
import org.springframework.context.ApplicationContext;
import org.springframework.context.ApplicationContextAware;

import java.util.HashMap;
import java.util.Map;
import java.util.Objects;

public class StrategyContext implements ApplicationContextAware {

    public static final String DEFAULT = "DEFAULT";

    private static Map<String, Map<String, StrategyHandler<?, Object>>> bizTypeHandlerMap = new HashMap<>();

    @Override
    public void setApplicationContext(ApplicationContext applicationContext) throws BeansException {
        Map<String, Object> nameBeanMap = applicationContext.getBeansWithAnnotation(Strategy.class);
        nameBeanMap.forEach((name, bean) -> {
            if (!(bean instanceof StrategyHandler)) {
                return;
            }

            // biz group.
            Strategy strategy = bean.getClass().getDeclaredAnnotation(Strategy.class);
            String biz = Objects.isNull(strategy.biz()) || strategy.biz().trim().length() == 0 ? DEFAULT : strategy.biz().trim();

            // add handler.
            if (bizTypeHandlerMap.containsKey(biz)) {
                bizTypeHandlerMap.get(strategy.biz()).put(strategy.type(), (StrategyHandler<?, Object>) bean);
                return;
            }

            // new handler container.
            Map<String, StrategyHandler<?, Object>> typeHandlerMap = new HashMap<>();
            typeHandlerMap.put(strategy.type(), (StrategyHandler<?, Object>) bean);
            bizTypeHandlerMap.put(biz, typeHandlerMap);
        });
    }


    public static StrategyHandler<?, Object> get(String biz, String type) {
        biz = Objects.isNull(biz) || biz.trim().length() == 0 ? DEFAULT : biz.trim();
        if (bizTypeHandlerMap.containsKey(biz)) {
            Map<String, StrategyHandler<?, Object>> typeStrategyHandlerMap = bizTypeHandlerMap.get(biz);
            if (typeStrategyHandlerMap.containsKey(type)) {
                return typeStrategyHandlerMap.get(type);
            }
        }
        throw new RuntimeException("Cant get handler by biz: " + biz + ", type: " + type + ".");
    }

    public static void main(String[] args) {
        Object result = StrategyContext.get("", "sdfs").handle("param");
    }
}
