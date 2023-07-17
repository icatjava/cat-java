package cloud.ffeng.cat.spring.strategy.base;

public interface StrategyHandler<RESULT, PARAM> {

    /**
     * 策略处理器
     *
     * @param param 入参
     * @return 出参
     */
    RESULT handle(PARAM param);
}
