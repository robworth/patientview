package com.worthsoln.batch;

import org.springframework.aop.support.AopUtils;
import org.springframework.batch.item.ItemReader;

import java.util.ArrayList;
import java.util.List;

/**
 *
 */
public class ListItemReader<T> implements ItemReader<T> {

    private List<T> list;

    public List<T> getList() {
        return list;
    }

    public void setList(List<T> list) {
        if (AopUtils.isAopProxy(list)) {
            this.list = list;
        } else {
            this.list = new ArrayList<T>(list);
        }
    }

    @Override
    public T read() {

        if (!list.isEmpty()) {
            T remove = list.remove(0);
            while (remove == null && !list.isEmpty()) {
                remove = list.remove(0);
            }
            return remove;
        }
        return null;
    }
}
