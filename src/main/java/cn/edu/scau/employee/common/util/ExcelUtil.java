package cn.edu.scau.employee.common.util;

import com.alibaba.excel.context.AnalysisContext;
import com.alibaba.excel.event.AnalysisEventListener;

import java.util.LinkedList;
import java.util.List;
import java.util.function.Consumer;

/**
 * @author chen.jiale
 * @Description excel工具
 * @date 2020/2/23 22:22
 */
public class ExcelUtil {

    /**
     * 指定阈值
     *
     * @param consumer
     * @param threshold
     * @param <T>
     * @return
     */
    public static <T> AnalysisEventListener<T> getListener(Consumer<List<T>> consumer, int threshold) {
        return new AnalysisEventListener<T>() {
            private LinkedList<T> list = new LinkedList<T>();

            @Override
            public void invoke(T t, AnalysisContext analysisContext) {
                list.add(t);
                //达到阈值后,执行consumer
                if (threshold == list.size()) {
                    consumer.accept(list);
                    list.clear();
                }
            }

            @Override
            public void doAfterAllAnalysed(AnalysisContext analysisContext) {
                if (list.size() > 0) {
                    consumer.accept(list);
                }
            }
        };
    }

    /**
     * 未指定阈值，默认10
     *
     * @param consumer
     * @param <T>
     * @return
     */
    public static <T> AnalysisEventListener<T> getListener(Consumer<List<T>> consumer) {
        return getListener(consumer, 10);
    }
}
