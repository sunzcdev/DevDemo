package com.cnjaj.myapplication.rx.rx;

import android.support.annotation.NonNull;
import com.cnjaj.myapplication.utils.SLog;
import rx.Observable;
import rx.android.schedulers.AndroidSchedulers;
import rx.functions.Action1;
import rx.subjects.PublishSubject;
import rx.subjects.Subject;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import java.util.concurrent.ConcurrentHashMap;

/**
 * 用RxJava实现的EventBus
 *
 * @author baixiaokang
 */
class RxBus {
    private static RxBus instance;
    @SuppressWarnings("rawtypes")
    private ConcurrentHashMap<Object, List<Subject>> subjectMapper = new ConcurrentHashMap<>();

    private RxBus() {
    }

    static synchronized RxBus $() {
        if (null == instance) {
            instance = new RxBus();
        }
        return instance;
    }

    @SuppressWarnings("rawtypes")
    private boolean isEmpty(Collection<Subject> collection) {
        return null == collection || collection.isEmpty();
    }

    /**
     * 订阅事件源
     *
     * @param mObservable
     * @param mAction1
     * @return
     */
    RxBus OnEvent(Observable<?> mObservable, Action1<Object> mAction1) {
        mObservable.observeOn(AndroidSchedulers.mainThread()).subscribe(mAction1, new Action1<Throwable>() {
            @Override
            public void call(Throwable throwable) {
                throwable.printStackTrace();
            }
        });
        return $();
    }

    /**
     * 注册事件源
     *
     * @param tag
     * @return
     */
    @SuppressWarnings({"rawtypes"})
    <T> Observable<T> register(@NonNull Object tag) {
        List<Subject> subjectList;
        if (subjectMapper.containsKey(tag)) {
            subjectList = subjectMapper.get(tag);
        } else {
            subjectList = new ArrayList<>();
            subjectMapper.put(tag, subjectList);
        }
        Subject<T, T> subject = PublishSubject.create();
        subjectList.add(subject);
        SLog.d("register", tag + "  size:" + subjectList.size());
        return subject;
    }

    @SuppressWarnings("rawtypes")
    void unregister(@NonNull Object tag) {
        List<Subject> subjects = subjectMapper.get(tag);
        if (null != subjects) {
            subjectMapper.remove(tag);
        }
    }

    /**
     * 取消监听
     *
     * @param tag
     * @param observable
     * @return
     */
    @SuppressWarnings("rawtypes")
    RxBus unregister(@NonNull Object tag,
                     @NonNull Observable<?> observable) {
        if (subjectMapper.containsKey(tag)) {
            List<Subject> subjects = subjectMapper.get(tag);
            if (subjects.contains((Subject) observable)) {
                subjects.remove((Subject) observable);
            } else {
                subjectMapper.remove(tag);
                SLog.d("unregister", tag + "  size:" + subjects.size());
            }
        }
        return $();
    }

    public void post(@NonNull Object content) {
        post(content.getClass().getName(), content);
    }

    /**
     * 触发事件
     *
     * @param content
     */
    @SuppressWarnings({"unchecked", "rawtypes"})
    void post(@NonNull Object tag, @NonNull Object content) {
        SLog.d("post", "eventName: " + tag);
        List<Subject> subjectList = subjectMapper.get(tag);
        if (!isEmpty(subjectList)) {
            for (Subject subject : subjectList) {
                subject.onNext(content);
                SLog.d("onEvent", "eventName: " + tag);
            }
        }
    }
}
